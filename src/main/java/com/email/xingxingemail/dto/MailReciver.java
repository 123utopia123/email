package com.email.xingxingemail.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Set;

/**
 * @author 郭星星
 * @create 2020-08-10 9:04
 */
@Builder
@Data
@ToString
public class MailReciver {
    /**
     * 收件人
     * */
    private Set<String> mailReviver;
    /**
     * 抄送人
     * */
    private Set<String> mailCc;
    /**
     * 主题
     * */
    public String mailSubject;
    /**
     * 内容
     */
    public List<MailContent> mailContent;
    /**
     * 附件
     * */
    public List<String> mailAttachment;
}
