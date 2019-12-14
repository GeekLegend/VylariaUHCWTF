package fr.geeklegend.vylaria.uhcwtf.listeners.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import fr.geeklegend.vylaria.uhcwtf.game.GameManager;
import fr.geeklegend.vylaria.uhcwtf.game.GameState;

public class PlayerRespawnListener implements Listener
{

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
		Player player = event.getPlayer();
		Player killer = player.getKiller();

		if (!GameState.isState(GameState.WAITING))
		{
			if (!GameManager.spectatorsContains(player))
			{
				GameManager.addSpectator(player);

				player.setGameMode(GameMode.SPECTATOR);
				player.setPlayerListName("§7§o[Spectateur] " + player.getName());
				player.getInventory().clear();

				if (killer != null)
				{
					event.setRespawnLocation(killer.getLocation());
				} else
				{
					List<Player> players = new ArrayList<Player>();

					for (Player pls : Bukkit.getOnlinePlayers())
					{
						players.add(pls);

						Player randomPlayer = players.get(new Random().nextInt(players.size()));

						event.setRespawnLocation(randomPlayer.getLocation());
					}
				}
			}
		}
	}

}
