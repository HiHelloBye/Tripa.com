package dao;

public class Accom_com {
	private int	accom_no;
	private float accom_rat;
	private String user_id;
	private String review_cont;
	private int saving_pnt;
	private String photo;
	private int com_no;
	private String accom_name;
	
	public String getAccom_name() {
		return accom_name;
	}
	public void setAccom_name(String accom_name) {
		this.accom_name = accom_name;
	}
	public int getCom_no() {
		return com_no;
	}
	public void setCom_no(int com_no) {
		this.com_no = com_no;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getAccom_no() {
		return accom_no;
	}
	public void setAccom_no(int accom_no) {
		this.accom_no = accom_no;
	}
	
	public float getAccom_rat() {
		return accom_rat;
	}
	public void setAccom_rat(float accom_rat) {
		this.accom_rat = accom_rat;
	}
	public void setAccom_rat(int accom_rat) {
		this.accom_rat = accom_rat;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getReview_cont() {
		return review_cont;
	}
	public void setReview_cont(String review_cont) {
		this.review_cont = review_cont;
	}
	public int getSaving_pnt() {
		return saving_pnt;
	}
	public void setSaving_pnt(int saving_pnt) {
		this.saving_pnt = saving_pnt;
	}
	
}
