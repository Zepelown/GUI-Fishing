package io.github.zepelown.guifish.gamedata;

import com.google.common.base.Preconditions;

public class FirstGameData implements Debuggable {

	private int endCount = 0;
	private Direction direction = Direction.RIGHT;

	public int getEndCount() {
		return endCount;
	}

	public void addEndCount() {
		endCount++;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = Preconditions.checkNotNull(direction);
	}

	@Override
	public void printDebug() {

	}

	public enum Direction {
		LEFT, RIGHT
	}

}
