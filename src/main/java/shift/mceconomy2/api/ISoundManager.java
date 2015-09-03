package shift.mceconomy2.api;

import net.minecraft.world.World;

/**
 * MCEの音楽を管理するClass
 * @since 1.0.0
 * @version 1.0.0
 * @author Shift02
 */
public interface ISoundManager {

	public static final String COIN_SOUND = "mceconomy2:coin";

	/**
	 * playCoinSoundEffect コインを取得した時の音をならす
	 * @param world ワールド
	 * @param x x軸
	 * @param y y軸
	 * @param z z軸
	 */
	public void playCoinSoundEffect(World world, int x, int y, int z);

}
