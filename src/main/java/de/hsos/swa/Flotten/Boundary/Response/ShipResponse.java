package de.hsos.swa.Flotten.Boundary.Response;

import java.util.HashMap;
import java.util.Map;

import de.hsos.swa.Flotten.Boundary.Ressources.ShipRessource;
import de.hsos.swa.Flotten.Entity.Ship;
import de.hsos.swa.Flotten.Entity.ShipState;
import io.quarkus.resteasy.reactive.links.RestLinkId;
import jakarta.ws.rs.core.UriInfo;

public class ShipResponse {

    @RestLinkId
    public long ID;
    public String name;
    public ShipState state;

    public static ShipResponse from(Ship ship){
        ShipResponse response = new ShipResponse();
        response.ID = ship.getID();
        response.name = ship.getName();
        response.state = ship.getState();

        return response;
    }

}
