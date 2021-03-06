package net.nabaal.majiir.realtimerender.rendering;

import net.nabaal.majiir.realtimerender.Coordinate;
import net.nabaal.majiir.realtimerender.image.ChunkRenderer;

import org.bukkit.ChunkSnapshot;

public class HeightMapRenderer implements ChunkRenderer {

	private final HeightMap heightMap;
	
	public HeightMapRenderer(HeightMap heightMap) {
		this.heightMap = heightMap;
	}
	
	@Override
	public void render(ChunkSnapshot snapshot) {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				heightMap.setHeight(
					Coordinate
					.fromSnapshot(snapshot)
					.zoomIn(Coordinate.OFFSET_BLOCK_CHUNK)
					.plus(
						new Coordinate(x, z, Coordinate.LEVEL_BLOCK)
					),
					
					TerrainHelper.getTerrainHeight(x, z, snapshot)
				);
			}
		}
	}

}
