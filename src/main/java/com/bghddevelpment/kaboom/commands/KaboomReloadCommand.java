package com.bghddevelpment.kaboom.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.bghddevelpment.kaboom.Kaboom;
import com.bghddevelpment.kaboom.utilities.Color;
import org.bukkit.command.CommandSender;

@CommandAlias("kaboomreload|kaboomrl")
@Description("Use me to reload the configs.")
@CommandPermission("kaboom.reload")
@Conditions("noconsole")
public class KaboomReloadCommand extends BaseCommand {

    @Dependency
    private Kaboom plugin;

    @Default
    public void onDefault(CommandSender sender, String[] args) {
        plugin.reloadConfig();

        sender.sendMessage(Color.translate("&aConfig has been reloaded."));

        return;
    }
}
