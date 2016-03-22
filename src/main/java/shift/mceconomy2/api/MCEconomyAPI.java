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
     * addPurchaseItem Mobなどがアイテムを買い取る価格を登録
     * @param PurchaseItem 買い取りアイテム
     * @param amount 価格  -1で非売品に設定出来ます
     */
    public static void addPurchaseItem(ItemStack PurchaseItem, int amount) {
        ShopManager.addPurchaseItem(PurchaseItem, amount);
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
     * @param PurchaseItem 買い取る流体
     * @param amount 価格(1mB)  -1で非売品に設定出来ます
     */
    public static void addPurchaseFluid(Fluid fluid, double mp) {
        ShopManager.addPurchaseFluid(fluid, mp);
    }

    /**
     * getPurchase 流体の買い取り額を取得
     * @param item 売る流体
     * @return 価格(1mB)
     */
    public static double getFluidPurchase(Fluid fluid) {
        return ShopManager.getFluidPurchase(fluid);
    }

    /**
     * hasPurchase 流体に価格が設定されているか
     * @param item 調べる流体
     * @return 設定されていればtrue 非売品はfalseになります.
     */
    public static boolean hasFluidPurchase(Fluid fluid) {
        return ShopManager.hasFluidPurchase(fluid);
    }

    /**
     * registerPurchaseItem API読み込み時にバニラアイテムの価格が自動で設定される。
     * 時間がかかりそうなので手をつけてない。
     * 少し手をつけた
     */
    public static void registerPurchaseItem() {

        //MCEconomyAPI.ShopManager = new ShopManager();

        //粘土 30
        //レンガ 1個 50
        //石レンガ 10
        //本 150
        //石炭 100
        //レッドストーン 40
        //エンダーアイ 40
        //水晶 300
        //小麦 50

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
        addPurchaseItem(new ItemStack(Blocks.glowstone), 1300);
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
        addPurchaseItem(new ItemStack(Blocks.quartz_block), 1200);
        addPurchaseItem(new ItemStack(Blocks.quartz_stairs), 1400);
        for (int i = 0; i < 16; i++) {
            addPurchaseItem(new ItemStack(Blocks.stained_hardened_clay, 1, i), 130);
        }
        addPurchaseItem(new ItemStack(Blocks.hay_block), 450);
        addPurchaseItem(new ItemStack(Blocks.hardened_clay), 130);
        addPurchaseItem(new ItemStack(Blocks.coal_block), 900);
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
        addPurchaseItem(new ItemStack(Blocks.torch), 30);
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
        addPurchaseItem(new ItemStack(Blocks.anvil, 1, 1), -1);//壊れた
        addPurchaseItem(new ItemStack(Blocks.anvil, 1, 2), -1);
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
            addPurchaseItem(new ItemStack(Blocks.stained_glass_pane), 0);
        }
        //装飾 Item

        //レッドストーン
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

        //移動
        addPurchaseItem(new ItemStack(Blocks.golden_rail), 4100);
        addPurchaseItem(new ItemStack(Blocks.detector_rail), 600);
        addPurchaseItem(new ItemStack(Blocks.rail), 250);
        addPurchaseItem(new ItemStack(Blocks.activator_rail), 600);

        //その他
        addPurchaseItem(new ItemStack(Blocks.beacon), 9000);

        //食べ物
        addPurchaseItem(new ItemStack(Blocks.cake), -1);//ブロックのケーキ
        addPurchaseItem(new ItemStack(Blocks.cocoa), -1);//ブロック状態のカカオ
        addPurchaseItem(new ItemStack(Blocks.carrots), -1);//にんじん
        addPurchaseItem(new ItemStack(Blocks.potatoes), -1);//じゃがいも

        //ポーション
        addPurchaseItem(new ItemStack(Blocks.brewing_stand), -1);//ブロック状態のスタンド
        addPurchaseItem(new ItemStack(Blocks.cauldron), -1);//ブロック状態の釜

        //レバーで終了

        //木こり使用時はただ
        //        int k = 1;
        //        if (Loader.isModLoaded("net.minecraft.scalar.cutall.mod_CutAllSMP")) {
        //            k = 0;
        //        }
        //
        //        addPurchaseItem(new ItemStack(Blocks.stone), 0);
        //        addPurchaseItem(new ItemStack(Blocks.grass), 2);
        //        addPurchaseItem(new ItemStack(Blocks.dirt), 0);
        //        addPurchaseItem(new ItemStack(Blocks.dirt, 1, 2), 4);
        //        addPurchaseItem(new ItemStack(Blocks.cobblestone), 0);
        //        addPurchaseItem(new ItemStack(Blocks.planks, 1, 0), 0);
        //        addPurchaseItem(new ItemStack(Blocks.planks, 1, 1), 0);
        //        addPurchaseItem(new ItemStack(Blocks.planks, 1, 2), 0);
        //        addPurchaseItem(new ItemStack(Blocks.planks, 1, 3), 0);
        //        addPurchaseItem(new ItemStack(Blocks.sapling, 1, 32767), 0);
        //        addPurchaseItem(new ItemStack(Blocks.bedrock), 1000);
        //        addPurchaseItem(new ItemStack(Blocks.flowing_water), -1);
        //        addPurchaseItem(new ItemStack(Blocks.water), -1);
        //        addPurchaseItem(new ItemStack(Blocks.flowing_lava), -1);
        //        addPurchaseItem(new ItemStack(Blocks.lava), -1);
        //        addPurchaseItem(new ItemStack(Blocks.sand, 1, OreDictionary.WILDCARD_VALUE), 0);
        //        addPurchaseItem(new ItemStack(Blocks.gravel), 1);
        //        addPurchaseItem(new ItemStack(Blocks.gold_ore), 100);
        //        addPurchaseItem(new ItemStack(Blocks.iron_ore), 20);
        //        addPurchaseItem(new ItemStack(Blocks.coal_ore), 10);
        //        for (int i = 0; i < 6; i++) {
        //            addPurchaseItem(new ItemStack(Blocks.planks, 1, i), 2 * k);
        //        }
        //        addPurchaseItem(new ItemStack(Blocks.wooden_pressure_plate, 1, 0), 2 * k);
        //        addPurchaseItem(new ItemStack(Blocks.log, 1, 0), 2 * k);
        //        addPurchaseItem(new ItemStack(Blocks.log, 1, 1), 2 * k);
        //        addPurchaseItem(new ItemStack(Blocks.log, 1, 2), 2 * k);
        //        addPurchaseItem(new ItemStack(Blocks.log, 1, 3), 2 * k);
        //        addPurchaseItem(new ItemStack(Blocks.log2, 1, 0), 3 * k);//アカシア、ダークオークはわずかに高値
        //        addPurchaseItem(new ItemStack(Blocks.log2, 1, 1), 3 * k);
        //        addPurchaseItem(new ItemStack(Blocks.leaves, 1, 32767), 0);
        //        addPurchaseItem(new ItemStack(Blocks.sponge), -1);
        //        addPurchaseItem(new ItemStack(Blocks.glass), 3);
        //
        //        addPurchaseItem(new ItemStack(Blocks.lapis_ore), 80);
        //        addPurchaseItem(new ItemStack(Blocks.lapis_block), 20);
        //        addPurchaseItem(new ItemStack(Blocks.dispenser), -1);
        //        addPurchaseItem(new ItemStack(Blocks.sandstone, OreDictionary.WILDCARD_VALUE), 1);
        //        addPurchaseItem(new ItemStack(Blocks.noteblock), 50);
        //        addPurchaseItem(new ItemStack(Blocks.bed), 10);
        //        addPurchaseItem(new ItemStack(Blocks.golden_rail), 24);
        //        addPurchaseItem(new ItemStack(Blocks.detector_rail), 12);
        //        addPurchaseItem(new ItemStack(Blocks.sticky_piston), 10);
        //        addPurchaseItem(new ItemStack(Blocks.web), 40);
        //        addPurchaseItem(new ItemStack(Blocks.tallgrass), 2);
        //        addPurchaseItem(new ItemStack(Blocks.deadbush, 1, 32767), 3);
        //        addPurchaseItem(new ItemStack(Blocks.piston), 6);
        //        addPurchaseItem(new ItemStack(Blocks.piston_head), -1);
        //
        //        addPurchaseItem(new ItemStack(Blocks.wool, 1, 32767), 20);
        //
        //        addPurchaseItem(new ItemStack(Blocks.piston_extension), -1);
        //        addPurchaseItem(new ItemStack(Blocks.yellow_flower), 1);
        //        addPurchaseItem(new ItemStack(Blocks.red_flower, 1, 32767), 1);
        //        addPurchaseItem(new ItemStack(Blocks.brown_mushroom), 8);
        //        addPurchaseItem(new ItemStack(Blocks.red_mushroom), 7);
        //        addPurchaseItem(new ItemStack(Blocks.gold_block), 1800);
        //        addPurchaseItem(new ItemStack(Blocks.iron_block), 450);
        //        //疲れた(´・ω・｀)
        //
        //        addPurchaseItem(new ItemStack(Blocks.double_stone_slab), -1);
        //        addPurchaseItem(new ItemStack(Blocks.stone_slab), 0);
        //        addPurchaseItem(new ItemStack(Blocks.brick_block), 16);
        //        addPurchaseItem(new ItemStack(Blocks.tnt), 18);
        //        addPurchaseItem(new ItemStack(Blocks.bookshelf), 12);
        //        addPurchaseItem(new ItemStack(Blocks.mossy_cobblestone), 4);
        //        addPurchaseItem(new ItemStack(Blocks.obsidian), 8);
        //        addPurchaseItem(new ItemStack(Blocks.torch), 1);
        //        addPurchaseItem(new ItemStack(Blocks.fire), -1);
        //        addPurchaseItem(new ItemStack(Blocks.mob_spawner), 999);
        //        addPurchaseItem(new ItemStack(Blocks.oak_stairs), 3);
        //        addPurchaseItem(new ItemStack(Blocks.birch_stairs), 3);
        //        addPurchaseItem(new ItemStack(Blocks.jungle_stairs), 3);
        //        addPurchaseItem(new ItemStack(Blocks.spruce_stairs), 3);
        //        addPurchaseItem(new ItemStack(Blocks.dark_oak_stairs), 3);
        //        addPurchaseItem(new ItemStack(Blocks.acacia_stairs), 3);
        //
        //        addPurchaseItem(new ItemStack(Blocks.chest), 6);
        //        addPurchaseItem(new ItemStack(Blocks.redstone_wire), -1);
        //        addPurchaseItem(new ItemStack(Blocks.diamond_ore), 2000);
        //        addPurchaseItem(new ItemStack(Blocks.diamond_block), 9000);
        //        addPurchaseItem(new ItemStack(Blocks.crafting_table), -1);
        //        addPurchaseItem(new ItemStack(Blocks.wheat), -1);
        //        addPurchaseItem(new ItemStack(Blocks.carrots), -1);
        //        addPurchaseItem(new ItemStack(Blocks.potatoes), -1);
        //        addPurchaseItem(new ItemStack(Blocks.farmland), -1);
        //        addPurchaseItem(new ItemStack(Blocks.furnace), -1);
        //        addPurchaseItem(new ItemStack(Blocks.lit_furnace), -1);
        //        addPurchaseItem(new ItemStack(Blocks.wooden_door), -1);
        //        addPurchaseItem(new ItemStack(Blocks.ladder), -1);
        //        addPurchaseItem(new ItemStack(Blocks.rail), 10);
        //        addPurchaseItem(new ItemStack(Blocks.stone_stairs), 0);
        //        addPurchaseItem(new ItemStack(Blocks.lever), -1);
        //        addPurchaseItem(new ItemStack(Blocks.stone_pressure_plate), 0);
        //        addPurchaseItem(new ItemStack(Blocks.iron_door), 0);
        //        addPurchaseItem(new ItemStack(Blocks.redstone_ore), 100);
        //        addPurchaseItem(new ItemStack(Blocks.lit_redstone_ore), -1);
        //        addPurchaseItem(new ItemStack(Blocks.redstone_torch), -1);
        //        addPurchaseItem(new ItemStack(Blocks.stone_button), -1);
        //        addPurchaseItem(new ItemStack(Blocks.snow), 2);
        //        addPurchaseItem(new ItemStack(Blocks.ice), 6);
        //        addPurchaseItem(new ItemStack(Blocks.snow_layer), 0);
        //        addPurchaseItem(new ItemStack(Blocks.cactus), 1);
        //        addPurchaseItem(new ItemStack(Blocks.clay), 42);
        //        addPurchaseItem(new ItemStack(Blocks.reeds), -1);
        //        addPurchaseItem(new ItemStack(Blocks.jukebox), -1);
        //        addPurchaseItem(new ItemStack(Blocks.fence), 2);
        //        addPurchaseItem(new ItemStack(Blocks.pumpkin), 3);
        //        addPurchaseItem(new ItemStack(Blocks.netherrack), -1);
        //        addPurchaseItem(new ItemStack(Blocks.soul_sand), 7);
        //        addPurchaseItem(new ItemStack(Blocks.glowstone), 21);

        addPurchaseItem(new ItemStack(Items.iron_ingot), 500);
        addPurchaseItem(new ItemStack(Items.gold_ingot), 4000);
        addPurchaseItem(new ItemStack(Items.diamond), 10000);

        /**Foods and crops: 基本的に無限資源はタダ、調理するとわずかに値打ちが出る*/
        //金のりんご
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.golden_apple, 1, 0), 180);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.golden_apple, 1, 1), 1600);

        //生食材
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.apple, 1, 0), 20);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.beef, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.carrot, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.chicken, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.fish, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.fish, 1, 1), 2);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.fish, 1, 2), 10);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.fish, 1, 3), 5);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.melon, 1, 0), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.porkchop, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.potato, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.reeds, 1, 0), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.wheat_seeds, 1, 0), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.melon_seeds, 1, 0), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.pumpkin_seeds, 1, 0), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.wheat, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.sugar, 1, 0), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.poisonous_potato, 1, 0), 0);

        //調理済み
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.baked_potato, 1, 0), 2);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.cooked_beef, 1, 0), 3);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.mushroom_stew, 1, 0), 100);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.bread, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.cake, 1, 0), 5);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.cooked_chicken, 1, 0), 2);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.cookie, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.cooked_fished, 1, 0), 2);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.cooked_fished, 1, 1), 3);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.cooked_porkchop, 1, 0), 2);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.pumpkin_pie, 1, 0), 2);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.golden_carrot, 1, 0), 180);

        /**Materials: ものによる*/
        //素材
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.emerald, 1, 0), 15);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.flint, 1, 0), 3);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.quartz, 1, 0), 35);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.gold_nugget, 1, 0), 20);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.redstone, 1, 0), 12);

        //misc
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.brick, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.clay_ball, 1, 0), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.coal, 1, 0), 5);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.coal, 1, 1), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.egg, 1, 0), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.feather, 1, 0), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.fermented_spider_eye, 1, 0), 3);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.fire_charge, 1, 0), 5);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.glowstone_dust, 1, 0), 25);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.leather, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.netherbrick, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.nether_wart, 1, 0), 7);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.paper, 1, 0), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.snowball, 1, 0), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.speckled_melon, 1, 0), 90);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.stick, 1, 0), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.potionitem, 1, 32767), 10);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.potionitem, 1, 0), 0);

        for (int i = 0; i < 16; i++) {
            if (i == 5) {
                MCEconomyAPI.addPurchaseItem(new ItemStack(Items.dye, 1, 5), 3);
            } else {
                MCEconomyAPI.addPurchaseItem(new ItemStack(Items.dye, 1, i), 1);
            }
        }

        //モブドロップ
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.blaze_powder, 1, 0), 3);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.blaze_rod, 1, 0), 10);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.bone, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.rotten_flesh, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.ender_pearl, 1, 0), 15);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.ender_eye, 1, 0), 40);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.ghast_tear, 1, 0), 120);//俗にいう金策モブ
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.gunpowder, 1, 0), 4);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.magma_cream, 1, 0), 5);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.string, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.slime_ball, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.spider_eye, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.nether_star, 1, 0), 3000);//やすい。

        /**Tools: 素材の額とだいたい等価か、少し安い*/
        //ツール
        //新品でないと売れない
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.arrow, 1, 0), 2);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.bow, 1, 0), 5);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.diamond_axe, 1, 0), 1500);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.golden_axe, 1, 0), 300);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.iron_axe, 1, 0), 150);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.stone_axe, 1, 0), 15);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.wooden_axe, 1, 0), 5);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.diamond_hoe, 1, 0), 1000);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.golden_hoe, 1, 0), 200);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.iron_hoe, 1, 0), 100);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.stone_hoe, 1, 0), 10);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.wooden_hoe, 1, 0), 5);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.diamond_pickaxe, 1, 0), 1800);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.golden_pickaxe, 1, 0), 350);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.iron_pickaxe, 1, 0), 150);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.stone_pickaxe, 1, 0), 15);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.wooden_pickaxe, 1, 0), 5);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.diamond_shovel, 1, 0), 500);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.golden_shovel, 1, 0), 100);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.iron_shovel, 1, 0), 50);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.stone_shovel, 1, 0), 5);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.wooden_shovel, 1, 0), 3);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.diamond_sword, 1, 0), 1000);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.golden_sword, 1, 0), 200);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.iron_sword, 1, 0), 100);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.stone_sword, 1, 0), 10);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.wooden_sword, 1, 0), 5);

        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.book, 1, 0), 3);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.bowl, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.bucket, 1, 0), 150);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.lava_bucket, 1, 0), 200);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.milk_bucket, 1, 0), 160);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.water_bucket, 1, 0), 150);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.carrot_on_a_stick, 1, 0), 8);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.compass, 1, 0), 200);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.experience_bottle, 1, 0), 50);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.flint_and_steel, 1, 0), 55);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.flower_pot, 1, 0), 10);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.glass_bottle, 1, 0), 2);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.lead, 1, 0), 5);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.map, 1, 0), 300);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.name_tag, 1, 0), 120);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.clock, 1, 0), 800);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.saddle, 1, 0), 20);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.written_book, 1, 0), 5);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.writable_book, 1, 0), 5);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.shears, 1, 0), 100);

        //のりもの
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.minecart, 1, 0), 450);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.boat, 1, 0), 10);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.hopper_minecart, 1, 0), 800);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.furnace_minecart, 1, 0), 500);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.tnt_minecart, 1, 0), 530);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.chest_minecart, 1, 0), 500);

        //設置物
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.bed, 1, 0), 10);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.brewing_stand, 1, 0), 20);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.cauldron, 1, 0), 350);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.comparator, 1, 0), 100);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.repeater, 1, 0), 25);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.iron_door, 1, 0), 250);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.wooden_door, 1, 0), 8);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.item_frame, 1, 0), 8);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.painting, 1, 0), 12);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.sign, 1, 0), 6);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.skull, 1, OreDictionary.WILDCARD_VALUE), 25);

        //防具
        //金とダイアは価値が半減する
        //ダメージ値ゼロ指定なので、たぶん新品以外は売れないはず
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.chainmail_boots, 1, 0), 80);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.diamond_boots, 1, 0), 2000);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.golden_boots, 1, 0), 400);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.iron_boots, 1, 0), 200);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.leather_boots, 1, 0), 20);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.chainmail_helmet, 1, 0), 100);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.diamond_helmet, 1, 0), 2500);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.golden_helmet, 1, 0), 500);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.iron_helmet, 1, 0), 250);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.leather_helmet, 1, 0), 30);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.chainmail_chestplate, 1, 0), 160);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.diamond_chestplate, 1, 0), 4000);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.golden_chestplate, 1, 0), 800);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.iron_chestplate, 1, 0), 400);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.leather_chestplate, 1, 0), 50);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.chainmail_leggings, 1, 0), 140);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.diamond_leggings, 1, 0), 3500);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.golden_leggings, 1, 0), 700);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.iron_leggings, 1, 0), 350);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.leather_leggings, 1, 0), 35);

        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.diamond_horse_armor, 1, 0), 2000);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.golden_horse_armor, 1, 0), 400);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.iron_horse_armor, 1, 0), 200);

        /**Blocks: API本体の傾向に似せる*/
        //ore

        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.emerald_ore, 1, 0), 400);//エメラルドは鉱石だと金の二倍。
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.diamond_ore, 1, 0), 1500);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.quartz_ore, 1, 0), 140);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.redstone_ore, 1, 0), 60);

        //Mineral block
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.quartz_block, 1, 0), 140);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.quartz_block, 1, 1), 150);//加工してるとちょっと高い
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.quartz_block, 1, 2), 150);//〃
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.quartz_stairs, 1, 0), 180);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.diamond_block, 1, 0), 9000);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.emerald_block, 1, 0), 135);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.redstone_block, 1, 0), 108);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.coal_block, 1, 0), 45);

        //地形
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.stonebrick), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.cactus, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.vine, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.waterlily, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.hardened_clay, 1, 0), 50);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.stained_hardened_clay, 1, 32767), 3);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.packed_ice, 1, 0), 5);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.netherrack, 1, 0), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.nether_brick, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.nether_brick_stairs, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.nether_brick_fence, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.end_stone, 1, 0), 15);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.end_stone, 1, 0), 15);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.obsidian, 1, 0), 12);

        //人工物系の地形
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.bookshelf, 1, 0), 10);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.cobblestone_wall, 1, OreDictionary.WILDCARD_VALUE), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.fence, 1, 0), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.fence_gate, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.iron_bars, 1, 0), 18);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.hay_block, 1, 0), 3);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.pumpkin, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.lit_pumpkin, 1, 0), 2);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.melon_block, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.glass_pane, 1, 0), 1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.tnt, 1, 0), 10);

        //設置物
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.stone_slab, 1, OreDictionary.WILDCARD_VALUE), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.wooden_slab, 1, OreDictionary.WILDCARD_VALUE), 2);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.anvil, 1, 0), 1550);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.carpet, 1, 0), 3);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.trapped_chest, 1, 0), 50);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.dispenser, 1, 0), 20);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.dropper, 1, 0), 8);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.daylight_detector, 1, 0), 110);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.enchanting_table, 1, 0), 1200);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.ender_chest, 1, 0), 150);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.end_portal, 1, 0), -1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.end_portal_frame, 1, 0), -1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.fire, 1, 0), -1);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.hopper, 1, 0), 250);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.ladder, 1, 0), 3);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.lever, 1, 0), 3);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.heavy_weighted_pressure_plate, 1, 0), 350);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.light_weighted_pressure_plate, 1, 0), 100);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.redstone_lamp, 1, 0), 120);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.tripwire_hook, 1, 0), 60);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.stone_button, 1, 0), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.wooden_button, 1, 0), 0);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.torch, 1, 0), 2);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.trapdoor, 1, 0), 2);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.stained_glass, 1, OreDictionary.WILDCARD_VALUE), 3);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.stained_glass_pane, 1, OreDictionary.WILDCARD_VALUE), 2);
        MCEconomyAPI.addPurchaseItem(new ItemStack(Blocks.beacon, 1, 0), -1);

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
