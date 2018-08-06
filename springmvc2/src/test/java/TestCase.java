import com.bz.dao.MysqlUserDao;
import com.bz.dao.UserDao;
import com.bz.entity.User;
import com.bz.service.UserService;
import com.bz.util.JdbcDateSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by lyx on 2018/7/26.
 */
public class TestCase {
    @Test
    public void testUserService(){
        String conf = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
        System.out.println(ac);
//        获取userService实例
        UserService userService = ac.getBean("userService",UserService.class);
//        调入登录方法，测试自动注入结果
        User tom = userService.login("tom","123");
        System.out.println(tom);
    }

    @Test
    public void testUserDao() {
        String conf = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
        System.out.println(ac);
        UserDao userDao = ac.getBean("userDao", UserDao.class);
        UserService userService = ac.getBean("userService", UserService.class);
        System.out.println(userDao);
        System.out.println(userDao == userService);
    }
    @Test
    public void testJDBCDateSource() {
        String conf = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
        System.out.println(ac);
        JdbcDateSource jdbcDateSource = ac.getBean("jdbcDateSource", JdbcDateSource.class);
        MysqlUserDao userDao = ac.getBean("userDao",MysqlUserDao.class);
        System.out.println(jdbcDateSource);
        System.out.println(jdbcDateSource == userDao.getDateSource());
        User user = userDao.findUserByName("tom");
    }

    @Test
    public void testJdbcProps() throws SQLException {
        String conf = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
        System.out.println(ac);
        Properties props = ac.getBean("jdbcProps",Properties.class);
        JdbcDateSource dateSource = ac.getBean("jdbcDateSource",JdbcDateSource.class);
        System.out.println(props);
        System.out.println(dateSource.getDriver());
        System.out.println(dateSource.getUrl());
        System.out.println(dateSource.getUser());
        System.out.println(dateSource.getPwd());
        Connection conn = dateSource.getConnection();
        System.out.println(conn);
    }

    @Test
    public void testLogin(){
        String conf = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
        System.out.println(ac);
        UserService service = ac.getBean("userService",UserService.class);
        UserDao dao = ac.getBean("userDao",MysqlUserDao.class);
        System.out.println(dao);
        User u = service.login("tom","123");
        System.out.println(u);
    }
}
