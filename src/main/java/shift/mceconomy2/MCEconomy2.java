package shift.mceconomy2;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.ProductItem;
import shift.mceconomy2.api.shop.ProductList;
import shift.mceconomy2.event.CommonEventManager;
import shift.mceconomy2.event.MPManager;
import shift.mceconomy2.gui.HUDMP;
import shift.mceconomy2.packet.PacketHandler;
import shift.mceconomy2.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = MCEconomy2.MODID, version = MCEconomy2.VERSION, dependencies = MCEconomy2.DEPENDENCY)
public class MCEconomy2 {

	public static final String MODID = "mceconomy2";
	public static final String VERSION = "2.4.5";

	public static final String DEPENDENCY = "";//"before:SextiarySector";

	@Mod.Instance(MCEconomyAPI.MODID)
	public static MCEconomy2 instance;

	@SidedProxy(clientSide = "shift.mceconomy2.proxy.ClientProxy", serverSide = "shift.mceconomy2.proxy.CommonProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{

		Config.ConfigRead(event);

		MinecraftForge.EVENT_BUS.register(new CommonEventManager());

		if (event.getSide().isClient()) {
			MinecraftForge.EVENT_BUS.register(new HUDMP());
		}
		PacketHandler.init(event);

		MCEconomyAPI.MPManager = MPManager.getInstance();
		MCEconomyAPI.SoundManager = new MCESoundManager();
		MCEconomyAPI.ShopManager = new ShopManager();
		MCEconomyAPI.registerPurchaseItem();

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

		//MCEItems.initItem();

		/*
		MCEconomyAPI.MPManager = MPManager.getInstance();
		MCEconomyAPI.SoundManager = new MCESoundManager();
		MCEconomyAPI.ShopManager = new ShopManager();
		MCEconomyAPI.registerPurchaseItem();
		*/

		int i = MCEconomyAPI.ShopManager.registerProductList(

				new ProductList() {

					@Override
					public String getProductListName() {
						return "[MCEconomy] Sample";
					}

				});

		MCEconomyAPI.ShopManager.getProductList(i).addItemProduct(new ProductItem(new ItemStack(Items.stick, 1), 10));
		MCEconomyAPI.ShopManager.getProductList(i).addItemProduct(new ProductItem(new ItemStack(Items.iron_ingot, 1), 30));
		MCEconomyAPI.ShopManager.getProductList(i).addItemProduct(new ProductItem(new ItemStack(Blocks.iron_block, 1), 130));

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new MCEGuiHandler());

	}

}
