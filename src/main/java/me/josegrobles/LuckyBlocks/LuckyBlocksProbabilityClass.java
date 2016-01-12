package me.josegrobles.LuckyBlocks;

import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by Jose on 24/12/15.
 */

public class LuckyBlocksProbabilityClass {
    public void Selection(BlockBreakEvent e){
        int RandomSelection = (int)(Math.random()*100);
        if (RandomSelection >= 0 && RandomSelection <= 10) new LuckyBlocksActionController().LuckyPotion(e);
        else if (RandomSelection >= 11 && RandomSelection <= 15) new LuckyBlocksActionController().EnchantedSword(e);
        else if (RandomSelection >= 16 && RandomSelection <= 20) new LuckyBlocksActionController().EnchantedBow(e);
        else if (RandomSelection >= 21 && RandomSelection <= 30) new LuckyBlocksActionController().RandomItems(e);
        else if (RandomSelection >= 31 && RandomSelection <= 40) new LuckyBlocksActionController().RandomItems(e);
        else if (RandomSelection >= 41 && RandomSelection <= 52) new LuckyBlocksActionController().armour(e);
        else if (RandomSelection >= 53 && RandomSelection <= 60){
            int spawnQuantity = (int)(Math.random()*10);
            new LuckyBlocksActionController().SpawnEnemyEntities(e,spawnQuantity);
        }
        else if (RandomSelection >= 61 && RandomSelection <= 67)  new LuckyBlocksActionController().ZombieSpawn(e);
        else if (RandomSelection >= 68 && RandomSelection <= 70) new LuckyBlocksActionController().AnvilDrop(e);
        else if (RandomSelection >= 71 && RandomSelection <= 74) new LuckyBlocksActionController().lavaDrop(e);
        else if (RandomSelection >= 75 && RandomSelection <= 82) new LuckyBlocksActionController().LuckyPit(e);
        else if (RandomSelection >= 83 && RandomSelection <= 87) new LuckyBlocksActionController().PrimeTNT(e);
        else if (RandomSelection >= 88 && RandomSelection <= 90) new LuckyBlocksActionController().waterStructure(e);
        else if (RandomSelection >= 91 && RandomSelection <= 94) new LuckyBlocksActionController().MobApocalypyse(e);
        else if (RandomSelection >= 95 && RandomSelection <= 97) new LuckyBlocksActionController().MagicChest(e);
        else new LuckyBlocksActionController().infiniteDestruction(e);
    }
}
