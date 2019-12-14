package fr.geeklegend.vylaria.uhcwtf.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.geeklegend.vylaria.uhcwtf.config.Config;

public class InvincibilityScheduler extends BukkitRunnable implements IScheduler
{

	private static int timer = Config.getDefaultConfig().getInt("schedulers.invincibility.timer");
	private static boolean running;

	@Override
	public void run()
	{
		timer--;

		if (timer == 5 || timer == 4 || timer == 2 || timer == 1)
		{
			Bukkit.broadcastMessage(Config.getDefaultConfig().getString("messages.invincibility.while").replace("&", "§")
					.replace("%timer%", "" + timer));
		} else if (timer == 0)
		{
			stop();

			for (Player players : Bukkit.getOnlinePlayers())
			{
				players.playSound(players.getLocation(), Sound.IRONGOLEM_DEATH, 20.0f, 20.0f);
				players.sendMessage(
						Config.getDefaultConfig().getString("messages.invincibility.disabled").replace("&", "§"));
			}
		}
	}

	@Override
	public void stop()
	{
		cancel();
		reset();
		running = false;
	}

	@Override
	public void reset()
	{
		timer = Config.getDefaultConfig().getInt("schedulers.invincibility.timer");
	}

	public static int getTimer()
	{
		return timer;
	}

	public static boolean isRunning()
	{
		return running;
	}

	public static void setRunning(boolean running)
	{
		InvincibilityScheduler.running = running;
	}

}
