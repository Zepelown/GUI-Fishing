package io.github.zepelown.guifish.gamedata;

import com.google.common.base.Preconditions;

import java.util.Random;

public class FirstGameData implements Debuggable {

	private static final Random random = new Random();

	private int difficulty = random.nextInt(3) + 1;
	private int endCount = 0;
	private Direction direction = Direction.RIGHT;

	public int getDifficulty() {
		return difficulty;
	}

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
		System.out.println("--------------");
		System.out.println("difficulty: " + difficulty);
		System.out.println("endCount: " + endCount);
		System.out.println("direction: " + direction.name());
		System.out.println("--------------");
	}

	public enum Direction {
		LEFT, RIGHT
	}

}
