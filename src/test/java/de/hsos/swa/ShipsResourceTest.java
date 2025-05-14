package de.hsos.swa;

import org.junit.jupiter.api.Test;

import de.hsos.swa.Flotten.Controller.ShipController;
import de.hsos.swa.Flotten.Entity.Ship;
import de.hsos.swa.Flotten.Entity.ShipState;
import io.quarkus.test.InjectMock;


import io.quarkus.test.junit.QuarkusTest;
//import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.hsos.swa.Flotten.Controller.ShipController;
import de.hsos.swa.Flotten.Entity.Ship;
import de.hsos.swa.Flotten.Entity.ShipState;
import de.hsos.swa.Flotten.Boundary.Response.ShipResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;

import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class ShipsResourceTest {

    @InjectMock
    ShipController shipController;

    @Test
    public void testGetAllShips() {
        // Mocks erstellen mit expliziten Werten
        Ship ship1 = Mockito.mock(Ship.class);
        Mockito.when(ship1.getID()).thenReturn(1L);
        Mockito.when(ship1.getName()).thenReturn("Titanic");
        Mockito.when(ship1.getState()).thenReturn(ShipState.FREE);

        Ship ship2 = Mockito.mock(Ship.class);
        Mockito.when(ship2.getID()).thenReturn(2L);
        Mockito.when(ship2.getName()).thenReturn("Queen Mary");
        Mockito.when(ship2.getState()).thenReturn(ShipState.BOOKED);

        // Controller-Verhalten mocken
        Mockito.when(shipController.getAllShips()).thenReturn(Arrays.asList(ship1, ship2));

        // Debug-Ausgabe hinzufügen
        given()
            .when().get("/ships")
            .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON)
               
                .body("size()", is(2))
                .body("[0].ID", is(1))
                .body("[0].name", is("Titanic"))
                .body("[0].state", is("FREE"))
                .body("[1].ID", is(2))
                .body("[1].name", is("Queen Mary"))
                .body("[1].state", is("BOOKED"));
        
        // Verifizieren, dass Methode aufgerufen wurde
        Mockito.verify(shipController).getAllShips();
    }

    @Test
    public void testGetAllShipsFiltered() {
        // Mocks erstellen
        Ship ship1 = Mockito.mock(Ship.class);
        Mockito.when(ship1.getID()).thenReturn(1L);
        Mockito.when(ship1.getName()).thenReturn("Titanic");
        Mockito.when(ship1.getState()).thenReturn(ShipState.FREE);

        Ship ship2 = Mockito.mock(Ship.class);
        Mockito.when(ship2.getID()).thenReturn(2L);
        Mockito.when(ship2.getName()).thenReturn("Queen Mary");
        Mockito.when(ship2.getState()).thenReturn(ShipState.BOOKED);

        // Controller-Verhalten mocken
        Mockito.when(shipController.getAllShips()).thenReturn(Arrays.asList(ship1, ship2));

        // Test ausführen - sollte nach Filterung nur 1 Schiff zurückgeben
        given()
            .queryParam("state", "FREE")
            .when().get("/ships")
            .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body("size()", is(1))
                .body("[0].ID", is(1))
                .body("[0].name", is("Titanic"))
                .body("[0].state", is("FREE"));
        
        // Verifizieren, dass Methode aufgerufen wurde
        Mockito.verify(shipController).getAllShips();
    }

    @Test
    public void testCreateShip() {
        // Vorbereitung der Test-DTO
        String requestBody = "{\"name\":\"Black Pearl\"}";

        // Mock konfigurieren
        Mockito.doNothing().when(shipController).createShip(Mockito.anyString());

        // Test ausführen
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(requestBody)
            .when().post("/ships")
            .then()
                .statusCode(200);

        // Überprüfen, ob die Controller-Methode aufgerufen wurde
        Mockito.verify(shipController).createShip("Black Pearl");
    }
}
