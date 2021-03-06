package amidst.minetest.world.mapgen;

import javax.vecmath.Vector3f;

import amidst.mojangapi.world.WorldType;
import amidst.mojangapi.world.biome.BiomeColor;

// TODO: be able to import these values from map_meta.txt files
public class MapgenV7Params extends MapgenParams {

	public static final int FLAG_V7_MOUNTAINS   = 0x01;
	public static final int FLAG_V7_RIDGES      = 0x02;
	public static final int FLAG_V7_FLOATLANDS  = 0x04;
	public static final int FLAG_V7_CAVERNS     = 0x08;
	public static final int FLAG_V7_BIOMEREPEAT = 0x10; // Now unused
		
	/*
	private short mount_zero_level;
	private short large_cave_depth;
	private float float_mount_density;
	private float float_mount_height;
	private float float_mount_exponent;
	private short floatland_level;
	private short shadow_limit;
	private short dungeon_ymin;
	private short dungeon_ymax;

	private Noise noise_terrain_alt;
	private Noise noise_terrain_persist;
	private Noise noise_height_select;
	private Noise noise_mount_height;
	private Noise noise_ridge_uwater;
	private Noise noise_floatland_base;
	private Noise noise_float_base_height;
	private Noise noise_mountain;
	private Noise noise_ridge;
	*/
		
	public int spflags = FLAG_V7_MOUNTAINS | FLAG_V7_RIDGES | FLAG_V7_CAVERNS;
	short mount_zero_level     = 0;
	float cave_width           = 0.09f;
	short large_cave_depth     = -33;
	short lava_depth           = -256;
	float float_mount_density  = 0.6f;
	public float float_mount_height   = 128.0f;
	float float_mount_exponent = 0.75f;
	short floatland_level      = 1280;
	short shadow_limit         = 1024;
	short cavern_limit         = -256;
	short cavern_taper         = 256;
	float cavern_threshold     = 0.7f;
	short dungeon_ymin         = -31000;
	short dungeon_ymax         = 31000;
	
	// params that aren't public aren't being used by Amidst
	public NoiseParams np_terrain_base      = new NoiseParams(4,     70,   new Vector3f(600,  600,  600),  82341, (short)5, 0.6f,  2.0f);
	public NoiseParams np_terrain_alt       = new NoiseParams(4,     25,   new Vector3f(600,  600,  600),  5934,  (short)5, 0.6f,  2.0f);
	public NoiseParams np_terrain_persist   = new NoiseParams(0.6f,  0.1f, new Vector3f(2000, 2000, 2000), 539,   (short)3, 0.6f,  2.0f);
	public NoiseParams np_height_select     = new NoiseParams(-8,    16,   new Vector3f(500,  500,  500),  4213,  (short)6, 0.7f,  2.0f);
           NoiseParams np_filler_depth      = new NoiseParams(0,     1.2f, new Vector3f(150,  150,  150),  261,   (short)3, 0.7f,  2.0f);
	public NoiseParams np_mount_height      = new NoiseParams(256f,  112f, new Vector3f(1000, 1000, 1000), 72449, (short)3, 0.6f,  2.0f);
	public NoiseParams np_ridge_uwater      = new NoiseParams(0,     1,    new Vector3f(1000, 1000, 1000), 85039, (short)5, 0.6f,  2.0f);
	public NoiseParams np_floatland_base    = new NoiseParams(-0.6f, 1.5f, new Vector3f(600,  600,  600),  114,   (short)5, 0.6f,  2.0f);
	public NoiseParams np_float_base_height = new NoiseParams(48,    24,   new Vector3f(300,  300,  300),  907,   (short)4, 0.7f,  2.0f);
	public NoiseParams np_mountain          = new NoiseParams(-0.6f, 1,    new Vector3f(250,  350,  250),  5333,  (short)5, 0.63f, 2.0f);
	public NoiseParams np_ridge             = new NoiseParams(0,     1,    new Vector3f(100,  100,  100),  6467,  (short)4, 0.75f, 2.0f);
	       NoiseParams np_cavern            = new NoiseParams(0,     1,    new Vector3f(384,  128,  384),  723,   (short)5, 0.63f, 2.0f);
	       NoiseParams np_cave1             = new NoiseParams(0,     12,   new Vector3f(61,   61,   61),   52534, (short)3, 0.5f,  2.0f);
	       NoiseParams np_cave2             = new NoiseParams(0,     12,   new Vector3f(67,   67,   67),   10325, (short)3, 0.5f,  2.0f);
	       
	       
   	@Override
   	public String toString() {   		
   		String prefix = "mgv7_";
		StringBuilder result = new StringBuilder();
		result.append("mg_flags    = "); 
		appendFlags(result, spflags, new String[] {"mountains", "ridges", "floatlands", "caverns", "biomerepeat"}); 
		result.append("\r\n");
		result.append(super.toString());
		result.append(np_terrain_base.toString(   prefix + "np_terrain_base"));
		result.append(np_terrain_alt.toString(    prefix + "np_terrain_alt"));
		result.append(np_terrain_persist.toString(prefix + "np_terrain_persist"));
		result.append(np_height_select.toString(  prefix + "np_height_select"));
		result.append(np_mount_height.toString(   prefix + "np_mount_height"));
		result.append(np_mountain.toString(       prefix + "np_terrain_persist"));
		result.append(np_ridge_uwater.toString(   prefix + "np_ridge_uwater"));
        return result.toString();		
   	}
	       
   	@Override
	public WorldType getWorldType() {
		return WorldType.V7;
	}		       
}
