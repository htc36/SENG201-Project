package unit;

import java.util.Random;

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

    // Getters and Setters
    /* 
     * @return number of actions the crew member has
     */
    public int getActions() {
        return actions;
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
    public void increaseFatique() {
        fatiqueLevel += 5;
    }

    /* 
     * @return hunger level of crew member
     */
    public int getHunger() {
        return hungerLevel;
    }

    /* 
     * Increases the crew member's hunger level
     */
    public void increaseHunger() {
        hungerLevel += 5;
    }
    /* 
     * @return crew member's health status
     * if true, crew member will lose health over time
     */
    public boolean isSick() {
        return hasPlague;
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
    public void sleep() {
        reduceAction();
        fatiqueLevel -= 10;
    }

    /* 
     * Repairs the shield of the spaceship, increasing
     * its health by a certain amount
     * @param memberName name of the new unit
     */
    public void repairShield(Spaceship s) {
        reduceAction();
        s.repairShield(10);
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
    
    //public void useMedicalSupply(MedicalSupply item) {
    //}

    //public void feed(Food item) {
    //}

}
