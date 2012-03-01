package com.bukkit.tm.Trampolin;

import org.bukkit.Material;

public class Boost {
	private Material mat;
	private float boost;
	private float vertboost;
	public Material getMat() {
		return mat;
	}
	public void setMat(Material mat) {
		this.mat = mat;
	}
	public float getBoost() {
		return boost;
	}
	public void setBoost(int boost) {
		this.boost = ((float)boost) / 100;
	}
	public float getVertboost() {
		return vertboost;
	}
	public void setVertboost(int vertboost) {
		this.vertboost = ((float)vertboost) / 1000;
	}
	public Boost(Material m, int  i, int v) {
		mat = m;
		boost =  ((float)i) / 100;
		vertboost = ((float)v) / 1000;
	}

}
