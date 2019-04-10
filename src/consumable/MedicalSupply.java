package consumable;
public class MedicalSupply extends Consumable {

    private boolean healsSpacePlague;

    /**
     * Constructor for a MedicalSupply
     * @param name name of the item
     * @param healingAmount health it increases when consumed
     * @param price price of the item
     * @param healsPlague can the item heal space plague when consumed
     */
    public MedicalSupply(String name, int healingAmount, int price, boolean healsPlague) {
        super(name, healingAmount, price);
        healsSpacePlague = healsPlague;
    }

    /**
     * @return boolean true if it can heal space plague, 
     * false otherwise
     */
    public boolean canHealSpacePlague() {
        return healsSpacePlague;
    }

    /**
     * string representation of a MedicalSupply
     * it is formatted such that it fits nicely in a table
     * @return String string representation of a MedicalSupply
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
