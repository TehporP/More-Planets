/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent.Serializer;
import net.minecraft.util.StatCollector;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ThreadVersionCheckMP extends Thread
{
	public static ThreadVersionCheckMP INSTANCE = new ThreadVersionCheckMP();
	private int count = 0;
	private String URL = "http://minecraftforum.net/forums/thread/2358057";

	public static int remoteMajVer;
	public static int remoteMinVer;
	public static int remoteBuildVer;
	public static String information;

	public ThreadVersionCheckMP()
	{
		super("More Planets Version Check Thread");
	}

	public static void startCheck()
	{
		Thread thread = new Thread(ThreadVersionCheckMP.INSTANCE);
		thread.start();
	}

	@Override
	public void run()
	{
		Side sideToCheck = FMLCommonHandler.instance().getSide();

		if (sideToCheck == null)
		{
			return;
		}

		while (this.count < 3 && remoteBuildVer == 0)
		{
			try
			{
				URL url = new URL("https://raw.githubusercontent.com/SteveKunG/More-Planets/master/version.txt");
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				http.addRequestProperty("User-Agent", "Mozilla/4.76");
				BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
				String str;
				String str2[] = null;

				while ((str = in.readLine()) != null)
				{
					if (str.contains("Version"))
					{
						str = str.replace("Version=", "");
						str2 = str.split("#");

						if (str2.length == 3)
						{
							remoteMajVer = Integer.parseInt(str2[0]);
							remoteMinVer = Integer.parseInt(str2[1]);
							remoteBuildVer = Integer.parseInt(str2[2]);
						}

						if (remoteMajVer > MorePlanetsCore.major_version || remoteMajVer == MorePlanetsCore.major_version && remoteMinVer > MorePlanetsCore.minor_version || remoteMajVer == MorePlanetsCore.major_version && remoteMinVer == MorePlanetsCore.minor_version && remoteBuildVer > MorePlanetsCore.build_version)
						{
							Thread.sleep(5000);

							if (sideToCheck.equals(Side.CLIENT))
							{
								if (ConfigManagerMP.enableClientVersionCheck == true)
								{
									FMLClientHandler.instance().getClient().thePlayer.addChatMessage(Serializer.func_150699_a("[{text:\"" + EnumChatFormatting.GRAY + "New \",extra:[{text:\"" + EnumChatFormatting.AQUA + "More Planets\"},{text:\"" + EnumChatFormatting.GRAY + " version available!\"},{text:\"" + EnumChatFormatting.GREEN + EnumChatFormatting.BOLD + " v" + String.valueOf(remoteMajVer) + "." + String.valueOf(remoteMinVer) + "." + String.valueOf(remoteBuildVer) + " \"},{text:\"" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + "CLICK HERE!\",hoverEvent:{action:show_text,value:\"" + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "Download Latest Version\"},clickEvent:{action:open_url,value:\"" + this.URL + "\"}}]}]"));
								}
							}
							else if (sideToCheck.equals(Side.SERVER))
							{
								if (ConfigManagerMP.enableServerVersionCheck == true)
								{
									MorePlanetsCore.info("New version available! v" + String.valueOf(remoteMajVer) + "." + String.valueOf(remoteMinVer) + "." + String.valueOf(remoteBuildVer) + " ");
								}
							}
						}
					}
				}
			}
			catch (Exception e) {}

			if (remoteBuildVer == 0)
			{
				try
				{
					MorePlanetsCore.severe(StatCollector.translateToLocal("mp.failed.name"));
					Thread.sleep(15000);
				}
				catch (InterruptedException e) {}
			}
			else
			{
				MorePlanetsCore.info(StatCollector.translateToLocal("mp.success.name") + " " + remoteMajVer + "." + remoteMinVer + "." + remoteBuildVer);
			}
			this.count++;
		}
	}
}
