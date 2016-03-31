package shift.mceconomy2.api;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import shift.mceconomy2.api.purchase.IPurchaseItem;
import shift.mceconomy2.api.shop.IProductList;
import shift.mceconomy2.api.shop.IShop;
import shift.mceconomy2.api.shop.IShopManager;
import shift.mceconomy2.api.shop.ProductList;

/**
 * MCEconomyのAPI <br>
 * MCEconomyとのやりとりはこのClassのメソッドを使用する(他の場合は互換性の保証が出来ない)
 * @since 1.0.0
 * @version 2.0.0
 * @author Shift02
 */
public class MCEconomyAPI {

    public static final String MODID = "mceconomy2";

    public static IMPManager MPManager;

    public static ISoundManager SoundManager;

    public static IShopManager ShopManager;

    static {
        //registerPurchaseItem();
    }

    /*
     *--------------------------------------
     *  Minecraft Point
     *--------------------------------------
     */

    /**
     * addPlayerMP 指定したプレイヤーのMPを追加
     * @param entityPlayer MPを追加するプレイヤー
     * @param amount MPの額
     * @return 実際に追加できたMPの額
     */
    public static int addPlayerMP(EntityPlayer entityPlayer, int amount, boolean simulation) {
        return MPManager.addPlayerMP(entityPlayer, amount, simulation);
    }

    /**
     * reducePlayerMP 指定したプレイヤーのMPを削減
     * @param entityPlayer MPを削減するプレイヤー
     * @param amount MPの額
     * @return 実際に削減できたMPの額
     */
    public static int reducePlayerMP(EntityPlayer entityPlayer, int amount, boolean simulation) {
        return MPManager.reducePlayerMP(entityPlayer, amount, simulation);
    }

    /**
     * setPlayerMP  指定したプレイヤーのMPを設定
     * @param entityPlayer MPを設定するプレイヤー
     * @param amount MPの額
     */
    public static void setPlayerMP(EntityPlayer entityPlayer, int amount) {
        MPManager.setPlayerMP(entityPlayer, amount);
    }

    /**
     * getPlayerMP 指定したプレーヤーの所持金額を返す
     * @param entityPlayer MPを所持するプレイヤー
     * @return プレイヤーの所持しておるMP量
     */
    public static int getPlayerMP(EntityPlayer entityPlayer) {
        return MPManager.getPlayerMP(entityPlayer);
    }

    //廃止
    /**
     * printChatMPMessage 指定したプレーヤーの所持金額をチャット欄に表示
     * @param entityPlayer MPを所持するプレイヤー
     */
    @Deprecated
    public static void printChatMPMessage(EntityPlayer entityPlayer) {
        //MPManager.printChatMPMessage(entityPlayer);
    }

    //未実装
    /**
     * spawnWorldMP ワールドにMPコインをスポーンさせる(未実装)
     * @param world ワールド
     * @param x x軸
     * @param y y軸
     * @param z z軸
     * @param amount MPの額
     */
    @Deprecated
    public static void spawnWorldMP(World world, int x, int y, int z, int amount) {
        //MPManager.spawnWorldMP(world, x, y, z, amount);
    }

    /*
     *--------------------------------------
     *  Shop
     *--------------------------------------
     */

    /**
     * 非推奨 <br>
     * registerProductList IProductListを実装したClassを登録
     * @param list 商品リスト <br>{@link ProductList}  を使うと便利です
     * @see IProductList
     * @return ShopID
     */
    @Deprecated
    public static int registerProductList(IProductList list) {
        return ShopManager.registerProductList(list);
    }

    /**
     * 非推奨 <br>
     * getProductList ShopIDからIProductListを取得
     * @param id ShopID
     * @return IProductList
     */
    @Deprecated
    public static IProductList getProductList(int id) {
        return ShopManager.getProductList(id);
    }

    /**
     * registerProductList IShopを実装したClassを登録
     * @param shop 店屋データ
     * @see IShop
     * @return ShopID
     */
    public static int registerShop(IShop shop) {
        return ShopManager.registerShop(shop);
    }

    /**
     * getProductList ShopIDからIShopを取得
     * @param id ShopID
     * @return IShop
     */
    public static IShop getShop(int id) {
        return ShopManager.getShop(id);
    }

    /**
     * openShopGui ShopのGUIを開く
     * @param id ShopID
     * @param player プレイヤー
     * @param world ワールド
     * @param x x軸
     * @param y y軸
     * @param z z軸
     */
    public static void openShopGui(int id, EntityPlayer player, World world, int x, int y, int z) {
        ShopManager.openShopGui(id, player, world, x, y, z);
    }

    /**
     * addPurchaseItem アイテムの買い取り価格を判定するクラスを登録
     * @param purchaseItem 登録するIPurchaseItemクラス
     */
    public static void addPurchaseItem(IPurchaseItem purchaseItem) {
        ShopManager.addPurchaseItem(purchaseItem);
    }

    /**
     * addPurchaseItem Mobなどがアイテムを買い取る価格をItemStackで登録
     * @param PurchaseItem 買い取りアイテム
     * @param amount 価格  -1で非売品に設定出来ます
     */
    public static void addPurchaseItem(ItemStack PurchaseItem, int amount) {
        ShopManager.addPurchaseItem(PurchaseItem, amount);
    }

