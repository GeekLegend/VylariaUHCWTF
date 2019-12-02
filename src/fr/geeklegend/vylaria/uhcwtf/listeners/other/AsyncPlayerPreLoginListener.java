package fr.geeklegend.vylaria.uhcwtf.listeners.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import fr.geeklegend.vylaria.uhcwtf.schedulers.schematic.SchematicScheduler;

public class AsyncPlayerPreLoginListener implements Listener
{

	@EventHandler
	public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event)
	{
		if (SchematicScheduler.isRunning())
		{
			event.setKickMessage("§cLe serveur redémarre");
			event.setLoginResult(Result.KICK_WHITELIST);
		}
	}

}
