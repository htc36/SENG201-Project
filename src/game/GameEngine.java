package game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeMap;

import consumable.*;
import crew.Crew;
import outpost.Outpost;
import planet.Planet;
import random_events.*;
import unit.*;

public class GameEngine {

    private int gameLength;
    private int shipPieces;
    private ArrayList<String> crewMemberTypes;
    private ArrayList<CrewMember> crewMembers;
    private Spaceship ship;
    private Crew crew;
    private Outpost outpost;
    private int currDay;
    private ArrayList<Planet> planets;
    private int currentPlanetIndex;
    private int foundShipPieces;
    private CrewMember selectedCrew;
    private CrewMember copilot;

    /**
     * Constructor for GameEngine
     * it sets up the current day
     *      "     the outpost menu
     *      "     the initial planet
     */
    public GameEngine() {
        currDay = 1;

        crewMemberTypes = new ArrayList<>();
        crewMemberTypes.add("medic");
        crewMemberTypes.add("explorer");
        crewMemberTypes.add("hungus");
        crewMemberTypes.add("sleeper");
        crewMemberTypes.add("actioneer");
        crewMemberTypes.add("builder");

        Food f1 = new Brownie();
        Food f2 = new FriedRice();
        Food f3 = new Dumplings();
        Food f4 = new SpaceCake();
        Food f5 = new TikkaMasala();
        Food f6 = new Hotbot();

        MedicalSupply m1 = new PolyJuice();
        MedicalSupply m2 = new PickledPlum();
        MedicalSupply m3 = new Vaccine();

        Consumable[] c = new Consumable[]{f1, f2, f3, f4, f5, f6, m1, m2, m3};
        outpost = new Outpost(c);

        currentPlanetIndex = 0;
        foundShipPieces = 0;

        crewMembers = new ArrayList<>();
    }

    // CREW RELATED FUNCTIONS START

    /**
     * Returns the current money the crew has
     * @return int amount of money the crew has
     */
    public int getCrewMoney() {
        return crew.getMoney();
    }

    /**
     * Returns the list of items the crew has
     * @return ArrayList<ArrayList<String>> List of items owned by the crew
     */
    public ArrayList<ArrayList<String>> getCrewConsumables() {
        TreeMap<Consumable, Integer> crewItems = crew.getConsumables();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (Consumable c : crewItems.keySet()) {
            ArrayList<String> itemStats = c.getConsumableStats();
            itemStats.add(String.valueOf(crewItems.get(c)));
            result.add(itemStats);
        }
        return result;
    }

    /**
     * Returns the number of items the crew has
     * @return int Number of items the crew has
     */
    public int getCrewConsumablesCount() {
        return crew.getConsumables().size();
    }

    /**
     * Returns the possible crew member types
     * @return ArrayList<String> Possible crew member types
     */
    public ArrayList<String> getCrewMemberTypes() {
        return crewMemberTypes;
    }

    /**
     * Returns the random event that happened during the start of the day
     * @return int code for the random event. 1 means AlienPirates, 2 means SpacePlague
     */
    public int getRandomEvent() {
        // We start the day with random events occuring, what fun!
        Random rand = new Random();
        int randomEvent = rand.nextInt(3);
        switch (randomEvent) {
            case 1:
                AlienPirates.causeDamage(crew); 
            case 0:
                SpacePlague.causeDamage(crew);
        }

        // 2 Nothing happens
        // 1 Alien Pirates
        // 0 Space Plague
        return randomEvent;
    }

    /**
     * Adds an item to the crew consumables list
     * @param item the item to be added
     */
    public void addCrewConsumable(Consumable item) {
        crew.addConsumable(item);
    }

    /**
     * Sets up the crew with crew members and a spaceship
     */
    public void setupCrew() {
        crew = new Crew(crewMembers, ship);
    }

