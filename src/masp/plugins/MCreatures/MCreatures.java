package masp.plugins.MCreatures;


import masp.plugins.MCreatures.API.MCreature;
import masp.plugins.MCreatures.Entities.MEntityZombie;


public class MCreatures {

	//Version 0.1 In-Dev
	public void createEntity(MCreature mc){
		switch(mc.getEntity()){
		case ZOMBIE:
			MEntityZombie.setAPIMob(mc);
			break;
		default:
			break;

		}
	}
	//Leaving this for 0.2 In-Dev 
	public void createComplexEntity(){

	}	
}