    /**
     * addPurchaseItem Mobなどがアイテムを買い取る価格を鉱石辞書名で登録
     * @param oreName 買い取り対象となる鉱石辞書名
     * @param amount 価格  -1で非売品に設定出来ます
     */
    public static void addPurchaseItem(String oreName, int amount) {
        ShopManager.addPurchaseItem(oreName, amount);
    }

    /**
     * getPurchase アイテムの買い取り額を取得
     * @param item 売るアイテム
     * @return 価格
     */
    public static int getPurchase(ItemStack item) {
        return ShopManager.getPurchase(item);
    }

    /**
     * hasPurchase アイテムに価格が設定されているか
     * @param item 調べるアイテム
     * @return 設定されていればtrue 非売品はfalseになります.
     */
    public static boolean hasPurchase(ItemStack item) {
        return ShopManager.hasPurchase(item);
    }

    /**
     * addPurchaseItem Mobなどが流体を買い取る価格を登録
     * @param fluid 買い取る流体
     * @param mp 価格(1mB)  -1で非売品に設定出来ます
     */
    public static void addPurchaseFluid(Fluid fluid, double mp) {
        ShopManager.addPurchaseFluid(fluid, mp);
    }

    /**
     * getPurchase 流体の買い取り額を取得
     * @param fluid 売る流体
     * @return 価格(1mB)
     */
    public static double getFluidPurchase(Fluid fluid) {
        return ShopManager.getFluidPurchase(fluid);
    }

    /**
     * hasPurchase 流体に価格が設定されているか
     * @param fluid 調べる流体
     * @return 設定されていればtrue 非売品はfalseになります.
     */
    public static boolean hasFluidPurchase(Fluid fluid) {
        return ShopManager.hasFluidPurchase(fluid);
    }

