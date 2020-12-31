package com.email.xingxingemail.demo;

import com.email.xingxingemail.dto.MailContent;
import com.email.xingxingemail.dto.MailReciver;
import com.email.xingxingemail.dto.MailSender;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public abstract class BasedMailSenderProcessor implements MailSenderInterface {

    @Override
    public void process(MailSender mailSender, Map<String, Object> content,
                        String subject,String reciverAddress,String ccAddress) {
      log.info("ready to process mail request!,mailReciver:{},mailSender:{},mailContent:{}",
              mailSender.toString());
      try{
          doProcess(content, subject, reciverAddress,ccAddress);
      } catch (Exception e) {
          log.info("error");
      }
      return;
    }

    public abstract void doProcess(Map<String, Object> content,
                                   String subject,String reciverAddress,String ccAddress);

    //发件人信息不变
    public MailSender buildSender() {
        String senderAddress = "2059701820@qq.com";
        String password = "fjlwdcqgkrjbbega";
        String senderHost = "smtp.qq.com";
        String senderName = "星星";
        int senderPort = 433;
        return MailSender.builder()
                .mailHost(senderHost)
                .mailPort(senderPort)
                .mailAccount(senderAddress)
                .mailPassword(password)
                .mailSenderName(senderName)
                .build();
    }

    public MailReciver buildReciver(List<MailContent> mailContents, String subject, String reciverAddress,String ccAddress) {
             return MailReciver.builder()
                .mailReviver(Collections.singleton(reciverAddress))
                .mailCc(Collections.singleton(ccAddress))
                .mailSubject(subject)
                .mailContent(mailContents)
                .build();
    }
}
