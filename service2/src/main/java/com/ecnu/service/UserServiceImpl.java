package com.ecnu.service;

import com.ecnu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by kun on 17-6-26.
 */
@Component
public class UserServiceImpl implements UserService{
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public boolean addUser(User user){
        try {
            String insertSql = "insert into user(loginId,name,passwd) values(?,?,?)";
            jdbcTemplate.update(insertSql, user.getLoginId(), user.getName(), user.getPasswd());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserbyName(String name) {
        String sql="select * from user where name=?";
        return jdbcTemplate.queryForObject(sql,User.class,name);
    }

    @Override
    public int register(User user) {
        String sql = "SELECT * from user WHERE loginId=?";
        List<User> dbUsers = jdbcTemplate.query(sql, new Object[]{user.getLoginId()}, new BeanPropertyRowMapper<User>(User.class));
        //判断用户是否存在
        if (dbUsers.size() == 0) {
            addUser(user);
            return 200;
        } else {
            return 0;
        }
    }

    @Override
    public int login(User user) {

        String sql="select * from user where loginId=?";
        List<User> dbUsers=jdbcTemplate.query(sql,new Object[]{user.getLoginId()}, new BeanPropertyRowMapper<User>(User.class));
        //若获取失败
        if (dbUsers.size()==0) {
            return 0;
        }
        //获取成功后，将获取用户的密码和传入密码对比
        else if (!dbUsers.get(0).getPasswd().equals(user.getPasswd())){
            return 0;
        }
        else {
            //若密码也相同则登陆成功
            //让传入用户的属性和数据库保持一致
            user.setLoginId(dbUsers.get(0).getLoginId());
            user.setPasswd(dbUsers.get(0).getPasswd());
            user.setId(dbUsers.get(0).getId());
            return 200;
        }
    }
}
