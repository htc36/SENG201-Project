package unit;

import java.util.ArrayList;
import java.util.Random;

import consumable.Consumable;
import consumable.Food;
import consumable.MedicalSupply;

public abstract class CrewMember extends Unit {

    private int hungerLevel;
    private int fatiqueLevel;
    private int luck;
    private int actions;
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
     * <<auto generated javadoc comment>>
     * @return boolean <<Return Desc>>
     */
    public boolean stillHasActions() {
        return actions > 0;
    }

    /**
     * @return number of actions the crew member has
     */
    public int getActions() {
        return actions;
    }

    /**
     * @return int returns the fatique level of a crew member
     */
    public int getFatiqueLevel() {
        return fatiqueLevel;
    }

    /**
     * @return int returns the luck stat of a crew member
     */
    public int getLuck() {
        return luck;
    }

    /**
     * @return fatique level of crew member
     */
    public int getFatique() {
        return fatiqueLevel;
    }

    /**
     * Increases the crew member's fatique level
     */
    public void increaseFatique(int amount) {
        fatiqueLevel += amount;
        if (fatiqueLevel > 100) 
            fatiqueLevel = 100;
    }

    /**
     * @return hunger level of crew member
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
     * @return crew member's health status
     * returns true if crew member has the plague
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
     * use a medical item on a crew member, increasing their health
     * and possibly curing them from space plague
     * @param item the medicalsupply object item
     */
    public void useMedicalSupply(MedicalSupply item) {
        addHealth(item.getHealingAmount());
        if (item.canHealSpacePlague()) {
            cureSick();
        }
    }

    /**
     * feeding an item to a crew member, increasing their health
     * and decreasing their hunger level
     * @param item the food object item
     */
    public void feed(Food item) {
        addHealth(item.getHealingAmount());
        decreaseHunger(item.getFillStomach());
    }

    /**
     * <<auto generated javadoc comment>>
     * @param item <<Param Desc>>
     */
    public void useItem(Consumable item) {
        reduceAction();
        if (item instanceof Food) {
            feed((Food) item);
        } else if (item instanceof MedicalSupply) {
            useMedicalSupply((MedicalSupply) item);
        }
    }
    /**
     * <<auto generated javadoc comment>>
     * @param amount <<Param Desc>>
     */
    @Override
    public void reduceHealth(int amount){
        if (hungerLevel > 50)
            super.reduceHealth(amount * 2);
        else if (hungerLevel > 80)
            super.reduceHealth(amount * 3);
        if (fatiqueLevel > 50)
            super.reduceHealth(amount * 2);
        else if (fatiqueLevel > 80)
            super.reduceHealth(amount * 3);
    }

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
