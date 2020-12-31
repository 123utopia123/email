package com.email.xingxingemail.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MailVO {
    /**
     * 邮件类型
     * */
    private String type;
    /**
     * 邮件内容
     * */
    private Map<String, Object> content;
    /**
     * 邮件主题
     * */
    private String subject;
    /**
     * 收件人地址
     * */
    private String reciverAddress;
    /**
     * 抄送人地址
     * */
    private String ccAddress;
}
