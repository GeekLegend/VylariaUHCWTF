package fr.geeklegend.vylaria.uhcwtf.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.geeklegend.vylaria.api.VylariaAPI;
import fr.geeklegend.vylaria.api.mysql.data.manager.PlayerDataManager;
import fr.geeklegend.vylaria.uhcwtf.UHCWTF;
import fr.geeklegend.vylaria.uhcwtf.game.states.GameStates;

public class PlayerQuitListener implements Listener
{

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		
		PlayerDataManager.savePlayerData(player);
		
		UHCWTF.getInstance().getScoreboardManager().onLogout(player);

		if (!GameStates.isState(GameStates.GAME))
		{
			VylariaAPI.getInstance().getMySQL().removeOnline("uhcwtf");
			
			event.setQuitMessage("§e" + player.getName() + " §7a quitter la partie ! §e(" + (Bukkit.getOnlinePlayers().size() - 1) + "/" + Bukkit.getMaxPlayers() + ")");
		}
	}
	
}
