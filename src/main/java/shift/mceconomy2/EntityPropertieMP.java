package shift.mceconomy2;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class EntityPropertieMP implements IExtendedEntityProperties {

    private int mp=0;
    private int mpDisplay ;
    private int mpCount;
    //private boolean init= false;

    @Override
    public void saveNBTData(NBTTagCompound compound) {


     compound.setInteger("mp", getMp());
     //compound.setInteger("mpd", getMpDisplay());
     //compound.setInteger("mpc", mpCount);
     //compound.setBoolean("init", init);

    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {

    	if(compound.hasKey("mp")){

    		if(compound.getBoolean("init")){
    			this.setMpDisplay(0);
    			this.mpCount = 0;
    		}else{

    			int addMP = compound.getInteger("mp");
    			mpDisplay = addMP - mp + mpDisplay;
    			this.mpCount = 20;

    		}

    		setMp(compound.getInteger("mp"));

    	}

    }

    @Override
    public void init(Entity entity, World world) {
        // TODO 自動生成されたメソッド・スタブ

    }

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getMpDisplay() {
		return mpDisplay;
	}

	public void setMpDisplay(int mpDisplay) {
		this.mpDisplay = mpDisplay;
	}

	public int getMpCount() {
		return mpCount;
	}

	public void setMpCount(int mpCount) {
		this.mpCount = mpCount;
	}


}
