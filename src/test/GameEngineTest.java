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
        // set spaceship name
        engine.setupSpaceship("X-WING");
        // set game length to 10 days
        engine.setGameLength(10);
        engine.setShipPieces();

        String[] crewOne = new String[] {"dora", "medic"};
        
        ArrayList<String[]> crewMembers = new ArrayList<>();
        crewMembers.add(crewOne);
        
        // add crew members
        engine.setCrewMembers(crewMembers);
        engine.setupCrew();
        
        // add 1 vaccine to crew's inventory
        Consumable vaccine = new Vaccine();
        engine.addCrewConsumable(vaccine);
        
        // setup the planets
        engine.setupPlanets();
    }
    
    @Test
    void totalShipPieceTest() {
        // floor(2/3 * 10) should be 6
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
        // should not have actions left after doing 2 actions
        assertEquals(false, engine.selectedCrewHasAction());
        engine.endDay();
        // actions should be refreshed after ending the day
        assertEquals(true, engine.selectedCrewHasAction());
    }

    @Test
    void randomEventTest() {
        int randomEvent = engine.getRandomEvent();
        switch(randomEvent) {
        case 0:
            // test for space plague
            String crewOneHealth = engine.getCrewMemberStatus().get(0).get(1);
            assertEquals("95", crewOneHealth);
            ArrayList<String> sickCrews = engine.getSickCrew();
            assertEquals("DORA", sickCrews.get(0));
            break;
        case 1:
            // test for alien pirates
            assertEquals("Vaccine", engine.getCrewLostItem());
            break;
        case 2:
            // nothing happens
            break;
        }
    }
    
    @Test
    void gameEndsTest() {
        // game should end when all pieces are found
        engine.setFoundShipPieces(6);
        assertEquals(true, engine.hasGameEnded());
        
        // game should end when no more days left
        engine.setFoundShipPieces(0);
        
        // game should not end on the last day
        engine.setCurrDay(10);
        assertEquals(false, engine.hasGameEnded());
        // but should end on the day after
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
    void finalScoreTest() {
        int finalScore = engine.getFinalScore();
        assertEquals(90000, finalScore);
    }
    
    @Test
    void crewPilotTest() {
        // add 2 crew members so we can pilot the spaceship
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
        // checks if the current planet is not the same as the previous one
        assertEquals(false, currPlanetName.equals(engine.getPlanetName()));
    }
    
    @Test
    void crewShoppingTest() {
        // add enough money to do some shopping spree
        engine.crewAddMoney();
        engine.crewAddMoney();
        engine.crewAddMoney();
        engine.crewAddMoney();

        engine.addItemToShoppingBag("1xBrownie");
        engine.addItemToShoppingBag("1xHotbot");
        engine.purchaseItems(engine.getShoppingBag());
        // make sure all items are added to crew's inventory
        assertEquals(3, engine.getCrewConsumablesCount());
        
        // make sure they are sorted by item name
        assertEquals("Brownie", engine.getCrewConsumables().get(0).get(0));
        assertEquals("Hotbot", engine.getCrewConsumables().get(1).get(0));
        assertEquals("Vaccine", engine.getCrewConsumables().get(2).get(0));
    }
    
    @Test
    void curePlagueTest() {
        int randomEvent = engine.getRandomEvent();
        switch(randomEvent) {
        case 0:
            // test curing plague with vaccine
            System.out.println("Testing Cure Plague");
            String crewOneHealth = engine.getCrewMemberStatus().get(0).get(1);
            assertEquals("95", crewOneHealth);
            String crewOneSick = engine.getCrewMemberStatus().get(0).get(3);
            assertEquals("T", crewOneSick);
            engine.selectCrewMember(0);
            engine.selectedCrewUseItem(0);
            crewOneSick = engine.getCrewMemberStatus().get(0).get(3);
            assertEquals("F", crewOneSick);
        case 1:
            break;
        case 2:
            break;
        }
    }
    
    @Test
    void crewSearchPlanetTest() {
        // crew should start on planet with a ship piece
        assertEquals(true, engine.planetHasShipPieces());
        engine.selectCrewMember(0);
        
        boolean foundPieces = engine.selectedCrewSearchPlanet();

        if (foundPieces) {
            System.out.println("Found ship piece");
            engine.incrementFoundShipPieces();
            engine.planetExtractShipPieces();
            // the ship piece should now be gone
            assertEquals(false, engine.planetHasShipPieces());
            // and increments the number of ship piece by one
            assertEquals(1, engine.getFoundShipPieces());
        } else {
            if (engine.unlucky(20)) { // 20% of finding nothing
                System.out.println("Found nothing");
                //
            } else if (engine.unlucky(50)) { // or if found something, 50% chance it's item
                assertEquals(1, engine.getCrewConsumables().size());
                System.out.println("Found random item");
                String randomItem = engine.crewGetRandomItem();
                if (randomItem.equals("Vaccine"))
                    assertEquals(1, engine.getCrewConsumables().size());
                else
                    assertEquals(2, engine.getCrewConsumables().size());
            } else { // 50% chance it's money
                System.out.println("Found money");
                engine.crewAddMoney();
                assertEquals(145, engine.getCrewMoney());
            }
        }
    }

    @Test
    void shoppingBagPriceTest() {
        engine.addItemToShoppingBag("7xBrownie");
        // should not have enough money to buy these
        assertEquals(true, engine.isShoppingBagTooExpensive());
        engine.removeItemFromShoppingBag("Brownie");
        // should not have enough money to buy these
        assertEquals(false, engine.isShoppingBagTooExpensive());
    }
    
    @Test
    void outpostItemNameTest() {
        // outpost should not have these items
        assertEquals(false, engine.hasOutpostStock("$3Rice"));
        assertEquals(false, engine.hasOutpostStock("VAccine"));
        
        // but should have these
        assertEquals(true, engine.hasOutpostStock("Vaccine"));
        assertEquals(true, engine.hasOutpostStock("Brownie"));
    }
    
}
