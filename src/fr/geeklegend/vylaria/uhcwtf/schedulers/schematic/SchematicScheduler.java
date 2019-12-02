package fr.geeklegend.vylaria.uhcwtf.schedulers.schematic;

import org.bukkit.scheduler.BukkitRunnable;

import fr.geeklegend.vylaria.uhcwtf.utils.world.WorldUtils;

public class SchematicScheduler extends BukkitRunnable
{
	
	private static int timer = 11;
	private static boolean running;

	@Override
	public void run()
	{
		timer--;
		
		if (timer == 10)
		{
			WorldUtils.loadSchematic(null, "spawn", true);
		} else if (timer == 0)
		{
			stop();
		}
	}
	
	public void stop()
	{
		cancel();
		running = false;
		timer = 11;
	}

	public static int getTimer()
	{
		return timer;
	}

	public static void setTimer(int timer)
	{
		SchematicScheduler.timer = timer;
	}

	public static boolean isRunning()
	{
		return running;
	}

	public static void setRunning(boolean running)
	{
		SchematicScheduler.running = running;
	}

}
