package Earth.Combiner.Utilety.CoalHeaterUtilety;

import Earth.Combiner.CombinerCore;
import Earth.Combiner.Blocks.CoalHeater;
import Earth.Combiner.ItemTools.InitItemTools;
import Earth.Combiner.core.packets.Waterpacket;
import Earth.Combiner.lib.Ints;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
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
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ForgeDummyContainer;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidTank;

public class TileEntityCoalHeater extends TileEntity implements ISidedInventory
{
	/** toegankelijk heid van de kanten (ik denk voor andere mods zoals buildcraft enzo). */
    private static final int[] slots_top = new int[] {0};
    private static final int[] slots_bottom = new int[] {2, 1};
    private static final int[] slots_sides = new int[] {1};

    /** Hoeveleheid slots. */
    private ItemStack[] coalheatereItemStacks = new ItemStack[5];

    /** mechaniek variabelen */  
    public int BurnTime;
    public static int BurnTimeB;
    public int canBurn;
    public int currentBurnTime;
    public int waterLvl;
    public static int waterLvlcheck;
    public static int steamCheck;
    
    //public int combmachineCombDCurTime;
    //public int NumberOfSlots;
    private String field_94130_e;
 
    public int getSizeInventory()
    {
        return this.coalheatereItemStacks.length;
    }

