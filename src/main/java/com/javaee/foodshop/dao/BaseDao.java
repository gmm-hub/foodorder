package com.javaee.foodshop.dao;

import com.javaee.foodshop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 基本的CRUD
 */
public class BaseDao<T> {

    private QueryRunner runner = new QueryRunner();
    /**
     * 增删改
     * @param sql SQL语句
     * @param params  多个参数
     *
     * @return int 受影响的行数
     */
    public int update(String sql ,Object...params) throws SQLException {
        int i = runner.update(DataSourceUtils.getConnection(), sql, params);
        return i;
    }


    /**
     * 查询单条记录  getBean
     * @param type 类类型
     * @param sql  SQL语句
     * @param params 传递的参数
     *
     * @return  T  返回对象
     */
    public T getBean(Class<T> type , String sql , Object...params) throws SQLException {
        T t = runner.query(DataSourceUtils.getConnection(), sql, new BeanHandler<>(type), params);
        return t;
    }

    /**
     * 查询多条相同类型的记录  getBeanList
     * @param type 类类型
     * @param sql  SQL语句
     * @param params 传递的参数
     *
             * @return  List<T>  返回对象的集合列表
     */
    public List<T> getBeanList(Class<T> type , String sql , Object...params) throws SQLException {
        List<T> list = runner.query(DataSourceUtils.getConnection(), sql, new BeanListHandler<>(type), params);
        return list;
    }


    /**
     * 查询单个值  getSingleValue
     * @param sql  SQL语句
     * @param params 传递的参数
     *
     * @return  Object  返回数据
     */
    public Object getSingleValue(String sql , Object...params) throws SQLException {
        Object o = runner.query(DataSourceUtils.getConnection(), sql, new ScalarHandler(), params);
        return o;
    }


    /**
     * 查询多条不同类型的记录  getMapList
     * @param sql  SQL语句
     * @param params 传递的参数
     *
     * @return  List<Map<String,Object>>  返回集合列表，列表中数据Map集合（其中键：表中字段的名称 ； 值：数据）
     */
    public List<Map<String,Object>> getMapList(String sql , Object...params) throws SQLException {
        List<Map<String, Object>> mapList = runner.query(DataSourceUtils.getConnection(), sql, new MapListHandler(), params);
        return mapList;
    }
}

