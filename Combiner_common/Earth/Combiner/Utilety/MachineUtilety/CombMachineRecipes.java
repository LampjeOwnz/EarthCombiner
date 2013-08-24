package Earth.Combiner.Utilety.MachineUtilety;

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

public class CombMachineRecipes
{
    private static final CombMachineRecipes combiningBase = new CombMachineRecipes();

    /** De lijst voor recipes en experience. */
    private Map experienceList = new HashMap();
    private Map CombiningCombList = new HashMap();
    private HashMap<List<Integer>, ItemStack> CombiningCombOneSlot = new HashMap<List<Integer>, ItemStack>();
    private HashMap<List<Integer>, ItemStack> CombiningCombTwoSlots = new HashMap<List<Integer>, ItemStack>();
    private HashMap<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();

    /** Dit word gebruikt voor getCombiningResults. */
    public static final CombMachineRecipes smelting()
    {
        return combiningBase;
    }

    /** De recipes natuurlijk. */
    private CombMachineRecipes()
    {
    	// One Slot recipes
    	this.addCombiningOneSlot(new ItemStack(Block.oreIron), new ItemStack(Item.ingotIron), 0.7F);
    	this.addCombiningOneSlot(new ItemStack(Block.oreGold), new ItemStack(Item.ingotGold), 1.0F);
        this.addCombiningOneSlot(new ItemStack(Block.oreDiamond), new ItemStack(Item.diamond), 1.0F);
        this.addCombiningOneSlot(new ItemStack(Block.sand), new ItemStack(Block.glass), 0.1F);
        this.addCombiningOneSlot(new ItemStack(Item.porkRaw), new ItemStack(Item.porkCooked), 0.35F);
        this.addCombiningOneSlot(new ItemStack(Item.beefRaw), new ItemStack(Item.beefCooked), 0.35F);
        this.addCombiningOneSlot(new ItemStack(Item.chickenRaw), new ItemStack(Item.chickenCooked), 0.35F);
        this.addCombiningOneSlot(new ItemStack(Item.fishRaw), new ItemStack(Item.fishCooked), 0.35F);
        this.addCombiningOneSlot(new ItemStack(Block.cobblestone), new ItemStack(Block.stone), 0.1F);
        this.addCombiningOneSlot(new ItemStack(Item.clay), new ItemStack(Item.brick), 0.3F);
        this.addCombiningOneSlot(new ItemStack(Block.blockClay), new ItemStack(Block.field_111032_cD), 0.35F);
        this.addCombiningOneSlot(new ItemStack(Block.cactus), new ItemStack(Item.dyePowder, 1, 2), 0.2F);
        this.addCombiningOneSlot(new ItemStack(Block.wood), new ItemStack(Item.coal, 1, 1), 0.15F);
        this.addCombiningOneSlot(new ItemStack(Block.oreEmerald), new ItemStack(Item.emerald), 1.0F);
        this.addCombiningOneSlot(new ItemStack(Item.potato), new ItemStack(Item.bakedPotato), 0.35F);
        this.addCombiningOneSlot(new ItemStack(Block.netherrack), new ItemStack(Item.netherrackBrick), 0.1F);
        this.addCombiningOneSlot(new ItemStack(Block.oreCoal), new ItemStack(Item.coal), 0.1F);
        this.addCombiningOneSlot(new ItemStack(Block.oreRedstone), new ItemStack(Item.redstone), 0.7F);
        this.addCombiningOneSlot(new ItemStack(Block.oreLapis), new ItemStack(Item.dyePowder, 1, 4), 0.2F);
        this.addCombiningOneSlot(new ItemStack(Block.oreNetherQuartz), new ItemStack(Item.netherQuartz), 0.2F);
    	
    	// Two Slot recipes
    	this.addCombiningTwoSlots(new ItemStack(Block.planks), new ItemStack(Block.cobblestone), new ItemStack(InitItemTools.CompWoodScrap,1), 0.7F);
    	this.addCombiningTwoSlots(new ItemStack(Item.coal), new ItemStack(Block.cobblestone), new ItemStack(InitItemTools.CompCoalScrap,1), 0.7F);
    	this.addCombiningTwoSlots(new ItemStack(Block.glass), new ItemStack(Block.cobblestone), new ItemStack(InitBlocks.StoneGlass,1), 0.7F);
    }

    /** Deze voegt de recipes en de exp in de bij behorende lijsten. */
    public void addCombiningOneSlot(ItemStack slot1, ItemStack out, float par3)
    {
     	this.CombiningCombOneSlot.put(Arrays.asList(slot1.itemID), out);
     	this.experienceList.put(Integer.valueOf(out.itemID), Float.valueOf(par3));
    }
    
    /** Deze voegt de recipes en de exp in de bij behorende lijsten. */
    public void addCombiningTwoSlots(ItemStack slot1, ItemStack slot2, ItemStack out, float par3)
    {
     	this.CombiningCombTwoSlots.put(Arrays.asList(slot1.itemID, slot2.itemID), out);
     	this.experienceList.put(Integer.valueOf(out.itemID), Float.valueOf(par3));
    }
    
    /** Dit is nodig om het exp te krijgen. */
    @Deprecated //In favor of ItemStack sensitive version
    public float getExperience(int par1)
    {
        return this.experienceList.containsKey(Integer.valueOf(par1)) ? ((Float)this.experienceList.get(Integer.valueOf(par1))).floatValue() : 0.0F;
    }
    
    /** Dit is nodig om de ouput te krijgen van de recipe input. */
    public ItemStack getCombiningResultOneSlot(ItemStack slot1) 
    {
        if (slot1 == null){ return null;}
        ItemStack ret = (ItemStack)CombiningCombOneSlot.get(Arrays.asList(slot1.itemID));
        if (ret != null) 
        {
            return ret;
        }
        return (ItemStack)CombiningCombOneSlot.get(Arrays.asList(slot1.itemID));
    }
    
    /** Dit is nodig om de ouput te krijgen van de recipe input. */
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


