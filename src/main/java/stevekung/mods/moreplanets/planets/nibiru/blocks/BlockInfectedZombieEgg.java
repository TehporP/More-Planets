/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.blocks;

import net.minecraft.util.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockEggMP;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityInfectedZombie;

public class BlockInfectedZombieEgg extends BlockEggMP
{
	public BlockInfectedZombieEgg(String name)
	{
		super();
		this.setResistance(0.0F);
		this.setHardness(-1.0F);
		this.setUnlocalizedName(name);
	}

	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion)
	{
		if (!world.isRemote)
		{
			EntityInfectedZombie zombie = new EntityInfectedZombie(world);
			zombie.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
			world.spawnEntityInWorld(zombie);
		}
		world.setBlockToAir(pos);
		this.onBlockDestroyedByExplosion(world, pos, explosion);
	}
}