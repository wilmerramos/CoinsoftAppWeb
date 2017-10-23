package com.coinsoft.models;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CmService {
    private Connection connection;
    private CmDataStore dataStore;

    public CmService() {
        try {
            InitialContext context = new InitialContext();
            dataStore = new CmDataStore();
            connection = ((DataSource) context
                    .lookup("jdbc/MySQLDataSource"))
                    .getConnection();
            dataStore.setConnection(connection);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public CmDataStore getDataStore() {
        return dataStore;
    }

    public void setDataStore(CmDataStore dataStore) {
        this.dataStore = dataStore;
    }


    public Customer findCustomerByName(String name){ return  dataStore.findCustomerByName(name); }

    public Customer findCustomerByLastName(String lastName){ return  dataStore.findCustomerByLastName(lastName);}

    public Customer findCustomerById(int id){ return  dataStore.findCustomerById(id); }

    public List<Customer> findAllCustomers() { return dataStore.findAllCustomers(); }

    public Customer createCustomer(String name, String lastName,int age, String status) {
        return dataStore.createCustomer(name,lastName,age,status);
    }

    public boolean updateCustomer(int id, String name, String lastName,int age, String status) {
        return dataStore.updateCustomer(id, name,lastName,age,status);
    }

}