    /**
     * Returns if the number of crew members is between 2 to 4
     * @param amount the amount of crew members
     * @return boolean true if valid, false otherwise
     */
    public boolean isCrewNumberValid(int amount) {
        return amount >= 2 && amount <= 4;
    }

    /**
     * Returns if the crew member name is a duplicate of an existing one (not valid)
     * @param crewList list of existing crews
     * @param name the name of the new crew member
     * @return boolean true if not a duplicate, false otherwise
     */
    public boolean isCrewNameValid(ArrayList<String> crewList, String name) {
        // to avoid 2 crew members having the same name
        for (String c : crewList) {
            if (c.equals(name))
                return false;
        }

        return true;
    }

    /**
     * Adds a crew member into the crew members list
     * @param crewType The type of the new member
     * @param memberName The name of the new member
     */
    public void addCrewMember(String crewType, String memberName) {
        switch(crewType) {
            case "medic":
                crewMembers.add(new Medic(memberName));
                break;
            case "explorer":
                crewMembers.add(new Explorer(memberName));
                break;
            case "builder":
                crewMembers.add(new Builder(memberName));
                break;
            case "sleeper":
                crewMembers.add(new Sleeper(memberName));
                break;
            case "actioneer":
                crewMembers.add(new Actioneer(memberName));
                break;
            case "hungus":
                crewMembers.add(new Hungus(memberName));
                break;
        }
    }

    /**
     * Remove a crew member from the crew members list
     * @param c The crew member to be removed
     */
    public void removeCrewMember(String name) throws CrewMemberNotFoundException {
    	CrewMember toBeRemoved = null;
    	for (CrewMember c : crew.getCrewMembers()) {
    		if (c.getName().equals(name) && c.getHealth() == 0) {
    			toBeRemoved = c;
    			break;
    		}
    	}
        crew.removeCrewMember(toBeRemoved);
    }

    /**
     * Sets the crew members name to upper case and type to lower case, then
     * adds them to the crew members list
     * @param crewString crew member string input from the player
     */
    public void setCrewMembers(ArrayList<String> crewString) {
        for (String s : crewString) {
            String memberName = s.split("-")[0].toUpperCase();
            String crewType = s.split("-")[1].toLowerCase();
            addCrewMember(crewType, memberName);
        }
    }

    /**
     * Returns the current status of the crew members
     * @return ArrayList<ArrayList<String>> List of crew member current stats
     */
    public ArrayList<ArrayList<String>> getCrewMemberStatus() {
        return crew.getCrewMemberStatus();
    }

    /**
     * Updates the crew members as time goes on
     */
    public void updateCrewMemberStatus() {
        crew.updateCrewStatus();
    }

    /**
     * Returns the crew members that have 0 health
     * @return ArrayList<String> List of dead crew members
     */
    public ArrayList<String> getDeadCrewMembers() {
        ArrayList<String> names = new ArrayList<>();
        for (CrewMember c : crew.getDeadCrewMembers()) {
            names.add(c.getName());
        }
        return names;
    }

    /**
     * Returns the crew members that have high fatigue and tiredness
     * @return ArrayList<String> List of unhealthy crew members
     */
    public ArrayList<String> getUnhealthyCrewMembers() {
        ArrayList<String> names = new ArrayList<>();
        for (CrewMember c : crew.getUnhealthyCrewMembers()) {
            names.add(c.getName());
        }
        return names;
    }

    /**
     * Use an item on the selected crew
     * @param itemIndex the index of the item
     */
    public void selectedCrewUseItem(int itemIndex) {
        String itemName = getCrewConsumables().get(itemIndex).get(0);
        selectedCrew.useItem(crew.popConsumable(itemName));
    }

    /**
     * Returns the fatigue level on the selected crew
     * @return int fatigue level of selected crew
     */
    public int selectedCrewGetFatique() {
        return selectedCrew.getFatique();
    }

