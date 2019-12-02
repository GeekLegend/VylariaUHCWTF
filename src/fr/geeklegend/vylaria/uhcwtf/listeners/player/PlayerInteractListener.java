package fr.geeklegend.vylaria.uhcwtf.listeners.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.geeklegend.vylaria.api.bungeecord.BungeecordManager;
import fr.geeklegend.vylaria.uhcwtf.game.states.GameStates;

public class PlayerInteractListener implements Listener
{

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack item = event.getItem();
		
		if (item != null)
		{
			if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
			{
				if (!GameStates.isState(GameStates.GAME))
				{
					if (item.getType() == Material.BED)
					{
						BungeecordManager.sendToServer(player, "hub");
					}
				}
			}
		}
	}
	
}
