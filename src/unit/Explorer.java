package unit;

public class Explorer extends CrewMember {

    public Explorer(String memberName) {
        super(memberName, 25);
    }

    @Override
    public String toString() {
        return String.format(super.toString(), "Explorer");
    }

}
