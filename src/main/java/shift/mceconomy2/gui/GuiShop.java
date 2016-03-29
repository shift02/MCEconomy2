package shift.mceconomy2.gui;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProduct;
import shift.mceconomy2.api.shop.IShop;
import shift.mceconomy2.api.shop.OpenShopGuiEvent;
import shift.mceconomy2.packet.PacketHandler;
import shift.mceconomy2.packet.PacketShopButton;

public class GuiShop extends GuiContainer {

    private static final ResourceLocation merchantGuiTextures = new ResourceLocation("mceconomy2:textures/guis/villager.png");

    private IShop theIProductList;
    private GuiButtonMerchant nextProductButton;
    private GuiButtonMerchant previousProductButton;
    private int currentRecipeIndex;

    private int shopID;

    public GuiShop(EntityPlayer entityPlayer, IShop productList, int id, World world) {
        super(new ContainerShop(entityPlayer, productList, world));
        theIProductList = productList;
        this.shopID = id;
    }

    @Override
    public void initGui() {
        super.initGui();
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.allowUserInput = true;
        //this.buttonList.add(this.nextRecipeButtonIndex = new GuiButtonMerchant(1, i + 120 + 27, j + 24 - 1, true));
        //this.buttonList.add(this.previousRecipeButtonIndex = new GuiButtonMerchant(2, i + 36 - 19, j + 24 - 1, false));
        this.buttonList.add(this.nextProductButton = new GuiButtonMerchant(1, i + 154, j + 3, true));
        this.buttonList.add(this.previousProductButton = new GuiButtonMerchant(2, i + 9, j + 3, false));
        this.nextProductButton.enabled = false;
        this.previousProductButton.enabled = false;
        this.currentRecipeIndex = 0;

        MinecraftForge.EVENT_BUS.post(new OpenShopGuiEvent(this, this.buttonList, shopID));

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        // this.fontRenderer.drawString(this.field_94082_v, this.xSize / 2 - this.fontRenderer.getStringWidth(this.field_94082_v) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format(theIProductList.getShopName(this.mc.theWorld, this.mc.thePlayer)), 28, this.ySize - 159 + 2, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);

        ArrayList<IProduct> ProductItemlist = this.theIProductList.getProductList(this.mc.theWorld, this.mc.thePlayer);
        if (ProductItemlist != null && !ProductItemlist.isEmpty()) {
            int i1 = this.currentRecipeIndex;
            IProduct ProductItem = ProductItemlist.get(i1);

            this.fontRendererObj.drawString("Cost", 46, 25, 4210752);
            this.fontRendererObj.drawString(ProductItem.getCost(theIProductList, this.mc.theWorld, this.mc.thePlayer) + " MP", 46, 34, 4210752);
            //this.fontRenderer.drawString(I18n.getString(itemstack2.getDisplayName()), 45,  38, 4210752);

        }

    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        ArrayList<IProduct> ProductItemlist = this.theIProductList.getProductList(this.mc.theWorld, this.mc.thePlayer);

        if (ProductItemlist != null) {
            this.nextProductButton.enabled = this.currentRecipeIndex < ProductItemlist.size() - 1;
            this.previousProductButton.enabled = this.currentRecipeIndex > 0;
        }
    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        boolean flag = false;

        if (par1GuiButton == this.nextProductButton) {
            ++this.currentRecipeIndex;
            flag = true;
        } else if (par1GuiButton == this.previousProductButton) {
            --this.currentRecipeIndex;
            flag = true;
        }

        if (flag) {
            ((ContainerShop) this.inventorySlots).setCurrentRecipeIndex(this.currentRecipeIndex);

            PacketHandler.INSTANCE.sendToServer(new PacketShopButton(this.currentRecipeIndex));
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(merchantGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        ArrayList<IProduct> ProductItemlist = this.theIProductList.getProductList(this.mc.theWorld, this.mc.thePlayer);

        if (ProductItemlist != null && !ProductItemlist.isEmpty()) {
            int i1 = this.currentRecipeIndex;
            IProduct roductItem = ProductItemlist.get(i1);

            int money = MCEconomyAPI.getPlayerMP(mc.thePlayer);

            if (money < roductItem.getCost(theIProductList, this.mc.theWorld, this.mc.thePlayer) || !roductItem.canBuy(theIProductList, this.mc.theWorld, this.mc.thePlayer)) //merchantrecipe.func_82784_g())
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
        int m2 = 93 - 86;
        this.drawTexturedModalRect(k + 83 - m2, l + 64, 0, 27, 86 + m2, 15);

        this.drawTexturedModalRect(k + 86 - m2, l + 67, 9, 0, 9, 9);

        int a = -3;

        drawTexturedModalRect(k + 86 + 65 + a, l + 67, 0, 18, 9, 9);
        drawTexturedModalRect(k + 86 + 74 + a, l + 67, 9, 18, 9, 9);

        int money = MCEconomyAPI.getPlayerMP(mc.thePlayer);

        k += 86 + 56 + a;

        for (int i = 1; i <= String.valueOf(money).length(); i += 1) {
            String s = String.valueOf(money).substring(String.valueOf(money).length() - i, String.valueOf(money).length() - i + 1);
            drawTexturedModalRect(k, l + 67, 9 * Integer.parseInt(s), 9, 9, 9);
            k -= 8;
        }

    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        super.drawScreen(par1, par2, par3);
        ArrayList<IProduct> ProductItemlist = this.theIProductList.getProductList(this.mc.theWorld, this.mc.thePlayer);

        if (ProductItemlist != null && !ProductItemlist.isEmpty()) {
            int k = (this.width - this.xSize) / 2;
            int l = (this.height - this.ySize) / 2;
            int i1 = this.currentRecipeIndex;
            IProduct ProductItem = ProductItemlist.get(i1);
            GL11.glPushMatrix();
            //ItemStack itemstack = merchantrecipe.getItemToBuy();
            //ItemStack itemstack1 = merchantrecipe.getSecondItemToBuy();
            ItemStack itemstack2 = ProductItem.getItem(theIProductList, this.mc.theWorld, this.mc.thePlayer);
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
            else*/ if (this.func_146978_c(8, 23, 34, 34, par1, par2)) {
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
