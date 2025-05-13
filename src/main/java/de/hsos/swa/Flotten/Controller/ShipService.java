package de.hsos.swa.Flotten.Controller;

import java.util.List;
import java.util.Optional;

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


    public void onNewOrder(@Observes OrderAccepted event) {
        // Freies Schiff mit Stream API finden
        Optional<Ship> freiesSchiff = shipRepository.findFirstFreeShip();
        
        freiesSchiff.ifPresent(ship -> {
            // Schiff als gebucht markieren
            ship.setState(ShipState.BOOKED);
            // should i give my ship as param? I just could use my ID
            shipRepository.updateShip(ship.getID(), ship);
            
            // Neues Event auslösen
            orderAccepted.fire(new OrderAccepted(ship));
        });
    }





    @PostConstruct
    public void mockData(){
        createShip("MS Kevin");
        createShip("MS KevinII");
        createShip("MS KevinIII");
        createShip("MS KevinIV");
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
