package eu.treppi.simplewarps.core;

import eu.treppi.simplewarps.commands.*;
import eu.treppi.simplewarps.utils.WarpController;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SimplewarpsPlugin extends JavaPlugin {

    public void onEnable() {
        Bukkit.getLogger().info("SimpleWarps plugin enabled!");

        getCommand("setwarp").setExecutor(new SetwarpCommand());
        getCommand("delwarp").setExecutor(new DelwarpCommand());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("warps").setExecutor(new WarpsCommand());
        getCommand("simplewarps").setExecutor(new SimplewarpsCommand());

        WarpController.reloadWarps();
    }

    public void onDisable() {

    }
}
