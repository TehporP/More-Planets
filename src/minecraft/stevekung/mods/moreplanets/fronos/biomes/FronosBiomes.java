/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import stevekung.mods.moreplanets.fronos.util.FronosConfigManager;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class FronosBiomes
{
	public static Optional<? extends BiomeGenBase> testingBiome;
	public static Optional<? extends BiomeGenBase> canyon;
	public static Optional<? extends BiomeGenBase> cherryBlossomGrove;
	public static Optional<? extends BiomeGenBase> mesa;

	public static void init()
	{
		initializeBiomes();
		addToBiomeDictionary();
		registerBiomes();
	}

	public static void initializeBiomes()
	{
		FronosBiomes.testingBiome = Optional.of(new FronosBiomeGenTest3(FronosConfigManager.idBiomeOases).setColor(5470985).setBiomeName("Testing").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.9F, 1.2F));
		FronosBiomes.canyon = Optional.of(new FronosBiomeGenTest1(FronosConfigManager.idBiomeCanyon).setColor(5470985).setBiomeName("Canyon").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.9F, 1.2F));
		FronosBiomes.cherryBlossomGrove = Optional.of(new FronosBiomeGenTest2(FronosConfigManager.idBiomeCherry).setColor(5470985).setBiomeName("Cherry").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.9F, 1.2F));
		FronosBiomes.mesa = Optional.of(new FronosBiomeGenTest4(FronosConfigManager.idBiomeMesa).setColor(5470985).setBiomeName("Mesa").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.9F, 1.2F));
	}

	public static void addToBiomeDictionary()
	{
		BiomeDictionary.registerBiomeType(testingBiome.get(), Type.JUNGLE, Type.HILLS);
		BiomeDictionary.registerBiomeType(canyon.get(), Type.DESERT, Type.HILLS);
		BiomeDictionary.registerBiomeType(cherryBlossomGrove.get(), Type.MAGICAL, Type.FOREST);
		BiomeDictionary.registerBiomeType(mesa.get(), Type.DESERT, Type.HILLS);
	}

	public static void registerBiomes()
	{
		registerBiome(testingBiome);
		registerBiome(canyon);
		registerBiome(cherryBlossomGrove);
		registerBiome(mesa);
	}

	public static void registerBiome(Optional<? extends BiomeGenBase> biome)
	{
		if (biome.isPresent())
		{
			GameRegistry.addBiome(biome.get());
		}
	}
}