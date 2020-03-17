package io.github.zepelown.guifish.gamedata;

import org.bukkit.inventory.ItemStack;

public class DataContainer implements Debuggable {

	private final ItemStack caught;
	private FirstGameData firstGameData = null;
	private SecondGameData secondGameData = null;

	public DataContainer(ItemStack caught) {
		this.caught = caught;
	}

	public ItemStack getCaught() {
		return caught;
	}

	public FirstGameData getFirstGameData() {
		if (firstGameData == null) this.firstGameData = new FirstGameData();
		return firstGameData;
	}

	public SecondGameData getSecondGameData() {
		if (secondGameData == null) this.secondGameData = new SecondGameData();
		return secondGameData;
	}

	@Override
	public void printDebug() {
		if (firstGameData != null) {
			firstGameData.printDebug();
		}
		if (secondGameData != null) {
			secondGameData.printDebug();
		}
	}

}
