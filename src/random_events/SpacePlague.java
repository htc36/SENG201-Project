package random_events;

import java.util.ArrayList;
import java.util.Random;

import crew.Crew;
import unit.CrewMember;
import unit.Spaceship;


public class SpacePlague implements RandomEvents {
	
	public static void causeDamage(Crew cr) {
		ArrayList<CrewMember> c = cr.getCrewMembers();
		Random rand = new Random();
		int index = rand.nextInt(c.size());
		ArrayList<Integer> indexs = new ArrayList<>();
		
		while (!indexs.contains(index)) {
			indexs.add(index);
			c.get(index).makeSick();
			c.get(index).reduceHealth(10);
			index = rand.nextInt(c.size());
		}
		
	}
	
	public static void main(String[] args) {
		ArrayList<CrewMember> crews = new ArrayList<>();
		crews.add(new CrewMember("Walter", 0));
		crews.add(new CrewMember("Richard", 100));
		crews.add(new CrewMember("Matthias", 50));
		crews.add(new CrewMember("Adrian", 10));
		
		Spaceship s = new Spaceship("DeathStar");
		Crew c = new Crew(crews, s);
		
		SpacePlague.causeDamage(c);


	}
	
	


}
