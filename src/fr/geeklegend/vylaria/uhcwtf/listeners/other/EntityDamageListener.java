package fr.geeklegend.vylaria.uhcwtf.listeners.other;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fr.geeklegend.vylaria.uhcwtf.game.GameManager;
import fr.geeklegend.vylaria.uhcwtf.game.GameState;
import fr.geeklegend.vylaria.uhcwtf.schedulers.InvincibilityScheduler;
import fr.geeklegend.vylaria.uhcwtf.schedulers.PvPScheduler;

public class EntityDamageListener implements Listener
{

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event)
	{
		Entity entity = event.getEntity();
		DamageCause damageCause = event.getCause();

		if (GameState.isState(GameState.WAITING) || GameState.isState(GameState.PREGAME))
		{
			event.setCancelled(true);
		} else if (GameState.isState(GameState.GAME))
		{
			if (InvincibilityScheduler.isRunning())
			{
				event.setCancelled(true);
			} else
			{
				if (!GameManager.isPvP())
				{
					if (damageCause == DamageCause.LAVA || damageCause == DamageCause.FIRE
							|| damageCause == DamageCause.FIRE_TICK)
					{
						event.setCancelled(true);
					}
				}
				if (damageCause != DamageCause.ENTITY_ATTACK || !(entity instanceof Player))
				{
					return;
				} else
				{
					if (PvPScheduler.isRunning())
					{
						event.setCancelled(true);
					} 
				}
			}
		}
	}

}
