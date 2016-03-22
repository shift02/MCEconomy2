package shift.mceconomy2.gui;

import java.util.List;

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
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProduct;
import shift.mceconomy2.api.shop.IShop;
import shift.mceconomy2.packet.PacketHandler;
import shift.mceconomy2.packet.PacketShopButton;

public class GuiNewShop extends GuiContainer {

    private static final ResourceLocation merchantGuiTextures = new ResourceLocation("mceconomy2:textures/guis/villager.png");

    private final IShop shop;

    private GuiButton nextButton;
    private GuiButton prevButton;

    private int currentIndex;

    public GuiNewShop(EntityPlayer player, IShop shop, int id, World world) {
        super(new ContainerShop(player, shop, world));
        this.shop = shop;
    }

    @Override
    public void initGui() {
        super.initGui();

        allowUserInput = true;

        if (nextButton == null) {

            nextButton = new GuiButtonMerchant(1, 0, 0, true);
            nextButton.enabled = false;
        }

        nextButton.xPosition = guiLeft + 154;
        nextButton.yPosition = guiTop + 3;

        if (prevButton == null) {

            prevButton = new GuiButtonMerchant(2, 0, 0, false);
            prevButton.enabled = false;
        }

        prevButton.xPosition = guiLeft + 9;
        prevButton.yPosition = nextButton.yPosition;

        buttonList.clear();
        buttonList.add(nextButton);
        buttonList.add(prevButton);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {

        fontRendererObj.drawString(I18n.format(shop.getShopName(mc.theWorld, mc.thePlayer)), 28, ySize - 159 + 2, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, 4210752);

        List<IProduct> products = shop.getProductList(mc.theWorld, mc.thePlayer);

        if (products != null && !products.isEmpty()) {

            IProduct product = products.get(currentIndex);

            if (product != null) {

                fontRendererObj.drawString("Cost", 46, 25, 4210752);
                fontRendererObj.drawString(product.getCost(shop, mc.theWorld, mc.thePlayer) + " MP", 46, 34, 4210752);
            }
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();

        List<IProduct> products = shop.getProductList(mc.theWorld, mc.thePlayer);

        if (products != null && !products.isEmpty()) {

            nextButton.enabled = currentIndex < products.size() - 1;
            prevButton.enabled = currentIndex > 0;
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {

        if (!button.enabled) {

            return;
        }

        boolean flag = false;

        if (button.id == nextButton.id) {

            ++currentIndex;

            flag = true;
        }
        else if (button.id == prevButton.id) {

            --currentIndex;

            flag = true;
        }

        if (flag) {

            ((ContainerShop)inventorySlots).setCurrentRecipeIndex(currentIndex);

            PacketHandler.INSTANCE.sendToServer(new PacketShopButton(currentIndex));
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(merchantGuiTextures);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        List<IProduct> products = shop.getProductList(mc.theWorld, mc.thePlayer);

        if (products != null && !products.isEmpty()) {

            IProduct product = products.get(currentIndex);

            if (product != null) {

                int money = MCEconomyAPI.getPlayerMP(mc.thePlayer);

                if (money < product.getCost(shop, mc.theWorld, mc.thePlayer) || !product.canBuy(shop, mc.theWorld, mc.thePlayer))
                {
                    mc.getTextureManager().bindTexture(merchantGuiTextures);
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    GL11.glDisable(GL11.GL_LIGHTING);
                    drawTexturedModalRect(guiLeft + 101, guiTop + 29, 212, 0, 28, 21);
                    drawTexturedModalRect(guiLeft + 134, guiTop + 27, 176, 38, 30, 30);
                }
            }
        }

        mc.getTextureManager().bindTexture(HUDMP.icons);

        int i = 93 - 86;

        drawTexturedModalRect(guiLeft + 83 - i, guiTop + 64, 0, 27, 86 + i, 15);
        drawTexturedModalRect(guiLeft + 86 - i, guiTop + 67, 9, 0, 9, 9);

        int j = -3;

        drawTexturedModalRect(guiLeft + 86 + 65 + j, guiTop + 67, 0, 18, 9, 9);
        drawTexturedModalRect(guiLeft + 86 + 74 + j, guiTop + 67, 9, 18, 9, 9);

        int money = MCEconomyAPI.getPlayerMP(mc.thePlayer);
        int k = guiLeft;

        k += 86 + 56 + j;

        for (int l = 1; l <= String.valueOf(money).length(); l += 1) {

            String s = String.valueOf(money).substring(String.valueOf(money).length() - l, String.valueOf(money).length() - l + 1);
            drawTexturedModalRect(k, guiTop + 67, 9 * Integer.parseInt(s), 9, 9, 9);

            k -= 8;
        }
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        super.drawScreen(par1, par2, par3);

        List<IProduct> products = shop.getProductList(mc.theWorld, mc.thePlayer);

        if (products != null && !products.isEmpty()) {

            IProduct product = products.get(currentIndex);

            if (product != null) {

                ItemStack itemstack = product.getItem(shop, mc.theWorld, mc.thePlayer);

                if (itemstack != null) {

                    GL11.glPushMatrix();
                    RenderHelper.enableGUIStandardItemLighting();
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL12.GL_RESCALE_NORMAL);
                    GL11.glEnable(GL11.GL_COLOR_MATERIAL);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    itemRender.zLevel = 100.0F;
                    itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, guiLeft + 17, guiTop + 32);
                    itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, guiLeft + 17, guiTop + 32);
                    itemRender.zLevel = 0.0F;
                    GL11.glDisable(GL11.GL_LIGHTING);

                    if (func_146978_c(8, 23, 34, 34, par1, par2)) {

                        renderToolTip(itemstack, par1, par2);
                    }

                    GL11.glPopMatrix();
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_DEPTH_TEST);
                    RenderHelper.enableStandardItemLighting();
                }
            }
        }
    }
}