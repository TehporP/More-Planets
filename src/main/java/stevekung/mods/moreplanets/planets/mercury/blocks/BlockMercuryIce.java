/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.mercury.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import stevekung.mods.moreplanets.common.blocks.BlockIceMP;
import stevekung.mods.moreplanets.common.util.WorldUtilMP;

public class BlockMercuryIce extends BlockIceMP
{
	public BlockMercuryIce(String name)
	{
		super(Material.ice);
		this.setUnlocalizedName(name);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile)
	{
		player.addExhaustion(0.025F);

		if (this.canSilkHarvest(world, pos, world.getBlockState(pos), player) && EnchantmentHelper.getSilkTouchModifier(player))
		{
			List<ItemStack> items = new ArrayList<ItemStack>();
			ItemStack itemstack = this.createStackedBlock(state);

			if (itemstack != null)
			{
				items.add(itemstack);
			}

			ForgeEventFactory.fireBlockHarvesting(items, world, pos, world.getBlockState(pos), 0, 1.0f, true, player);

			for (ItemStack is : items)
			{
				spawnAsEntity(world, pos, is);
			}
		}
		else
		{
			if (world.provider.doesWaterVaporize())
			{
				world.setBlockToAir(pos);
				return;
			}
			else if (WorldUtilMP.isMercuryWorld(world, pos))
			{
				world.setBlockToAir(pos);
				return;
			}

			int i = EnchantmentHelper.getFortuneModifier(player);
			this.harvesters.set(player);
			this.dropBlockAsItem(world, pos, state, i);
			this.harvesters.set(null);
			Material material = world.getBlockState(pos.down()).getBlock().getMaterial();

			if (material.blocksMovement() || material.isLiquid())
			{
				world.setBlockState(pos, MercuryBlocks.dirty_water.getDefaultState());
			}
		}
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (world.getLightFor(EnumSkyBlock.BLOCK, pos) > 11 - this.getLightOpacity())
		{
			if (world.provider.doesWaterVaporize())
			{
				world.setBlockToAir(pos);
				return;
			}
			this.dropBlockAsItem(world, pos, state, 0);
			world.setBlockState(pos, MercuryBlocks.dirty_water.getDefaultState());
		}
		if (WorldUtilMP.isMercuryWorld(world, pos))
		{
			world.setBlockToAir(pos);
			return;
		}
	}
}