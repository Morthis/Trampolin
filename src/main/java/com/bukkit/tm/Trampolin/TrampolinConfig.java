package com.bukkit.tm.Trampolin;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Trampolin for Bukkit
 * Get the Config Stuff done
 * @author Thomas Bucher 
 * @version 0.1
 *
 */
public class TrampolinConfig {
	//private static final String settingsFile = "Trampolin.yml";
	public static List<String> boosts = new ArrayList<String>();
	public static List<String> defaultboosts = new ArrayList<String>();
	public static boolean useredtorch;
	public static int nodamagetimeout;
	public static boolean nodamage;
	public static boolean playeronly;
	public static boolean usecmd;
	public static boolean boostminecarts;
        
	public static void initialize(FileConfiguration config , Trampolin plugin) {
	        //if(!dataFolder.exists()) {
	        //    dataFolder.mkdirs();
	        //}
	        defaultboosts.add("41,75,0");
	        defaultboosts.add("42,50,0");
	        defaultboosts.add("49,120,40");
	        //File configFile  = new File(dataFolder, settingsFile);
	        //BetterConfig config = new BetterConfig(configFile);
	        //config.load();
                config.options().copyDefaults(true);
	        boosts = config.getStringList("boosts");
	        useredtorch = config.getBoolean("useredtorch");
	        nodamagetimeout = config.getInt("nodamagetimeout");
	        nodamage = config.getBoolean("nodamage");
	        playeronly = config.getBoolean("playeronly");
	        usecmd = config.getBoolean("usecmd");
	        boostminecarts = config.getBoolean("boostminecarts");
	        plugin.saveConfig();
	        
	        for (String s: boosts) {
	        	try {
	        		String[] splits = s.split(",");
	        		Trampolin.getBoosts().add(new Boost( Material.getMaterial(Integer.valueOf(splits[0])),Integer.valueOf(splits[1]),Integer.valueOf(splits[2])));
	        	} catch (Exception e) {
					e.printStackTrace();
				}
	        }
	    }
}
