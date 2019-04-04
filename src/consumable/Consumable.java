package consumable;

import java.security.InvalidParameterException;

public class Consumable {

    private int price;
    private int healingAmount;
    private String name;

    public Consumable(String itemName, int heal, int itemPrice) {
        name = itemName;
        healingAmount = heal;
        price = itemPrice;

        if (name.length() <= 0) {
            throw new InvalidParameterException("Name can't be empty");
        }

        if (healingAmount <= 0) {
            throw new InvalidParameterException("Healing amount can't be below 0");
        }

        if (price <= 0) {
            throw new InvalidParameterException("Price can't be below $0");
        }
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getHealingAmount() {
        return healingAmount;
    }
}
