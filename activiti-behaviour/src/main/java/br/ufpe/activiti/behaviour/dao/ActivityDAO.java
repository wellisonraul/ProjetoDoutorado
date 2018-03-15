package br.ufpe.activiti.behaviour.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import br.ufpe.activiti.behaviour.model.ProcessoLog;

public class ActivityDAO {
	// CLASSE RESPOSÁVEL POR INSERIR INFORMAÇÕES NO BANCO!
	
	public int insertActivityLog(ProcessoLog processLog) {
		SqlSessionFactory sqlSessionFactory = RealizarConexao.getSession();
		SqlSession session = sqlSessionFactory.openSession();
		int id = 0;
		try {
			session.insert("Activity.setActivityLog", processLog);
			session.commit();
		} finally {
			session.close();
		}
		return id;
	}
}
