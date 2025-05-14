package de.hsos.swa.Auftraege.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import de.hsos.swa.Auftraege.Entity.Order;
import de.hsos.swa.Auftraege.Entity.OrderCatalog;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    long orderID = event.getOrderID(); 
    
    if (ship == null || ship.getID() == 0L) {
        System.out.println("Ungültiges Schiff!");
        return;
    }

    URI shipUri;
    try {
        shipUri = new URI("/ship/" + ship.getID());
        System.out.println("/ship/" + ship.getID());
    } catch (URISyntaxException e) {
        e.printStackTrace();
        return;
    }
    
    Order order = orderCatalog.getOrder(orderID);
    
    if (order != null) {
        order.setShip(shipUri);
        orderCatalog.updateOrder(order, orderID);
        
        System.out.println("Auftrag " + orderID + 
                        " wurde dem Schiff mit ID " + ship.getID() + " zugewiesen");
    } else {
        System.out.println("Auftrag mit ID " + orderID + " nicht gefunden!");
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
