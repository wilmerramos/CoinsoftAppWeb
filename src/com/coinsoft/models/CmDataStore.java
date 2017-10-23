package com.coinsoft.models;

import java.sql.Connection;
import java.util.List;

public class CmDataStore {

    private Connection connection;
    private AdminsEntity adminsEntity;
    private CustomersEntity customersEntity;
    private ManagersEntity managersEntity;
    private LoansEntity loansEntity;
    private PaymentsEntity paymentEntity;
    private ManagementsEntity managementsEntity;

    public CmDataStore(Connection connection) {
        this.connection = connection;
    }

    public CmDataStore() {
    }

    public Customer findCustomerById(int id){
        if(connection==null) return null;
        return  getCustomersEntity().findById(id);
    }

    public Customer findCustomerByName(String name){
        if(connection==null) return null;
        return  getCustomersEntity().findByName(name);
    }

    public Customer findCustomerByLastName(String lastName){
        if(connection==null) return null;
        return  getCustomersEntity().findByLastName(lastName);
    }

    public List<Customer> findAllCustomers() {
        return connection == null ? null: getCustomersEntity().findAll();
    }

    public Customer createCustomer(String name, String lastName,int age, String status) {
        return connection == null ?
                null :
                getCustomersEntity().create(name,lastName,age,status);
    }

    public boolean updateCustomer(int id, String name, String lastName,int age, String status) {
        return connection == null ?
                false :
                getCustomersEntity().update(id,name,lastName,age,status);
    }

    public boolean updateCustomer(Customer customer) {
        return updateCustomer(customer.getId(), customer.getName(),customer.getLastName(),customer.getAge(),customer.getStatus());
    }

    public boolean eraseCustomer(int id) {
        return connection == null ?
                false :
                getCustomersEntity().erase(id);

    }



    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private CustomersEntity getCustomersEntity() {
        if(customersEntity == null) {
            customersEntity = new CustomersEntity();
            customersEntity.setConnection(connection);
        }
        return customersEntity;
    }


}