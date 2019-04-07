package unit;

public class HardWorker extends CrewMember{
	
	public HardWorker(String memberName) {
		super(memberName, 10, 3);
	}
	
	@Override
	public void refreshActions(int amount) {
		super.refreshActions(3);
		
	}
	

}
