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
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.event.entity.EntityDismountEvent;
import org.spigotmc.event.entity.EntityMountEvent;

public class onDismount implements Listener {
    @EventHandler
    public static void onDismount(EntityDismountEvent event){
        if (event.getDismounted() instanceof Boat boat && event.getEntity() instanceof Player player){
            boat.remove();
            ItemStack boatItem = new ItemStack(Material.OAK_BOAT);
            player.getInventory().addItem(boatItem);
        }
    }
    @EventHandler
    public static void mountEvent(EntityMountEvent event){
        Player p = (Player) event.getEntity();
        if (!event.getMount().getPassengers().isEmpty() && p.getInventory().contains(Material.OAK_BOAT) && p.getVehicle() != null){
            event.setCancelled(true);
        }
    }
}
