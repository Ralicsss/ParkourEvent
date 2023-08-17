package ralics.net.parkourevent1.util;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import ralics.net.parkourevent1.listeners.onMove;

import java.util.*;

import static ralics.net.parkourevent1.listeners.onMove.currentCheckPoint;

public class Helper {
    //private static ;
    //public Helper(onMove onMove){
      //  this.onMove = onMove;
    //}
    public static int course = 0;
    public static Location lobby;
    public static Location course2startloc;
    public static Location course3startloc;
    public static Location course4startloc;
    public static Location course5startloc;
    public static Border course3Finish = new Border(new Vector(-250, 63, 2458 ), new Vector(-251, 81, 2473));
    public static Border course4Finish  = new Border(new Vector(67, 128, 539 ), new Vector(69, 131, 537));
    public static Border course5Finish = new Border(new Vector(180, 78, -30 ), new Vector(181, 75, -29));
    public static Border course1p1 = new Border(new Vector(111, 87, 321), new Vector(335, 87, 291)); // checks for grass and not slime blocks/gold/emerald
    public static Border course1p2 = new Border(new Vector(336, 87, 193), new Vector(342, 91, 322)); // Checks at the hill at the end of course one
    public static Border course1Finish = new Border(new Vector(350, 91, 292), new Vector(342, 111, 323)); // Checks if the player is at the finish
    public static Border course2p1 = new Border(new Vector(348, 90, 234), new Vector(469, 89, 381)); // Island parkour
    public static Border course2p2 = new Border(new Vector(400, 69, 210), new Vector(490, 70, 415)); // Red sandstone check at drop
    public static Border course2p3 = new Border(new Vector(400, 62, 210), new Vector(490, 62, 415)); // checks for the lava at the red sandstone parkour
    public static Border course2Finish = new Border(new Vector(396, 62, 295 ), new Vector( 407, 68, 308));
    public static Border course3bardar = new Border(new Vector(229, 144, 2663), new Vector(236, 137, 2650));
    public static Border fullCourse3 = new Border(new Vector(-300, 50, 2740), new Vector(314, 186, 2306)); //2546
    public static Border course4border = new Border(new Vector(-80, 40, 318), new Vector(-115, 44, 284));
    public static Border course4ELAITRAAAAAAAAAAAAAAAAAAAAAAAAAAA = new Border(new Vector(- 93, 128, 312), new Vector(-112, 133, 292));
    public static Border course5border = new Border(new Vector(188, 50, -44 ), new Vector(107, 56, 0));
    public static void openNextCourse(){
        course++;
    }

