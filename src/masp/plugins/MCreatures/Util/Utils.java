package masp.plugins.MCreatures.Util;

import java.util.logging.Level;

import org.bukkit.Bukkit;

public class Utils {
	public void info(String s){
		Bukkit.getLogger().log(Level.INFO, "[MCreature] " + s);	
	}
	public void warning(String s){
		Bukkit.getLogger().log(Level.WARNING, "[MCreature] " + s);
	}

}
