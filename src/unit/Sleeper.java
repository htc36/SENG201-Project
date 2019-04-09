package unit;

public class Sleeper extends CrewMember{

    public Sleeper(String memberName) {
        super(memberName, 10);
    }

    @Override
    public void sleep(int amount) {
        super.sleep(amount * 2);
    }

    @Override
    public String toString() {
        return String.format(super.toString(), "Sleeper");
    }

}
