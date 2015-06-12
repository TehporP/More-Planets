/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockOysterMP;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP.ParticleTypesMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntitySpaceOysterOpen;

public class BlockSpaceOysterOpen extends BlockOysterMP
{
	public BlockSpaceOysterOpen(String name)
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
		if (rand.nextInt(1) == 0)
		{
			MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.GOLDEN_DUST, pos.getX() + rand.nextFloat(), pos.getY() + 0.15F, pos.getZ() + rand.nextFloat());
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float par7, float par8, float par9)
	{
		world.setBlockState(pos, FronosBlocks.space_oyster_close.getDefaultState().withProperty(FACING, EnumFacing.getFront(this.getMetaFromState(state))), 3);
		EntityItem entityitem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(FronosItems.pearl, 1, 0));

		if (!world.isRemote && world.rand.nextInt(20) == 0)
		{
			world.spawnEntityInWorld(entityitem);
		}
		return true;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		if (rand.nextInt(20) == 0)
		{
			return FronosItems.pearl;
		}
		return Item.getItemFromBlock(this);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 0;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntitySpaceOysterOpen();
	}
}