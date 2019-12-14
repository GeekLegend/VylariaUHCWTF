package fr.geeklegend.vylaria.uhcwtf.listeners.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.geeklegend.vylaria.uhcwtf.game.GameState;

public class InventoryClickListener implements Listener
{
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		if (!GameState.isState(GameState.GAME))
		{
			event.setCancelled(true);
		}
	}

}
