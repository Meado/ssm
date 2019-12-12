package cn.nyse.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.mapper.session.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * spring整合mybatis配置：
 * 1.配置数据源
 * a.读取配置文件对象
 * b.properties获取属性，注入数据源属性
 * 2.配置SqlSessionFactoryBean
 * 3.配置MapperScannerConfigurature
 *
 *
 *
 * spring整合事务：
 * 1.开启服务层包扫描，扫描服务层
 * 2.配置事务管理器
 * 3.开始事务注解支持
 * 4.服务层使用事务注解
 */
@org.springframework.context.annotation.Configuration
@MapperScan(basePackages="cn.nyse.dao")//配置MapperScannerConfigurature
@ComponentScan(basePackages={"cn.nyse.service","cn.nyse.util"})//开启服务层包扫描，扫描服务层
@EnableTransactionManagement//开始事务注解支持
public class SpringMybatisConfig {


//1.配置数据源
    @Bean
    public DruidDataSource getDataSource(){
        Properties properties = new Properties();
        try {
            properties.load(SpringMybatisConfig.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.configFromPropety(properties);//自动配置
        return druidDataSource;

    }

    //2.配置SqlSessionFactoryBean
    @Bean
    public SqlSessionFactory getFactoryBean(DruidDataSource dataSource){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //设置数据源
        factoryBean.setDataSource(dataSource);

        //设置分页插件   分页拦截器  类似过滤器
        PageInterceptor pageInterceptor = new PageInterceptor();
        pageInterceptor.setProperties(new Properties());//调用设置属性方法，进行方言设置
        factoryBean.setPlugins(new Interceptor[]{pageInterceptor});

        //设置驼峰命名转换
        Configuration configuration = new Configuration();
        configuration.setMapUnderscoreToCamelCase(true);

        configuration.setCallSettersOnNulls(true);//解决map返回字段null不显示key的问题
        factoryBean.setConfiguration(configuration);


        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //配置事务管理器
    @Bean
    public DataSourceTransactionManager getTransactrionManager(DruidDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}
