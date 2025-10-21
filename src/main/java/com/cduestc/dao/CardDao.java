package com.cduestc.dao;

import com.cduestc.pojo.Card;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class CardDao {
    public Card findByCode(String code){
        Card card=new Card();
        try {
            InputStream is= Resources.getResourceAsStream("mybatis.config.xml");
            SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession=factory.openSession();
            card = sqlSession.selectOne("com.cduestc.mapper.CardMapper.selByCode",code);
            sqlSession.commit();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return  card;
    }
}
