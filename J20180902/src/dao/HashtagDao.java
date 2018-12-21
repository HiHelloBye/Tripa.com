package dao;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class HashtagDao {
	private static HashtagDao instance;

	private HashtagDao() {
	}

	public static HashtagDao getInstance() {
		if (instance == null) {
			instance = new HashtagDao();
		}
		return instance;
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

    //Hashtag 테이블 posting_no에 따른 total Count 계산
	public int totHashtagCnt(int posting_no) throws SQLException {
		Connection conn = null;
		Statement stmt	= null; 
		ResultSet rs    = null;
		
		int tot = 0;
		String sql = "select count(*) from hashtag where posting_no=" + posting_no;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs 	 = stmt.executeQuery(sql);

			if (rs.next())
				tot = rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)		rs.close();
			if (stmt != null) 	stmt.close();
			if (conn != null)	conn.close();
		}
		return tot;
	}

	//SQL select
	public Hashtag select(int posting_no) throws SQLException {
		Connection conn = null;	
		Statement stmt	= null; 

		ResultSet rs 	= null;

		String sql 		= "select * from hashtag where posting_no=" + posting_no;

		Hashtag hashtag 	= new Hashtag();
		String hashTag = "";

		try {
			conn = getConnection();
			stmt = conn.createStatement();
		
			rs 	 = stmt.executeQuery(sql);

			while (rs.next()) {

				
				hashtag.setPosting_no(rs.getInt("posting_no"));
				hashtag.setHash_no(rs.getInt("hash_no"));
				hashtag.setHashtag(hashTag);
			}
		
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) 		rs.close();
			if (stmt != null) 	stmt.close();
			if (conn !=null) 	conn.close();
		}
		return hashtag;
	}
	
	public String getHashtag(int posting_no) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt	= null; 
		ResultSet rs 	= null;
		
		String sql 		= "select hashtag from hashtag where posting_no=?";

		String hashtag = "";
		
		try {
			conn = getConnection();
			pstmt 	= conn.prepareStatement(sql);
			pstmt.setInt(1, posting_no);
			rs = pstmt.executeQuery();
			
			System.out.println(rs);
			
			if (rs.next()) {
				hashtag = rs.getString(1);
			}

			System.out.println("while 문 밖");
			System.out.println("************************");
			System.out.print(hashtag);
			
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) 		rs.close();
			if (pstmt != null) 	pstmt.close();
			if (conn !=null) 	conn.close();
		}
		return hashtag;
	}
	
    //SQL insert
    public int insert(Hashtag hashtag) throws SQLException {		
		Connection conn 		= null;	
		PreparedStatement pstmt	= null; 
		int result 		= 0;			
		ResultSet rs 	= null;
        
		String sql  	= "insert into hashtag values(?,?,?)";
		
		try {
			
//			System.out.println("사진 insert");
//			System.out.println(hashtag.getPosting_no());
//			System.out.println(hashtag.getHash_no());
//			System.out.println(hashtag.getHashtag());
			
			conn 	= getConnection();
			pstmt 	= conn.prepareStatement(sql);
				
			pstmt.setInt   	(1, hashtag.getPosting_no());
			pstmt.setInt	(2, hashtag.getHash_no());
			pstmt.setString (3, hashtag.getHashtag());

			result = pstmt.executeUpdate();
		
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}

	public int update(String hashtag, int posting_no, int hash_no) throws SQLException {
		Connection conn 		= null;	
		PreparedStatement pstmt	= null; 
		
		String sql  	= "update hashtag set hashtag=? where posting_no=? and hash_no=?";
	
		try {
			conn 	= getConnection();
			pstmt 	= conn.prepareStatement(sql);
			
			pstmt.setString(1, hashtag);
			pstmt.setInt(2, posting_no);
			pstmt.setInt(3, hash_no);
		
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (pstmt != null) 	pstmt.close();
			if (conn !=null)	conn.close();
		}
		return -1;
	}

	public int delete(int posting_no, int hash_no) throws SQLException {
		Connection conn 		= null;	
		Statement stmt			= null; 
		
		int result				= 0;		    

		String sql ="delete from hashtag where posting_no="+ posting_no + "and hash_no>="+hash_no;
		
		try {
			
			conn 	= getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery(sql);

			
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (stmt != null)	 stmt.close();
			if (conn !=null)	 conn.close();
		}
		return result;
	}
}
