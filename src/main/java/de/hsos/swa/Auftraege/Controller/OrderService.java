package de.hsos.swa.Auftraege.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import de.hsos.swa.Auftraege.Entity.Order;
import de.hsos.swa.Auftraege.Entity.OrderCatalog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import de.hsos.swa.Auftraege.Controller.Events.NewOrderEvent;
import de.hsos.swa.Flotten.Controller.Events.OrderAccepted;
import de.hsos.swa.Flotten.Entity.Ship;


@ApplicationScoped
public class OrderService implements OrderManager{
    @Inject
    OrderCatalog orderCatalog;

    @Inject
    Event<NewOrderEvent> newOrderEvent;

    @Override
    public void createOrder(Order order) {
        orderCatalog.createOrder(order);
        newOrderEvent.fire(new NewOrderEvent(order.getId()));
    }

    public void onOrderAccepted(@Observes OrderAccepted event) {
        Ship ship = event.getShip();
        
        URI shipUri;
        try {
            shipUri = new URI("/ship/" + ship.getID());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        
        List<Order> allOrder = orderCatalog.getAllOrders();
        List<Order> shiplessOrder = new ArrayList<>();
        
        for (Order order : allOrder) {
            if (order.getShip() == null) {
                shiplessOrder.add(order);          
            }            
        }
        
        if (!shiplessOrder.isEmpty()) {
            Order updatedOrder = shiplessOrder.get(0);
            updatedOrder.setShip(shipUri);
            orderCatalog.updateOrder(updatedOrder, updatedOrder.getId());
            
            System.out.println("Auftrag " + updatedOrder.getId() + 
                            " wurde dem Schiff mit ID " + ship.getID() + " zugewiesen");
        }
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
