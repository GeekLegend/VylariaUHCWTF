package fr.geeklegend.vylaria.uhcwtf.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.geeklegend.vylaria.uhcwtf.UhcWTF;
import fr.geeklegend.vylaria.uhcwtf.config.Config;
import fr.geeklegend.vylaria.uhcwtf.game.BorderManager;
import fr.geeklegend.vylaria.uhcwtf.game.CageManager;
import fr.geeklegend.vylaria.uhcwtf.game.GameManager;
import fr.geeklegend.vylaria.uhcwtf.game.GameState;

public class StartScheduler extends BukkitRunnable implements IScheduler
{

	private static int timer = Config.getDefaultConfig().getInt("schedulers.start.timer");
	private static boolean running;

	@Override
	public void run()
	{
		timer--;

		if (timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1)
		{
			for (Player players : Bukkit.getOnlinePlayers())
			{
				players.playSound(players.getLocation(), Sound.NOTE_PLING, 20.0f, 20.0f);
			}
			
			Bukkit.broadcastMessage(Config.getDefaultConfig().getString("messages.schedulers.start.while")
					.replace("&", "§").replace("%timer%", "" + timer));
		} else if (timer == 0)
		{
			stop();
		
			GameState.setState(GameState.PREGAME);

			BorderManager.load();
			
			CageManager.remove(true);
			
			new CageScheduler().runTaskTimer(UhcWTF.getInstance(), 20L, 20L);
			
			for (Player players : Bukkit.getOnlinePlayers())
			{
				GameManager.removePreviousLocation(players);				
			}
			
			GameManager.preGameSetup();
		}

		for (Player players : Bukkit.getOnlinePlayers())
		{
			players.setLevel(timer);
		}

		if (Bukkit.getOnlinePlayers().size() < Config.getDefaultConfig().getInt("schedulers.start.minplayers"))
		{
			stop();

			GameState.setState(GameState.WAITING);
			
			Bukkit.broadcastMessage(
					Config.getDefaultConfig().getString("messages.schedulers.start.noenought").replace("&", "§"));
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
		timer = Config.getDefaultConfig().getInt("schedulers.start.timer");
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
		StartScheduler.running = running;
	}

}
