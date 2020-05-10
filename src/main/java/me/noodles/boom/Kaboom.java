package me.noodles.boom;

import me.noodles.boom.commands.KaboomCommand;
import me.noodles.boom.listeners.UpdateJoinEvent;
import me.noodles.boom.utilities.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Kaboom extends JavaPlugin {
    public static Kaboom plugin;
    private UpdateChecker checker;

    public void onEnable() {
        Kaboom.plugin = this;
        final String version = this.getDescription().getVersion();

        this.getLogger().info(String.format("Kaboom v%s starting ...", version));
        this.saveDefaultConfig();
        this.reloadConfig();

        this.getLogger().info(String.format("Kaboom v%s loading commands ...", version));
        this.getCommand("kaboom").setExecutor((CommandExecutor)new KaboomCommand());

        this.getLogger().info(String.format("Kaboom v%s loading events ...", version));
        registerEvents((Plugin)this, new UpdateJoinEvent());

        this.getLogger().info(String.format("Kaboom v%s started ...", version));
        this.checker = new UpdateChecker(this);
        if (this.checker.isConnected()) {
            if (this.checker.hasUpdate()) {
                getServer().getConsoleSender().sendMessage("------------------------");
                getServer().getConsoleSender().sendMessage("Kaboom is outdated!");
                getServer().getConsoleSender().sendMessage("Newest version: " + this.checker.getLatestVersion());
                getServer().getConsoleSender().sendMessage("Your version: " + Kaboom.plugin.getDescription().getVersion());
                getServer().getConsoleSender().sendMessage("Please Update Here: https://www.spigotmc.org/resources/22841");
                getServer().getConsoleSender().sendMessage("------------------------");
            }
            else {
                getServer().getConsoleSender().sendMessage("------------------------");
                getServer().getConsoleSender().sendMessage("Kaboom  is up to date!");
                getServer().getConsoleSender().sendMessage("------------------------");            }
        }
    }

    public static void registerEvents(final Plugin plugin, final Listener... listeners) {
        for (final Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes"})
    public static Kaboom getPlugin() {
        return (Kaboom)getPlugin((Class)Kaboom.class);
    }

}
