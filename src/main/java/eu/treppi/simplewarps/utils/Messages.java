package eu.treppi.simplewarps.utils;

public class Messages {
    public static String transformMessage(String message) {
        message = message.replace("{prefix}", PREFIX);
        return message.replace("&", "§");
    }

    public static String transformMessage(String message, Warp warp) {
        message = transformMessage(message);
        message = message.replace("{warp.name}", warp.getName());
        return message;
    }

    public static final String PREFIX = "&eSimpleWarps &8>> &7";

    public static final String NOPLAYER = "{prefix} you need to be a player to use this command!";
    public static final String NOPERMISSION = "{prefix} you don't have permission to execute this command!";
    public static final String WARP_EXISTS = "{prefix} a warp with that name already exists! use /delwarp <name> to delete it";
    public static final String WARP_DOESNT_EXIST = "{prefix} no warp with such name was found!";
    public static final String WARP_CREATED = "{prefix} warp \"{warp.name}\" successfully created!";
    public static final String TELEPORT = "{prefix} you have been teleported to \"{warp.name}\"!";
    public static final String RELOAD = "{prefix} &areloaded successfully!";
    public static final String DELETED = "{prefix} &adeleted {warp.name}!";
}
