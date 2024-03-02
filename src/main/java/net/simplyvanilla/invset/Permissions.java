package net.simplyvanilla.invset;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class Permissions {
    public static final Permission GET = new Permission("invset.get", PermissionDefault.OP);
    public static final Permission AIR = new Permission("invset.air", PermissionDefault.OP);

    public static void register() {
        Bukkit.getPluginManager().addPermission(GET);
        Bukkit.getPluginManager().addPermission(AIR);
    }

    public static void unregister() {
        Bukkit.getPluginManager().removePermission(GET);
        Bukkit.getPluginManager().removePermission(AIR);
    }
}
