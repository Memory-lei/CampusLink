package com.cduestc.dao;

import com.cduestc.pojo.Student;
import com.cduestc.pojo.Subject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class SubjectDao {
    public Subject findById(int subid) {
        Subject sub = new Subject();
        try {
            InputStream is = Resources.getResourceAsStream("mybatis.config.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = factory.openSession();
            sub = sqlSession.selectOne("com.cduestc.mapper.SubjectMapper.selectSubjectWithStudents", subid);
            sqlSession.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sub;
    }

}