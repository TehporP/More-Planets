/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

//package stevekung.mods.moreplanets.planets.polongnius.blocks;
//
//import net.minecraft.block.Block;
//import net.minecraft.block.ITileEntityProvider;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.properties.PropertyDirection;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.BlockPos;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.MathHelper;
//import net.minecraft.util.MovingObjectPosition;
//import net.minecraft.util.StatCollector;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;
//
//public class BlockUltraVioletSolarPanel extends BlockBaseMP implements IPartialSealableBlock, IBlockShiftDesc, ITileEntityProvider
//{
//	public static PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
//
//	public BlockUltraVioletSolarPanel(String name)
//	{
//		super(Material.iron);
//		this.setHardness(2.0F);
//		this.setStepSound(soundTypeMetal);
//		this.setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
//		this.setUnlocalizedName(name);
//	}
//
//	@Override
//	public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side)
//	{
//		for (int y = 1; y <= 2; y++)
//		{
//			for (int x = -1; x <= 1; x++)
//			{
//				for (int z = -1; z <= 1; z++)
//				{
//					Block block = world.getBlock(x1 + (y == 2 ? x : 0), y1 + y, z1 + (y == 2 ? z : 0));
//
//					if (block.getMaterial() != Material.air && !block.isReplaceable(world, x1 + x, y1 + y, z1 + z))
//					{
//						return false;
//					}
//				}
//			}
//		}
//		return new BlockVec3(x1, y1, z1).newVecSide(side ^ 1).getBlock(world) != PolongniusBlocks.ultra_violet_solar_fake;
//	}
//
//	@Override
//	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
//	{
//		world.getBlockMetadata(x, y, z);
//
//		int angle = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
//		int change = 0;
//
//		switch (angle)
//		{
//		case 0:
//			change = 1;
//			break;
//		case 1:
//			change = 2;
//			break;
//		case 2:
//			change = 0;
//			break;
//		case 3:
//			change = 3;
//			break;
//		}
//
//		world.setBlockMetadataWithNotify(x, y, z, Block.getIdFromBlock(this) + change, 3);
//
//		TileEntity tile = world.getTileEntity(x, y, z);
//
//		if (tile instanceof TileEntityUltraVioletSolarPanel)
//		{
//			((TileEntityUltraVioletSolarPanel) tile).onCreate(new BlockVec3(x, y, z));
//		}
//	}
//
//	@Override
//	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
//	{
//		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
//	}
//
//	@Override
//	public void breakBlock(World world, BlockPos pos, IBlockState state)
//	{
//		TileEntity tile = world.getTileEntity(pos);
//
//		if (tile instanceof TileEntityUltraVioletSolarPanel)
//		{
//			((TileEntityUltraVioletSolarPanel)tile).onDestroy(tile);
//		}
//		super.breakBlock(world, pos, state);
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
//		if (metadata >= Block.getIdFromBlock(this))
//		{
//			original -= Block.getIdFromBlock(this);
//		}
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
//
//		if (metadata >= Block.getIdFromBlock(this))
//		{
//			original -= Block.getIdFromBlock(this);
//		}
//
//		TileEntity te = par1World.getTileEntity(x,  y,  z);
//
//		if (te instanceof TileBaseUniversalElectrical)
//		{
//			((TileBaseUniversalElectrical) te).updateFacing();
//		}
//		par1World.setBlockMetadataWithNotify(x, y, z, change, 3);
//		return true;
//	}
//
//	@Override
//	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ)
//	{
//		entityPlayer.openGui(MorePlanetCore.instance, -1, world, x, y, z);
//		return true;
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos)
//	{
//		return new ItemStack(this, 1, 0);
//	}
//
//	@Override
//	public TileEntity createNewTileEntity(World world, int meta)
//	{
//		return new TileEntityUltraVioletSolarPanel(5);
//	}
//
//	@Override
//	public boolean isOpaqueCube()
//	{
//		return false;
//	}
//
//	@Override
//	public boolean isFullCube()
//	{
//		return false;
//	}
//
//	@Override
//	public boolean isSealed(World world, BlockPos pos, EnumFacing side)
//	{
//		return true;
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