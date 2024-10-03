package accountbook;

public class Analyze {
	private String userId;
	private int totalIncome; // 총수입 (이월)
	private int totalLosses; // 총지출 (이번달)
	private int remainMoney; // 남은 잔액 (총수입 - 지출)
	private double expenseIncomeRate; // 수입대비 지출 퍼센트

	public Analyze(String userId, int totalIncome, int totalLosses, int remainMoney, double expenseIncomeRate) {
		this.userId = userId;
		this.totalIncome = totalIncome;
		this.totalLosses = totalLosses;
		this.remainMoney = remainMoney;
		this.expenseIncomeRate = expenseIncomeRate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(int totalIncome) {
		this.totalIncome = totalIncome;
	}

	public int getTotalLosses() {
		return totalLosses;
	}

	public void setTotalLosses(int totalLosses) {
		this.totalLosses = totalLosses;
	}

	public int getRemainMoney() {
		return remainMoney;
	}

	public void setRemainMoney(int remainMoney) {
		this.remainMoney = remainMoney;
	}

	public double getExpenseIncomeRate() {
		return expenseIncomeRate;
	}

	public void setExpenseIncomeRate(double expenseIncomeRate) {
		this.expenseIncomeRate = expenseIncomeRate;
	}

	@Override
	public String toString() {
		return "Analyze [userId=" + userId + ", totalIncome=" + totalIncome + ", totalLosses=" + totalLosses
				+ ", remainMoney=" + remainMoney + ", expenseIncomeRate=" + expenseIncomeRate + "]";
	}

}
