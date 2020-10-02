package cn.mvc.session.dao;

import cn.mvc.session.model.User;

import java.sql.Connection;
import java.util.List;

/**
 * 接口制定规则，定义方法不实现方法；定义与User数据表相关的方法。
 */
public interface UserDao {
    /**
     * 实现插入一条新的用户信息
     * @param user
     * @return
     */
    public int save(User user);

    /**
     * 通过id删除
     * @param id
     * @return
     */
    public int deleteUserById(int id);

    /**
     * 通过id修改用户信息
     * @param
     * @param user
     * @return
     */
    public int updateUserById(User user);

    /**
     * 根据用户id获取第一条信息，封装成类We的一个对象
     * @param id
     * @return
     */
    public User get(int id);

    //支持事务
    public User get(Connection conn, int id);

    /**
     * 获取所有用户数据
     * @return
     */
    public List<User> getlistAll();

    /**
     * 查询指定用户名，用户的数量
     * @param name
     * @return
     */
    public long getCountByUserame(String name);

    List<User> query(String username);

    /**
     *dao实现根据用户名和密码查询用户记录的方法
     */
    public User getUserByUp(String username, String pasword);
}