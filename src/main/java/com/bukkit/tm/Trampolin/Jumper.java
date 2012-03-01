package com.bukkit.tm.Trampolin;

import java.util.Date;

public class Jumper {
	private int jumperid;
	private long lastjump;
	
	public void setLastjump() {
		this.lastjump = new Date().getTime();
	}
	
	public Jumper(int playerid) {
		this.jumperid = playerid;
	}
	
	public boolean isJumper(int playerid) {
		if (playerid == this.jumperid) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean getDamage() {
		if ((this.lastjump + TrampolinConfig.nodamagetimeout ) >  new Date().getTime()) 
		{
			return false;
		} else {
			return true;
		}
		
	}
}
