package fr.geeklegend.vylaria.uhcwtf.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.geeklegend.vylaria.api.VylariaAPI;
import fr.geeklegend.vylaria.uhcwtf.UhcWTF;
import fr.geeklegend.vylaria.uhcwtf.config.Config;

public class WinManager
{

	private Player player;

	public WinManager(Player player)
	{
		this.player = player;
	}

	public void check()
	{
		GameState.setState(GameState.FINISH);
		
		GameManager.clearEntities(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world.name")));

		Bukkit.getScheduler().cancelAllTasks();
		Bukkit.broadcastMessage(Config.getDefaultConfig().getString("messages.win").replace("&", "§")
				.replace("%playername%", player.getName()));

		Bukkit.getScheduler().runTaskLater(UhcWTF.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				for (Player players : Bukkit.getOnlinePlayers())
				{
					VylariaAPI.getInstance().getBungeeChannelApi().connect(players, "lobby");
				}
			}
		}, 100L);

		Bukkit.getScheduler().runTaskLater(UhcWTF.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				Bukkit.getServer().shutdown();
			}
		}, 120L);
	}

}
