package me.hsgamer.invset;

import com.google.gson.Gson;
import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetCommand extends Command {
    private final InvSet plugin;

    public GetCommand(InvSet plugin) {
        super("getinventory", "Get the inventory of a player", "/getinv <player> [end]", List.of("getinv"));
        setPermission(Permissions.GET.getName());
        this.plugin = plugin;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!testPermission(sender)) {
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage("Usage: " + getUsage());
            return false;
        }

        Player target = plugin.getServer().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("The player is not online");
            return false;
        }

        boolean end = args.length >= 2 && args[1].equalsIgnoreCase("end");
        Inventory inventory = end ? target.getEnderChest() : target.getInventory();

        Map<Integer, String> result = new HashMap<>();
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null) {
                ReadWriteNBT nbt = NBT.itemStackToNBT(item);
                String nbtString = nbt.toString();
                result.put(i, nbtString);
            }
        }

        Gson gson = new Gson();
        String json = gson.toJson(result);
        sender.sendMessage(json);

        return true;
    }
}
