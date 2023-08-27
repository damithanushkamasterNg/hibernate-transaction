package service;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dto.OrderDetailDto;
import dto.OrderDto;
import entity.CustomerEntity;
import entity.ItemEntity;
import entity.OrderDetailEntity;
import entity.OrderEntity;
import repository.CustomerRepository;
import repository.ItemRepository;
import repository.OrderDetailRepository;
import repository.OrderRepository;
import util.SessionFactoryConfiguration;

public class OrderService {

    Session session = SessionFactoryConfiguration.getInstance().getSession();

    CustomerRepository customerRepository = new CustomerRepository();
    OrderRepository orderRepository = new OrderRepository();
    ItemRepository itemRepository = new repository.ItemRepository();
    OrderDetailRepository orderDetailRepository = new OrderDetailRepository();

    public String saveOrder(OrderDto dto) {

        CustomerEntity customerEntity = customerRepository.getCustomer(dto.getCustId(), session);

        Transaction transaction = session.beginTransaction();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(dto.getId());
        orderEntity.setDate(new Date());
        orderEntity.setCustomerEntity(customerEntity);

        String orderId = orderRepository.save(orderEntity, session);
        if (orderId != null) {
            orderEntity.setId(orderId);

            Boolean isOrderDetailSaved = true;

            for (OrderDetailDto orderDetailDto : dto.getDetailDtos()) {
                ItemEntity itemEntity = itemRepository.getItem(orderDetailDto.getItemCode(), session);
                OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
                orderDetailEntity.setDiscount(orderDetailDto.getDiscount());
                orderDetailEntity.setItemEntity(itemEntity);
                orderDetailEntity.setOrderEntity(orderEntity);
                orderDetailEntity.setQty(orderDetailDto.getQty());

                itemEntity.setQoh(itemEntity.getQoh() - orderDetailDto.getQty());

                itemRepository.update(itemEntity, session);
                Integer orderDetailId = orderDetailRepository.save(orderDetailEntity, session);
                if (orderDetailId == null) {
                    isOrderDetailSaved = false;
                }
            }

            if (isOrderDetailSaved) {
                transaction.commit();
                return "Success";
            } else {
                transaction.rollback();
                return "Fail";
            }

        } else {
            transaction.rollback();
            return "Order Save Error";
        }

    }
}