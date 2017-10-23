package com.coinsoft.models;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
    private int id;
    private String name;
    private String lastName;
    private int age;
    private String status;

    public Customer(int id, String name, String lastName, int age, String status) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.status = status;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public Customer setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer setLastName(String lastName) {
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
                    rs.getInt("costumer_id"),
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

