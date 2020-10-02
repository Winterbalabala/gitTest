package cn.mvc.session.dao;

public class FactoryDao {
    public static UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
