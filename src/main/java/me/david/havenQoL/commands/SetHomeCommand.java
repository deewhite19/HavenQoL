package me.david.havenQoL.commands;
import me.david.havenQoL.HavenQoL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor{
    private final HavenQoL plugin;

    public SetHomeCommand(HavenQoL plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)){
            sender.sendMessage("Must be a player to use this command.");
            return true;
        }

        Player player = (Player) sender;

        plugin.getHomes().put(player.getUniqueId(), player.getLocation());

        player.sendMessage("Home set!");

        return true;
    }
}
