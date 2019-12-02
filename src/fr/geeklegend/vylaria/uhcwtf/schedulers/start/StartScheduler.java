package fr.geeklegend.vylaria.uhcwtf.schedulers.start;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.geeklegend.vylaria.uhcwtf.UHCWTF;
import fr.geeklegend.vylaria.uhcwtf.game.manager.GameManager;
import fr.geeklegend.vylaria.uhcwtf.game.states.GameStates;
import fr.geeklegend.vylaria.uhcwtf.schedulers.pregame.PreGameScheduler;

public class StartScheduler extends BukkitRunnable
{

	private static int timer = 16;
	private static boolean running;

	@Override
	public void run()
	{
		timer--;

		for (Player players : Bukkit.getOnlinePlayers())
		{
			players.setLevel(timer);
		}

		if (Bukkit.getOnlinePlayers().size() < 1)
		{
			stop();
			GameStates.setState(GameStates.WAITING);

			Bukkit.broadcastMessage("§cIl n'y a pas assez de joueurs pour commencer la partie.");
		}

		if (timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1)
		{
			for (Player players : Bukkit.getOnlinePlayers())
			{
				players.playSound(players.getLocation(), Sound.NOTE_PLING, 20.0f, 20.0f);
				players.sendMessage("§7La partie commence dans §e" + timer + " seconde(s).");
			}
		} else if (timer == 0)
		{
			stop();

			GameStates.setState(GameStates.PREGAME);

			GameManager.preGameSetup();
			
			new PreGameScheduler().runTaskTimer(UHCWTF.getInstance(), 20L, 20L);

			for (Player players : Bukkit.getOnlinePlayers())
			{
				players.playSound(players.getLocation(), Sound.ENDERMAN_TELEPORT, 20.0f, 20.0f);
				players.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1));
			}
		}
	}

	public void stop()
	{
		cancel();
		running = false;
		timer = 16;

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
		StartScheduler.timer = timer;
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
