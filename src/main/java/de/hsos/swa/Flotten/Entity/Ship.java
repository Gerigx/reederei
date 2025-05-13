package de.hsos.swa.Flotten.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ships")
public class Ship {
    @Id
    @GeneratedValue
    private long ID;
    private String name;
    private ShipState state;

    public long getID() {
        return ID;
    }
    public void setID(long iD) {
        ID = iD;
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