    /**
     * Tells the selected crew to go to sleep zZzzzZzzz
     */
    public void selectedCrewSleep() {
        selectedCrew.sleep(30);
    }

    /**
     * Tells the selected crew to repair the spaceship
     */
    public void selectedCrewRepairShield() {
        selectedCrew.repairShield(ship, 30);
    }

    /**
     * Tells the selected crew to search the planet for items
     * @return boolean true if found a ship piece, false otherwise
     */
    public boolean selectedCrewSearchPlanet() {
        boolean found = selectedCrew.searchPlanet();
    	if (!planets.get(currentPlanetIndex).stillHasShipPieces()) {
    		return false;
    	}
    	
    	return found;
    }

    /**
     * Cancel the current selected crew selection
     */
    public void selectedCrewCancel() {
        selectedCrew = null;
    }

    /**
     * The crew gets a random item from searching the planet
     * @return String name of the obtained item
     */
    public String crewGetRandomItem() {
        Consumable item = outpost.getRandomItem();
        crew.addConsumable(item);
        return item.getName();
    }

    /**
     * Select a crew member to perform actions
     * @param index Index of the crew member
     */
    public void selectCrewMember(int index) throws IndexOutOfBoundsException {
        selectedCrew = crewMembers.get(index);
    }

    /**
     * Checks if the selected crew member still has actions
     * @return boolean true if still has actions, false otherwise
     */
    public boolean selectedCrewHasAction() {
        return selectedCrew.stillHasActions();
    }

    /**
     * Returns the name of the selected crew
     * @return String name of the selected crew
     */
    public String selectedCrewName() {
        return selectedCrew.getName();
    }

    /**
     * Sets the copilot to pilot the spaceship to another planet
     * @param index index of the copilot in the crew list
     */
    public void setCopilot(int index) throws IndexOutOfBoundsException {
        copilot = crewMembers.get(index);
    }

    /**
     * Adds money to the crew, I wish it was that easy in real life
     */
    public void crewAddMoney() {
        crew.addMoney(45);
    }

    /**
     * Checks if the selected copilot is valid, that is
     * if the copilot is not the same as the pilot and 
     * the copilot still has actions
     * @param currIndex pilot index
     * @param copilotIndex copilot index
     * @return boolean true if valid, false otherwise
     */
    public boolean isValidCopilot(int currIndex, int copilotIndex) {
        if (copilotIndex < 0 || copilotIndex >= crewMembers.size()) 
            return false;

        int actions = crewMembers.get(copilotIndex).getActions();
        if (currIndex != copilotIndex) {
            return actions > 0;
        }
        return false;
    }

    /**
     * Selected crew and copilot pilots th ship to another planet
     */
    public void selectedCrewPilotSpaceship() {
        selectedCrew.pilotShip(copilot);
        Random rand = new Random();
        int nextPlanetIndex;
        do {
            nextPlanetIndex = rand.nextInt(planets.size());
        } while (nextPlanetIndex == currentPlanetIndex);
        currentPlanetIndex = nextPlanetIndex;

    }

    // CREW RELATED FUNCTIONS END

    // SHIP RELATED FUNCTIONS START

    /**
     * Sets up the spceship with specified name
     * @param name name of the spaceship
     */
    public void setupSpaceship(String name) {
        String shipName;
        if (name.length() == 0)
            shipName = "andromeda";
        else
            shipName = name;
        ship = new Spaceship(shipName.toUpperCase());
    }

    /**
     * Returns the health of the spaceship
     * @return int health of spaceship
     */
    public int getSpaceshipHealth() {
        return ship.getHealth();
    }

    /**
     * Checks if spaceship is able to fly
     * @return boolean True if able, false otherwise
     */
    public boolean isSpaceshipAbleToFly() {
        return ship.getHealth() > 50;
    }

    // SHIP RELATED FUNCTIONS END

    // OUTPOST RELATED FUNCTIONS START