    public static void finishedCourseMsg(Player p, int course){

        if (hasFinishedCourse.get(course) != p.getUniqueId()){
            p.sendMessage(ChatColor.GOLD + "You have finished the course!");
            p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, SoundCategory.AMBIENT, 1, 1);
            hasFinishedCourse.put(course, p.getUniqueId());
        }
    }
    public static void checkCourses(Player player, Block block){
        course1check(player, block);
        course2check(player, block);
        course3Check(player, block);
        course4Check(player, block);
        course5check(player, block);


    }




    private static void course1check(Player player, Block block){

        HashMap<UUID, Location> currentCheckPoint = ralics.net.parkourevent1.listeners.onMove.getCheckPointMap();
        Material type = block.getType();
        if (course1p1.contains(player.getLocation())){
            if (type != Material.SLIME_BLOCK && type != Material.GOLD_BLOCK && type != Material.EMERALD_BLOCK && type != Material.AIR) {
                player.teleport(currentCheckPoint.get(player.getUniqueId()));
            }
        }
        if (course1p2.contains(player.getLocation())){
            player.teleport(currentCheckPoint.get(player.getUniqueId()));
        }
        if (course1Finish.contains(player.getLocation())){
            //player.teleport(course2startloc);
            currentCheckPoint.remove(player.getUniqueId(), block.getLocation());
            currentCheckPoint.put(player.getUniqueId(), course2startloc);
            finishedCourseMsg(player, 1);
        }
    }
    private static void course2check(Player player, Block block){
        HashMap<UUID, Location> currentCheckPoint = onMove.getCheckPointMap();
        if (course2p1.contains(player.getLocation())){
            player.teleport(currentCheckPoint.get(player.getUniqueId()));
        }
        if (course2p2.contains(player.getLocation())){
            if (block.getType() == Material.RED_SANDSTONE || block.getType() == Material.WATER){
                player.teleport(currentCheckPoint.get(player.getUniqueId()));
            }
        }
        if (course2p3.contains(player.getLocation())){
            player.teleport(currentCheckPoint.get(player.getUniqueId()));
        }
        if (course2Finish.contains(player.getLocation())){
            player.teleport(course3startloc);
            currentCheckPoint.remove(player.getUniqueId(), block.getLocation());
            currentCheckPoint.put(player.getUniqueId(), course3startloc);
            finishedCourseMsg(player, 2);
        }
    }
    //229, 144, 2663 : 236, 137, 2650

    private static void course3Check(Player player, Block block){
        if (course3bardar.contains(player.getLocation())){
            if (player.getVehicle() == null && !player.getInventory().contains(Material.OAK_BOAT)) {
                spawnBoat(player);
            }
        }
        if (fullCourse3.contains(player.getLocation())){
            Material type = block.getType();
            HashMap<UUID, Location> currentCheckPoint = onMove.getCheckPointMap();
            if (type != Material.SLIME_BLOCK && type != Material.ICE && type != Material.AIR && type != Material.POLISHED_BLACKSTONE_BRICKS && type != Material.LADDER && type != Material.OBSIDIAN&& type != Material.GOLD_BLOCK){
                player.teleport(currentCheckPoint.get(player.getUniqueId()));
            }
        }
        if (course3Finish.contains(player.getLocation())){
            player.teleport(course4startloc);
            currentCheckPoint.remove(player.getUniqueId(), block.getLocation());
            currentCheckPoint.put(player.getUniqueId(), course4startloc);
            finishedCourseMsg(player, 3);
        }
    }
    public static void resetEventDatas(){
        finishedPlace = 1;
    }
    private static void course4Check(Player player, Block block){
        HashMap<UUID, Location> currentCheckPoint = onMove.getCheckPointMap();
        Location loc = player.getLocation();
        if (course4border.contains(loc)){
            player.teleport(currentCheckPoint.get(player.getUniqueId()));
        }
        if (course4ELAITRAAAAAAAAAAAAAAAAAAAAAAAAAAA.contains(loc)){
            if (!player.getInventory().contains(Material.FIREWORK_ROCKET)) {
                ItemStack stack = new ItemStack(Material.ELYTRA);
                ItemMeta meta = stack.getItemMeta();
                meta.setUnbreakable(true);
                stack.setItemMeta(meta);
                ItemStack fireWorks = new ItemStack(Material.FIREWORK_ROCKET);
                fireWorks.setAmount(300);
                player.getInventory().addItem(fireWorks);
                player.getInventory().setChestplate(stack);
                player.sendMessage(ChatColor.GREEN + "You have been given an elytra.");
            }
        }
        if (course4Finish.contains(loc)){
            player.teleport(course5startloc);
            currentCheckPoint.remove(player.getUniqueId(), block.getLocation());
            currentCheckPoint.put(player.getUniqueId(), course5startloc);
            finishedCourseMsg(player, 4);
        }
    }
    private static void course5check(Player player, Block block){
        HashMap<UUID, Location> currentCheckPoint = onMove.getCheckPointMap();
        Location loc = player.getLocation();
        if (course5border.contains(loc)){
            player.teleport(currentCheckPoint.get(player.getUniqueId()));
        }
        if (course5Finish.contains(loc)){
            playerFinishedCourses(player);
        }
    }
    private static int finishedPlace = 1;
    public static List<Player> hasFinished = new ArrayList<>();
    public static HashMap<Integer, UUID> hasFinishedCourse = new HashMap<>();
    public static void playerFinishedCourses(Player player){
        if (!hasFinished.contains(player)) {
            if (finishedPlace == 1) {
                Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " has placed 1st in the event!");
            } else if (finishedPlace == 2) {
                Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " has placed 2nd in the event!");
            } else if (finishedPlace == 3) {
                Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " has placed 3rd in the event!");
            } else {
                Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " has placed " + finishedPlace + "th in the event!");
            }
            hasFinished.add(player);
            player.setGameMode(GameMode.SPECTATOR);
            finishedPlace++;
        }
    }


    public static void spawnBoat(Player player){
        World world = Bukkit.getWorld("world");
        Location location = player.getLocation();
        Entity boat = world.spawnEntity(location, EntityType.BOAT);
        boat.addPassenger(player);
        Boat boats = (Boat) boat;
        boats.setBoatType(Boat.Type.OAK); // When player shift leaves boat delete boat and give it to player? // already did :cool:
    }
}
