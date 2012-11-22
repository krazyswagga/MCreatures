package masp.plugins.MCreatures;


import java.io.File;
import java.lang.reflect.Method;

import masp.plugins.MCreatures.API.MCreature;
import masp.plugins.MCreatures.Entities.MEntityCreeper;
import masp.plugins.MCreatures.Util.Utils;

import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;



public class MCreatures extends JavaPlugin {
	Utils util = new Utils();
	File pd = this.getDataFolder() ;
	public void onEnable() {
		util.info("Is now enabled");
		if(getServer().getPluginManager().getPlugin("MRPG") == null){
			util.info("MRPG is not found. Disabling MRPG aspects");
		}else{
			util.info("MRPG has been found -- Checking for effects");
		}
	}
	public void onDisable(){
		util.info("Is now disabled");
	}

	//Version 0.1 In-Dev
	public void createEntity(MCreature mc){
		replaceMethod(mc.getEntity());
		switch(mc.getEntity()){
		case ZOMBIE:
			MEntityCreeper.creatEntityCreeper(null);
			break;
		default:
			break;

		}
	}
	@SuppressWarnings("incomplete-switch")
	private void replaceMethod(EntityType type) {
		try{
			@SuppressWarnings("rawtypes")
			Class[] args = new Class[3];
			args[0] = Class.class;
			args[1] = String.class;
			args[2] = int.class;

			Method a = net.minecraft.server.EntityTypes.class.getDeclaredMethod("a", args);
			a.setAccessible(true);

			switch(type) {
			case CREEPER:
				a.invoke(a, MEntityCreeper.class, "Creeper", 50);
				break;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}