package me.josegrobles.LuckyBlocks;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.material.DirectionalContainer;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.Horse.Variant;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jose on 24/12/15.
 */

@SuppressWarnings("ALL")
public class LuckyBlocksActionController implements Listener{
    private static String coinDropper = "NoString";
    private static int coinLocationX = 0;
    private static World worldPit;
    private static Location pit;
    private static int isUnluckyChestActivated = 0;

    public void EnchantedSword(BlockBreakEvent event){
        Location loc = event.getBlock().getLocation();
        World world = loc.getWorld();
        String[] TypeOfSword = new String[]{"DIAMOND_SWORD","IRON_SWORD","GOLD_SWORD","STONE_SWORD","WOOD_SWORD"};
        int TypeRandomizer = (int)(Math.random()*5);
        ItemStack TheSword = new ItemStack(Material.valueOf(TypeOfSword[TypeRandomizer]));
        int Randomizer = (int)(Math.random()*12);
        TheSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, Randomizer);
        world.dropItemNaturally(loc,TheSword);
    }
    public void EnchantedBow(BlockBreakEvent event){
        Location loc = event.getBlock().getLocation();
        World world = loc.getWorld();
        int Randomizer = (int)(Math.random()*12);
        ItemStack TheBow = new ItemStack(Material.BOW);
        TheBow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, Randomizer);
        TheBow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, Randomizer);
        world.dropItemNaturally(loc,TheBow);
        ItemStack Arrows = new ItemStack(Material.ARROW);
        Arrows.setAmount(64);
        world.dropItemNaturally(loc,Arrows);
    }
    public void SpawnEnemyEntities(BlockBreakEvent event, int Quantity){
        Location loc = event.getBlock().getLocation();
        World world = loc.getWorld();
        String[] TypeOfEntyties = new String[]{"BLAZE","CAVE_SPIDER","CREEPER","ENDERMAN","GIANT","GHAST","PIG_ZOMBIE","SILVERFISH","SLIME","WITCH","ZOMBIE","GUARDIAN"};
        int Randomizer = (int)(Math.random()*TypeOfEntyties.length);
        for(int i=0;i<Quantity;i++){
         double X = loc.getX()-i;
         double Z = loc.getBlockZ()-i;
            Location locModified = new Location(world,X,loc.getY(),Z);
           Creature creature = (Creature) world.spawnEntity(locModified, EntityType.valueOf(TypeOfEntyties[Randomizer]));
            creature.setTarget(event.getPlayer());
        }
    }
    public void LuckyPotion(BlockBreakEvent event){
        Location loc = event.getBlock().getLocation();
        World world = loc.getWorld();
        ItemStack energyPotion = new ItemStack(Material.POTION, 1);
        PotionMeta energyPotionMeta = (PotionMeta) energyPotion.getItemMeta();
        energyPotionMeta.setDisplayName("§6Lucky Potion");
        List<String> lore = new ArrayList<String>();
        lore.add("§6"+ LuckyBlocksMainController.instance.language.getString("POTION_DURATION"));
        energyPotionMeta.setLore(lore);
        energyPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 20*60, 1), true);
        energyPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20*60, 1), true);
        energyPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*60, 1), true);
        energyPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 20*60, 1), true);
        energyPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 20*60, 1), true);
        energyPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*60, 1), true);
        energyPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 20*60, 3), true);
        energyPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 20*60, 3), true);
        energyPotion.setItemMeta(energyPotionMeta);
        Potion po = new Potion((byte) 8258);
        po.apply(energyPotion);
        world.dropItemNaturally(loc,energyPotion);
    }
    public void armour(BlockBreakEvent event){
        Location loc = event.getBlock().getLocation();
        World world = loc.getWorld();
        String[] ArmourTypes = new String[]{"DIAMOND_CHESTPLATE","IRON_CHESTPLATE","CHAINMAIL_CHESTPLATE","DIAMOND_BOOTS","IRON_BOOTS","CHAINMAIL_BOOTS","DIAMOND_HELMET","IRON_HELMET","CHAINMAIL_HELMET","DIAMOND_LEGGINGS","IRON_LEGGINGS","CHAINMAIL_LEGGINGS"};
        int TypeRandomizer = (int)(Math.random()*ArmourTypes.length);
        ItemStack ArmourElement = new ItemStack(Material.valueOf(ArmourTypes[TypeRandomizer]));
        ArmourElement.addUnsafeEnchantment(Enchantment.THORNS, 5);
        ArmourElement.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
        world.dropItemNaturally(loc,ArmourElement);
    }
    public void infiniteDestruction(BlockBreakEvent event){
        int j,k,l;
        Location loc = event.getBlock().getLocation();
        World world = loc.getWorld();
        int X = (event.getBlock().getX())+1;
        int Y = event.getBlock().getY();
        int Z = (event.getBlock().getZ())-1;
        for (j=X;j>X-3;j--){
            for (k=Z;k<Z+3;k++){
                for (l=0;l<Y+1;l++){
                    world.getBlockAt(j,l,k).setType(Material.AIR);
                }
            }
        }
    }
    public void lavaDrop(BlockBreakEvent event){
        int j,k,l,notUse=0;
        Location loc = event.getPlayer().getLocation();
        World world = loc.getWorld();
        int X = (loc.getBlockX())+1;
        int Y = loc.getBlockY();
        int Z = (loc.getBlockZ())-1;
        for (j=X;j>X-3;j--){
            for (k=Z;k<Z+3;k++){
                for (l=Y;l<Y+4;l++){
                    if (notUse!=4) world.getBlockAt(j,l,k).setType(Material.IRON_FENCE);
                    else world.getBlockAt(j,l,k).setType(Material.AIR);
                }
                notUse++;
            }
        }
        world.getBlockAt(loc.getBlockX(),loc.getBlockY()+3,loc.getBlockZ()).setType(Material.LAVA);
    }
    public void AnvilDrop(BlockBreakEvent event){
        int j,k,l,notUse=0;
        Location loc = event.getPlayer().getLocation();
        World world = loc.getWorld();
        int X = (loc.getBlockX())+1;
        int Y = loc.getBlockY();
        int Z = (loc.getBlockZ())-1;
        for (j=X;j>X-3;j--){
            for (k=Z;k<Z+3;k++){
                for (l=Y;l<Y+4;l++){
                    if (notUse!=4) world.getBlockAt(j,l,k).setType(Material.IRON_FENCE);
                    else world.getBlockAt(j,l,k).setType(Material.AIR);
                }
                notUse++;
            }
        }
        for (j=3;j>0;j--){
            world.getBlockAt(loc.getBlockX(),(loc.getBlockY()+80)-j,loc.getBlockZ()).setType(Material.ANVIL);
        }
    }
    public void PrimeTNT(BlockBreakEvent event){
        int j,k,l,notUse=0;
        Location loc = event.getBlock().getLocation();
        World world = loc.getWorld();
        int X = (event.getBlock().getX())+2;
        int Y = event.getBlock().getY();
        int Z = (event.getBlock().getZ())-2;
        for (j=X;j>X-5;j--){
            for (k=Z;k<Z+5;k++){
                for (l=Y+2;l<Y+3;l++){
                    if (notUse==0 || notUse==2 || notUse==4 || notUse==10 || notUse==12 || notUse==14 || notUse==20 || notUse==22 || notUse==24){
                        Location loc2 = new Location(world,j,l,k);
                        world.spawnEntity(loc2,EntityType.PRIMED_TNT);
                    }

                }
                notUse++;
            }
        }
    }
    public void LuckyPit(BlockBreakEvent event){
        int j,k,l,notUse=0,height=0;
        coinDropper = event.getPlayer().getDisplayName();
        coinLocationX = event.getBlock().getX();
        Location loc = event.getBlock().getLocation();
        World world = loc.getWorld();
        int X = (event.getBlock().getX())+2;
        int Y = event.getBlock().getY();
        int Z = (event.getBlock().getZ())-2;
        for (j=X;j>X-5;j--){
            for (k=Z;k<Z+5;k++){
                for (l=Y;l<Y+1;l++){
                    world.getBlockAt(j,l,k).setType(Material.COBBLESTONE);
                }

            }
        }
        int X2 = (event.getBlock().getX())+1;
        int Y2 = event.getBlock().getY();
        int Z2 = (event.getBlock().getZ())-1;
        for (j=X2;j>X2-3;j--){
            for (k=Z2;k<Z2+3;k++){
                for (l=Y2+1;l<Y2+5;l++){
                    if (notUse!=4 && height==0){
                        world.getBlockAt(j,l,k).setType(Material.COBBLESTONE);
                    }
                    else if(height==3){
                        world.getBlockAt(j,l,k).setType(Material.COBBLESTONE);
                    }
                    else {
                        world.getBlockAt(j,l,k).setType(Material.AIR);
                    }
                    height++;
                }
                height=0;
                notUse++;
            }
        }
        notUse = 0;
        for (j=X2;j>X2-3;j--){
            for (k=Z2;k<Z2+3;k++){
                for (l=Y2+2;l<Y2+4;l++){
                    if (notUse==0 || notUse==2 || notUse==6 || notUse==8)world.getBlockAt(j,l,k).setType(Material.FENCE);
                    else world.getBlockAt(j,l,k).setType(Material.AIR);
                }
                notUse++;
            }
        }
        world.getBlockAt(X-2,Y+1,Z+2).setType(Material.STATIONARY_WATER);
        ItemStack coin = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta coinMeta = (ItemMeta) coin.getItemMeta();
        coinMeta.setDisplayName("§6"+ LuckyBlocksMainController.instance.language.getString("WINNER_COIN"));
        List<String> lore = new ArrayList<String>();
        lore.add("§9"+LuckyBlocksMainController.instance.language.getString("COIN_LORE"));
        coinMeta.setLore(lore);
        coin.setItemMeta(coinMeta);
        event.getPlayer().sendMessage("§9"+LuckyBlocksMainController.instance.language.getString("DROP_THE_COIN"));
        event.getPlayer().getInventory().addItem(coin);
        worldPit = event.getBlock().getWorld();
        pit = event.getBlock().getLocation();
                }
    public void coinCheck(PlayerDropItemEvent event){
            int LocationX = event.getItemDrop().getLocation().getBlockX();
            if (coinDropper.equalsIgnoreCase(event.getPlayer().getDisplayName())  && (LocationX == coinLocationX)){
                Location pitDropModifier = new Location(worldPit,pit.getBlockX(),pit.getBlockY()+3,pit.getBlockZ());
                Location Fireworks = new Location(worldPit,pit.getBlockX(),pit.getBlockY()+6,pit.getBlockZ());
                ItemStack Diamonds = new ItemStack(Material.DIAMOND);
                Diamonds.setAmount(40);
                int RandomProbability = (int)(Math.random()*100);
                if (RandomProbability<50){
                    event.getPlayer().sendMessage("§6"+LuckyBlocksMainController.instance.language.getString("LUCKY_THIS_TIME"));
                worldPit.dropItemNaturally(pitDropModifier,Diamonds);
                for (int i=0;i<5;i++) homeFireworks(Fireworks,worldPit);}
                else{
                    TNTPrimed TNT = (TNTPrimed) event.getPlayer().getWorld().spawnEntity(pitDropModifier,EntityType.PRIMED_TNT);
                    TNT.setYield(50);
                    TNT.setFuseTicks(10);
                }
                event.getItemDrop().remove();
            }
        else{
                 event.getPlayer().sendMessage(LuckyBlocksMainController.instance.language.getString("DROP_IT_CLOSER"));
            }
    }
    public void homeFireworks(Location fire, World world) {
        int R = (int)(Math.random() * 255.0);
        int G = (int)(Math.random() * 255.0);
        int B = (int)(Math.random() * 255.0);
        int R2 = (int)(Math.random() * 255.0);
        int G2 = (int)(Math.random() * 255.0);
        int B2 = (int)(Math.random() * 255.0);
        String[] Type2 = new String[]{"BALL", "BALL_LARGE", "BURST", "CREEPER", "STAR"};
        int TypeR = (int)(Math.random() * 5.0);
        Firework f = (Firework)world.spawnEntity(fire, EntityType.FIREWORK);
        FireworkMeta fm = f.getFireworkMeta();
        fm.addEffect(FireworkEffect.builder().flicker(true).trail(true).with(FireworkEffect.Type.valueOf((String)Type2[TypeR])).withColor(Color.fromRGB((int)R, (int)G, (int)B)).withFade(Color.fromRGB((int)R2, (int)G2, (int)B2)).build());
        fm.setPower(2);
        f.setFireworkMeta(fm);
    }
    public void waterStructure(BlockBreakEvent event){
        int j,k,l,notUse=0,height=0;
        Location loc = event.getPlayer().getLocation();
        World world = loc.getWorld();
        int X = (loc.getBlockX())+1;
        int Y = loc.getBlockY();
        int Z = (loc.getBlockZ())-1;
        for (j=X;j>X-3;j--){
            for (k=Z;k<Z+3;k++){
                for (l=Y-1;l<Y+3;l++){
                    if (notUse!=4 && height!= 0 && height!=3 && height!=2) world.getBlockAt(j,l,k).setType(Material.OBSIDIAN);
                    else if (height==2 && (notUse==0 || notUse==2 ||notUse==6 ||notUse==8)) world.getBlockAt(j,l,k).setType(Material.OBSIDIAN);
                    else if (height==0 || height==3) world.getBlockAt(j,l,k).setType(Material.OBSIDIAN);
                    else if (height == 2 && (notUse==1 || notUse==3 ||notUse==5 || notUse==7)) world.getBlockAt(j,l,k).setType(Material.GLASS);
                    else world.getBlockAt(j,l,k).setType(Material.STATIONARY_WATER);
                    height++;
                }
                height=0;
                notUse++;
            }
        }
    }
    public void MobApocalypyse(BlockBreakEvent event){
        int j,k,l,notUse=0,Quantity;
        Location loc = event.getBlock().getLocation();
        World world = loc.getWorld();
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,20*60,1));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,20*60,1));
        int X = (event.getBlock().getX())+2;
        int Y = event.getBlock().getY();
        int Z = (event.getBlock().getZ())-2;
        for (j=X;j>X-5;j--){
            for (k=Z;k<Z+5;k++){
                for (l=Y+2;l<Y+3;l++){
                    if (notUse==0 || notUse==2 || notUse==4 || notUse==10 || notUse==12 || notUse==14 || notUse==20 || notUse==22 || notUse==24){
                        Location loc2 = new Location(world,j,l,k);
                        for (Quantity=0;Quantity<1;Quantity++){
                        world.spawnEntity(loc2,EntityType.CREEPER);
                        world.spawnEntity(loc2,EntityType.ZOMBIE);
                            Horse undead = (Horse) world.spawnEntity(loc,EntityType.HORSE);
                            undead.setVariant(Variant.UNDEAD_HORSE);
                            Horse SKELETON = (Horse) world.spawnEntity(loc,EntityType.HORSE);
                            SKELETON.setVariant(Variant.SKELETON_HORSE);
                        }
                    }

                }
                notUse++;
            }
        }
    }
    public void MagicChest(BlockBreakEvent event){
        Location loc = event.getBlock().getLocation();
        World world = loc.getWorld();
        event.setCancelled(true);
        Block block = event.getBlock();
        block.setType(Material.CHEST);
        DirectionalContainer chestData = (DirectionalContainer) block.getState().getData();
        BlockFace directionInfo = new LuckyBlocksInvokeMethodClass().getCardinalDirection(event.getPlayer());
        chestData.setFacingDirection(directionInfo);
        //Next Line makes use of a Deprecated Method
        block.setData(chestData.getData(),true);
        Chest chest = (Chest) block.getState();
        world.playEffect(loc,Effect.ENDER_SIGNAL,50);
        int RandomSelection = (int) (Math.random()*100);
        if (RandomSelection < 50) {
            ItemStack goldenApples = new ItemStack(Material.GOLDEN_APPLE);
            goldenApples.setAmount(5);
            chest.getInventory().addItem(goldenApples);
        }
        else if (RandomSelection >= 50) isUnluckyChestActivated = 1;

    }
    public void UnluckyMagicChest(PlayerInteractEvent event){
        if (isUnluckyChestActivated == 1){
          Location loc =  event.getClickedBlock().getLocation();
            World world = loc.getWorld();
            world.getBlockAt(loc).setType(Material.AIR);
            TNTPrimed TNT = (TNTPrimed) world.spawnEntity(loc,EntityType.PRIMED_TNT);
            TNT.setFuseTicks(2);
            isUnluckyChestActivated = 0;
        }
    }
    public void RandomItems(BlockBreakEvent event){
        Location loc = event.getBlock().getLocation();
        World world = loc.getWorld();
        String[] Items = new String[]{"WOOD","GLASS","CACTUS","COBBLESTONE","COOKED_BEEF","COOKED_FISH","COOKED_CHICKEN","SAND","DIRT"};
        int randomItemValue = (int) (Math.random()*(Items.length-1));
        int randomItemQuantity = (int) (Math.random()*64);
        ItemStack randomItem = new ItemStack(Material.valueOf(Items[randomItemValue]));
        randomItem.addUnsafeEnchantment(Enchantment.DAMAGE_ALL,randomItemValue);
        randomItem.setAmount(randomItemQuantity);
        world.dropItemNaturally(loc,randomItem);
        world.playEffect(loc,Effect.MOBSPAWNER_FLAMES,100);
        world.playSound(loc, Sound.CAT_MEOW,10,1);
    }
    public void ZombieSpawn(BlockBreakEvent event){
        Location loc = event.getBlock().getLocation();
        World world = loc.getWorld();
        Zombie zombie = (Zombie)  world.spawnEntity(loc,EntityType.ZOMBIE);
        zombie.setCustomName("Bob the Zombie");
        zombie.setCustomNameVisible(true);
        zombie.setMaxHealth(90);
        zombie.setHealth(90);
        zombie.setVillager(true);
        zombie.setTarget(event.getPlayer());
        EntityEquipment zombieEquipment = zombie.getEquipment();
        zombieEquipment.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        zombieEquipment.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        zombieEquipment.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        zombieEquipment.setBoots(new ItemStack(Material.GOLD_BOOTS));
        zombieEquipment.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
    }

}
