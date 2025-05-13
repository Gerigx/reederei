package de.hsos.swa.Flotten.Boundary.Ressources;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.common.util.RestMediaType;

import de.hsos.swa.Flotten.Boundary.Response.ShipResponse;
import de.hsos.swa.Flotten.Controller.ShipController;
import de.hsos.swa.Flotten.Entity.Ship;
import de.hsos.swa.Flotten.Entity.ShipState;
import io.quarkus.resteasy.reactive.links.InjectRestLinks;
import io.quarkus.resteasy.reactive.links.RestLink;
import io.quarkus.resteasy.reactive.links.RestLinkType;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;



@Path("/ships")
@Produces({MediaType.APPLICATION_JSON, RestMediaType.APPLICATION_HAL_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class ShipsRessource {


    
    @Inject
    ShipController shipController;

    @GET
    @RestLink(rel = "list")
    @InjectRestLinks(RestLinkType.TYPE)
    public RestResponse<List<ShipResponse>> getAll(
        @Parameter(description = "Filtern nach Team-Kategorie")
        @QueryParam("state") ShipState shipState
    ){


        List<Ship> allShips = shipController.getAllShips();

        if (shipState != null){
            allShips = allShips.stream()
                .filter(p -> p.getState() == shipState)
                .collect(Collectors.toList());
        }

        Stream<ShipResponse> shipsStream = allShips.stream()
            .map(p -> ShipResponse.from(p));


        return RestResponse.ok(shipsStream.toList());
    }

    @POST
    public RestResponse<ShipResponse> createShip(){

        return RestResponse.ok();
    }

}
