package ralics.net.parkourevent1.listeners;


import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ralics.net.parkourevent1.Main;
import ralics.net.parkourevent1.commands.EventCommand;
import ralics.net.parkourevent1.teams.noCollision;
import ralics.net.parkourevent1.util.Helper;

public class onJoi implements Listener {
    @EventHandler
    public static void onJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        Main.addToTeam(p);
        p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 111, 111, true, false, false));
        noCollision.noCollision.addEntry(p.getName());
        p.setGameMode(GameMode.ADVENTURE);
        if (!EventCommand.admins.contains(p)) {
            if (Helper.course == 0){
                p.teleport(Helper.lobby);
            }
        }//

    }
}
