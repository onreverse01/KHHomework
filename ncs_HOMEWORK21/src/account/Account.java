package account;

public class Account {

	private String account;
	private int balance;
	private double interestRate;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(String account, int balance, double interstRate) {
		super();
		this.account = account;
		this.balance = balance;
		this.interestRate = interstRate;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public double getInterstRate() {
		return interestRate;
	}
	public void setInterstRate(double interstRate) {
		this.interestRate = interstRate;
	}
	
	public double calculateInterest() {
		return balance*(interestRate/100);
	}
	
	public void deposit (int money) {
		balance+=money;
	}
	
	public void withdraw (int money) {
		if(balance>=0) {
			balance-=money;
		}
	}
	
	public void accountInfo() {
		System.out.print("계좌번호: "+account+" 잔액: "+balance+"원 이자율: "+interestRate+"%");
	}
	
}
