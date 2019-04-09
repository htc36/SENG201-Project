package unit;

public class Explorer extends CrewMember {

    /**
     * <<auto generated javadoc comment>>
     * @param memberName <<Param Desc>>
     */
    public Explorer(String memberName) {
        super(memberName, 25);
    }

    /**
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    @Override
    public String toString() {
        return String.format(super.toString(), "Explorer");
    }

}
