package com.coinsoft.models;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
    private int idcustomer;
    private String name;
    private String last_name;
    private int age;
    private String status;

    public Customer(int idcustomer, String name, String last_name, int age, String status) {
        this.idcustomer = idcustomer;
        this.name = name;
        this.last_name = last_name;
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

    public String getLast_name() {
        return last_name;
    }

    public Customer setLast_name(String last_name) {
        this.last_name = last_name;
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
                    rs.getString("last_name"),
                    rs.getInt("age"),
                    rs.getString("status")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

}

