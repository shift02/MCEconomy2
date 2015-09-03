package shift.mceconomy2.item;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import shift.mceconomy2.api.IMPWallet;
import shift.mceconomy2.api.IMPWalletManager;

public class MPWalletManager implements IMPWalletManager{


	private final ArrayList<Item> r = new ArrayList<Item>();

	@Override
	public int storeMP(ItemStack itemStack, int mp, boolean simulate) {

		if(!this.isMPWalletItem(itemStack))return 0;

		if(this.getMP(itemStack)<=0){
			return 0;
		}

		int mpReceived = 0;
		int mpReceived2 = 0;

		if(this.getMP(itemStack)>=mp){
			mpReceived = this.getMP(itemStack) - mp;
			mpReceived2 = mp;
		}else{
			mpReceived = 0;
			mpReceived2 = this.getMP(itemStack);
		}

        if (!simulate)
        {
                this.setEnergy(itemStack, mpReceived);
        }

        return mpReceived2;

	}

	@Override
	public int reduceMP(ItemStack itemStack, int mp, boolean simulate) {

		if(!this.isMPWalletItem(itemStack))return 0;

		IMPWallet item = (IMPWallet)itemStack.getItem();


		int mpReceived = 0;
		int mpReceived2 = 0;

		if(item.getMaxMPStored(itemStack)>this.getMP(itemStack)+mp){
			mpReceived = this.getMP(itemStack)+mp;
			mpReceived2 = mp;
		}else{
			mpReceived = item.getMaxMPStored(itemStack);
			mpReceived2 = item.getMaxMPStored(itemStack)-this.getMP(itemStack);
		}

        if (!simulate)
        {
                this.setEnergy(itemStack, mpReceived);
        }

        return mpReceived2;
	}

	@Override
	public int getMP(ItemStack itemStack) {

		if (itemStack.getTagCompound() == null)
        {
			itemStack.setTagCompound(new NBTTagCompound());
			return 0;
        }

		return itemStack.getTagCompound().getInteger("mp");

	}

	@Override
	public boolean setEnergy(ItemStack itemStack, int mp) {

		if(!(itemStack.getItem() instanceof IMPWallet)){
			return false ;
		}

		IMPWallet item = (IMPWallet)itemStack.getItem();

		if (itemStack.getTagCompound() == null)
        {
                itemStack.setTagCompound(new NBTTagCompound());
        }

		int d = 100;

        long mpStored = Math.max(Math.min(mp, item.getMaxMPStored(itemStack)), 0);
        itemStack.getTagCompound().setLong("mp", mpStored);


        if(!r.contains(itemStack.getItem())){
        	//SextiarySector.proxy.registerItemRenderer(itemStack.itemID);
        	r.add(itemStack.getItem());
        }

        //itemStack.setItemDamage(damage);


		return true;
	}

	@Override
	public boolean isMPWalletItem(ItemStack itemStack) {
		return itemStack.getItem() instanceof IMPWallet;
	}



}
