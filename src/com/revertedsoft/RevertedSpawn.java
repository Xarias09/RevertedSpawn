//Copyright (c) 2012 RevertedSoft <revertedsoft.com>
//
//Permission is hereby granted, free of charge, to any person obtaining a copy 
//of this software and associated documentation file (the "Software"), to deal 
//with the Software without limitaion in the rights to use, copy, modify, merge 
//publish, distribute, but NOT to sell copies of the Software, subject to the 
//following condition:
//
//The above copyright notice and this permission notice shall be included in all 
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
//SOFTWARE.

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
 * @version 0.2
 * @since 2012-12-29
 * @see http://www.revertedsoft.com/bukkit-plugins.html for for info
 * 
 * A spawn plugin that allows players to do the command /spawn to return
 * to the spawn point that can be set with /setspawn (should only be given
 * to staff)
 * 
 * Permissions:
 * RevertedSpawn.spawn      - Allows use of /spawn command
 * RevertedSpawn.setspawn   - Allows use of /setspawn command
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
                sender.sendMessage(ChatColor.RED + "This command can only be run by a player.");
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
                sender.sendMessage(ChatColor.RED + "This command can only be run by a player.");
            } else {
                Player player = (Player) sender;
                World world = player.getWorld();
                Location loc = player.getLocation();
                
                /**
                 * Change the world spawn location to the current position of the player
                 */
                world.setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
                sender.sendMessage(ChatColor.GREEN + "You have moved the spawn to your current location.");
            }
            
            return true;
        }
        
        return false;
    }
}
