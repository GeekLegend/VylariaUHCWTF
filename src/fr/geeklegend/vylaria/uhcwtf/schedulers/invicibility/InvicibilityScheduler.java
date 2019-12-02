package fr.geeklegend.vylaria.uhcwtf.schedulers.invicibility;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class InvicibilityScheduler extends BukkitRunnable
{

	private static int timer = 31;
	private static boolean running;

	@Override
	public void run()
	{
		timer--;

		if (timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1)
		{
			for (Player players : Bukkit.getOnlinePlayers())
			{
				players.sendMessage("§7L'invincibilité prend fin dans §e" + timer + " seconde(s).");
			}
		} else if (timer == 0)
		{
			stop();
			
			for (Player players : Bukkit.getOnlinePlayers())
			{
				players.sendMessage("§7Vous n'êtes plus invincible.");
			}
		}
	}

	public void stop()
	{
		cancel();
		running = false;
		timer = 31;
	}

	public static int getTimer()
	{
		return timer;
	}

	public static void setTimer(int timer)
	{
		InvicibilityScheduler.timer = timer;
	}

	public static boolean isRunning()
	{
		return running;
	}

	public static void setRunning(boolean running)
	{
		InvicibilityScheduler.running = running;
	}

}
