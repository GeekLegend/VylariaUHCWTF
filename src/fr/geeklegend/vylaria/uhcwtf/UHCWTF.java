package fr.geeklegend.vylaria.uhcwtf;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import fr.geeklegend.vylaria.uhcwtf.config.Config;
import fr.geeklegend.vylaria.uhcwtf.game.GameManager;
import fr.geeklegend.vylaria.uhcwtf.game.GameState;
import fr.geeklegend.vylaria.uhcwtf.listeners.manager.ListenersManager;
import fr.geeklegend.vylaria.uhcwtf.scoreboard.manager.ScoreboardManager;
import fr.geeklegend.vylaria.uhcwtf.utils.WorldUtils;

public class UhcWTF extends JavaPlugin
{

	private static UhcWTF instance;

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

		Config.create("config");
		Config.load("config");
		Config.getDefaultConfig().options().copyDefaults(true);
		Config.getDefaultConfig().addDefault("schedulers.start.minplayers", 1);
		Config.getDefaultConfig().addDefault("schedulers.start.timer", 30);
		Config.getDefaultConfig().addDefault("schedulers.cages.timer", 5);
		Config.getDefaultConfig().addDefault("schedulers.invincibility.timer", 30);
		Config.getDefaultConfig().addDefault("schedulers.pvp.timer", 300);
		Config.getDefaultConfig().addDefault("schedulers.border.timer", 900);
		Config.getDefaultConfig().addDefault("messages.join",
				"&e%playername% &7a rejoint la partie. &e(%online%/%maxonline%)");
		Config.getDefaultConfig().addDefault("messages.quit",
				"&e%playername% &7a quitté la partie. &e(%online%/%maxonline%)");
		Config.getDefaultConfig().addDefault("messages.alreadystart",
				"&7La partie à déja commencer, vous avez rejoint en tant que spectateur.");
		Config.getDefaultConfig().addDefault("messages.schedulers.start.while",
				"&7La partie commence dans &e%timer% seconde(s).");
		Config.getDefaultConfig().addDefault("messages.schedulers.start.noenought",
				"&cIl n'y a pas assez de joueur pour commencer la partie.");
		Config.getDefaultConfig().addDefault("messages.invincibility.start",
				"&7Vous êtes invincible pendant &e%timer% secondes.");
		Config.getDefaultConfig().addDefault("messages.invincibility.while",
				"&7&7Vous êtes vulnérable dans &e%timer% seconde(s).");
		Config.getDefaultConfig().addDefault("messages.invincibility.disabled",
				"&7Vous êtes désormais vulnérable.");
		Config.getDefaultConfig().addDefault("messages.pvp",
				"&7Les dégâts entre joueurs sont désormais actifs.");
		Config.getDefaultConfig().addDefault("messages.border",
				"&7La bordure commence à se rétracter.");
		Config.getDefaultConfig().addDefault("messages.deathbykiller",
				"&e%victimname% §7a été tué par &c%killername%");
		Config.getDefaultConfig().addDefault("messages.deathnokiller",
				"&e%victimname% §7est mort.");
		Config.getDefaultConfig().addDefault("messages.win", "&7Le joueur &e%playername% &7a remporter la victoire !");
		Config.getDefaultConfig().addDefault("game.world.name", "world");
		Config.getDefaultConfig().addDefault("game.world.size", 800.0);
		Config.getDefaultConfig().addDefault("setups.join.gamemode", "adventure");		
		Config.getDefaultConfig().addDefault("setups.join.spawn.y", 150.0);
		Config.getDefaultConfig().addDefault("setups.join.spawn.z", 0.5);
		Config.getDefaultConfig().addDefault("setups.join.spawn.yaw", 180);
		Config.getDefaultConfig().addDefault("setups.join.spawn.pitch", 0);
		Config.getDefaultConfig().addDefault("setups.join.items.leave.slot", 8);
		Config.getDefaultConfig().addDefault("setups.join.items.leave.material", "bed");
		Config.getDefaultConfig().addDefault("setups.join.items.leave.name", "&6Quitter");
		Config.getDefaultConfig().addDefault("setups.game.gamemode", "survival");		
		Config.saveDefaultConfig();

		GameState.setState(GameState.WAITING);

		GameManager.load();
		
		WorldUtils.loadSchematic(new Location(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world")),
				Config.getDefaultConfig().getDouble("setups.join.spawn.x"),
				Config.getDefaultConfig().getDouble("setups.join.spawn.y") - 1,
				Config.getDefaultConfig().getDouble("setups.join.spawn.z")), "uhcwtfwaitingcage", true);
		
		new ListenersManager(this).register();
	}

	@Override
	public void onDisable()
	{
		instance = null;

		scoreboardManager.onDisable();

		Bukkit.unloadWorld(Config.getDefaultConfig().getString("game.world.name"), false);

		WorldUtils.deleteWorld(new File(Config.getDefaultConfig().getString("game.world.name")));
	}

	public static UhcWTF getInstance()
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
