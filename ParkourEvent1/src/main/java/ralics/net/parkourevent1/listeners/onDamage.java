package ralics.net.parkourevent1.listeners;

import org.bukkit.entity.Painting;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class onDamage implements Listener {
    @EventHandler
    public static void playerDamage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player){
            e.setCancelled(true);
        }
    }
}
