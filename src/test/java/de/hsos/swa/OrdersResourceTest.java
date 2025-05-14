package de.hsos.swa;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.InjectMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.hsos.swa.Auftraege.Controller.OrderManager;
import de.hsos.swa.Auftraege.Entity.Order;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.net.URI;
import java.sql.Date;
import java.util.Arrays;

import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class OrdersResourceTest {

    @InjectMock
    OrderManager orderManager;

    @Test
    public void testGetAllOrders() {
        // Mock Orders erstellen
        Order order1 = Mockito.mock(Order.class);
        Mockito.when(order1.getId()).thenReturn(1L);
        Mockito.when(order1.getDescription()).thenReturn("Erste Bestellung");
        Mockito.when(order1.getDate()).thenReturn(new Date(System.currentTimeMillis()));

        Order order2 = Mockito.mock(Order.class);
        Mockito.when(order2.getId()).thenReturn(2L);
        Mockito.when(order2.getDescription()).thenReturn("Zweite Bestellung");
        Mockito.when(order2.getDate()).thenReturn(new Date(System.currentTimeMillis()));

        // OrderManager-Verhalten konfigurieren
        Mockito.when(orderManager.getAllOrders()).thenReturn(Arrays.asList(order1, order2));

        // Test ausführen
        given()
            .when().get("/orders")
            .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body("size()", is(2))
                .body("[0].Id", is(1))
                .body("[0].description", is("Erste Bestellung"))
                .body("[1].Id", is(2))
                .body("[1].description", is("Zweite Bestellung"));
    }

    @Test
    public void testCreateOrder() {
        // OrderDTO als JSON
        String requestBody = "{\"description\":\"Neue Bestellung\"}";

        // OrderManager-Verhalten konfigurieren
        // Hier verwenden wir doNothing, da die createOrder-Methode void zurückgibt
        Mockito.doNothing().when(orderManager).createOrder(Mockito.any(Order.class));

        // Test ausführen
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(requestBody)
            .when().post("/orders")
            .then()
                .statusCode(200);

        // Verifizieren, dass OrderManager.createOrder aufgerufen wurde
        Mockito.verify(orderManager).createOrder(Mockito.any(Order.class));
    }
}