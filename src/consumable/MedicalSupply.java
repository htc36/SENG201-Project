package consumable;

import java.util.ArrayList;

public abstract class MedicalSupply extends Consumable {

    /**
     * Can the medical supply heal space plague
     */
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
     * Returns if the item can heal space plague
     * @return boolean true if it can, false otherwise
     */
    public boolean canHealSpacePlague() {
        return healsSpacePlague;
    }

    /**
     * Returns the stats of a consumable as ArrayList of String
     * @return ArrayList Status of consumable
     */
    public ArrayList<String> getConsumableStats() {
        ArrayList<String> template = super.getConsumableStats();
        template.add("[Meds]");
        if (healsSpacePlague) 
            template.add("T");
        else
            template.add("F");
        return template;
    }

}
