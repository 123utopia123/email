package com.email.xingxingemail.demo;

public class MailFactory {
    public BasedMailSenderProcessor getMail(String key) {
        if (key == null) {
            return null;
        } else if (key.contains("CONTENT")) {
            return new ContentMailProcessor();
        } else if (key.equalsIgnoreCase("PICTURE")) {
            return new PictureMailProcessor();

        } else if (key.equalsIgnoreCase("TABLE")) {
            return new TableMailProcessor();
        } else if (key.equalsIgnoreCase("ATTACHMENT")) {
            return new AttachmentMailProcessor();
        } else if (key.equalsIgnoreCase("PICTUREATTACHMENT")) {
            return new PictureAttachmentMailProcessor();
        } else if (key.equalsIgnoreCase("PICTURETABLE")) {
            return new PictureTableMailProcessor();
        }else if (key.equalsIgnoreCase("TABLEPICTURE")) {
            return new TablePictureMailProcessor();
        }else if (key.equalsIgnoreCase("TABLEATTACHMENT")) {
            return new TableAttachmentMailProcessor();
        }else if (key.equalsIgnoreCase("PictureTableAttachment")) {
            return new PictureTableAttachmentMailProcessor();
        }else if (key.equalsIgnoreCase("TablePictureAttachment")) {
            return new TablePictureAttachmentMailProcessor();
        }
            return null;
        }

}