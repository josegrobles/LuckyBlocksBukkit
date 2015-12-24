package me.josegrobles.LuckyBlocks;

import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by Jose on 24/12/15.
 */
public class LuckyBlocksProbabilityClass {
    public void selection(BlockBreakEvent e){
        int RandomSelection = (int)(Math.random()*100);
        if (RandomSelection >= 0 && RandomSelection <= 25) new LuckyBlockActionController().LuckyPotion(e);
        else if (RandomSelection >= 26 && RandomSelection <= 52) new LuckyBlockActionController().EnchantedSword(e);
        else if (RandomSelection >= 53 && RandomSelection <= 80) new LuckyBlockActionController().armour(e);
        else if (RandomSelection >= 81 && RandomSelection <= 96){
            int spawnQuantity = (int)(Math.random()*10);
            new LuckyBlockActionController().SpawnEnemyEntities(e,spawnQuantity);
        }
        else new LuckyBlockActionController().infiniteDestruction(e);




    }
}
