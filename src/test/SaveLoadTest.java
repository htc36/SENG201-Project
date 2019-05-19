package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import consumable.Consumable;
import consumable.Vaccine;
import game.GameEngine;

class SaveLoadTest {

    @Test
    void loadSavedGameTest() {
        GameEngine engine = new GameEngine();
        engine.setupSpaceship("X-WING");
        // set game length to 10 days
        engine.setGameLength(6);
        engine.setShipPieces();

        String[] crewOne = new String[] {"dora", "medic"};
        
        ArrayList<String[]> crewMembers = new ArrayList<>();
        crewMembers.add(crewOne);
        
        // add crew members
        engine.setCrewMembers(crewMembers);
        engine.setupCrew();
        engine.endDay();
        
        // add 1 vaccine to crew's inventory
        Consumable vaccine = new Vaccine();
        engine.addCrewConsumable(vaccine);
        
        // setup the planets
        engine.setupPlanets();
        engine.saveGameState();
        
        engine = new GameEngine();
        String homeEnv = System.getenv("HOME");
        try {
            engine = new GameEngine(homeEnv + "/.save.json");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        assertEquals("DORA", engine.getCrewMemberStatus().get(0).get(0));
        assertEquals(2, engine.getCurrDay());
        assertEquals("100", engine.getShipStatus().get(1));
        assertEquals("X-WING", engine.getShipStatus().get(0));
        assertEquals(6, engine.getGameLength());
        assertEquals(0, engine.getFoundShipPieces());
    }

}
