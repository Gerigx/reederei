package de.hsos.swa.Flotten.Entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import de.hsos.swa.Flotten.Boundary.DTO.ShipDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "Ships")
@Schema(name = "Ship", description = "Entity representing a ship in the fleet")
public class Ship {
    @Id
    @GeneratedValue
    @Schema(description = "Unique identifier of the ship", readOnly = true)
    private long ID;
    
    @NotBlank(message = "Ship name cannot be blank")
    @Column(nullable = false)
    @Schema(description = "Name of the ship", example = "HMS Victory", required = true)
    private String name;
    
    @NotNull(message = "Ship state cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Current state of the ship", required = true, enumeration = {"FREE", "BOOKED"})
    private ShipState state;
    
    public Ship() {
    }

    public Ship(ShipDTO shipDTO){
        this.name = shipDTO.name;
        this.state = shipDTO.state;
    }

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
