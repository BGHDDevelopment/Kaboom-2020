package com.bghddevelpment.kaboom.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.bghddevelpment.kaboom.Kaboom;
import com.bghddevelpment.kaboom.utilities.Color;
import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CommandAlias("kaboom|kaboom")
@Description("Kaboom everyone.")
@CommandPermission("kaboom.reload")
@Conditions("noconsole")
public class KaboomCommand extends BaseCommand implements TabCompleter {

    @Dependency
    private Kaboom plugin;

    private final ImmutableList<String> keywords = ImmutableList.of("reload");

    @Default
    public void onDefault(CommandSender sender, String[] args) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                if (!player.hasPermission("kaboom.bypass")) {
                    player.getWorld().strikeLightningEffect(player.getLocation());
                    player.setVelocity(new Vector(0, 64, 0));
                    player.setFallDistance(-65.0F);
                    player.sendMessage(Color.translate(plugin.getConfig().getString("Messages.Message1")));
                }
            });

        return;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return (args.length == 1 && sender.hasPermission("kaboom.reload")) ? StringUtil.copyPartialMatches(args[0], getKeywords(), new ArrayList<>()) : Collections.emptyList();
    }

    private ImmutableList<String> getKeywords() {
        return keywords;
    }

}
