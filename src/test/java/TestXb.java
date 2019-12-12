import cn.nyse.config.SpringMybatisConfig;
import cn.nyse.controller.MainController;
import cn.nyse.dao.UserMapper;
import cn.nyse.entity.User;
import cn.nyse.service.UserService;
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

}
