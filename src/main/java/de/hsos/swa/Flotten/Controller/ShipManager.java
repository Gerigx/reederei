package de.hsos.swa.Flotten.Controller;

import java.util.List;

import de.hsos.swa.Flotten.Entity.Ship;
import de.hsos.swa.Flotten.Gateway.ShipRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

// muss das applschoped sein?
@ApplicationScoped
public class ShipManager implements ShipController {

    @Inject
    ShipRepository shipRepository;

    @Override
    public void createShip() {

        
    }

    @Override
    public Ship getShip(long ID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getShip'");
    }

    @Override
    public List<Ship> getAllShips() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllShips'");
    }

    @Override
    public void updateShip(long ID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateShip'");
    }

    @Override
    public void deleteShip(long ID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteShip'");
    }

}
