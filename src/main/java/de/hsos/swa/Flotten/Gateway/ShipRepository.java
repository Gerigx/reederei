package de.hsos.swa.Flotten.Gateway;

import java.util.List;
import java.util.Optional;

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

    // atomi long für ID




    @Override
    public Ship createShip(String name) {
        Ship ship = new Ship();

        ship.setName(name);
        ship.setState(ShipState.FREE);

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
    public List<Ship> getShipsByState(ShipState state) {
        if (state == ShipState.BOOKED){
            return findBooked();
        }
        return findFree();        
    }

    @Override
    public Ship updateShip(long id, Ship ship) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateShip'");
    }

    @Override
    public boolean deleteShip(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteShip'");
    }

    @Override
    public Optional<Ship> findFirstFreeShip() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findFirstFreeShip'");
    }

    // joa

    public Ship findByName(String name){
        return find("name", name).firstResult();
    }
 
    public List<Ship> findFree(){
        return list("status", ShipState.FREE);
    }

    public List<Ship> findBooked(){
        return list("status", ShipState.BOOKED);
    }
 

}
