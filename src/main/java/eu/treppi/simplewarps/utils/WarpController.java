package eu.treppi.simplewarps.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WarpController {
    private static ArrayList<Warp> warps;

    public static void reloadWarps() {
        Bukkit.getLogger().info("reloading warps from warps.yml!");
        warps = getWarpsFromFile();
    }

    public static ArrayList<Warp> getWarpsFromFile() {
        ArrayList<Warp> warplist = new ArrayList<Warp>();
        FileConfiguration storage = getStorage();

        for(int i = 0; true; i++) {

            if(!storage.contains("warps."+i))
                return warplist;

            if(!storage.getBoolean("warps."+i+".removed")) {
                String name = storage.getString("warps."+i+".name");
                String creator = storage.getString("warps."+i+".creator");
                long creationTime = storage.getLong("warps."+i+".creationTime");

                String worldname = storage.getString("warps."+i+".location.world");
                World world = Bukkit.getWorld(worldname);
                double x = storage.getDouble("warps."+i+".location.x");
                double y = storage.getDouble("warps."+i+".location.y");
                double z = storage.getDouble("warps."+i+".location.z");

                Location loc = new Location(world, x, y, z);
                float pitch = Float.parseFloat(Double.toString(storage.getDouble("warps."+i+".location.pitch")));
                float yaw = Float.parseFloat(Double.toString(storage.getDouble("warps."+i+".location.yaw")));

                loc.setPitch(pitch);
                loc.setYaw(yaw);

                Warp warp = new Warp(name, loc, creator, creationTime);
                warplist.add(warp);
            }
        }
    }

    public static void setWarp(Warp warp) {
        FileConfiguration storage = getStorage();

        for(int i = 0; true; i++) {
            if(!storage.contains("warps."+i)|| storage.getString("warps."+i+".name").equalsIgnoreCase(warp.getName()) || storage.getBoolean("warps."+i+".removed")) {

                storage.set("warps."+i+".name", warp.getName());
                storage.set("warps."+i+".creator", warp.getCreator());
                storage.set("warps."+i+".creationTime", warp.getCreationTime());

                Location loc = warp.getLocation();
                storage.set("warps."+i+".location.world", loc.getWorld().getName());
                storage.set("warps."+i+".location.x", loc.getX());
                storage.set("warps."+i+".location.y", loc.getY());
                storage.set("warps."+i+".location.z", loc.getZ());

                storage.set("warps."+i+".location.pitch", loc.getPitch());
                storage.set("warps."+i+".location.yaw", loc.getYaw());

                storage.set("warps."+i+".removed", false);
                saveStorage(storage);
                WarpController.reloadWarps();
                return;
            }
        }
    }

    public static void deleteWarp(Warp warp) {
        FileConfiguration storage = getStorage();

        for(int i = 0; true; i++) {
            if(!storage.contains("warps."+i))
                return;

            if(storage.getString("warps."+i+".name").equalsIgnoreCase(warp.getName())) {
                storage.set("warps."+i+".removed", true);
                saveStorage(storage);
                reloadWarps();
                return;
            }
        }
    }

    public static void teleport(Player player, Warp warp) {
        player.teleport(warp.getLocation());
        player.playSound(player.getLocation(), Sound.ENTITY_ENDEREYE_LAUNCH, 1, 1);
        player.sendMessage(Messages.transformMessage(Messages.TELEPORT, warp));
    }

    public static boolean warpExists(String name) {
        for(Warp warp : getWarps()) {
            if(warp.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static Warp getWarpByName(String name) {
        for(Warp warp : getWarps()) {
            if(warp.getName().equalsIgnoreCase(name)) {
                return warp;
            }
        }
        return null;
    }

    public static ArrayList<Warp> getWarps() {
        return warps;
    }

    private static FileConfiguration getStorage() {
        return YamlConfiguration.loadConfiguration(new File("plugins/SimpleWarps/warps.yml"));
    }

    private static void saveStorage(FileConfiguration storage) {
        try {
            storage.save(new File("plugins/SimpleWarps/warps.yml"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
