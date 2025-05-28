import java.util.ArrayList;

public class Finance {

    private double balance;
    private ArrayList<String> transactionList;

    public Finance(){
        this.transactionList = new ArrayList<>();
        this.balance = 500.0;
    }

    public double getBalance() {
        return balance;
    }

    public void printTransactionList(){
        if(transactionList.isEmpty()){
            System.out.println("Ще не було транзакцій");
            return;
        }
        System.out.println("Список транзакцій починаючи з недавніх:");
        for(int i=transactionList.size()-1; i>=0;i--){
            System.out.println(transactionList.get(i));
        }
    }

    public void addMoney(int amount){
        balance+=amount;
        transactionList.add("Додано " + amount + " грн. Поточний баланс: " + balance);
    }

    public boolean removeMoney(int amount){
        if(amount<balance){
            balance-=amount;
            transactionList.add("Витрачено " + amount + " грн. Поточний баланс: " + balance);
            return true;
        }
        else {
            transactionList.add("Спроба витратити " + amount + " грн. — не вдалося (недостатньо коштів). Баланс: " + balance);
            return false;
        }
    }
}
