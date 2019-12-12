package cn.nyse.util;

import com.google.code.kaptcha.servlet.KaptchaServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * 生成验证码工具：
 * 1.是一个servlet，需要继承KaptchaServlet
 * 2.通过WebServlet指定url请求对应响应的servlet
 */
@WebServlet(urlPatterns="/getCode.jpg",
        initParams = {
                @WebInitParam(name="kaptcha.image.width",value = "120"),
                @WebInitParam(name="kaptcha.image.height",value = "32"),
                @WebInitParam(name="kaptcha.textproducer.char.length",value = "4"),
                @WebInitParam(name = "kaptcha.textproducer.font.color",value = "black"),
                @WebInitParam(name = "kaptcha.background.clear.from",value = "yellow"),
                @WebInitParam(name = "kaptcha.background.clear.to",value = "green"),
                @WebInitParam(name = "kaptcha.noise.color",value = "white"),
                @WebInitParam(name = "kaptcha.session.key",value = "checkCode"),
                @WebInitParam(name = "kaptcha.textproducer.font.size",value = "28"),
                @WebInitParam(name = "kaptcha.textproducer.char.string",value = "1234567890"),
                @WebInitParam(name = "kaptcha.textproducer.font.names",value = "Monospaced"),
                @WebInitParam(name = "kaptcha.obscurificator.impl",value = "com.google.code.kaptcha.impl.ShadowGimpy")
        }
)
public class CodeUtil extends KaptchaServlet {


}
