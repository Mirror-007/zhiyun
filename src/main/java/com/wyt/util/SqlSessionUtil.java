package com.wyt.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtil {

	private static ThreadLocal<SqlSession> tol = new ThreadLocal<SqlSession>();
	private static SqlSessionFactory ssf = null;
	static{
		InputStream is = null;

		try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
			//2.创建SqlSesionFactory
			ssf = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static SqlSession getSqlSession(){

		//3.创建SqlSession
		SqlSession sqlSession =tol.get();
		if(sqlSession == null){
			sqlSession = ssf.openSession();
			tol.set(sqlSession);
		}
		return sqlSession;
	}

	//关闭sqlSession
	public static void close(SqlSession sqlSession){

		if(sqlSession != null){
			sqlSession.close();
			tol.remove();
		}

	}
}
