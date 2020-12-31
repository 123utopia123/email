package com.email.xingxingemail;


import com.email.xingxingemail.dto.MailContent;
import com.email.xingxingemail.dto.MailReciver;
import com.email.xingxingemail.dto.MailSender;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class MailSenderTest extends BaseSenderTest{

    @Test
    public void sendTextTest() {
        String content = "<p><b>这是一张图片</b></p><br/>";
        MailSender sender = buildSender();
        MailReciver reciver = buildReciver();
        MailContent mailContent = new MailContent();
        mailContent.setMailContent(content);
        reciver.setMailContent(Collections.singletonList(mailContent));
        CompleteEmailUtil.send(sender, reciver);
    }

    @Test
    public void sendAttachmentTest() {
        String content = "<p><b>混合附件邮件</b></p><br/>";
        String attachmentAddress = "E:\\网络下载\\Redis-x64-3.2.100.zip";
        MailSender sender = buildSender();
        MailReciver reciver = buildReciver();
        MailContent mailContent = new MailContent();
        mailContent.setMailContent(content);
        reciver.setMailAttachment(Collections.singletonList(attachmentAddress));
        reciver.setMailContent(Collections.singletonList(mailContent));
        CompleteEmailUtil.send(sender, reciver);
    }

    @Test
    public void sendPicTest() {
        String content = "<p><b>混合图片邮件</b></p><br/>";
        String pictureAddress = "E:\\网络下载\\猫咪.jpg";
        MailSender sender = buildSender();
        MailReciver reciver = buildReciver();
        MailContent mailContent = new MailContent();
        mailContent.setMailContent(content);
        mailContent.setMailPicture(pictureAddress);
        reciver.setMailContent(Collections.singletonList(mailContent));
        CompleteEmailUtil.send(sender, reciver);
    }


    @Test
    public void sendTableTest() {
        List<List<String>> bigList = buildTable();
        String content = "<p><b>混合表格邮件</b></p><br/>";
        MailSender sender = buildSender();
        MailReciver reciver = buildReciver();
        MailContent mailContent = new MailContent();
        mailContent.setMailContent(content);
        mailContent.setMailTable(bigList);

        reciver.setMailContent(Collections.singletonList(mailContent));
        CompleteEmailUtil.send(sender, reciver);
    }

    @Test
    public void sendAllTest() {
        List<List<String>> bigList = buildTable();
        String content = "<p><b>混合所有邮件</b></p><br/>";
        String pictureAddress = "E:\\网络下载\\猫咪.jpg";
        String attachmentAddress = "E:\\网络下载\\Redis-x64-3.2.100.zip";

        MailSender sender = buildSender();
        MailReciver reciver = buildReciver();
        MailContent mailContent = new MailContent();
        mailContent.setMailContent(content);
        mailContent.setMailTable(bigList);
        mailContent.setMailPicture(pictureAddress);

        reciver.setMailContent(Collections.singletonList(mailContent));
        reciver.setMailAttachment(Collections.singletonList(attachmentAddress));

        CompleteEmailUtil.send(sender, reciver);
    }

}
