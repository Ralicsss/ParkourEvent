package ralics.net.parkourevent1.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class onDrop implements Listener {
    @EventHandler
    public static void dropEvent(PlayerDropItemEvent e){
        if (!e.getPlayer().isOp()){e.setCancelled(true);}
    }
}
