package unit;

public class Hungus extends CrewMember{
	
	public Hungus(String memberName) {
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
	
	@Override
    public String toString() {
        String status = "F";
        if (super.isSick()) {
            status = "T";
        }

        String template = "%12.12s"; // name
        template += "%11.11s"; //type
        template += "%7d"; // health stat
        template += "%6d"; // luck stat
        template += "%8.1s"; // has plague
        template += "%7d"; // hunger level
        template += "%8d"; // fatique level
        template += "%8d"; // actions

        return String.format(template, super.getName(), "Hungus", super.getHealth(),
                super.getLuck(), status, super.getHunger(), super.getFatiqueLevel(), super.getActions());
    }
	
    	
	
	
	

}
