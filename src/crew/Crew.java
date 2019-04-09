package crew;

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Random;

import consumable.Consumable;
import unit.CrewMember;
import unit.Spaceship;

public class Crew {
    private TreeMap<Consumable, Integer> consumables;
    private ArrayList<String> consumablesList;

    private int money;
    private ArrayList<CrewMember> crewMembers;
    private Spaceship ship;

    /**
     * <<auto generated javadoc comment>>
     * @param crews <<Param Desc>>
     * @param newShip <<Param Desc>>
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
     * @return Spaceship <<Return Desc>>
     */
    public Spaceship getSpaceship() {
        return ship;
    }

    public ArrayList<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return int <<Return Desc>>
     */
    public int getMoney() {
        return money;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param sum <<Param Desc>>
     */
    public void addMoney(int sum) {
        money += sum;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param sum <<Param Desc>>
     */
    public void shellOutMoney(int sum) {
        if (money < sum) {
            String errTemplate = "You don't have enough money to do this action";
            throw new InsufficientFundException(errTemplate);
        }

        money -= sum;
    }

    /**
     * <<auto generated javadoc comment>>
     * @param itemName <<Param Desc>>
     * @return int <<Return Desc>>
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
     * <<auto generated javadoc comment>>
     * @param itemName <<Param Desc>>
     * @return Consumable <<Return Desc>>
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
     * <<auto generated javadoc comment>>
     * @param item <<Param Desc>>
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
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    public String getCrewMemberStatus() {
        String template = "";
        for (CrewMember c : crewMembers) {
            template += c + "\n";
        }
        return template;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    public String getSpaceshipStatus() {
        return ship.toString();
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void popRandomItem() {
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
    public String consumablesToString() {
        String template = "";
        for (Consumable c : consumables.keySet()) {
            template += c + "\n";
        }

        return template;
    }

}
