package cn.mvc.session.model;

import java.util.Date;

public class User {
    //用户的编号
    private int id;
    //用户名称
    private String username;
    //用户密码
    private String pasword;
    //日期
    private Date regDate;


    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public User(){
        super();
    }

    public User(int id, String username, String pasword, Date regDate) {
        this.id = id;
        this.username = username;
        this.pasword = pasword;
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pasword='" + pasword + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
