package shift.mceconomy2.api.shop;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;

/**
 * MOD別にショップを管理します。<br>
 * MODIDごとにショップリストが生成されます。
 * @author kegare
 */
public final class ShopAPI {

    public static IShopAPI apiHandler;

    /**
     * ショップを登録します。
     * @param modid 登録するMODのID
     * @param shop 登録するショップ
     * @return MOD別のショップIDを返します。登録に失敗した場合は、-1を返します。
     */
    public static int registerShop(String modid, IShop shop) {

        if (apiHandler != null) {

            return apiHandler.registerShop(modid, shop);
        }

        return -1;
    }

    /**
     * ショップを取得します。
     * @param modid ショップのMODID
     * @param id ショップID
     * @return ショップが取得できなかった場合は、nullを返します。
     */
    public static IShop getShop(String modid, int id) {

        if (apiHandler != null) {

            return apiHandler.getShop(modid, id);
        }

        return null;
    }

    /**
     * ショップリストを取得します。
     * @return ショップリストが取得できなかった場合は、不変の空のリストを返します。
     */
    public static List<IShop> getShops(String modid) {

        if (apiHandler != null) {

            return apiHandler.getShops(modid);
        }

        return Collections.emptyList();
    }

    /**
     * ショップリストのエントリマップを取得します。<br>
     * マップのキーにはMODID、値には対応するショップリストが格納されています。
     */
    public static Map<String, List<IShop>> getShopEntries() {

        if (apiHandler != null) {

            return apiHandler.getShopEntries();
        }

        return Collections.emptyMap();
    }

    /**
     * ショップを開きます。<br>
     * クライアントからでもサーバーからでも呼び出すことができます。<br>
     * 片側のみで呼び出すようにしてください。
     * @param player プレイヤー
     * @param modid ショップのMODID
     * @param id ショップID
     */
    public static void openShop(EntityPlayer player, String modid, int id) {

        if (apiHandler != null) {

            apiHandler.openShop(player, modid, id);
        }
    }
}