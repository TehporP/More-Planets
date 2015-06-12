/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.dimension;

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
import stevekung.mods.moreplanets.core.world.IUltraVioletLevel;
import stevekung.mods.moreplanets.planets.kapteynb.worldgen.ChunkProviderKapteynB;
import stevekung.mods.moreplanets.planets.kapteynb.worldgen.WorldChunkManagerKapteynB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderKapteynB extends WorldProviderSpace implements IGalacticraftWorldProvider, ISolarLevel, IUltraVioletLevel, IIceCrystalMeteor
{
	@Override
	public Vector3 getFogColor()
	{
		float f = 1.1F - this.getStarBrightness(1.0F);
		return new Vector3(135F / 255F * f, 145F / 255F * f, 170F / 255F * f);
	}

	@Override
	public Vector3 getSkyColor()
	{
		float f = 1.1F - this.getStarBrightness(1.0F);
		return new Vector3(172 / 255F * f, 193 / 255F * f, 210 / 255F * f);
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
		return 246000L;
	}

	@Override
	public boolean shouldForceRespawn()
	{
		return !ConfigManagerCore.forceOverworldRespawn;
	}

	@Override
	public Class<? extends IChunkProvider> getChunkProviderClass()
	{
		return ChunkProviderKapteynB.class;
	}

	@Override
	public Class<? extends WorldChunkManager> getWorldChunkManagerClass()
	{
		return WorldChunkManagerKapteynB.class;
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
			f2 = 1.0F;
		}
		return f2 * f2 * 0.7F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getSunBrightness(float par1)
	{
		float f1 = this.worldObj.getCelestialAngle(1.0F);
		float f2 = -0.55F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.2F);

		if (f2 < 0.0F)
		{
			f2 = 0.0F;
		}
		if (f2 > 1.0F)
		{
			f2 = 1.05F;
		}
		f2 = 1.0F - f2;
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
	public boolean canCoordinateBeSpawn(int var1, int var2)
	{
		return true;
	}

	@Override
	public double getSolarEnergyMultiplier()
	{
		return 4.0D;
	}

	@Override
	public float getGravity()
	{
		return 0.032F;
	}

	@Override
	public double getMeteorFrequency()
	{
		return 5.0D;
	}

	@Override
	public double getFuelUsageMultiplier()
	{
		return 0.9D;
	}

	@Override
	public boolean canSpaceshipTierPass(int tier)
	{
		return tier >= 7;
	}

	@Override
	public float getFallDamageModifier()
	{
		return 0.5F;
	}

	@Override
	public float getSoundVolReductionAmount()
	{
		return 15.0F;
	}

	@Override
	public CelestialBody getCelestialBody()
	{
		return MorePlanetsCore.kapteynB;
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
			return 3.0F;
		}
		else
		{
			return -10.0F;
		}
	}

	@Override
	public float getWindLevel()
	{
		return 0.5F;
	}

	@Override
	public double getUltraVioletEnergyMultiplie()
	{
		return 4.0D;
	}

	@Override
	public double getIceCrystalMeteorFrequency()
	{
		return 3.5D;
	}
}