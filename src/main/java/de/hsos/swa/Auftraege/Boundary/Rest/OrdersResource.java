package de.hsos.swa.Auftraege.Boundary.Rest;

import java.util.Collection;
import java.util.stream.Stream;

import org.jboss.resteasy.reactive.RestResponse;

import de.hsos.swa.Auftraege.Boundary.DTO.OrderDTO;
import de.hsos.swa.Auftraege.Controller.OrderManager;
import de.hsos.swa.Auftraege.Entity.Order;
import de.hsos.swa.Flotten.Boundary.Response.ShipResponse;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RequestScoped
@Path("orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrdersResource {

    @Inject
    OrderManager orderManager;

    @GET
    public RestResponse<Collection<OrderDTO>> getAllOrders(){
        Collection<Order> orders = orderManager.getAllOrders();
        Stream<OrderDTO> orderStream = orders.stream()
            .map(p -> OrderDTO.from(p));


        return RestResponse.ok(orderStream.toList());
    }

    @POST
}
