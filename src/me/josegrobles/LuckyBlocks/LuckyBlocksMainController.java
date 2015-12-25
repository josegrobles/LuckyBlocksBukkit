package me.josegrobles.LuckyBlocks;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

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
            new LuckyBlocksProbabilityClass().selection(event);
        }
    }
}
