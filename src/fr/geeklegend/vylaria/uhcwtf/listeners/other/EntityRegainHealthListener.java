package fr.geeklegend.vylaria.uhcwtf.listeners.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;

import fr.geeklegend.vylaria.uhcwtf.game.GameState;

public class EntityRegainHealthListener implements Listener
{
	
	@EventHandler
	public void onEntityRegainHealth(EntityRegainHealthEvent event)
	{
		RegainReason reason = event.getRegainReason();
		
		if (GameState.isState(GameState.GAME))
		{
			if (reason == RegainReason.SATIATED || reason == RegainReason.REGEN)
			{
				event.setCancelled(true);
			}
		}
	}

}
