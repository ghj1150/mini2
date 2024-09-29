package accountbook;

public class AccountBook {
    private int idx;
    private int userIdx;
    private int income;
    private int losses;
    private String detail;


    public AccountBook(int idx, int userIdx, int income, int losses, String detail){
        this.idx = idx;
        this.userIdx = userIdx;
        this.income = income;
        this.losses = losses;
        this.detail = detail;
    }

    public int getIdx() {
        return idx;
    }
    public void setIdx(int idx) {
        this.idx = idx;
    }
    public int getUserIdx() {
        return userIdx;
    }
    public void setUserIdx(int userIdx) {
        this.userIdx = userIdx;
    }

}
