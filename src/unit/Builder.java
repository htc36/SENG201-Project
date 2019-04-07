package unit;

public class Builder extends CrewMember{
	
	public Builder(String memberName) {
		super(memberName, 10);
	}
	
	@Override
	public void repairShield(Spaceship s, int amount) {
        super.repairShield(s, amount * 2);
	}
	
	
	
	

}
