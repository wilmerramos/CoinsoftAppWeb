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