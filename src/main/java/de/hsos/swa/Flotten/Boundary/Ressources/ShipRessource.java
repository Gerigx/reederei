package de.hsos.swa.Flotten.Boundary.Ressources;

import javax.print.attribute.standard.Media;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.RestResponse;

import de.hsos.swa.Flotten.Boundary.DTO.ShipDTO;
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
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PATCH;
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

    @PATCH
    @APIResponse(responseCode = "200", description = "Schiff erfolgreich aktualisiert")
    @APIResponse(responseCode = "404", description = "Schiff nicht gefunden")
    @RestLink(rel = "update")
    public RestResponse<ShipResponse> updateShip(
        @Parameter(description = "ID des zu aktualisierenden Schiffs") 
        @PathParam("ID") long id,
        @Parameter(description = "Zu aktualisierende Schiffsdaten") 
        ShipDTO shipDTO
    ) {
        Ship existingShip = shipController.getShip(id);
        
        if (existingShip == null) {
            throw new NotFoundException();
        }
        
        if (shipDTO.name != null) {
            existingShip.setName(shipDTO.name);
        }
        
        if (shipDTO.state != null) {
            existingShip.setState(shipDTO.state);
        }
        
        Ship updatedShip = shipController.updateShip(id, existingShip);
        
        return RestResponse.ok(ShipResponse.from(updatedShip));
    }

    @DELETE
    @APIResponse(responseCode = "204", description = "Schiff erfolgreich gelöscht")
    @APIResponse(responseCode = "404", description = "Schiff nicht gefunden")
    public RestResponse<ShipResponse> deleteShip(
        @Parameter(description = "ID des zu löschenden Schiffs") 
        @PathParam("ID") long id
    ){
        Ship existingShip = shipController.getShip(id);

        if (existingShip == null) {
            throw new NotFoundException();
        }

        shipController.deleteShip(id);

        return RestResponse.ok();
    }



}
