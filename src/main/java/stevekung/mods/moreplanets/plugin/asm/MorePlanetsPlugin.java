/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.plugin.asm;

import java.util.Map;

import net.minecraftforge.fml.relauncher.FMLRelaunchLog;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

import org.apache.logging.log4j.Level;

@TransformerExclusions("stevekung.mods.moreplanets.plugin.asm")
public class MorePlanetsPlugin implements IFMLLoadingPlugin
{
	@Override
	public String[] getASMTransformerClass()
	{
		FMLRelaunchLog.log("More Planets", Level.INFO, "Calling tweak class " + MorePlanetsPlugin.class.getName());
		return new String[] { MorePlanetsTransformer.class.getName() };
	}

	@Override
	public String getModContainerClass()
	{
		return null;
	}

	@Override
	public String getSetupClass()
	{
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {}

	@Override
	public String getAccessTransformerClass()
	{
		FMLRelaunchLog.log("More Planets", Level.INFO, "Calling access transformer class " + MorePlanetsAccessTransformer.class.getName());
		return MorePlanetsAccessTransformer.class.getName();
	}
}