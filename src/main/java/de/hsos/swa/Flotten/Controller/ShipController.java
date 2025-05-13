package de.hsos.swa.Flotten.Controller;

import java.util.List;

import de.hsos.swa.Flotten.Entity.Ship;

public interface ShipController {
    public void createShip(String name);
    public Ship getShip(long ID);
    public List<Ship> getAllShips();
    public void updateShip(long ID);
    public void deleteShip(long ID);

}
