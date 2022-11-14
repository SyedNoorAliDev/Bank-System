package BankSys;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class Bank {
    public static String bankname;
    static List<Client> Clist = new ArrayList<Client>();
    static List<Account> aclist = new ArrayList<Account>();

    Bank(String a){
        this.bankname = a;
    }
    Client addClient(Person p) throws IOException {
        Client c = new Client(p);
        return c;
    }
    Account addAccount(float amount, Client c) throws IOException {
        Account a = new Account(amount,c);
        return a;
    }
    Account searchAccount(String id){
        for (Account a:aclist){
            if (a.getAccnum().compareTo(id) == 0){
                return a;
            }
        }
        return null;
    }
    Boolean removeClient (String id){
        boolean b = true;
        for (Client c:Clist){
            if((c.Id).compareTo(id) == 0){
                Clist.remove(c);
                return true;
            }
        } return false;
    }
    float sum =0;
    float totalAmount(){
        for (Client c:Clist){
            for (Account a:c.acclist){
                sum += a.amount();
            }
        }
        return sum;
    }
    public String toString(){
        String po = "";
        for (Client c:Clist){
            po += ("--------------------"+"\n");
            po += (c+"\n");
            for (Account a: c.acclist){
                po += a+"\n";
            }
            po += ("--------------------"+"\n");
        }

        return po;
    }
    void writeBank() throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream("src/BankSys/Bank.txt",false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Driver.loadAll();
        oos.writeObject(this);
        oos.close();
        fos.close();
    }


}
