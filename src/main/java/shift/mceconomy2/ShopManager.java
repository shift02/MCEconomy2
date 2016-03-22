package shift.mceconomy2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.common.collect.Lists;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import shift.mceconomy2.api.event.PriceEvent;
import shift.mceconomy2.api.shop.IProductList;
import shift.mceconomy2.api.shop.IShop;
import shift.mceconomy2.api.shop.IShopManager;
import shift.mceconomy2.api.shop.ShopAPI;
import shift.mceconomy2.api.shop.ShopAdapter;

public class ShopManager implements IShopManager {

    //private final ArrayList<IProductList> ProductList = new ArrayList<IProductList>();

    private static final HashMap<ItemStack, Integer> purchaseList = new HashMap<ItemStack, Integer>();

    private static final HashMap<Integer, Double> purchaseFluidList = new HashMap<Integer, Double>();

    private static final HashMap<Class<? extends Entity>, Integer> purchaseEntityList = new HashMap<Class<? extends Entity>, Integer>();

    //ショップ関係

    @Override
    public int registerProductList(IProductList list) {

        IShop shop = new ShopAdapter(list);

        return this.registerShop(shop);

    }

    @Override
    public IProductList getProductList(int id) {

        return null;

        //        if (ProductList.size() < id) {
        //            return null;
        //        }
        //        return ProductList.get(id);

    }

    @Override
    public ArrayList<IProductList> getProductLists() {

        return null;// ProductList;

    }

    @Override
    public int registerShop(IShop shop) {

        return ShopAPI.registerShop("deprecated", shop);
    }

    @Override
    public IShop getShop(int id) {

        return ShopAPI.getShop("deprecated", id);
    }

    @Override
    public ArrayList<IShop> getShops() {

        return Lists.newArrayList(ShopAPI.getShops("deprecated"));
    }

    @Override
    public void openShopGui(int id, EntityPlayer player, World world, int x, int y, int z) {

        ShopAPI.openShop(player, "deprecated", id);
    }

    @Override
    public void addPurchaseItem(ItemStack par1ItemStack, Integer par2Integer) {
        purchaseList.put(par1ItemStack, par2Integer);
    }

    @Override
    public int getPurchase(ItemStack item) {
        if (item == null) {
            return -2;
        }

        Iterator iterator = this.purchaseList.entrySet().iterator();
        Entry entry;

        do {
            if (!iterator.hasNext()) {
                return -2;
            }

            entry = (Entry) iterator.next();
        } while (!this.func_151397_a(item, (ItemStack) entry.getKey()));

        int old = (Integer) entry.getValue();

        PriceEvent event = new PriceEvent(item, old);
        event.setNewPrice(old);

        MinecraftForge.EVENT_BUS.post(event);

        if (old != event.getNewPrice()) return event.getNewPrice();

        return (Integer) entry.getValue();

    }

    private boolean func_151397_a(ItemStack p_151397_1_, ItemStack p_151397_2_) {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

    @Override
    public boolean hasPurchase(ItemStack item) {
        return this.getPurchase(item) != -1 && this.getPurchase(item) != -2;
    }

    @Override
    public void addPurchaseFluid(Fluid fluid, double mp) {
        if (fluid == null) return;
        purchaseFluidList.put(fluid.getID(), mp);
    }

    @Override
    public double getFluidPurchase(Fluid fluid) {

        if (fluid == null) {
            return -2;
        }
        if (!purchaseFluidList.containsKey(fluid.getID())) {
            return -2;
        }

        return purchaseFluidList.get(fluid.getID());
    }

    @Override
    public boolean hasFluidPurchase(Fluid fluid) {
        return this.getFluidPurchase(fluid) != -1 && this.getFluidPurchase(fluid) != -2;
    }

    @Override
    public void addPurchaseEntity(Class<? extends Entity> class1, int mp) {

        purchaseEntityList.put(class1, mp);

    }

    @Override
    public int getEntityPurchase(Entity entity) {

        if (entity == null) {
            return -2;
        }
        if (!purchaseEntityList.containsKey(entity.getClass())) {
            return -2;
        }

        return purchaseEntityList.get(entity.getClass());

    }

    @Override
    public boolean hasEntityPurchase(Entity entity) {
        return this.getEntityPurchase(entity) != -1 && this.getEntityPurchase(entity) != -2;
    }

}
