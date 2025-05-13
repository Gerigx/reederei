package de.hsos.swa.Auftraege.Boundary.DTO;

import java.net.URI;
import java.sql.Date;

import de.hsos.swa.Auftraege.Entity.Order;

public class OrderDTO {
   
    public long Id;

    public Date date;

    public String description;

    public URI ship;

    public static OrderDTO from(Order order){
        OrderDTO dto = new OrderDTO();
        dto.Id = order.getId();
        dto.date = order.getDate();
        dto.description = order.getDescription();
        dto.ship = order.getShip();
        return dto;
    }
}
