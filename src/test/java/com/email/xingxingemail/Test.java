package com.email.xingxingemail;

import com.email.xingxingemail.demo.*;
import com.email.xingxingemail.dto.MailReciver;
import com.email.xingxingemail.dto.MailSender;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您想发送的邮件类型：");
        String key =sc.next();
        MailFactory mailFactory = new MailFactory();
        BasedMailSenderProcessor typeMail = mailFactory.getMail(key);
//        typeMail.doProcess(MailReciver.builder().build(), MailSender.builder().build(),
//                new HashMap<>(),new String(),new HashSet<String>());

    }
}
