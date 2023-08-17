package ralics.net.parkourevent1.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class onBreak implements Listener {
    @EventHandler
    public static void breakEvent(BlockBreakEvent e){
        if (!e.getPlayer().isOp()){e.setCancelled(true);}
    }
}
