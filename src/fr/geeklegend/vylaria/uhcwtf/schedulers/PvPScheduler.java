package fr.geeklegend.vylaria.uhcwtf.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.geeklegend.vylaria.uhcwtf.config.Config;
import fr.geeklegend.vylaria.uhcwtf.game.GameManager;

public class PvPScheduler extends BukkitRunnable implements IScheduler
{
	
	private static int timer = Config.getDefaultConfig().getInt("schedulers.pvp.timer");
	private static boolean running;
	
	@Override
	public void run()
	{
		timer--;

		if (timer == 0)
		{
			stop();
			
			GameManager.setPvP(true);
			
			for (Player players : Bukkit.getOnlinePlayers())
			{
				players.playSound(players.getLocation(), Sound.ANVIL_USE, 20.0f, 20.0f);
				players.sendMessage(Config.getDefaultConfig().getString("messages.pvp").replace("&", "§"));
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
		PvPScheduler.running = running;
	}
	
}
