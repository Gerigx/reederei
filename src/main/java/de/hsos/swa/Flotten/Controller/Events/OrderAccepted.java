package de.hsos.swa.Flotten.Controller.Events;

import de.hsos.swa.Flotten.Entity.Ship;

//todo ship or shipDTO
public class OrderAccepted {
    private final Ship ship;
    private final long orderID; 

    public OrderAccepted(Ship ship, long orderID) {
        this.ship = ship;
        this.orderID = orderID;
    }

    public Ship getShip() {
        return ship;
    }
    
    public long getOrderID() {
        return orderID;
    }
}
