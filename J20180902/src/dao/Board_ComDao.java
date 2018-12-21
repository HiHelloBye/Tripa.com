package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Board_ComDao {
	private static Board_ComDao instance;

	private Board_ComDao() {
	}

	public static Board_ComDao getInstance() {
		if (instance == null) {
			instance = new Board_ComDao();
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
	
	public int comUpdate(Board_Com board_com) throws SQLException {
		Connection conn = null;	PreparedStatement pstmt= null; 
		int result = 0;			
		String sql="update board_com set comments=? where comm_no=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_com.getPosting_no());
			pstmt.setInt(2, board_com.getSaving_pnt());
			pstmt.setString(3, board_com.getComments());
			pstmt.setString(4, board_com.getUser_id());
			pstmt.setInt(5, board_com.getComm_no());
			pstmt.setString(6, board_com.getRegi_date());
						
			result = pstmt.executeUpdate();
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}

	public Board_Com select (int num) throws SQLException {
		Board_Com board_com = new Board_Com();
		Connection conn = null;	PreparedStatement pstmt= null;
		Statement stmt= null; 
		ResultSet rs = null;		
		String sql= "select * from board_com where comm_no="+num;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				board_com.setComm_no(rs.getInt("comm_no"));
				board_com.setPosting_no(rs.getInt("posting_no"));
				board_com.setUser_id(rs.getString("user_id"));
				board_com.setComments(rs.getString("comments"));
				board_com.setSaving_pnt(rs.getInt("saving_pnt"));
				board_com.setRegi_date(rs.getString("regi_date"));				
				board_com.setRef(rs.getInt("ref"));
				board_com.setRe_level(rs.getInt("re_level"));
				board_com.setRe_step(rs.getInt("re_step"));
					}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)   rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
		return board_com;
	}
	
	public int getCommNo() throws SQLException 
    {
        int comm_no = 0;
        PreparedStatement pstmt= null;
        Connection conn = null;ResultSet rs= null;
        try {
        	conn = getConnection();
            String sql = "SELECT max(comm_no) from board_com";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			comm_no = rs.getInt(1);
			
        } catch (Exception e) {
        	System.out.println(e.getMessage());      		
		} finally {
			if (rs != null)   rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
        return comm_no;
    }
	
	public int getReStep(int posting_no, int re_level) throws SQLException 
    {
        int re_step = 0;
        PreparedStatement pstmt= null;
        Connection conn = null;ResultSet rs= null;
        try {
        	conn = getConnection();
            String sql = "SELECT max(re_step) from board_com where posting_no=? and re_level=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, posting_no);
			pstmt.setInt(2, re_level);
			rs = pstmt.executeQuery();
			rs.next();
			re_step = rs.getInt(1);
			
        } catch (Exception e) {
        	System.out.println(e.getMessage());      		
		} finally {
			if (rs != null)   rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
        return re_step;
    }
	
	public int getReLevel(int posting_no) throws SQLException 
    {
        int re_level = 0;
        PreparedStatement pstmt= null;
        Connection conn = null;ResultSet rs= null;
        try {
        	conn = getConnection();
            String sql = "SELECT max(re_level) from board_com where posting_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, posting_no);
			rs = pstmt.executeQuery();
			rs.next();
			re_level = rs.getInt(1);
			
        } catch (Exception e) {
        	System.out.println(e.getMessage());      		
		} finally {
			if (rs != null)   rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
        return re_level;
    }
	
	public int getStep(int posting_no) throws SQLException 
    {
        int re_step = 0;
        PreparedStatement pstmt= null;
        Connection conn = null;ResultSet rs= null;
        try {
        	conn = getConnection();
            String sql = "SELECT max(re_step) from board_com where posting_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, posting_no);
			rs = pstmt.executeQuery();
			rs.next();
			re_step = rs.getInt(1);
			
        } catch (Exception e) {
        	System.out.println(e.getMessage());      		
		} finally {
			if (rs != null)   rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
        return re_step;
    }
	
	public String getDate() throws SQLException {
		String sysdate = null; 
		PreparedStatement pstmt= null;
        Connection conn = null;ResultSet rs= null;
        try {
        	conn = getConnection();
            String sql = "SELECT to_char(sysdate, 'yy/mm/dd') FROM dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			sysdate = rs.getString(1);

        } catch (Exception e) {
        	System.out.println(e.getMessage());    		
		} finally {
			if (rs != null)   rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
        return sysdate;
    }

	 public int insertComments(Board_Com board_com) throws SQLException
	    {
	        int result = 0;
	        PreparedStatement pstmt= null;
	        Connection conn = null;ResultSet rs= null;
	        String sql = "INSERT INTO BOARD_COM values(?,?,?,?,?,?,?,?,?)";
	        try {
	        	conn = getConnection();
	        	pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, board_com.getComm_no());
	            pstmt.setInt(2, board_com.getPosting_no());
	            pstmt.setString(3, board_com.getUser_id());
	            pstmt.setString(4, board_com.getComments());
	            pstmt.setInt(5, board_com.getSaving_pnt());
	            pstmt.setString(6, board_com.getRegi_date());
	            pstmt.setInt(7, board_com.getRe_level());
	            pstmt.setInt(8, board_com.getRe_step());
	            pstmt.setInt(9, board_com.getRef());
	            result = pstmt.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	        }
	        
	        finally {
				if (rs != null)   rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}
	        return result;    
	    }

	 public int deleteCom(int posting_no,int re_level, int re_step) throws SQLException {
			int result5 = 0; 
			PreparedStatement pstmt= null;
	        Connection conn = null;ResultSet rs= null;
	        String sql = "delete board_com where posting_no=? and re_level=? and re_step=?";
	        try {
	        	conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, posting_no);
				pstmt.setInt(2, re_level);
				pstmt.setInt(3, re_step);
				result5 = pstmt.executeUpdate();

	        } catch (Exception e) {
	        	System.out.println(e.getMessage());    		
			} finally {
				if (rs != null)   rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}
	        return result5;
	    }
	 public int updateCom(Board_Com board_com) throws SQLException {
			int result6 = 0; 
			PreparedStatement pstmt= null;
	        Connection conn = null;ResultSet rs= null;
	        String sql = "update board_com set comments=? where posting_no=? and re_level=? and re_step=?";
	        try {
	        	conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, board_com.getComments());
				pstmt.setInt(2, board_com.getPosting_no());
				pstmt.setInt(3, board_com.getRe_level());
				pstmt.setInt(4, board_com.getRe_step());
				
				result6 = pstmt.executeUpdate();

	        } catch (Exception e) {
	        	System.out.println(e.getMessage());    		
			} finally {
				if (rs != null)   rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}
	        return result6;
	    }
	 
}
