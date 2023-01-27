package me.xtimdevx.thomsehsmp.utils;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LocationUtils {

    public static Location spawn = new Location(Bukkit.getWorld("SMP"), 86.5, 69, -119.5);
    public static Location arena = new Location(Bukkit.getWorld("SMP"), -30.5, 69, -70.5);
    public static Location crates = new Location(Bukkit.getWorld("SMP"), 35.5, 68, -110.5);

    private static BlockFace[] faces = new BlockFace[] { BlockFace.SELF, BlockFace.EAST, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH_EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH_WEST, BlockFace.NORTH_WEST};

    public static boolean hasBlockNearby(Material material, Location location) {
        Block block = location.getBlock();

        for (BlockFace face : faces) {
            if (block.getRelative(face).getType() == material) {
                return true;
            }
        }

        return false;
    }

    public static Location getHighestBlock(Location loc) {
        int highest = loc.getWorld().getEnvironment() == World.Environment.NETHER ? 100 : 255;

        for (int i = highest; i >= 0; i--) {
            if (loc.getWorld().getBlockAt(loc.getBlockX(), i, loc.getBlockZ()).getType() != Material.AIR) {
                return loc.getWorld().getBlockAt(loc.getBlockX(), i, loc.getBlockZ()).getLocation();
            }
        }
        return loc;
    }

    public static boolean isOutsideOfBorder(Location loc) {
        WorldBorder border = loc.getWorld().getWorldBorder();

        double size = border.getSize();
        double x = loc.getX() - border.getCenter().getX();
        double z = loc.getZ() - border.getCenter().getZ();

        return Math.abs(x) < size && Math.abs(z) < size;
    }


        private static final Material[] nospawn = { Material.WATER, Material.WATER, Material.LAVA, Material.LAVA, Material.CACTUS, Material.BEDROCK, Material.AIR };

    public static List<Location> getScatterLocations(World world, int radius, boolean nether) {
        ArrayList<Location> locs = new ArrayList<Location>();
        double min = 150;

        for (int j = 0; j < 4004; j++) {
            if (j == 4003) {
                Bukkit.broadcastMessage(ChatColor.RED + "Could not scatter a player");
                break;
            }

            Random rand = new Random();
            int x = rand.nextInt(radius * 2) - radius;
            int z = rand.nextInt(radius * 2) - radius;

            Location loc = new Location(world, x + 0.5, 0, z + 0.5);

            boolean close = false;
            for (Location l : locs) {
                if (l.distanceSquared(loc) < min) {
                    close = true;
                }
            }

            if (!close && isVaild(loc.clone())) {
                if(nether) {
                    double y = LocationUtils.getProperLocationNether(Bukkit.getWorld("SMP_nether"), loc.getX(), loc.getY(), loc.getZ());
                    loc.setY(y + 2);
                    locs.add(loc);
                    break;
                }else {
                    double y = LocationUtils.highestTeleportableYAtLocation(loc);
                    loc.setY(y + 2);
                    locs.add(loc);
                    break;
                }
            } else {
                min -= 1;
            }
        }

        return locs;
    }

    private static boolean isVaild(Location loc) {
        loc.setY(loc.getWorld().getHighestBlockYAt(loc));

        Material m = loc.add(0, -1, 0).getBlock().getType();
        boolean vaild = true;

        if (loc.getBlockY() < 60) {
            vaild = false;
        }

        for (Material no : nospawn) {
            if (m == no) {
                vaild = false;
            }
        }

        return vaild;
    }

    public static int highestTeleportableYAtLocation(Location location) {
        Location startingLocation = location.clone();
        startingLocation.setY(location.getWorld().getMaxHeight());

        boolean above2WasAir = false;
        boolean aboveWasAir = false;
        Block currentBlock = startingLocation.getBlock();

        while (currentBlock.getY() >= 0) {

            if (currentBlock.getType() != Material.AIR) {
                if (above2WasAir && aboveWasAir) {
                    return currentBlock.getY();
                }

                above2WasAir = aboveWasAir;
                aboveWasAir = false;
            } else {
                above2WasAir = aboveWasAir;
                aboveWasAir = true;
            }

            currentBlock = currentBlock.getRelative(BlockFace.DOWN);
        }

        return -1;
    }

    private static int getProperLocationNether(World world, double x, double y, double z) {

        int y2 = (int) y;
        Location location = null;
        Block centerBlock = null;
        loop:
        while (y2 <= 120) {
            centerBlock = world.getBlockAt((int) x, y2, (int) z);
            if (chkRelativeBlock(centerBlock, BlockFace.SELF, 0)) {
                location = new Location(world, x, (double) y2 + 2d, z);
                break loop;
            }

            y2++;
        }
        return location.getBlockY();
    }

    private static boolean chkRelativeBlock(Block block, BlockFace face, int distance){

        Block relativeBlock = block.getRelative(face, distance);

        Block footBlock = relativeBlock.getRelative(BlockFace.UP);
        Block headBlock = relativeBlock.getRelative(BlockFace.UP, 2);

        if(!relativeBlock.isLiquid() && !relativeBlock.isEmpty()){
            if(footBlock.isEmpty() && headBlock.isEmpty()){
                return true;
            }
        }

        return false;
    }
}
