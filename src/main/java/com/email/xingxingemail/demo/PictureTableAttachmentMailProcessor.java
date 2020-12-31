package com.email.xingxingemail.demo;

import com.email.xingxingemail.CompleteEmailUtil;
import com.email.xingxingemail.dto.MailContent;
import com.email.xingxingemail.dto.MailReciver;
import com.email.xingxingemail.dto.MailSender;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PictureTableAttachmentMailProcessor extends BasedMailSenderProcessor{

    @Override
    public void doProcess(Map<String, Object> content,
                          String subject, String reciverAddress, String ccAddress) {
        MailSender sender = buildSender();
        MailContent mailContent = new MailContent();
        mailContent.setMailContent((String) content.get("content"));
        mailContent.setMailPicture((String) content.get("pictureAddress"));
        mailContent.setMailTable((List<List<String>>) content.get("table"));
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
