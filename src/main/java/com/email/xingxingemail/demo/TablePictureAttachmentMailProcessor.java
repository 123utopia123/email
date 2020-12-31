package com.email.xingxingemail.demo;

import com.email.xingxingemail.CompleteEmailUtil;
import com.email.xingxingemail.dto.MailContent;
import com.email.xingxingemail.dto.MailReciver;
import com.email.xingxingemail.dto.MailSender;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TablePictureAttachmentMailProcessor extends BasedMailSenderProcessor {
    @Override
    public void doProcess(Map<String, Object> content,
                          String subject, String reciverAddress, String ccAddress) {
//        图片邮件处理过程
        //String content = "<p><b>混合图片邮件</b></p><br/>";
        //String pictureAddress = "E:\\网络下载\\猫咪.jpg";
        MailSender sender = buildSender();
        MailContent mailContent = new MailContent();
        mailContent.setMailContent((String) content.get("content"));
        mailContent.setMailTable((List<List<String>>) content.get("table"));
        mailContent.setMailPicture((String) content.get("pictureAddress"));
        MailReciver reciver = buildReciver(Collections.singletonList(mailContent),subject,reciverAddress,ccAddress);
        reciver.setMailAttachment((List<String>) Arrays.asList(
                ((String) content.get("attachmentAddress"))
                        .split(",")));

        boolean sendResult = CompleteEmailUtil.send(sender, reciver);
        if (!sendResult) {
            System.out.println("send mail fail");
            return;
        }
        System.out.println("send mail success");
    }


}
