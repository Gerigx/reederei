package de.hsos.swa;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.InjectMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.hsos.swa.Auftraege.Controller.OrderManager;
import de.hsos.swa.Auftraege.Entity.Order;
import de.hsos.swa.Auftraege.Boundary.DTO.OrderUpdateDTO;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.net.URI;
import java.sql.Date;

import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class OrderResourceTest {

    @InjectMock
    OrderManager orderManager;

    @Test
    public void testGetOrderById() {
        // Mock Order erstellen
        Order order = Mockito.mock(Order.class);
        Mockito.when(order.getId()).thenReturn(1L);
        Mockito.when(order.getDescription()).thenReturn("Test Bestellung");
        Mockito.when(order.getDate()).thenReturn(new Date(System.currentTimeMillis()));
        
        // OrderManager-Verhalten konfigurieren
        Mockito.when(orderManager.getOrder(1L)).thenReturn(order);

        // Test ausführen
        given()
            .when().get("/orders/1")
            .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Id", is(1))
                .body("description", is("Test Bestellung"));
    }

    @Test
    public void testGetOrderByIdNotFound() {
        // OrderManager-Verhalten konfigurieren
        Mockito.when(orderManager.getOrder(999L)).thenReturn(null);

        // Test ausführen
        given()
            .when().get("/orders/999")
            .then()
                .statusCode(404);
    }

    @Test
    public void testUpdateOrder() throws Exception {
        // Mock Order erstellen
        Order existingOrder = Mockito.mock(Order.class);
        Mockito.when(existingOrder.getId()).thenReturn(1L);
        Mockito.when(existingOrder.getDescription()).thenReturn("Alte Beschreibung");
        
        Order updatedOrder = Mockito.mock(Order.class);
        Mockito.when(updatedOrder.getId()).thenReturn(1L);
        Mockito.when(updatedOrder.getDescription()).thenReturn("Neue Beschreibung");
        
        // OrderManager-Verhalten konfigurieren
        Mockito.when(orderManager.updateOrder(Mockito.any(Order.class))).thenReturn(updatedOrder);

        // OrderUpdateDTO erstellen
        String requestBody = "{\"Id\":1,\"description\":\"Neue Beschreibung\",\"ship\":null}";
        
        // Test ausführen
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(requestBody)
            .when().put("/orders/1")
            .then()
                .statusCode(200)
                .body("Id", is(1))
                .body("description", is("Neue Beschreibung"));
    }

    @Test
    public void testDeleteOrder() {
        // OrderManager-Verhalten konfigurieren
        Mockito.when(orderManager.deleteOrder(1L)).thenReturn(true);

        // Test ausführen
        given()
            .when().delete("/orders/1")
            .then()
                .statusCode(204);
    }

    @Test
    public void testDeleteOrderNotFound() {
        // OrderManager-Verhalten konfigurieren
        Mockito.when(orderManager.deleteOrder(999L)).thenReturn(false);

        // Test ausführen
        given()
            .when().delete("/orders/999")
            .then()
                .statusCode(404);
    }
}