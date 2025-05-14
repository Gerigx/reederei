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
public class ShipRessourceTest {


    @InjectMock
    ShipController shipController;

    @Test
    public void testGetShipById() {
        // Vorbereitung der Testdaten
        Ship ship = new Ship();
        ship.setID(1L); // Beachte: ID ist großgeschrieben in deiner Implementierung
        ship.setName("Titanic");
        ship.setState(ShipState.FREE);

        // Mock konfigurieren
        Mockito.when(shipController.getShip(1L)).thenReturn(ship);

        // Test ausführen
        given()
            .when().get("/ship/1")
            .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body("ID", is(1)) // Beachte: ID ist großgeschrieben in deiner Implementierung
                .body("name", is("Titanic"))
                .body("state", is("FREE"));
    }

    @Test
    public void testGetShipByIdNotFound() {
        // Mock konfigurieren
        Mockito.when(shipController.getShip(999L)).thenReturn(null);

        // Test ausführen
        given()
            .when().get("/ship/999")
            .then()
                .statusCode(404);
    }

    @Test
    public void testUpdateShip() {
        // Vorbereitung der Testdaten
        Ship existingShip = new Ship();
        existingShip.setID(1L);
        existingShip.setName("Titanic");
        existingShip.setState(ShipState.FREE);
        
        Ship updatedShip = new Ship();
        updatedShip.setID(1L);
        updatedShip.setName("Titanic Updated");
        updatedShip.setState(ShipState.BOOKED);

        // Mock konfigurieren
        Mockito.when(shipController.getShip(1L)).thenReturn(existingShip);
        Mockito.when(shipController.updateShip(Mockito.eq(1L), Mockito.any(Ship.class))).thenReturn(updatedShip);

        // Test DTO
        String requestBody = "{\"name\":\"Titanic Updated\", \"state\":\"BOOKED\"}";

        // Test ausführen
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(requestBody)
            .when().patch("/ship/1")
            .then()
                .statusCode(200)
                .body("ID", is(1)) // Beachte: ID ist großgeschrieben in deiner Implementierung
                .body("name", is("Titanic Updated"))
                .body("state", is("BOOKED"));
    }

    @Test
    public void testDeleteShip() {
        // Vorbereitung der Testdaten
        Ship ship = new Ship();
        ship.setID(1L);
        ship.setName("Titanic");
        ship.setState(ShipState.FREE);

        // Mock konfigurieren
        Mockito.when(shipController.getShip(1L)).thenReturn(ship);
        Mockito.doNothing().when(shipController).deleteShip(1L);

        // Test ausführen
        given()
            .when().delete("/ship/1")
            .then()
                .statusCode(200); // Annahme: Du verwendest 204 für erfolgreiche Löschungen
    }

    @Test
    public void testDeleteShipNotFound() {
        // Mock konfigurieren
        Mockito.when(shipController.getShip(999L)).thenReturn(null);

        // Test ausführen
        given()
            .when().delete("/ship/999")
            .then()
                .statusCode(404);
    }

}
