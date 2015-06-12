package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityInfectedWorm;

public class BlockInfectedWormEggRock extends BlockBaseMP
{
	public BlockInfectedWormEggRock(String name)
	{
		super(Material.rock);
		this.setResistance(5.0F);
		this.setHardness(2.0F);
		this.setUnlocalizedName(name);
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 0;
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		return true;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote)
		{
			EntityInfectedWorm infectedWorm = new EntityInfectedWorm(world);
			infectedWorm.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0F, 0.0F);
			world.spawnEntityInWorld(infectedWorm);
			infectedWorm.spawnExplosionParticle();
		}
		super.onBlockDestroyedByPlayer(world, pos, state);
	}
}