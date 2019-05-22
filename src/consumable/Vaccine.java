package consumable;

/**
 * Defines Medical Supply - Vaccine, can be consumed to cure space plague
 */
public class Vaccine extends MedicalSupply {

    /**
     * Constructor for Vaccine MedicalSupply.
     * Vaccines heals SpacePlague
     */
    public Vaccine() {
        super("Vaccine", 0, 20, true);
    }

}
