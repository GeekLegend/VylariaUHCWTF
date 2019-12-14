package fr.geeklegend.vylaria.uhcwtf.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.geeklegend.vylaria.uhcwtf.UhcWTF;
import fr.geeklegend.vylaria.uhcwtf.config.Config;
import fr.geeklegend.vylaria.uhcwtf.game.CageManager;
import fr.geeklegend.vylaria.uhcwtf.game.GameManager;
import fr.geeklegend.vylaria.uhcwtf.game.GameState;

public class CageScheduler extends BukkitRunnable implements IScheduler
{

	private static int timer = Config.getDefaultConfig().getInt("schedulers.cages.timer");

	@Override
	public void run()
	{
		timer--;

		if (timer == 0)
		{
			stop();

			GameState.setState(GameState.GAME);
			
			CageManager.remove(false);

			for (Player players : Bukkit.getOnlinePlayers())
			{
				players.setLevel(0);
				
				GameManager.removePreviousLocation(players);				
			}

			GameManager.gameSetup();

			new TimeScheduler().runTaskTimer(UhcWTF.getInstance(), 20L, 20);
			
			InvincibilityScheduler invincibilityScheduler = new InvincibilityScheduler();
			invincibilityScheduler.setRunning(true);
			invincibilityScheduler.runTaskTimer(UhcWTF.getInstance(), 20L, 20L);
			
			PvPScheduler pvpScheduler = new PvPScheduler();
			pvpScheduler.setRunning(true);
			pvpScheduler.runTaskTimer(UhcWTF.getInstance(), 20L, 20L);
			
			new BorderTimeScheduler().runTaskTimer(UhcWTF.getInstance(), 20L, 20L);
			
			Bukkit.broadcastMessage(Config.getDefaultConfig().getString("messages.invincibility.start").replace("&", "§")
					.replace("%timer%", "" + InvincibilityScheduler.getTimer()));
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
		timer = Config.getDefaultConfig().getInt("schedulers.cages.timer");
	}

}
