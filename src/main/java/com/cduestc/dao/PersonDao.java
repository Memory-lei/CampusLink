package com.cduestc.dao;

import com.cduestc.pojo.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public  class PersonDao {
    public  Person findOne(int pid){
        Person person=new Person();
        try {InputStream is=Resources.getResourceAsStream("mybatis.config.xml");
            SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession=factory.openSession();
            sqlSession.selectList("com.cduestc.mapper.PersonMapper.SelById",pid);


        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return person;

    }
}
