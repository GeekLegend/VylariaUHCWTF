package fr.geeklegend.vylaria.uhcwtf.schedulers;

import org.bukkit.scheduler.BukkitRunnable;

import fr.geeklegend.vylaria.uhcwtf.game.BorderManager;

public class BorderScheduler extends BukkitRunnable implements IScheduler
{
	
	private static int timer;

	@Override
	public void run()
	{
		timer++;

		BorderManager.retract();
		
		if (BorderManager.getSize() <= 75)
		{
			stop();
		}
	}

	@Override
	public void stop()
	{
		cancel();
		reset();
	}

	@Override
	public void reset()
	{
		timer = 0;
	}
	
	public static int getTimer()
	{
		return timer;
	}
	
}
