package ralics.net.parkourevent1.listeners;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import ralics.net.parkourevent1.commands.AddBlockDataCommand;
import ralics.net.parkourevent1.util.Helper;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class onMove implements Listener {
   // public onMove(HashMap<UUID, Location> currentCheckPoint, AddBlockDataCommand blockDatas){
   //     this.currentCheckPoint = currentCheckPoint;
    //    this.blockDatas = blockDatas;
   // }
    public static HashMap<UUID, Location> currentCheckPoint = new HashMap<UUID, Location>(); //is needed
    private HashMap<Location, Integer> blockDataMap = AddBlockDataCommand.getBlockDataMap(); // not needed
    public static HashMap<UUID, Integer> blockDataValue = new HashMap<>(); // needed
    public static void stff(){
        blockDataValue.put(UUID.randomUUID(), 1);
    }

    @EventHandler
    public void moveEvent(PlayerMoveEvent event){
        blockDataMap = AddBlockDataCommand.getBlockDataMap();
        Player p = event.getPlayer();
        Location playerLocation = p.getLocation();
        Location blockLocation = playerLocation.clone().subtract(0, 1, 0);
        Block b = blockLocation.getBlock();
        Helper.checkCourses(p, b);
        if (blockDataMap.get(b.getLocation()) != null && !Objects.equals(blockDataMap.get(b.getLocation()), blockDataValue.get(p.getUniqueId()))){  //fail?? not anymore.
            Location loc = p.getLocation();
            UUID uuid = p.getUniqueId();
            loc.setYaw(correctYaw(loc.getYaw()));
            loc.setPitch(0);
            if (currentCheckPoint.get(uuid) != loc){
                blockDataValue.remove(uuid, blockDataMap.get(b.getLocation()));
                blockDataValue.put(uuid, blockDataMap.get(b.getLocation()));
                currentCheckPoint.remove(uuid, loc);
                currentCheckPoint.put(uuid, loc);
                p.sendMessage(ChatColor.GOLD+"Checkpoint!");
                p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.AMBIENT, 1, 1);
            }

        }//else if(blockDataValue.get(p.getUniqueId()) == null){
            //Location loc = p.getLocation();
           // UUID uuid = p.getUniqueId();
           // loc.setYaw(correctYaw(loc.getYaw()));
          //  loc.setPitch(0);
          //  if (currentCheckPoint.get(uuid) != loc){
           //     blockDataValue.remove(uuid, blockDataMap.get(b.getLocation()));
            //    blockDataValue.put(uuid, blockDataMap.get(b.getLocation()));
            //    currentCheckPoint.remove(uuid, loc);
           //     currentCheckPoint.put(uuid, loc);
            //    p.sendMessage(ChatColor.GOLD+"Checkpoint!");
           //     p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.AMBIENT, 1, 1);
            //}
       // }


    }

    public static HashMap<UUID, Location> getCheckPointMap(){
        return currentCheckPoint;
    }


    public Float correctYaw(float yaw){
        if (yaw >= -45 && yaw <= 45){yaw = 0;}
        if (yaw < -45 && yaw > -135){yaw = -90;}
        if (yaw <= -135 || yaw >= 135){yaw = 180;}
        if (yaw > 45 && yaw < 135){yaw = 90;}
        return yaw;
    }
}
