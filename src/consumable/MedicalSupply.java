package consumable;
public class MedicalSupply extends Consumable {

    private boolean healsSpacePlague;
    public MedicalSupply(String name, int healingAmount, int price, boolean healsPlague) {
        super(name, healingAmount, price);
        healsSpacePlague = healsPlague;
    }

    public boolean canHealSpacePlague() {
        return healsSpacePlague;
    }

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
