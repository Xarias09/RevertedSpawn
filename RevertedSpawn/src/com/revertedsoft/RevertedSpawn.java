package com.revertedsoft;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 
 * @author RevertedSoft
 * 
 * A spawn plugin that allows players to do the command /spawn to return
 * to the spawn point that can be set with /setspawn by OP's or by 
 * people with the RevertedSpawn.setspawn permission
 *
 */
public final class RevertedSpawn extends JavaPlugin{
    
    @Override
    public void onEnable(){
	getLogger().info("RevertedSpawn has been enabled!");
    }
 
    @Override
    public void onDisable(){
	getLogger().info("RevertedSpawn has been disabled!");
    }
    
    /**
     * 
     * @param sender origin of the command
     * @param cmd the actual command
     * @param label
     * @param args additional arguments sent to the command
     * 
     * @return true if the command was part of this plugin, else false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(cmd.getName().equalsIgnoreCase("spawn")) {
            
            /**
             * Make sure that the command was sent by a player
             */
            if(!(sender instanceof Player)) {
                sender.sendMessage("This command can only be run by a player.");
            } else {
                
                /**
                 * Move the player to the spawn
                 */
                Player player = (Player) sender;
                player.teleport(player.getWorld().getSpawnLocation());
            }
            
            return true;
        } else if(cmd.getName().equalsIgnoreCase("setspawn")) {
            
            /**
             * Make sure the command was sent by a player
             */
            if(!(sender instanceof Player)) {
                sender.sendMessage("This command can only be run by a player.");
            } else {
                
                /**
                 * Set the spawn to the player's current location
                 */
                Player player = (Player) sender;
                World world = player.getWorld();
                Location loc = player.getLocation();
                world.setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
                player.sendMessage(ChatColor.GREEN + "You have moved the spawn to the current location.");
            }
            
            return true;
        }
        
        return false;
    }
}
