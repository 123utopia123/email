package com.email.xingxingemail.demo;

import com.email.xingxingemail.dto.MailContent;
import com.email.xingxingemail.dto.MailReciver;
import com.email.xingxingemail.dto.MailSender;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MailSenderInterface {
    void process(MailSender mailSender, Map<String, Object> content,
                 String subject, String reciverAddress,String ccAddress);
}
