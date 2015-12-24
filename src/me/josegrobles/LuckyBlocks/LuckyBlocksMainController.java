package me.josegrobles.LuckyBlocks;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jose on 24/12/15.
 */
public class LuckyBlocksMainController extends JavaPlugin implements Listener{
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
    @EventHandler
    public void LuckyBlocks(BlockBreakEvent event){
        if (event.getBlock().getType().equals(Material.SPONGE)){
            new LuckyBlockActionController().armour(event);

        }
        else{
            event.getPlayer().sendMessage("Major Key to sucess");
        }
    }
}
