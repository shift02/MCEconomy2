package shift.mceconomy2.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProductItem;
import shift.mceconomy2.api.shop.IProductList;

public class InventoryShop implements IInventory{

	private final IProductList theMerchant;
    private final ItemStack[] theInventory = new ItemStack[1];
    private final EntityPlayer thePlayer;
    private IProductItem currentRecipe;
    int currentRecipeIndex;

	public InventoryShop(EntityPlayer par1EntityPlayer, IProductList par2IMerchant)
    {
        this.thePlayer = par1EntityPlayer;
        this.theMerchant = par2IMerchant;
    }

	@Override
	public int getSizeInventory()
    {
        return this.theInventory.length;
    }

	@Override
	public ItemStack getStackInSlot(int par1)
    {
        return this.theInventory[par1];
    }

	@Override
	public ItemStack decrStackSize(int par1, int par2)
    {
		if (this.theInventory[par1] != null)
        {
            ItemStack itemstack;

            if (this.theInventory[par1].stackSize <= par2)
            {
                itemstack = this.theInventory[par1];
                this.theInventory[par1] = null;
                this.markDirty();
                return itemstack;
            }
            else
            {
                itemstack = this.theInventory[par1].splitStack(par2);

                if (this.theInventory[par1].stackSize == 0)
                {
                    this.theInventory[par1] = null;
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.theInventory[par1] != null)
        {
            ItemStack itemstack = this.theInventory[par1];
            this.theInventory[par1] = null;
            this.resetSlots();
            return itemstack;

        }
        else
        {
        	this.resetSlots();
            return null;

        }

    }

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.theInventory[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }

        /*
        if (this.inventoryResetNeededOnSlotChange(par1))
        {
            this.resetRecipeAndSlots();
        }*/
    }

	@Override
	public String getInventoryName()
    {
        return theMerchant.getProductListName();
    }

	@Override
	public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
	public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return true;//this.theMerchant.getCustomer() == par1EntityPlayer;
    }

    @Override
	public void openInventory() {}

    @Override
	public void closeInventory() {}

    @Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return true;
    }

    @Override
	public void markDirty()
    {
        this.resetSlots();
    }

    //public EntityClientPlayerMP thePlayer2 = FMLClientHandler.instance().getClient().thePlayer;

    public void resetSlots()
    {

    	IProductItem item = theMerchant.getProductList().get(currentRecipeIndex);

    	int money = MCEconomyAPI.getPlayerMP(thePlayer);

        /*if(thePlayer!=null){
        	NBTTagCompound nbt = thePlayer.getEntityData();
        	money = nbt.getInteger("money");
        	//System.out.println("resetSlots0"+money);
        }/*else if(thePlayer2!=null){
        	NBTTagCompound nbt = thePlayer2.getEntityData();
        	money = nbt.getInteger("money");
        	System.out.println("resetSlots0"+money);
        }*/

        if(money>=item.getcost()){
        	this.setInventorySlotContents(0, item.getProductItem().copy());
        	//System.out.println("resetSlots");
        }else{
        	this.setInventorySlotContents(0, (ItemStack)null);

        	//System.out.println("resetSlots2");
        }
        //System.out.println("resetSlots");

    }

    public void setCurrentRecipeIndex(int par1)
    {
        this.currentRecipeIndex = par1;
        this.resetSlots();
    }






}
