package unit;

public class Explorer extends CrewMember {

    public Explorer(String memberName) {
        super(memberName, 25);
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

        return String.format(template, super.getName(), "Explorer", super.getHealth(),
                super.getLuck(), status, super.getHunger(), super.getFatiqueLevel(), super.getActions());
    }

}
