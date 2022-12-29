package com.bghddevelpment.kaboom.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.bghddevelpment.kaboom.Kaboom;
import com.bghddevelpment.kaboom.utilities.Color;
import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

@CommandAlias("kaboom|kaboom")
@Description("Kaboom everyone.")
@CommandPermission("kaboom.reload")
@Conditions("noconsole")
public class KaboomCommand extends BaseCommand {

    @Dependency
    private Kaboom plugin;

    private final ImmutableList<String> keywords = ImmutableList.of("reload");

    @Default
    public void onDefault(CommandSender sender, String[] args) {

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (!target.hasPermission("kaboom.bypass")) {
                target.getWorld().strikeLightningEffect(target.getLocation());
                target.setVelocity(new Vector(0, 64, 0));
                target.setFallDistance(-65.0F);
                target.sendMessage(Color.translate(plugin.getConfig().getString("Messages.Message1")));
            }
        }

        return;
    }

}
