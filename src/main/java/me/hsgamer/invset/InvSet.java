package me.hsgamer.invset;

import de.tr7zw.changeme.nbtapi.utils.MinecraftVersion;
import me.hsgamer.hscore.bukkit.command.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class InvSet extends JavaPlugin {
    private final CommandManager commandManager = new CommandManager(this);

    @Override
    public void onLoad() {
        MinecraftVersion.disableBStats();
        MinecraftVersion.disablePackageWarning();
        MinecraftVersion.disableUpdateCheck();
    }

    @Override
    public void onEnable() {
        Permissions.register();

        commandManager.register(new GetCommand(this));
        CommandManager.syncCommand();
    }

    @Override
    public void onDisable() {
        commandManager.unregisterAll();
        Permissions.unregister();
    }
}
