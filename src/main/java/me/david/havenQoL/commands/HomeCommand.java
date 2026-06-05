package me.david.havenQoL.commands;
import me.david.havenQoL.HavenQoL;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor{
    private final HavenQoL plugin;

    public HomeCommand(HavenQoL plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)){
            sender.sendMessage("Must be a player to use this command.");
            return true;
        }

        Player player = (Player) sender;

        Location home = plugin.getHomes().get(player.getUniqueId());

        if (home == null){
            player.sendMessage("No home has been set.");
            return true;
        }

        player.teleport(home);

        player.sendMessage("Teleported home!");

        return true;
    }
}
