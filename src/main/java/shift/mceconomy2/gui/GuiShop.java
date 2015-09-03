package shift.mceconomy2.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProductItem;
import shift.mceconomy2.api.shop.IProductList;
import shift.mceconomy2.api.shop.OpenShopGuiEvent;
import shift.mceconomy2.packet.PacketHandler;
import shift.mceconomy2.packet.PacketShopButton;

public class GuiShop extends  GuiContainer{

	private static final ResourceLocation merchantGuiTextures = new ResourceLocation("mceconomy2:textures/guis/villager.png");

	private IProductList theIProductList;
    private GuiButtonMerchant nextRecipeButtonIndex;
    private GuiButtonMerchant previousRecipeButtonIndex;
    private int currentRecipeIndex;

    private int shopID;

	public GuiShop(InventoryPlayer par1InventoryPlayer, IProductList par2IProductList, int id, World par3World) {
		super(new ContainerShop(par1InventoryPlayer, par2IProductList, par3World));
		theIProductList = par2IProductList;
		this.shopID = id;
	}


	public void initGui()
    {
        super.initGui();
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.allowUserInput = true;
        //this.buttonList.add(this.nextRecipeButtonIndex = new GuiButtonMerchant(1, i + 120 + 27, j + 24 - 1, true));
        //this.buttonList.add(this.previousRecipeButtonIndex = new GuiButtonMerchant(2, i + 36 - 19, j + 24 - 1, false));
        this.buttonList.add(this.nextRecipeButtonIndex = new GuiButtonMerchant(1, i + 154 , j + 3, true));
        this.buttonList.add(this.previousRecipeButtonIndex = new GuiButtonMerchant(2, i + 9, j + 3, false));
        this.nextRecipeButtonIndex.enabled = false;
        this.previousRecipeButtonIndex.enabled = false;
        this.currentRecipeIndex = 0;

        MinecraftForge.EVENT_BUS.post(new OpenShopGuiEvent(this, this.buttonList, shopID));

    }

	protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
       // this.fontRenderer.drawString(this.field_94082_v, this.xSize / 2 - this.fontRenderer.getStringWidth(this.field_94082_v) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format(theIProductList.getProductListName()), 28, this.ySize - 159 + 2, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);



