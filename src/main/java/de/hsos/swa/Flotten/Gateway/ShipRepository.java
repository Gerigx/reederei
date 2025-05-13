package de.hsos.swa.Flotten.Gateway;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import de.hsos.swa.Flotten.Entity.Ship;
import de.hsos.swa.Flotten.Entity.ShipCatalog;
import de.hsos.swa.Flotten.Entity.ShipState;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

//FRAGE: sind Filter oder Query-Methoden das goto?
// pro filter: weniger Code, wesentlich weniger
// pro Method: sicheres ergebnis für den anwender
@ApplicationScoped
public class ShipRepository implements PanacheRepository<Ship>, ShipCatalog  {

    private static final AtomicLong SHIP_ID_COUNTER = new AtomicLong(1);

    @Override
    public Ship createShip(String name) {
        Ship ship = new Ship();

        ship.setName(name);

        if (this.listAll().size() == 2) {
            ship.setState(ShipState.BOOKED);
        } else {
            ship.setState(ShipState.FREE);
        }       

        this.persist(ship);

        return ship;
    }

    @Override
    public Ship getShip(long id) {
        return findById(id);
    }

    @Override
    public List<Ship> getAllShips() {
        System.out.println(listAll().size());
        return listAll();
    }


    @Override
    public Ship updateShip(long id, Ship ship) {
        Ship existingShip = this.getShip(id);

        if (existingShip != null){
            return null;
        }

        existingShip.setName(ship.getName());
        existingShip.setState(ship.getState());
        
        return existingShip;
    }

    @Override
    public boolean deleteShip(long id) {
        boolean deleted = this.delete("id", id) > 0;
        return deleted;
    }

    @Override
    public Optional<Ship> findFirstFreeShip() {
        List<Ship> allShips = this.getAllShips();
        if (allShips.size() > 0) {
            return allShips.stream().findFirst();
        }
        return null;
    }

 

}
