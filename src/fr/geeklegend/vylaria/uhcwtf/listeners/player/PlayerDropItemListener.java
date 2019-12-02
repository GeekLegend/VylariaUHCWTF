package fr.geeklegend.vylaria.uhcwtf.listeners.player;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import fr.geeklegend.vylaria.uhcwtf.game.states.GameStates;

public class PlayerDropItemListener implements Listener
{

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event)
	{
		Player player = event.getPlayer();
		Item item = event.getItemDrop();
		
		if (item != null)
		{
			if (!GameStates.isState(GameStates.GAME))
			{
				event.setCancelled(true);
			}
		}
	}

}
