package repository;

import org.hibernate.Session;
 
import entity.CustomerEntity;

public class CustomerRepository {
    
    public CustomerEntity getCustomer(String id, Session session){
        CustomerEntity customerEntity =  session.get(CustomerEntity.class, id);
        return customerEntity;
    }

}