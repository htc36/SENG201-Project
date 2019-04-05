package unit;

public class Medic extends CrewMember {

	public Medic(String memberName, int luckStat) {
		super(memberName, 10);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public void reduceHealth(int amount) {
		int health = super.getHealth();
        health -= (amount / 2);
        if (health < 0) {
            health = 0;
        }
        super.setHealth(health);
    }
}