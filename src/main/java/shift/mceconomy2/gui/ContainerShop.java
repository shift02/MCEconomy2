package shift.mceconomy2.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import shift.mceconomy2.api.shop.IProductList;

public class ContainerShop extends Container {

	private final IProductList theMerchant;
	private final InventoryShop merchantInventory;
	private final World theWorld;

	public ContainerShop(InventoryPlayer par1InventoryPlayer, IProductList par2IProductList, World par3World)
	{
		this.theMerchant = par2IProductList;
		this.theWorld = par3World;
		this.merchantInventory = new InventoryShop(par1InventoryPlayer.player, par2IProductList);
		this.addSlotToContainer(new SlotShopResult(par1InventoryPlayer.player, par2IProductList, this.merchantInventory, 0, 138, 31));
		//this.addSlotToContainer(new Slot(this.merchantInventory, 1, 62, 53));
		//this.addSlotToContainer(new SlotMerchantSlotResult(par1InventoryPlayer.player, par2IProductList, this.merchantInventory, 2, 120, 53));
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

		this.setCurrentRecipeIndex(0);
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);

		try
		{
			dataoutputstream.writeInt(0);
			//FMLClientHandler.instance().getClient().getNetHandler().addToSendQueue(new Packet250CustomPayload(MCEconomy.channels2, bytearrayoutputstream.toByteArray()));
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer)
	{
		//System.out.println("slotClick");
		this.merchantInventory.resetSlots();
		return super.slotClick(par1, par2, par3, par4EntityPlayer);
	}

	@Override
	protected void retrySlotClick(int par1, int par2, boolean par3, EntityPlayer par4EntityPlayer)
	{
		//this.slotClick(par1, par2, 1, par4EntityPlayer);
	}

	@Override
	public void onCraftMatrixChanged(IInventory par1IInventory)
	{
		this.merchantInventory.resetSlots();
		super.onCraftMatrixChanged(par1IInventory);
		this.merchantInventory.resetSlots();
	}

	public void setCurrentRecipeIndex(int i) {

		this.merchantInventory.setCurrentRecipeIndex(i);

	}

}
