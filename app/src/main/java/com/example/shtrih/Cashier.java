package com.example.shtrih;

public class Cashier {
    public int id;
    public String name;
    public String inn;
    public String password;

    public Cashier(int Id, String Name,String INN, String Password)
    {
        name = Name;
        inn = INN;
        password = Password;
        id = Id;

    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
