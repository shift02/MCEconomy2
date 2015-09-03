package shift.mceconomy2.api;

import net.minecraft.item.ItemStack;

public interface IMPWalletManager {

	public int storeMP(ItemStack itemStack, int mp,  boolean simulate);

	public int reduceMP(ItemStack itemStack, int mp, boolean simulate);

	public int getMP(ItemStack itemStack);

	public boolean setEnergy(ItemStack itemStack, int mp);

	boolean isMPWalletItem(ItemStack item);

}
