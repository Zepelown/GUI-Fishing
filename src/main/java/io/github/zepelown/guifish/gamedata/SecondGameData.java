package io.github.zepelown.guifish.gamedata;

import org.bukkit.Material;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

public class SecondGameData implements Debuggable {

	private static final Random random = new Random();
	private static final Material[] usedMaterials = { Material.GLASS, Material.GRASS_BLOCK, Material.STONE, Material.OAK_LOG, Material.BEDROCK };

	SecondGameData() {}

	private final LinkedList<Material> materials = new LinkedList<Material>() {
		{
			for (int i = 0; i < 7; i++) {
				add(usedMaterials[random.nextInt(usedMaterials.length)]);
			}
		}
	};

	public boolean hasRemovedAll() {
		return materials.size() == 0;
	}

	public int getRemovedCount() {
		return 7 - materials.size();
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public boolean removeIfCorrect(Material type) {
		if (materials.size() > 0 && materials.getFirst() == type) {
			materials.removeFirst();
			return true;
		}
		return false;
	}

	@Override
	public void printDebug() {
		System.out.println("--------------");
		StringJoiner joiner = new StringJoiner(" | ");
		for (Material type : materials) joiner.add(type.name());
		System.out.println("materials: " + joiner.toString());
		System.out.println("--------------");
	}

}
