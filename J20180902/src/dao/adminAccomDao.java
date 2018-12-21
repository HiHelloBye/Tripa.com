package dao;

import java.sql.Array;
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

public class adminAccomDao {
	private static adminAccomDao instance;
	private adminAccomDao() {}
	public static adminAccomDao getInstance() {
		if (instance == null) {	instance = new adminAccomDao();		}
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
	
	public List<Accom> list(int startRow, int endRow) throws SQLException {
		List<Accom> list = new ArrayList<Accom>();
		Connection conn = null;	PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String sql = "select * from (select rownum rn ,a.* from " + 
					" (select * from accom) a ) "+
					" where rn between ? and ?";
		try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Accom accom = new Accom();
					accom.setAccom_no(rs.getInt("accom_no"));
					accom.setAccom_name(rs.getString("accom_name"));
					accom.setAccom_type(rs.getString("accom_type"));
					accom.setInout(rs.getString("inout"));
					accom.setArea(rs.getString("area"));
					list.add(accom);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}
	public int getTotalCnt() throws SQLException {
		Connection conn = null;	Statement stmt= null; 
		ResultSet rs = null;    int tot = 0;
		String sql = "select count(*) from accom";
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
	
	public int insert(Accom accom) throws SQLException {
	
		Connection conn = null;	PreparedStatement pstmt= null; 
		int result = 0;			ResultSet rs = null;
		String sql1 = "select nvl(max(accom_no),0) from accom";
		String sql="insert into accom values(?,?,?,?,?,?,?,0,?,?,?)";
		try {		
			System.out.println("여기 들어오는지 확인!!!!!!!!!!!");
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			rs.next();
			int number = rs.getInt(1) + 1;  
			rs.close();   
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
		
			pstmt.setString(2, accom.getAccom_name());
			pstmt.setString(3, accom.getAccom_type());
			pstmt.setString(4, accom.getAccom_addr());
			pstmt.setString(5, accom.getAccom_func());
			pstmt.setString(6, accom.getAccom_map());
			pstmt.setString(7, accom.getAccom_cont());
			pstmt.setString(8, accom.getInout());
			pstmt.setString(9, accom.getArea());
			pstmt.setString(10, accom.getAccom_photo());
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {	
			System.out.println(""+e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}
	public Accom select(int accom_no) throws SQLException{
		Connection conn = null;	Statement stmt= null; ResultSet rs = null;
		String sql = "select * from accom where accom_no="+accom_no;
		Accom accom = new Accom();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {			
				System.out.println("여기 실행되는지");
				accom.setAccom_no(rs.getInt("accom_no"));
				accom.setAccom_name(rs.getString("accom_name"));
				accom.setAccom_type(rs.getString("accom_type"));
				accom.setAccom_addr(rs.getString("accom_addr"));
				accom.setAccom_func(rs.getString("accom_func"));
				accom.setAccom_map(rs.getString("accom_map"));
				accom.setAccom_cont(rs.getString("accom_cont"));
				accom.setAccom_rat(rs.getInt("accom_rat"));
				accom.setInout(rs.getString("inout"));
				accom.setArea(rs.getString("area"));
				System.out.println("확인");
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return accom;
	}
	public int delete(String accom_no, String user_pass) throws SQLException {
		
		Connection conn = null;	PreparedStatement pstmt= null; 
		int result = 0;		    ResultSet rs = null;
		String sql1 = "select user_pass from member where user_pass=? and user_id='admin'";
		String sql="delete from accom where accom_no=?";
		try {
			String dbPasswd = "";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, user_pass);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPasswd = rs.getString(1); 
				if (dbPasswd.equals(user_pass)) {
					rs.close();  pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, accom_no);
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
	public Accom select2(String accom_no) throws SQLException{
		Connection conn = null;	Statement stmt= null; ResultSet rs = null;
		String sql = "select * from accom where accom_no="+accom_no;
		Accom accom = new Accom();
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {				
				accom.setAccom_no(rs.getInt("accom_no"));
				accom.setAccom_name(rs.getString("accom_name"));
				accom.setAccom_type(rs.getString("accom_type"));
				accom.setAccom_addr(rs.getString("accom_addr"));
				accom.setAccom_func(rs.getString("accom_func"));
				accom.setAccom_map(rs.getString("accom_map"));
				accom.setAccom_cont(rs.getString("accom_cont"));
				accom.setAccom_rat(rs.getInt("accom_rat"));
				accom.setInout(rs.getString("inout"));
				accom.setArea(rs.getString("area"));
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return accom;
	}
	public int update(Accom accom) throws SQLException{
		Connection conn = null;	PreparedStatement pstmt= null; 
		int result = 0;			
		String sql="update accom set accom_name=?,accom_type=?,accom_addr=?,"
				+ "accom_func=?,accom_map=?,accom_cont=?,inout=?, area=? where accom_no=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accom.getAccom_name());
			pstmt.setString(2, accom.getAccom_type());
			pstmt.setString(3, accom.getAccom_addr());
			pstmt.setString(4, accom.getAccom_func());
			pstmt.setString(5, accom.getAccom_map());
			pstmt.setString(6, accom.getAccom_cont());
			pstmt.setString(7, accom.getInout());
			pstmt.setString(8, accom.getArea());
			pstmt.setInt(9, accom.getAccom_no());
			
			result = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			conn.close();
			pstmt.close();
		}
		return result;
	}
	public int insertAphoto(Aphoto aphoto) throws SQLException{
		Connection conn = null;	PreparedStatement pstmt= null; 
		int result = 0;			ResultSet rs = null;
		String sql1 = "select nvl(max(accom_no),0) from aphoto";
		String sql="insert into aphoto values(?,?,?)";
		try {		
			System.out.println("****포토 시작****");
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			rs.next();
			int number = rs.getInt(1) + 1;  
			rs.close();   
			pstmt.close();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setInt(2, 1);
			pstmt.setString(3, aphoto.getPhoto());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setInt(2, 2);
			pstmt.setString(3, aphoto.getPhoto2());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setInt(2, 3);
			pstmt.setString(3, aphoto.getPhoto3());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setInt(2, 4);
			pstmt.setString(3, aphoto.getPhoto4());
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {	
			System.out.println(""+e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}
	public int insertAccom_info(Accom_info info) throws SQLException{
		Connection conn = null;	PreparedStatement pstmt= null; 
		int result = 0;			ResultSet rs = null;
		String sql1 = "select nvl(max(accom_no),0) from accom_info";
		String sql="insert into accom_info values (?,?,?,null,null,?,null,?)";
		try {		
			System.out.println("****인포 시작****");
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			rs.next();
			int number = rs.getInt(1) + 1;  
			rs.close();   
			pstmt.close();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, "Single");
			pstmt.setInt(3, info.getNum_people());
			pstmt.setInt(4, info.getPrice() );
			pstmt.setInt(5, 1);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, "Double");
			pstmt.setInt(3, info.getNum_people2());
			pstmt.setInt(4, info.getPrice2() );
			pstmt.setInt(5, 2);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, "Family");
			pstmt.setInt(3, info.getNum_people3());
			pstmt.setInt(4, info.getPrice3() );
			pstmt.setInt(5, 3);
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
	
}
