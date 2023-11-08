package com.example.yjm.controller;

import com.example.yjm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/material")
public class SendEmail {
    @Autowired
    private JavaMailSender javaMailSender;

    public static Map<String, String> verificationCodes = new HashMap<>();

    //读取yml文件中username的值并赋值给form
    @Value("${spring.mail.username}")
    private String from;

    @PostMapping("/sendEmail")
    public Result sendSimpleMail(@RequestParam("email") String email) throws MessagingException {
        // 1、方法一
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件发送者
        message.setFrom(from);
        // 设置邮件接收者
        message.setTo(email);
        // 设置邮件的主题
        message.setSubject("验证码");
        // 设置邮件的正文
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            int r = random.nextInt(10);
            code += r;
        }
        String text = "您的验证码为：" + code + ",请勿泄露给他人。<br/>" +
                "<h1 style='color:pink;font-size:20px'>我想对风说，你慢些经过。天上的云朵，好像也在等着日落</h1>" +
                "<img src='https://img0.baidu.com/it/u=2540662184,2552994670&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=934'/>";
        message.setText(text);
        // 2、方法二
        // MimeMessage message = javaMailSender.createMimeMessage();
        // MimeMessageHelper helper = new MimeMessageHelper(message, true);
        //
        // helper.setFrom(from);
        // helper.setTo(emailReceiver);
        // helper.setSubject("验证码");
        //
        // // 设置 HTML 格式的邮件内容
        // helper.setText("您的验证码为：" + code + ",请勿泄露给他人。<br/>" +
        //         "<h1 style='color:pink;font-size:20px'>我想对风说，你慢些经过。天上的云朵，好像也在等着日落</h1>" +
        //         "<img src='https://img1.baidu.com/it/u=2463514011,1142503686&fm=253&fmt=auto&app=138&f=JPEG?w=888&h=500'/>", true);
        // 发送邮件
        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
        System.out.println("验证码:" + code);
        verificationCodes.put(email,code);
        return Result.ok(code);
    }
}
