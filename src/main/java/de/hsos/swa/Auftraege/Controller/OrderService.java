package de.hsos.swa.Auftraege.Controller;

import java.util.List;

import de.hsos.swa.Auftraege.Entity.Order;
import de.hsos.swa.Auftraege.Entity.OrderCatalog;
import jakarta.inject.Inject;


public class OrderService implements OrderManager{

    @Inject
    OrderCatalog orderCatalog;

    @Override
    public void createOrder(Order order) {
        orderCatalog.createOrder(order);
    }

    @Override
    public Order getOrder(long ID) {
        return orderCatalog.getOrder(ID);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderCatalog.getAllOrders();
    }

    @Override
    public Order updateOrder(Order order) {
        return orderCatalog.updateOrder(order, order.getId());
    }

    @Override
    public boolean deleteOrder(long ID) {
        return orderCatalog.deleteOrder(ID);
    }

}
