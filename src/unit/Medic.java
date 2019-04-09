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
        String status = "F";
        if (super.isSick()) {
            status = "T";
        }

        String template = "%12.12s"; // name
        template += "%11.11s"; //type
        template += "%7d"; // health stat
        template += "%5d"; // luck stat
        template += "%8.1s"; // has plague
        template += "%7d"; // hunger level
        template += "%8d"; // fatique level
        template += "%8d"; // actions

        return String.format(template, super.getName(), "Medic", super.getHealth(),
                super.getLuck(), status, super.getHunger(), super.getFatiqueLevel(), super.getActions());
    }
}
