/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.mercury.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.planets.mercury.items.MercuryItems;

public class BlockMetallicRock extends BlockBaseMP
{
	public BlockMetallicRock(String name)
	{
		super(Material.rock);
		this.setUnlocalizedName(name);
		this.setHardness(3.0F);
		this.setResistance(8.0F);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		if (fortune > 3)
		{
			fortune = 3;
		}
		if (rand.nextInt(10 - fortune * 3) == 0)
		{
			return MercuryItems.mercury_item;
		}
		return Item.getItemFromBlock(this);
	}

	@Override
	public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float par6, int par7)
	{
		super.dropBlockAsItemWithChance(world, pos, state, par6, par7);

		if (this.getItemDropped(state, world.rand, par7) != Item.getItemFromBlock(this))
		{
			int var8 = MathHelper.getRandomIntegerInRange(world.rand, 3, 5);
			this.dropXpOnBlockBreak(world, pos, var8);
		}
	}
}