package unit;

public class Hungus extends CrewMember{

    /**
     * constructor for hungus type crew member
     * hungus type units gets hungry less easily
     * and benefit more from food
     * @param memberName name of the hungus unit
     */
    public Hungus(String memberName) {
        super(memberName, 10);

    }

    /**
     * increases the hunger of hungus unit
     * hungus units gets hungry less easily
     * @param amount amount of hunger level increased
     */
    @Override
    public void increaseHunger(int amount) {
        super.increaseHunger(amount / 2);
    }


    /**
     * decreases the hunger of hungus unit
     * @param amount amount of hunger level decreased
     */
    @Override
    public void decreaseHunger(int amount) {

        super.decreaseHunger(amount * 2);
    }

    /**
     * toString() method of hungus units
     * it is formatted such that it fits nicely in a table
     * @return String String representation of a hungus unit
     */
    @Override
    public String toString() {
        return String.format(super.toString(), "Hungus");
    }

}
