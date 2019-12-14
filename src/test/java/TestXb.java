import cn.nyse.config.SpringMybatisConfig;
import cn.nyse.controller.MainController;
import cn.nyse.dao.UserMapper;
import cn.nyse.entity.User;
import cn.nyse.service.UserService;
import cn.nyse.util.KeyUtil;
import cn.nyse.util.SendMailUtil;
import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatisConfig.class)
public class TestXb {
    @Autowired
    private UserService service;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private DataSource dataSource;


    @Test
    public void testSelectOne(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");

        User user1 = service.selectOne(user);
        System.out.println(user1);
    }
    
    @Test
    public void testSelectAll(){
        List<User> users = mapper.selectAll();
    }

    @Test
    public void getConection(){
        Properties properties = new Properties();
        try {
            properties.load(SpringMybatisConfig.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.configFromPropety(properties);//自动配置
        System.out.println(druidDataSource);
    }

    @Test
    public void testInsertOne(){
        User user = new User();
        user.setEmail("123456@ailiyun");
        user.setUsername("小测");
        user.setPassword("123456");
        int i = service.insertSelective(user);
        System.out.println(i);
    }

    @Test
    public void testKeyUtil(){
        String s = KeyUtil.keyUtil();
        System.out.println(s);
    }

}