    /**
     * Checks if outpost has item with the name specified
     * @param itemName Name of the item
     * @return boolean True of in stock, false otherwise
     */
    public boolean hasOutpostStock(String itemName) {
        return outpost.hasItemInStock(itemName);
    }

    /**
     * Clears the crew's shopping bag in outpost
     */
    public void clearShoppingBag() {
        outpost.clearShoppingBag();
    }

    /**
     * Adds an item to the crew's shopping bag
     * @param query name of the item
     */
    public void addItemToShoppingBag(String query) {
        int amount = 0;
        String itemName = "";
        amount = Integer.valueOf(query.split("x")[0]);
        itemName = query.split("x")[1];
        for (int i = 0; i < amount; i ++ ) {
            outpost.addItemToShoppingBag(itemName);
        }
    }

    public TreeMap<String, Integer> getShoppingBag() {
        TreeMap<String, Integer> itemMap = outpost.getShoppingBagStatus();
        return itemMap;
    }

    /**
     * Purchase all the items that are currently in the shopping bag
     * @param shoppingBag the shopping bag
     */
    public void purchaseItems(TreeMap<String, Integer> shoppingBag) {
        TreeMap<String, Consumable> conMap = outpost.getConsumableMap();
        crew.shellOutMoney(outpost.getTotalPrice());
        for (String itemName : shoppingBag.keySet()) {
            for (int i = 0; i < shoppingBag.get(itemName); i++) {
                crew.addConsumable(conMap.get(itemName));
            }
        }

        outpost.clearShoppingBag();
    }

    /**
     * Returns the list of items that are currently in sale on the outpost
     * @return ArrayList<ArrayList<String>> List of items on sale
     */
    public ArrayList<ArrayList<String>> getOutpostSaleProducts() {
        return outpost.getSaleProducts();
    }

    /**
     * Checks if the total price of the shopping bag is affordable by the crew
     * @return boolean true if un-affordable, false otherwise
     */
    public boolean isShoppingBagTooExpensive() {
        return outpost.getTotalPrice() > crew.getMoney();
    }

    /**
     * Removes an item from the shopping bag
     * @param item The item to be removed
     */
    public void removeItemFromShoppingBag(String item) {
        outpost.removeItemFromShoppingBag(item);
    }

    /**
     * Returns the total price of items in the shopping bag
     * @return int Total price of items
     */
    public int getShoppingBagTotalPrice() {
        return outpost.getTotalPrice();
    }
    //
    // OUTPOST RELATED FUNCTIONS END

    // PLANET RELATED FUNCTIONS START
    //
    /**
     * Removes the ship pieces from the planet
     */
    public void planetExtractShipPieces() {
        planets.get(currentPlanetIndex).extractShipPieces();
    }

    /**
     * Get the name of the planet the crew is currently in
     * @return String name of the planet
     */
    public String getPlanetName() {
        return planets.get(currentPlanetIndex).getName();
    }

    /**
     * Returns if the current planet has ship pieces
     * @return boolean true if planet has ship pieces, false otherwise
     */
    public boolean planetHasShipPieces() {
        return planets.get(currentPlanetIndex).stillHasShipPieces();
    }

    /**
     * Sets up the planets for the player to explore
     */
    public void setupPlanets() {
        planets = new ArrayList<>();
        // Make 15 planets, only 6 of them have ship pieces
        String[] planetsWithShipPieces = {"CX1337", 
            "Aiur", "Earth0x02", "RuhRoh", "BobbyBobBob", "Amber"};

        for (int i = 0; i < shipPieces; i++) {
            planets.add(new Planet(planetsWithShipPieces[i], true));
        }

        String[] firstName = {"Auspicious", "Green", "Dumpster"};
        String[] lastName = {"Pie", "Crane", "Bonsai", "Ilama"};
        for (int i = 0; i < firstName.length; i++) {
            for(int j = 0; j < lastName.length; j++) {
                planets.add(new Planet(firstName[i] + " " + lastName[j], false));
                if (planets.size() >= shipPieces * 2) {
                    return;
                }
            }
        }
    }

