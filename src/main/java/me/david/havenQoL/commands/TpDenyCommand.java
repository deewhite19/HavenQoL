package me.david.havenQoL.commands;

import me.david.havenQoL.HavenQoL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TpDenyCommand implements CommandExecutor {
    private final HavenQoL plugin;

    public TpDenyCommand(HavenQoL plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Must be a player to use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!(plugin.getTpaRequests().containsKey(player.getUniqueId()))) {
            player.sendMessage("You have no pending TPA requests.");
            return true;
        }

        UUID requestorUUID = plugin.getTpaRequests().get(player.getUniqueId());
        Player requestor = Bukkit.getPlayer(requestorUUID);

        if (requestor == null) {
            plugin.getTpaRequests().remove(player.getUniqueId());
            player.sendMessage("Request has expired.");
            return true;
        }

        player.sendMessage("Teleport request denied.");
        requestor.sendMessage("Your TPA request to " + player.getName() + " has been denied.");

        plugin.getTpaRequests().remove(player.getUniqueId());

        return true;
    }
}
