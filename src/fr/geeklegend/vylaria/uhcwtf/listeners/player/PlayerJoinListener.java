package fr.geeklegend.vylaria.uhcwtf.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import fr.geeklegend.vylaria.api.VylariaAPI;
import fr.geeklegend.vylaria.api.mysql.data.manager.PlayerDataManager;
import fr.geeklegend.vylaria.api.utils.builder.ItemBuilder;
import fr.geeklegend.vylaria.uhcwtf.UHCWTF;
import fr.geeklegend.vylaria.uhcwtf.game.manager.GameManager;
import fr.geeklegend.vylaria.uhcwtf.game.states.GameStates;
import fr.geeklegend.vylaria.uhcwtf.schedulers.start.StartScheduler;

public class PlayerJoinListener implements Listener
{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();

		PlayerDataManager.loadPlayerData(player);

		UHCWTF.getInstance().getScoreboardManager().onLogin(player);

		if (!GameStates.isState(GameStates.GAME))
		{
			VylariaAPI.getInstance().getMySQL().addOnline("uhcwtf");

			GameManager.setHealthTabList();

			setup(player);

			if (Bukkit.getOnlinePlayers().size() == 1)
			{
				if (!StartScheduler.isRunning())
				{
					StartScheduler startScheduler = new StartScheduler();
					startScheduler.setRunning(true);
					startScheduler.runTaskTimer(UHCWTF.getInstance(), 20L, 20L);
				}
			}

			event.setJoinMessage("§e" + player.getName() + " §7a rejoint la partie ! §e("
					+ Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + ")");
		} else
		{
			event.setJoinMessage(null);
		}
	}

	private void setup(Player player)
	{
		player.setHealth(20.0D);
		player.setFoodLevel(20);
		player.setGameMode(GameMode.ADVENTURE);
		player.teleport(new Location(Bukkit.getWorld("world"), 16.5, 90, 5.5, 90, 0));
		player.getInventory().setHelmet(new ItemStack(Material.AIR));
		player.getInventory().setChestplate(new ItemStack(Material.AIR));
		player.getInventory().setLeggings(new ItemStack(Material.AIR));
		player.getInventory().setBoots(new ItemStack(Material.AIR));

		giveItems(player);
	}

	private void giveItems(Player player)
	{
		player.getInventory().clear();
		player.getInventory().setItem(8, new ItemBuilder(Material.BED).setName("§cQuitter").toItemStack());
	}

}
