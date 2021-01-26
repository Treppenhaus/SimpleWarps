package eu.treppi.simplewarps.commands;

import eu.treppi.simplewarps.utils.Messages;
import eu.treppi.simplewarps.utils.Warp;
import eu.treppi.simplewarps.utils.WarpController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class WarpsCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        ArrayList<Warp> warps = WarpController.getWarps();
        StringBuilder msg = new StringBuilder();

        for(int i = 0; i < warps.size(); i++) {
            Warp warp = warps.get(i);
            msg.append("&b" + warp.getName());

            if(!(i == warps.size() - 1)) msg.append(" &7/ ");
        }

        sender.sendMessage(Messages.transformMessage("{prefix} " + msg));

        return false;
    }
}
