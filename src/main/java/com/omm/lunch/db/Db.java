package com.omm.lunch.db;

public class Db {
	static public String DB_JDBC_DRIVER_PACKAGE_PATH = "com.mysql.cj.jdbc.Driver";	//mysql 상수들은 대문자로 만든다.
//	static private String DB_JDBC_DRIVER_PACKAGE_PATH = "oracle.jdbc.OracleDriver";	//오라클
	
	static private String DB_NAME = "omm_user";
	static private String DB_URL_MYSQL = "jdbc:mysql://localhost:3306/"+DB_NAME;	//mysql
//	static private String DB_URL_ORACLE = "jdbc:oracle:thin:@127.0.0.1:1521:"+DB_NAME;	//오라클
	static public String DB_URL = DB_URL_MYSQL;	//디비 바뀌면 여기 바꾸시오. 상수를 두번처리함.(구분용)
	static public String DB_ID = "root";
	static public String DB_PW = "root";
	
	/* table들 */
	////	게시판
	public static final String FREE_TABLE = "omm_board";	//게시판
	public static final String ROULETTE = "omm_roulette";	//룰렛
	public static final String USER = "omm_user";	//회원관리
	
	//omm_user로 db를 걸어두면 비회원처리를 다 해줘야 한다.
	//기본적으로 sql문 작성할때 omm_board나 omm_roulette에 값을 주면 상관없을거 같긴하다.
	//어렵게 생각말고 테이블 3개인버전이랑 한개나 두개로 하는 버전 둘다 만들어보자.
}