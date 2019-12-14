package fr.geeklegend.vylaria.uhcwtf.listeners.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import fr.geeklegend.vylaria.uhcwtf.game.GameState;

public class BlockPlaceListener implements Listener
{

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event)
	{
		if (!GameState.isState(GameState.GAME))
		{
			event.setCancelled(true);
		}
	}
	
}
