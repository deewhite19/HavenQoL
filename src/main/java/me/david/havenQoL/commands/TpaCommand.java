package me.david.havenQoL.commands;

import me.david.havenQoL.HavenQoL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TpaCommand implements CommandExecutor {

    private final HavenQoL plugin;

    public TpaCommand(HavenQoL plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Must be a player to use this command.");
            return true;
        }

        Player player = (Player) sender;

        if(args.length != 1){
            sender.sendMessage("Usage: /tpa <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if(target == null){
            sender.sendMessage("Player not found.");
            return true;
        }

        if(target.equals(player)){
            sender.sendMessage("Cannot TPA to yourself.");
            return true;
        }

        if(plugin.getTpaRequests().containsKey(target.getUniqueId())){
            player.sendMessage(player.getName() + " already has a pending TPA request.");
            return true;
        }

        plugin.getTpaRequests().put(target.getUniqueId(), player.getUniqueId());
        player.sendMessage("TPA request sent.");
        target.sendMessage(player.getName() + " has sent you a TPA request.");
        target.sendMessage("Type /tpaccept to accept.");

        Bukkit.getScheduler().runTaskLater(plugin, () -> {

            UUID storedRequester = plugin.getTpaRequests().get(target.getUniqueId());

            if (storedRequester == null) {
                return;
            }

            if (!storedRequester.equals(player.getUniqueId())) {
                return;
            }

            plugin.getTpaRequests().remove(target.getUniqueId());

            player.sendMessage("Your TPA request to " + target.getName() + " has expired.");
            target.sendMessage("The TPA request from " + player.getName() + " has expired.");

        }, 20L * 60);

        return true;
    }
}
