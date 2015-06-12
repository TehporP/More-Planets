/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.worldgen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;

public class BiomeDecoratorPolongnius extends BiomeDecoratorSpace
{
	private World world;

	private WorldGenerator dirtGen;
	private WorldGenerator copperGen;
	private WorldGenerator tinGen;
	private WorldGenerator ironGen;
	private WorldGenerator palladiumGen;
	private WorldGenerator floniumGen;
	private WorldGenerator purpleCrystalGen;
	private WorldGenerator cheeseOfMilkGen;

	public BiomeDecoratorPolongnius()
	{
		// Block,NumberOfBlock,Meta,IsMeta,FillBlock,FillMeta
		this.dirtGen = new WorldGenMinableMeta(PolongniusBlocks.polongnius_block, 32, 1, true, PolongniusBlocks.polongnius_block, 2);
		this.copperGen = new WorldGenMinableMeta(PolongniusBlocks.polongnius_block, 8, 4, true, PolongniusBlocks.polongnius_block, 2);
		this.tinGen = new WorldGenMinableMeta(PolongniusBlocks.polongnius_block, 8, 5, true, PolongniusBlocks.polongnius_block, 2);
		this.ironGen = new WorldGenMinableMeta(PolongniusBlocks.polongnius_block, 8, 6, true, PolongniusBlocks.polongnius_block, 2);
		this.palladiumGen = new WorldGenMinableMeta(PolongniusBlocks.polongnius_block, 8, 7, true, PolongniusBlocks.polongnius_block, 2);
		this.floniumGen = new WorldGenMinableMeta(PolongniusBlocks.polongnius_block, 16, 8, true, PolongniusBlocks.polongnius_block, 2);
		this.purpleCrystalGen = new WorldGenMinableMeta(PolongniusBlocks.polongnius_block, 6, 9, true, PolongniusBlocks.polongnius_block, 2);
		this.cheeseOfMilkGen = new WorldGenMinableMeta(PolongniusBlocks.cheese_of_milk, 4, 0, true, PolongniusBlocks.polongnius_block, 2);
	}

	@Override
	public void decorate()
	{
		this.generateOre(16, this.tinGen, 0, 64);
		this.generateOre(16, this.copperGen, 0, 64);
		this.generateOre(16, this.ironGen, 0, 64);
		this.generateOre(32, this.dirtGen, 0, 255);
		this.generateOre(10, this.purpleCrystalGen, 0, 32);
		this.generateOre(12, this.palladiumGen, 0, 48);
		this.generateOre(12, this.floniumGen, 0, 48);
		this.generateOre(10, this.cheeseOfMilkGen, 0, 24);
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