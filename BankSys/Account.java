package BankSys;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.*;
import java.io.Serializable;

public class Account implements Serializable {
    String accnum;
    float amount;
    Client accholder;
    static int acount;

    Account(float amounty,Client c) throws IOException {
        acount = (Bank.aclist.size())+1;
        this.accnum = (c.Id+"-ACC-"+acount);
        this.amount = amounty;
        this.accholder = c;
        Bank.aclist.add(this);
        c.acclist.add(this);
        writeAcc();
    }
    String getAccnum () {
        return this.accnum;
    }
    float amount () {
        return this.amount;
    }

    float withdraw ( float z) throws IOException {
        this.amount -= z;
        return this.amount;

    }
    float deposit ( float n) throws IOException {
        this.amount += n;
        return this.amount;
    }
    public String toString () {
        return " Account Number: "+this.accnum +","+" Total Amount: "+ this.amount;
    }
    static void writeAcc () throws IOException {
        FileOutputStream fos = new FileOutputStream("src/BankSys/Accounts.txt",false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Bank.aclist);
        oos.close();
        fos.close();


    }
}
