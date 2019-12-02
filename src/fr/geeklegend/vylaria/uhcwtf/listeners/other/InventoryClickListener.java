package fr.geeklegend.vylaria.uhcwtf.listeners.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.geeklegend.vylaria.uhcwtf.game.states.GameStates;

public class InventoryClickListener implements Listener
{

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		if (!GameStates.isState(GameStates.GAME))
		{
			event.setCancelled(true);
		}
	}
	
}
