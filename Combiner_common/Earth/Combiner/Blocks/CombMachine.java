package Earth.Combiner.Blocks;

import java.util.Random;

import Earth.Combiner.CombinerCore;
import Earth.Combiner.Utilety.MachineUtilety.TileEntityCombMachine;
import Earth.Combiner.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CombMachine extends BlockContainer
{
    private final Random combmachineRand = new Random();
    private final boolean isActive;
    
    private static boolean keepCombMachineInventory;
    @SideOnly(Side.CLIENT)
    private Icon combmachnineIconTop;
    @SideOnly(Side.CLIENT)
    private Icon combmachineIconFront;
    @SideOnly(Side.CLIENT)
    private Icon combmachineIconSide;

    public CombMachine(int par1, boolean par2)
    {
        super(par1, Material.rock);
        this.isActive = par2;
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return InitBlocks.CombineHeaterOff.blockID;
    }

    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
    }

    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            int l = par1World.getBlockId(par2, par3, par4 - 1);
            int i1 = par1World.getBlockId(par2, par3, par4 + 1);
            int j1 = par1World.getBlockId(par2 - 1, par3, par4);
            int k1 = par1World.getBlockId(par2 + 1, par3, par4);
            byte b0 = 3;

            if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
            {
                b0 = 3;
            }

            if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
            {
                b0 = 2;
            }

            if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
            {
                b0 = 5;
            }

            if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
            {
                b0 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }
    }

    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        return side == 1 ? this.combmachnineIconTop : (side == 0 ? this.combmachnineIconTop : (side != meta ? this.combmachineIconSide : this.combmachineIconFront));
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(Strings.modID + ":" + (this.getUnlocalizedName().substring(5)));;
        this.combmachineIconSide = par1IconRegister.registerIcon("combinercore:combiner_side");
        this.combmachineIconFront = par1IconRegister.registerIcon(this.isActive ? "combinercore:combiner_front_lit" : "combinercore:combiner_front");
        this.combmachnineIconTop = par1IconRegister.registerIcon("combinercore:combiner_top");
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		if (tile_entity == null || player.isSneaking()) {

			return false;
		}

		player.openGui(CombinerCore.instance, 0, world, x, y, z);

		return true;
	}

    public static void updateCombMachineBlockState(boolean par0, World par1World, int par2, int par3, int par4)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
        keepCombMachineInventory = true;

        if (par0)
        {
            par1World.setBlock(par2, par3, par4, InitBlocks.CombineHeaterOn.blockID);
        }
        else
        {
            par1World.setBlock(par2, par3, par4, InitBlocks.CombineHeaterOff.blockID);
        }

        keepCombMachineInventory = false;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            par1World.setBlockTileEntity(par2, par3, par4, tileentity);
        }
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (this.isActive)
        {
            int l = par1World.getBlockMetadata(par2, par3, par4);
            float f = (float)par2 + 0.5F;
            float f1 = (float)par3 + 0.3F + par5Random.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)par4 + 0.5F;
            float f3 = 0.52F;
            float f4 = par5Random.nextFloat() * 0.3F - 0.15F;

            if (l == 4)
            {
                par1World.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), -0.02D, 0.005D, 0.0D);
                par1World.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), -0.02D, 0.005D, 0.0D);
            }
            else if (l == 5)
            {
                par1World.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.02D, 0.005D, 0.0D);
                par1World.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.02D, 0.01D, 0.0D);
            }
            else if (l == 2)
            {
                par1World.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.005D, -0.02D);
                par1World.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.005D, -0.02D);
            }
            else if (l == 3)
            {
                par1World.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.005D, 0.02D);
                par1World.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.005D, 0.02D);
            }
        }
    }

    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntityCombMachine();
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
        }

        if (par6ItemStack.hasDisplayName())
        {
            ((TileEntityCombMachine)par1World.getBlockTileEntity(par2, par3, par4)).setGuiDisplayName(par6ItemStack.getDisplayName());
        }
    }

    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        if (!keepCombMachineInventory)
        {
        	TileEntityCombMachine tileentitycombmachine = (TileEntityCombMachine)par1World.getBlockTileEntity(par2, par3, par4);

            if (tileentitycombmachine != null)
            {
                for (int j1 = 0; j1 < tileentitycombmachine.getSizeInventory(); ++j1)
                {
                    ItemStack itemstack = tileentitycombmachine.getStackInSlot(j1);

                    if (itemstack != null)
                    {
                        float f = this.combmachineRand.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.combmachineRand.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.combmachineRand.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0)
                        {
                            int k1 = this.combmachineRand.nextInt(21) + 10;

                            if (k1 > itemstack.stackSize)
                            {
                                k1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= k1;
                            EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound())
                            {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (double)((float)this.combmachineRand.nextGaussian() * f3);
                            entityitem.motionY = (double)((float)this.combmachineRand.nextGaussian() * f3 + 0.2F);
                            entityitem.motionZ = (double)((float)this.combmachineRand.nextGaussian() * f3);
                            par1World.spawnEntityInWorld(entityitem);
                        }
                    }
                }

                par1World.func_96440_m(par2, par3, par4, par5);
            }
        }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
    {
        return Container.calcRedstoneFromInventory((IInventory)par1World.getBlockTileEntity(par2, par3, par4));
    }

    @SideOnly(Side.CLIENT)
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return InitBlocks.CombineHeaterOff.blockID;
    }
}


