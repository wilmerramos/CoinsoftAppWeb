package com.coinsoft.models;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
    private int idcustomer;
    private String name;
    private String lastName;
    private int age;
    private String status;

    public Customer(int idcustomer, String name, String lastName, int age, String status) {
        this.idcustomer = idcustomer;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.status = status;

    }
    public Customer() {

    }

    public int getIdcustomer() {
        return idcustomer;
    }

    public Customer setIdcustomer(int idcustomer) {
        this.idcustomer = idcustomer;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getlastName() {
        return lastName;
    }

    public Customer setLast_name(String last_name) {
        this.lastName = lastName;
        return this;

    }

    public int getAge() {
        return age;
    }

    public Customer setAge(int age) {
        this.age = age;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Customer setStatus(String status) {
        this.status = status;
        return this;
    }


    public static Customer from(ResultSet rs) {
        try {
            return new Customer(
                    rs.getInt("idcustomer"),
                    rs.getString("name"),
                    rs.getString("lastName"),
                    rs.getInt("age"),
                    rs.getString("status")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

}

