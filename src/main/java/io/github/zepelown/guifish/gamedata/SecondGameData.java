package io.github.zepelown.guifish.gamedata;

import org.bukkit.Material;

import java.util.LinkedList;
import java.util.Random;

public class SecondGameData implements Debuggable {

	private static final Random random = new Random();
	private static final Material[] usedMaterials = { Material.GLASS, Material.GRASS, Material.STONE, Material.OAK_LOG, Material.BEDROCK };

	private final LinkedList<Material> materials = new LinkedList<Material>() {
		{
			for (int i = 0; i < 7; i++) {
				add(usedMaterials[random.nextInt(usedMaterials.length)]);
			}
		}
	};

	public boolean removeIfCorrect(Material type) {
		if (materials.size() > 0 && materials.getFirst() == type) {
			materials.removeFirst();
			return true;
		}
		return false;
	}

	@Override
	public void printDebug() {

	}

}
