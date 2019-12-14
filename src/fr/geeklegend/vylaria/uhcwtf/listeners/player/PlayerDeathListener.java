package fr.geeklegend.vylaria.uhcwtf.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.geeklegend.vylaria.uhcwtf.UhcWTF;
import fr.geeklegend.vylaria.uhcwtf.config.Config;
import fr.geeklegend.vylaria.uhcwtf.game.GameManager;
import fr.geeklegend.vylaria.uhcwtf.schedulers.WinScheduler;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;

public class PlayerDeathListener implements Listener
{

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event)
	{
		Player victim = event.getEntity();
		Player killer = victim.getKiller();

		event.setDeathMessage(null);

		if (victim instanceof Player)
		{
			GameManager.removePlayer(victim);
			
			if (GameManager.getPlayers().size() == 1)
			{
				if (!WinScheduler.isRunning())
				{
					WinScheduler winScheduler = new WinScheduler();
					winScheduler.setRunning(true);
					winScheduler.setPlayer(killer);
					winScheduler.runTaskTimer(UhcWTF.getInstance(), 20L, 20L);
				}
			}

			if (killer != null)
			{
				if (killer == victim)
				{
					Bukkit.broadcastMessage(Config.getDefaultConfig().getString("messages.deathnokiller")
							.replace("&", "§").replace("%victimname%", victim.getName()));
				} else
				{
					Bukkit.broadcastMessage(Config.getDefaultConfig().getString("messages.deathbykiller")
							.replace("&", "§").replace("%victimname%", victim.getName()).replace("&", "§")
							.replace("%killername%", killer.getName()));
				}
			} else
			{
				Bukkit.broadcastMessage(Config.getDefaultConfig().getString("messages.deathnokiller").replace("&", "§")
						.replace("%victimname%", victim.getName()));
			}

			Bukkit.getScheduler().scheduleSyncDelayedTask(UhcWTF.getInstance(), new Runnable()
			{
				public void run()
				{
					((CraftPlayer) victim).getHandle().playerConnection
							.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));
				}
			}, 1L);

			killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 20.0f, 20.0f);
		}
	}

}
