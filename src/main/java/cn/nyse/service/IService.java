package cn.nyse.service;

import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface IService<T> {

    int deleteByPrimaryKey(Object key) ;


    int delete(T t) ;


    int insert(T t) ;


    int insertSelective(T t) ;


    boolean existsWithPrimaryKey(Object key) ;


    List<T> selectAll() ;


    T selectByPrimaryKey(Object key) ;


    int selectCount(T t) ;


    List<T> select(T t) ;


    T selectOne(T t) ;


    int updateByPrimaryKey(T t) ;


    int updateByPrimaryKeySelective(T t) ;


    int deleteByExample(Object example) ;


    List<T> selectByExample(Object example) ;


    int selectCountByExample(Object example) ;


    T selectOneByExample(Object example) ;


    int updateByExample(T t, Object example) ;


    int updateByExampleSelective(T t, Object example) ;


    List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) ;


    List<T> selectByRowBounds(T t, RowBounds rowBounds) ;
}
