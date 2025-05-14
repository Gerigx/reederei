package de.hsos.swa.Auftraege.Controller.Events;

public class NewOrderEvent {
    private final long orderID;

    public NewOrderEvent(long orderID) {
        this.orderID = orderID;
    }

    public long getOrderID() {
        return orderID;
    }

    

}
