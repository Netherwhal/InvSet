package me.hsgamer.invset;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AirCommand extends Command {
    private final InvSet plugin;

    public AirCommand(InvSet plugin) {
        super("airinventory", "Set a slot of the inventory to air", "/airinv <player> <slot> [end]", List.of("airinv"));
        setPermission(Permissions.AIR.getName());
        this.plugin = plugin;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!testPermission(sender)) {
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage("Usage: " + getUsage());
            return false;
        }

        Player player = plugin.getServer().getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage("The player is not online");
            return false;
        }

        boolean end = args.length > 2 && args[2].equalsIgnoreCase("end");
        Inventory inventory = end ? player.getEnderChest() : player.getInventory();

        int slot;
        try {
            slot = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("The slot must be a number");
            return false;
        }

        if (slot < 0 || slot >= inventory.getSize()) {
            sender.sendMessage("The slot must be between 0 and " + (inventory.getSize() - 1));
            return false;
        }

        inventory.setItem(slot, null);
        sender.sendMessage("The slot " + slot + " of " + player.getName() + "'s " + (end ? "ender chest" : "inventory") + " is now air");
        return true;
    }
}
