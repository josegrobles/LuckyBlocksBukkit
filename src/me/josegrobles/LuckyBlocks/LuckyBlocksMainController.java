package me.josegrobles.LuckyBlocks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Level;


/**
 * Created by Jose on 24/12/15.
 */
public class LuckyBlocksMainController extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

    }
    @EventHandler
    public void PlayerJoin(final PlayerJoinEvent e){
        e.getPlayer().sendMessage("§6Cargando Pack de Texturas...");

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            public void run() {

                e.getPlayer().setResourcePack("http://bicimap.azurewebsites.net/LuckyBlocks.zip");
            }
        }, 20L);


    }



    @EventHandler
    public void LuckyBlocks(BlockBreakEvent event) {
        if (event.getBlock().getType().equals(Material.SPONGE)) {
            event.setCancelled(true);
            event.getBlock().setType(Material.AIR);
            new LuckyBlocksProbabilityClass().Selection(event);
        }
    }

    @EventHandler
    public void coinDropEvent(PlayerDropItemEvent event) {
        if (event.getItemDrop().getName().equalsIgnoreCase("item.item.goldnugget")) {
            new LuckyBlockActionController().coinCheck(event);
        }
    }

    @EventHandler
    public void chestOpenEvent(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        else if (event.getClickedBlock().getType() == Material.CHEST && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            new LuckyBlockActionController().UnluckyMagicChest(event);
        }
    }
    @EventHandler
    public void onTNTExplosion(EntityExplodeEvent event) {
        if(event.getEntityType() == EntityType.PRIMED_TNT || event.getEntityType() == EntityType.CREEPER) {
            List<Block> blocks = event.blockList();
            for(int i=0; i < blocks.size(); i++) {
                Block b = blocks.get(i);
                if(b.getType() == Material.SPONGE)
                    event.blockList().remove(b);
            }
        }
    }
}



