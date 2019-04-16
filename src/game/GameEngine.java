package game;


import java.util.ArrayList;
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
     * <<auto generated javadoc comment>>
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

    public int getCrewMoney() {
        return crew.getMoney();
    }

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

    public int getCrewConsumablesCount() {
        return crew.getConsumables().size();
    }

    public ArrayList<String> getCrewMemberTypes() {
        return crewMemberTypes;
    }

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
     * <<auto generated javadoc comment>>
     * @param item <<Param Desc>>
     */
    public void addCrewConsumable(Consumable item) {
        crew.addConsumable(item);
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void setupCrew() {
        crew = new Crew(crewMembers, ship);
    }

    public boolean isCrewNumberValid(int amount) {
        return amount >= 2 && amount <= 4;
    }

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

    public void removeCrewMember(CrewMember c) {
        crew.removeCrewMember(c);
    }

    public void setCrewMembers(ArrayList<String> crewString) {
        for (String s : crewString) {
            String memberName = s.split("-")[0].toUpperCase();
            String crewType = s.split("-")[1].toLowerCase();
            addCrewMember(crewType, memberName);
        }
    }

    public ArrayList<ArrayList<String>> getCrewMemberStatus() {
        return crew.getCrewMemberStatus();
    }

    public void updateCrewMemberStatus() {
        crew.updateCrewStatus();
    }

    public ArrayList<String> getDeadCrewMembers() {
        ArrayList<String> names = new ArrayList<>();
        for (CrewMember c : crew.getDeadCrewMembers()) {
            names.add(c.getName());
        }
        return names;
    }

    public ArrayList<String> getUnhealthyCrewMembers() {
        ArrayList<String> names = new ArrayList<>();
        for (CrewMember c : crew.getUnhealthyCrewMembers()) {
            names.add(c.getName());
        }
        return names;
    }

    public void selectedCrewUseItem(int itemIndex) {
        String itemName = getCrewConsumables().get(itemIndex).get(0);
        selectedCrew.useItem(crew.popConsumable(itemName));
    }

    public int selectedCrewGetFatique() {
        return selectedCrew.getFatique();
    }

    public void selectedCrewSleep() {
        selectedCrew.sleep(30);
    }

    public void selectedCrewRepairShield() {
        selectedCrew.repairShield(ship, 30);
    }

    public boolean selectedCrewSearchPlanet() {
        return selectedCrew.searchPlanet();
    }

    public String crewGetRandomItem() {
        Consumable item = outpost.getRandomItem();
        crew.addConsumable(item);
        return item.getName();
    }

    public void selectCrewMember(int index) throws IndexOutOfBoundsException {
        selectedCrew = crewMembers.get(index);
    }

    public boolean selectedCrewHasAction() {
        return selectedCrew.stillHasActions();
    }

    public String selectedCrewName() {
        return selectedCrew.getName();
    }

    public void setCopilot(int index) throws IndexOutOfBoundsException {
        copilot = crewMembers.get(index);
    }

    public void crewAddMoney() {
        crew.addMoney(45);
    }

    /**
     * <<auto generated javadoc comment>>
     * @param currIndex <<Param Desc>>
     * @param copilotIndex <<Param Desc>>
     * @return boolean <<Return Desc>>
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
     * <<auto generated javadoc comment>>
     * @param name <<Param Desc>>
     */
    public void setupSpaceship(String name) {
        String shipName;
        if (name.length() == 0)
            shipName = "andromeda";
        else
            shipName = name;
        ship = new Spaceship(shipName.toUpperCase());
    }

    public int getSpaceshipHealth() {
        return ship.getHealth();
    }

    public boolean isSpaceshipAbleToFly() {
        return ship.getHealth() > 50;
    }

    // SHIP RELATED FUNCTIONS END

    // OUTPOST RELATED FUNCTIONS START

    public boolean hasOutpostStock(String itemName) {
        return outpost.hasItemInStock(itemName);
    }

    public void clearShoppingBag() {
        outpost.clearShoppingBag();
    }

    /**
     * <<auto generated javadoc comment>>
     * @param query <<Param Desc>>
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

    public ArrayList<ArrayList<String>> getOutpostSaleProducts() {
        return outpost.getSaleProducts();
    }

    public boolean isShoppingBagTooExpensive() {
        return outpost.getTotalPrice() > crew.getMoney();
    }

    public void removeItemFromShoppingBag(String item) {
        outpost.removeItemFromShoppingBag(item);
    }
    //
    // OUTPOST RELATED FUNCTIONS END

    // PLANET RELATED FUNCTIONS START
    //
    public void planetExtractShipPieces() {
        planets.get(currentPlanetIndex).extractShipPieces();
    }

    public String getPlanetName() {
        return planets.get(currentPlanetIndex).getName();
    }

    public boolean planetHasShipPieces() {
        return planets.get(currentPlanetIndex).stillHasShipPieces();
    }

    /**
     * <<auto generated javadoc comment>>
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
     * <<auto generated javadoc comment>>
     * @param numOfDays <<Param Desc>>
     * @return boolean <<Return Desc>>
     */
    public boolean isValidNumOfDays(int numOfDays) {
        return numOfDays <= 10 && numOfDays >= 3;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param length <<Param Desc>>
     */
    public void setGameLength(int length) {
        gameLength = length;
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void setShipPieces() {
        shipPieces = calculateShipPieces(gameLength);
    }

    /**
     * <<auto generated javadoc comment>>
     * @param days <<Param Desc>>
     * @return int <<Return Desc>>
     */
    public int calculateShipPieces(int days) {
        return days * 2 / 3;
    }

    public ArrayList<String> getShipStatus() {
        return ship.getShipStatus();
    }
    public boolean unlucky(int happeningChance) {
        Random rand = new Random();
        int chance = rand.nextInt(101);
        return chance < happeningChance;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return boolean <<Return Desc>>
     */
    public boolean hasFoundEnoughPieces() {
        return foundShipPieces == shipPieces;
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void refreshActions() {
        // by the end of the day, everyone went to bed and get ready
        // for the next day. Refreshes all the crew members action to 2
        for (CrewMember c : crewMembers) {
            c.refreshActions(2);
        }
    }

    public void endDay() {
        refreshActions();
        currDay++;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return boolean <<Return Desc>>
     */
    public boolean hasGameEnded() {
        return hasFoundEnoughPieces() || gameLength - currDay == -1 || crewMembers.size() == 0;
    }

    public int getFoundShipPieces() {
        return foundShipPieces;
    }

    public void incrementFoundShipPieces() {
        foundShipPieces++;
    }

    public int getCurrDay() {
        return currDay;
    }

    public int getGameLength() {
        return gameLength;
    }

    public boolean isHitAsteroid() {
        // percentage chance of 40% happening
        boolean unlucky = unlucky(40);
        return unlucky;
    }

    public void asteroidCausingDamage() {
        AsteroidBelt.causeDamage(crew);
    }

    // GAME RELATED FUNCTIONS END

}
