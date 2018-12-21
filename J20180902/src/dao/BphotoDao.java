package dao;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BphotoDao {
	private static BphotoDao instance;

	private BphotoDao() {
	}

	public static BphotoDao getInstance() {
		if (instance == null) {
			instance = new BphotoDao();
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
	
    //Bphoto 테이블 posting_no에 따른 total Count 계산
	public int totPhotoCnt(int posting_no) throws SQLException {
		Connection conn = null;
		Statement stmt	= null; 
		ResultSet rs    = null;
		
		int tot = 0;
		
		String sql = "select count(*) from bphoto where posting_no=" + posting_no;
		
		try {
			conn = getConnection();
			
			stmt = conn.createStatement();
			rs 	 = stmt.executeQuery(sql);

			if (rs.next()) {
				tot = rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) 	rs.close();
			if (stmt != null) 	stmt.close();
			if (conn != null)conn.close();
		}
		return tot;
	}

    //SQL insert
//    public int insert(Bphoto bphoto) throws SQLException {		
//		Connection conn 		= null;	
//		PreparedStatement pstmt	= null; 
//		int result 		= 0;			
//		ResultSet rs 	= null;
//        
//		String sql  	= "insert into bphoto values(?,?,?)";
//		
//		try {
//			
////			System.out.println("사진 insert");
////			System.out.println(bphoto.getPosting_no());
////			System.out.println(bphoto.getPho_no());
////			System.out.println(bphoto.getPhoto());
//			
//			conn 	= getConnection();
//			pstmt 	= conn.prepareStatement(sql);
//				
//			pstmt.setInt   	(1, bphoto.getPosting_no());
//			pstmt.setInt	(2, bphoto.getPho_no());
//			pstmt.setString (3, bphoto.getPhoto());
//
//			result = pstmt.executeUpdate();
//		
//		} catch(Exception e) {	System.out.println(e.getMessage()); 
//		} finally {
//			if (rs !=null) rs.close();
//			if (pstmt != null) pstmt.close();
//			if (conn !=null) conn.close();
//		}
//		return result;
//	}
	 public int insert(Bphoto bphoto) throws SQLException {		
			Connection conn 		= null;	
			Statement stmt	= null; 
			PreparedStatement pstmt	= null; 
			int result 		= 0;			
			ResultSet rs 	= null;
	        
			String sql  	= "insert into bphoto values(?,?,?)";
			String sql1 	= "update table set clob_data = empty_clob()";
			
			try {
				
//				System.out.println("사진 insert");
//				System.out.println(bphoto.getPosting_no());
//				System.out.println(bphoto.getPho_no());
//				System.out.println(bphoto.getPhoto());
				
				conn 	= getConnection();
				pstmt 	= conn.prepareStatement(sql);
					
				pstmt.setInt   	(1, bphoto.getPosting_no());
				pstmt.setInt	(2, bphoto.getPho_no());
				pstmt.setString (3, bphoto.getPhoto());

				result = pstmt.executeUpdate();
			
			} catch(Exception e) {	System.out.println(e.getMessage()); 
			} finally {
				if (rs !=null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn !=null) conn.close();
			}
			return result;
		}

	public int update(String photo, int posting_no, int pho_no) throws SQLException {
		Connection conn 		= null;	
		PreparedStatement pstmt	= null; 
		
		String sql  	= "update bphoto set photo=? where posting_no=? and pho_no=?";
	
		try {
			conn 	= getConnection();
			pstmt 	= conn.prepareStatement(sql);
						
			pstmt.setString(1, photo);
			pstmt.setInt(2, posting_no);
			pstmt.setInt(3, pho_no);
		
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (pstmt != null) 	pstmt.close();
			if (conn !=null)	conn.close();
		}
		return -1;
	}

	public int delete(int posting_no, int pho_no) throws SQLException {
		Connection conn 		= null;	
		Statement stmt			= null; 
		
		int result				= 0;		    

		String sql ="delete from bphoto where posting_no="+posting_no +"and pho_no>="+pho_no;
		
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
