
import java.util.*;
import java.io.IOException;
import java.sql.*;

public class Customer {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Account Number:");
		int acc_no = sc.nextInt();
		System.out.println("Enter Pin Number:");
		int inp_pin = sc.nextInt();
		if (inp_pin == getPin(acc_no)) {
			System.out.println("1:Check Balance\n	"
					+ "2:Withdraw Money\n	"
					+ "3:Transfer Money\n	"
					+ "4:Chech ATM Balance");

			System.out.println("Enter Your Choice:");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				System.out.println(" Account balance =" + getBalance(acc_no));
				break;
			case 2:
				System.out.println("Enter amount:");
				int amount = sc.nextInt();
				if (amount > getBalance(acc_no) || ATM_Machine.getATMBalance() < amount) {
					System.out.println("Not Enough Balance");
					break;
				}
				if (amount > 10000 || amount < 100 || amount % 100 != 0) {
					System.out.println(" You have Entered Invalid Amount");
					break;
				}
				ATM_Machine.reduceATM(amount);
				withdraw(acc_no, amount);
				System.out.println("Withdrawn Amount:" + amount);
				break;
			case 3:
				System.out.println("Enter amount:");
				int transferamount = sc.nextInt();
				if (transferamount > getBalance(acc_no)) {
					System.out.println("Not Enough Balance");
					break;
				}
				System.out.println("Enter  Account Number: to send amount");
				int acc_no2 = sc.nextInt();
				Transfer(acc_no, acc_no2, transferamount);
				System.out.println("Transaction is done Successfully");
				System.out.println(transferamount + " Transefered to " + acc_no2);
				break;
			case 4:
				ATM_Machine.displayBalance();
				break;
			}
		}

		sc.close();
	}

	public static int getPin(int accountnumber) throws SQLException {
		Connection conn = DButil.createConnection();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("Select Pin from ATMUsers where Acc_No=" + accountnumber);
		int PIN = 0;
		while (rs.next()) {
			pin = rs.getInt(1);
		}
		conn.close();
		return PIN;
	}

	public static int getBalance(int accountnumber) throws SQLException {
		Connection conn = DButil.createConnection();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("Select Balance from ATMUsers where Acc_No=" + accountnumber);
		int balance = 0;
		while (rs.next()) {
			balance = rs.getInt(1);
		}
		conn.close();
		return balance;
	}

	public static void withdraw(int accountnumber, int amount) throws SQLException {
		Connection conn = DButil.createConnection();
		Statement statement = conn.createStatement();
		statement.executeUpdate(
				"Update ATMUsers Set Balance=" + (getBalance(accountnumber) - amount) + " where Acc_No=" + accountnumber);
		conn.close();
	}

	public static void Transfer(int accountnumber1, int accountnumber2, int amount) throws SQLException {
		Connection conn = DButil.createConnection();
		Statement statement = conn.createStatement();
		statement.executeUpdate("Update ATMUsers Set Balance=" + (getBalance(accountnumber1) - amount) + " where Acc_No="
				+ accountnumber1);
		statement.executeUpdate("Update ATMUsers Set Balance=" + (getBalance(accountnumber2) + amount) + " where Acc_No="
				+ accountnumber2);
		conn.close();
	}
}
