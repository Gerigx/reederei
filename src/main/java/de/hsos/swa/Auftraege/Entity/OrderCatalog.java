package de.hsos.swa.Auftraege.Entity;

import java.util.List;

public interface OrderCatalog {
    public Order createOrder(Order order);
    public Order getOrder(long Id);
    public List<Order> getAllOrders();
    public Order updateOrder(Order order, long Id);
    public boolean deleteOrder(long Id);
}
