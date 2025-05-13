package de.hsos.swa.Flotten.Entity;

import java.util.List;
import java.util.Optional;

public interface ShipCatalog {

    public Ship createShip(String name); // Parameter hinzufügen
    public Ship getShip(long id);
    public List<Ship> getAllShips();
    public List<Ship> getShipsByState(ShipState zustand); // Separate Methode für Statusfilterung
    public Ship updateShip(long id, Ship ship); // Parameter für neue Daten hinzufügen
    public boolean deleteShip(long id);
    
    // Methode für die Stream API Anforderung
    public Optional<Ship> findFirstFreeShip();

}
