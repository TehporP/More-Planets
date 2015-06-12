/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class BiomeDecoratorFronosOre extends BiomeDecoratorSpace
{
	private World world;

	private WorldGenerator berryJellyGen;
	private WorldGenerator strawberryJellyGen;
	private WorldGenerator raspberryJellyGen;
	private WorldGenerator grapeJellyGen;
	private WorldGenerator limeJellyGen;
	private WorldGenerator orangeJellyGen;
	private WorldGenerator greenJellyGen;
	private WorldGenerator lemonJellyGen;
	private WorldGenerator ironGen;
	private WorldGenerator coalGen;
	private WorldGenerator aluminumGen;
	private WorldGenerator tinGen;
	private WorldGenerator copperGen;
	private WorldGenerator lapisGen;
	private WorldGenerator mineralGen;
	private WorldGenerator blackDiamondGen;
	private WorldGenerator iridiumGen;
	private WorldGenerator fronosDirtGen;

	private WorldGenerator cakeGen1;
	private WorldGenerator cakeGen2;
	private WorldGenerator cakeGen3;
	private WorldGenerator cakeBreadGen1;
	private WorldGenerator cakeBreadGen2;

	public BiomeDecoratorFronosOre()
	{
		// Block,NumberOfBlock,Meta,IsMeta,FillBlock,FillMeta
		this.berryJellyGen = new WorldGenMinableMeta(FronosBlocks.jelly_ore, 8, 0, true, FronosBlocks.fronos_block, 0);
		this.strawberryJellyGen = new WorldGenMinableMeta(FronosBlocks.jelly_ore, 8, 1, true, FronosBlocks.fronos_block, 0);
		this.raspberryJellyGen = new WorldGenMinableMeta(FronosBlocks.jelly_ore, 8, 2, true, FronosBlocks.fronos_block, 0);
		this.grapeJellyGen = new WorldGenMinableMeta(FronosBlocks.jelly_ore, 8, 4, true, FronosBlocks.fronos_block, 0);
		this.limeJellyGen = new WorldGenMinableMeta(FronosBlocks.jelly_ore, 8, 3, true, FronosBlocks.fronos_block, 0);
		this.orangeJellyGen = new WorldGenMinableMeta(FronosBlocks.jelly_ore, 8, 5, true, FronosBlocks.fronos_block, 0);
		this.greenJellyGen = new WorldGenMinableMeta(FronosBlocks.jelly_ore, 8, 6, true, FronosBlocks.fronos_block, 0);
		this.lemonJellyGen = new WorldGenMinableMeta(FronosBlocks.jelly_ore, 8, 7, true, FronosBlocks.fronos_block, 0);
		this.fronosDirtGen = new WorldGenMinableMeta(FronosBlocks.fronos_dirt, 32, 0, true, FronosBlocks.fronos_block, 0);

		this.ironGen = new WorldGenMinableMeta(FronosBlocks.fronos_block, 8, 2, true, FronosBlocks.fronos_block, 0);
		this.coalGen = new WorldGenMinableMeta(FronosBlocks.fronos_block, 16, 3, true, FronosBlocks.fronos_block, 0);
		this.aluminumGen = new WorldGenMinableMeta(FronosBlocks.fronos_block, 8, 4, true, FronosBlocks.fronos_block, 0);
		this.tinGen = new WorldGenMinableMeta(FronosBlocks.fronos_block, 8, 5, true, FronosBlocks.fronos_block, 0);
		this.copperGen = new WorldGenMinableMeta(FronosBlocks.fronos_block, 8, 6, true, FronosBlocks.fronos_block, 0);
		this.lapisGen = new WorldGenMinableMeta(FronosBlocks.fronos_block, 8, 7, true, FronosBlocks.fronos_block, 0);
		this.mineralGen = new WorldGenMinableMeta(FronosBlocks.fronos_block, 4, 8, true, FronosBlocks.fronos_block, 0);
		this.blackDiamondGen = new WorldGenMinableMeta(FronosBlocks.fronos_block, 8, 9, true, FronosBlocks.fronos_block, 0);
		this.iridiumGen = new WorldGenMinableMeta(FronosBlocks.fronos_block, 8, 10, true, FronosBlocks.fronos_block, 0);

		this.cakeGen1 = new WorldGenMinableMeta(FronosBlocks.frosted_cake, 32, 4, true, FronosBlocks.frosted_cake, 3);
		this.cakeGen2 = new WorldGenMinableMeta(FronosBlocks.frosted_cake, 32, 5, true, FronosBlocks.frosted_cake, 3);
		this.cakeGen3 = new WorldGenMinableMeta(FronosBlocks.frosted_cake, 32, 6, true, FronosBlocks.frosted_cake, 3);
		this.cakeBreadGen1 = new WorldGenMinableMeta(FronosBlocks.frosted_cake, 32, 1, true, FronosBlocks.frosted_cake, 0);
		this.cakeBreadGen2 = new WorldGenMinableMeta(FronosBlocks.frosted_cake, 32, 2, true, FronosBlocks.frosted_cake, 0);
	}

	@Override
	protected void decorate()
	{
		this.generateOre(16, this.tinGen, 0, 64);
		this.generateOre(16, this.copperGen, 0, 64);
		this.generateOre(12, this.aluminumGen, 0, 48);
		this.generateOre(16, this.berryJellyGen, 0, 255);
		this.generateOre(16, this.strawberryJellyGen, 0, 255);
		this.generateOre(16, this.raspberryJellyGen, 0, 255);
		this.generateOre(16, this.grapeJellyGen, 0, 255);
		this.generateOre(16, this.limeJellyGen, 0, 255);
		this.generateOre(16, this.orangeJellyGen, 0, 255);
		this.generateOre(16, this.greenJellyGen, 0, 255);
		this.generateOre(16, this.lemonJellyGen, 0, 255);
		this.generateOre(32, this.fronosDirtGen, 0, 255);
		this.generateOre(16, this.ironGen, 0, 64);
		this.generateOre(16, this.coalGen, 0, 255);
		this.generateOre(3, this.lapisGen, 0, 24);
		this.generateOre(6, this.mineralGen, 0, 32);
		this.generateOre(8, this.blackDiamondGen, 0, 16);
		this.generateOre(10, this.iridiumGen, 0, 24);

		this.generateOre(32, this.cakeGen1, 0, 255);
		this.generateOre(32, this.cakeGen2, 0, 255);
		this.generateOre(32, this.cakeGen3, 0, 255);
		this.generateOre(32, this.cakeBreadGen1, 0, 255);
		this.generateOre(32, this.cakeBreadGen2, 0, 255);
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