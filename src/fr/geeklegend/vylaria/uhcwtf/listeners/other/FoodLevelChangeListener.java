package fr.geeklegend.vylaria.uhcwtf.listeners.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import fr.geeklegend.vylaria.uhcwtf.game.states.GameStates;

public class FoodLevelChangeListener implements Listener
{
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event)
	{
		if (!GameStates.isState(GameStates.GAME))
		{
			event.setCancelled(true);
		}
	}

}
