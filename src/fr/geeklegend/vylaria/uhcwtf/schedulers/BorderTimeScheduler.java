package fr.geeklegend.vylaria.uhcwtf.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import fr.geeklegend.vylaria.uhcwtf.UhcWTF;
import fr.geeklegend.vylaria.uhcwtf.config.Config;
import fr.geeklegend.vylaria.uhcwtf.game.BorderManager;
import fr.geeklegend.vylaria.uhcwtf.game.GameManager;

public class BorderTimeScheduler extends BukkitRunnable implements IScheduler
{
	
	private static int timer = Config.getDefaultConfig().getInt("schedulers.border.timer");
	private static boolean running;
	
	@Override
	public void run()
	{
		timer--;

		if (timer == 0)
		{
			stop();
			
			GameManager.setBorder(true);
			BorderManager.setMoved(true);

			new BorderScheduler().runTaskTimer(UhcWTF.getInstance(), 20L, 20L);
			
			Bukkit.broadcastMessage(Config.getDefaultConfig().getString("messages.border").replace("&", "§"));
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
		timer = Config.getDefaultConfig().getInt("schedulers.border.timer");
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
		BorderTimeScheduler.running = running;
	}
	
}
