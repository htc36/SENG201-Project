package random_events;

import java.util.ArrayList;
import java.util.Random;

import unit.CrewMember;

public class SpacePlague implements RandomEvents {
	
	public static void causeDamage(ArrayList<CrewMember> c) {
		
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
		
		SpacePlague.causeDamage(crews);


	}
	
	


}
