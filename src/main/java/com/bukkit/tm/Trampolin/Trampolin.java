package com.bukkit.tm.Trampolin;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Trampolin extends JavaPlugin {
	//private final TrampolinBlockListener blocklistener=new TrampolinBlockListener(this);
	//private final TrampolinEntityListener entitylistener=new TrampolinEntityListener(this);
	//private final TramoplinVehicleListener vehiclelistener=new TramoplinVehicleListener(this);
        private final TrampolinListener listener = new TrampolinListener(this);
	public static List<Boost> boosts = new ArrayList<Boost>();
	public static List<Jumper> jumpers = new ArrayList<Jumper>();
	
	public static List<Boost> getBoosts() {
		return boosts;
	}

        @Override
	public void onEnable (){
		PluginManager pm=getServer().getPluginManager();
		TrampolinConfig.initialize(this.getConfig(), this);
                
                pm.registerEvents(listener, this);
		//pm.registerEvent(Event.Type.REDSTONE_CHANGE, blocklistener,Event.Priority.Normal,this);
		//pm.registerEvent(Event.Type.ENTITY_DAMAGE, entitylistener, Event.Priority.Normal, this);
		//if (TrampolinConfig.boostminecarts) pm.registerEvent(Event.Type.VEHICLE_MOVE, vehiclelistener, Event.Priority.Normal, this);
		getCommand("trampolin").setExecutor(new TrampolinConfigCommand(this));
	}
	
        @Override
	public void onDisable (){
		
	}	
	
	public void addJumper(int jumperid) 
	{
		// Update existing
		for (Jumper p:jumpers) {
			if (p.isJumper(jumperid)) {
				p.setLastjump();
				return;
			}
		}
		// New jumper
		jumpers.add(new Jumper(jumperid));
	}
	
	public boolean doDamage(int jumperid)
	{
		// Check if this Jumper gets damage
		for (Jumper p:jumpers) {
			if (p.isJumper(jumperid)) {
				return p.getDamage();
			}
		}
		return true;
	}
        
        public void loadConfig(FileConfiguration config) {
            config.options().copyDefaults(true);
            
        }
}
