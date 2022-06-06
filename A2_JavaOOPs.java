import java.util.Scanner;
abstract class BankAccount{
    private double balance;
    int depositCount;
    int withdrawlCount;
    double annIntRate;
    final double minBal = 1942.36; // 1$ = 77.69 So $25=Rs.1942.36

    // Parent class constructor
    public BankAccount(double balance, double interestRate){
        this.balance = balance;
        this.annIntRate = interestRate;
    }
    // Getter method
    public double getBalance(){   
        return balance;
    }
    //deposit method
    public void deposit(double depAmount){ 
        balance = balance + depAmount;
        System.out.println("The Balance after deposit: "+balance);
    }
    // Withdraw method
    public void withdraw(double withAmount){
        balance = balance - withAmount;
        System.out.println("The Balance after withdraw: "+balance);
    }
    // method to calculate the Interest
    public void calcInterest(){
        double monthly_Interest;
        double monIntRate = (annIntRate/12);
        monthly_Interest = (balance*monIntRate)/100; 
        balance = balance + monthly_Interest; 
        System.out.println("The Balance after adding Interest: "+balance);
    }
    // Monthly service charge deducting method and re-setting the varibles
    public void monthlyProcess(){
        int diffCount = withdrawlCount - 4;  // if withdrawl count more than 4
        double serviceCharge = diffCount*77.69;  // 1$ = 77.69Rs
        if (balance>minBal) {
            balance = balance - serviceCharge; // deducting service charge
            System.out.println("The balance after deducting Service Charge: "+balance);
        }
        else
            System.out.println("Account Status- Non-Active");
        calcInterest(); // calling calculate monthly Interest method
        depositCount=0;
        withdrawlCount=0;
        serviceCharge=0;
    }
}
// Extended class - SavingAccount
class SavingAccount extends BankAccount{
    // Derived class constructor
    public SavingAccount(double balance, double interestRate){
        super(balance, interestRate); // calling superclass constructor
    }
    // To check Account Status
    public boolean checkAccStatus(){
        if (getBalance()>minBal) { // checking minimum balance condition
            System.out.println("Account Status- Active");
            return true;
        }
        else{
            System.out.println("Account Status- Non-Active");
            return false;
        }
    }
    //deposit method
    public void deposit(double amount){
        checkAccStatus();
        super.deposit(amount); // calling superclass deposit method
        depositCount++;  // increamenting the counter
        System.out.println("The count of deposit: "+depositCount);
        checkAccStatus();
    }
    // Withdraw method
    public void withdraw(double amount){
        if (checkAccStatus()) {
            super.withdraw(amount);  // calling superclass withdraw method
            withdrawlCount++;  // increamenting the counter
            System.out.println("The count of withdraw: "+withdrawlCount);
        }
        else
        System.out.println("Can't Withdraw. Below Minimum Balance!");
    }
    // Monthly process status method
    public void monthlyProcess(){
        System.out.println("The count of number of deposit: "+depositCount);
        System.out.println("The count of number of withdrawals: "+withdrawlCount);
        if (withdrawlCount>4) {
            super.monthlyProcess();  // calling superclass monthlyProcess method
        }
    }
}
// Main class  // Test Class
public class A2_JavaOOPs{
    public static void main(String[] args) {
        double amount; int choice;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Intial Account Balance: ");
        double balance = in.nextDouble();
        System.out.print("Enter the Annual Rate of Interest: ");
        double interestRate = in.nextDouble();
        BankAccount obj = new SavingAccount(balance, interestRate);

        System.out.print("\nPlease enter the amount you want to withdraw : ");
        amount = in.nextDouble();
        obj.withdraw(amount);

        System.out.print("\nPlease enter the amount you want to deposit : ");
        amount = in.nextDouble();
        obj.deposit(amount);

        while (true) {
            System.out.println("\nDo you want to make any more withdrawals? If yes please enter 1 and if no enter 0");
            choice = in.nextInt();
            if (choice==1) {
                System.out.print("Please enter the amount you want to withdraw : ");
                amount = in.nextDouble();
                obj.withdraw(amount);
            }
            else
                break;
        }
        while (true) {
            System.out.println("\nDo you want to make any more deposits? If yes please enter 1 and if no enter 0");
            choice = in.nextInt();
            if (choice==1) {
                System.out.print("Please enter the amount you want to deposit : ");
                amount = in.nextDouble();
                obj.deposit(amount);
            }
            else
                break;
        }
        obj.monthlyProcess();
        in.close();
    }

}