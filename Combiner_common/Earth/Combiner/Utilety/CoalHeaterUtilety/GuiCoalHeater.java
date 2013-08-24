package Earth.Combiner.Utilety.CoalHeaterUtilety;

import Earth.Combiner.CombinerCore;
import Earth.Combiner.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;// Cant import somhow ...

@SideOnly(Side.CLIENT)
public class GuiCoalHeater extends GuiContainer
{
    //private static final ResourceLocation field_110410_t = new ResourceLocation("combinercore", "/textures/gui/combheater.png");
    
    private TileEntityCoalHeater coalheaterInventory;

    public GuiCoalHeater(InventoryPlayer par1InventoryPlayer, TileEntityCoalHeater par2TileEntityCoalHeater)
    {
        super(new ContainerCoalHeater(par1InventoryPlayer, par2TileEntityCoalHeater));
        this.coalheaterInventory = par2TileEntityCoalHeater;
    }
    

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = this.coalheaterInventory.isInvNameLocalized() ? this.coalheaterInventory.getInvName() : I18n.func_135053_a(this.coalheaterInventory.getInvName());
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(I18n.func_135053_a("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }
    

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //this.mc.func_110434_K().func_110577_a(field_110410_t);
        mc.renderEngine.func_110577_a(Strings.GUI_COALHEATER);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;
        int b1;
        

        if (this.coalheaterInventory.isHeating())
        {
        	this.drawTexturedModalRect(k+7, l+38, 176, 15, 12, 11);
            i1 = this.coalheaterInventory.getHeatTimeRemainingScaled(12);
            this.drawTexturedModalRect(k + 78, l + 54 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
            //b1 = this.coalheaterInventory.getWaterLVLScaled(49);
            //this.drawTexturedModalRect(k + 156, l + 36 + 27 - b1, 176, 76 - b1, 76, b1 + 2);
        }
        
        b1 = this.coalheaterInventory.getWaterLVLScaled(49);
        this.drawTexturedModalRect(k + 156, l + 63 - b1, 176, 76 - b1, 76, b1 + 2);
        
        /*
        if (this.coalheaterInventory.hasWater())
        {
        	b1 = this.coalheaterInventory.getWaterLVLScaled(49);
            this.drawTexturedModalRect(k + 156, l + 36 + 27 - b1, 176, 76 - b1, 76, b1 + 2);
        }
        */
        buttonList.add(new GuiButton(0, k + 176, l + 5, 20, 20, "..."));
        
        //i1 = this.coalheaterInventory.getCombDurProgressScaled(24);
        //this.drawTexturedModalRect(k + 70, l + 34, 176, 14, i1 + 1, 16);
        /*
        this.mc.renderEngine.bindTexture(LiquidDictionary.getCanonicalLiquid("Water").getTextureSheet());
		this.drawTexturedModelRectFromIcon(xOffset + 8, yOffset + 143 - height, LiquidDictionary.getCanonicalLiquid("Water").getRenderingIcon(), 16, height);
         */
        
    }
    
    protected void actionPerformed(GuiButton guiButton)
    {
    	if(guiButton.id == 0)
    	{
    		ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Test mode Water:" + TileEntityCoalHeater.waterLvlcheck);
    		ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Test mode Steam:" + TileEntityCoalHeater.steamCheck);
    		//this.mc.displayGuiScreen(new GuiIngameMenu());
    	}
    }
}

