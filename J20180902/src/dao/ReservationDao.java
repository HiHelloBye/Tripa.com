package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReservationDao {
	private static ReservationDao instance;
	public ReservationDao() {}
	public static ReservationDao getInstance() {
		if (instance == null) {	instance = new ReservationDao();		}
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
	
	public int insert(Reservation resv) throws SQLException {		
		Connection conn = null;	PreparedStatement pstmt= null; 
		int result = 0;			ResultSet rs = null;
		String sql="insert into reservation values(?,?,?,?,?,?,?,?,?,?,?,?)";

		try {			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, resv.getAccom_no());
			pstmt.setString(2, resv.getRoom_no());
			pstmt.setInt(3, resv.getReser_no());
			pstmt.setString(4, resv.getOut_date());
			pstmt.setString(5, resv.getIn_date());
			pstmt.setString(6, resv.getUser_email());
			pstmt.setString(7, resv.getRes_cont());
			pstmt.setString(8, resv.getUser_id());
			pstmt.setString(9, resv.getRes_name());
			pstmt.setString(10, resv.getUser_cell());
			pstmt.setInt(11, resv.getRsaving_pnt());
			pstmt.setInt(12, 0);
			result = pstmt.executeUpdate();
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}
	
	public int compare(String user_id, int saving_pnt) throws SQLException {
		Connection conn = null;	PreparedStatement pstmt= null; 
		int result2 = 0;			ResultSet rs = null;
		String sql = "select saving_pnt from member where user_id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int saved_point = rs.getInt(1);
				System.out.println("saved_point=>"+saved_point);
				if(saved_point >= saving_pnt) {
					result2=1;
				} else result2=0;
				}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
			if(rs!=null) rs.close();
		}
		return result2;
	}
	
	public Member select(String user_id) throws SQLException {
		Member member = new Member();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "Select * from member where user_id=?";
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
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
                System.out.println("memberDao-> " + user_id);

			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
			if(rs != null) rs.close();
		}
		return member;
	}
	
	public Accom select2(int accom_no,String room_no) throws SQLException {
		Accom accomInfo = new Accom();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "Select * from accom_info where accom_no=? and room_no=?";
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accom_no);
			pstmt.setString(2, room_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				accomInfo.setAccom_no(rs.getInt(1));
				accomInfo.setRoom_no(rs.getString(2));
				accomInfo.setNum_people(rs.getInt(3));
				accomInfo.setIn_date(rs.getString(4));
				accomInfo.setOut_date(rs.getString(5));
				accomInfo.setPrice(rs.getInt(6));
				accomInfo.setRes_stat(rs.getString(7));
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
			if(rs != null) rs.close();
		}
		return accomInfo;
	}
	
	public int date_compare(String out_date, String in_date, int accom_no, String room_no) throws SQLException {
		Connection conn = null;	PreparedStatement pstmt= null; 
		int result3 = 1;			ResultSet rs = null;
		int Odate = Integer.parseInt(out_date);
		int Idate = Integer.parseInt(in_date);
		String sql = "select out_date, in_date from reservation where accom_no=? and room_no=?";
		System.out.println("sql -> " + sql);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accom_no);
			pstmt.setString(2, room_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int Rout_date = rs.getInt(1);
				int Rin_date = rs.getInt(2);
				if(Idate>=Odate) {
					result3=4;
				} else if (Rin_date>Idate && Odate<Rin_date) {
					result3=1;
				} else if(Rin_date>Idate && Odate>=Rin_date && Odate<=Rout_date) {
					result3=2;
				} else if(Rout_date>Idate && Odate>=Rout_date) {
					result3=2;
				} else if(Rin_date<=Idate && Odate<=Rout_date) {
					result3=3;		
				} else if(Rout_date>=Idate && Rin_date<=Idate && Odate>=Rout_date) {
					result3=3;
				} else if(Rout_date<Idate && Odate>Rout_date) {
					result3=1;
				} else {
					result3=0;
				}
			}
			System.out.println(" result3-> " + result3);

		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
			if(rs!=null) rs.close();
		}
		return result3;
	}
	
	public int maxReserNo() throws SQLException {
		int maxReserNo = 0;
		Connection conn = null;	PreparedStatement pstmt= null; ResultSet rs = null;
		try {
			conn=getConnection();
			String sql = "select max(reser_no) from reservation";
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			maxReserNo = rs.getInt(1);
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return maxReserNo;
	}
	public int descPnt(String user_id,int saving_pnt) throws SQLException {
		int result4 = 0;
		Connection conn = null;	PreparedStatement pstmt= null; ResultSet rs = null;
		String sql = "update member set saving_pnt=saving_pnt-? where user_id =?";
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, saving_pnt);
			pstmt.setString(2, user_id);
			
			result4 = pstmt.executeUpdate();
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result4;
	}
	
	public int updateFlag(int reser_no) throws SQLException {
	    	
		Connection conn = null;	
		Statement stmt  = null;
		ResultSet rs = null;
			
		String sql = " UPDATE reservation SET flag=1 where reser_no=" + reser_no;
		
		System.out.println("??????????????????????????????????"+reser_no);
			
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
				
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return -1;
	}
	
	public String endDate() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String endDate = null;
		String sql = "select to_char(sysdate+14,'yyyy/mm/dd') from dual";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			endDate = rs.getString(1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally { 
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return endDate;
	}
    public int ReservationDelete(int reser_no) throws SQLException { 
		Connection conn = null;
		PreparedStatement pstmt = null;
 	   	String SQL= "DELETE RESERVATION WHERE reser_no = ?";
 	    int result = 0;
		System.out.println("result===>"+result);
 	    try {
			conn = getConnection();
 	        pstmt = conn.prepareStatement(SQL);
 	        pstmt.setInt(1, reser_no);
			result = pstmt.executeUpdate();
			System.out.println("result===>"+result);
 	    } catch (Exception e) { 
 	        System.out.println(e.toString());
		} finally { 
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
 	    return result; 
    }
 
}
