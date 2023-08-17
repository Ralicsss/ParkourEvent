package ralics.net.parkourevent1.teams;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class noCollision {
    public static ScoreboardManager manager = Bukkit.getScoreboardManager();
    public static Scoreboard scoreboard = manager.getNewScoreboard();
    public static Team noCollision = scoreboard.registerNewTeam("noCollision");
    public static void registerModification(){
        noCollision.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
    }
}
