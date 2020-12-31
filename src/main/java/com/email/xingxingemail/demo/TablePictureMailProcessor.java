package com.email.xingxingemail.demo;

import com.email.xingxingemail.CompleteEmailUtil;
import com.email.xingxingemail.dto.MailContent;
import com.email.xingxingemail.dto.MailReciver;
import com.email.xingxingemail.dto.MailSender;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TablePictureMailProcessor extends BasedMailSenderProcessor{

    @Override
    public void doProcess(Map<String, Object> content,
                          String subject, String reciverAddress, String ccAddress) {
//        图片邮件处理过程
         MailSender sender = buildSender();
        MailContent mailContent = new MailContent();
        mailContent.setMailContent((String) content.get("content"));
        mailContent.setMailTable((List<List<String>>) content.get("table"));
        mailContent.setMailPicture((String) content.get("pictureAddress"));
        MailReciver reciver = buildReciver(Collections.singletonList(mailContent),subject,reciverAddress,ccAddress);
        // CompleteEmailUtil.send(sender, reciver);
        boolean sendResult = CompleteEmailUtil.send(sender, reciver);
        if (!sendResult) {
            System.out.println("send mail fail");
            return;
        }
        System.out.println("send mail success");
    }
}
