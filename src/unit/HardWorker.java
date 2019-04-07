package unit;

public class HardWorker extends CrewMember{
	
	public HardWorker(String memberName, int luckStat, int actions) {
		super(memberName, luckStat, 3);
	}
	
	@Override
	public void refreshActions(int amount) {
		super.refreshActions(3);
		
	}
	

}
