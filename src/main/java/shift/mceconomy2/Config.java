package shift.mceconomy2;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

/**
 * @author Shift02
 */
public class Config {

    public static boolean HUD;

    public static int maxMP;

    public static boolean sound;

    public static void ConfigRead(FMLPreInitializationEvent event) {

        Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());

        try {

            cfg.load();

            ConfigForMCEconomy(cfg);

        } catch (Exception e) {
        } finally {
            cfg.save();
        }

    }

    public static void ConfigForMCEconomy(Configuration cfg) {

        //maxMP = cfg.getInt("Player_MP_MAX", "general", 1000000, 0, 9999999, "");
        maxMP = cfg.getInt("Player_MP_MAX", "general", 100000000, 0, 999999999, "");

        HUD = cfg.getBoolean("MP", "general", true, "");

        sound = cfg.getBoolean("Sound", "general", true, "");

    }

}
