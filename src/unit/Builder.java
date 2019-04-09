package unit;

public class Builder extends CrewMember{

    public Builder(String memberName) {
        super(memberName, 10);
    }

    @Override
    public void repairShield(Spaceship s, int amount) {
        super.repairShield(s, amount * 2);
    }

    @Override
    public String toString() {
        return String.format(super.toString(), "Builder");
    }

}
