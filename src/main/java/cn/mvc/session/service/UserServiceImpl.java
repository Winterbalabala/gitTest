package cn.mvc.session.service;

import cn.mvc.session.dao.FactoryDao;
import cn.mvc.session.dao.UserDao;
import cn.mvc.session.model.User;
import cn.mvc.session.utils.JdbcUtils;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService{

    UserDao userDao = FactoryDao.getUserDao();

    @Override
    public int save(User user) {
        return userDao.save(user);
    }

    @Override
    public int deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public int updateUserById(User user) {
        return userDao.updateUserById(user);
    }

    @Override
    public User get(int id) {
        return userDao.get(id);
    }

    @Override
    public User getTraansation(int id) {
        Connection conn = null;
        User user = null;
        try{
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);//开始事务
            user = userDao.get(conn,id);
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            JdbcUtils.rollbackTransation(conn);//回滚
        }
        return user;
    }

    @Override
    public List<User> getlistAll() {
        return userDao.getlistAll();
    }

    @Override
    public long getCountByUsername(String username) {
        return userDao.getCountByUserame(username);
    }

    @Override
    //模糊查询
    public List<User> query(String username) {
        return userDao.query(username);
    }

    @Override
    public User getUserByUp(String username, String pasword) {
        return null;
    }

    @Override

    public User login(String username, String pasword) {
        return userDao.getUserByUp(username,pasword);
    }
}
