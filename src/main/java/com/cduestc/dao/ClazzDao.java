package com.cduestc.dao;

import com.cduestc.pojo.Clazz;
import com.cduestc.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class ClazzDao {
    public Clazz findByCode(String code) {
        Clazz clazz = new Clazz();
        try {
            InputStream is = Resources.getResourceAsStream("mybatis.config.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = factory.openSession();
            clazz = sqlSession.selectOne("com.cduestc.mapper.ClazzMapper.selByCode2",code);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return clazz;
    }
}