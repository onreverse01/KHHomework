package account;

public class AccountTest {

	public static void main(String[] args) {
		Account account = new Account("441-0290-1203", 500000, 0.073);
		System.out.println("Account(계좌번호):"+account.getAccount()
			+", balance(잔액):"+account.getBalance()
			+"원, interestRate(이율):"+account.getInterstRate());
		account.deposit(20000);
		System.out.println("Account(계좌번호):"+account.getAccount()
			+", balance(잔액):"+account.getBalance()
			+"원, interestRate(이율):"+account.getInterstRate());
		System.out.println("이자:"+account.calculateInterest());
	}

}
