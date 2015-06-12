/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumWorldBlockLayer;

public class BlockIceMP extends BlockBreakableMP
{
	public BlockIceMP(Material material)
	{
		super(material);
		this.slipperiness = 0.98F;
		this.setHardness(0.5F);
		this.setResistance(0.1F);
		this.setTickRandomly(true);
		this.setStepSound(soundTypeGlass);
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.TRANSLUCENT;
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 0;
	}

	@Override
	public int getMobilityFlag()
	{
		return 0;
	}
}