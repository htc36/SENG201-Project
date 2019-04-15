package crew;

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Random;

import game.InsufficientFundException;
import consumable.Consumable;
import unit.CrewMember;
import unit.Spaceship;

public class Crew {
    private TreeMap<Consumable, Integer> consumables;
    private ArrayList<String> consumablesList;
    private String lostItem;

    private int money;
    private ArrayList<CrewMember> crewMembers;
    private Spaceship ship;

    /**
     * constructor for a crew
     * Crew object is responsible for the state of a player in the game
     * It keeps track of player's items, money, spaceship, and crew members
     * @param crews list of crew members inside this crew
     * @param newShip the spaceship of the crew
     */
    public Crew(ArrayList<CrewMember> crews, Spaceship newShip) {
        consumables = new TreeMap<>();
        consumablesList = new ArrayList<>();

        money = 100;
        crewMembers = crews;
        ship = newShip;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param c <<Param Desc>>
     */
    public void removeCrewMember(CrewMember c) {
        crewMembers.remove(c);
    }

    public TreeMap<Consumable, Integer> getConsumables() {
        return consumables;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return ArrayList<CrewMember> <<Return Desc>>
     */
    public void updateCrewStatus() {
        for(CrewMember c : crewMembers) {
            c.increaseFatique(25);
            c.increaseHunger(25);
            if(c.isSick())
                c.reduceHealth(5);
            if (c.getHealth() == 0) {
            }
        }
    }

    public ArrayList<CrewMember> getUnhealthyCrewMembers() {
        ArrayList<CrewMember> unhealthyMembers = new ArrayList<>();
        for (CrewMember c : crewMembers) {
            if (c.getHunger() > 50 || c.getFatique() > 50)
                unhealthyMembers.add(c);
        }
        return unhealthyMembers;
    }


    public ArrayList<CrewMember> getDeadCrewMembers() {
        ArrayList<CrewMember> deadMembers = new ArrayList<>();
        for (CrewMember c : crewMembers) {
            if (c.getHealth() == 0)
                deadMembers.add(c);
        }
        return deadMembers;
    }

    /**
     * get the spaceship of the crew
     * @return Spaceship the spaceship of the crew
     */
    public Spaceship getSpaceship() {
        return ship;
    }

    /**
     * get the list of crew members of the crew
     * @return list of the crew members
     */
    public ArrayList<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    /**
     * get the amount of money the crew has
     * @return int amount of money
     */
    public int getMoney() {
        return money;
    }

    /**
     * adds money to crew's pocket
     * @param sum the amount of money added
     */
    public void addMoney(int sum) {
        money += sum;
    }

    /**
     * takes out money from a crew's pocket for purchasing stuff
     * @param sum amount of money taken out
     */
    public void shellOutMoney(int sum) {
        if (money < sum) {
            String errTemplate = "You don't have enough money to do this action";
            throw new InsufficientFundException(errTemplate);
        }

        money -= sum;
    }

    /**
     * get the count of particular consumable in crew's possession
     * @param itemName the name of the consumable
     * @return int count of the consumable
     */
    public int getConsumableCount(String itemName) {
        int itemCount = 0;
        for (Consumable c : consumables.keySet()) {
            String consumableName = c.getName();
            if (consumableName == itemName) {
                itemCount = consumables.get(c);
            }
        }

        return itemCount;
    }

    /**
     * pops a consumable from the crew's possession, removing it
     * @param itemName name of the consumable
     * @return Consumable the consumable object removed
     */
    public Consumable popConsumable(String itemName) {
        int itemCount = 0;
        for (Consumable c : consumables.keySet()) {
            String consumableName = c.getName();
            if (consumableName == itemName) {
                itemCount = consumables.get(c);
                if (itemCount > 0) {
                    itemCount--;
                    consumables.put(c, itemCount);
                    if (itemCount == 0) {
                        consumablesList.remove(itemName);
                    }
                    return c;
                }
            }
        }

        String errTemplate = "There is not enough " + itemName + " in your stock.";
        throw new InsufficientItemInStock(errTemplate);
    }

    /**
     * adds a consumable to the crew's possession
     * @param item the consumable item
     */
    public void addConsumable(Consumable item) {
        int itemCount = 0;
        if (consumables.containsKey(item)) { 
            itemCount = consumables.get(item);
        }
        consumables.put(item, itemCount + 1);

        String itemName = item.getName();
        if (!consumablesList.contains(itemName)) {
            consumablesList.add(itemName);
        }
    }

    /**
     * get a string representation of crew members status
     * it is formatted such that it fits nicely in a table
     * @return String string representation of crew members
     */
    public ArrayList<ArrayList<String>> getCrewMemberStatus() {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (CrewMember c : crewMembers) {
            result.add(c.getCrewString());
        }
        return result;
    }

    /**
     * get a string representation of crew's spaceship
     * it is formatted such that it fits nicely in a table
     * @return String string representation of a spaceship
     */
    public String getSpaceshipStatus() {
        return ship.toString();
    }

    /**
     * pops a random consumable from crew's inventory
     */
    public void popRandomItem() {
        lostItem = "";
        Random rand = new Random();
        int totalItems = consumablesList.size();
        if (totalItems == 0) {
            // jokes on them we got no items HAHA
            return;
        }

        int randFoodIndex = rand.nextInt(totalItems);
        String randomConsumable = consumablesList.get(randFoodIndex);
        for (Consumable c : consumables.keySet()) {
            String consumableName = c.getName();
            if (randomConsumable == consumableName) {
                lostItem = consumableName; 
                popConsumable(consumableName);

                return;
            }
        }
        return;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    public String getLostItem(){
        return lostItem;
    }

}