        ArrayList<IProductItem> ProductItemlist = this.theIProductList.getProductList();
        if (ProductItemlist != null && !ProductItemlist.isEmpty())
        {
            int i1 = this.currentRecipeIndex;
            IProductItem ProductItem = (IProductItem)ProductItemlist.get(i1);

            ItemStack itemstack2 = ProductItem.getProductItem();

            this.fontRendererObj.drawString("Cost", 46,  25, 4210752);
            this.fontRendererObj.drawString(ProductItem.getcost()+" MP", 46,  34, 4210752);
            //this.fontRenderer.drawString(I18n.getString(itemstack2.getDisplayName()), 45,  38, 4210752);

        }




    }

	public void updateScreen()
    {
        super.updateScreen();
        ArrayList<IProductItem> ProductItemlist = this.theIProductList.getProductList();

        if (ProductItemlist != null)
        {
            this.nextRecipeButtonIndex.enabled = this.currentRecipeIndex < ProductItemlist.size() - 1;
            this.previousRecipeButtonIndex.enabled = this.currentRecipeIndex > 0;
        }
    }

	protected void actionPerformed(GuiButton par1GuiButton)
    {
        boolean flag = false;

        if (par1GuiButton == this.nextRecipeButtonIndex)
        {
            ++this.currentRecipeIndex;
            flag = true;
        }
        else if (par1GuiButton == this.previousRecipeButtonIndex)
        {
            --this.currentRecipeIndex;
            flag = true;
        }

        if (flag)
        {
            ((ContainerShop)this.inventorySlots).setCurrentRecipeIndex(this.currentRecipeIndex);
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);

            try
            {
                dataoutputstream.writeInt(this.currentRecipeIndex);
                PacketHandler.INSTANCE.sendToServer(new PacketShopButton(this.currentRecipeIndex));
                //this.mc.getNetHandler().addToSendQueue(new Packet250CustomPayload(MCEconomy.channels2, bytearrayoutputstream.toByteArray()));
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }
    }

	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(merchantGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        ArrayList<IProductItem> ProductItemlist = this.theIProductList.getProductList();

        if (ProductItemlist != null && !ProductItemlist.isEmpty())
        {
            int i1 = this.currentRecipeIndex;
            IProductItem roductItem = (IProductItem)ProductItemlist.get(i1);

            int money = MCEconomyAPI.getPlayerMP(mc.thePlayer);

            if (money<roductItem.getcost())//merchantrecipe.func_82784_g())
            {
                this.mc.getTextureManager().bindTexture(merchantGuiTextures);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDisable(GL11.GL_LIGHTING);
                this.drawTexturedModalRect(this.guiLeft + 101, this.guiTop + 29, 212, 0, 28, 21);
                this.drawTexturedModalRect(this.guiLeft + 134, this.guiTop + 27, 176, 38, 30, 30);
                //this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 51, 212, 0, 28, 21);
            }



        }

        this.mc.getTextureManager().bindTexture(HUDMP.icons);


        //109
        int m2 = 93-86;
        this.drawTexturedModalRect(k+83 - m2 , l+64, 0, 27, 86 + m2, 15);

        this.drawTexturedModalRect(k+86 - m2, l+67, 9, 0, 9, 9);

        int a = -3;


        drawTexturedModalRect(k+(86 +65)+a, l+67, 0, 18, 9, 9);
        drawTexturedModalRect(k+(86 +74)+a, l+67, 9, 18, 9, 9);

        int money = MCEconomyAPI.getPlayerMP(mc.thePlayer);

        k += (86 +56)+a;

        for (int i = 1; i<= String.valueOf(money).length(); i += 1)
        {
        	String s = String.valueOf(money).substring(String.valueOf(money).length()-i, String.valueOf(money).length()-i+1);
        	drawTexturedModalRect(k, l+67, 9*Integer.parseInt(s), 9, 9, 9);
        	k-=8;
        }

    }

	public void drawScreen(int par1, int par2, float par3)
    {
        super.drawScreen(par1, par2, par3);
        ArrayList<IProductItem> ProductItemlist = this.theIProductList.getProductList();

        if (ProductItemlist != null && !ProductItemlist.isEmpty())
        {
            int k = (this.width - this.xSize) / 2;
            int l = (this.height - this.ySize) / 2;
            int i1 = this.currentRecipeIndex;
            IProductItem ProductItem = (IProductItem)ProductItemlist.get(i1);
            GL11.glPushMatrix();
            //ItemStack itemstack = merchantrecipe.getItemToBuy();
            //ItemStack itemstack1 = merchantrecipe.getSecondItemToBuy();
            ItemStack itemstack2 = ProductItem.getProductItem();
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            GL11.glEnable(GL11.GL_LIGHTING);
            itemRender.zLevel = 100.0F;
            //itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), itemstack, k + 36, l + 24);
            //itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.getTextureManager(), itemstack, k + 36, l + 24);

            /*
            if (itemstack1 != null)
            {
                itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), itemstack1, k + 62, l + 24);
                itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.getTextureManager(), itemstack1, k + 62, l + 24);
            }
            */

            itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack2, k + 17, l + 32);
            itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack2, k + 17, l + 32);
            itemRender.zLevel = 0.0F;
            GL11.glDisable(GL11.GL_LIGHTING);

            /*
            if (this.isPointInRegion(36, 24, 16, 16, par1, par2))
            {
                this.drawItemStackTooltip(itemstack, par1, par2);
            }
            else if (itemstack1 != null && this.isPointInRegion(62, 24, 16, 16, par1, par2))
            {
                this.drawItemStackTooltip(itemstack1, par1, par2);
            }
            else*/ if (this.func_146978_c(8, 23, 34, 34, par1, par2))
            {
                //this.drawItemStackTooltip(itemstack2, par1, par2);
                this.renderToolTip(itemstack2, par1, par2);
            }

            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.enableStandardItemLighting();
        }
    }


}
