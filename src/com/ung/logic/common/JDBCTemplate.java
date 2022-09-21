package com.ung.logic.common;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

private JDBCTemplate() {

}
static Properties prop= new Properties();

// connection 객체를 담을 그릇 생성.
// 1. db와 접속된 connection 객체를 생성해서 반환시켜주는 메소드
private static Connection conn = null;

public static Connection getConnection() {

try {
	prop.load(new FileInputStream("resources/driver.properties"));
	Class.forName(prop.getProperty("oracle"));
if (conn == null) {
synchronized (JDBCTemplate.class) {
	if (conn == null) {
		conn = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("username"),prop.getProperty("password"));
	}
}
}
}catch(IOException e) {
	e.printStackTrace();
} catch (ClassNotFoundException e) {
e.printStackTrace();
} catch (SQLException e) {
e.printStackTrace();
}
return conn;
}

/*
* 싱글톤패턴엔 단점이있다. 멀티스레드 환경에서 안전하지않다. ex)동시에 여러명의 사용자가 getconnection을 실행했는데 당시 서버의
* connection객체가 null이었다면. 그만큼의 conn객체가 생성되었을껏
* 
* 해결법1) 메서드에 synchronized 키워드를 이용하는방법. 멀티스레드 환경에서 발생하는 문제는 해결되지만.
* getConnection을 이용해 객체를 가저올때마다 lock이 걸리기 때문에 좋지 않음.
* 
* 해결법2) 블록에 synchronized 키워드사용 if(conn==null){ synchronized(클래스이름){ 분리시킬코드 } }
* 해결법3) 이른초기화 private static final connection conn =drivermanager.getConnection
* (url,id,pwd); static 변수는 서버 시작과 동시에 static영영에 들어가기때문에 스레드로 부터 안전함. 단 인스턴스 만드는
* 방법을
* 
* 
*/
// 2. 전달받은 JDBC용 객체를 반납시켜주는 메소드(각 객체별로)
// 2_1 Connection 객체를 전달받아 반납시켜주는 메서드
public static void close() {
try {
if (conn != null && !conn.isClosed()) {
conn.close();
}
} catch (SQLException e) {
e.printStackTrace();
}
conn=null;
}

// 2_2 statement 객체를 전달받아 반납시켜주는 메소드(오버로딩 사용)
// => 다형성으로 인해 preparedstetement 객체또한 매개변수로 전달가능
public static void close(Statement stmt) {
try {
if (stmt != null && !stmt.isClosed()) {
stmt.close();
}
} catch (SQLException e) {
e.printStackTrace();
}
}

// 2_3 resultset 객체를 전달받아서 반납시켜주는 매서드
public static void close(ResultSet rset) {
try {
if (rset != null && !rset.isClosed()) {
rset.close();
}
} catch (SQLException e) {
e.printStackTrace();
}

}

// 3. 전달받은 Connection객체를 가지고 트랜잭션 처리를 해주는 메서드
// 3_1)전달받은 Connection객체를 commit시켜주는 메서드
public static void commit() {
try {
if (conn != null && !conn.isClosed()) {
conn.commit();
}
} catch (SQLException e) {
e.printStackTrace();
}
}

public static void rollback() {
try {
if (conn != null && !conn.isClosed()) {
conn.rollback();
}
} catch (SQLException e) {
e.printStackTrace();
}
}

}
