package io.github.zepelown.guifish.gamedata;

public class DataContainer implements Debuggable {

	private FirstGameData firstGameData = null;
	private SecondGameData secondGameData = null;

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

	}

}
