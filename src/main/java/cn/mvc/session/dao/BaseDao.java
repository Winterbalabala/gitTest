package cn.mvc.session.dao;

import cn.mvc.session.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

public class BaseDao<T> {

    //Dao的基本类，被具体的Dao类继承
    //泛型，要针对操作各张数据表映射刀Java工程里的Java类，user
    //对数据库的基本的增删改查操作
    QueryRunner queryRunner = new QueryRunner();
    private Class<T> clazz;
    public BaseDao(){
        //用baseDao的构造方法初始化clazz属性
        Type superType = this.getClass().getGenericSuperclass();//拿到调用者父类的类行
        if (superType instanceof ParameterizedType){
            ParameterizedType pt = (ParameterizedType) superType;
            Type[] tarry = pt.getActualTypeArguments();//返回一个类型数组，数组的第一个元素就是运行时的第一个类
            if (tarry[0] instanceof Class){
                clazz = (Class<T>) tarry[0];
            }
        }
    }
    /**
     * 查询数据表，去除sql语句结果集的第一条数据，封装成一个类的对象返回（不支持事务）
     * 执行sql语句，用到dbUtils工具类
     * null的位置应该传入具体的类
     * @param sql
     * @param args
     * @return
     */
    public T get(String sql,Object... args){
        Connection conn = null;
        T entity = null;
        try {
            //获取conn
            conn = JdbcUtils.getConnection();
            entity = queryRunner.query(conn,sql,new BeanHandler<T>(clazz),args);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils.closeConn(conn);
        }
        return entity;
    }
    /**
     * 查询数据表，去除sql语句结果集的第一条数据，封装成一个类的对象返回（支持事务）
     * @param sql
     * @param args
     * @return
     */
    public T get(Connection conn,String sql,Object... args){
        T entity = null;
        try {
            //获取conn
            entity = queryRunner.query(conn,sql,new BeanHandler<T>(clazz),args);
        }catch (Exception e){
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * 获取多条记录的通用方法，利用泛型实现
     * @return
     */
    public List<T> getlist(String sql, Object... args){
        Connection conn = null;
        List<T> list =null;
        try {
            //获取conn
            conn = JdbcUtils.getConnection();
            list = queryRunner.query(conn,sql,new BeanListHandler<>(clazz),args);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils.closeConn(conn);
        }
        return list;
    }

    /**
     * 实现insert，update，delete通用更新方法
     * @param sql
     * @return
     */
    public int update(String sql,Object... args){
        Connection conn = null;
        int rows = 0;
        try {
            //获取conn
            conn = JdbcUtils.getConnection();
            rows = queryRunner.update(conn,sql,args);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils.closeConn(conn);
        }
        return rows;
    }
    /**
     *通用的放回sql语句的结果只有一个数值的类型的查询，用户个数count
     */
    public Object getValue(String sql,Object... args){
        Connection conn = null;
        Object obj = null;
        try {
            //获取conn
            conn = JdbcUtils.getConnection();
            obj = queryRunner.query(conn,sql, new ScalarHandler(),args);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils.closeConn(conn);
        }
        return obj;
    }

}
