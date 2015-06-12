/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

//package stevekung.mods.moreplanets.planets.polongnius.blocks;
//
//import java.util.Random;
//
//import net.minecraft.block.Block;
//import net.minecraft.block.ITileEntityProvider;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.client.particle.EffectRenderer;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.init.Blocks;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.BlockPos;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.MovingObjectPosition;
//import net.minecraft.world.Explosion;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import stevekung.mods.moreplanets.common.blocks.BlockContainerMP;
//
//public class BlockUltraVioletSolarFake extends BlockContainerMP implements IPartialSealableBlock, ITileEntityProvider
//{
//	public BlockUltraVioletSolarFake(String assetName)
//	{
//		super(Material.iron);
//		this.setStepSound(Block.soundTypeMetal);
//		this.setResistance(1000000000000000.0F);
//	}
//
//	@Override
//	public boolean isOpaqueCube()
//	{
//		return false;
//	}
//
//	@Override
//	public boolean canDropFromExplosion(Explosion explosion)
//	{
//		return false;
//	}
//
//	@Override
//	public float getBlockHardness(World world, BlockPos pos)
//	{
//		TileEntity tileEntity = world.getTileEntity(pos);
//
//		if (tileEntity != null)
//		{
//			BlockVec3 mainBlockPosition = ((TileEntityUltraVioletFake) tileEntity).mainBlockPosition;
//
//			if (mainBlockPosition != null)
//			{
//				return mainBlockPosition.getBlock(world).getBlockHardness(world, pos);
//			}
//		}
//		return this.blockHardness;
//	}
//
//	@Override
//	public boolean isSealed(World world, BlockPos pos, IBlockState state, EnumFacing side)
//	{
//		return true;
//	}
//
//	@Override
//	public void breakBlock(World world, BlockPos pos, IBlockState state)
//	{
//		TileEntity tileEntity = world.getTileEntity(pos);
//
//		if (tileEntity instanceof TileEntityUltraVioletFake)
//		{
//			((TileEntityUltraVioletFake) tileEntity).onBlockRemoval();
//		}
//		super.breakBlock(world, pos, state);
//	}
//
//	@Override
//	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float par7, float par8, float par9)
//	{
//		TileEntityUltraVioletFake tileEntity = (TileEntityUltraVioletFake)world.getTileEntity(pos);
//		return tileEntity.onBlockActivated(player);
//	}
//
//	@Override
//	public int quantityDropped(Random par1Random)
//	{
//		return 0;
//	}
//
//	@Override
//	public int getRenderType()
//	{
//		return -1;
//	}
//
//	@Override
//	public boolean isFullCube()
//	{
//		return false;
//	}
//
//	@Override
//	public TileEntity createNewTileEntity(World var1, int meta)
//	{
//		return new TileEntityUltraVioletFake();
//	}
//
//	@Override
//	public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos)
//	{
//		TileEntity tileEntity = world.getTileEntity(pos);
//
//		if (tileEntity instanceof TileEntityUltraVioletFake)
//		{
//			if (((TileEntityUltraVioletFake) tileEntity).mainBlockPosition != null)
//			{
//				Block mainBlockID = world.getBlockState(pos).getBlock();
//
//				if (Blocks.air != mainBlockID)
//				{
//					return mainBlockID.getPickBlock(moving, world, pos);
//				}
//			}
//		}
//		return null;
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public boolean addHitEffects(World world, MovingObjectPosition moving, EffectRenderer effect)
//	{
//		BlockPos pos = moving.getBlockPos();
//		TileEntity tileEntity = world.getTileEntity(pos);
//
//		if (tileEntity instanceof TileEntityUltraVioletFake)
//		{
//			if (((TileEntityUltraVioletFake) tileEntity).mainBlockPosition != null)
//			{
//				effect.addBlockHitEffects(pos, moving);
//			}
//		}
//		return super.addHitEffects(world, moving, effect);
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public boolean addDestroyEffects(World world, BlockPos pos, EffectRenderer effect)
//	{
//		return super.addDestroyEffects(world, pos, effect);
//	}
//
//	public void makeFakeBlock(World worldObj, BlockPos pos, BlockVec3 mainBlock, int meta)
//	{
//		worldObj.setBlockState(pos, this.getDefaultState(), 3);
//		((TileEntityUltraVioletFake) worldObj.getTileEntity(pos)).setMainBlock(mainBlock);
//	}
//}