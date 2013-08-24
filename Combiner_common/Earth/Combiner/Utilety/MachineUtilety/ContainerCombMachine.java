package Earth.Combiner.Utilety.MachineUtilety;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerCombMachine extends Container
{
    private TileEntityCombMachine combmachine;
    private int lastCombDurTime;
    private int lastCombTime;
    private int lastItemCombTime;

    public ContainerCombMachine(InventoryPlayer par1InventoryPlayer, TileEntityCombMachine par2TileEntityCombMachine)
    {
        this.combmachine = par2TileEntityCombMachine;
        this.addSlotToContainer(new Slot(par2TileEntityCombMachine, 0, 17, 17));
        this.addSlotToContainer(new Slot(par2TileEntityCombMachine, 2, 39, 17));
        this.addSlotToContainer(new Slot(par2TileEntityCombMachine, 1, 28, 53));
        this.addSlotToContainer(new SlotCombMachine(par1InventoryPlayer.player, par2TileEntityCombMachine, 3, 116, 35));
        this.addSlotToContainer(new Slot(par2TileEntityCombMachine, 4, 154, 6));
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.combmachine.combmachineCombDCurTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.combmachine.combmachineCombTime);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.combmachine.currentItemCombTime);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCombDurTime != this.combmachine.combmachineCombDCurTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.combmachine.combmachineCombDCurTime);
            }

            if (this.lastCombTime != this.combmachine.combmachineCombTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.combmachine.combmachineCombTime);
            }

            if (this.lastItemCombTime != this.combmachine.currentItemCombTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.combmachine.currentItemCombTime);
            }
        }

        this.lastCombDurTime = this.combmachine.combmachineCombDCurTime;
        this.lastCombTime = this.combmachine.combmachineCombTime;
        this.lastItemCombTime = this.combmachine.currentItemCombTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.combmachine.combmachineCombDCurTime = par2;
        }

        if (par1 == 1)
        {
            this.combmachine.combmachineCombTime = par2;
        }

        if (par1 == 2)
        {
            this.combmachine.currentItemCombTime = par2;
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.combmachine.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 != 1 && par2 != 0)
            {
            	if (CombMachineRecipes.smelting().getCombiningResultOneSlot(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                if (CombMachineRecipes.smelting().getCombiningResultTwoSlots(itemstack1, itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityCombMachine.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
}

