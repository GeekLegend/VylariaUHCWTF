package fr.geeklegend.vylaria.uhcwtf.listeners.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import fr.geeklegend.vylaria.uhcwtf.game.states.GameStates;

public class BlockPlaceListener implements Listener
{

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event)
	{
		if (!GameStates.isState(GameStates.GAME))
		{
			event.setCancelled(true);
		}
	}
	
}
