package me.hsgamer.invset;

import me.hsgamer.hscore.bukkit.command.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class InvSet extends JavaPlugin {
    private final CommandManager commandManager = new CommandManager(this);

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {
        commandManager.unregisterAll();
    }
}
