package de.hsos.swa.Flotten.Controller;

import java.util.List;
import java.util.Optional;

import de.hsos.swa.Auftraege.Controller.Events.NewOrderEvent;
import de.hsos.swa.Flotten.Controller.Events.OrderAccepted;
import de.hsos.swa.Flotten.Entity.Ship;
import de.hsos.swa.Flotten.Entity.ShipCatalog;
import de.hsos.swa.Flotten.Entity.ShipState;
import de.hsos.swa.Flotten.Gateway.ShipRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

// muss das applschoped sein?
@ApplicationScoped
public class ShipService implements ShipController {

    @Inject
    ShipCatalog shipRepository;

    @Inject
    Event<OrderAccepted> orderAccepted;


    public void onNewOrder(@Observes NewOrderEvent event) {
    long orderID = event.getOrderID();
    Optional<Ship> freeShip = shipRepository.findFirstFreeShip();
    
    freeShip.ifPresent(ship -> {
        ship.setState(ShipState.BOOKED);
        shipRepository.updateShip(ship.getID(), ship);
        
        orderAccepted.fire(new OrderAccepted(ship, orderID)); 
        
        System.out.println("Schiff " + ship.getName() + " wurde für Auftrag " + orderID + " gebucht");
    });
    }




    @Override
    public void createShip(String name) {

        shipRepository.createShip(name);
    }

    @Override
    public Ship getShip(long ID) {
        return shipRepository.getShip(ID);

    }

    @Override
    public List<Ship> getAllShips() {
        return shipRepository.getAllShips();
    }

    @Override
    public Ship updateShip(long ID, Ship ship) {
        return shipRepository.updateShip(ID, ship);
    }

    @Override
    public void deleteShip(long ID) {
        shipRepository.deleteShip(ID);
    }

}
