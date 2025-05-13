package de.hsos.swa.Flotten.Boundary.Ressources;

import javax.print.attribute.standard.Media;

import org.jboss.resteasy.reactive.RestResponse;

import de.hsos.swa.Flotten.Controller.ShipController;

import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("ship/{ID}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class ShipRessource {

    @Inject
    ShipController shipController;

    // get by ID
    public RestResponse<Void> getByID(){
        return null;
    }

    // post

    @POST
    public RestResponse<Void> createNewShip(){
        

        
        return null;
    }

}
