package de.hsos.swa.Auftraege.Boundary.Rest;

import org.jboss.resteasy.reactive.RestResponse;


import de.hsos.swa.Auftraege.Boundary.DTO.OrderDTO;
import de.hsos.swa.Auftraege.Boundary.DTO.OrderUpdateDTO;
import de.hsos.swa.Auftraege.Controller.OrderManager;
import de.hsos.swa.Auftraege.Entity.Order;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RequestScoped
@Path("orders/{id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {
    @Inject
    OrderManager orderManager;

    @GET
    public RestResponse<OrderDTO> getOrderById(@PathParam("id") long Id){
        Order order = orderManager.getOrder(Id);
        if (order == null){
            throw new NotFoundException();
        }
        OrderDTO dto = OrderDTO.from(order);
        return RestResponse.ok(dto);
    }
    
    @PUT
    public RestResponse<OrderDTO> updateOrder(@PathParam("id") long Id, OrderUpdateDTO updatedOrder){
        if(Id != updatedOrder.Id){
            throw new BadRequestException();
        }
        Order order = orderManager.updateOrder(updatedOrder);
        if (order == null) {
            throw new NotFoundException();
        }
        OrderDTO dto = OrderDTO.from(order);
        return RestResponse.ok(dto);
    }

    @DELETE
    public RestResponse<Void> deleteOrder(@PathParam("id") long Id){

        if (orderManager.deleteOrder(Id)){
            return RestResponse.noContent();
        }
        throw new NotFoundException();
    } 
}
