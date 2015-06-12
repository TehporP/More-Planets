/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.tileentities;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.core.entities.GCCoreEntityCreeper;
import micdoodle8.mods.galacticraft.core.entities.GCCoreEntitySkeleton;
import micdoodle8.mods.galacticraft.core.entities.GCCoreEntitySpider;
import micdoodle8.mods.galacticraft.core.entities.GCCoreEntityZombie;
import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityDungeonSpawner;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import stevekung.mods.moreplanets.diona.entities.DionaEntityCreeperBoss;
import stevekung.mods.moreplanets.diona.entities.DionaEntitySpaceEnderman;

public class DionaTileEntityDungeonSpawner extends GCCoreTileEntityDungeonSpawner
{
	public DionaTileEntityDungeonSpawner()
	{
		super(DionaEntityCreeperBoss.class);
	}

	@Override
	public List<Class<? extends EntityLiving>> getDisabledCreatures()
	{
		List<Class<? extends EntityLiving>> list = new ArrayList<Class<? extends EntityLiving>>();
		list.add(GCCoreEntitySkeleton.class);
		list.add(GCCoreEntityZombie.class);
		list.add(GCCoreEntitySpider.class);
		list.add(GCCoreEntityCreeper.class);
		list.add(DionaEntitySpaceEnderman.class);
		return list;
	}

	@Override
	public void playSpawnSound(Entity entity)
	{
		this.worldObj.playSoundAtEntity(entity, "galacticraftcore:ambience.scaryscape", 9.0F, 1.4F);
	}
}