package unit;

public class Sleeper extends CrewMember{
	
	public Sleeper(String memberName, int luckStat) {
        super(memberName, 10);
    }
	
	@Override
	public void sleep(int amount) {
        super.sleep(amount * 2);
    }

}
