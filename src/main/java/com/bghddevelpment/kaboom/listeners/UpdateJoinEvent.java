package com.bghddevelpment.kaboom.listeners;

import com.bghddevelpment.kaboom.Kaboom;
import com.bghddevelpment.kaboom.utilities.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateJoinEvent implements Listener {
	private final Kaboom plugin;

    public UpdateJoinEvent(Kaboom plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (getPlugin().getConfig().getBoolean("Update.Enabled", true)) {
            if (player.hasPermission("kaboom.update")) {
                if (getPlugin().getConfig().getBoolean("CheckForUpdates.Enabled", true)) {
                    new UpdateChecker(getPlugin(), 46678).getLatestVersion(version -> {
                        if (!getPlugin().getDescription().getVersion().equalsIgnoreCase(version)) {
                            player.sendMessage(ChatColor.GRAY + "=========================");
                            player.sendMessage(ChatColor.RED + "Kaboom is outdated!");
                            player.sendMessage(ChatColor.GREEN + "Newest version: " + version);
                            player.sendMessage(ChatColor.RED + "Your version: " + getPlugin().getDescription().getVersion());
                            player.sendMessage(ChatColor.GRAY + "=========================");
                        }
                    });
                }
            }
        }
    }

    private Kaboom getPlugin() {
        return plugin;
    }

}
