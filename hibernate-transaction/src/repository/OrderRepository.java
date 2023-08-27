package repository;

import org.hibernate.Session;

import entity.OrderEntity;

public class OrderRepository {
    public String save(OrderEntity orderEntity, Session session){
        String id = (String) session.save(orderEntity);
        return id;
    }
}