    public ItemStack getStackInSlot(int par1)
    {
        return this.coalheatereItemStacks[par1];
    }

    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.coalheatereItemStacks[par1] != null)
        {
            ItemStack itemstack;

            if (this.coalheatereItemStacks[par1].stackSize <= par2)
            {
                itemstack = this.coalheatereItemStacks[par1];
                this.coalheatereItemStacks[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.coalheatereItemStacks[par1].splitStack(par2);

                if (this.coalheatereItemStacks[par1].stackSize == 0)
                {
                    this.coalheatereItemStacks[par1] = null;
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
        if (this.coalheatereItemStacks[par1] != null)
        {
            ItemStack itemstack = this.coalheatereItemStacks[par1];
            this.coalheatereItemStacks[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.coalheatereItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    public String getInvName()
    {
        return this.isInvNameLocalized() ? this.field_94130_e : "Coal Heater";
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
        this.coalheatereItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.coalheatereItemStacks.length)
            {
                this.coalheatereItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.BurnTime = par1NBTTagCompound.getShort("BurnTime");
        this.waterLvl = par1NBTTagCompound.getShort("WaterLVL");
        this.currentBurnTime = par1NBTTagCompound.getShort("CurrBurn");
        TileSteam.SteamCount = par1NBTTagCompound.getShort("SteamAmount");
        //this.combmachineCombDCurTime = par1NBTTagCompound.getShort("CombDurTime");
        //this.currentBurnTime = getBurnHeaterTime(this.coalheatereItemStacks[Ints.BURNSLOT]);

        if (par1NBTTagCompound.hasKey("Coal Heater"))
        {
            this.field_94130_e = par1NBTTagCompound.getString("Coal Heater");
        }
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("BurnTime", (short)this.BurnTime);
        par1NBTTagCompound.setShort("WaterLVL", (short)this.waterLvl);
        par1NBTTagCompound.setShort("CurrBurn", (short)this.currentBurnTime);
        par1NBTTagCompound.setShort("SteamAmount", (short)TileSteam.SteamCount);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.coalheatereItemStacks.length; ++i)
        {
            if (this.coalheatereItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.coalheatereItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

        if (this.isInvNameLocalized())
        {
            par1NBTTagCompound.setString("Coal Heater", this.field_94130_e);
        }
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    /*
    @SideOnly(Side.CLIENT)
    public int getCombDurProgressScaled(int par1)
    {
    	return this.combmachineCombDCurTime * par1 / 200;
    }
    */

    @SideOnly(Side.CLIENT)
    public int getHeatTimeRemainingScaled(int par1)
    {
        if (this.currentBurnTime == 0)
        {
            this.currentBurnTime = 200;
        }

        return this.BurnTime * par1 / this.currentBurnTime;
    }
    
    @SideOnly(Side.CLIENT)
    public int getWaterLVLScaled(int par1)
    {
    	return this.waterLvl * par1 / 10000;
    }
    
    public boolean hasWater()
    {
    	return this.waterLvl > 0;
    }
    
    public boolean isHeating()
    {
        return this.BurnTime > 0;
    }
    

    /** Als hij "fuel" en een recipe heeft */
    public void updateEntity()
    {
    	boolean flag = this.BurnTime > 0;
        boolean flag1 = false;
        
        
        if (this.BurnTime > 0)
        {
            --this.BurnTime;
        }
        
        if (!this.hasWater())
        {
        	this.BurnTime = 0;
        }
        if (TileSteam.SteamCount == TileSteam.getMaxSteamCount())
        {
        	this.BurnTime = 0;
        }
        
        if (this.isHeating() && this.hasWater())
        {
        	if (TileSteam.SteamCount < TileSteam.getMaxSteamCount())
        	{
        		TileSteam.SteamCount = TileSteam.SteamCount + 17;
        		this.waterLvl = this.waterLvl - 1;
        		this.waterLvlcheck = this.waterLvl;
        		this.steamCheck = TileSteam.SteamCount;

        	}
            
        }
        
        

        if (!this.worldObj.isRemote)
        {
        	if (this.waterLvl <= 9000)
            {
            	this.loseWater();
            }
            if (this.BurnTime == 0)
            {
            	if(this.hasWater() && TileSteam.SteamCount < TileSteam.getMaxSteamCount())
            	{
            		this.currentBurnTime = this.BurnTime = getBurnHeaterTime(this.coalheatereItemStacks[Ints.BURNSLOT]);
                    
            		if (this.BurnTime > 0)
            		{
            			flag1 = true;

            			if (this.coalheatereItemStacks[Ints.BURNSLOT] != null)
            			{
            				--this.coalheatereItemStacks[Ints.BURNSLOT].stackSize;

            				if (this.coalheatereItemStacks[Ints.BURNSLOT].stackSize == 0)
            				{
            					this.coalheatereItemStacks[Ints.BURNSLOT] = this.coalheatereItemStacks[Ints.BURNSLOT].getItem().getContainerItemStack(coalheatereItemStacks[Ints.BURNSLOT]);
            				}
            			}
            		}              
            	}
            }
            if (flag != this.BurnTime > 0 && hasWater())
            {
                flag1 = true;                
            }
            else
            {
            	flag1 = false;
            }
            
            if(flag1)
            {
            	//this.onInventoryChanged();
            	CoalHeater.updateCombMachineBlockState(this.BurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
            else
            {
            	CoalHeater.updateCombMachineBlockState(this.BurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1)
        {
            //this.onInventoryChanged();
        }
    }
    
    private boolean canTakeWater()
    {
    	if (this.coalheatereItemStacks[Ints.WATERSLOT] == null)
        {
            return false;
        }
    	else
    	{
    		ItemStack itemstack = CoalHearterRecipes.smelting().getWaterBucket(this.coalheatereItemStacks[Ints.WATERSLOT]);
            if (itemstack == null) return false;
            int result = coalheatereItemStacks[Ints.WATERSLOT].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
    	}
    }

    /** Hier pakt hij de recipe en of hij aan mag of niet. */
    /*
    private boolean canHeat()
    {
        if (this.coalheatereItemStacks[Ints.BURNSLOT] == null)
        {
            return false;
        }
        else
        {
        	ItemStack itemstack = CoalHearterRecipes.smelting().getCombiningResultOneSlot(this.coalheatereItemStacks[Ints.BURNSLOT]);
            if (itemstack == null) return false;
            if (this.coalheatereItemStacks[Ints.OUTPUT] == null) return true;
            if (!this.coalheatereItemStacks[Ints.OUTPUT].isItemEqual(itemstack)) return false;
            int result = coalheatereItemStacks[Ints.OUTPUT].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }
    */

    /** Hier gebruikt hij de recipe en maakt er een output van. */
    public void loseWater()
    {
    	if (this.canTakeWater())
        {
            ItemStack itemstack = CoalHearterRecipes.smelting().getWaterBucket(this.coalheatereItemStacks[Ints.WATERSLOT]);  
            
            this.waterLvl = this.waterLvl + getWater(this.coalheatereItemStacks[Ints.WATERSLOT]);
            //this.waterTank = this.waterTank + getWater(this.coalheatereItemStacks[Ints.WATERSLOT]);
            
            // Output
            if (this.coalheatereItemStacks[Ints.BUCKETOUT] == null)
            {
                this.coalheatereItemStacks[Ints.BUCKETOUT] = itemstack.copy();
            }
            else if (this.coalheatereItemStacks[Ints.BUCKETOUT].isItemEqual(itemstack))
            {
            	coalheatereItemStacks[Ints.BUCKETOUT].stackSize += itemstack.stackSize;
            }
            // Input
            --this.coalheatereItemStacks[Ints.WATERSLOT].stackSize;
            
            ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Test mode:" + this.waterLvl);
            

            if (this.coalheatereItemStacks[Ints.WATERSLOT].stackSize <= 0)
            {
           	 	this.coalheatereItemStacks[Ints.WATERSLOT] = null;
            }
            
        }
    }

    public static int getBurnHeaterTime(ItemStack par0ItemStack)
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
    
    public static int getWater(ItemStack waterstack)
    {
    	if (waterstack == null)
        {
            return 0;
        }
    	else
    	{
    		int i = waterstack.getItem().itemID;
    		if (i == Item.bucketWater.itemID) return 1000;

    		return GameRegistry.getFuelValue(waterstack);
    	}
    }


    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}
    
    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getBurnHeaterTime(par0ItemStack) > 0;
    }
    
    public static boolean isWaterBucket(ItemStack par0ItemStack)
    {
    	return getWater(par0ItemStack) > 0;
    }
    
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return par1 == 2 ? false : (par1 == 1 ? isItemFuel(par2ItemStack) : true);
    }
    
    public boolean isItemValidForBucket(int par1, ItemStack par2ItemStack)
    {
        return par1 == 2 ? false : (par1 == 1 ? isWaterBucket(par2ItemStack) : true);
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

