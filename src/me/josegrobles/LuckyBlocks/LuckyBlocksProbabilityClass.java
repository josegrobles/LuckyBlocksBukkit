package me.josegrobles.LuckyBlocks;

import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by Jose on 24/12/15.
 */

public class LuckyBlocksProbabilityClass {
    public void selection(BlockBreakEvent e){
        int RandomSelection = (int)(Math.random()*100);
        if (RandomSelection >= 0 && RandomSelection <= 15) new LuckyBlockActionController().LuckyPotion(e);
        else if (RandomSelection >= 16 && RandomSelection <= 35) new LuckyBlockActionController().EnchantedSword(e);
        else if (RandomSelection >= 36 && RandomSelection <= 63) new LuckyBlockActionController().armour(e);
        else if (RandomSelection >= 64 && RandomSelection <= 72){
            int spawnQuantity = (int)(Math.random()*10);
            new LuckyBlockActionController().SpawnEnemyEntities(e,spawnQuantity);
        }
        else if (RandomSelection >= 73 && RandomSelection <= 82) new LuckyBlockActionController().AnvilDrop(e);
        else if (RandomSelection >= 83 && RandomSelection <= 90) new LuckyBlockActionController().lavaDrop(e);
        else if (RandomSelection >= 91 && RandomSelection <= 96) new LuckyBlockActionController().PrimeTNT(e);
        else new LuckyBlockActionController().infiniteDestruction(e);




    }
}
