/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.kapteynb.worldgen;

public class KapteynBBiomeGenFlat extends KapteynBBiomeGenBase
{
	public KapteynBBiomeGenFlat(int par1)
	{
		super(par1);
		this.setBiomeName("Kapteyn B");
		this.setColor(11111111);
		this.minHeight = 1.5F;
		this.maxHeight = 0.4F;
	}
}