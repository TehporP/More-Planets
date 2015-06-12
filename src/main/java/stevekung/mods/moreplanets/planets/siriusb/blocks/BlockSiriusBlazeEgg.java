/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.blocks;

import net.minecraft.util.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.blocks.BlockEggMP;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusBlaze;

public class BlockSiriusBlazeEgg extends BlockEggMP
{
	public BlockSiriusBlazeEgg(String name)
	{
		super();
		this.setLightLevel(1.0F);
		this.setUnlocalizedName(name);
	}

	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion)
	{
		if (!world.isRemote)
		{
			EntitySiriusBlaze blaze = new EntitySiriusBlaze(world);
			blaze.setPosition(pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D);
			world.spawnEntityInWorld(blaze);
		}
		world.setBlockToAir(pos);
		this.onBlockDestroyedByExplosion(world, pos, explosion);
	}
}