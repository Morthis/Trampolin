/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bukkit.tm.Trampolin;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.PoweredMinecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.util.Vector;

/**
 *
 * @author PIETER
 */
public class TrampolinListener implements Listener {
    
    public static Trampolin plugin;
    
    public TrampolinListener(Trampolin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        //System.out.print("Found Damage");
        if (!TrampolinConfig.nodamage) return;
        //System.out.print("No Damage Enabled");
        if (event.getCause().equals(DamageCause.FALL)) 
        {
                //System.out.print("Its Fall damage");
                if (!plugin.doDamage(event.getEntity().getEntityId())){
                        //System.out.print("We cancel this event...");
                        event.setCancelled(true);
                }
        }
    }
    
    @EventHandler
    public void onBlockRedstoneChange (BlockRedstoneEvent event){
        if (event.getBlock().getType()==Material.getMaterial(70)){
                for (Boost boost:Trampolin.getBoosts() ) {
                        if (event.getBlock().getRelative(BlockFace.DOWN).getType()==boost.getMat()){
                                if (event.getBlock().getBlockPower()==0){
                                        Location l;
                                        l=event.getBlock().getLocation();
                                        if (TrampolinConfig.playeronly) {
                                                for (Player p:event.getBlock().getWorld().getPlayers()){
                                                        if (l.toVector().distance(p.getLocation().toVector()) < 2.5){
                                                                if (boost.getVertboost() > 0) {
                                                                        p.setVelocity(p.getVelocity().setX(0).setZ(0).add(new Vector(p.getLocation().getDirection().getX() * boost.getVertboost(),0,p.getLocation().getDirection().getZ() * boost.getVertboost())));
                                                                }
                                                                if (event.getBlock().getRelative(BlockFace.DOWN).getType()==boost.getMat()) p.setVelocity(p.getVelocity().setY(0).add(new Vector(0,boost.getBoost(),0)));
                                                                event.getBlock().setData((byte) 0);
                                                                event.setNewCurrent(event.getOldCurrent());
                                                                if (TrampolinConfig.nodamage) {
                                                                        plugin.addJumper(p.getEntityId());	
                                                                }
                                                        }
                                                }
                                        } else {
                                                for (LivingEntity p:event.getBlock().getWorld().getLivingEntities()){
                                                        Location pl = p.getLocation();
                                                        pl.setY(pl.getY()+1);
                                                        if (l.toVector().distance(pl.toVector()) < 2){
                                                                if (boost.getVertboost() > 0) {
                                                                        p.setVelocity(p.getVelocity().setX(0).setZ(0).add(new Vector(p.getLocation().getDirection().getX() * boost.getVertboost(),0,p.getLocation().getDirection().getZ() * boost.getVertboost())));
                                                                }
                                                                if (event.getBlock().getRelative(BlockFace.DOWN).getType()==boost.getMat()) p.setVelocity(p.getVelocity().setY(0).add(new Vector(0,boost.getBoost(),0)));
                                                                event.getBlock().setData((byte) 0);
                                                                event.setNewCurrent(event.getOldCurrent());
                                                                if (TrampolinConfig.nodamage) {
                                                                        plugin.addJumper(p.getEntityId());	
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }
    
    @EventHandler
    public void onVehicleMove(VehicleMoveEvent event) {
        if(!(event.getVehicle() instanceof Minecart || event.getVehicle() instanceof PoweredMinecart || event.getVehicle() instanceof StorageMinecart))
        {
            return;
        }
        if (!TrampolinConfig.boostminecarts) {
            return;
        }
        Minecart cart 	= (Minecart)(event.getVehicle());
        Chunk current 	= cart.getWorld().getChunkAt(cart.getLocation()); 
        Block onblock 	= current.getBlock(cart.getLocation().getBlockX(), cart.getLocation().getBlockY(), cart.getLocation().getBlockZ());
        if (onblock.getType() == Material.STONE_PLATE) {
            for (Boost boost:Trampolin.getBoosts() ) {
                if (onblock.getRelative(BlockFace.DOWN).getType()==boost.getMat()){
                     if (boost.getVertboost() > 0) {
                         cart.setVelocity(cart.getVelocity().setX(0).setZ(0).add(new Vector(cart.getLocation().getDirection().getX() * boost.getVertboost(),0,cart.getLocation().getDirection().getZ() * boost.getVertboost())));
                     }
                     if (onblock.getRelative(BlockFace.DOWN).getType()==boost.getMat()) cart.setVelocity(cart.getVelocity().setY(0).add(new Vector(0,boost.getBoost(),0)));
                            if (TrampolinConfig.nodamage) {
                                    plugin.addJumper(cart.getEntityId());	
                                    }
                            }
                    }
        }
    }
}
