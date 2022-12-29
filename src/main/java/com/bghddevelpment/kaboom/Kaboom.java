package com.bghddevelpment.kaboom;

import com.bghddevelpment.kaboom.commands.KaboomCommand;
import com.bghddevelpment.kaboom.listeners.UpdateJoinEvent;
import com.bghddevelpment.kaboom.utilities.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Kaboom extends JavaPlugin {

    public void onEnable() {
        final String version = this.getDescription().getVersion();

        this.getLogger().info(String.format("Kaboom v%s starting ...", version));
        this.saveDefaultConfig();
        this.reloadConfig();

        this.getLogger().info(String.format("Kaboom v%s loading commands ...", version));
        this.getCommand("kaboom").setExecutor(new KaboomCommand(this));

        this.getLogger().info(String.format("Kaboom v%s loading events ...", version));
        registerEvents(this, new UpdateJoinEvent(this));

        this.getLogger().info(String.format("Kaboom v%s started ...", version));

        if (getConfig().getBoolean("CheckForUpdates.Enabled", true)) {
            new UpdateChecker(this, 46678).getLatestVersion(remoteVersion -> {
                getLogger().info("Checking for Updates ...");

                if (getDescription().getVersion().equalsIgnoreCase(remoteVersion)) {
                    getLogger().info("No new version available");
                } else {
                    getLogger().warning(String.format("Newest version: %s it out! You are running version: %s", remoteVersion, getDescription().getVersion()));
                    getLogger().warning("Please Update Here: http://www.spigotmc.org/resources/46678");
                }
            });
        }
    }

    private void registerEvents(final Plugin plugin, final Listener... listeners) {
        for (final Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

}