    // PLANET RELATED FUNCTIONS END

    // GAME RELATED FUNCTIONS START

    /**
     * Returns if the game length is between 3 to 10
     * @param numOfDays length of the game in days
     * @return boolean True if valid, false otherwise
     */
    public boolean isValidNumOfDays(int numOfDays) {
        return numOfDays <= 10 && numOfDays >= 3;
    }

    /**
     * Sets the length of the game
     * @param length length of the game
     */
    public void setGameLength(int length) {
        gameLength = length;
    }

    /**
     * Sets the number of ship pieces a player has to find
     */
    public void setShipPieces() {
        shipPieces = calculateShipPieces(gameLength);
    }

    /**
     * Returns the number of ship pieces a player has to find
     * @return int Number of ship pieces
     */
    public int getShipPieces() {
        return shipPieces;
    }

    /**
     * Calculates the number of ship pieces according to game length
     * @param days length of the game in days
     * @return int number of ship pieces
     */
    public int calculateShipPieces(int days) {
        return days * 2 / 3;
    }

    /**
     * Returns the ship status
     * @return ArrayList<String> List of stats the spaceship currently is in
     */
    public ArrayList<String> getShipStatus() {
        return ship.getShipStatus();
    }
    /**
     * Returns whether an event happens
     * @param happeningChance Chance of it happening
     * @return boolean True if happened, false otherwise
     */
    public boolean unlucky(int happeningChance) {
        Random rand = new Random();
        int chance = rand.nextInt(101);
        return chance < happeningChance;
    }

    /**
     * Checks if the player has found all the ship pieces
     * @return boolean True if found all, false otherwise
     */
    public boolean hasFoundEnoughPieces() {
        return foundShipPieces == shipPieces;
    }

    /**
     * Refresh the action stat for all the crew members
     */
    public void refreshActions() {
        // by the end of the day, everyone went to bed and get ready
        // for the next day. Refreshes all the crew members action to 2
        for (CrewMember c : crewMembers) {
            c.refreshActions(2);
        }
    }

    /**
     * Ends the day, refreshes the actions of the crew members and 
     * increments the current day
     */
    public void endDay() {
        refreshActions();
        currDay++;
    }

    /**
     * Check if the game has finished
     * @return boolean True if ended, false otherwise
     */
    public boolean hasGameEnded() {
        return hasFoundEnoughPieces() || gameLength - currDay == -1 || getDeadCrewMembers().size() == getCrewMemberStatus().size();
    }

    /**
     * Returns the number of ship pieces in crew's possession
     * @return int Number of ship pieces in crew's possession
     */
    public int getFoundShipPieces() {
        return foundShipPieces;
    }

    /**
     * Increases the number of ship pieces in crew's possession by 1
     */
    public void incrementFoundShipPieces() {
        foundShipPieces++;
    }

    /**
     * Returns the current day
     * @return int current day
     */
    public int getCurrDay() {
        return currDay;
    }

    /**
     * Returns the game length in days
     * @return int Game length in days
     */
    public int getGameLength() {
        return gameLength;
    }

    /**
     * Returns if the spaceship hits asteroid on their way to other planet
     * @return boolean True if hit, false otherwise
     */
    public boolean isHitAsteroid() {
        // percentage chance of 40% happening
        boolean unlucky = unlucky(40);
        return unlucky;
    }

    /**
     * Asteroid causes damage to the crew
     */
    public void asteroidCausingDamage() {
        AsteroidBelt.causeDamage(crew);
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void saveGame() {

    }
    
    public int getFinalScore() {
    	int finalScore = 0;
    	finalScore = (gameLength - currDay) * 10000 + foundShipPieces * 5000;
    	
    	return finalScore;
    }

    // GAME RELATED FUNCTIONS END

}
