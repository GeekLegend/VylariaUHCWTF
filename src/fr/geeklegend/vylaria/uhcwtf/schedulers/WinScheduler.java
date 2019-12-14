package fr.geeklegend.vylaria.uhcwtf.schedulers;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.geeklegend.vylaria.uhcwtf.game.WinManager;

public class WinScheduler extends BukkitRunnable
{

	private static Player player;
	private static int timer = 1;
	private static boolean running;

	@Override
	public void run()
	{
		timer--;

		if (timer == 0)
		{
			stop();

			new WinManager(player).check();
		}
	}

	public void stop()
	{
		cancel();
		running = false;
		timer = 1;
	}

	public static void setPlayer(Player player)
	{
		WinScheduler.player = player;
	}

	public static int getTimer()
	{
		return timer;
	}

	public static void setTimer(int timer)
	{
		WinScheduler.timer = timer;
	}

	public static boolean isRunning()
	{
		return running;
	}

	public static void setRunning(boolean running)
	{
		WinScheduler.running = running;
	}

}
