package cn.nyse.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送工具类
 */
public class SendMailUtil {

    public static Boolean sendLoginMail(String receiveEmail,String keyCode){
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.qq.com");// smtp服务器地址

        try {
            Session session = Session.getInstance(props);
            session.setDebug(true);

            Message msg = new MimeMessage(session);
            msg.setSubject("小标交友登录密码修改");
            msg.setText("您好，你正在申请修改小标交友的登录密码，如果是您本人的操作请记下该验证码："+keyCode+";如果不是您本人的操作，请忽略！！！");
            msg.setFrom(new InternetAddress("1159437090@qq.com"));//发件人邮箱
            msg.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(receiveEmail)); //收件人邮箱
            msg.saveChanges();

            Transport transport = session.getTransport();
            transport.connect("1159437090@qq.com", "lurtlywtggnticje");//发件人邮箱,授权码(可以在邮箱设置中获取到授权码的信息)

            transport.sendMessage(msg, msg.getAllRecipients());

            System.out.println("邮件发送成功...");
            transport.close();
        } catch (MessagingException e) {
            return false;
        }
        return true;
    }

}
