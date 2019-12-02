package fr.geeklegend.vylaria.uhcwtf;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import fr.geeklegend.vylaria.api.VylariaAPI;
import fr.geeklegend.vylaria.uhcwtf.game.manager.GameManager;
import fr.geeklegend.vylaria.uhcwtf.game.states.GameStates;
import fr.geeklegend.vylaria.uhcwtf.listeners.manager.ListenersManager;
import fr.geeklegend.vylaria.uhcwtf.schedulers.schematic.SchematicScheduler;
import fr.geeklegend.vylaria.uhcwtf.scoreboard.manager.ScoreboardManager;
import fr.geeklegend.vylaria.uhcwtf.utils.world.WorldUtils;

public class UHCWTF extends JavaPlugin
{

	private static UHCWTF instance;

	private ScheduledExecutorService executorMonoThread;
	private ScheduledExecutorService scheduledExecutorService;
	private ScoreboardManager scoreboardManager;

	@Override
	public void onEnable()
	{
		instance = this;

		executorMonoThread = Executors.newScheduledThreadPool(16);
		scheduledExecutorService = Executors.newScheduledThreadPool(1);
		scoreboardManager = new ScoreboardManager();
		
		GameStates.setState(GameStates.WAITING);

		new ListenersManager(this).registerListeners();

		new SchematicScheduler().runTaskTimer(this, 20L, 20L);
		
		GameManager.load();
	}

	@Override
	public void onLoad()
	{
		VylariaAPI.getInstance().getMySQL().resetOnline("uhcwtf");
	}

	@Override
	public void onDisable()
	{
		instance = null;

		scoreboardManager.onDisable();

		if (GameStates.isState(GameStates.FINISH))
		{
			World world = Bukkit.getWorld("world");

			Bukkit.unloadWorld(world, false);

			File worldFile = new File(world.getName());

			WorldUtils.deleteWorld(worldFile);
		}
	}

	public static UHCWTF getInstance()
	{
		return instance;
	}

	public ScheduledExecutorService getExecutorMonoThread()
	{
		return executorMonoThread;
	}

	public ScheduledExecutorService getScheduledExecutorService()
	{
		return scheduledExecutorService;
	}

	public ScoreboardManager getScoreboardManager()
	{
		return scoreboardManager;
	}

}
