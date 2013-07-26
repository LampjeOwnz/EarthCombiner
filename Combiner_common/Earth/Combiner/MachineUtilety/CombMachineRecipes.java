package Earth.Combiner.MachineUtilety;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import Earth.Combiner.CombinerCore;
import Earth.Combiner.Blocks.InitBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CombMachineRecipes
{
    private static final CombMachineRecipes combiningBase = new CombMachineRecipes();

    /** De lijst voor recipes en experience. */
    private Map experienceList = new HashMap();
    private HashMap<List<Integer>, ItemStack> CombiningCombList = new HashMap<List<Integer>, ItemStack>();
    private HashMap<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();

    /** Dit word gebruikt voor getCombiningResults. */
    public static final CombMachineRecipes smelting()
    {
        return combiningBase;
    }

    /** De recipes natuurlijk. */
    private CombMachineRecipes()
    {
    	this.addCombining(new ItemStack(Block.planks), new ItemStack(Block.cobblestone), new ItemStack(InitBlocks.StonePlank,1), 0.7F);
    	this.addCombining(new ItemStack(Block.glass), new ItemStack(Block.cobblestone), new ItemStack(InitBlocks.StoneGlass,1), 0.7F);    	
    }

    /** Deze voegt de recipes en de exp in de bij behorende lijsten. */
    public void addCombining(ItemStack slot1, ItemStack slot2, ItemStack slot3, float par3)
    {
    	this.CombiningCombList.put(Arrays.asList(slot1.itemID, slot2.itemID), slot3);
    	this.experienceList.put(Integer.valueOf(slot3.itemID), Float.valueOf(par3));
    }
    
    /** Dit is nodig om het exp te krijgen. */
    @Deprecated //In favor of ItemStack sensitive version
    public float getExperience(int par1)
    {
        return this.experienceList.containsKey(Integer.valueOf(par1)) ? ((Float)this.experienceList.get(Integer.valueOf(par1))).floatValue() : 0.0F;
    }
    
    /** Dit is nodig om de ouput te krijgen van de recipe input. */
    public ItemStack getCombiningResult(ItemStack slot1, ItemStack slot2) 
    {
        if (slot1 == null){ return null;}
        if (slot2 == null){ return null;}
        ItemStack ret = (ItemStack)CombiningCombList.get(Arrays.asList(slot1.itemID, slot2.itemID));
        if (ret != null) 
        {
            return ret;
        }
        return (ItemStack)CombiningCombList.get(Arrays.asList(slot1.itemID, slot2.itemID));
    }

    /** Hier pakt hij het exp van de recipe (0.7F). */
    public float getExperience(ItemStack item)
    {
        if (item == null || item.getItem() == null)
        {
            return 0;
        }
        float ret = item.getItem().getSmeltingExperience(item);
        if (ret < 0 && metaExperience.containsKey(Arrays.asList(item.itemID, item.getItemDamage())))
        {
            ret = metaExperience.get(Arrays.asList(item.itemID, item.getItemDamage()));
        }
        if (ret < 0 && experienceList.containsKey(item.itemID))
        {
            ret = ((Float)experienceList.get(item.itemID)).floatValue();
        }
        return (ret < 0 ? 0 : ret);
    }

    /** De lijsten vast stellen. */
    public Map<List<Integer>, ItemStack> getMetaSmeltingList()
    {
        return CombiningCombList;
    }
}


