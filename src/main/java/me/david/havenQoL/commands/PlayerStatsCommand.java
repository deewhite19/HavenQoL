package me.david.havenQoL.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class PlayerStatsCommand implements CommandExecutor {

    private ItemStack createStatItem(Material material, String name, String value){
        ItemStack item = new ItemStack(material);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(List.of(value));

        item.setItemMeta(meta);
        return item;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)){
            sender.sendMessage("Must be a player to use this command.");
            return true;
        }

        Player player = (Player) sender;

        Inventory playerStats = Bukkit.createInventory(null, 27, "Your Statistics");

        double playtimeHours = player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 72000.0;

        String playtimeText = String.format("%.1f Hours", playtimeHours);
        playerStats.setItem(10, createStatItem(
                Material.CLOCK,
                "Playtime",
                playtimeText
        ));
        playerStats.setItem(11, createStatItem(
                Material.SKELETON_SKULL,
                "Deaths",
                String.valueOf(player.getStatistic(Statistic.DEATHS))
        ));
        playerStats.setItem(12, createStatItem(
                Material.IRON_SWORD,
                "Mob Kills",
                String.valueOf(player.getStatistic(Statistic.MOB_KILLS))
        ));
        playerStats.setItem(13, createStatItem(
                Material.DIAMOND_SWORD,
                "Player Kills",
                String.valueOf(player.getStatistic(Statistic.PLAYER_KILLS))
        ));

        double distanceKm = player.getStatistic(Statistic.WALK_ONE_CM) / 100000.0;

        String distanceText = String.format("%.2f km", distanceKm);

        playerStats.setItem(14, createStatItem(
                Material.LEATHER_BOOTS,
                "Distance Walked",
                distanceText
        ));
        playerStats.setItem(15, createStatItem(
                Material.SLIME_BLOCK,
                "Jumps",
                String.valueOf(player.getStatistic(Statistic.JUMP))
        ));
        playerStats.setItem(16, createStatItem(
                Material.RED_BED,
                "Times Slept",
                String.valueOf(player.getStatistic(Statistic.SLEEP_IN_BED))
        ));

        player.openInventory(playerStats);

        return true;
    }
}
