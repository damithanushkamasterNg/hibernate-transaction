package repository;

import org.hibernate.Session;

import entity.OrderDetailEntity;

public class OrderDetailRepository {
    public Integer save(OrderDetailEntity detailEntity, Session session){
        Integer id = (Integer) session.save(detailEntity);
        return id;
    }
}