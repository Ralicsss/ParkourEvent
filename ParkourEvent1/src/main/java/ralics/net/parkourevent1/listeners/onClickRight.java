package ralics.net.parkourevent1.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class onClickRight implements Listener {
    @EventHandler
    public static void rightClickEvent(PlayerInteractEvent e){
        Block t = e.getPlayer().getTargetBlockExact(4);
        //Player p = e.getPlayer();
        if (t.getType() == Material.MANGROVE_TRAPDOOR){
            e.setCancelled(true);

       // }if(e.getClickedBlock() != null){
           // if(p.getInventory().getItemInMainHand().getType() == Material.OAK_BOAT){
              //  e.setCancelled(true);
               // p.getInventory().setItemInMainHand(null);
               // Location loc = t.getLocation();
               // loc.setY(loc.getY()+1);
              //  Boat boat = loc.getWorld().spawn(loc, Boat.class);              idfk this is too bugged for me to fix
               // boat.setBoatType(Boat.Type.OAK);
               // boat.addPassenger(p);
            }
        }

}
