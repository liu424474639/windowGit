package com.bz.dao;

import com.bz.entity.User;
import com.bz.util.JdbcDateSource;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lyx on 2018/7/26.
 */
/*持久层注解*/
@Repository("userDao")
public class MysqlUserDao implements UserDao,Serializable{

    private JdbcDateSource dateSource;

    public MysqlUserDao(){

    }

    //@Autowired
    @Inject
    public void setDateSource(@Named("jdbcDateSource") JdbcDateSource dateSource) {
        this.dateSource = dateSource;
    }

    public JdbcDateSource getDateSource() {
        return dateSource;
    }

    @Override
    public User findUserByName(String name) {
        System.out.println("利用JDBC技术查找User信息");
        String sql = "select id,name,pwd,phone from users where name=?";
        Connection conn = null;
        try {
            conn = dateSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            User user = null;
            while (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPwd(rs.getString("pwd"));
                user.setPhone(rs.getString("phone"));
            }
            rs.close();
            ps.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            dateSource.close(conn);
        }
    }
}
