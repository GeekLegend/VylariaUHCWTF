package fr.geeklegend.vylaria.uhcwtf.listeners.other;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fr.geeklegend.vylaria.uhcwtf.game.GameManager;
import fr.geeklegend.vylaria.uhcwtf.game.GameState;

public class EntityDamageByEntityListener implements Listener
{

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
	{
		Entity damaged = event.getEntity();
		Entity damager = event.getDamager();
		DamageCause damageCause = event.getCause();

		if (!GameState.isState(GameState.WAITING))
		{
			if (!GameManager.isPvP())
			{
				if (damager instanceof FishHook)
				{
					event.setCancelled(true);
				} else if (damager instanceof Arrow)
				{
					Arrow arrow = (Arrow) damager;

					if (arrow.getShooter() instanceof Player)
					{
						Entity entityHit = event.getEntity();

						if (entityHit instanceof Player)
						{
							event.setCancelled(true);
						}
					}
				}
			}
		}
	}

}
