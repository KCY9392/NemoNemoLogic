package com.ung.logic.model.service;

import java.sql.Connection;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.ung.logic.common.JDBCTemplate;
import com.ung.logic.model.dao.LogicDAO;
import com.ung.logic.model.vo.Player;
import com.ung.logic.model.vo.Question;
import com.ung.logic.model.vo.QuestionInfo;

public class LogicService {
	private LogicDAO ld=new LogicDAO();
	public Player getPlayer(String PlayerId) {
		Player p=null;
		Connection conn=JDBCTemplate.getConnection();
		p=ld.getPlayer(conn, PlayerId);
		JDBCTemplate.close();
		
		return p;
	}
	public List<QuestionInfo> getQuestionList(String level){
		Connection conn=JDBCTemplate.getConnection();
		List<QuestionInfo> list=ld.getQuestionList(conn, level);
		JDBCTemplate.close();
		
		return list;

	}
	public Question getQuestion(int QCode){
		Connection conn=JDBCTemplate.getConnection();
		Question q=ld.getQuestion(conn, QCode);
		JDBCTemplate.close();
		return q;
	}
	
	public void SaveCurrent(Player player) {
		Connection conn=JDBCTemplate.getConnection();
		ld.SaveCurrent(player, conn);
		JDBCTemplate.close();
		
	}
	public void SaveClearList(Player player) {
		Connection conn=JDBCTemplate.getConnection();
		ld.saveClearList(player, conn);
		JDBCTemplate.close();
	}
}

