/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

//package stevekung.mods.moreplanets.planets.fronos.blocks;
//
//import net.minecraft.block.ITileEntityProvider;
//import net.minecraft.block.material.Material;
//import net.minecraft.creativetab.CreativeTabs;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.MathHelper;
//import net.minecraft.world.World;
//import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;
//import stevekung.mods.moreplanets.core.MorePlanetsCore;
//
//public class BlockMineralWaterGenerator extends BlockBaseMP implements ITileEntityProvider
//{
//	public BlockMineralWaterGenerator(String name)
//	{
//		super(Material.iron);
//		this.setHardness(2.0F);
//		this.setStepSound(soundTypeMetal);
//		this.setUnlocalizedName(name);
//	}
//
//	@Override
//	public CreativeTabs getCreativeTabToDisplayOn()
//	{
//		return MorePlanetsCore.mpBlocksTab;
//	}
//
//	@Override
//	public int getRenderType()
//	{
//		return 3;
//	}
//
//	@Override
//	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
//	{
//		int metadata = world.getBlockMetadata(x, y, z);
//
//		int angle = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
//		int change = 0;
//
//		switch (angle)
//		{
//		case 0:
//			change = 3;
//			break;
//		case 1:
//			change = 1;
//			break;
//		case 2:
//			change = 2;
//			break;
//		case 3:
//			change = 0;
//			break;
//		}
//		world.setBlockMetadataWithNotify(x, y, z, (metadata & 4) + change, 3);
//	}
//
//	@Override
//	public boolean onUseWrench(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float hitX, float hitY, float hitZ)
//	{
//		int metadata = par1World.getBlockMetadata(x, y, z);
//		int original = metadata;
//
//		int change = 0;
//
//		switch (original)
//		{
//		case 0:
//			change = 3;
//			break;
//		case 3:
//			change = 1;
//			break;
//		case 1:
//			change = 2;
//			break;
//		case 2:
//			change = 0;
//			break;
//		}
//		par1World.setBlockMetadataWithNotify(x, y, z, change, 3);
//		return true;
//	}
//
//	@Override
//	public boolean onMachineActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float hitX, float hitY, float hitZ)
//	{
//		if (!par1World.isRemote)
//		{
//			par5EntityPlayer.openGui(MorePlanetsCore.instance, -1, par1World, x, y, z);
//			return true;
//		}
//		return true;
//	}
//
//	@Override
//	public TileEntity createNewTileEntity(World world, int metadata)
//	{
//		return new TileEntityMineralWaterGenerator();
//	}
//}