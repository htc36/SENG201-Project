package consumable;

/**
 * Defines MedicalSupply - Vaccine, can be consumed to increase health and decrease hunger
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
