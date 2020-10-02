package cn.mvc.session.service;

public class FactoryService {

    public static UserService getUserService(){
        return new UserServiceImpl();
    }
}
