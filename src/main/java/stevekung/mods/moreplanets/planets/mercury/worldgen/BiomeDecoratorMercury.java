/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.mercury.worldgen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.mercury.blocks.MercuryBlocks;

public class BiomeDecoratorMercury extends BiomeDecoratorSpace
{
	private WorldGenerator dirtGen;
	private WorldGenerator ironGen;
	private WorldGenerator aluminumGen;
	private WorldGenerator tinGen;
	private WorldGenerator copperGen;
	private WorldGenerator meteorGen;
	private WorldGenerator silicateGen;
	private WorldGenerator metallicGen;
	private World world;

	public BiomeDecoratorMercury()
	{
		// Block,NumberOfBlock,Meta,IsMeta,FillBlock,FillMeta
		this.dirtGen = new WorldGenMinableMeta(MercuryBlocks.mercury_block, 32, 1, true, MercuryBlocks.mercury_block, 2);
		this.tinGen = new WorldGenMinableMeta(MercuryBlocks.mercury_block, 8, 4, true, MercuryBlocks.mercury_block, 2);
		this.copperGen = new WorldGenMinableMeta(MercuryBlocks.mercury_block, 8, 5, true, MercuryBlocks.mercury_block, 2);
		this.aluminumGen = new WorldGenMinableMeta(MercuryBlocks.mercury_block, 8, 6, true, MercuryBlocks.mercury_block, 2);
		this.ironGen = new WorldGenMinableMeta(MercuryBlocks.mercury_block, 8, 7, true, MercuryBlocks.mercury_block, 2);
		this.meteorGen = new WorldGenMinableMeta(MercuryBlocks.mercury_block, 8, 8, true, MercuryBlocks.mercury_block, 2);
		this.silicateGen = new WorldGenMinableMeta(MercuryBlocks.mercury_block, 32, 9, true, MercuryBlocks.mercury_block, 2);
		this.metallicGen = new WorldGenMinableMeta(MercuryBlocks.metallic_rock, 32, 0, true, MercuryBlocks.mercury_block, 2);
	}

	@Override
	protected void decorate()
	{
		this.generateOre(12, this.aluminumGen, 0, 48);
		this.generateOre(16, this.tinGen, 0, 64);
		this.generateOre(16, this.copperGen, 0, 64);
		this.generateOre(32, this.ironGen, 0, 128);
		this.generateOre(32, this.dirtGen, 0, 255);
		this.generateOre(12, this.meteorGen, 0, 64);
		this.generateOre(32, this.silicateGen, 0, 255);
		this.generateOre(16, this.metallicGen, 0, 32);
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