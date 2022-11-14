package BankSys;
import BankSys.Driver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Person implements Serializable {
    @Override
    public String toString() {
        return "Person{"+ " Name: '" + Name + " CNIC: " + CNIC + " Number: " + number+"}";
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }


    public Person(String name, String CNIC, int number) {
        Name = name;
        this.CNIC = CNIC;
        this.number = number;
    }

    public Person() {
    }


    String Name;
    String CNIC;
    double number;
}

