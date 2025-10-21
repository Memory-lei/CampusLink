package com.cduestc.dao;

import com.cduestc.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    public List<Student> findAll(){
        List<Student> lststu = new ArrayList<Student>();
        try {
            InputStream is= Resources.getResourceAsStream("mybatis.config.xml");
            SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession=factory.openSession();
            lststu = sqlSession.selectList("com.cduestc.mapper.StudentMapper.findAll");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return lststu;

    }

    public Student findWithSubjects(int sid) {
        Student stu = new Student();
        try {
            InputStream is = Resources.getResourceAsStream("mybatis.config.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = factory.openSession();
            stu = sqlSession.selectOne("com.cduestc.mapper.StudentMapper.selectStudentWithSubjects", sid);
            sqlSession.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stu;
    }
}
