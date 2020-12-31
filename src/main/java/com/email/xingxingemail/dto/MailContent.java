package com.email.xingxingemail.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class MailContent {
    /**
     * 文本
     * */
    public String mailContent;
    /**
     * 图片路径
     * */
    public String mailPicture;
    /**
     * 表格
     * */
    public List<List<String>> mailTable;

}
