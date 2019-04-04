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

}
