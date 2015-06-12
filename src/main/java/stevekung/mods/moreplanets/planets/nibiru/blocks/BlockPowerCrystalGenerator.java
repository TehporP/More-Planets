/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

//package stevekung.mods.moreplanets.planets.nibiru.blocks;
//
//import java.util.Random;
//
//import net.minecraft.block.ITileEntityProvider;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.creativetab.CreativeTabs;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.BlockPos;
//import net.minecraft.util.MathHelper;
//import net.minecraft.util.StatCollector;
//import net.minecraft.world.World;
//import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;
//import stevekung.mods.moreplanets.core.MorePlanetsCore;
//
//public class BlockPowerCrystalGenerator extends BlockBaseMP implements IBlockShiftDesc, ITileEntityProvider
//{
//	public BlockPowerCrystalGenerator(String name)
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
//	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
//	{
//		TileEntity tile = world.getTileEntity(pos);
//
//		if (tile instanceof TileEntityPowerCrystalGenerator)
//		{
//			TileEntityPowerCrystalGenerator tileEntity = (TileEntityPowerCrystalGenerator) tile;
//
//			if (tileEntity.heatGJperTick > 0)
//			{
//				int metadata = world.getBlockMetadata(x, y, z);
//				float var7 = x + 0.5F;
//				float var8 = y + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
//				float var9 = z + 0.5F;
//				float var10 = 0.52F;
//				float var11 = par5Random.nextFloat() * 0.6F - 0.3F;
//
//				if (metadata == 0)
//				{
//					world.spawnParticle("smoke", var7 - var10, var8, var9 + var11, 0.0D, 0.0D, 0.0D);
//					MorePlanetCore.proxy.spawnParticle("ichoriusSmoke", var7 - var10, var8, var9 + var11);
//				}
//				else if (metadata == 1)
//				{
//					world.spawnParticle("smoke", var7 + var10, var8, var9 + var11, 0.0D, 0.0D, 0.0D);
//					MorePlanetCore.proxy.spawnParticle("ichoriusSmoke", var7 + var10, var8, var9 + var11);
//				}
//				else if (metadata == 2)
//				{
//					world.spawnParticle("smoke", var7 + var11, var8, var9 + var10, 0.0D, 0.0D, 0.0D);
//					MorePlanetCore.proxy.spawnParticle("ichoriusSmoke", var7 + var11, var8, var9 + var10);
//				}
//				else if (metadata == 3)
//				{
//					world.spawnParticle("smoke", var7 + var11, var8, var9 - var10, 0.0D, 0.0D, 0.0D);
//					MorePlanetCore.proxy.spawnParticle("ichoriusSmoke", var7 + var11, var8, var9 - var10);
//				}
//			}
//		}
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
//		return new TileEntityPowerCrystalGenerator();
//	}
//
//	@Override
//	public String getShiftDescription(int meta)
//	{
//		return StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc");
//	}
//
//	@Override
//	public boolean showDescription(int meta)
//	{
//		return true;
//	}
//}