    /**
     * registerPurchaseItem API読み込み時にバニラアイテムの価格が自動で設定される。
     * MODアイテムにMPを設定する時の参考にしてください。
     */
    public static void registerPurchaseItem() {

        //MCEconomyAPI.ShopManager = new ShopManager();

        //建築 Block
        addPurchaseItem(new ItemStack(Blocks.stone), 1);
        addPurchaseItem(new ItemStack(Blocks.grass), 10);
        addPurchaseItem(new ItemStack(Blocks.dirt), 5);
        addPurchaseItem(new ItemStack(Blocks.dirt, 1, 2), 10);
        addPurchaseItem(new ItemStack(Blocks.cobblestone), 0);
        for (int i = 0; i < 6; i++) {
            addPurchaseItem(new ItemStack(Blocks.planks, 1, i), 4);
        }
        addPurchaseItem(new ItemStack(Blocks.bedrock), -1);
        addPurchaseItem(new ItemStack(Blocks.sand), 1);
        addPurchaseItem(new ItemStack(Blocks.sand, 1, 1), 4);//赤砂
        addPurchaseItem(new ItemStack(Blocks.gravel), 5);
        addPurchaseItem(new ItemStack(Blocks.gold_ore), 2000);
        addPurchaseItem(new ItemStack(Blocks.iron_ore), 100);
        addPurchaseItem(new ItemStack(Blocks.coal_ore), 70);
        for (int i = 0; i < 6; i++) {
            addPurchaseItem(new ItemStack(Blocks.log, 1, i), 10);
        }
        for (int i = 0; i < 2; i++) {
            addPurchaseItem(new ItemStack(Blocks.log2, 1, i), 10);
        }
        addPurchaseItem(new ItemStack(Blocks.sponge), -1);
        addPurchaseItem(new ItemStack(Blocks.glass), 30);
        addPurchaseItem(new ItemStack(Blocks.lapis_ore), 1200);
        addPurchaseItem(new ItemStack(Blocks.lapis_block), 2000);
        addPurchaseItem(new ItemStack(Blocks.sandstone), 8);
        for (int i = 1; i < 3; i++) {
            addPurchaseItem(new ItemStack(Blocks.sandstone, 1, i), 16);
        }
        for (int i = 0; i < 16; i++) {
            addPurchaseItem(new ItemStack(Blocks.wool, 1, i), 60);
        }
        addPurchaseItem(new ItemStack(Blocks.gold_block), 36000);
        addPurchaseItem(new ItemStack(Blocks.iron_block), 4500);
        addPurchaseItem(new ItemStack(Blocks.double_stone_slab, 1, 0), 2);//焼石
        addPurchaseItem(new ItemStack(Blocks.double_stone_slab, 1, 1), 8);//砂岩
        addPurchaseItem(new ItemStack(Blocks.double_stone_slab, 1, 3), 0);//石
        addPurchaseItem(new ItemStack(Blocks.double_stone_slab, 1, 4), 200);//レンガ
        addPurchaseItem(new ItemStack(Blocks.double_stone_slab, 1, 5), 12);//石レンガ
        addPurchaseItem(new ItemStack(Blocks.double_stone_slab, 1, 6), 50);//ネザーレンガ
        addPurchaseItem(new ItemStack(Blocks.double_stone_slab, 1, 7), 1200);//水晶
        //ハーフ
        addPurchaseItem(new ItemStack(Blocks.stone_slab, 1, 0), 1);//焼石
        addPurchaseItem(new ItemStack(Blocks.stone_slab, 1, 1), 4);//砂岩
        addPurchaseItem(new ItemStack(Blocks.stone_slab, 1, 3), 0);//石
        addPurchaseItem(new ItemStack(Blocks.stone_slab, 1, 4), 100);//レンガ
        addPurchaseItem(new ItemStack(Blocks.stone_slab, 1, 5), 5);//石レンガ
        addPurchaseItem(new ItemStack(Blocks.stone_slab, 1, 6), 25);//ネザーレンガ
        addPurchaseItem(new ItemStack(Blocks.stone_slab, 1, 7), 600);//水晶
        //
        addPurchaseItem(new ItemStack(Blocks.brick_block), 200);
        addPurchaseItem(new ItemStack(Blocks.bookshelf), 510);
        addPurchaseItem(new ItemStack(Blocks.mossy_cobblestone), 10);
        addPurchaseItem(new ItemStack(Blocks.obsidian), 5);
        addPurchaseItem(new ItemStack(Blocks.oak_stairs), 10);//オークの階段
        addPurchaseItem(new ItemStack(Blocks.diamond_ore), 8000);
        addPurchaseItem(new ItemStack(Blocks.diamond_block), 90000);
        addPurchaseItem(new ItemStack(Blocks.wheat), -1);
        addPurchaseItem(new ItemStack(Blocks.farmland), -1);
        addPurchaseItem(new ItemStack(Blocks.stone_stairs), 1);
        addPurchaseItem(new ItemStack(Blocks.redstone_ore), 50);
        addPurchaseItem(new ItemStack(Blocks.lit_redstone_ore), -1);//光っているレッドストーン
        addPurchaseItem(new ItemStack(Blocks.ice), 65);
        addPurchaseItem(new ItemStack(Blocks.snow), 1);
        addPurchaseItem(new ItemStack(Blocks.clay), 120);
        addPurchaseItem(new ItemStack(Blocks.pumpkin), 90);
        addPurchaseItem(new ItemStack(Blocks.netherrack), 0);
        addPurchaseItem(new ItemStack(Blocks.soul_sand), 2);
        addPurchaseItem(new ItemStack(Blocks.glowstone), 400);
        addPurchaseItem(new ItemStack(Blocks.portal), -1);//ポータル
        addPurchaseItem(new ItemStack(Blocks.lit_pumpkin), 120);
        addPurchaseItem(new ItemStack(Blocks.stonebrick), 10);
        addPurchaseItem(new ItemStack(Blocks.stonebrick, 1, 1), 8);
        addPurchaseItem(new ItemStack(Blocks.stonebrick, 1, 2), 5);
        addPurchaseItem(new ItemStack(Blocks.stonebrick, 1, 3), 14);
        addPurchaseItem(new ItemStack(Blocks.melon_block), 95);
        addPurchaseItem(new ItemStack(Blocks.pumpkin_stem), -1);//根
        addPurchaseItem(new ItemStack(Blocks.melon_stem), -1);
        addPurchaseItem(new ItemStack(Blocks.brick_stairs), 210);
        addPurchaseItem(new ItemStack(Blocks.stone_brick_stairs), 12);
        addPurchaseItem(new ItemStack(Blocks.mycelium), 1);
        addPurchaseItem(new ItemStack(Blocks.nether_brick), 10);
        addPurchaseItem(new ItemStack(Blocks.nether_brick_stairs), 12);
        addPurchaseItem(new ItemStack(Blocks.nether_wart), -1);//ブロック状態のネザーウォート
        addPurchaseItem(new ItemStack(Blocks.end_stone), 10);
        addPurchaseItem(new ItemStack(Blocks.dragon_egg), -1);
        for (int i = 0; i < 6; i++) {
            addPurchaseItem(new ItemStack(Blocks.double_wooden_slab, 1, i), 4);
        }
        for (int i = 0; i < 6; i++) {
            addPurchaseItem(new ItemStack(Blocks.wooden_slab, 1, i), 2);
        }
        addPurchaseItem(new ItemStack(Blocks.sandstone_stairs), 20);
        addPurchaseItem(new ItemStack(Blocks.emerald_ore), 1500);
        addPurchaseItem(new ItemStack(Blocks.emerald_block), 2700);
        addPurchaseItem(new ItemStack(Blocks.spruce_stairs), 10);//まつの階段
        addPurchaseItem(new ItemStack(Blocks.birch_stairs), 10);
        addPurchaseItem(new ItemStack(Blocks.jungle_stairs), 10);
        for (int i = 0; i < 2; i++) {
            addPurchaseItem(new ItemStack(Blocks.cobblestone_wall, 1, i), 2);
        }
        addPurchaseItem(new ItemStack(Blocks.quartz_ore), 280);
        addPurchaseItem(new ItemStack(Blocks.quartz_block, 1, 0), 1200);
        addPurchaseItem(new ItemStack(Blocks.quartz_block, 1, 1), 1280);
        addPurchaseItem(new ItemStack(Blocks.quartz_block, 1, 2), 1280);
        addPurchaseItem(new ItemStack(Blocks.quartz_stairs), 1400);
        for (int i = 0; i < 16; i++) {
            addPurchaseItem(new ItemStack(Blocks.stained_hardened_clay, 1, i), 130);
        }
        addPurchaseItem(new ItemStack(Blocks.hay_block), 450);
        addPurchaseItem(new ItemStack(Blocks.hardened_clay), 130);
        addPurchaseItem(new ItemStack(Blocks.coal_block), 450);
        addPurchaseItem(new ItemStack(Blocks.packed_ice), 100);
        addPurchaseItem(new ItemStack(Blocks.acacia_stairs), 10);//階段
        addPurchaseItem(new ItemStack(Blocks.dark_oak_stairs), 10);
        for (int i = 0; i < 16; i++) {
            addPurchaseItem(new ItemStack(Blocks.stained_glass, 1, i), 35);
        }
        //建築 Item

        //装飾 Block
        for (int i = 0; i < 6; i++) {
            addPurchaseItem(new ItemStack(Blocks.sapling, 1, i), 2);
        }
        for (int i = 0; i < 6; i++) {
            addPurchaseItem(new ItemStack(Blocks.leaves, 1, i), 3);
        }
        for (int i = 0; i < 2; i++) {
            addPurchaseItem(new ItemStack(Blocks.leaves2, 1, i), 3);
        }
        addPurchaseItem(new ItemStack(Blocks.bed), -1);//ブロック状態のベッド
        addPurchaseItem(new ItemStack(Blocks.web), 40);
        for (int i = 0; i < 3; i++) {
            addPurchaseItem(new ItemStack(Blocks.tallgrass, 1, i), 1);
        }
        addPurchaseItem(new ItemStack(Blocks.yellow_flower), 20);
        for (int i = 0; i < 9; i++) {
            addPurchaseItem(new ItemStack(Blocks.red_flower, 1, i), 20);
        }
        addPurchaseItem(new ItemStack(Blocks.brown_mushroom), 15);
        addPurchaseItem(new ItemStack(Blocks.red_mushroom), 15);
        addPurchaseItem(new ItemStack(Blocks.torch), 10);
        addPurchaseItem(new ItemStack(Blocks.fire), -1);
        addPurchaseItem(new ItemStack(Blocks.mob_spawner), -1);
        addPurchaseItem(new ItemStack(Blocks.chest), 50);
        addPurchaseItem(new ItemStack(Blocks.crafting_table), 20);
        addPurchaseItem(new ItemStack(Blocks.furnace), 10);
        addPurchaseItem(new ItemStack(Blocks.lit_furnace), -1);//火のついたカマド
        addPurchaseItem(new ItemStack(Blocks.standing_sign), -1);//看板
        addPurchaseItem(new ItemStack(Blocks.ladder), 5);
        addPurchaseItem(new ItemStack(Blocks.wall_sign), -1);//壁看板
        addPurchaseItem(new ItemStack(Blocks.snow_layer), -1);//浅い雪
        addPurchaseItem(new ItemStack(Blocks.cactus), 60);
        addPurchaseItem(new ItemStack(Blocks.reeds), -1);//ブロックのサトウキビ
        addPurchaseItem(new ItemStack(Blocks.jukebox), 10250);
        addPurchaseItem(new ItemStack(Blocks.fence), 8);
        for (int i = 0; i < 6; i++) {
            addPurchaseItem(new ItemStack(Blocks.monster_egg, 1, i), -1);//シルバーFish
        }
        addPurchaseItem(new ItemStack(Blocks.brown_mushroom_block), -1);
        addPurchaseItem(new ItemStack(Blocks.red_mushroom_block), -1);
        addPurchaseItem(new ItemStack(Blocks.iron_bars), 190);
        addPurchaseItem(new ItemStack(Blocks.glass_pane), 12);
        addPurchaseItem(new ItemStack(Blocks.vine), 1);
        addPurchaseItem(new ItemStack(Blocks.waterlily), 1);
        addPurchaseItem(new ItemStack(Blocks.nether_brick_fence), 12);
        addPurchaseItem(new ItemStack(Blocks.enchanting_table), 22000);
        addPurchaseItem(new ItemStack(Blocks.end_portal), -1);
        addPurchaseItem(new ItemStack(Blocks.end_portal_frame), -1);
        addPurchaseItem(new ItemStack(Blocks.ender_chest), 150);
        addPurchaseItem(new ItemStack(Blocks.flower_pot), -1);//植木鉢
        addPurchaseItem(new ItemStack(Blocks.skull), -1);//頭
        addPurchaseItem(new ItemStack(Blocks.anvil, 1, 0), 16000);
        addPurchaseItem(new ItemStack(Blocks.anvil, 1, 1), -1);//少し壊れた金床
        addPurchaseItem(new ItemStack(Blocks.anvil, 1, 2), -1);//壊れた金床
        addPurchaseItem(new ItemStack(Blocks.trapped_chest), 520);
        for (int i = 0; i < 16; i++) {
            addPurchaseItem(new ItemStack(Blocks.carpet, 1, i), 60);
        }
        addPurchaseItem(new ItemStack(Blocks.double_plant, 1, 0), 20);
        addPurchaseItem(new ItemStack(Blocks.double_plant, 1, 1), 20);
        addPurchaseItem(new ItemStack(Blocks.double_plant, 1, 2), 1);
        addPurchaseItem(new ItemStack(Blocks.double_plant, 1, 3), 1);
        addPurchaseItem(new ItemStack(Blocks.double_plant, 1, 4), 20);
        addPurchaseItem(new ItemStack(Blocks.double_plant, 1, 5), 20);
        for (int i = 0; i < 16; i++) {
            addPurchaseItem(new ItemStack(Blocks.stained_glass_pane, 1, i), 18);
        }
        //装飾 Item
        addPurchaseItem(new ItemStack(Items.painting), 20);
        addPurchaseItem(new ItemStack(Items.sign), 24);
        addPurchaseItem(new ItemStack(Items.bed), 200);
        addPurchaseItem(new ItemStack(Items.item_frame), 60);
        addPurchaseItem(new ItemStack(Items.flower_pot), 150);
        addPurchaseItem(new ItemStack(Items.skull), 50);

        //レッドストーン Block
        addPurchaseItem(new ItemStack(Blocks.dispenser), 200);
        addPurchaseItem(new ItemStack(Blocks.noteblock), 120);
        addPurchaseItem(new ItemStack(Blocks.sticky_piston), 800);
        addPurchaseItem(new ItemStack(Blocks.piston), 700);
        addPurchaseItem(new ItemStack(Blocks.piston_head), -1);//ピストンの先
        addPurchaseItem(new ItemStack(Blocks.piston_extension), -1);
        addPurchaseItem(new ItemStack(Blocks.tnt), 80);
        addPurchaseItem(new ItemStack(Blocks.redstone_wire), -1);
        addPurchaseItem(new ItemStack(Blocks.wooden_door), -1);//木のドア
        addPurchaseItem(new ItemStack(Blocks.lever), 55);
        addPurchaseItem(new ItemStack(Blocks.stone_pressure_plate), 5);
        addPurchaseItem(new ItemStack(Blocks.iron_door), -1);//鉄のドア
        addPurchaseItem(new ItemStack(Blocks.wooden_pressure_plate), 10);
        addPurchaseItem(new ItemStack(Blocks.unlit_redstone_torch), -1);//消えているレッドストーンTouch
        addPurchaseItem(new ItemStack(Blocks.redstone_torch), 45);
        addPurchaseItem(new ItemStack(Blocks.stone_button), 1);
        addPurchaseItem(new ItemStack(Blocks.unpowered_repeater), -1);
        addPurchaseItem(new ItemStack(Blocks.powered_repeater), -1);
        addPurchaseItem(new ItemStack(Blocks.trapdoor), 15);
        addPurchaseItem(new ItemStack(Blocks.fence_gate), 10);
        addPurchaseItem(new ItemStack(Blocks.redstone_lamp), 1500);
        addPurchaseItem(new ItemStack(Blocks.lit_redstone_lamp), -1);
        addPurchaseItem(new ItemStack(Blocks.tripwire_hook), 250);
        addPurchaseItem(new ItemStack(Blocks.tripwire), -1);//糸
        addPurchaseItem(new ItemStack(Blocks.command_block), -1);//コマンドブロック
        addPurchaseItem(new ItemStack(Blocks.wooden_button), 5);
        addPurchaseItem(new ItemStack(Blocks.light_weighted_pressure_plate), 8000);
        addPurchaseItem(new ItemStack(Blocks.heavy_weighted_pressure_plate), 1000);
        addPurchaseItem(new ItemStack(Blocks.unpowered_comparator), -1);
        addPurchaseItem(new ItemStack(Blocks.powered_comparator), -1);
        addPurchaseItem(new ItemStack(Blocks.daylight_detector), 1000);
        addPurchaseItem(new ItemStack(Blocks.redstone_block), 360);
        addPurchaseItem(new ItemStack(Blocks.hopper), 2600);
        addPurchaseItem(new ItemStack(Blocks.dropper), 50);
        //レッドストーン Item
        addPurchaseItem(new ItemStack(Items.wooden_door), 28);
        addPurchaseItem(new ItemStack(Items.iron_door), 2800);
        addPurchaseItem(new ItemStack(Items.redstone), 40);
        addPurchaseItem(new ItemStack(Items.repeater), 150);
        addPurchaseItem(new ItemStack(Items.comparator), 210);

        //移動 Block
        addPurchaseItem(new ItemStack(Blocks.golden_rail), 4100);
        addPurchaseItem(new ItemStack(Blocks.detector_rail), 600);
        addPurchaseItem(new ItemStack(Blocks.rail), 250);
        addPurchaseItem(new ItemStack(Blocks.activator_rail), 600);
        //移動 Item
        addPurchaseItem(new ItemStack(Items.minecart), 800);
        addPurchaseItem(new ItemStack(Items.saddle), 40);
        addPurchaseItem(new ItemStack(Items.boat), 30);
        addPurchaseItem(new ItemStack(Items.chest_minecart), 830);
        addPurchaseItem(new ItemStack(Items.furnace_minecart), 820);
        addPurchaseItem(new ItemStack(Items.carrot_on_a_stick), 26);
        addPurchaseItem(new ItemStack(Items.tnt_minecart), 800);
        addPurchaseItem(new ItemStack(Items.hopper_minecart), 980);
        addPurchaseItem(new ItemStack(Items.command_block_minecart), -1);

        //その他 Block
        addPurchaseItem(new ItemStack(Blocks.beacon), 9000);
        //その他 Item
        addPurchaseItem(new ItemStack(Items.bucket), 150);
        addPurchaseItem(new ItemStack(Items.water_bucket), 150);
        addPurchaseItem(new ItemStack(Items.lava_bucket), 200);
        addPurchaseItem(new ItemStack(Items.snowball), 0);
        addPurchaseItem(new ItemStack(Items.milk_bucket), 600);
        addPurchaseItem(new ItemStack(Items.paper), 10);
        addPurchaseItem(new ItemStack(Items.book), 150);
        addPurchaseItem(new ItemStack(Items.slime_ball), 1);
        addPurchaseItem(new ItemStack(Items.bone), 1);
        addPurchaseItem(new ItemStack(Items.filled_map), 1);
        addPurchaseItem(new ItemStack(Items.ender_pearl), 20);
        addPurchaseItem(new ItemStack(Items.ender_eye), 20);
        addPurchaseItem(new ItemStack(Items.spawn_egg, 1, OreDictionary.WILDCARD_VALUE), -1);
        addPurchaseItem(new ItemStack(Items.experience_bottle), 150);
        addPurchaseItem(new ItemStack(Items.fire_charge), 50);
        addPurchaseItem(new ItemStack(Items.writable_book), 180);
        addPurchaseItem(new ItemStack(Items.written_book), -1);
        addPurchaseItem(new ItemStack(Items.map), 10);
        addPurchaseItem(new ItemStack(Items.fireworks, 1, OreDictionary.WILDCARD_VALUE), 10);
        addPurchaseItem(new ItemStack(Items.firework_charge, 1, OreDictionary.WILDCARD_VALUE), 10);
        addPurchaseItem(new ItemStack(Items.iron_horse_armor), 1000);
        addPurchaseItem(new ItemStack(Items.golden_horse_armor), 10000);
        addPurchaseItem(new ItemStack(Items.diamond_horse_armor), 20000);
        addPurchaseItem(new ItemStack(Items.record_13), 500);
        addPurchaseItem(new ItemStack(Items.record_cat), 500);
        addPurchaseItem(new ItemStack(Items.record_blocks), 500);
        addPurchaseItem(new ItemStack(Items.record_chirp), 500);
        addPurchaseItem(new ItemStack(Items.record_far), 500);
        addPurchaseItem(new ItemStack(Items.record_mall), 500);
        addPurchaseItem(new ItemStack(Items.record_mellohi), 500);
        addPurchaseItem(new ItemStack(Items.record_stal), 500);
        addPurchaseItem(new ItemStack(Items.record_strad), 500);
        addPurchaseItem(new ItemStack(Items.record_ward), 500);
        addPurchaseItem(new ItemStack(Items.record_11), -1);
        addPurchaseItem(new ItemStack(Items.record_wait), 500);

        //食べ物 Block
        addPurchaseItem(new ItemStack(Blocks.cake), -1);//ブロックのケーキ
        addPurchaseItem(new ItemStack(Blocks.cocoa), -1);//ブロック状態のカカオ
        addPurchaseItem(new ItemStack(Blocks.carrots), -1);//にんじん
        addPurchaseItem(new ItemStack(Blocks.potatoes), -1);//じゃがいも

        //食べ物 Item
        addPurchaseItem(new ItemStack(Items.apple), 20);
        addPurchaseItem(new ItemStack(Items.mushroom_stew), 50);
        addPurchaseItem(new ItemStack(Items.porkchop), 20);
        addPurchaseItem(new ItemStack(Items.cooked_porkchop), 28);
        addPurchaseItem(new ItemStack(Items.golden_apple, 1, 0), 4000);
        addPurchaseItem(new ItemStack(Items.golden_apple, 1, 1), 36000);
        addPurchaseItem(new ItemStack(Items.fish, 1, 0), 62);
        addPurchaseItem(new ItemStack(Items.fish, 1, 1), 272);//さけ
        addPurchaseItem(new ItemStack(Items.fish, 1, 2), 160);//くまのみ
        addPurchaseItem(new ItemStack(Items.fish, 1, 3), 104);//ふぐ
        addPurchaseItem(new ItemStack(Items.cooked_fished, 1, 0), 70);
        addPurchaseItem(new ItemStack(Items.cooked_fished, 1, 1), 296);
        addPurchaseItem(new ItemStack(Items.cake), 640);
        addPurchaseItem(new ItemStack(Items.cookie), 22);
        addPurchaseItem(new ItemStack(Items.melon), 8);
        addPurchaseItem(new ItemStack(Items.beef), 90);
        addPurchaseItem(new ItemStack(Items.cooked_beef), 100);
        addPurchaseItem(new ItemStack(Items.chicken), 40);
        addPurchaseItem(new ItemStack(Items.cooked_chicken), 50);
        addPurchaseItem(new ItemStack(Items.rotten_flesh), 0);
        addPurchaseItem(new ItemStack(Items.spider_eye), 1);
        addPurchaseItem(new ItemStack(Items.carrot), 53);
        addPurchaseItem(new ItemStack(Items.potato), 42);
        addPurchaseItem(new ItemStack(Items.baked_potato), 50);
        addPurchaseItem(new ItemStack(Items.poisonous_potato), 0);
        addPurchaseItem(new ItemStack(Items.golden_carrot), 650);
        addPurchaseItem(new ItemStack(Items.pumpkin_pie), 128);

        //道具 素材の金属の1/10
        addPurchaseItem(new ItemStack(Items.iron_shovel), 50);
        addPurchaseItem(new ItemStack(Items.iron_pickaxe), 150);
        addPurchaseItem(new ItemStack(Items.iron_axe), 150);
        addPurchaseItem(new ItemStack(Items.flint_and_steel), 50);
        addPurchaseItem(new ItemStack(Items.wooden_shovel), 1);
        addPurchaseItem(new ItemStack(Items.wooden_pickaxe), 1);
        addPurchaseItem(new ItemStack(Items.wooden_axe), 1);
        addPurchaseItem(new ItemStack(Items.stone_shovel), 2);
        addPurchaseItem(new ItemStack(Items.stone_pickaxe), 2);
        addPurchaseItem(new ItemStack(Items.stone_axe), 2);
        addPurchaseItem(new ItemStack(Items.diamond_shovel), 1000);
        addPurchaseItem(new ItemStack(Items.diamond_pickaxe), 3000);
        addPurchaseItem(new ItemStack(Items.diamond_axe), 3000);
        addPurchaseItem(new ItemStack(Items.golden_shovel), 400);
        addPurchaseItem(new ItemStack(Items.golden_pickaxe), 1200);
        addPurchaseItem(new ItemStack(Items.golden_axe), 1200);
        addPurchaseItem(new ItemStack(Items.wooden_hoe), 1);
        addPurchaseItem(new ItemStack(Items.stone_hoe), 2);
        addPurchaseItem(new ItemStack(Items.iron_hoe), 100);
        addPurchaseItem(new ItemStack(Items.diamond_hoe), 2000);
        addPurchaseItem(new ItemStack(Items.golden_hoe), 800);
        addPurchaseItem(new ItemStack(Items.compass), 2100);
        addPurchaseItem(new ItemStack(Items.fishing_rod), 30);
        addPurchaseItem(new ItemStack(Items.clock), 15000);
        addPurchaseItem(new ItemStack(Items.shears), 500);
        addPurchaseItem(new ItemStack(Items.lead), 30);
        addPurchaseItem(new ItemStack(Items.name_tag), 60);

        //戦闘
        addPurchaseItem(new ItemStack(Items.bow), 20);
        addPurchaseItem(new ItemStack(Items.arrow), 1);
        addPurchaseItem(new ItemStack(Items.iron_sword), 100);
        addPurchaseItem(new ItemStack(Items.wooden_sword), 1);
        addPurchaseItem(new ItemStack(Items.stone_sword), 2);
        addPurchaseItem(new ItemStack(Items.diamond_sword), 2000);
        addPurchaseItem(new ItemStack(Items.golden_sword), 800);
        addPurchaseItem(new ItemStack(Items.leather_helmet), 250);//革は素材の値段のまま
        addPurchaseItem(new ItemStack(Items.leather_chestplate), 400);
        addPurchaseItem(new ItemStack(Items.leather_leggings), 350);
        addPurchaseItem(new ItemStack(Items.leather_boots), 200);
        addPurchaseItem(new ItemStack(Items.chainmail_helmet), -1);//MODで入手できるようにする場合は価格を設定する
        addPurchaseItem(new ItemStack(Items.chainmail_chestplate), -1);
        addPurchaseItem(new ItemStack(Items.chainmail_leggings), -1);
        addPurchaseItem(new ItemStack(Items.chainmail_boots), -1);
        addPurchaseItem(new ItemStack(Items.iron_helmet), 250);
        addPurchaseItem(new ItemStack(Items.iron_chestplate), 400);
        addPurchaseItem(new ItemStack(Items.iron_leggings), 350);
        addPurchaseItem(new ItemStack(Items.iron_boots), 200);
        addPurchaseItem(new ItemStack(Items.diamond_helmet), 5000);
        addPurchaseItem(new ItemStack(Items.diamond_chestplate), 8000);
        addPurchaseItem(new ItemStack(Items.diamond_leggings), 7000);
        addPurchaseItem(new ItemStack(Items.diamond_boots), 4000);
        addPurchaseItem(new ItemStack(Items.golden_helmet), 2000);
        addPurchaseItem(new ItemStack(Items.golden_chestplate), 6400);
        addPurchaseItem(new ItemStack(Items.golden_leggings), 5600);
        addPurchaseItem(new ItemStack(Items.golden_boots), 3200);
        addPurchaseItem(new ItemStack(Items.enchanted_book), 30);

        //ポーション Block
        addPurchaseItem(new ItemStack(Blocks.brewing_stand), -1);//ブロック状態のスタンド
        addPurchaseItem(new ItemStack(Blocks.cauldron), -1);//ブロック状態の釜
        //ポーション Item
        addPurchaseItem(new ItemStack(Items.ghast_tear), 5);
        addPurchaseItem(new ItemStack(Items.potionitem), 1);//ポーションはまた今度
        addPurchaseItem(new ItemStack(Items.glass_bottle), 10);
        addPurchaseItem(new ItemStack(Items.fermented_spider_eye), 1);
        addPurchaseItem(new ItemStack(Items.blaze_powder), 2);
        addPurchaseItem(new ItemStack(Items.magma_cream), 5);
        addPurchaseItem(new ItemStack(Items.brewing_stand), 50);
        addPurchaseItem(new ItemStack(Items.cauldron), 50);
        addPurchaseItem(new ItemStack(Items.brewing_stand), 3500);
        addPurchaseItem(new ItemStack(Items.speckled_melon), 650);

        //材料
        addPurchaseItem(new ItemStack(Items.coal, 1, 0), 50);
        addPurchaseItem(new ItemStack(Items.coal, 1, 1), 10);//木炭
        addPurchaseItem(new ItemStack(Items.diamond), 10000);
        addPurchaseItem(new ItemStack(Items.iron_ingot), 500);
        addPurchaseItem(new ItemStack(Items.gold_ingot), 4000);
        addPurchaseItem(new ItemStack(Items.stick), 2);
        addPurchaseItem(new ItemStack(Items.bowl), 3);
        addPurchaseItem(new ItemStack(Items.string), 4);
        addPurchaseItem(new ItemStack(Items.feather), 1);
        addPurchaseItem(new ItemStack(Items.gunpowder), 4);
        addPurchaseItem(new ItemStack(Items.wheat_seeds), 1);
        addPurchaseItem(new ItemStack(Items.wheat), 50);
        addPurchaseItem(new ItemStack(Items.bread), 80);
        addPurchaseItem(new ItemStack(Items.flint), 3);
        addPurchaseItem(new ItemStack(Items.leather), 50);
        addPurchaseItem(new ItemStack(Items.brick), 50);
        addPurchaseItem(new ItemStack(Items.clay_ball), 30);
        addPurchaseItem(new ItemStack(Items.reeds), 10);
        addPurchaseItem(new ItemStack(Items.egg), 20);
        addPurchaseItem(new ItemStack(Items.glowstone_dust), 100);
        addPurchaseItem(new ItemStack(Items.dye, 1, 0), 30);//染料 イカスミ
        addPurchaseItem(new ItemStack(Items.dye, 1, 1), 5);//染料
        addPurchaseItem(new ItemStack(Items.dye, 1, 2), 5);//染料
        addPurchaseItem(new ItemStack(Items.dye, 1, 3), 40);//染料 カカオ
        addPurchaseItem(new ItemStack(Items.dye, 1, 4), 220);//染料 ラピス
        addPurchaseItem(new ItemStack(Items.dye, 1, 5), 5);//染料
        addPurchaseItem(new ItemStack(Items.dye, 1, 6), 5);//染料
        addPurchaseItem(new ItemStack(Items.dye, 1, 7), 5);//染料
        addPurchaseItem(new ItemStack(Items.dye, 1, 8), 5);//染料
        addPurchaseItem(new ItemStack(Items.dye, 1, 9), 5);//染料
        addPurchaseItem(new ItemStack(Items.dye, 1, 10), 5);//染料
        addPurchaseItem(new ItemStack(Items.dye, 1, 11), 5);//染料
        addPurchaseItem(new ItemStack(Items.dye, 1, 12), 5);//染料
        addPurchaseItem(new ItemStack(Items.dye, 1, 13), 5);//染料
        addPurchaseItem(new ItemStack(Items.dye, 1, 14), 5);//染料
        addPurchaseItem(new ItemStack(Items.dye, 1, 15), 10);//染料 骨粉
        addPurchaseItem(new ItemStack(Items.sugar), 10);
        addPurchaseItem(new ItemStack(Items.pumpkin_seeds), 2);
        addPurchaseItem(new ItemStack(Items.melon_seeds), 2);
        addPurchaseItem(new ItemStack(Items.blaze_rod), 10);
        addPurchaseItem(new ItemStack(Items.gold_nugget), 440);
        addPurchaseItem(new ItemStack(Items.nether_wart), 10);
        addPurchaseItem(new ItemStack(Items.emerald), 300);
        addPurchaseItem(new ItemStack(Items.nether_star), 8000);
        addPurchaseItem(new ItemStack(Items.netherbrick), 20);
        addPurchaseItem(new ItemStack(Items.quartz), 300);

        //液体
        addPurchaseFluid(FluidRegistry.WATER, 0.0001);
        addPurchaseFluid(FluidRegistry.LAVA, 0.01);

        //Entity
        MCEconomyAPI.ShopManager.addPurchaseEntity(EntityZombie.class, 2);
        MCEconomyAPI.ShopManager.addPurchaseEntity(EntitySkeleton.class, 5);
        MCEconomyAPI.ShopManager.addPurchaseEntity(EntityCreeper.class, 6);
        MCEconomyAPI.ShopManager.addPurchaseEntity(EntityEnderman.class, 13);

        MCEconomyAPI.ShopManager.addPurchaseEntity(EntitySilverfish.class, 40);
        MCEconomyAPI.ShopManager.addPurchaseEntity(EntityWitch.class, 32);

        MCEconomyAPI.ShopManager.addPurchaseEntity(EntityGhast.class, 24);
        MCEconomyAPI.ShopManager.addPurchaseEntity(EntityBlaze.class, 8);
        MCEconomyAPI.ShopManager.addPurchaseEntity(EntityPigZombie.class, 6);

        MCEconomyAPI.ShopManager.addPurchaseEntity(EntityPlayer.class, -1);

        MCEconomyAPI.ShopManager.addPurchaseEntity(EntityVillager.class, 22);

        MCEconomyAPI.ShopManager.addPurchaseEntity(EntityBat.class, 300);

        MCEconomyAPI.ShopManager.addPurchaseEntity(EntityDragon.class, 7400);
        MCEconomyAPI.ShopManager.addPurchaseEntity(EntityWither.class, 3600);

    }

    /*
     *--------------------------------------
     *  Sound
     *--------------------------------------
     */
    public static void playCoinSoundEffect(World world, int x, int y, int z) {
        SoundManager.playCoinSoundEffect(world, x, y, z);
    }

}
