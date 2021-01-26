package eu.treppi.simplewarps.commands;

import eu.treppi.simplewarps.utils.Messages;
import eu.treppi.simplewarps.utils.Warp;
import eu.treppi.simplewarps.utils.WarpController;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetwarpCommand implements CommandExecutor {
    private static final String SYNTAX = "{prefix} &bSyntax: &e/setwarp <name>";
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {

            if(!sender.hasPermission("simplewarps.setwarp")) {
                sender.sendMessage(Messages.transformMessage(Messages.NOPERMISSION));
                return false;
            }

            if(!(args.length == 1)) {
                sender.sendMessage(Messages.transformMessage(SYNTAX));
                return false;
            }


            Player player = (Player) sender;
            Location location = player.getLocation();
            String name = args[0];

            if(WarpController.warpExists(name)) {
                sender.sendMessage(Messages.transformMessage(Messages.WARP_EXISTS));
                return false;
            }

            String creator = player.getUniqueId().toString();
            long currentTime = System.currentTimeMillis();

            Warp warp = new Warp(name, location, creator, currentTime);
            WarpController.setWarp(warp);
            player.sendMessage(Messages.transformMessage(Messages.WARP_CREATED, warp));
        }
        else {
            sender.sendMessage(Messages.transformMessage(Messages.NOPLAYER));
        }

        return false;
    }
}
