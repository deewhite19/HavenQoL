package me.david.havenQoL;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Location;
import java.util.HashMap;
import java.util.UUID;
import me.david.havenQoL.listeners.DeathCoordinatesListener;
import me.david.havenQoL.commands.SetHomeCommand;
import me.david.havenQoL.commands.HomeCommand;


public final class HavenQoL extends JavaPlugin {

    private final HashMap<UUID, Location> homes = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(
                new DeathCoordinatesListener(),
                this
        );

        getCommand("sethome").setExecutor(new SetHomeCommand(this));
        getCommand("home").setExecutor(new HomeCommand(this));

        }

    public HashMap<UUID, Location> getHomes(){
        return homes;
        }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
