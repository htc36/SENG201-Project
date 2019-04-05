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

    public Crew(CrewMember[] crews, Spaceship newShip) {
        consumables = new TreeMap<>();
        consumablesList = new ArrayList<>();

        money = 0;
        crewMembers = new ArrayList<>();
        ship = newShip;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int sum) {
        money += sum;
    }

    public Consumable popConsumable(String itemName) {
        int itemCount = 0;
        for (Consumable c : consumables.keySet()) {
            String consumableName = c.getName();
            if (consumableName == itemName) {
                itemCount = consumables.get(c);
                if (itemCount > 0) {
                    consumables.put(c, itemCount - 1);
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

    public String getCrewMemberStatus() {
        String template = "";
        for (CrewMember c : crewMembers) {
            template += c + "\n";
        }
        return template;
    }

    public String getSpaceshipStatus() {
        return ship.toString();
    }

    //public void visitOutpost() {
    //}

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

}
