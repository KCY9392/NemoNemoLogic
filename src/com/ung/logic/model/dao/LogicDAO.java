package com.ung.logic.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import com.ung.logic.common.JDBCTemplate;
import com.ung.logic.model.vo.Player;
import com.ung.logic.model.vo.Question;
import com.ung.logic.model.vo.QuestionInfo;

public class LogicDAO {
	Properties prop = new Properties();

	public LogicDAO() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 문제리스트를 난이도별로 테이블에서 가저온다.
	public List<QuestionInfo> getQuestionList(Connection conn, String level) {
		List<QuestionInfo> list = new ArrayList<QuestionInfo>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getQuestionList");
		try {
			pstm = conn.prepareStatement(sql);
			if (level.equals("EASY")) {
				pstm.setInt(1, 1000);
				pstm.setInt(2, 1999);
			} else if (level.equals("NOMAL")) {
				pstm.setInt(1, 2000);
				pstm.setInt(2, 2999);
			} else {
				pstm.setInt(1, 3000);
				pstm.setInt(2, 3999);
			}
			rset = pstm.executeQuery();
			QuestionInfo QI = null;
			while ((QI = resultToQuestionInfo(rset)) != null) {
				list.add(QI);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstm);
			JDBCTemplate.close(rset);
		}

		return list;
	}

//    
//해당 Qcode에 해당하는 문제를 Question테이블에서 가저온다
	public Question getQuestion(Connection conn, int QCode) {
		Question Q = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getQuestion");
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, QCode);
			rset = pstm.executeQuery();
			Q = resultToQuestion(rset);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstm);
			JDBCTemplate.close(rset);
		}
		return Q;
	}

