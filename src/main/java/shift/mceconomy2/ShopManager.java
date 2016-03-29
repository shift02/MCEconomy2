package shift.mceconomy2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import shift.mceconomy2.api.purchase.IPurchaseItem;
import shift.mceconomy2.api.purchase.PurchaseItemStack;
import shift.mceconomy2.api.purchase.PurchaseOreDictionary;
import shift.mceconomy2.api.shop.IProductList;
import shift.mceconomy2.api.shop.IShopManager;
import shift.mceconomy2.api.shop.PriceEvent;
import shift.mceconomy2.packet.PacketGuiId;
import shift.mceconomy2.packet.PacketHandler;

public class ShopManager implements IShopManager {

	private final ArrayList<IProductList> ProductList = new ArrayList<IProductList>();

    /**
     * アイテムの価格
     */
    private static final ArrayList<IPurchaseItem> purchaseItems=new ArrayList<IPurchaseItem>();
    private static IPurchaseItem cachedItem;

	private static final HashMap<Integer, Double> purchaseFluidList = new HashMap<Integer, Double>();

	private static final HashMap<Class<? extends Entity>, Integer> purchaseEntityList = new HashMap<Class<? extends Entity>, Integer>();

	//shop関係
	@Override
	public int registerProductList(IProductList list) {

		ProductList.add(list);

		return ProductList.indexOf(list);

	}

	@Override
	public IProductList getProductList(int id) {

		if (ProductList.size() < id) {
			return null;
		}
		return ProductList.get(id);

	}

	@Override
	public ArrayList<IProductList> getProductLists() {

		return ProductList;

	}

	@Override
	public void openShopGui(int id, EntityPlayer player, World world, int x, int y, int z) {

		/*OpenShopGuiEvent event = new OpenShopGuiEvent(player, id, world, x, y, z);

		if (MinecraftForge.EVENT_BUS.post(event))
		{
		    return;
		}

		if (event.getResult() == Result.ALLOW)
		{
		    return ;
		}*/

		if (world.isRemote) {
			PacketHandler.INSTANCE.sendToServer(new PacketGuiId(id));
		} else {
			player.openGui(MCEconomy2.instance, id, world, x, y, z);
		}

	}

    @Override
    public void addPurchaseItem(IPurchaseItem purchaseItem)
    {
        purchaseItems.add(purchaseItem);
    }

    @Override
	public void addPurchaseItem(ItemStack par1ItemStack, Integer par2Integer)
	{
		//purchaseList.put(par1ItemStack, par2Integer);
        purchaseItems.add(new PurchaseItemStack(par1ItemStack, par2Integer));
	}

    @Override
    public void addPurchaseItem(String par1String, Integer par2Integer)
    {
        purchaseItems.add(new PurchaseOreDictionary(par1String, par2Integer));
    }

    @Override
	public int getPurchase(ItemStack item)
	{
		if (item == null)
		{
			return -2;
		}

        IPurchaseItem purchaseItem=null;

        //キャッシュがある場合、それをまず見る
        if(cachedItem!=null && cachedItem.isMatch(item))
        {
            purchaseItem=cachedItem;
        }
        else
        {
            for(IPurchaseItem it : purchaseItems)
            {
                if(it.isMatch(item))
                {
                    purchaseItem=cachedItem=it;
                    break;
                }
            }
        }
        if(purchaseItem==null) return -2;

        //このへんは同じ
		int old = purchaseItem.getPrice(item);

		PriceEvent event = new PriceEvent(item, old);
		event.setNewPrice(old);

		MinecraftForge.EVENT_BUS.post(event);

		if (old != event.getNewPrice()) return event.getNewPrice();

		return old;

	}

	@Override
	public boolean hasPurchase(ItemStack item) {
		return (this.getPurchase(item) != -1 && this.getPurchase(item) != -2);
	}

	@Override
	public void addPurchaseFluid(Fluid fluid, double mp) {
		if (fluid == null) return;
		purchaseFluidList.put(fluid.getID(), mp);
	}

	@Override
	public double getFluidPurchase(Fluid fluid) {

		if (fluid == null)
		{
			return -2;
		}
		if (!purchaseFluidList.containsKey(fluid.getID())) {
			return -2;
		}

		return purchaseFluidList.get(fluid.getID());
	}

	@Override
	public boolean hasFluidPurchase(Fluid fluid) {
		return (this.getFluidPurchase(fluid) != -1 && this.getFluidPurchase(fluid) != -2);
	}

	@Override
	public void addPurchaseEntity(Class<? extends Entity> class1, int mp) {

		purchaseEntityList.put(class1, mp);

	}

	@Override
	public int getEntityPurchase(Entity entity) {

		if (entity == null)
		{
			return -2;
		}
		if (!purchaseEntityList.containsKey(entity.getClass())) {
			return -2;
		}

		return purchaseEntityList.get(entity.getClass());

	}

	@Override
	public boolean hasEntityPurchase(Entity entity) {
		return (this.getEntityPurchase(entity) != -1 && this.getEntityPurchase(entity) != -2);
	}

}
