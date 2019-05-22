package game;


import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

import consumable.*;
import crew.Crew;
import events.*;
import outpost.Outpost;
import planet.Planet;
import unit.*;

// Use JSON Simple to write save files
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

// JSON Simple to read JSON files
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Defines the game environment that keeps track of all the components
 * in the game. Every client of the game should call the methods 
 * provided by the game environment to run the game logic
 * 
 */
public class GameEngine {

    /**
     * Length of the game
     */
    private int gameLength;

    /**
     * Number of ship pieces in total
     */
    private int shipPieces;

    /**
     * List of possible crew member types
     */
    private ArrayList<String> crewMemberTypes;

    /**
     * List of crew's crew members
     */
    private ArrayList<CrewMember> crewMembers;

    /**
     * Crew's spaceship
     */
    private Spaceship ship;

    /**
     * The crew
     */
    private Crew crew;

    /**
     * Outpost where crew can buy stuff
     */
    private Outpost outpost;

    /**
     * Count of current day
     */
    private int currDay;

    /**
     * List of planets that can be travelled to
     */
    private ArrayList<Planet> planets;

    /**
     * Current position in the galaxy
     */
    private int currentPlanetIndex;

    /**
     * Number of ship pieces found by the crew
     */
    private int foundShipPieces;

    /**
     * Current crew member that is ready to commit an action
     */
    private CrewMember selectedCrew;

    /**
     * The copilot if the crew is going to another planet
     */
    private CrewMember copilot;

    /**
     * Constructor for GameEngine
     * Sets the current planet index to be the first planet,
     * Initialises found ship pieces to be 0,
     * Initialises the crewMembers list
     * and Sets up the outpost
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

        currentPlanetIndex = 0;
        foundShipPieces = 0;

        setupOutpost();

        crewMembers = new ArrayList<>();
    }

    /**
     * Another constructor for Game Engine to load the game from a save file
     * @param saveFilename Filename for the save file
     * @throws FileNotFoundException 
     */
    public GameEngine(String saveFilename) throws FileNotFoundException {
    	File saveFile = new File(saveFilename);
    	if (!saveFile.exists()) {
    		throw new FileNotFoundException("No saved file found");
    	}
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(saveFilename));
            JSONObject gameState = (JSONObject) obj;

            setCurrDay((int) (long) gameState.get("currDay"));

            String shipName = (String) gameState.get("shipName");
            ship = new Spaceship(shipName);
            ship.setHealth((int ) (long) gameState.get("shipHealth"));

            setGameLength((int) (long) gameState.get("gameLength"));
            setShipPieces();

            setFoundShipPieces((int) (long) gameState.get("foundShipPieces"));

            planets = new ArrayList<>();
            JSONObject planetsJSON = (JSONObject) gameState.get("planets");
            for (Object p : planetsJSON.keySet()) {
                String planetName = (String) p;
                boolean hasShipPiece = (boolean) planetsJSON.get(p);
                planets.add(new Planet(planetName, hasShipPiece));
            }

            setCurrentPlanetIndex((String) gameState.get("currPlanet"));

            setupOutpost();
            JSONArray crewMembersJSON = (JSONArray) gameState.get("crewMembers");
            ArrayList<ArrayList<String>> crewMemberArrayList = new ArrayList<>();
            for (int i = 0; i < crewMembersJSON.size(); i++) {
                JSONArray crewMemberDesc = (JSONArray) crewMembersJSON.get(i);
                ArrayList<String> memberData = new ArrayList<>();
                for (int j = 0; j < crewMemberDesc.size(); j++) {
                    memberData.add((String) crewMemberDesc.get(j));
                }

                crewMemberArrayList.add(memberData);
            }

            crewMembers = new ArrayList<>();
            loadCrewMembers(crewMemberArrayList);
            crew = new Crew(crewMembers, ship);
            crew.setMoney((int) (long) gameState.get("money"));

            // consumables
            JSONArray consumablesJSON = (JSONArray) gameState.get("consumables");
            ArrayList<ArrayList<String>> consumablesList = new ArrayList<>();
            for (int i = 0; i < consumablesJSON.size(); i++) {
                JSONArray consumableDesc = (JSONArray) consumablesJSON.get(i);
                ArrayList<String> consumableData = new ArrayList<>();
                for (int j = 0; j < consumableDesc.size(); j++) {
                    consumableData.add((String) consumableDesc.get(j));
                }
                consumablesList.add(consumableData);
            }

