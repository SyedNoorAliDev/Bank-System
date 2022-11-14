package BankSys;
import java.util.*;
import java.io.*;
import java.io.Serializable;

public class Client implements Serializable {
    String Id;
    Person personDetails;
    public List<Account> acclist = new ArrayList<>();
    int Ccount;
    String a;
    float amount;
    File file;
    private static final long serialVersionUID = 1113799434508676095L;
    Client(){}

    Client(Person p) throws IOException {
        personDetails = p;
        Ccount = Bank.Clist.size()+1;
        Bank.Clist.add(this);
        Id = "CLE-"+Ccount;



    }

    String GetID() {
        return this.Id;
    }

    float totalAmount() {
        for (Account a : acclist) {
            this.amount += a.amount;
        }
        return this.amount;
    }

    public String toString() {
        a = (personDetails+","+Id);
        return a;

    }

    float withdraw(float amount, String Accnum) throws IOException {
        for (Account a : acclist) {
            if (Accnum.equals(a.accnum)) {
                a.withdraw(amount);
                return a.amount;
            }
        }
        return 0;
    }

    float deposit(float amount, String Accnum) throws IOException {
        for (Account a : acclist) {
            if (Accnum.compareTo(a.accnum) == 0) {
                a.deposit(amount);
                return a.amount;
            }
        }
        return 0;

    }

    void addAccount(Account a1) throws IOException {
        acclist.add(a1);
        writeCl();
    }

    float sum = 0;

    float getTotal() {
        for (Account a : acclist) {
            sum += a.amount;
        }
        return sum;
    }
    public boolean equals(Client cp){
        if (this.Id.compareTo(cp.Id)==0){
            return true;
        }
        return false;
    }

    void writeCl() throws IOException {
        FileOutputStream fos = new FileOutputStream("src/BankSys/Clients.txt",false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Bank.Clist);
        oos.close();
        fos.close();



    }}







