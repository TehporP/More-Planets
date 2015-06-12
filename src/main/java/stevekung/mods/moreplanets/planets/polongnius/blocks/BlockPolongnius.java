/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockBasicMP;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP.ParticleTypesMP;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;

public class BlockPolongnius extends BlockBasicMP /*implements IDetectableResource, ITerraformableBlock*/
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockPolongnius(String name)
	{
		super(Material.rock);
		this.setUnlocalizedName(name);
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.cheese_gas));
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if (state == state.withProperty(VARIANT, BlockType.polongnius_slime_dungeon_brick))
		{
			entity.motionX *= 0.45D;
			entity.motionZ *= 0.45D;
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		if (state == state.withProperty(VARIANT, BlockType.cheese_gas))
		{
			float f = 0.25F;
			return AxisAlignedBB.fromBounds(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1 - f, pos.getZ() + 1);
		}
		if (state == state.withProperty(VARIANT, BlockType.polongnius_slime_dungeon_brick))
		{
			float f = 0.1F;
			return AxisAlignedBB.fromBounds(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1 - f, pos.getZ() + 1);
		}
		return super.getCollisionBoundingBox(world, pos, state);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 16; ++i)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World world, BlockPos pos)
	{
		Block block = world.getBlockState(pos).getBlock();

		if (!(block instanceof BlockPolongnius))
		{
			return 0.0F;
		}

		switch (this.getMetaFromState(world.getBlockState(pos)))
		{
		case 0:
			return 0.25F;
		case 1:
			return 0.5F;
		case 2:
			return 1.5F;
		case 11:
		case 12:
		case 13:
			return 3.0F;
		case 14:
		case 15:
			return 4.0F;
		default:
			return 2.0F;
		}
	}

	@Override
	public float getExplosionResistance(World world, BlockPos pos, Entity entity, Explosion explosion)
	{
		int meta = this.getMetaFromState(world.getBlockState(pos));

		if (meta <= 1)
		{
			return 6.0F;
		}
		if (meta >= 2 && meta <= 10)
		{
			return 5.0F;
		}
		if (meta >= 11 && meta <= 13)
		{
			return 8.0F;
		}
		if (meta == 14 || meta == 15)
		{
			return 40.0F;
		}
		return super.getExplosionResistance(world, pos, entity, explosion);
	}

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
	{
		IBlockState state = world.getBlockState(pos);

		if (state == state.withProperty(VARIANT, BlockType.cheese_gas) || state == state.withProperty(VARIANT, BlockType.solid_cheese_gas))
		{
			return true;
		}
		return super.canHarvestBlock(world, pos, player);
	}

	@Override
	public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon)
	{
		IBlockState state = world.getBlockState(pos);
		return state == state.withProperty(VARIANT, BlockType.solid_polongnius_meteoric_iron) || state == state.withProperty(VARIANT, BlockType.palladium_block);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		if (state == state.withProperty(VARIANT, BlockType.palladium_ore) || state == state.withProperty(VARIANT, BlockType.flonium_ore) || state == state.withProperty(VARIANT, BlockType.purple_crystal_ore))
		{
			return PolongniusItems.polongnius_item;
		}
		if (state == state.withProperty(VARIANT, BlockType.cheese_of_milk_ore))
		{
			return PolongniusItems.cheese_food;
		}
		return Item.getItemFromBlock(this);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		int meta = this.getMetaFromState(state);

		if (meta == 2 || meta == 7)
		{
			return 3;
		}
		if (meta == 8 || meta == 10)
		{
			return 0;
		}
		if (meta == 9)
		{
			return 1;
		}
		return meta;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random rand)
	{
		if (state == state.withProperty(VARIANT, BlockType.palladium_ore))
		{
			return 1 + rand.nextInt(2);
		}
		if (state == state.withProperty(VARIANT, BlockType.flonium_ore))
		{
			return 1 + rand.nextInt(4);
		}
		if (state == state.withProperty(VARIANT, BlockType.cheese_of_milk_ore))
		{
			return 1 + rand.nextInt(1);
		}
		return super.quantityDropped(state, fortune, rand);
	}

	/*@Override
	public boolean isValueable(IBlockState state)
	{
		int meta = (Integer)state.getValue(VARIANT);

		if (meta >= 4 && meta <= 9)
		{
			return true;
		}
		return false;
	}*/

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (state == state.withProperty(VARIANT, BlockType.polongnius_slime_dungeon_brick))
		{
			if (!World.doesBlockHaveSolidTopSurface(world, pos.up()))
			{
				if (world.rand.nextInt(10) == 0)
				{
					MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.CHEESE_SLIME, pos.getX() + world.rand.nextFloat(), pos.getY() + 1.2F, pos.getZ() + world.rand.nextFloat());
				}
			}
		}
	}

	/*@Override
	public boolean isTerraformable(World world, BlockPos pos, IBlockState state)
	{
		if (state == state.withProperty(VARIANT, BlockType.cheese_gas) || state == state.withProperty(VARIANT, BlockType.solid_cheese_gas))
		{
			return true;
		}
		return false;
	}*/

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { VARIANT });
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, BlockType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((BlockType)state.getValue(VARIANT)).ordinal();
	}

	public static enum BlockType implements IStringSerializable
	{
		cheese_gas,
		solid_cheese_gas,
		polongnius_stone,
		polongnius_cobblestone,
		polongnius_copper_ore,
		polongnius_tin_ore,
		polongnius_iron_ore,
		palladium_ore,
		flonium_ore,
		purple_crystal_ore,
		cheese_of_milk_ore,
		solid_polongnius_meteoric_iron,
		purple_crystal_block,
		palladium_block,
		polongnius_slime_dungeon_brick,
		polongnius_dungeon_brick;

		@Override
		public String toString()
		{
			return this.getName();
		}

		@Override
		public String getName()
		{
			return this.name();
		}
	}
}