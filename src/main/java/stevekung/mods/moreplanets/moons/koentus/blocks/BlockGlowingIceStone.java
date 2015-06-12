/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockBreakableMP;

public class BlockGlowingIceStone extends BlockBreakableMP
{
	public static PropertyEnum COLOR = PropertyEnum.create("color", EnumDyeColor.class);

	public BlockGlowingIceStone(String name)
	{
		super(Material.ice);
		this.setUnlocalizedName(name);
		this.slipperiness = 0.98F;
		this.setStepSound(soundTypeGlass);
		this.setDefaultState(this.getDefaultState().withProperty(COLOR, EnumDyeColor.WHITE));
		this.setHardness(0.5F);
		this.setLightLevel(1.0F);
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.TRANSLUCENT;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
	}

	@Override
	public MapColor getMapColor(IBlockState state)
	{
		return ((EnumDyeColor)state.getValue(COLOR)).getMapColor();
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {COLOR});
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		EnumDyeColor[] var4 = EnumDyeColor.values();
		int var5 = var4.length;

		for (int var6 = 0; var6 < var5; ++var6)
		{
			EnumDyeColor var7 = var4[var6];
			list.add(new ItemStack(this, 1, var7.getMetadata()));
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(Blocks.air);
	}
}