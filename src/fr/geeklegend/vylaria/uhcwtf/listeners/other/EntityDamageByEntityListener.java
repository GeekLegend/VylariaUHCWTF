package fr.geeklegend.vylaria.uhcwtf.listeners.other;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import fr.geeklegend.vylaria.uhcwtf.game.manager.GameManager;

public class EntityDamageByEntityListener implements Listener
{

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
	{
		Entity damaged = event.getEntity();
		Entity damager = event.getDamager();
		double damage = event.getDamage();

		if (damaged instanceof Creature)
		{
			if (damager instanceof Player)
			{
				Creature victim = (Creature) damaged;
				Player killer = (Player) damager;

				if (victim.getHealth() <= damage)
				{
					victim.remove();
					victim.getWorld().dropItemNaturally(victim.getLocation(), GameManager.getRandomItem());
				}
			}
		}
	}

}
