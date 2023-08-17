package ralics.net.parkourevent1.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import ralics.net.parkourevent1.commands.AddBlockDataCommand;
import ralics.net.parkourevent1.commands.EventCommand;
import ralics.net.parkourevent1.listeners.onMove;
import ralics.net.parkourevent1.util.Helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DataFile {
    public static File dataFile = new File("plugins/ParkourEvent/spawnAndMaps.yml");
    public static FileConfiguration customFile = new YamlConfiguration();
    public static void saveFile(){
        if (!dataFile.exists()){
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                System.out.println("why am i using sout, cant make file");
            }
        } else {
            customFile = YamlConfiguration.loadConfiguration(dataFile);

            List<UUID> uuidKeys1 = new ArrayList<>(onMove.currentCheckPoint.keySet());
            HashMap<String, Location> lockeysstuff = new HashMap<>();
            for (UUID keys: uuidKeys1) {
                lockeysstuff.put(keys.toString(), onMove.currentCheckPoint.get(keys));
            }
            customFile.set("checkpointMap", lockeysstuff); // uuid

            List<UUID> uuidKeys2 = new ArrayList<>(onMove.blockDataValue.keySet());
            HashMap<String, Integer> lockeysstuff2 = new HashMap<>();
            for (UUID keys: uuidKeys2) {
                lockeysstuff2.put(keys.toString(), onMove.blockDataValue.get(keys));
            }

            customFile.set("blockdatavalue", lockeysstuff2); // uuid
            customFile.set("blockdata", AddBlockDataCommand.getBlockDataMap());
            customFile.set("admins", EventCommand.admins);
            customFile.set("course1start", EventCommand.course1Startloc);
            customFile.set("course2start", Helper.course2startloc);
            customFile.set("course3start", Helper.course3startloc);
            customFile.set("course4start", Helper.course4startloc);
            customFile.set("course5start", Helper.course5startloc);
            customFile.set("lobby", Helper.lobby);
            try {
                customFile.save(dataFile);
            } catch (IOException e) {
                System.out.println("This sout stuff makes console angry, cant save file");
            }
        }
    }
    public static void dotheloadingforcheck(String filesavename) {
        MemorySection blockDataValueSection = (MemorySection) customFile.get(filesavename);

        for (String key : blockDataValueSection.getKeys(false)) {

            UUID uuid = UUID.fromString(key);
            int value = blockDataValueSection.getInt(key);
            onMove.blockDataValue.put(uuid, value);
        }

    }
    public static void dotheloadingforloc(String filesavename) {
        MemorySection blockDataValueSection = (MemorySection) customFile.get(filesavename);

        for (String key : blockDataValueSection.getKeys(false)) {
            ConfigurationSection locationSection = blockDataValueSection.getConfigurationSection(key);

            if (locationSection != null) {
                String worldName = locationSection.getString("world");
                double x = locationSection.getDouble("x");
                double y = locationSection.getDouble("y");
                double z = locationSection.getDouble("z");
                float pitch = (float) locationSection.getDouble("pitch");
                float yaw = (float) locationSection.getDouble("yaw");

                World world = Bukkit.getWorld(worldName);
                if (world != null) {
                    Location location = new Location(world, x, y, z, yaw, pitch);
                    UUID uuid = UUID.fromString(key);
                    System.out.println(uuid);
                    onMove.currentCheckPoint.put(uuid, location);
                } else {
                    System.out.println("no world i hate this");
                }
            }
        }
    }
    public static void dotheloadingfordata(String filesavename) {
        MemorySection blockDataValueSection = (MemorySection) customFile.get(filesavename);

        for (String key : blockDataValueSection.getKeys(false)) {
            int val = blockDataValueSection.getInt(key);

            ConfigurationSection locationSection = blockDataValueSection.getConfigurationSection(key);
            if (locationSection != null) {
                String worldName = locationSection.getString("world");
                double x = locationSection.getDouble("x");
                double y = locationSection.getDouble("y");
                double z = locationSection.getDouble("z");
                float pitch = (float) locationSection.getDouble("pitch");
                float yaw = (float) locationSection.getDouble("yaw");
                if (worldName != null) {
                    World world = Bukkit.getWorld(worldName);
                    if (world != null) {
                        Location location = new Location(world, x, y, z, yaw, pitch);
                        AddBlockDataCommand.blockData.put(location, val);
                    } else {
                        System.out.println("no world i hate this");
                    }
                }
            }
        }
    }
    public static void loadFile(){
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                System.out.println("console mad, me mad, cant create file");
            }
        } else {
            customFile = YamlConfiguration.loadConfiguration(dataFile);

            dotheloadingforloc("checkpointMap");
            dotheloadingforcheck("blockdatavalue");
            dotheloadingfordata("blockdata");
            EventCommand.admins = (List<Player>) customFile.getList("admins");
            EventCommand.course1Startloc = customFile.getLocation("course1start");
            Helper.course2startloc = customFile.getLocation("course2start");
            Helper.course3startloc = customFile.getLocation("course3start");
            Helper.course4startloc = customFile.getLocation("course4start");
            Helper.course5startloc = customFile.getLocation("course5start");
            Helper.lobby = customFile.getLocation("lobby");


        }
    }
}
