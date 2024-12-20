package com.blcoder.competitionmanager.utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class VaildateCodeUtils {
    // 发件人的邮箱和 SMTP 配置
    private static final String SMTP_HOST = "smtp.qq.com";  // QQ 邮箱的 SMTP 服务器地址
    private static final String SMTP_PORT = "587";  // QQ 邮箱的 SMTP 端口
    private static final String USERNAME = "1692632285@qq.com";  // 发件人的 QQ 邮箱地址
    private static final String PASSWORD = "nclynjigdivwcchf";  // QQ 邮箱授权码

    // 发送邮件的方法
    public static boolean sendVerificationCode(String toEmail, String code) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");  // 启用 TLS

        // 创建一个会话对象
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            // 创建邮件内容
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("验证码");
            message.setText("您的验证码是: " + code);

            // 发送邮件
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 生成4位随机验证码
    public static String generateVerificationCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            code.append((int) (Math.random() * 10));  // 生成0-9之间的随机数字
        }
        return code.toString();
    }
}
