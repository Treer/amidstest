package amidst.fragment.colorprovider;

import amidst.documentation.ThreadSafe;
import amidst.fragment.Fragment;
import amidst.mojangapi.world.Dimension;
import amidst.minetest.world.oracle.BiomeDataOracle;

@ThreadSafe
public class MinetestOceanColorProvider implements ColorProvider {
	private static final int OCEAN_COLOR     = 0xD8000555; // 0xAARRGGBB
	private static final int NOT_OCEAN_COLOR = 0x00000000; // 0xAARRGGBB

	@Override
	public int getColorAt(Dimension dimension, Fragment fragment, long cornerX, long cornerY, int x, int y) {
		
		if ((fragment.getBiomeDataAt(x, y) & BiomeDataOracle.BITPLANE_OCEAN) > 0) {
			return OCEAN_COLOR;
		} else {
			return NOT_OCEAN_COLOR;
		}
	}
}
