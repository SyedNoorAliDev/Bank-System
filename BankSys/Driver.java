package BankSys;
import java.io.*;
import java.util.*;

public class Driver implements Serializable{
    public static List<Person> persons = new ArrayList<Person>();
    private static Person  searchPerson(String ID)
    {
        for(Person p:persons)
        {
            if(p.getCNIC().compareTo(ID)==0)
                return p;
        }
        return null;
    }
    public static void loadPeople() throws FileNotFoundException {
            File file = new File("src/BankSys/Persons.txt");
            Scanner sc = new Scanner(file);

// read() method : reading and printing Characters
            // one by one
            while (sc.hasNextLine())
            {
                String l=sc.nextLine();
                String st[]=l.split(",");
                Person p=new Person(st[0],st[1],Integer.parseInt(st[2]));
                persons.add(p);

            }


    }
    public static void loadCustomers() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("src/BankSys/Clients.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Bank.Clist = (List<Client>) ois.readObject();
        ois.close();
        fis.close();
    }
    public  static void loadAccounts() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("src/BankSys/Accounts.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Bank.aclist = (List<Account>) ois.readObject();
        ois.close();
        fis.close();
    }
    public static void loadAll() throws IOException, ClassNotFoundException {
        loadCustomers();
        loadAccounts();
        loadPeople();
    }
    private static Bank loadBank() {
        Bank b = new Bank ("--- HBL ---");
        return b;
    }


    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Bank bank = loadBank();
        System.out.println("---------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------");
        System.out.println("-----------------------"+Bank.bankname+"-----------------------------");
        System.out.println("-------------------- Rishta Bharosay ka -----------------------");
        System.out.println("---------------------------------------------------------------");
        loadAll();
        Scanner input = new Scanner(System.in);
        String choice="";
        while(choice.compareTo("F")!=0)
        {
            System.out.println("Enter the following \n C - adding clients \n A - adding account");
            System.out.println(" W - withdrawing money \n D - deposit money \n I - client Info \n B- Bank's Info ");
            System.out.println(" X - Account detail \n F - exit");
            switch(input.next())
            {
                case "C": {
                    boolean t = true;
                    Scanner sc = new Scanner(System.in);
                        System.out.println("Enter CNIC: ");
                        String cnic = sc.next();
                        for (Person po : persons) {
                            t = true;
                            if (Driver.searchPerson(cnic) == null) {
                                t = false;
                            } else {
                                t=true;
                                Client c = new Client(Driver.searchPerson(cnic));
                                c.writeCl();
                                System.out.println("Clients Successfully Added!! .......");
                                break;
                            }
                        }
                    if (!t) {
                        System.out.println("Person Not Found!.......");
                        break;
                    }





                }
                case "A":
                {   boolean t=true;
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter CNIC: ");
                    String cnic = sc.next();
                    for (Client co : Bank.Clist) {
                        t = true;
                        if ((cnic.compareTo(co.personDetails.CNIC)) == 0) {
                            System.out.println("Enter Amount: ");
                            int ammount = sc.nextInt();
                            Account ac = new Account(ammount, co);
                            ac.writeAcc();
                            System.out.println("Your Account Number: "+ac.accnum);
                            System.out.println("Account Added Successfully !! .......");
                            t=true;
                            break;
                        } else {
                            t = false;

                        }

                    }
                    if (!t) {
                        System.out.println("Person/Client Not Found!.......");
                        break;
                    }

                    break;
                }
                case "I": {
                    System.out.println("Enter CNIC: ");
                    Scanner sc = new Scanner(System.in);
                    Boolean t = true;
                    String cnic = sc.next();
                    for (Client co : Bank.Clist) {
                        t = true;
                        if ((cnic.compareTo(co.personDetails.CNIC)) == 0) {
                            System.out.println(co);
                            for (Account a : Bank.aclist) {
                                if (a.accholder.equals(co)) {
                                    System.out.println(a);
                                    t=true;
                                }
                            }
                            break;
                        }
                        else {
                            t=false;
                        }
                    }
                    if (t == false) {
                        System.out.println("Person/Client Not Found!.......");
                        break;
                    }
                    break;
                }
                case "X":{
                     boolean t=true;
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter CNIC: ");
                    String cnic = sc.next();
                    System.out.println("Enter Account Number: ");
                    String acno = sc.next();
                    for (Client co : Bank.Clist) {
                        t = true;
                        if ((cnic.compareTo(co.personDetails.CNIC)) == 0) {
                            t = true;
                            System.out.println(co);
                            for (Account a:Bank.aclist){
                                if (a.accnum.equals(acno)){
                                    System.out.println(a);
                                    break;
                                }
                            }
                            break;
                        } else {
                            t = false;
                        }
                    }
                    if (t == false) {
                        System.out.println("Person/Client Not Found!.......");
                        break;
                    }
                    break;
                }
                case "B": {
                    System.out.println("--------------------------- Bank Info -----------------------------");
                    for (Client c:Bank.Clist){
                        System.out.println("---------------------------------------------------------------");
                        System.out.println(c);
                        for (Account a:Bank.aclist){
                            if (a.accholder.equals(c)){
                                System.out.println(a);
                            }

                        }

                    }
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("Total Accounts: "+Bank.aclist.size());
                    System.out.println("Total Clients: "+Bank.Clist.size());
                    System.out.println("---------------------------------------------------------------");
                    break;

                }
                case "D" :{
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter CNIC: ");
                    String nic = sc.next();
                    System.out.println("Enter Account Number: ");
                    String acnumb = sc.next();
                    Client cp;
                    boolean t= true;
                    for (Client co : Bank.Clist) {
                        if ((nic.compareTo(co.personDetails.CNIC)) == 0) {
                            t=true;
                            break;
                        }
                        else {
                            t=false;
                        }
                    }
                    if (!t){
                        System.out.println("Incorrect CNIC / Account NUmber");
                    } else {
                        for (Account a:Bank.aclist){
                            if ((a.accnum).compareTo(acnumb) == 0){
                                System.out.println("Enter Amount to Deposit: ");
                                float dep = sc.nextFloat();
                                a.deposit(dep);
                                a.writeAcc();
                            }
                        }

                    }


                }
                case "W":{
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter CNIC: ");
                    String nic = sc.next();
                    System.out.println("Enter Account Number: ");
                    String acnumb = sc.next();
                    Client cp;
                    boolean t= true;
                    for (Client co : Bank.Clist) {
                        if ((nic.compareTo(co.personDetails.CNIC)) == 0) {
                            t=true;
                            break;
                        }
                        else {
                            t=false;
                        }
                    }
                    if (!t){
                        System.out.println("Incorrect CNIC / Account NUmber");
                    } else {
                        for (Account a:Bank.aclist){
                            if ((a.accnum).compareTo(acnumb) == 0){
                                System.out.println("Enter Amount to WithDraw: ");
                                float dep = sc.nextFloat();
                                a.withdraw(dep);
                                a.writeAcc();
                            }
                        }

                    }
                }



            }
            break;
        }


    }







}



