/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.phobos.dimension;

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
import stevekung.mods.moreplanets.moons.phobos.worldgen.ChunkProviderPhobos;
import stevekung.mods.moreplanets.moons.phobos.worldgen.WorldChunkManagerPhobos;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderPhobos extends WorldProviderSpace implements IGalacticraftWorldProvider, ISolarLevel, IUltraVioletLevel
{
	@Override
	public Vector3 getFogColor()
	{
		return new Vector3(0, 0, 0);
	}

	@Override
	public Vector3 getSkyColor()
	{
		return new Vector3(0, 0, 0);
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
		return 9180L;
	}

	@Override
	public boolean shouldForceRespawn()
	{
		return !ConfigManagerCore.forceOverworldRespawn;
	}

	@Override
	public Class<? extends IChunkProvider> getChunkProviderClass()
	{
		return ChunkProviderPhobos.class;
	}

	@Override
	public Class<? extends WorldChunkManager> getWorldChunkManagerClass()
	{
		return WorldChunkManagerPhobos.class;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float par1)
	{
		float var2 = this.worldObj.getCelestialAngle(par1);
		float var3 = 1.0F - (MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

		if (var3 < 0.0F)
		{
			var3 = 0.0F;
		}
		if (var3 > 1.0F)
		{
			var3 = 1.0F;
		}
		return var3 * var3 * 0.5F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getSunBrightness(float par1)
	{
		float f1 = this.worldObj.getCelestialAngle(1.0F);
		float f2 = 0.85F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.2F);

		if (f2 < 0.0F)
		{
			f2 = 0.0F;
		}
		if (f2 > 1.0F)
		{
			f2 = 1.0F;
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
		return 6.0D;
	}

	@Override
	public float getGravity()
	{
		return 0.060F;
	}

	@Override
	public double getMeteorFrequency()
	{
		return 5.5D;
	}

	@Override
	public double getFuelUsageMultiplier()
	{
		return 0.9D;
	}

	@Override
	public boolean canSpaceshipTierPass(int tier)
	{
		return tier >= 2;
	}

	@Override
	public float getFallDamageModifier()
	{
		return 0.30F;
	}

	@Override
	public float getSoundVolReductionAmount()
	{
		return 20.0F;
	}

	@Override
	public CelestialBody getCelestialBody()
	{
		return MorePlanetsCore.phobos;
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
			return 2.0F;
		}
		else
		{
			return -1.0F;
		}
	}

	@Override
	public float getWindLevel()
	{
		return 0F;
	}

	@Override
	public double getUltraVioletEnergyMultiplie()
	{
		return 5.0D;
	}
}