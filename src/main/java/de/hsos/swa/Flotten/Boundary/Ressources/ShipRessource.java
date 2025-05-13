package de.hsos.swa.Flotten.Boundary.Ressources;

import javax.print.attribute.standard.Media;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.resteasy.reactive.RestResponse;

import de.hsos.swa.Flotten.Boundary.Response.ShipResponse;
import de.hsos.swa.Flotten.Controller.ShipController;
import de.hsos.swa.Flotten.Entity.Ship;
import io.quarkus.resteasy.reactive.links.InjectRestLinks;
import io.quarkus.resteasy.reactive.links.RestLink;
import io.quarkus.resteasy.reactive.links.RestLinkType;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

// für links
// https://quarkus.io/extensions/io.quarkus/quarkus-rest-links/ 

@Path("ship/{ID}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class ShipRessource {

    @Inject
    ShipController shipController;

    // get by ID
    @GET
    @RestLink(rel = "self")
    @InjectRestLinks(RestLinkType.INSTANCE)
    public RestResponse<ShipResponse> getByID(
        @Parameter(description = "ID eines Schiffes")
        @PathParam("ID") long ID
    ){
        Ship ship = shipController.getShip(ID);
        if (ship == null) {
            throw new NotFoundException();
        }



        return RestResponse.ok(ShipResponse.from(ship));
    }

    // post

    @POST
    public RestResponse<Void> createNewShip(){
        

        
        return null;
    }

}
