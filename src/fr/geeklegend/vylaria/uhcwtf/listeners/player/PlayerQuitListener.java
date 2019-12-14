package fr.geeklegend.vylaria.uhcwtf.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.geeklegend.vylaria.api.mysql.data.manager.PlayerDataManager;
import fr.geeklegend.vylaria.uhcwtf.UhcWTF;
import fr.geeklegend.vylaria.uhcwtf.config.Config;
import fr.geeklegend.vylaria.uhcwtf.game.GameManager;
import fr.geeklegend.vylaria.uhcwtf.game.GameState;

public class PlayerQuitListener implements Listener
{

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
	
		PlayerDataManager.savePlayerData(player);
		
		UhcWTF.getInstance().getScoreboardManager().onLogout(player);
		
		if (!GameState.isState(GameState.GAME))
		{
			event.setQuitMessage(Config.getDefaultConfig().getString("messages.quit").replace("&", "§")
					.replace("%playername%", player.getName())
					.replace("%online%", "" + (Bukkit.getOnlinePlayers().size() - 1))
					.replace("%maxonline%", "" + Bukkit.getMaxPlayers()));
		} else
		{
			if (GameManager.playersContains(player))
			{
				GameManager.removePlayer(player);				
			}
			
			event.setQuitMessage(null);
		}
	}
	
}
