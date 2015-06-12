/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.blocks;

import java.util.Random;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.moons.europa.items.EuropaItems;

public class BlockEuropaSeaLantern extends BlockBaseMP
{
	public BlockEuropaSeaLantern(String name)
	{
		super(Material.glass);
		this.setStepSound(soundTypeGlass);
		this.setHardness(0.3F);
		this.setLightLevel(1.0F);
		this.setUnlocalizedName(name);
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 2 + rand.nextInt(2);
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random rand)
	{
		return MathHelper.clamp_int(this.quantityDropped(rand) + rand.nextInt(fortune + 1), 1, 5);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return EuropaItems.europa_prismarine;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 1;
	}

	@Override
	public MapColor getMapColor(IBlockState state)
	{
		return MapColor.quartzColor;
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		return true;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos)
	{
		return new ItemStack(this, 1, 0);
	}
}