package me.david.havenQoL.commands;

import me.david.havenQoL.HavenQoL;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TpAcceptCommand implements CommandExecutor {
    private final HavenQoL plugin;

    public TpAcceptCommand(HavenQoL plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Must be a player to use this command.");
            return true;
        }

        Player player = (Player) sender;

        if(!(plugin.getTpaRequests().containsKey(player.getUniqueId()))){
            player.sendMessage("No pending TPA requests to accept.");
            return true;
        }

        UUID requestorUUID = plugin.getTpaRequests().get(player.getUniqueId());
        Player requestor = Bukkit.getPlayer(requestorUUID);

        if(requestor == null){
            player.sendMessage("Request has expired.");
            plugin.getTpaRequests().remove(player.getUniqueId());
            return true;
        }

        Location playerLocation = player.getLocation();
        requestor.teleport(playerLocation);
        plugin.getTpaRequests().remove(player.getUniqueId());
        requestor.sendMessage("Teleport request accepted.");
        player.sendMessage("Accepted teleport request from " + requestor.getName() + ".");

        return true;
    }
}
