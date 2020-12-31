package com.email.xingxingemail.controller;

import com.email.xingxingemail.demo.BasedMailSenderProcessor;
import com.email.xingxingemail.demo.MailFactory;
import com.email.xingxingemail.dto.MailVO;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuickController {

    @PostMapping("/test")
    public void test(@RequestBody MailVO vo){
        MailFactory mailFactory = new MailFactory();
        BasedMailSenderProcessor typeMail = mailFactory.getMail(vo.getType());

        typeMail.doProcess(vo.getContent(),vo.getSubject(),vo.getReciverAddress(),vo.getCcAddress());
    }
}
