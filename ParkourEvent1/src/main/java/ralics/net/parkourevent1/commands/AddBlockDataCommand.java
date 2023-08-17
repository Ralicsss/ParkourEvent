package ralics.net.parkourevent1.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AddBlockDataCommand implements TabExecutor {
    public static HashMap<Location, Integer> blockData = new HashMap<Location, Integer>();
    private static List<String> completions = Arrays.asList("add", "remove");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p){
            Block target = p.getTargetBlockExact(5);
            p.sendMessage("notnullceck");
            if (target != null){
                p.sendMessage("notnull");
                if (args[0].equalsIgnoreCase("add")) {
                    p.sendMessage("got added");
                    Location loc = target.getLocation();
                    int customDataVal = Integer.parseInt(args[1]);
                    p.sendMessage("parsedint");
                    blockData.put(loc, customDataVal);
                    p.sendMessage(ChatColor.GOLD + "Block is now in teleport list!");
                }else if (args[0].equalsIgnoreCase("remove")){
                    Location loc = target.getLocation();
                    int customDataVal = Integer.parseInt(args[1]);
                    blockData.remove(loc, customDataVal);
                    p.sendMessage(ChatColor.GOLD + "Block removed from teleport list!");
                }
            }else{
                p.sendMessage(ChatColor.GOLD + "You are not looking at a block/are in the wrong range!");
            }
        }
        return false;
    }
    public static HashMap<Location, Integer> getBlockDataMap(){
        return blockData;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1){
            String arg = args[0].toLowerCase();
            List<String> tabCompletions = new ArrayList<>();

            for (String option : completions){
                if (option.startsWith(arg)){
                    tabCompletions.add(option);
                }
            }
            return tabCompletions;
        }
        return null;
    }
}
