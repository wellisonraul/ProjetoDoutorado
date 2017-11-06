package br.ufpe.activiti.behaviour.dao;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class RealizarConexao {
	private static SqlSessionFactory sqlMapper;
	private static Reader reader; 

	static{
		try{
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession(){
		return sqlMapper;
	}
}
