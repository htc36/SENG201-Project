package consumable;
public class MedicalSupply extends Consumable {

    private boolean healsSpacePlague;
    /**
     * <<auto generated javadoc comment>>
     * @param name <<Param Desc>>
     * @param healingAmount <<Param Desc>>
     * @param price <<Param Desc>>
     * @param healsPlague <<Param Desc>>
     */
    public MedicalSupply(String name, int healingAmount, int price, boolean healsPlague) {
        super(name, healingAmount, price);
        healsSpacePlague = healsPlague;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return boolean <<Return Desc>>
     */
    public boolean canHealSpacePlague() {
        return healsSpacePlague;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    public String toString() {
        String effect = "F";
        if (healsSpacePlague) {
            effect = "T";
        }

        String template = "[Meds] %12.12s"; // name
        template += "%6d"; // price
        template += "%5d"; // heal
        template += "%18s"; // cures plague

        return String.format(template, super.getName(), super.getPrice(),
                super.getHealingAmount(), effect);
    }

}
