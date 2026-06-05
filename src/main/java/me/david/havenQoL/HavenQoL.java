package me.david.havenQoL;

import me.david.havenQoL.listeners.DeathCoordinatesListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class HavenQoL extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(
                new DeathCoordinatesListener(),
                this
        );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
