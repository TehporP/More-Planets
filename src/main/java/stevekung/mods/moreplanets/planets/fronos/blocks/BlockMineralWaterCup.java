/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityMineralWaterCup;

public class BlockMineralWaterCup extends BlockFilledCup
{
	public BlockMineralWaterCup(String name)
	{
		super();
		this.setUnlocalizedName(name);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float par7, float par8, float par9)
	{
		if (player.isPotionActive(Potion.regeneration.id) || player.isPotionActive(Potion.moveSpeed.id))
		{
			return false;
		}
		else
		{
			player.getFoodStats().addStats(6, 0.6F);
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 1200, 2));
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2400, 3));
			world.setBlockState(pos, FronosBlocks.cup.getDefaultState().withProperty(FACING, EnumFacing.getFront(this.getMetaFromState(state))), 3);
			world.playSoundEffect(pos.getX(), pos.getY(), pos.getZ(), "random.drink", 0.5F, world.rand.nextFloat() * 0.1F + 1.2F);
			return true;
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return FronosItems.cup;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 1;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityMineralWaterCup();
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
	{
		return new ItemStack(FronosItems.cup, 1, 1);
	}
}