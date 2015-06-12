/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockOysterMP;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP.ParticleTypesMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityCavernOysterOpen;

public class BlockCavernOysterOpen extends BlockOysterMP
{
	public BlockCavernOysterOpen(String name)
	{
		super();
		this.setUnlocalizedName(name);
	}

	@Override
	public int getLightValue(IBlockAccess world, BlockPos pos)
	{
		return 4;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		for (int i = 0; i < 4; i++)
		{
			double d1 = pos.getX() + rand.nextFloat();
			double d2 = pos.getY() + rand.nextFloat();
			double d3 = pos.getZ() + rand.nextFloat();
			double d4 = 0.0D;
			double d5 = 0.0D;
			double d6 = 0.0D;
			d4 = (rand.nextFloat() - 0.5D) * 0.5D;
			d5 = (rand.nextFloat() - 0.5D) * 0.5D;
			d6 = (rand.nextFloat() - 0.5D) * 0.5D;
			MorePlanetsCore.proxy.spawnMotionParticle(ParticleTypesMP.CAVERN_OYSTER, d1, d2, d3, d4, d5, d6);
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float par7, float par8, float par9)
	{
		world.setBlockState(pos, FronosBlocks.cavern_oyster_close.getDefaultState().withProperty(FACING, EnumFacing.getFront(this.getMetaFromState(state))), 3);
		EntityItem entityitem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(FronosItems.pearl, 1, 1));

		if (!world.isRemote && world.rand.nextInt(20) == 0)
		{
			world.spawnEntityInWorld(entityitem);
		}
		return true;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		List<ItemStack> ret = new ArrayList<ItemStack>();
		Random rand = world instanceof World ? ((World)world).rand : RANDOM;
		int count = this.quantityDropped(state, fortune, rand);

		for (int i = 0; i < count; i++)
		{
			if (rand.nextInt(20) == 0)
			{
				ret.add(new ItemStack(FronosItems.pearl, 1, 1));
			}
			else
			{
				ret.add(new ItemStack(this, 1, 0));
			}
		}
		return ret;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityCavernOysterOpen();
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
	{
		return new ItemStack(this, 1, 0);
	}
}