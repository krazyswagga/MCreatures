package masp.plugins.MCreatures.API;

import org.bukkit.entity.LivingEntity;

public class MCreature {
	public MCreature(String name, int health, LivingEntity le){
		this.Name = name;
		this.Health = health;
		this.LE = le;
	}
	private String Name;
	private int Health;
	private LivingEntity LE;
	
	public int getHealth() {
		return Health;
	}
	public String getName() {
		return Name;
	}
	public LivingEntity getLivingEntity() {
		return LE;
	}
}
