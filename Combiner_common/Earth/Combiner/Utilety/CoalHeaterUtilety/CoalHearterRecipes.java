package Earth.Combiner.Utilety.CoalHeaterUtilety;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//import Earth.Combiner.CombinerCore;
import Earth.Combiner.Blocks.InitBlocks;
import Earth.Combiner.ItemTools.InitItemTools;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CoalHearterRecipes
{
    private static final CoalHearterRecipes combiningBase = new CoalHearterRecipes();

    /** De lijst voor recipes en experience. */
    //private Map experienceList = new HashMap();
    private Map WaterLists = new HashMap();
    private HashMap<List<Integer>, ItemStack> WaterSlot = new HashMap<List<Integer>, ItemStack>();
    private HashMap<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();

    /** Dit word gebruikt voor getCombiningResults. */
    public static final CoalHearterRecipes smelting()
    {
        return combiningBase;
    }

    /** De recipes natuurlijk. */
    private CoalHearterRecipes()
    {
    	this.addWaterSlot(new ItemStack(Item.bucketWater), new ItemStack(Item.bucketEmpty));
    }

    /** Deze voegt de recipes en de exp in de bij behorende lijsten. */
    public void addWaterSlot(ItemStack slot1, ItemStack out)
    {
     	this.WaterSlot.put(Arrays.asList(slot1.itemID), out);
    }
    
    
    /** Dit is nodig om het exp te krijgen. */
    /*
    @Deprecated //In favor of ItemStack sensitive version
    public float getExperience(int par1)
    {
        return this.experienceList.containsKey(Integer.valueOf(par1)) ? ((Float)this.experienceList.get(Integer.valueOf(par1))).floatValue() : 0.0F;
    }
    */
    
    /** Dit is nodig om de ouput te krijgen van de recipe input. */
    public ItemStack getWaterBucket(ItemStack slot1) 
    {
        if (slot1 == null){ return null;}
        ItemStack ret = (ItemStack)WaterSlot.get(Arrays.asList(slot1.itemID));
        if (ret != null) 
        {
            return ret;
        }
        return (ItemStack)WaterSlot.get(Arrays.asList(slot1.itemID));
    }
    
    /** Dit is nodig om de ouput te krijgen van de recipe input. */
    /*
    public ItemStack getCombiningResultTwoSlots(ItemStack slot1, ItemStack slot2) 
    {
        if (slot1 == null){ return null;}
        if (slot2 == null){ return null;}
        else
        {
        	ItemStack ret = (ItemStack)CombiningCombTwoSlots.get(Arrays.asList(slot1.itemID, slot2.itemID));
        	if (ret != null) 
        	{
            	return ret;
        	}
        }
        return (ItemStack)CombiningCombTwoSlots.get(Arrays.asList(slot1.itemID, slot2.itemID));
    }
    */

    /** Hier pakt hij het exp van de recipe (0.7F). */
    /*
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
    */

    /** De lijsten vast stellen. */
    public Map<List<Integer>, ItemStack> getMetaSmeltingList()
    {
        return WaterLists;
    }
}


