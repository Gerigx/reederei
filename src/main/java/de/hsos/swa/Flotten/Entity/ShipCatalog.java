package de.hsos.swa.Flotten.Entity;

import java.util.List;
import java.util.Optional;

public interface ShipCatalog {

    public Ship createShip(String name); 
    public Ship getShip(long id);
    public List<Ship> getAllShips();
    public Ship updateShip(long id, Ship ship); 
    public boolean deleteShip(long id);
    
    public Optional<Ship> findFirstFreeShip();

}
