package com.haeny.nemologicsolver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class saveResult {
	/**
	 *
	 * @author 진웅휘
	 */
	//받아온 문제들을 db에 저장한다.
	public void saveQuestion(char[][] result,int[][]row,int[][]column,String title){
		
		
		Connection conn=null;
		Statement stmt=null;
		int resultQ=0;
		int resultQL=0;
		String level=null;
		if(result.length<=10) {
			level="EASY";
		}else if(result.length<=15) {
			level="NOMAL";
		}else {
			level="HARD";
		}
		
		String[] sarr=toStringArr(result,row,column);
		StringBuffer sb=new StringBuffer("INSERT INTO QUESTION VALUES(SEQ_");
		sb.append(level);
		sb.append("_QUESTION.NEXTVAL,'");
		sb.append(sarr[1]);
		sb.append("','");
		sb.append(sarr[2]);
		sb.append("','");		
		sb.append(sarr[0]);
		sb.append("')");		
		String sql=sb.toString();
		StringBuffer sb2=new StringBuffer("INSERT INTO QUESTIONLIST VALUES(SEQ_");
		sb2.append(level);
		sb2.append("_QUESTION.CURRVAL,'");
		sb2.append(title);
		sb2.append("','");
		sb2.append(result.length+"*"+result[0].length);
		sb2.append("','");
		sb2.append(level);
	    sb2.append("')");
		String sql2=sb2.toString();
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","LOGIC","LOGIC");
		stmt=conn.createStatement();
		System.out.println(sql);
		resultQ=stmt.executeUpdate(sql);
		if(resultQ>0) {
			resultQL=stmt.executeUpdate(sql2);
			System.out.println(sql2);
			conn.commit();
		}else {
			conn.rollback();
			
		}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	public String[] toStringArr(char[][] result,int[][] row,int[][]column) {
		String[] arr=new String[3];
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				sb.append(result[i][j]);
			}
			if(result.length-1!=i)sb.append(',');
		}
		arr[0]=sb.toString();
		StringBuffer sb2=new StringBuffer();
		for (int i = 0; i < row.length; i++) {
			for (int j = 0; j < row[i].length; j++) {
				sb2.append(row[i][j]);
				if(j!=row[i].length-1)sb2.append(',');
			}
			if(row.length-1!=i)sb2.append(';');
		}
		arr[1]=sb2.toString();
		StringBuffer sb3=new StringBuffer();
		for (int i = 0; i < column.length; i++) {
			for (int j = 0; j < column[i].length; j++) {
				sb3.append(column[i][j]);
				if(j!=column[i].length-1)sb3.append(',');
			}
			if(column.length-1!=i)sb3.append(';');
		}
		arr[2]=sb3.toString();
		return arr;
	}
	
	
	
}
