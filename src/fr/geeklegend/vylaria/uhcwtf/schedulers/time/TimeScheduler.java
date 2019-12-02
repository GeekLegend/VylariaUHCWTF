package fr.geeklegend.vylaria.uhcwtf.schedulers.time;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TimeScheduler extends BukkitRunnable
{

	private static int timer = 0;
	private static boolean running;

	@Override
	public void run()
	{
		timer++;

		if (timer == 900 || timer == 960 || timer == 1020 || timer == 1080 || timer == 1140)
		{
			for (Player players : Bukkit.getOnlinePlayers())
			{
				players.sendMessage("§7PvP actif dans §e" + timer + " seconde(s).");
				players.playSound(players.getLocation(), Sound.NOTE_PLING, 20.0f, 20.0f);
			}
		} else if (timer == 1200)
		{
			for (Player players : Bukkit.getOnlinePlayers())
			{
				players.sendMessage("§ePvp activé ! §7Préparez votre épée !");
				players.playSound(players.getLocation(), Sound.EXPLODE, 20.0f, 20.0f);
			}
		}
	}

	public void stop()
	{
		cancel();
		running = false;
		timer = 0;
	}

	public static int getTimer()
	{
		return timer;
	}

	public static void setTimer(int timer)
	{
		TimeScheduler.timer = timer;
	}

	public static boolean isRunning()
	{
		return running;
	}

	public static void setRunning(boolean running)
	{
		TimeScheduler.running = running;
	}

}
