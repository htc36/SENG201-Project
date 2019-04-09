package unit;

public class HardWorker extends CrewMember{

    /**
     * <<auto generated javadoc comment>>
     * @param memberName <<Param Desc>>
     */
    public HardWorker(String memberName) {
        super(memberName, 10, 3);
    }

    /**
     * <<auto generated javadoc comment>>
     * @param amount <<Param Desc>>
     */
    @Override
    public void refreshActions(int amount) {
        super.refreshActions(3);

    }

    /**
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    @Override
    public String toString() {
        return String.format(super.toString(), "HardWorker");
    }

}
