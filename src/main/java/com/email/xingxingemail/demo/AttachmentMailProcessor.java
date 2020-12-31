package com.email.xingxingemail.demo;

import com.email.xingxingemail.CompleteEmailUtil;
import com.email.xingxingemail.dto.MailContent;
import com.email.xingxingemail.dto.MailReciver;
import com.email.xingxingemail.dto.MailSender;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
@Component
public class AttachmentMailProcessor extends BasedMailSenderProcessor {
    public String mailProcessorKey = "attachment";

    @Override
    public void doProcess(Map<String, Object> content,
                          String subject, String reciverAddress,String ccAddress) {
//        实现附件发送；
        MailSender sender = buildSender();
        MailContent mailContent = new MailContent();
        mailContent.setMailContent((String) content.get("content"));
        if (!StringUtils.isEmpty((String)content.get("picture"))) {
            mailContent.setMailPicture((String)content.get("picture"));
        }
        if (!StringUtils.isEmpty((String)content.get("table"))) {
            mailContent.setMailPicture((String)content.get("table"));
        }
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


