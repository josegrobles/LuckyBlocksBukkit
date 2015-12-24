package me.josegrobles.LuckyBlocks;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jose on 24/12/15.
 */
public class LuckyBlockActionController {
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
    public void SpawnEnemyEntities(BlockBreakEvent event, int Quantity){
        Location loc = event.getBlock().getLocation();
        World world = loc.getWorld();
        String[] TypeOfEntyties = new String[]{"BLAZE","CAVE_SPIDER","CREEPER","ENDERMAN","GIANT","GHAST","MAGMA_CUBE","PIG_ZOMBIE","SILVERFISH","SLIME","WITCH","ZOMBIE"};
        int Randomizer = (int)(Math.random()*TypeOfEntyties.length);
        for(int i=0;i<Quantity;i++){
         double X = loc.getX()-i;
         double Z = loc.getBlockZ()-i;
            Location locModified = new Location(world,X,loc.getY(),Z);
        world.spawnEntity(locModified, EntityType.valueOf(TypeOfEntyties[Randomizer]));
        }
    }
    public void LuckyPotion(BlockBreakEvent event){
        Location loc = event.getBlock().getLocation();
        World world = loc.getWorld();
        ItemStack energyPotion = new ItemStack(Material.POTION, 1);
        PotionMeta energyPotionMeta = (PotionMeta) energyPotion.getItemMeta();
        energyPotionMeta.setDisplayName("§6Lucky Potion");
        List<String> lore = new ArrayList<String>();
        lore.add("§6Duration:§e 1 minute");
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
}
