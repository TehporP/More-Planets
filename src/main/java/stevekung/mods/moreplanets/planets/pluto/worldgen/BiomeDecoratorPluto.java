/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.worldgen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.pluto.blocks.PlutoBlocks;

public class BiomeDecoratorPluto extends BiomeDecoratorSpace
{
	private WorldGenerator dirtGen;
	private WorldGenerator ironGen;
	private WorldGenerator frozenIronGen;
	private WorldGenerator meteorGen;
	private WorldGenerator xeoniumGen;
	private WorldGenerator nitrogenGen;
	private WorldGenerator methaneGen;
	private World world;

	public BiomeDecoratorPluto()
	{
		// Block,NumberOfBlock,Meta,IsMeta,FillBlock,FillMeta
		this.dirtGen = new WorldGenMinableMeta(PlutoBlocks.pluto_block, 32, 1, true, PlutoBlocks.pluto_block, 2);
		this.frozenIronGen = new WorldGenMinableMeta(PlutoBlocks.pluto_block, 8, 5, true, PlutoBlocks.pluto_block, 2);
		this.xeoniumGen = new WorldGenMinableMeta(PlutoBlocks.pluto_block, 4, 7, true, PlutoBlocks.pluto_block, 2);
		this.ironGen = new WorldGenMinableMeta(PlutoBlocks.pluto_block, 8, 6, true, PlutoBlocks.pluto_block, 2);
		this.meteorGen = new WorldGenMinableMeta(PlutoBlocks.pluto_block, 8, 4, true, PlutoBlocks.pluto_block, 2);
		this.nitrogenGen = new WorldGenMinableMeta(PlutoBlocks.frozen_nitrogen_block, 4, 0, true, Blocks.air, 0);
		this.methaneGen = new WorldGenMinableMeta(PlutoBlocks.frozen_methane_block, 4, 0, true, Blocks.air, 0);
	}

	@Override
	protected void decorate()
	{
		this.generateOre(16, this.xeoniumGen, 0, 32);
		this.generateOre(16, this.frozenIronGen, 48, 64);
		this.generateOre(16, this.ironGen, 0, 128);
		this.generateOre(32, this.dirtGen, 0, 255);
		this.generateOre(12, this.meteorGen, 0, 64);
		this.generateOre(1, this.nitrogenGen, 60, 64);
		this.generateOre(1, this.methaneGen, 60, 64);
	}

	@Override
	protected void setCurrentWorld(World world)
	{
		this.world = world;
	}

	@Override
	protected World getCurrentWorld()
	{
		return this.world;
	}
}