package shift.mceconomy2.gui;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProductList;
import shift.mceconomy2.api.shop.ShopBuyEvent;

public class SlotShopResult  extends Slot{

	private final EntityPlayer thePlayer;
	private final InventoryShop theShopInventory;
	private final IProductList theMerchant;
	protected Random rand = new Random();

	public SlotShopResult(EntityPlayer par1EntityPlayer,IProductList par2IProductList,InventoryShop par1iInventory, int par2, int par3,int par4) {
		super(par1iInventory, par2, par3, par4);
		theShopInventory = par1iInventory;
		thePlayer = par1EntityPlayer;
		theMerchant = par2IProductList;
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
    {
        return false;
    }

	@Override
	protected void onCrafting(ItemStack par1ItemStack)
    {
        par1ItemStack.onCrafting(this.thePlayer.worldObj, this.thePlayer, 0);
        //this.field_75231_g = 0;
    }

	@Override
	public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
		this.onCrafting(par2ItemStack);
		//System.out.println("onPickupFromSlot");
		int cost = theMerchant.getProductList().get(theShopInventory.currentRecipeIndex).getcost();

		MCEconomyAPI.reducePlayerMP(par1EntityPlayer, cost, false);

		MinecraftForge.EVENT_BUS.post(new ShopBuyEvent(par1EntityPlayer, par2ItemStack));
		//par1EntityPlayer.playSound(ISoundManager.COIN_SOUND, 0.1F, 0.5F * ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.8F));

		theShopInventory.resetSlots();

		this.onSlotChanged();

    }

}
