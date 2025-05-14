package de.hsos.swa.Flotten.Controller.Events;

public class NewOrderEvent {
    private final String orderId;
    
    public NewOrderEvent(String orderId) {
        this.orderId = orderId;
    }
    
    public String getOrderId() {
        return orderId;
    }

}
