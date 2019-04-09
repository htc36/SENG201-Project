package unit;

public class HardWorker extends CrewMember{

    public HardWorker(String memberName) {
        super(memberName, 10, 3);
    }

    @Override
    public void refreshActions(int amount) {
        super.refreshActions(3);

    }

    @Override
    public String toString() {
        String status = "F";
        if (super.isSick()) {
            status = "T";
        }

        String template = "%12.12s"; // name
        template += "%11.11s"; //type
        template += "%9d"; // health stat
        template += "%7d"; // luck stat
        template += "%10.1s"; // has plague
        template += "%9d"; // hunger level
        template += "%10d"; // fatique level
        template += "%10d"; // actions

        return String.format(template, super.getName(), "HardWorker", super.getHealth(),
                super.getLuck(), status, super.getHunger(), super.getFatiqueLevel(), super.getActions());
    }


}
