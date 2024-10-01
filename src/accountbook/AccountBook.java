package accountbook;

import java.io.Serializable;

public class AccountBook implements Serializable{
    private int idx;
    private String userId;
    private String detail;
    private int income;
    private int losses;
    private String date;
    
    
    public AccountBook(int idx, String userId, String detail, int income, int losses, String date) {
		this.idx = idx;
		this.userId = userId;
		this.detail = detail;
		this.income = income;
		this.losses = losses;
		this.date = date;
	}


	public int getIdx() {
		return idx;
	}


	public void setIdx(int idx) {
		this.idx = idx;
	}


	public String getuserId() {
		return userId;
	}


	public void setuserId(String userId) {
		this.userId = userId;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public int getIncome() {
		return income;
	}


	public void setIncome(int income) {
		this.income = income;
	}


	public int getLosses() {
		return losses;
	}


	public void setLosses(int losses) {
		this.losses = losses;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "AccountBook [idx=" + idx + ", userId=" + userId + ", detail=" + detail + ", income=" + income
				+ ", losses=" + losses + ", date=" + date + "]\n";
	}
	


  

}
