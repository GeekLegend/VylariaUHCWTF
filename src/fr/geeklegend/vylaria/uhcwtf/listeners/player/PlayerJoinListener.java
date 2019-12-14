package fr.geeklegend.vylaria.uhcwtf.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import fr.geeklegend.vylaria.api.mysql.data.manager.PlayerDataManager;
import fr.geeklegend.vylaria.api.utils.builder.ItemBuilder;
import fr.geeklegend.vylaria.uhcwtf.UhcWTF;
import fr.geeklegend.vylaria.uhcwtf.config.Config;
import fr.geeklegend.vylaria.uhcwtf.game.GameManager;
import fr.geeklegend.vylaria.uhcwtf.game.GameState;
import fr.geeklegend.vylaria.uhcwtf.schedulers.StartScheduler;
import fr.geeklegend.vylaria.uhcwtf.tablist.Tablist;

public class PlayerJoinListener implements Listener
{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();

		PlayerDataManager.loadPlayerData(player);
		
		UhcWTF.getInstance().getScoreboardManager().onLogin(player);

		if (GameState.isState(GameState.WAITING))
		{
			setup(player);
			
			Tablist.setHealthScore(player);

			if (Bukkit.getOnlinePlayers().size() >= Config.getDefaultConfig().getInt("schedulers.start.minplayers"))
			{
				if (!StartScheduler.isRunning())
				{
					StartScheduler startScheduler = new StartScheduler();
					startScheduler.setRunning(true);
					startScheduler.runTaskTimer(UhcWTF.getInstance(), 20L, 20L);
				}
			}

			event.setJoinMessage(Config.getDefaultConfig().getString("messages.join").replace("&", "§")
					.replace("%playername%", player.getName())
					.replace("%online%", "" + Bukkit.getOnlinePlayers().size())
					.replace("%maxonline%", "" + Bukkit.getMaxPlayers()));
		} else
		{
			if (!GameManager.spectatorsContains(player))
			{
				GameManager.addSpectator(player);

				player.setGameMode(GameMode.SPECTATOR);
				player.setPlayerListName("§7§o[Spectateur] " + player.getName());
				player.teleport(new Location(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world.name")), 0,
						100, 0));
				player.sendMessage(Config.getDefaultConfig().getString("messages.alreadystart").replace("&", "§"));
			}

			event.setJoinMessage(null);
		}
	}

	private void setup(Player player)
	{
		player.setHealth(20.0);
		player.setFoodLevel(20);
		player.setLevel(0);
		player.setStatistic(Statistic.PLAYER_KILLS, 0);
		player.setGameMode(GameMode.valueOf(Config.getDefaultConfig().getString("setups.join.gamemode").toUpperCase()));
		
		Location location = new Location(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world.name")),
				Config.getDefaultConfig().getDouble("setups.join.spawn.x") + 13,
				Config.getDefaultConfig().getDouble("setups.join.spawn.y"),
				Config.getDefaultConfig().getDouble("setups.join.spawn.z") - 13,
				Config.getDefaultConfig().getInt("setups.join.spawn.yaw"),
				Config.getDefaultConfig().getInt("setups.join.spawn.pitch"));
		
		player.teleport(location);
		
		GameManager.addPreviousLocation(player, location);
		
		player.getInventory().setHelmet(new ItemStack(Material.AIR));
		player.getInventory().setChestplate(new ItemStack(Material.AIR));
		player.getInventory().setLeggings(new ItemStack(Material.AIR));
		player.getInventory().setBoots(new ItemStack(Material.AIR));

		giveItems(player);
	}

	private void giveItems(Player player)
	{
		player.getInventory().clear();
		player.getInventory().setItem(Config.getDefaultConfig().getInt("setups.join.items.leave.slot"),
				new ItemBuilder(Material.valueOf(Config.getDefaultConfig().getString("setups.join.items.leave.material")
						.toUpperCase().replace(" ", "_"))).setName(
								Config.getDefaultConfig().getString("setups.join.items.leave.name").replace("&", "§"))
								.toItemStack());
	}

}
