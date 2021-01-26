package eu.treppi.simplewarps.commands;

import eu.treppi.simplewarps.utils.Messages;
import eu.treppi.simplewarps.utils.Warp;
import eu.treppi.simplewarps.utils.WarpController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DelwarpCommand implements CommandExecutor {
    private static final String SYNTAX = "{prefix} &bSyntax: &e/delwarp <name>";
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender.hasPermission("simplewarps.delete")) {
            if(args.length == 1) {

                String name = args[0];
                if(!WarpController.warpExists(name)) {
                    sender.sendMessage(Messages.transformMessage(Messages.WARP_DOESNT_EXIST));
                    return false;
                }

                Warp warp = WarpController.getWarpByName(name);
                WarpController.deleteWarp(warp);

                sender.sendMessage(Messages.transformMessage(Messages.DELETED, warp));
                return false;

            }
            else
                sender.sendMessage(Messages.transformMessage(SYNTAX));
        }
        else
            sender.sendMessage(Messages.transformMessage(Messages.NOPERMISSION));

        return false;
    }
}
