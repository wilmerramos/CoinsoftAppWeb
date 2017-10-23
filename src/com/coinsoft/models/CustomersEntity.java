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


    public Customer findById(int id) {
        return findByCriteria(
                String.format("WHERE customer_id = '%d'", id)).get(0);
    }

    public Customer findByName(String name) {
        return findByCriteria(
                String.format("WHERE name = '%s'", name)).get(0);
    }

    public Customer findByLastName(String lastName) {
        return findByCriteria(
                String.format("WHERE last_name = '%s'", lastName)).get(0);
    }

    public List<Customer> findAll() {
        return findByCriteria("");
    }


    public List<Customer> findAllWithManagement() {
        return findByCriteria("customer_id IN (SELECT DISTINCT customer_id FROM managements)");
    }

    private int getMaxId() {
        String sql = "SELECT MAX(customer_id) AS max_id FROM customers";
        try {
            ResultSet resultSet = getConnection() .createStatement() .executeQuery(sql);
            return resultSet.next() ? resultSet.getInt("max_id") : 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getMaxAge() {
        String sql = "SELECT MAX(age) AS max_age FROM customers";
        try {
            ResultSet resultSet = getConnection() .createStatement() .executeQuery(sql);
            return resultSet.next() ? resultSet.getInt("max_age") : 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getMinId() {
        String sql = "SELECT MIN(customer_id) AS min_id FROM customers";
        try {
            ResultSet resultSet = getConnection() .createStatement() .executeQuery(sql);
            return resultSet.next() ? resultSet.getInt("min_id") : 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getMinAge() {
        String sql = "SELECT MIN(age) AS min_age FROM customers";
        try {
            ResultSet resultSet = getConnection() .createStatement() .executeQuery(sql);
            return resultSet.next() ? resultSet.getInt("min_age") : 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public Customer create(Customer customer) {
        return executeUpdate(String.format(
                "INSERT INTO %s(customer_id, name, last_name, age , status ) VALUES(%d, '%s', '%s',%d,'%s')",
                getTableName(), customer.getId(), customer.getName(), customer.getLastName(),
                customer.getAge(),customer.getStatus()))
                ? customer : null;
    }

    public Customer create(String name, String lastName,int age, String status) {
        return create(new Customer(getMaxId()+1,name,lastName,age,status));
    }

    public Customer create(int id, String name, String lastName,int age, String status) {
        return create(new Customer(id, name,lastName,age,status));
    }

    public boolean update(int id, String name, String lastName,int age, String status) {
        return executeUpdate(String.format(
                "UPDATE %s SET name = '%s', last_name='%s', age=%d, status='%s' WHERE customer_id = %d",
                getTableName(), name, lastName, age, status, id));
    }

    public boolean update(Customer customer) {
        return update(customer.getId(), customer.getName(), customer.getLastName(),
                customer.getAge(), customer.getStatus() );}


    public boolean erase(int id) {
        return executeUpdate(String.format("DELETE FROM %s WHERE id_customer = %d",
                getTableName(), id));
    }

    public boolean erase(Customer customer) {
        return executeUpdate(String.format("DELETE FROM %s WHERE id_customer = %d",
                getTableName(), customer.getId()));
    }


}