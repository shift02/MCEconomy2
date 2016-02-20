package shift.mceconomy2.api.shop;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

/**
 * Shopを管理するClass
 * @author Shift02
 */
public interface IShopManager {

    public int registerProductList(IProductList list);

    public IProductList getProductList(int id);

    public ArrayList<IProductList> getProductLists();

    public int registerShop(IShop list);

    public IShop getShop(int id);

    public ArrayList<IShop> getShops();

    public void openShopGui(int id, EntityPlayer player, World world, int x, int y, int z);

    public void addPurchaseItem(ItemStack par1ItemStack, Integer par2Integer);

    public int getPurchase(ItemStack item);

    public boolean hasPurchase(ItemStack item);

    public void addPurchaseFluid(Fluid fluid, double mp);

    public double getFluidPurchase(Fluid fluid);

    public boolean hasFluidPurchase(Fluid fluid);

    public void addPurchaseEntity(Class<? extends Entity> class1, int mp);

    public int getEntityPurchase(Entity entity);

    public boolean hasEntityPurchase(Entity entity);

}
