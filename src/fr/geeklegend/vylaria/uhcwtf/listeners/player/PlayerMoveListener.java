package fr.geeklegend.vylaria.uhcwtf.listeners.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.geeklegend.vylaria.uhcwtf.game.GameManager;
import fr.geeklegend.vylaria.uhcwtf.game.GameState;

public class PlayerMoveListener implements Listener
{

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event)
	{
		Player player = event.getPlayer();

		if (GameState.isState(GameState.WAITING) || GameState.isState(GameState.PREGAME))
		{
			if (player.getLocation().getY() < 140)
			{
				player.teleport(GameManager.getPreviousLocation(player));
			}
		}
	}

}
