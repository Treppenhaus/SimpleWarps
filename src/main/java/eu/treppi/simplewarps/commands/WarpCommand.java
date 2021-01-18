package eu.treppi.simplewarps.commands;

import eu.treppi.simplewarps.utils.Messages;
import eu.treppi.simplewarps.utils.WarpController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {
    private static final String SYNTAX = "{prefix} &bSyntax: /warp <name>";
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            if(!sender.hasPermission("simplewarps.warp")) {
                sender.sendMessage(Messages.transformMessage(Messages.NOPERMISSION));
                return false;
            }
            if(!(args.length == 1)) {
                sender.sendMessage(Messages.transformMessage(SYNTAX));
                return false;
            }

            Player player = (Player) sender;
            String name = args[0];

            if(!WarpController.warpExists(name)) {
                player.sendMessage(Messages.transformMessage(Messages.WARP_DOESNT_EXIST));
                return false;
            }

            WarpController.teleport(player, WarpController.getWarpByName(name));

        }
        else {
            sender.sendMessage(Messages.transformMessage(Messages.NOPLAYER));
        }
        return false;
    }
}
