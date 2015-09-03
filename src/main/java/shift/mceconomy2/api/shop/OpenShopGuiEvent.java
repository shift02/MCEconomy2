package shift.mceconomy2.api.shop;

import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;

public class OpenShopGuiEvent extends InitGuiEvent{

	public GuiContainer gui;
	public int shopID;

	public OpenShopGuiEvent(GuiContainer gui , List buttonList, int shopID) {
		super(gui, buttonList);
		this.gui = gui;
		this.shopID = shopID;
	}

}
