package de.hsos.swa.Flotten.Boundary.DTO;

import de.hsos.swa.Flotten.Entity.ShipState;

public class ShipDTO {
    public String name;
    public ShipState state;

    public ShipDTO() {
    }

    public ShipDTO(String name, ShipState state) {
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShipState getState() {
        return state;
    }

    public void setState(ShipState state) {
        this.state = state;
    }    

}
