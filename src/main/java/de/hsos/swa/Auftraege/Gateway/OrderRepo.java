package de.hsos.swa.Auftraege.Gateway;

import java.util.List;

import de.hsos.swa.Auftraege.Entity.Order;
import de.hsos.swa.Auftraege.Entity.OrderCatalog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class OrderRepo implements OrderCatalog{

    @Inject
    EntityManager entityManager;

    @Override
    public Order createOrder(Order order) {
        entityManager.persist(order);
        entityManager.detach(order);
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
        return entityManager.createQuery("SELECT orders FROM Order orders", Order.class).getResultList();
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
