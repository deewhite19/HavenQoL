package me.david.havenQoL;

import me.david.havenQoL.commands.*;
import me.david.havenQoL.listeners.StatsGUIListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Location;
import java.util.HashMap;
import java.util.UUID;
import me.david.havenQoL.listeners.DeathCoordinatesListener;


public final class HavenQoL extends JavaPlugin {

    private final HashMap<UUID, Location> homes = new HashMap<>();
    private final HashMap<UUID, UUID> tpaRequests = new HashMap<>();
    public HashMap<UUID, UUID> getTpaRequests(){
        return tpaRequests;
    }
    public HashMap<UUID, Location> getHomes(){
        return homes;
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new DeathCoordinatesListener(), this);
        getServer().getPluginManager().registerEvents(new StatsGUIListener(), this);

        getCommand("sethome").setExecutor(new SetHomeCommand(this));
        getCommand("home").setExecutor(new HomeCommand(this));
        getCommand("tpa").setExecutor(new TpaCommand(this));
        getCommand("tpaccept").setExecutor(new TpAcceptCommand(this));
        getCommand("tpdeny").setExecutor(new TpDenyCommand(this));
        getCommand("playerstats").setExecutor(new PlayerStatsCommand());

        }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
