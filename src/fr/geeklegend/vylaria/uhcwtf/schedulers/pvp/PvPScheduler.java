package fr.geeklegend.vylaria.uhcwtf.schedulers.pvp;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.geeklegend.vylaria.uhcwtf.game.states.GameStates;

public class PvPScheduler extends BukkitRunnable
{

	private static int timer = 11;
	private static boolean running;

	@Override
	public void run()
	{
		timer--;

		for (Player players : Bukkit.getOnlinePlayers())
		{
			players.setLevel(timer);
		}
		
		if (timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1)
		{
			for (Player players : Bukkit.getOnlinePlayers())
			{
				players.playSound(players.getLocation(), Sound.NOTE_PLING, 20.0f, 20.0f);
			}
		} else if (timer == 0)
		{
			stop();
			GameStates.setState(GameStates.GAME);
			
			for (Player players : Bukkit.getOnlinePlayers())
			{
				players.playSound(players.getLocation(), Sound.LEVEL_UP, 20.0f, 20.0f);
			}
		}
	}

	public void stop()
	{
		cancel();
		running = false;
		timer = 11;

		for (Player players : Bukkit.getOnlinePlayers())
		{
			players.setLevel(0);
		}
	}

	public static int getTimer()
	{
		return timer;
	}

	public static void setTimer(int timer)
	{
		PvPScheduler.timer = timer;
	}

	public static boolean isRunning()
	{
		return running;
	}

	public static void setRunning(boolean running)
	{
		PvPScheduler.running = running;
	}

}
