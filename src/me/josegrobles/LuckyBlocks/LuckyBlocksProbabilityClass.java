package me.josegrobles.LuckyBlocks;

import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by Jose on 24/12/15.
 */

public class LuckyBlocksProbabilityClass {
    public void Selection(BlockBreakEvent e){
        int RandomSelection = (int)(Math.random()*100);
        if (RandomSelection >= 0 && RandomSelection <= 10) new LuckyBlockActionController().LuckyPotion(e);
        else if (RandomSelection >= 11 && RandomSelection <= 15) new LuckyBlockActionController().EnchantedSword(e);
        else if (RandomSelection >= 16 && RandomSelection <= 20) new LuckyBlockActionController().EnchantedBow(e);
        else if (RandomSelection >= 21 && RandomSelection <= 40) new LuckyBlockActionController().RandomItems(e);
        else if (RandomSelection >= 41 && RandomSelection <= 52) new LuckyBlockActionController().armour(e);
        else if (RandomSelection >= 53 && RandomSelection <= 67){
            int spawnQuantity = (int)(Math.random()*10);
            new LuckyBlockActionController().SpawnEnemyEntities(e,spawnQuantity);
        }
        else if (RandomSelection >= 68 && RandomSelection <= 70) new LuckyBlockActionController().AnvilDrop(e);
        else if (RandomSelection >= 71 && RandomSelection <= 74) new LuckyBlockActionController().lavaDrop(e);
        else if (RandomSelection >= 75 && RandomSelection <= 82) new LuckyBlockActionController().LuckyPit(e);
        else if (RandomSelection >= 83 && RandomSelection <= 87) new LuckyBlockActionController().PrimeTNT(e);
        else if (RandomSelection >= 88 && RandomSelection <= 90) new LuckyBlockActionController().waterStructure(e);
        else if (RandomSelection >= 91 && RandomSelection <= 94) new LuckyBlockActionController().MobApocalypyse(e);
        else if (RandomSelection >= 95 && RandomSelection <= 97) new LuckyBlockActionController().MagicChest(e);
        else new LuckyBlockActionController().infiniteDestruction(e);




    }
}
