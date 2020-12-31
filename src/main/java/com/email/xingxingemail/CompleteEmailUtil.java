package com.email.xingxingemail;

import com.email.xingxingemail.dto.MailContent;
import com.email.xingxingemail.dto.MailReciver;
import com.email.xingxingemail.dto.MailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 创建并发送一封包含文本、图片、表格、附件的复杂邮件
 * JavaMail 版本: 1.6.0
 * JDK 版本: JDK 1.8
 */

@Slf4j
public class CompleteEmailUtil {

    public static boolean send(MailSender sender, MailReciver reciver) {
        boolean success = true;
        Properties props = CompleteEmailUtil.initProperties(sender.getMailHost());
        // 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);         // 设置为debug模式, 可以查看详细的发送 log
        Transport transport = null;
        try {
            // 创建一封邮件
            MimeMessage message = createMimeMessage(session, sender.getMailAccount(), sender.getMailSenderName(), reciver);

            // 根据 Session 获取邮件传输对象
            transport = session.getTransport();

            // 使用 邮箱账号 和 密码 连接邮件服务器
            // 这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
            transport.connect(sender.getMailAccount(), sender.getMailPassword());

            // 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {
            log.error("发送失败", e);
            success = false;
        } finally {
            if (transport != null) {
                // 7. 关闭连接
                try {
                    transport.close();
                    log.info("close successfully");
                } catch (MessagingException e) {
                    log.error(e.getMessage(), e.getCause(), e.toString());
                    e.printStackTrace();
                    success = false;
                }
            }
        }
        return success;
    }

    public static Properties initProperties(String myEmailSMTPHost) {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", myEmailSMTPHost);
        properties.setProperty("mail.smtp.auth", "true");
        return properties;
    }

    /**
     * 创建一封复杂邮件（文本+图片+表格+附件）
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String senderName, MailReciver receiver) throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);

        // From: 发件人
//        message.setFrom(new InternetAddress(sendMail, "测试邮件", "UTF-8"));
        message.setFrom(new InternetAddress(sendMail, senderName, "UTF-8"));

        // To: 收件人（可以增加多个收件人、抄送、密送）
//        message.addRecipient(RecipientType.TO, new InternetAddress(String.join(";", receiver.getMailReviver()), "UTF-8"));
         message.addRecipients(RecipientType.TO, String.join(";", receiver.getMailReviver()));   //收件人
          message.addRecipients(RecipientType.CC,String.join(";",receiver.getMailCc()));     //抄送

        // Subject: 邮件主题
//        message.setSubject("TEST复杂邮件有图片（文本+图片+表格+附件）", "UTF-8");
        message.setSubject(receiver.getMailSubject(), "UTF-8");

        //  设置 文本 和 图片 “节点”的关系（将 文本 和 图片 “节点”合成一个混合“节点”）
        MimeMultipart mm_text_image = new MimeMultipart();

        receiver.getMailContent().forEach(c -> {
            // 创建文本“节点”
            try {
                buildTextPart(c, mm_text_image);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            // 创建表格“节点”
            try {
                buildTablePart(c, mm_text_image);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            // 创建图片“节点”
            try {
                buildImagePart(c, mm_text_image);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        });
        // 创建附件“节点”
        buildAttatchmentPart(receiver.getMailAttachment(), mm_text_image);

        //  将 文本+图片 的混合“节点”封装成一个普通“节点”
        // 最终添加到邮件的 Content 是由多个 BodyPart 组成的 Multipart, 所以我们需要的是 BodyPart,
        // 上面的 mm_text_image 并非 BodyPart, 所以要把 mm_text_image 封装成一个 BodyPart
        MimeBodyPart text_image = new MimeBodyPart();
        text_image.setContent(mm_text_image);

        //  设置（文本+图片+表格）和 附件 的关系（合成一个大的混合“节点” / Multipart ）
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text_image);
        mm.setSubType("mixed");   // 混合关系

        // 设置整个邮件的关系（将最终的混合“节点”作为邮件的内容添加到邮件对象）
        message.setContent(mm);

        //  设置发件时间
        message.setSentDate(new Date());

        // 保存上面的所有设置
        message.saveChanges();

        return message;
    }

    private static void buildTablePart(MailContent content, MimeMultipart mm_text_image) throws MessagingException {
        if (content.getMailTable() != null && !content.getMailTable().isEmpty()) {
            MimeBodyPart table = new MimeBodyPart();
            String tableContent = FreeMarkerTemplateUtil.getTableHtml(content.getMailTable());
            table.setContent(tableContent, "text/html;charset=UTF-8");
            mm_text_image.addBodyPart(table);
        }
    }

    private static void buildAttatchmentPart(List<String> attachmentList, MimeMultipart mm_text_image) throws MessagingException, UnsupportedEncodingException {
        if (attachmentList != null && !attachmentList.isEmpty()) {
            for (String s : attachmentList) {
                MimeBodyPart attachment = new MimeBodyPart();
                DataHandler dh2 = new DataHandler(new FileDataSource(s)); // 读取本地文件
                attachment.setDataHandler(dh2);            // 将附件数据添加到“节点”
                attachment.setFileName(MimeUtility.encodeText(dh2.getName()));    // 设置附件的文件名（需要编码）
                mm_text_image.addBodyPart(attachment);
            }
        }
    }

    private static void buildTextPart(MailContent content, MimeMultipart mm_text_image) throws MessagingException {
        if (!StringUtils.isEmpty(content.getMailContent())) {
            MimeBodyPart text = new MimeBodyPart();
            text.setContent(content.getMailContent(), "text/html;charset=UTF-8");
            mm_text_image.addBodyPart(text);
        }
    }

    /**
     * <img src='cid:cat'/>
     *
     * @param content
     * @param mm_text_image
     * @throws MessagingException
     */
    private static void buildImagePart(MailContent content, MimeMultipart mm_text_image) throws MessagingException {
        if (!StringUtils.isEmpty(content.getMailPicture())) {
            String uid = UUID.randomUUID().toString();
            MimeBodyPart image1 = new MimeBodyPart();
            DataHandler dh = new DataHandler(new FileDataSource(content.getMailPicture())); // 读取本地文件
            image1.setDataHandler(dh);     // 将图片数据添加到“节点”
            image1.setContentID(uid);  // 为“节点”设置一个唯一编号（在文本“节点”将引用该ID）
            mm_text_image.addBodyPart(image1);
            MimeBodyPart text = new MimeBodyPart();
            text.setContent("<p><img src='cid:" + uid + "'/></p><br>", "text/html;charset=UTF-8");
            mm_text_image.addBodyPart(text);
            mm_text_image.setSubType("related"); // 关联关系
        }
    }
}
