package com.email.xingxingemail.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author guo
 *
 */
@Builder
@Data
@ToString
@EqualsAndHashCode
public class MailSender {
    public String mailAccount;
    public String mailPassword;
    public String mailSenderName;
    public String mailHost;
    public Integer mailPort;
}
