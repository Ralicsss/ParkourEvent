package ralics.net.parkourevent1.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import ralics.net.parkourevent1.listeners.onMove;
import ralics.net.parkourevent1.util.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EventCommand implements TabExecutor {
    public static List<Player> admins = new ArrayList<>();
    public static Location course1Startloc;
    public Float correctYaw(float yaw){
        if (yaw >= -45 && yaw <= 45){yaw = 0;}
        if (yaw < -45 && yaw > -135){yaw = -90;}
        if (yaw <= -135 || yaw >= 135){yaw = 180;}
        if (yaw > 45 && yaw < 135){yaw = 90;}
        return yaw;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player){
            if (args[0].equalsIgnoreCase("start")){
                startEvent(); //errar
            }
            if (args[0].equalsIgnoreCase("nextCourse")){Helper.openNextCourse();}
            if (args[0].equalsIgnoreCase("resetCourses")){Helper.course = 0;}
            if (args[0].equalsIgnoreCase("addCourseStarts")){
                int arg1 = Integer.parseInt(args[1]);
                Location playerloc = player.getLocation();
                playerloc.setPitch(0);
                playerloc.setYaw(correctYaw(playerloc.getYaw()));
                if (arg1 == 1){
                    course1Startloc = playerloc;
                }if (arg1 == 2){Helper.course2startloc = playerloc;}if (arg1 == 3){Helper.course3startloc = playerloc;
                }
                if (arg1 == 4){Helper.course4startloc = playerloc;}
                if (arg1 == 5){Helper.course5startloc = playerloc;}
            }
            if (args[0].equalsIgnoreCase("setAdmin")){
                Player target = Bukkit.getPlayerExact(args[1]);
                if (target != null && !admins.contains(target)){
                    admins.add(target);
                    player.sendMessage("Successfully made " + target.getName() + "an admin!");
                }else if (admins.contains(target)){
                    admins.remove(target);
                    player.sendMessage("Player was an admin already, so the player was removed from admin!");
                }
            }
            if (args[0].equalsIgnoreCase("reset")){
                Helper.course = 0;
                Helper.hasFinishedCourse.clear();
                Helper.hasFinished.clear();
                Helper.resetEventDatas();
            }
            if (args[0].equalsIgnoreCase("setLobby")){
                Helper.lobby = player.getLocation();
            player.sendMessage("Set lobby location");}
        }
        return false;
    }


    private void startEvent(){
        HashMap<UUID, Location> currentCheckPoint = onMove.getCheckPointMap();
        HashMap<Location, Integer>  mappin =  AddBlockDataCommand.getBlockDataMap();
        HashMap<UUID, Integer> maps = ralics.net.parkourevent1.listeners.onMove.blockDataValue;
        for (Player p : Bukkit.getOnlinePlayers()){
            if (!admins.contains(p)){
                maps.put(p.getUniqueId(), 1);

                p.getInventory().clear();
                p.teleport(course1Startloc); //errar
                currentCheckPoint.clear();
                currentCheckPoint.put(p.getUniqueId(), course1Startloc);
                mappin.put(course1Startloc.getBlock().getLocation(), 1);
                Helper.course = 1;
                p.playSound(p, Sound.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.HOSTILE, 1, 1);
                p.sendMessage(ChatColor.GOLD+ "------------------------------------------------------------------------------------------------------------------");
                p.sendMessage(ChatColor.GOLD+ "The event has started!");
                p.sendMessage(ChatColor.GOLD+ "There are 5 courses.");
                p.sendMessage(ChatColor.GOLD+ "The first player to finish all courses wins the event!");
                p.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"Gold Blocks"+ChatColor.RESET + ChatColor.GOLD + " are checkpoints!");
                p.sendMessage(ChatColor.GOLD+ "------------------------------------------------------------------------------------------------------------------");
            }
        }
        ralics.net.parkourevent1.listeners.onMove.blockDataValue = maps;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
