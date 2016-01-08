package me.josegrobles.LuckyBlocks;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

/**
 * Created by Jose on 26/12/15.
 */
public class LuckyBlocksInvokeMethodClass {
    public static BlockFace getCardinalDirection(Player player) {
        BlockFace dir = null;

        float y = player.getLocation().getYaw();

        if( y < 0 ){y += 360;}

        y %= 360;

        int i = (int)((y+8) / 22.5);

        if(i == 0){dir = BlockFace.NORTH;}
        else if(i == 1){dir = BlockFace.NORTH;}
        else if(i == 2){dir = BlockFace.EAST;}
        else if(i == 3){dir = BlockFace.EAST;}
        else if(i == 4){dir = BlockFace.EAST;}
        else if(i == 5){dir = BlockFace.EAST;}
        else if(i == 6){dir = BlockFace.EAST;}
        else if(i == 7){dir = BlockFace.SOUTH;}
        else if(i == 8){dir = BlockFace.SOUTH;}
        else if(i == 9){dir = BlockFace.SOUTH;}
        else if(i == 10){dir = BlockFace.WEST;}
        else if(i == 11){dir = BlockFace.WEST;}
        else if(i == 12){dir = BlockFace.WEST;}
        else if(i == 13){dir = BlockFace.WEST;}
        else if(i == 14){dir = BlockFace.WEST;}
        else if(i == 15){dir = BlockFace.NORTH;}
        else {dir = BlockFace.NORTH;}

        return dir;
    }
}
