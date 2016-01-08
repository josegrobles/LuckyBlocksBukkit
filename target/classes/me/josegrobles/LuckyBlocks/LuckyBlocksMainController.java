package me.josegrobles.LuckyBlocks;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.io.*;
import java.util.List;


/**
 * Created by Jose on 24/12/15.
 */
public class LuckyBlocksMainController extends JavaPlugin implements Listener {
    public static LuckyBlocksMainController instance;

    public static File configf, languagef;
    public static FileConfiguration config, language;

    ScoreboardManager manager,manager2;
    public static Scoreboard healthScoreboard, killsScoreboard;
    public static Objective healthObjective, killsObjective;
    @Override
    public void onEnable() {
        instance = this;
        createFiles();
        getServer().getPluginManager().registerEvents(this, this);
        manager = Bukkit.getScoreboardManager();
        manager2 = Bukkit.getScoreboardManager();
        healthScoreboard = manager.getNewScoreboard();
        healthObjective = healthScoreboard.registerNewObjective("showhealth", "health");
        healthObjective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        healthObjective.setDisplayName("/ 20");
        killsScoreboard = manager2.getNewScoreboard();
        killsObjective = killsScoreboard.registerNewObjective("playersKills","deathCount");
        killsObjective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        killsObjective.setDisplayName("Kills");
        for(Player player: Bukkit.getOnlinePlayers()){
            player.setScoreboard(killsScoreboard);
        }
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }
    }
    @Override
    public void onDisable(){
        instance = null;

    }
    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        if (this.config.getBoolean("RESOURCE_PACK_AUTOMATIC_DOWNLOAD")) {
            e.getPlayer().sendMessage("§6"+language.getString("RESOURCE_PACK_LOADING"));
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                public void run() {

                    e.getPlayer().setResourcePack("http://bicimap.azurewebsites.net/LuckyBlocks.zip");
                }
            }, 20L);
        }
        playersHealth(healthScoreboard, e.getPlayer());
    }


    @EventHandler
    public void LuckyBlocks(BlockBreakEvent event) {
        if (event.getBlock().getType().equals(Material.SPONGE) && event.getPlayer().hasPermission("LuckyBlocks.use")) {
            event.setCancelled(true);
            event.getBlock().setType(Material.AIR);
            new LuckyBlocksProbabilityClass().Selection(event);

        }
    }

    @EventHandler
    public void onCoinDrop(PlayerDropItemEvent event) {
        if (event.getItemDrop().getName().equalsIgnoreCase("item.item.goldnugget")) {
            new LuckyBlocksActionController().coinCheck(event);
        }
    }

    @EventHandler
    public void onChestOpen(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        else if (event.getClickedBlock().getType() == Material.CHEST && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            new LuckyBlocksActionController().UnluckyMagicChest(event);
        }
    }

    @EventHandler
    public void onTNTExplosion(EntityExplodeEvent event) {
        if (event.getEntityType() == EntityType.PRIMED_TNT || event.getEntityType() == EntityType.CREEPER) {
            List<Block> blocks = event.blockList();
            for (int i = 0; i < blocks.size(); i++) {
                Block b = blocks.get(i);
                if (b.getType() == Material.SPONGE)
                    event.blockList().remove(b);
            }
        }
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("resetscoreboard") && ((Player) sender).getPlayer().hasPermission("LuckyBlocks.resetScoreboard")) {
            //Using Deprecated methods (UPDATE)
            for (OfflinePlayer player : killsScoreboard.getPlayers()) {
                killsScoreboard.resetScores(player);

            }
        return true;
        }
        if(cmd.getName().equalsIgnoreCase("language") && ((Player) sender).getPlayer().hasPermission("LuckyBlocks.languageSet")){
            if (args.length > 0){
                if (args[0].equals("EN")){
                    copy(getResource("language_EN.yml"), languagef);
                    getLogger().info("Loading English language");
                    this.getConfig().set("LANGUAGE","EN");
                    this.saveConfig();
                }
                else if(args[0].equals("ES")){
                    copy(getResource("language_ES.yml"), languagef);
                    getLogger().info("Cargando el idioma castellano");
                    this.getConfig().set("LANGUAGE","ES");
                    this.saveConfig();
                }
                else return false;
                try {
                    language.load(languagef);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ((Player) sender).getPlayer().sendMessage(language.getString("LANGUAGE_LOADING"));
                return true;
            }
            else return false;
        }
        return false;
    }
    public void playersHealth(Scoreboard board, Player player) {
            player.setScoreboard(board);
            player.setHealth(player.getHealth()); //Update their health
    }
    public void createFiles() {

        configf = new File(getDataFolder(), "config.yml");
        languagef = new File(getDataFolder(), "language.yml");

        if (!configf.exists()) {
            configf.getParentFile().mkdirs();
            copy(getResource("config.yml"), configf);
        }
        if (!languagef.exists()) {
            languagef.getParentFile().mkdirs();
            copy(getResource("language_EN.yml"), languagef);
        }

        config = new YamlConfiguration();
        language = new YamlConfiguration();
        try {
            config.load(configf);
            language.load(languagef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (languagef.exists()){
            if (config.getString("LANGUAGE").equals("EN")){
                copy(getResource("language_EN.yml"), languagef);
                getLogger().info("Loading English language");

            }
            else if(config.getString("LANGUAGE").equals("ES")){
                copy(getResource("language_ES.yml"), languagef);
                getLogger().info("Cargando el idioma castellano");
            }
            try {
                language.load(languagef);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void copy(InputStream in, File file) {

        try {

            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {

                out.write(buf, 0, len);

            }
            out.close();
            in.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}



