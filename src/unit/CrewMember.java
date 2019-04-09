package unit;

import java.util.Random;

import consumable.Food;

public class CrewMember extends Unit {

    private int hungerLevel;
    private int fatiqueLevel;
    private int luck;
    private int actions;
    private boolean hasPlague;

    /* 
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

    public CrewMember(String memberName, int luckStat, int actions) {
        super(memberName);
        luck = luckStat;
        this.actions = actions;
        hungerLevel = 0;
        fatiqueLevel = 0;
        hasPlague = false;
    }

    // Getters and Setters
    /* 
     * @return number of actions the crew member has
     */
    public int getActions() {
        return actions;
    }

    public int getFatiqueLevel() {
        return fatiqueLevel;
    }

    public int getLuck() {
        return luck;
    }

    /* 
     * @return fatique level of crew member
     */
    public int getFatique() {
        return fatiqueLevel;
    }

    /* 
     * Increases the crew member's fatique level
     */
    public void increaseFatique(int amount) {
        fatiqueLevel += amount;
    }

    /* 
     * @return hunger level of crew member
     */
    public int getHunger() {
        return hungerLevel;
    }

    public void decreaseHunger(int amount) {

        hungerLevel -= amount;
        if (hungerLevel < 0) {
            hungerLevel = 0;
        }
    }

    /* 
     * Increases the crew member's hunger level
     */
    public void increaseHunger(int amount) {
        hungerLevel += amount;
    }
    /* 
     * @return crew member's health status
     * if true, crew member will lose health over time
     */
    public boolean isSick() {
        return hasPlague;
    }

    public void makeSick() {
        hasPlague = true;
    }

    public void cureSick() {
        hasPlague = false;
    }


    /* 
     * Pilot the spaceship to another planet along with
     * another crew member co-pilot
     * @param c the co-pilot
     */
    public void pilotShip(CrewMember c) {
        reduceAction();
        c.reduceAction();
    }

    /* 
     * The crew member sleeps, decreasing their fatique level
     */
    public void sleep(int amount) {
        reduceAction();
        fatiqueLevel -= amount;
    }



    /* 
     * Repairs the shield of the spaceship, increasing
     * its health by a certain amount
     * @param memberName name of the new unit
     */
    public void repairShield(Spaceship s, int amount) {
        reduceAction();
        s.repairShield(amount);
    }

    /* 
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

    /* 
     * Reduces actions for a crew member by 1
     * Crew member has a minimum of 0 action and 
     * a maximum of 2.
     * The method throws InsufficientActionError
     * if it tries to reduce the action below 0.
     */
    public void reduceAction() throws InsufficientActionException {
        if (actions > 0) {
            actions -= 1;
        } else {
            throw new InsufficientActionException();
        }
    }

    public void refreshActions(int amount) {
        actions = amount;
    }

    public String toString() {
        String status = "F";
        if (hasPlague) {
            status = "T";
        }

        String template = String.format("%12.12s", super.getName()); // name
        template += "%11.11s"; //type
        template += String.format("%9d", super.getHealth()); // health stat
        template += String.format("%7d", luck); // luck stat
        template += String.format("%10.1s", status); // has plague
        template += String.format("%9d", hungerLevel); // hunger level
        template += String.format("%10d", fatiqueLevel); // fatique level
        template += String.format("%10d", actions); // actions

        return template;
    }   
    //
    //public void useMedicalSupply(MedicalSupply item) {
    //}

    public void feed(Food item) {
        addHealth(item.getHealingAmount());
        decreaseHunger(item.getFillStomach());
    }

}
