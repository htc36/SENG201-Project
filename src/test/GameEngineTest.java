package test;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import consumable.*;

import static org.junit.jupiter.api.Assertions.*;

import game.GameEngine;

public class GameEngineTest {
    GameEngine engine;
    
    @BeforeEach
    void setup() {
        engine = new GameEngine();
        engine.setupSpaceship("X-WING");
        engine.setGameLength(10);
        engine.setShipPieces();
        String[] crewOne = new String[] {"dora", "medic"};
        //String[] crewTwo = new String[] {"ralph", "builder"};
        //String[] crewThree = new String[] {"stewart", "actioneer"};
        //String[] crewFour = new String[] {"bob", "medic"};
        
        ArrayList<String[]> crewMembers = new ArrayList<>();
        crewMembers.add(crewOne);
//        crewMembers.add(crewTwo);
//        crewMembers.add(crewThree);
//        crewMembers.add(crewFour);
        
        engine.setCrewMembers(crewMembers);
        engine.setupCrew();
        Consumable vaccine = new Vaccine();
        engine.addCrewConsumable(vaccine);
        engine.setupPlanets();
    }
    
    @Test
    void totalShipPieceTest() {
        assertEquals(6, engine.getShipPieces());
    }
    
    @Test
    void crewMemberTest() {
        // should only have 1 crew member
        assertEquals(1, engine.getCrewMemberStatus().size());
    }
    
    @Test
    void refreshActionsTest() {
        engine.selectCrewMember(0);
        engine.selectedCrewSleep();
        engine.selectedCrewSleep();
        assertEquals(false, engine.selectedCrewHasAction());
        engine.endDay();
        assertEquals(true, engine.selectedCrewHasAction());
    }

    @Test
    void randomEventTest() {
        int randomEvent = engine.getRandomEvent();
        switch(randomEvent) {
        case 0:
            String crewOneHealth = engine.getCrewMemberStatus().get(0).get(1);
            assertEquals("95", crewOneHealth);
            ArrayList<String> sickCrews = engine.getSickCrew();
            assertEquals("DORA", sickCrews.get(0));
            break;
        case 1:
            assertEquals("Vaccine", engine.getCrewLostItem());
            break;
        case 2:
            break;
        }
    }
    
    @Test
    void foundAllPiecesTest() {
        // game should end when all pieces are found
        engine.setFoundShipPieces(6);
        assertEquals(true, engine.hasGameEnded());
        
        // game should end when no more days left
        engine.setFoundShipPieces(0);
        engine.setCurrDay(10);
        assertEquals(false, engine.hasGameEnded());
        engine.endDay();
        assertEquals(true, engine.hasGameEnded());
        engine.setCurrDay(1);
        
        // game should also end when all crew members are dead
        engine.selectCrewMember(0);
        engine.selectedCrewKill();
        assertEquals(true, engine.hasGameEnded());
    }
    
    @Test
    void crewRepairSpaceshipTest() {
        // reduce the health of the spaceship by 15
        engine.asteroidCausingDamage();
        assertEquals(85, engine.getSpaceshipHealth());
        
        engine.selectCrewMember(0);
        engine.selectedCrewRepairShield();
        
        assertEquals(100, engine.getSpaceshipHealth());
        
        // reduce the health of spaceship by 
        engine.asteroidCausingDamage();
        engine.asteroidCausingDamage();
        engine.asteroidCausingDamage();
        engine.asteroidCausingDamage();
        engine.asteroidCausingDamage();
        assertEquals(false, engine.isSpaceshipAbleToFly());
        assertEquals(10, engine.getSpaceshipHealth());

        engine.selectCrewMember(0);
        engine.selectedCrewRepairShield();
        assertEquals(40, engine.getSpaceshipHealth());
    }

    @Test
    void crewSleepTest() {
        // reduce the health of the spaceship by 15
        engine.updateCrewMemberStatus();
        engine.updateCrewMemberStatus();
        engine.updateCrewMemberStatus();
        engine.selectCrewMember(0);
        engine.selectedCrewSleep();
        assertEquals("45", engine.getCrewMemberStatus().get(0).get(5));
    }
    
    @Test
    void crewPilotTest() {
        String[] crewOne = new String[] {"dora", "medic"};
        String[] crewTwo = new String[] {"ralph", "builder"};
        
        ArrayList<String[]> crewMembers = new ArrayList<>();
        crewMembers.add(crewOne);
        crewMembers.add(crewTwo);
        
        engine.setCrewMembers(crewMembers);
        engine.setupCrew();
        engine.setupPlanets();
        
        engine.selectCrewMember(0);
        engine.setCopilot(1);
        String currPlanetName = engine.getPlanetName();
        engine.selectedCrewPilotSpaceship();
        assertEquals(false, currPlanetName.equals(engine.getPlanetName()));
    }

}
