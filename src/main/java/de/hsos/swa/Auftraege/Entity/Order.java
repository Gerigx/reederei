package de.hsos.swa.Auftraege.Entity;

import java.net.URI;
import java.sql.Date;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.enterprise.inject.Vetoed;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Vetoed
@Entity
@Table(name = "Orders")
@Schema(name = "Orders", description = "Entity representing an Order")
public class Order {

    @Id
    @GeneratedValue
    private long Id;

    @CreationTimestamp
    private Date date;

    private String description;

    private URI ship;

    public long getId() {
        return Id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setShip(URI ship) {
        this.ship = ship;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public URI getShip() {
        return ship;
    }

    
}