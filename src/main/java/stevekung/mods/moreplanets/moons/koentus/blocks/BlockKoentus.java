/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.blocks;

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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
import stevekung.mods.moreplanets.moons.koentus.items.KoentusItems;

public class BlockKoentus extends BlockBasicMP /*implements IDetectableResource, ITerraformableBlock*/
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockKoentus(String name)
	{
		super(Material.rock);
		this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.koentus_surface_rock));
		this.setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 15; ++i)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World world, BlockPos pos)
	{
		Block block = world.getBlockState(pos).getBlock();

		if (!(block instanceof BlockKoentus))
		{
			return 0.0F;
		}

		switch (this.getMetaFromState(world.getBlockState(pos)))
		{
		case 0:
		case 1:
			return 1.25F;
		case 2:
		case 11:
		case 12:
		case 13:
			return 1.5F;
		case 9:
		case 10:
			return 3.0F;
		case 14:
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
		if (meta >= 2 && meta <= 8 || meta >= 11 && meta <= 13)
		{
			return 5.0F;
		}
		if (meta == 9 || meta == 10)
		{
			return 8.0F;
		}
		if (meta == 14)
		{
			return 40.0F;
		}
		return super.getExplosionResistance(world, pos, entity, explosion);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		int meta = this.getMetaFromState(state);

		if (meta == 2)
		{
			return Item.getItemFromBlock(KoentusBlocks.koentus_block);
		}
		if (meta >= 6 && meta <= 8)
		{
			return KoentusItems.koentus_item;
		}
		return Item.getItemFromBlock(this);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		int meta = this.getMetaFromState(state);

		if (meta == 2)
		{
			return 3;
		}
		if (meta == 6)
		{
			return 0;
		}
		if (meta == 7)
		{
			return 1;
		}
		if (meta == 8)
		{
			return 2;
		}
		return meta;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random rand)
	{
		int meta = this.getMetaFromState(state);

		if (meta == 7)
		{
			return 1 + rand.nextInt(4);
		}
		if (meta == 8)
		{
			return 1 + rand.nextInt(2);
		}
		return super.quantityDropped(state, fortune, rand);
	}

	/*@Override
	public boolean isValueable(IBlockState state)
	{
		int meta = this.getMetaFromState(state);

		if (meta >= 4 && meta <= 8)
		{
			return true;
		}
		return false;
	}*/

	@Override
	public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon)
	{
		IBlockState state = world.getBlockState(pos);
		return state == state.withProperty(VARIANT, BlockType.white_crystal_block) || state == state.withProperty(VARIANT, BlockType.solid_koentus_meteoric_iron);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		int meta = this.getMetaFromState(state);

		if (meta == 11)
		{
			if (rand.nextInt(10) == 0)
			{
				if (!World.doesBlockHaveSolidTopSurface(world, pos.down()))
				{
					MorePlanetsCore.proxy.spawnParticle(ParticleTypesMP.KOENTUS_SLUDGE_DRIP, pos.getX() + rand.nextDouble(), pos.getY(), pos.getZ() + rand.nextDouble());

					if (rand.nextInt(100) == 0)
					{
						world.playSound(pos.getX(), pos.getY(), pos.getZ(), "galacticraftcore:ambience.singledrip", 1, 0.8F + rand.nextFloat() / 5.0F, false);
					}
				}
			}
		}
	}

	/*@Override
	public boolean isTerraformable(World world, BlockPos pos, IBlockState state)
	{
		int meta = this.getMetaFromState(state);

		if (meta == 0 || meta == 1)
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
		koentus_surface_rock,
		koentus_sub_surface_rock,
		koentus_rock,
		koentus_cobblestone,
		koentus_tin_ore,
		koentus_copper_ore,
		white_crystal_ore,
		emp_crystal_ore,
		becterial_fossil_ore,
		white_crystal_block,
		emp_crystal_block,
		solid_koentus_meteoric_iron,
		koentus_ancient_stone,
		koentus_ancient_stone_brick,
		koentus_dungeon_brick;

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