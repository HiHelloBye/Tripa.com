package dao;

import java.util.Date;

public class Member {
	private String user_id;
	private String user_pass;
	private String user_name;
	private String user_gen;
	private String user_email;
	private String user_question;
	private String user_answer;
	private String user_cell;
	private Date time;
	private int saving_pnt;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_gen() {
		return user_gen;
	}
	public void setUser_gen(String user_gen) {
		this.user_gen = user_gen;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	public String getUser_question() {
		return user_question;
	}
	public void setUser_question(String user_question) {
		this.user_question = user_question;
	}
	public String getUser_answer() {
		return user_answer;
	}
	public void setUser_answer(String user_answer) {
		this.user_answer = user_answer;
	}
	public String getUser_cell() {
		return user_cell;
	}
	public void setUser_cell(String user_cell) {
		this.user_cell = user_cell;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getSaving_pnt() {
		return saving_pnt;
	}
	public void setSaving_pnt(int saving_pnt) {
		this.saving_pnt = saving_pnt;
	}
}