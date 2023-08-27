package repository;

import org.hibernate.Session;

import entity.ItemEntity;

public class ItemRepository {
    
    public ItemEntity getItem(String id, Session session){
        ItemEntity itemEntity = session.get(ItemEntity.class, id);
        return itemEntity;
    }

    public void update(ItemEntity itemEntity, Session session){
        session.update(itemEntity);
    }
}