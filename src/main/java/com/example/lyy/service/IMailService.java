package com.example.lyy.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

public interface IMailService {
    /**
     * 发送文本邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送HTML邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendHtmlMail(String to, String subject, String content);
    public void sendFileMail(String to, String subject, String content) throws IOException;

}