//
//   .
//
	public Player getPlayer(Connection conn, String PlayerId) {
		Player p = null;
		PreparedStatement pstm = null;
		PreparedStatement cPstm = null;
		ResultSet rset = null;
		int result = 0;
		String getPlayer = prop.getProperty("getPlayer");
		String createPlayer = prop.getProperty("createPlayer");
		try {
			pstm = conn.prepareStatement(getPlayer);
			pstm.setString(1, PlayerId);
			rset = pstm.executeQuery();
			p = resultsetToPlayer(rset);
			if (p == null) {
				cPstm = conn.prepareStatement(createPlayer);
				cPstm.setString(1, PlayerId);
				result = cPstm.executeUpdate();
				JDBCTemplate.commit();
				p=new Player(PlayerId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstm);
			JDBCTemplate.close(cPstm);
		}
		return p;
	}

//
//해당 아이디랑 일치하는 계정정보를 Player를 가저온다.
//
//없다면 계정을 새로 만들어서 Player을 반환한다.
//		
//
//      
//
	public void SaveCurrent(Player player, Connection conn) {
		char[][] Current = player.getCurrentData();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < Current.length; i++) {
			for (int j = 0; j < Current.length; j++) {
				sb.append(Current[i][j]);
			}
			if (i != Current.length - 1) {
				sb.append(",");
			}
		}
		PreparedStatement pstm = null;
		int result = 0;
		String sql = prop.getProperty("SaveCurrent");
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, sb.toString());
			pstm.setInt(2, player.getCurrentCode());
			result = pstm.executeUpdate(sql);
			JDBCTemplate.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstm);
		}

	}

	public void saveClearList(Player player, Connection conn) {
		Set<Integer> clearset = player.getClearList();
		String clearList = null;
		if (clearset.isEmpty()) {
			clearList = player.getCurrentCode() + "";
		} else {
			Iterator<Integer> it = clearset.iterator();
			StringBuffer sb = new StringBuffer();
			while (it.hasNext()) {
				sb.append(it.next());
				sb.append(',');
			}
			String str = sb.toString();
			clearList = str.substring(0, str.length() - 1);
		}
		PreparedStatement pstm = null;
		int result = 0;
		String sql = prop.getProperty("SaveClearList");
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, player.getCurrentCode());
			pstm.setString(2, clearList);
			result = pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstm);
		}

	}

	/*
	 * resultSet들을 가공처리해주는 메소드 들이다.
	 */
	private Player resultsetToPlayer(ResultSet rset) throws SQLException {
		Player p = null;
		char[][] current = null;
		if (rset.next()) {
			Set<Integer> clearList = new TreeSet<Integer>();
			String CLEAR_LIST = rset.getString("CLEAR_LIST");
			if (CLEAR_LIST != null) {
				StringTokenizer st = new StringTokenizer(CLEAR_LIST, ",");
				while (st.hasMoreTokens()) {
					clearList.add(Integer.parseInt(st.nextToken()));
				}
			}
			int CurrentQId = rset.getInt("CURRENT_QST");
			String currentData = rset.getString("Current_DATA");
			if (currentData != null) {
				StringTokenizer st = new StringTokenizer(currentData, ",");
				List<String> currentList = new ArrayList<String>();
				while (st.hasMoreTokens()) {
					currentList.add(st.nextToken());
				}

				current = new char[currentList.size()][currentList.get(0).length()];

				for (int i = 0; i < current.length; i++) {
					String str = st.nextToken();
					for (int j = 0; j < str.length(); j++) {
						current[i][j] = str.charAt(j);
					}

				}
			}
			p = new Player(rset.getString("PLAYER_ID"), CurrentQId, current, clearList);
		}
		return p;
	}

	private QuestionInfo resultToQuestionInfo(ResultSet rset) throws SQLException {
		QuestionInfo QI = null;
		if (rset.next()) {
			QI = new QuestionInfo(rset.getInt("QST_ID"), rset.getString("QST_Name"), rset.getString("QST_SIZE"),
					rset.getString("QST_LEVER"));
		}
		return QI;
	}

	private Question resultToQuestion(ResultSet rset) throws SQLException {
		Question Q = null;
		if (rset.next()) {
			// Answer 가공
			StringTokenizer st = new StringTokenizer(rset.getString("ANSWER"), ",");
			List<String> list = new LinkedList<String>();
			while (st.hasMoreTokens()) {
				list.add(st.nextToken());
			}
			int listLength = list.size();
			int strLength = list.get(0).length();
			int[][] answer = new int[listLength][strLength];
			for (int i = 0; i < listLength; i++) {
				String row = list.get(i);
				for (int j = 0; j < strLength; j++) {
					answer[i][j] = (int) row.charAt(j) - 48;
				}
			}
			// Row_Hint 가공
			st = new StringTokenizer(rset.getString("row_HINT"), ";");
			List<String> RowList = new ArrayList<String>();
			while (st.hasMoreTokens()) {
				RowList.add(st.nextToken());
			}

			int rowsize = RowList.size();
			int[][] rowHint = new int[rowsize][];

			for (int i = 0; i < rowsize; i++) {
				st = new StringTokenizer(RowList.get(i), ",");
				List<Integer> iList = new LinkedList<Integer>();
				while (st.hasMoreTokens()) {
					iList.add(Integer.parseInt(st.nextToken()));
				}

				int size = iList.size();
				rowHint[i] = new int[size];
				for (int j = 0; j < size; j++) {

					rowHint[i][size-j-1] = (int) iList.get(j);
				}
			}
			// Column_Hint 가공
			st = new StringTokenizer(rset.getString("Column_HINT"), ";");
			List<String> columnList = new ArrayList<String>();
			while (st.hasMoreTokens()) {
				columnList.add(st.nextToken());
			}
			int columnsize = columnList.size();
			int[][] columnHint = new int[columnsize][];

			for (int i = 0; i < columnsize; i++) {
				st = new StringTokenizer(columnList.get(i), ",");
				List<Integer> iList = new LinkedList<Integer>();
				while (st.hasMoreTokens()) {
					iList.add(Integer.parseInt(st.nextToken()));
				}
				int size = iList.size();
				columnHint[i] = new int[size];
				for (int j = 0; j < size; j++) {
					columnHint[i][size-j-1] = (int) iList.get(j);
				}
			}


			Q = new Question(rset.getInt("QST_CODE"), rowHint, columnHint, answer);


		}
		return Q;
	}

//
}
