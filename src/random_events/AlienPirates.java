package random_events;

import crew.Crew;

public class AlienPirates implements RandomEvents{
	
	public static void causeDamage(Crew c) {
		c.popRandomItem();
		
	}

}
