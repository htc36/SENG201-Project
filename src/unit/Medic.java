package unit;

public class Medic extends CrewMember {

    public Medic(String memberName) {
        super(memberName, 10);
    }
    @Override
    public void makeSick() {
        return ;
    }

    @Override
    public void reduceHealth(int amount) {
        super.reduceHealth(amount / 2);
    }

    @Override
    public String toString() {
        return String.format(super.toString(), "Medic");
    }

}
