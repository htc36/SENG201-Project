package random_events;

import crew.Crew;

public class AlienPirates implements RandomEvents{
	
	public void cause_damage(Crew c) {
		c.popRandomItem();
		
	}

}
