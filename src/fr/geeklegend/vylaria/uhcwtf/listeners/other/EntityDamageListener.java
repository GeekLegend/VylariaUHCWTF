package fr.geeklegend.vylaria.uhcwtf.listeners.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.geeklegend.vylaria.uhcwtf.game.states.GameStates;
import fr.geeklegend.vylaria.uhcwtf.schedulers.pvp.PvPScheduler;

public class EntityDamageListener implements Listener
{
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event)
	{
		if (!GameStates.isState(GameStates.GAME))
		{
			event.setCancelled(true);
		} else
		{
			if (PvPScheduler.isRunning())
			{
				event.setCancelled(true);
			}
		}
	}
	
}
