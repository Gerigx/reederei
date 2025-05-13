package de.hsos.swa.Flotten.Gateway;

import java.util.List;

import de.hsos.swa.Flotten.Entity.Ship;
import de.hsos.swa.Flotten.Entity.ShipState;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShipRepository implements PanacheRepository<Ship>  {



    public Ship findByName(String name){
        return find("name", name).firstResult();
    }
 
    public List<Ship> findFree(){
        return list("status", ShipState.FREE);
    }

    public List<Ship> findBooked(){
        return list("status", ShipState.BOOKED);
    }
 
    public void deleteStefs(){
        delete("name", "Stef");
   }

}
