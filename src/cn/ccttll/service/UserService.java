package cn.ccttll.service;

import cn.ccttll.bean.User;
import cn.ccttll.dao.UserDao;

public class UserService {
    private UserDao userDao;

    public UserService(){

        userDao=new UserDao();
    }


    public boolean insertUser(User user) {
       return userDao.insertUser(user);
    }

    public String login(String userName, String userPassword) {
        return userDao.login(userName,userPassword);
    }
}
