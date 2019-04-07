package unit;

public class Medic extends CrewMember {

    public Medic(String memberName, int luckStat) {
        super(memberName, 10);
    }

    @Override
    public void reduceHealth(int amount) {
        super.reduceHealth(amount / 2);
    }
}
