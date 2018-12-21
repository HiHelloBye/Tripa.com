package dao;

public class Reservation {
	public int getAccom_no() {
		return accom_no;
	}
	public void setAccom_no(int accom_no) {
		this.accom_no = accom_no;
	}
	public String getRoom_no() {
		return room_no;
	}
	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}
	public int getReser_no() {
		return reser_no;
	}
	public void setReser_no(int reser_no) {
		this.reser_no = reser_no;
	}
	public String getOut_date() {
		return out_date;
	}
	public void setOut_date(String out_date) {
		this.out_date = out_date;
	}
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getRes_cont() {
		return res_cont;
	}
	public void setRes_cont(String res_cont) {
		this.res_cont = res_cont;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getRes_name() {
		return res_name;
	}
	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}
	public String getUser_cell() {
		return user_cell;
	}
	public void setUser_cell(String user_cell) {
		this.user_cell = user_cell;
	}
	public int getRsaving_pnt() {
		return rsaving_pnt;
	}
	public void setRsaving_pnt(int rsaving_pnt) {
		this.rsaving_pnt = rsaving_pnt;
	}
	private int accom_no;
	private String room_no;
	private int reser_no;
	private String out_date;
	private String in_date;
	private String user_email;
	private String res_cont;
	private String user_id;
	private String res_name;
	private String user_cell;
	private int rsaving_pnt;
	private String accom_name;
	private int flag;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
	public String getAccom_name() {
		return accom_name;
	}
	public void setAccom_name(String accom_name) {
		this.accom_name = accom_name;
	}
	
}
