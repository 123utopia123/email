package com.email.xingxingemail.demo;

import com.email.xingxingemail.CompleteEmailUtil;
import com.email.xingxingemail.dto.MailContent;
import com.email.xingxingemail.dto.MailReciver;
import com.email.xingxingemail.dto.MailSender;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ContentMailProcessor extends BasedMailSenderProcessor {
    public String mailProcessorKey = "content";

    @Override
    public void doProcess(Map<String, Object> content,
                          String subject, String reciverAddress,String ccAddress) {
//        只有内容的邮件
//        String content = "<p><b>这是一个文本邮件</b></p><br/>";
        MailSender sender = buildSender();
        MailContent mailContentText = new MailContent();
        List<MailContent> mailContents = null;
        MailReciver reciver = buildReciver(mailContents,subject,reciverAddress,ccAddress);
        mailContentText.setMailContent((String) content.get("content"));
        reciver.setMailContent(Collections.singletonList(mailContentText));

         boolean sendResult = CompleteEmailUtil.send(sender, reciver);
        if (!sendResult) {
            System.out.println("send mail fail");
            return;
        }
        System.out.println("send mail success");
    }

}

