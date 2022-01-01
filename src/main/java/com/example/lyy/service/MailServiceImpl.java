package com.example.lyy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Component
@Service
public class MailServiceImpl implements IMailService {
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Value("${spring.mail.from}")
    private String from;
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        try {
            Thread.sleep(1000);
            //创建SimpleMailMessage对象
            SimpleMailMessage message = new SimpleMailMessage();
            //邮件发送人
            message.setFrom(from);
//            邮件时间
           message.setSentDate(new Date());
            //邮件接收人
            message.setTo(to);
            //邮件主题
            message.setSubject(subject);
            //邮件内容
            message.setText(content);
            //发送邮件
            mailSender.send(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * html邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        //获取MimeMessage对象
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            //邮件发送人
            messageHelper.setFrom(from);
            //邮件接收人
            messageHelper.setTo(to);
            //邮件主题
            message.setSubject(subject);
            //邮件内容，html格式
            messageHelper.setText(content, true);
            //发送
            mailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("发送失败");
        }
    }

    @Override
    public void sendFileMail(String to, String subject, String content) throws IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            //邮件发送人
            messageHelper.setFrom(from);
            //邮件接收人
            messageHelper.setTo(to);
            //邮件主题
            message.setSubject(subject);

            MimeBodyPart text = new MimeBodyPart();
            text.setContent(content, "text/html;charset=UTF-8");
            // 图片内容
            MimeBodyPart image = new MimeBodyPart();
            image.setDataHandler(new DataHandler(new FileDataSource("C:\\Users\\lyy\\Desktop\\IMG_7520.jpg")));
            image.setContentID("gzh.jpg");
            //附件内容
            MimeBodyPart attach = new MimeBodyPart();
            DataHandler file = new DataHandler(new FileDataSource("C:\\Users\\lyy\\Desktop\\IMG_7520.jpg"));
            attach.setDataHandler(file);
            attach.setFileName(file.getName());
            //关系:正文和图片
            MimeMultipart multipart1 = new MimeMultipart();
            multipart1.addBodyPart(text);
            multipart1.addBodyPart(image);
            multipart1.setSubType("related");
            //关系:正文和附件
            MimeMultipart multipart2 = new MimeMultipart();
            multipart2.addBodyPart(attach);
            // 全文内容
            MimeBodyPart body = new MimeBodyPart();
            body.setContent(multipart1);
            multipart2.addBodyPart(body);
            multipart2.setSubType("mixed");
            // 封装 MimeMessage 对象
            message.setContent(multipart2);
            message.saveChanges();
            //返回创建好的邮件对象
            //发送
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}