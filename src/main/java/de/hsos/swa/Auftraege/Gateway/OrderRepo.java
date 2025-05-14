package de.hsos.swa.Auftraege.Gateway;

import java.util.List;

import de.hsos.swa.Auftraege.Entity.Order;
import de.hsos.swa.Auftraege.Entity.OrderCatalog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Transactional
@RequestScoped
public class OrderRepo implements OrderCatalog{

    @Inject
    EntityManager entityManager;

    @Override
    public Order createOrder(Order order) {
        entityManager.persist(order);
        entityManager.flush();
        entityManager.detach(order);
        entityManager.clear();  
        return order;
    }

    @Override
    public Order getOrder(long Id) {
        Order order = entityManager.find(Order.class, Id);
        entityManager.detach(order);
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = entityManager.createQuery("SELECT orders FROM Order orders", Order.class).getResultList();
        for (Order order : orders) {
            entityManager.detach(order);
        }
        return orders;
    }

    @Override
    public Order updateOrder(Order order, long Id) {
        return entityManager.merge(order);
    }

    @Override
    public boolean deleteOrder(long Id) {
    Order order = entityManager.find(Order.class, Id);
    if (order != null) {
        entityManager.remove(order);
        return true;
    }
    return false;
    }

}
