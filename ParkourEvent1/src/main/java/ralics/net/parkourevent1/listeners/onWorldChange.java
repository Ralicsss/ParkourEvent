package ralics.net.parkourevent1.listeners;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.inventory.ItemStack;

import java.net.http.WebSocket;

import static ralics.net.parkourevent1.util.Helper.course4startloc;
import static ralics.net.parkourevent1.util.Helper.finishedCourseMsg;

public class onWorldChange implements Listener {
    @EventHandler
    public static void onWorldChane(PlayerChangedWorldEvent e){
        e.getPlayer().getInventory().remove(Material.OAK_BOAT);
        e.getPlayer().getInventory().remove(Material.FIREWORK_ROCKET);
        e.getPlayer().getInventory().remove(Material.ELYTRA);
        e.getPlayer().getInventory().setChestplate(null);
        World world = e.getFrom();
       // if (e.toString() == "world_the_nether"){
      //      e.getPlayer().teleport(course4startloc);
      //      finishedCourseMsg(e.getPlayer(), 3);
      //  }
       // if (e.toString() == "world_end")
    }
}
