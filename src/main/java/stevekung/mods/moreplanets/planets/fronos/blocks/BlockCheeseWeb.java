/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class BlockCheeseWeb extends BlockBaseMP
{
	public BlockCheeseWeb(String name)
	{
		super(Material.web);
		this.setUnlocalizedName(name);
		this.setLightOpacity(1);
		this.setHardness(4.0F);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		entity.setInWeb();
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		return null;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 9;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return FronosItems.fronos_item;
	}

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
	{
		ItemStack stack = player.inventory.getCurrentItem();

		if (stack == null)
		{
			return player.canHarvestBlock(this);
		}
		return stack != null && (stack.getItem() instanceof ItemShears || stack.getItem() instanceof ItemSword);
	}

	@Override
	public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, BlockPos pos)
	{
		ItemStack stack = player.inventory.getCurrentItem();

		if (stack != null && (stack.getItem() instanceof ItemShears || stack.getItem() instanceof ItemSword))
		{
			return 0.1F;
		}
		return ForgeHooks.blockStrength(this.getDefaultState(), player, world, pos);
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos)
	{
		return new ItemStack(this, 1, 0);
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT;
	}
}