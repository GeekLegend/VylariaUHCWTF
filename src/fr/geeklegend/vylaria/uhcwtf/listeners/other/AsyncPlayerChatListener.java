package fr.geeklegend.vylaria.uhcwtf.listeners.other;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.geeklegend.vylaria.uhcwtf.game.GameManager;
import fr.geeklegend.vylaria.uhcwtf.game.GameState;

public class AsyncPlayerChatListener implements Listener
{
	
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event)
	{
		Player player = event.getPlayer();
		String message = event.getMessage();
		
		if (!GameState.isState(GameState.WAITING))
		{
			if (GameManager.spectatorsContains(player))
			{
				for (Player spectators : GameManager.getSpectators())
				{
					spectators.sendMessage("§7§o[Spectateur] " + player.getName() + ": " + message);
				}
				
				event.setCancelled(true);
			}
		} 
	}

}