            for (ArrayList<String> cons : consumablesList) {
            	int stock = Integer.valueOf(cons.get(5));
            	for (int i = 0; i < stock; i++) {
					addCrewConsumable(cons.get(0));
            	}
            }


        } catch (FileNotFoundException err) {
            err.printStackTrace();
        } catch (IOException err) {
            err.printStackTrace();
        } catch (ParseException err) {
            err.printStackTrace();
        }
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
     * @return ArrayList List of items owned by the crew
     */
    public ArrayList<ArrayList<String>> getCrewConsumables() {
        TreeMap<Consumable, Integer> crewItems = crew.getConsumables();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (Consumable c : crewItems.keySet()) {
            ArrayList<String> itemStats = c.getConsumableStats();
            itemStats.add(String.valueOf(crewItems.get(c)));
            result.add(itemStats); // index number 
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
                break;
            case 0:
                SpacePlague.causeDamage(crew);
                break;
        }

        // 2 Nothing happens
        // 1 Alien Pirates
        // 0 Space Plague
        return randomEvent;
    }
    
    /**
     * Gets item that was lost from alien invasion
     * @return String item that was taken
     */
    public String getCrewLostItem() {
    	return crew.getLostItem();
    }

    /**
     * Adds consumable item to crew's possession
     * @param itemName Name of the item
     */
    public void addCrewConsumable(String itemName) {
        switch(itemName) {
            case "PickledPlum":
                addCrewConsumable(new PickledPlum());
                break;
            case "Brownie":
                addCrewConsumable(new Brownie());
                break;
            case "Dumplings":
                addCrewConsumable(new Dumplings());
                break;
            case "FriedRice":
                addCrewConsumable(new FriedRice());
                break;
            case "Hotbot":
                addCrewConsumable(new Hotbot());
                break;
            case "PolyJuice":
                addCrewConsumable(new PolyJuice());
                break;
            case "SpaceCake":
                addCrewConsumable(new SpaceCake());
                break;
            case "TikkaMasala":
                addCrewConsumable(new TikkaMasala());
                break;
            case "Vaccine":
                addCrewConsumable(new Vaccine());
                break;
        }
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
     * Checks if the number of crew members is between 2 to 4
     * @param amount the amount of crew members
     * @return boolean true if valid, false otherwise
     */
    public boolean isCrewNumberValid(int amount) {
        return amount >= 2 && amount <= 4;
    }

    /**
     * Checks if the crew member name is a duplicate of an existing one
     * @param crewList list of existing crews
     * @param name the name of the new crew member
     * @return boolean true if not a duplicate, false otherwise
     */
    public boolean isCrewNameValid(ArrayList<String[]> crewList, String name) {
        // to avoid 2 crew members having the same name
        for (String[] c : crewList) {
            if (c[0].equals(name))
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
     * @param name The crew member to be removed
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
    public void setCrewMembers(ArrayList<String[]> crewString) {
        for (String[] s : crewString) {
            String memberName = s[0].toUpperCase();
            String crewType = s[1].toLowerCase();
            addCrewMember(crewType, memberName);
        }
    }

    /**
     * Creates fresh batch of crew members from an array of crew member descriptions
     * @param crewMembers crew members descriptions 
     */
    public void loadCrewMembers(ArrayList<ArrayList<String>> crewMembers) {
        for (ArrayList<String> member : crewMembers) {
            String name = member.get(0);
            String type = member.get(7).toLowerCase();
            addCrewMember(type, name);

            int health = Integer.valueOf(member.get(1));
            String isPlagued = member.get(3);
            int hunger = Integer.valueOf(member.get(4));
            int fatigue = Integer.valueOf(member.get(5));
            int actions = Integer.valueOf(member.get(6));
            CrewMember c = this.crewMembers.get(this.crewMembers.size() - 1);
            c.setHealth(health);
            if (isPlagued.equals("T")) {
                c.makeSick();
            }
            c.setHunger(hunger);
            c.setFatique(fatigue);
            c.setActions(actions);
        }
    }

    /**
     * Returns the current status of the crew members
     * @return ArrayList List of crew member current stats
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
     * Returns the selected crew member for next action
     * Useful for testing
     * @return CrewMember selected crew member
     */
    public CrewMember getSelectedCrew() {
        return selectedCrew;
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
     * Kills the selected crew member, useful for testing
     */
    public void selectedCrewKill() {
        selectedCrew.setHealth(0);
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
     * Selected crew and copilot pilots the ship to another planet
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
    
    /**
     * Gets the crew members that are sick
     * @return String a string of sick crew members
     */
    public ArrayList<String> getSickCrew() {
    	ArrayList<String> sickCrew = new ArrayList<String>();
    	for (CrewMember crew : crewMembers) {
    		if (crew.isSick())
    			sickCrew.add(crew.getName());		
    	}
    	return sickCrew;
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
        return ship.getHealth() > 25;
    }
    
    /**
     * Returns the crew's spaceship name
     * @return String Crew's spaceship name
     */
    public String getSpaceshipName() {
    	return ship.getName();
    }

    // SHIP RELATED FUNCTIONS END

    // OUTPOST RELATED FUNCTIONS START

    /**
     * Creates a new outpost
     */
    public void setupOutpost() {
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
    }

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

    /**
     * Returns the shopping bag of a player from the outpost
     * @return Shopping bag of player
     */
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
     * @return ArrayList List of items on sale
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

    /**
     * Sets the current location of the spaceship to planet index
     * @param index Index of planet
     */
    public void setCurrentPlanetIndex(String planetName) {
    	for (Planet p : planets) {
    		if (planetName.equals(p.getName())) {
    			currentPlanetIndex = planets.indexOf(p);
    			break;
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
     * Sets the number of ship pieces found to amount
     * @param amount Amount of ship pieces found
     */
    public void setFoundShipPieces(int amount) {
        foundShipPieces = amount;
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
     * Sets the current day
     * @param currDay current day
     */
    public void setCurrDay(int currDay) {
        this.currDay = currDay;
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
        // percentage chance of 50% happening
        return unlucky(50);
    }

    /**
     * Asteroid causes damage to the crew
     */
    public void asteroidCausingDamage() {
        AsteroidBelt.causeDamage(crew);
    }

    /**
     * Processes the achievements the player has then calculates
     * the final score
     * @return int The final score
     */
    public int getFinalScore() {
        int finalScore = 0;
        finalScore = (gameLength - currDay) * 10000 + foundShipPieces * 10000;

        if (finalScore < 0) {
            finalScore = 0;
        }

        return finalScore;
    }

    /**
     * Saves the game state to a JSON file
     */
    @SuppressWarnings("unchecked")
    public void saveGameState() {
        JSONObject gameState = new JSONObject();

        gameState.put("gameLength", gameLength);
        gameState.put("shipPieces", shipPieces);
        gameState.put("currDay", currDay);
        gameState.put("foundShipPieces", foundShipPieces);
        gameState.put("currPlanet", planets.get(currentPlanetIndex).getName());
        gameState.put("money", crew.getMoney());

        // planets state
        JSONObject planetsList = new JSONObject();
        for (Planet p : this.planets) {
            planetsList.put(p.getName(), p.stillHasShipPieces());
        }

        gameState.put("planets", planetsList);

        // consumables state
        ArrayList<ArrayList<String>> cons = getCrewConsumables();
        JSONArray itemsList = new JSONArray();
        for (ArrayList<String> item : cons) {
            JSONArray itemDesc = new JSONArray();
            for (String desc : item) {
                itemDesc.add(desc);
            }
            itemsList.add(itemDesc);
        }

        gameState.put("consumables",  itemsList);

        // crew members state
        ArrayList<ArrayList<String>> members = getCrewMemberStatus();
        JSONArray membersList = new JSONArray();
        for (ArrayList<String> member : members) {
            JSONArray memberDesc = new JSONArray();
            for (String desc : member) {
                memberDesc.add(desc);
            }
            membersList.add(memberDesc);
        }
        gameState.put("crewMembers", membersList);

        gameState.put("shipName", ship.getName());
        gameState.put("shipHealth", ship.getHealth());

        // write json object to save file
        try {
            String homeEnv = System.getenv("HOME");
            FileWriter f = new FileWriter(homeEnv + "/.save.json");
            f.write(gameState.toJSONString());
            f.flush();
            f.close();
        } catch (IOException err) {
            err.printStackTrace();
        }

    }
    
    /**
     * Gets a random death message for our hard working crew member :'(
     * @return String random death message
     */
    public String getDeathMessage() {
    	return Utils.getDeathMessage();
    }

    // GAME RELATED FUNCTIONS END

}
