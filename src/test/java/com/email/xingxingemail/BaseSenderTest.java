package com.email.xingxingemail;

import com.email.xingxingemail.dto.MailReciver;
import com.email.xingxingemail.dto.MailSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseSenderTest {

    public MailSender buildSender() {
        String senderAddress = "@qq.com";
        String password = "";
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

    public MailReciver buildReciver() {
        String reciverAddress = "@qq.com";
        String subject = "TEST邮件";
        return MailReciver.builder()
            //    .mailReviver(Collections.singleton(reciverAddress))
                .mailSubject(subject)
                .build();
    }

    public List<List<String>> buildTable() {

        List<List<String>> bigList = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        String head1 = "数据项1";
        String head2 = "数据项2";
        String head3 = "数据项3";
        //添加到第一行
        list1.add(head1);
        list1.add(head2);
        list1.add(head3);
        //添加表格
        bigList.add(list1);

        List<String> list2 = new ArrayList<>();
        String blank1 = "123";
        String blank2 = "456";
        String blank3 = "789";
        //添加到第一行
        list2.add(blank1);
        list2.add(blank2);
        list2.add(blank3);
        //添加表格
        bigList.add(list2);
        return bigList;
    }

}
