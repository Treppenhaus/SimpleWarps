package eu.treppi.simplewarps.core;

import eu.treppi.simplewarps.commands.DelwarpCommand;
import eu.treppi.simplewarps.commands.SetwarpCommand;
import eu.treppi.simplewarps.commands.WarpCommand;
import eu.treppi.simplewarps.utils.Settings;
import eu.treppi.simplewarps.utils.WarpController;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class SimplewarpsPlugin extends JavaPlugin {

    public void onEnable() {
        Bukkit.getLogger().info("SimpleWarps plugin enabled!");

        getCommand("setwarp").setExecutor(new SetwarpCommand());
        getCommand("delwarp").setExecutor(new DelwarpCommand());
        getCommand("warp").setExecutor(new WarpCommand());

        WarpController.reloadWarps();
    }

    public void onDisable() {

    }
}
