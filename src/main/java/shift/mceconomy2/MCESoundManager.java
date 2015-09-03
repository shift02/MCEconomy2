package shift.mceconomy2;

import net.minecraft.world.World;
import shift.mceconomy2.api.ISoundManager;

public class MCESoundManager implements ISoundManager {

	@Override
	public void playCoinSoundEffect(World world, int x, int y, int z) {

		world.playSoundEffect(x, y, z, COIN_SOUND, 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);

	}

}
