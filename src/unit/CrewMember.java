package unit;

import java.util.ArrayList;
import java.util.Random;

import consumable.Consumable;
import consumable.Food;
import consumable.MedicalSupply;

public abstract class CrewMember extends Unit {

    /**
     * Current hunger level of a crew member
     */
    private int hungerLevel;

    /**
     * Current fatigue level of a crew member
     */
    private int fatiqueLevel;

    /**
     * Luck stat of a crew member
     * The higher it is, the more chance it has of 
     * finding a ship piece
     */
    private int luck;

    /**
     * Number of actions left
     */
    private int actions;

    /**
     * Whether it has space plague
     */
    private boolean hasPlague;

    /**
     * Constructor for Unit
     * @param memberName name of the new unit
     * @param luckStat unit's percentage chance of finding 
     *                 ship pieces, the higher the luckStat
     *                 the more likely they will find a ship piece
     */
    public CrewMember(String memberName, int luckStat) {
        super(memberName);
        luck = luckStat;
        actions = 2;
        hungerLevel = 0;
        fatiqueLevel = 0;
        hasPlague = false;
    }

    /**
     * Constructor for a crew member
     * This constructor will be called by the unit type
     * subclasses
     * @param memberName name of the unit
     * @param luckStat luck stat that a unit has
     * @param actions number of actions a unit has
     */
    public CrewMember(String memberName, int luckStat, int actions) {
        super(memberName);
        luck = luckStat;
        this.actions = actions;
        hungerLevel = 0;
        fatiqueLevel = 0;
        hasPlague = false;
    }

    /**
     * Sets the action of a crew member to an amount
     * @param actions the amount of actions to be set to
     */
    public void setActions(int actions) {
        this.actions = actions;
    }

    /**
     * Sets the hunger of a crew member to an amount
     * @param hunger the amount of hunger to be set to
     */
    public void setHunger(int hunger) {
        hungerLevel = hunger;
    }

    /**
     * Sets the fatigue of a crew member to an amount
     * @param fatique the amount of fatigue to be set to
     */
    public void setFatique(int fatique) {
        fatiqueLevel = fatique;
    }

    /**
     * Returns whether a crew member can still do actions
     * @return boolean True if can do actions, false otherwise
     */
    public boolean stillHasActions() {
        return actions > 0;
    }

    /**
     * Returns the number of actions the crew member has 
     * @return int number of actions
     */
    public int getActions() {
        return actions;
    }

    /**
     * Returns the luck stat of a crew member
     * @return int luck stat
     */
    public int getLuck() {
        return luck;
    }

    /**
     * Returns the fatigue level of the crew member
     * @return int fatique level
     */
    public int getFatique() {
        return fatiqueLevel;
    }

    /**
     * Increases the crew member's fatigue level
     */
    public void increaseFatique(int amount) {
        fatiqueLevel += amount;
        if (fatiqueLevel > 100) 
            fatiqueLevel = 100;
    }

    /**
     * Returns the hunger level of the crew
     * @return int hunger level
     */
    public int getHunger() {
        return hungerLevel;
    }

    /**
     * decreases the hunger level of a unit for amount specified
     * @param amount decreased hunger level
     */
    public void decreaseHunger(int amount) {

        hungerLevel -= amount;
        if (hungerLevel < 0) {
            hungerLevel = 0;
        }
    }

    /** 
     * Increases the crew member's hunger level
     * @param amount increased hunger level
     */
    public void increaseHunger(int amount) {
        hungerLevel += amount;
        if (hungerLevel > 100)
            hungerLevel = 100;
    }

    /** 
     * Returns if crew member has space plague
     * @return boolean True if has space plague, false otherwise
     */
    public boolean isSick() {
        return hasPlague;
    }

    /**
     * gives a plagued status to a crew member
     */
    public void makeSick() {
        hasPlague = true;
    }

    /**
     * removes plagued status from a crew member
     */
    public void cureSick() {
        hasPlague = false;
    }


    /**
     * Pilot the spaceship to another planet along with
     * another crew member co-pilot
     * @param c the co-pilot
     */
    public void pilotShip(CrewMember c) {
        reduceAction();
        c.reduceAction();
    }

    /**
     * The crew member sleeps, decreasing their fatique level
     * @param amount reduced fatique level
     */
    public void sleep(int amount) {
        reduceAction();
        fatiqueLevel -= amount;
        if (fatiqueLevel < 0){
            fatiqueLevel = 0;
        }   
    }

    /**
     * Repairs the shield of the spaceship, increasing
     * its health by a certain amount
     * @param s the spaceship that is being repaired
     * @param amount increased shield health by repairing
     */
    public void repairShield(Spaceship s, int amount) {
        reduceAction();
        s.repairShield(amount);
    }

    /**
     * The crew member searches for ship pieces on the planet
     * The probability they found one depends on their luck stat
     * @return whether they found a ship piece or not
     */
    public boolean searchPlanet() {
        reduceAction();
        Random rand = new Random();
        int chance = rand.nextInt(101);

        return chance < luck;
    }

    /**
     * Reduces actions for a crew member by 1
     * Crew member has a minimum of 0 action and 
     * a maximum of 2.
     * The method throws InsufficientActionError
     * if it tries to reduce the action below 0.
     */
    public void reduceAction() throws InsufficientActionException {
        if (actions > 0) {
            actions--;
        } else {
            throw new InsufficientActionException("Selected unit does not have enough actions");
        }
    }

    /**
     * refreshes a crew member's actions setting it back to
     * the amount specified
     * @param amount number of actions a crew member has
     */
    public void refreshActions(int amount) {
        actions = amount;
    }

    /**
     * feeding an item to a crew member, increasing their health
     * and decreasing their hunger level
     * @param item the item
     */
    public void useItem(Food item) {
        addHealth(item.getHealingAmount());
        decreaseHunger(item.getFillStomach());
    }

    /**
     * feeding an item to a crew member, increasing their health
     * and cures space plague
     * @param item the item
     */
    public void useItem(MedicalSupply item) {
        addHealth(item.getHealingAmount());
        if (item.canHealSpacePlague()) {
            cureSick();
        }
    }
    
    /**
     * uses an item, calls other useItem depending
     * on the item type
     * @param item
     */
    public void useItem(Consumable item) {
        reduceAction();
        if (item instanceof Food)
            useItem((Food) item);
        else
            useItem((MedicalSupply) item);
    }

    /**
     * Reduces the health of a crew member by a certain amount
     * @param amount the amount
     */
    @Override
    public void reduceHealth(int amount){
        if (hungerLevel > 80 || fatiqueLevel > 80)
            super.reduceHealth(amount * 3);
        else if (hungerLevel > 50 || fatiqueLevel > 50)
            super.reduceHealth(amount * 2);
        else
            super.reduceHealth(amount);
    }

    /**
     * Returns the representation of a crew member in ArrayList<String>
     * @return ArrayList<String> Representation of a crew member
     */
    public ArrayList<String> getCrewString() {
        String status = "F";
        if (hasPlague) {
            status = "T";
        }

        ArrayList<String> result = new ArrayList<>();
        result.add(super.getName());
        result.add(String.format("%d", super.getHealth()));
        result.add(String.format("%d", luck));
        result.add(status);
        result.add(String.format("%d", hungerLevel));
        result.add(String.format("%d", fatiqueLevel));
        result.add(String.format("%d", actions));

        return result;
    }

}
