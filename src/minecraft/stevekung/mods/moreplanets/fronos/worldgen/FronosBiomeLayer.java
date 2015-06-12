/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.worldgen;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

public abstract class FronosBiomeLayer extends GenLayer
{
	public static GenLayer[] initializeAllBiomeGenerators(long seed, int dim)
	{
		int biomesize = 3;

		FronosBiomeLayer obj = new FronosBiomeLayerCreate(1L, false);
		obj = new FronosBiomeLayerFuzzyZoom(2000L, obj);
		for(int i = 1; i < 3; i++) { obj = new FronosBiomeLayerZoom(2000L + i, obj); }
		obj = FronosBiomeLayerZoom.func_75915_a(1000L, obj, 0);
		obj = new FronosBiomeLayerBiomes(200L, obj, dim);
		obj = FronosBiomeLayerZoom.func_75915_a(1000L, obj, 2);
		for(int j = 0; j < biomesize; j++) { obj = new FronosBiomeLayerZoom(1000L + j, obj); }
		FronosBiomeLayerVoronoiZoom genlayervoronoizoom = new FronosBiomeLayerVoronoiZoom(10L, obj);
		obj.initWorldGenSeed(seed);
		genlayervoronoizoom.initWorldGenSeed(seed);
		return new GenLayer[] { obj, genlayervoronoizoom };
	}

	public FronosBiomeLayer(long seed)
	{
		super(seed);
	}

	public static byte getModdedBiomeSize(WorldType worldType, byte original)
	{
		WorldTypeEvent.BiomeSize event = new WorldTypeEvent.BiomeSize(worldType, original);
		MinecraftForge.TERRAIN_GEN_BUS.post(event);
		return event.newSize;
	}
}