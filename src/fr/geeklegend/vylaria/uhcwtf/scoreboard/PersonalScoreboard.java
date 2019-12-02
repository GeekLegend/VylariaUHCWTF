package fr.geeklegend.vylaria.uhcwtf.scoreboard;

import java.text.SimpleDateFormat;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.geeklegend.vylaria.uhcwtf.game.states.GameStates;
import fr.geeklegend.vylaria.uhcwtf.schedulers.start.StartScheduler;
import fr.geeklegend.vylaria.uhcwtf.schedulers.time.TimeScheduler;
import fr.geeklegend.vylaria.uhcwtf.scoreboard.objective.ObjectiveSign;

/*
 * This file is part of SamaGamesVylariaAPI.
 *
 * SamaGamesVylariaAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SamaGamesVylariaAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SamaGamesVylariaAPI.  If not, see <http://www.gnu.org/licenses/>.
 */
public class PersonalScoreboard
{
	private Player player;
	private final UUID uuid;
	private final ObjectiveSign objectiveSign;

	public PersonalScoreboard(Player player)
	{
		this.setPlayer(player);
		uuid = player.getUniqueId();
		objectiveSign = new ObjectiveSign("sidebar", "DevPlugin");

		reloadData();
		objectiveSign.addReceiver(player);
	}

	public void reloadData()
	{
	}

	public void setLines(String ip)
	{
		String timeTimerFormat = new SimpleDateFormat("mm:ss").format(Integer.valueOf(TimeScheduler.getTimer() * 1000));

		objectiveSign.setDisplayName("§e§lUHC WTF");
		if (GameStates.isState(GameStates.WAITING))
		{
			objectiveSign.setLine(0, "§7Id: " + Bukkit.getMotd());
			objectiveSign.setLine(1, "§6 ");
			objectiveSign.setLine(2, "§6" + player.getName());
			objectiveSign.setLine(3, "§2 ");
			objectiveSign.setLine(4,
					"§7Joueur(s) » §a" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
			objectiveSign.setLine(5, "§1 ");
			if (!StartScheduler.isRunning())
			{
				objectiveSign.setLine(6, "§7En attente...");
			} else
			{
				objectiveSign.setLine(6, "§e" + StartScheduler.getTimer() + " seconde(s)");
			}
			objectiveSign.setLine(7, "§4 ");
			objectiveSign.setLine(8, ip);
		} else if (GameStates.isState(GameStates.PREGAME) || GameStates.isState(GameStates.GAME))
		{
			objectiveSign.setLine(0, "§7Id: " + Bukkit.getMotd());
			objectiveSign.setLine(1, "§6§2 ");
			objectiveSign.setLine(2, "§2§6" + player.getName());
			objectiveSign.setLine(3, "§1§2 ");
			objectiveSign.setLine(4, "§7Temps: §e" + timeTimerFormat);
			objectiveSign.setLine(5, "§4§2 ");
			objectiveSign.setLine(6, "§7PvP: §cX");
			objectiveSign.setLine(7, "§7Bordure: §cX");
			objectiveSign.setLine(8, "§3§2 ");
			objectiveSign.setLine(9,
					"§7Joueur(s) » §a" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
			objectiveSign.setLine(10, "§8§2 ");
			objectiveSign.setLine(11, ip);
		}
		objectiveSign.updateLines();
	}

	public void onLogout()
	{
		objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}
}