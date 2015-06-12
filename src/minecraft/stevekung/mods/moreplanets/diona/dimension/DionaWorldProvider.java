/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.dimension;

import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.core.GCCoreConfigManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import stevekung.mods.moreplanets.diona.util.DionaConfigManager;
import stevekung.mods.moreplanets.diona.worldgen.DionaChunkProvider;
import stevekung.mods.moreplanets.diona.worldgen.DionaWorldChunkManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DionaWorldProvider extends WorldProvider implements IGalacticraftWorldProvider, ISolarLevel
{
	@Override
	public float[] calcSunriseSunsetColors(float var1, float var2)
	{
		return null;
	}

	@Override
	public float calculateCelestialAngle(long par1, float par3)
	{
		final int var4 = (int) (par1 % 150000L);
		float var5 = (var4 + par3) / 150000.0F - 0.25F;

		if (var5 < 0.0F)
		{
			++var5;
		}

		if (var5 > 1.0F)
		{
			--var5;
		}

		final float var6 = var5;
		var5 = 1.0F - (float) ((Math.cos(var5 * Math.PI) + 1.0D) / 2.0D);
		var5 = var6 + (var5 - var6) / 3.0F;
		return var5;
	}

	@Override
	public boolean canBlockFreeze(int x, int y, int z, boolean byWater)
	{
		return false;
	}

	@Override
	public boolean canCoordinateBeSpawn(int var1, int var2)
	{
		return true;
	}

	@Override
	public boolean canDoLightning(Chunk chunk)
	{
		return false;
	}

	@Override
	public boolean canDoRainSnowIce(Chunk chunk)
	{
		return false;
	}

	@Override
	public boolean canRespawnHere()
	{
		return !GCCoreConfigManager.forceOverworldRespawn;
	}

	@Override
	public boolean canSnowAt(int x, int y, int z)
	{
		return false;
	}

	@Override
	public boolean canSpaceshipTierPass(int tier)
	{
		return tier >= 3;
	}

	@Override
	public double getSolarEnergyMultiplier()
	{
		return 0.8D;
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new DionaChunkProvider(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
	}

	@Override
	protected void generateLightBrightnessTable()
	{
		final float var1 = 0.0F;

		for (int var2 = 0; var2 <= 15; ++var2)
		{
			final float var3 = 1.0F - var2 / 15.0F;
			this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
		}
	}

	@Override
	public int getAverageGroundLevel()
	{
		return 44;
	}

	@Override
	public String getDepartMessage()
	{
		return "Leaving Diona";
	}

	@Override
	public String getDimensionName()
	{
		return "Diona";
	}

	@Override
	public float getFallDamageModifier()
	{
		return 0.14F;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Vec3 getFogColor(float var1, float var2)
	{
		return this.worldObj.getWorldVec3Pool().getVecFromPool((double) 0F / 255F, (double) 0F / 255F, (double) 0F / 255F);
	}

	@Override
	public double getFuelUsageMultiplier()
	{
		return 1.10D;
	}

	@Override
	public float getGravity()
	{
		return 0.067F;
	}

	@Override
	public int getHeight()
	{
		return 800;
	}

	@Override
	public double getHorizon()
	{
		return 44.0D;
	}

	@Override
	public double getMeteorFrequency()
	{
		return 6.0D;
	}

	@Override
	public String getSaveFolder()
	{
		return "DIM" + DionaConfigManager.idDimensionDiona;
	}

	@Override
	public Vec3 getSkyColor(Entity cameraEntity, float partialTicks)
	{
		return this.worldObj.getWorldVec3Pool().getVecFromPool(0, 0, 0);
	}

	@Override
	public float getSoundVolReductionAmount()
	{
		return 22.0F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float par1)
	{
		final float var2 = this.worldObj.getCelestialAngle(par1);
		float var3 = 1.0F - (MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

		if (var3 < 0.0F)
		{
			var3 = 0.0F;
		}

		if (var3 > 1.0F)
		{
			var3 = 1.0F;
		}
		return var3 * var3 * 0.5F + 0.4F;
	}

	@Override
	public String getWelcomeMessage()
	{
		return "Entering Diona";
	}

	@Override
	public boolean isSkyColored()
	{
		return true;
	}

	@Override
	public boolean isSurfaceWorld()
	{
		return true;
	}

	@Override
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new DionaWorldChunkManager();
	}

	@Override
	public void setDimension(int var1)
	{
		this.dimensionId = var1;
		super.setDimension(var1);
	}

	@Override
	public void updateWeather()
	{
		this.worldObj.getWorldInfo().setRainTime(0);
		this.worldObj.getWorldInfo().setRaining(false);
		this.worldObj.getWorldInfo().setThunderTime(0);
		this.worldObj.getWorldInfo().setThundering(false);
	}
}