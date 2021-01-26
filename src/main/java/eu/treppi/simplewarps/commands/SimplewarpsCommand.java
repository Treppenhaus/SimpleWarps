package eu.treppi.simplewarps.commands;

import eu.treppi.simplewarps.utils.Messages;
import eu.treppi.simplewarps.utils.WarpController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SimplewarpsCommand implements CommandExecutor {
    private static final String SYNTAX = "{prefix} &bSyntax: &e/simplewarps reload";
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender.hasPermission("simplewarps.admin")) {
            if(args.length == 1) {

                if(args[0].equalsIgnoreCase("reload")) {
                    WarpController.reloadWarps();
                    sender.sendMessage(Messages.transformMessage(Messages.RELOAD));
                    return false;
                }

            }
            else
                sender.sendMessage(Messages.transformMessage(SYNTAX));
        }
        else
            sender.sendMessage(Messages.transformMessage(Messages.NOPERMISSION));

        return false;
    }
}
