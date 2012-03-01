package com.bukkit.tm.Trampolin;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TrampolinConfigCommand implements CommandExecutor {
    @SuppressWarnings("unused")
	private final Trampolin plugin;
    public TrampolinConfigCommand(Trampolin plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
		if (!TrampolinConfig.usecmd) { 
			 player.sendMessage("Use of Trampolincmd is disabled");
			 return true;
		}
        if (split.length == 0) {
            player.sendMessage("use: /trampolin blockid boost verticalboost");
            return true;
        } else if (split.length == 3) {
            try {
                int blockid = Integer.parseInt(split[0]);
                int boost = Integer.parseInt(split[1]);
                int vertical = Integer.parseInt(split[2]);
                for (Boost b: Trampolin.boosts) {
                	if (b.getMat().getId() == blockid) {
                		b.setBoost(boost);
                		b.setVertboost(vertical);
                		  player.sendMessage("Updated Block with ID "+blockid);
                		return true;
                	}
                }
                Trampolin.boosts.add(new Boost(Material.getMaterial(blockid), boost, vertical));
                player.sendMessage("Added Block with ID "+blockid);
            } catch (NumberFormatException ex) {
                player.sendMessage("use: /trampolin blockid boost verticalboost");
            }
            return true;
        } else {
            return false;
        }
    }
    
}
