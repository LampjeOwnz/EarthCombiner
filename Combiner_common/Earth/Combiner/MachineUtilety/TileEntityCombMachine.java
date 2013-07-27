package Earth.Combiner.MachineUtilety;

import Earth.Combiner.CombinerCore;
import Earth.Combiner.Blocks.CombMachine;
import Earth.Combiner.ItemTools.InitItemTools;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ForgeDummyContainer;

public class TileEntityCombMachine extends TileEntity implements ISidedInventory
{
	/** toegankelijk heid van de kanten (ik denk voor andere mods zoals buildcraft enzo). */
    private static final int[] slots_top = new int[] {0};
    private static final int[] slots_bottom = new int[] {2, 1};
    private static final int[] slots_sides = new int[] {1};

    /** Hoeveleheid slots. */
    private ItemStack[] combmachineItemStacks = new ItemStack[5];

    /** mechaniek variabelen */
    public int combmachineCombTime;
    public int currentItemCombTime;
    public int combmachineCombDCurTime;
    public int NumberOfSlots;
    //public int ReduceTime = 0;
    public int Timer = 200;
    private String field_94130_e;
 
    public int getSizeInventory()
    {
        return this.combmachineItemStacks.length;
    }

    public ItemStack getStackInSlot(int par1)
    {
        return this.combmachineItemStacks[par1];
    }

    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.combmachineItemStacks[par1] != null)
        {
            ItemStack itemstack;

            if (this.combmachineItemStacks[par1].stackSize <= par2)
            {
                itemstack = this.combmachineItemStacks[par1];
                this.combmachineItemStacks[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.combmachineItemStacks[par1].splitStack(par2);

                if (this.combmachineItemStacks[par1].stackSize == 0)
                {
                    this.combmachineItemStacks[par1] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /** Als je hem sloopt dropt hij de items. */
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.combmachineItemStacks[par1] != null)
        {
            ItemStack itemstack = this.combmachineItemStacks[par1];
            this.combmachineItemStacks[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.combmachineItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    public String getInvName()
    {
        return this.isInvNameLocalized() ? this.field_94130_e : "Combiner Machine";
    }

    public boolean isInvNameLocalized()
    {
        return this.field_94130_e != null && this.field_94130_e.length() > 0;
    }

    public void setGuiDisplayName(String par1Str)
    {
        this.field_94130_e = par1Str;
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.combmachineItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.combmachineItemStacks.length)
            {
                this.combmachineItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.combmachineCombTime = par1NBTTagCompound.getShort("CombTime");
        this.combmachineCombDCurTime = par1NBTTagCompound.getShort("CombDurTime");
        this.currentItemCombTime = getItemCombTime(this.combmachineItemStacks[1]);
        //this.ReduceTime = getItemReduceTime(this.combmachineItemStacks[4]);

        if (par1NBTTagCompound.hasKey("Combiner Machine"))
        {
            this.field_94130_e = par1NBTTagCompound.getString("Combiner Machine");
        }
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("CombTime", (short)this.combmachineCombTime);
        par1NBTTagCompound.setShort("CombDurTime", (short)this.combmachineCombDCurTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.combmachineItemStacks.length; ++i)
        {
            if (this.combmachineItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.combmachineItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

        if (this.isInvNameLocalized())
        {
            par1NBTTagCompound.setString("Combiner Machine", this.field_94130_e);
        }
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    @SideOnly(Side.CLIENT)
    public int getCombDurProgressScaled(int par1)
    {
    	int timer;
    	ItemStack Items = this.combmachineItemStacks[4];
        if (Items != null)
        {
        	timer = getItemReduceTime(this.combmachineItemStacks[4]);
        }
        else
        {
        	timer = 0;
        }
        //return this.combmachineCombDCurTime * par1 / (200 - timer); 	
    	if ((this.combmachineCombDCurTime >= 50) && (timer == 150))
    	{
    		 return this.combmachineCombDCurTime * par1 / 200;
    	}
    	else if ((this.combmachineCombDCurTime >= 100) && (timer == 100))
    	{
    		return this.combmachineCombDCurTime * par1 / 200;
    	}
    	else if ((this.combmachineCombDCurTime >= 150) && (timer == 50))
    	{
    		return this.combmachineCombDCurTime * par1 / 200;
    	}
    	else
    	{
    		return this.combmachineCombDCurTime * par1 / (200 - timer);
    	}
    }

    @SideOnly(Side.CLIENT)
    public int getCombTimeRemainingScaled(int par1)
    {
        if (this.currentItemCombTime == 0)
        {
            this.currentItemCombTime = 200;
        }

        return this.combmachineCombTime * par1 / this.currentItemCombTime;
    }

    public boolean isCombining()
    {
        return this.combmachineCombTime > 0;
    }

    /** Als hij "fuel" en een recipe heeft */
    public void updateEntity()
    {
    	boolean flag = this.combmachineCombTime > 0;
        boolean flag1 = false;

        if (this.combmachineCombTime > 0)
        {
            --this.combmachineCombTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.combmachineCombTime == 0)
            {
            	if(this.canCombine())
            	{
                    this.currentItemCombTime = this.combmachineCombTime = getItemCombTime(this.combmachineItemStacks[1]);
                    
                    if (this.combmachineCombTime > 0)
                    {
                        flag1 = true;

                        if (this.combmachineItemStacks[1] != null)
                        {
                            --this.combmachineItemStacks[1].stackSize;

                            if (this.combmachineItemStacks[1].stackSize == 0)
                            {
                                this.combmachineItemStacks[1] = this.combmachineItemStacks[1].getItem().getContainerItemStack(combmachineItemStacks[1]);
                            }
                        }
                    }
            	}              
            }

            if (this.isCombining() && this.canCombine())
            {
            	int ReduceTime;
            	ItemStack reducer = this.combmachineItemStacks[4];
                if (reducer != null)
                {
                	ReduceTime = getItemReduceTime(this.combmachineItemStacks[4]);
                }
                else
                {
                	ReduceTime = 0;
                }
                
                ++this.combmachineCombDCurTime;
                
                if ((this.combmachineCombDCurTime >= 50) && (ReduceTime == 150))
                {
                	if (this.combmachineCombDCurTime == 200) 
                	{
                		this.combmachineCombDCurTime = 0;
                		this.smeltItem();
                		flag1 = true;
                	}
                }
                else 
                {
                	if (this.combmachineCombDCurTime == 200 - ReduceTime) 
                	{
                		this.combmachineCombDCurTime = 0;
                		this.smeltItem();
                		flag1 = true;
                	}
                }
                if ((this.combmachineCombDCurTime >= 95) && (ReduceTime == 100))
                {
                	if (this.combmachineCombDCurTime == 200) 
                	{
                		this.combmachineCombDCurTime = 0;
                		this.smeltItem();
                		flag1 = true;
                	}
                }
                else
                {
                	if (this.combmachineCombDCurTime == 200 - ReduceTime) 
                	{
                		this.combmachineCombDCurTime = 0;
                		this.smeltItem();
                		flag1 = true;
                	}
                }
                if ((this.combmachineCombDCurTime >= 145) && (ReduceTime == 50))
                {
                	if (this.combmachineCombDCurTime == 200) 
                	{
                		this.combmachineCombDCurTime = 0;
                		this.smeltItem();
                		flag1 = true;
                	}
                }
                else
                {
                	if (this.combmachineCombDCurTime == 200 - ReduceTime) 
                	{
                		this.combmachineCombDCurTime = 0;
                		this.smeltItem();
                		flag1 = true;
                	}
                }
            }
            else
            {
                this.combmachineCombDCurTime = 0;
            }

            if (flag != this.combmachineCombTime > 0)
            {
                flag1 = true;                
            }
            
            if(flag1)
            {
            	this.onInventoryChanged();
            	CombMachine.updateCombMachineBlockState(this.combmachineCombTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1)
        {
            this.onInventoryChanged();
        }
    }

    /** Hier pakt hij de recipe en of hij aan mag of niet. */
    private boolean canCombine()
    {
    	this.NumberOfSlots = 0;
        if (this.combmachineItemStacks[0] == null)
        {
            return false;
        }
        else if (this.combmachineItemStacks[2] == null)
        {
        	ItemStack itemstack = CombMachineRecipes.smelting().getCombiningResultOneSlot(this.combmachineItemStacks[0]);
        	this.NumberOfSlots = 1;
            if (itemstack == null) return false;
            if (this.combmachineItemStacks[3] == null) return true;
            if (!this.combmachineItemStacks[3].isItemEqual(itemstack)) return false;
            int result = combmachineItemStacks[3].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
        else
        {
            ItemStack itemstack = CombMachineRecipes.smelting().getCombiningResultTwoSlots(this.combmachineItemStacks[0], this.combmachineItemStacks[2]);
            this.NumberOfSlots = 2;
            if (itemstack == null) return false;
            if (this.combmachineItemStacks[3] == null) return true;
            if (!this.combmachineItemStacks[3].isItemEqual(itemstack)) return false;
            int result = combmachineItemStacks[3].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }

    /** Hier gebruikt hij de recipe en maakt er een output van. */
    public void smeltItem()
    {
    	if ((this.canCombine()) && (this.NumberOfSlots == 1))
        {
            ItemStack itemstack = CombMachineRecipes.smelting().getCombiningResultOneSlot(this.combmachineItemStacks[0]);
            
            if (this.combmachineItemStacks[3] == null)
            {
                this.combmachineItemStacks[3] = itemstack.copy();
            }
            else if (this.combmachineItemStacks[3].isItemEqual(itemstack))
            {
           	 combmachineItemStacks[3].stackSize += itemstack.stackSize;
            }
            
            --this.combmachineItemStacks[0].stackSize;

            if (this.combmachineItemStacks[0].stackSize <= 0)
            {
           	 	this.combmachineItemStacks[0] = null;
            }
            
        }
    	else if ((this.canCombine()) && (this.NumberOfSlots == 2))
        {
            ItemStack itemstack = CombMachineRecipes.smelting().getCombiningResultTwoSlots(this.combmachineItemStacks[0], this.combmachineItemStacks[2]);
             
            if (this.combmachineItemStacks[3] == null)
            {
                this.combmachineItemStacks[3] = itemstack.copy();
            }
            else if (this.combmachineItemStacks[3].isItemEqual(itemstack))
            {
           	 combmachineItemStacks[3].stackSize += itemstack.stackSize;
            }
            
            --this.combmachineItemStacks[0].stackSize;
            --this.combmachineItemStacks[2].stackSize; 

            if (this.combmachineItemStacks[0].stackSize <= 0)
            {
           	 	this.combmachineItemStacks[0] = null;
            }
            if (this.combmachineItemStacks[2].stackSize <= 0)
            {
            	this.combmachineItemStacks[2] = null;
            }
             
         }
    }

    public static int getItemCombTime(ItemStack par0ItemStack)
    {
        if (par0ItemStack == null)
        {
            return 0;
        }
        else
        {
            int i = par0ItemStack.getItem().itemID;
            Item item = par0ItemStack.getItem();

            if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[i] != null)
            {
                Block block = Block.blocksList[i];

                if (block == Block.woodSingleSlab)
                {
                    return 150;
                }

                if (block.blockMaterial == Material.wood)
                {
                    return 300;
                }

                if (block == Block.field_111034_cE)
                {
                    return 16000;
                }
            }

            if (i == InitItemTools.WoodScrap.itemID) return 800;
            if (i == InitItemTools.CompWoodScrap.itemID) return 1600;
            if (i == InitItemTools.CompCoalScrap.itemID) return 2400;
           // if (i == Item.coal.itemID) return 1600;
            return GameRegistry.getFuelValue(par0ItemStack);
        }
    }
    
    public static int getItemReduceTime(ItemStack reducestack)
    {
    	if (reducestack == null)
        {
            return 0;
        }
    	else
    	{
    		int i = reducestack.getItem().itemID;
    		if (i == InitItemTools.CombinerIron.itemID) return 50;
    		if (i == InitItemTools.CombinerGold.itemID) return 100;
    		if (i == InitItemTools.CombinerEmerald.itemID) return 150;
    		
    		
    		return GameRegistry.getFuelValue(reducestack);
    	}
    }

    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getItemCombTime(par0ItemStack) > 0;
    }
    
    public static boolean isItemReducer(ItemStack reducerstack)
    {
    	return getItemReduceTime(reducerstack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return par1 == 2 ? false : (par1 == 1 ? isItemFuel(par2ItemStack) : true);
    }
    
    public boolean isItemValidForReducer(int par1, ItemStack par2ItemStack)
    {
        return par1 == 2 ? false : (par1 == 1 ? isItemReducer(par2ItemStack) : true);
    }

    public int[] getAccessibleSlotsFromSide(int par1)
    {
        return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
    }

    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }

    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return par3 != 0 || par1 != 1 || par2ItemStack.itemID == Item.bucketEmpty.itemID;
    }
}

