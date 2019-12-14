package fr.geeklegend.vylaria.uhcwtf.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import fr.geeklegend.vylaria.uhcwtf.game.GameState;

public class PlayerDropItemListener implements Listener
{
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event)
	{
		if (!GameState.isState(GameState.GAME))
		{
			event.setCancelled(true);
		}
	}

}
