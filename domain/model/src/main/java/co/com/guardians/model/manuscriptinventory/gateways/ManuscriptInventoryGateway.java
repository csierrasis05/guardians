package co.com.guardians.model.manuscriptinventory.gateways;

import co.com.guardians.model.manuscriptinventory.ManuscriptInventory;

import java.util.List;

public interface ManuscriptInventoryGateway {
    ManuscriptInventory save(ManuscriptInventory manuscriptInventory);
    List<ManuscriptInventory> getAllManuscripts();

}
