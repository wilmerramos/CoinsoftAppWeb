package com.coinsoft.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersEntity extends BaseEntity {

    public CustomersEntity() {
        super();
        setTableName("customers");
    }

    public CustomersEntity(Connection connection, String tableName) {
        super(connection, tableName);
    }

    public Customer findById(int id) {
        return findByCriteria(
                String.format("WHERE idcustomer = '%d'", id)).get(0);
    }



    public List<Customer> findByCriteria(String criteria) {
        try {
            ResultSet rs = getConnection()
                    .createStatement()
                    .executeQuery(
                            getBaseStatement()
                                    .concat(criteria));
            List<Customer> customers = new ArrayList<>();
            while (rs.next())
                customers.add(Customer.from(rs));

            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Customer findByName(String name) {
        return findByCriteria(
                String.format("WHERE name = '%s'", name)).get(0);
    }

    public Customer findByLastName(String last_name) {
        return findByCriteria(
                String.format("WHERE last_name = '%s'", last_name)).get(0);
    }

    public List<Customer> findAll(CustomersEntity customersEntity) {
        return findByCriteria("");
    }


 /*int idcustomer; String name; String last_name; int age; String status; */

    public boolean create(Customer customer) {
        return executeUpdate(String.format(
                "INSERT INTO %s(idcustomer, name, last_name, age , status ) VALUES(%d, '%s', '%s',%d,'%s')",
                getTableName(), customer.getIdcustomer(), customer.getName(), customer.getLast_name(),
                customer.getAge(),customer.getStatus()));
    }

    public boolean create(int id, String name, String last_name,int age, String status) {
        return create(new Customer(id, name,last_name,age,status));
    }

    public boolean update(int id, String name, String last_name,int age, String status) {
        return executeUpdate(String.format(
                "UPDATE %s SET name = '%s', last_name='%s',  WHERE idcustomer = %d", getTableName(), name, id));
    }

    public boolean update(Customer customer) {
        return update(customer.getIdcustomer(), customer.getName(), customer.getLast_name(),
                customer.getAge(), customer.getStatus() );}


    public boolean erase(int id) {
        return executeUpdate(String.format("DELETE FROM %s WHERE idcustomer = %d",
                getTableName(), id));
    }

    public boolean erase(Customer customer) {
        return executeUpdate(String.format("DELETE FROM %s WHERE idcustomer = %d",
                getTableName(), customer.getIdcustomer()));
    }


}