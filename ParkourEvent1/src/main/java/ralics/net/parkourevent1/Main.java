package ralics.net.parkourevent1;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ralics.net.parkourevent1.commands.AddBlockDataCommand;
import ralics.net.parkourevent1.commands.EventCommand;
import ralics.net.parkourevent1.data.DataFile;
import ralics.net.parkourevent1.listeners.*;
import ralics.net.parkourevent1.teams.noCollision;
import ralics.net.parkourevent1.util.Helper;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.*;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
       // List<String> completions = Arrays.asList("add", "remove");
      //  HashMap<Location, Integer> dataMap = new HashMap<>();
       // HashMap<UUID, Location> checkpoints = new HashMap<>();
       // AddBlockDataCommand addBlockDataCommand = new AddBlockDataCommand(dataMap, completions);
       // onMove moveLisener = new onMove(checkpoints, addBlockDataCommand);
      //  EventCommand eventCommand = new EventCommand(moveLisener, addBlockDataCommand);
       // Helper helper = new Helper(moveLisener);
        getCommand("event").setExecutor(new EventCommand());
        Bukkit.getPluginManager().registerEvents(new onMove(), this);
        Bukkit.getPluginManager().registerEvents(new onDamage(), this);
        Bukkit.getPluginManager().registerEvents(new onDismount(), this);
        Bukkit.getPluginManager().registerEvents(new onJoi(), this);
        Bukkit.getPluginManager().registerEvents(new onClickRight(), this);
        Bukkit.getPluginManager().registerEvents(new onWorldChange(), this);
        Bukkit.getPluginManager().registerEvents(new onBreak(), this);
        Bukkit.getPluginManager().registerEvents(new onDrop(), this);
        getCommand("addBlockData").setExecutor(new AddBlockDataCommand());
        noCollision.registerModification();
        DataFile.loadFile();

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        config = getConfig();
        createTeam();

    }
    public static FileConfiguration config;
    public static void createTeam(){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "team add noCollision");
    }
    public static void addToTeam(Player player){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "team join " + player.getName() + " noCollision");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        DataFile.saveFile();
    }
}
