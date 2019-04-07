package random_events;

import crew.Crew;

public class AlienPirates implements RandomEvents{
	
	public void causeDamage(Crew c) {
		c.popRandomItem();
		
	}

}
