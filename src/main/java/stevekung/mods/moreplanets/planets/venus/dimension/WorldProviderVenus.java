/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.dimension;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.world.ILightningStorm;
import stevekung.mods.moreplanets.core.world.IUltraVioletLevel;
import stevekung.mods.moreplanets.planets.venus.worldgen.ChunkProviderVenus;
import stevekung.mods.moreplanets.planets.venus.worldgen.WorldChunkManagerVenus;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderVenus extends WorldProviderSpace implements IGalacticraftWorldProvider, ISolarLevel, IUltraVioletLevel, ILightningStorm
{
	@Override
	public Vector3 getFogColor()
	{
		float f = 1.0F - this.getStarBrightness(1.0F);
		return new Vector3(109F / 255F * f, 86F / 255F * f, 45F / 255F * f);
	}

	@Override
	public Vector3 getSkyColor()
	{
		float f = 1.0F - this.getStarBrightness(1.0F);
		return new Vector3(180 / 255F * f, 134 / 255F * f, 59 / 255F * f);
	}

	@Override
	public boolean canRainOrSnow()
	{
		return false;
	}

	@Override
	public boolean hasSunset()
	{
		return false;
	}

	@Override
	public long getDayLength()
	{
		return 5832000L;
	}

	@Override
	public boolean shouldForceRespawn()
	{
		return !ConfigManagerCore.forceOverworldRespawn;
	}

	@Override
	public Class<? extends IChunkProvider> getChunkProviderClass()
	{
		return ChunkProviderVenus.class;
	}

	@Override
	public Class<? extends WorldChunkManager> getWorldChunkManagerClass()
	{
		return WorldChunkManagerVenus.class;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float par1)
	{
		float f1 = this.worldObj.getCelestialAngle(par1);
		float f2 = 1.0F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

		if (f2 < 0.0F)
		{
			f2 = 0.0F;
		}
		if (f2 > 1.0F)
		{
			f2 = 0.7F;
		}
		return f2 * f2 * 0.75F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getSunBrightness(float par1)
	{
		float f1 = this.worldObj.getCelestialAngle(1.0F);
		float f2 = 1.25F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.2F);

		if (f2 < 0.0F)
		{
			f2 = 0.0F;
		}
		if (f2 > 1.0F)
		{
			f2 = 1.0F;
		}
		f2 = 1.2F - f2;
		return f2 * 1.0F;
	}

	@Override
	public double getHorizon()
	{
		return 44.0D;
	}

	@Override
	public int getAverageGroundLevel()
	{
		return 44;
	}

	@Override
	public boolean canCoordinateBeSpawn(int x, int z)
	{
		return true;
	}

	@Override
	public double getSolarEnergyMultiplier()
	{
		return 7.5D;
	}

	@Override
	public float getGravity()
	{
		return 0.053F;
	}

	@Override
	public double getMeteorFrequency()
	{
		return 100.0D;
	}

	@Override
	public double getFuelUsageMultiplier()
	{
		return 0.9D;
	}

	@Override
	public boolean canSpaceshipTierPass(int tier)
	{
		return tier >= 3;
	}

	@Override
	public float getFallDamageModifier()
	{
		return 0.42F;
	}

	@Override
	public float getSoundVolReductionAmount()
	{
		return 10.0F;
	}

	@Override
	public CelestialBody getCelestialBody()
	{
		return MorePlanetsCore.venus;
	}

	@Override
	public boolean hasBreathableAtmosphere()
	{
		return false;
	}

	@Override
	public float getThermalLevelModifier()
	{
		if (this.isDaytime())
		{
			return 15.0F;
		}
		else
		{
			return 7.5F;
		}
	}

	@Override
	public float getWindLevel()
	{
		return 1.5F;
	}

	@Override
	public double getUltraVioletEnergyMultiplie()
	{
		return 30.0D;
	}

	@Override
	public double getLightningStormFrequency()
	{
		return 0.5D;
	}
}