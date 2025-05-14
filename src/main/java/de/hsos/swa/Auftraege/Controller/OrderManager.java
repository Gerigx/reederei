package de.hsos.swa.Auftraege.Controller;

import java.util.List;

import de.hsos.swa.Auftraege.Boundary.DTO.OrderUpdateDTO;
import de.hsos.swa.Auftraege.Entity.Order;

public interface OrderManager {
    public void createOrder(Order order);
    public Order getOrder(long ID);
    public List<Order> getAllOrders();
    public Order updateOrder(Order order);
    public boolean deleteOrder(long ID);
}
