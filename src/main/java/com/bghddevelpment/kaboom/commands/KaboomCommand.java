package com.bghddevelpment.kaboom.commands;

import com.bghddevelpment.kaboom.Kaboom;
import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KaboomCommand implements TabExecutor {
    private final ImmutableList<String> keywords = ImmutableList.of("reload");
    private final Kaboom plugin;

    public KaboomCommand(Kaboom plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1 && sender.hasPermission("kaboom.reload")) {
            if (getKeywords().contains(args[0])) {
                final String keyword = args[0];

                if (keyword.equalsIgnoreCase("reload")) {
                    getPlugin().reloadConfig();

                    sender.sendMessage(translate("&aConfig has been reloaded."));

                    return true;
                }
            }
        }

        if (sender.hasPermission("kaboom.use")) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                if (!player.hasPermission("kaboom.bypass")) {
                    player.getWorld().strikeLightningEffect(player.getLocation());
                    player.setVelocity(new Vector(0, 64, 0));
                    player.setFallDistance(-65.0F);
                    player.sendMessage(translate(getPlugin().getConfig().getString("Messages.Message1")));
                }
            });

            return true;
        }

        sender.sendMessage(translate("&cYou don't have permission to run that command."));

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return (args.length == 1 && sender.hasPermission("kaboom.reload")) ? StringUtil.copyPartialMatches(args[0], getKeywords(), new ArrayList<>()) : Collections.emptyList();
    }

    private String translate(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    private ImmutableList<String> getKeywords() {
        return keywords;
    }

    private Kaboom getPlugin() {
        return plugin;
    }

}
