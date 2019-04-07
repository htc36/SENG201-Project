package unit;

public class Hungus extends CrewMember{
	
	public Hungus(String memberName, int luckStat) {
		super(memberName, 10);
		
	}
	
	@Override
	public void increaseHunger(int amount) {
        super.increaseHunger(amount / 2);
    }
	
	
	@Override
	public void decreaseHunger(int amount) {
    	
    	super.decreaseHunger(amount * 2);
	}
	
    	
	
	
	

}
