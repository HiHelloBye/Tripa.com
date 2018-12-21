package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDao {
	private static MemberDao instance;
	public MemberDao() {}
	public static MemberDao getInstance() {
		if (instance == null) {	instance = new MemberDao();		}
		return instance;
	}
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)
				ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch(Exception e) { System.out.println(e.getMessage());	}
		return conn;
	}
	
	public int login(String user_id, String user_pass) throws SQLException {
		int result  = 0;  				
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL  = "SELECT user_pass FROM MEMBER WHERE user_id=?"; 
		
		try { 
			conn  = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbPasswd = rs.getString(1);
				if (dbPasswd.equals(user_pass)) 
					result = 1;
				else 
					result = 0;
			} else   
				result = -1;
		} catch(Exception e) { 
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}

		return result;
	}
	
	public int register(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "INSERT INTO MEMBER(user_id, user_pass, user_name, user_gen, user_email, user_question, user_answer, user_cell, time)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, member.getUser_id());
			pstmt.setString(2, member.getUser_pass());
			pstmt.setString(3, member.getUser_name());
			pstmt.setString(4, member.getUser_gen());
			pstmt.setString(5, member.getUser_email());
			pstmt.setString(6, member.getUser_question());
			pstmt.setString(7, member.getUser_answer());
			pstmt.setString(8, member.getUser_cell());
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return  -1;
	}
	
	public int registerCheck(String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM MEMBER WHERE user_id = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next() || user_id.equals("")) {
					return 0;
				} else {
					return 1;
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
    public int updateMember(String user_id, String user_pass, String user_email, String user_cell){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "UPDATE MEMBER SET user_pass = ?, user_emali = ?, user_cell = ?" + "WHERE user_id = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_pass);
			pstmt.setString(2, user_email);
			pstmt.setString(3, user_cell);
			pstmt.setString(4, user_id);
			
			return pstmt.executeUpdate();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		return -1;
    }
   
	public int getTotalCnt() throws SQLException {
		Connection conn = null;	Statement stmt= null; 
		ResultSet rs = null;    int tot = 0;
		String sql = "select count(*) from member";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) tot=rs.getInt(1);
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return tot;
	}
	public List<Member> list(int startRow, int endRow) throws SQLException {
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;	PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String sql = "select * from (select rownum rn ,a.* from " + 
					" (select * from member) a ) "+
					" where rn between ? and ? and user_id!='admin'";
		try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Member member = new Member();
					//아이디, 이름, 이메일, 전화번호, 적립금
					member.setUser_id(rs.getString("user_id"));
					member.setUser_name(rs.getString("user_name"));
					member.setUser_email(rs.getString("user_email"));
					member.setUser_cell(rs.getString("user_cell"));
					member.setSaving_pnt(rs.getInt("saving_pnt"));
					list.add(member);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}
	public int deleteadmin(String user_id, String user_pass) throws SQLException{
		Connection conn = null;	PreparedStatement pstmt= null; 
		int result = 0;		    ResultSet rs = null;
		String sql1 = "select user_pass from member where user_pass=?";
		String sql="delete from member where user_id=?";
		try {
			String dbPasswd = "";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, user_pass);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPasswd = rs.getString(1); 
				if (dbPasswd.equals(user_pass)) {
					System.out.println("******��й�ȣ �´��� Ȯ�� *****");
					rs.close();  pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, user_id);
					result = pstmt.executeUpdate();
				} else result = 0;
			} else result = -1;
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}
	
    public int updateMember(Member member) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "UPDATE MEMBER SET user_pass = ?, user_email = ?, user_cell = ?" + "WHERE user_id = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, member.getUser_pass());
			pstmt.setString(2, member.getUser_email());
			pstmt.setString(3, member.getUser_cell());
			pstmt.setString(4, member.getUser_id());
			
			return pstmt.executeUpdate();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		return -1;
    	}
    
    public int delete(String user_id, String user_pass) throws SQLException { 
		Connection conn = null;
		PreparedStatement pstmt = null;
 	   	String SQL= "DELETE FROM MEMBER WHERE user_id =? AND user_pass = ?";
 	    
 	    try {
			conn = getConnection();
 	        pstmt = conn.prepareStatement(SQL);
 	        pstmt.setString (1, user_id);
 	        pstmt.setString (2, user_pass);
 			return pstmt.executeUpdate();
 			        
 	    } catch (Exception e) { 
 	        System.out.println(e.toString());
 	    } 
 	    return -1; 
    }
    
    public String searchPassword(String user_id, String user_email, String user_question, String user_answer) throws SQLException {
 	   Connection conn = null;
 	   PreparedStatement pstmt = null;
 	   ResultSet rs = null;
 	   String user_pass = null;
	   String SQL= "SELECT user_pass FROM MEMBER WHERE user_id =? AND user_email = ? AND user_question = ? AND user_answer = ?";
	   
		try { 
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_email);
			pstmt.setString(3, user_question);
			pstmt.setString(4, user_answer);
			rs = pstmt.executeQuery();
		   	
			while (rs.next()) {
				user_pass = rs.getString("user_pass");
			}    
			System.out.println("MemberDao.java user_pass->" + user_pass);

		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return user_pass;
	}
    
    public Member getMember(String user_id) throws Exception{
        Connection conn =null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Member member=null;
        String SQL="";
        try{
            conn = getConnection();
            SQL="SELECT * FROM MEMBER WHERE user_id= ?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, user_id);
            rs= pstmt.executeQuery();
            
            if(rs.next()){
                member = new Member();
                member.setUser_id(rs.getString("user_id"));
                member.setUser_pass(rs.getString("user_pass"));
                member.setUser_name(rs.getString("user_name"));
                member.setUser_gen(rs.getString("user_gen"));
                member.setUser_email(rs.getString("user_email"));
                member.setUser_question(rs.getString("user_question"));
                member.setUser_answer(rs.getString("user_answer"));
                member.setUser_cell(rs.getString("user_cell"));
                member.setTime(rs.getDate("time"));
                member.setSaving_pnt(rs.getInt("saving_pnt"));
            }
        } catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return member;
    }
}