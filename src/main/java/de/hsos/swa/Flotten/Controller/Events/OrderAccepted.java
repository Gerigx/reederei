package de.hsos.swa.Flotten.Controller.Events;

import de.hsos.swa.Flotten.Entity.Ship;

//todo ship or shipDTO
public class OrderAccepted {
    private final Ship ship;

    public OrderAccepted(Ship ship) {
        this.ship = ship;

    }

    public Ship getShip() {
        return ship;
    }

    
}
