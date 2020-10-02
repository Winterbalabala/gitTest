package cn.mvc.session.dao;

import cn.mvc.session.model.User;

import java.sql.Connection;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    //添加用户
    @Override
    public int save(User user) {
        String sql ="INSERT INTO `user` (`username`,`pasword`,`reg_date`)VALUES(?,?,?);";
        return super.update(sql,user.getUsername(),user.getPasword(),user.getRegDate());
    }

    //删除用户
    @Override
    public int deleteUserById(int id) {
        String sql = "DELETE FROM `user` WHERE `id`=?;";
        return super.update(sql,id);
    }


    public int updateUserById(User user) {
        String sql = "UPDATE  `user` SET `username=?`,`pasword`=? WHERE `id`=?;";
        return super.update(sql,user.getUsername(),user.getPasword(),user.getId());
    }

    /**
     * 不支持事务
     * @param id
     * @return
     */
    @Override
    public User get(int id) {
        String sql = "SELECT `id`,`username`,`pasword`,`reg_date` ragDate FROM `user` WHERE `id`=?;";
        return super.get(sql,id);
    }

    @Override
    public User get(Connection conn, int id) {
        String sql = "SELECT `id`,`username`,`pasword`,`reg_date` ragDate FROM `user` WHERE `id`=?;";
        return super.get(conn,sql,id);
    }

    @Override
    public List<User> getlistAll() {
        String sql = "SELECT `id`,`username`,`pasword`,`reg_date` ragDate FROM `user`;";
        return super.getlist(sql);
    }

    @Override
    public long getCountByUserame(String username) {
        String sql = "SELECT COUNT(id) FROM `user` WHERE `username`=?;";
        return (long) super.getValue(sql,username);
    }

    //实现模糊查询
    @Override
    public List<User> query(String username) {
        String sql = "SELECT `id`,`username`,`pasword`,`reg_date` ragDate FROM `user` WHERE 1=1";//sql语句判断参数（检索的参数）
        if (username !=null && !"".equals(username)){
            sql = sql + " AND name like '%"+username+"%'";//sql注入问题
        }
        System.out.println(sql);
        return super.getlist(sql);
    }


    /**
     * 根据用户名和密码实现检查用户
     * @param username
     * @param pasword
     * @return
     */
    @Override
    public User getUserByUp(String username, String pasword) {
        String sql = "SELECT `id`,`username`,`pasword`,`reg_date` regDate FROM `user` WHERE `username`=? and `pasword` = ?;";
        return super.get(sql,username,pasword);
    }
}
