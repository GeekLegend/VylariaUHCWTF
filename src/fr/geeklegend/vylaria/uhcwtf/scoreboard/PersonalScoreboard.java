package fr.geeklegend.vylaria.uhcwtf.scoreboard;

import java.text.SimpleDateFormat;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.geeklegend.vylaria.uhcwtf.game.BorderManager;
import fr.geeklegend.vylaria.uhcwtf.game.GameManager;
import fr.geeklegend.vylaria.uhcwtf.game.GameState;
import fr.geeklegend.vylaria.uhcwtf.schedulers.BorderTimeScheduler;
import fr.geeklegend.vylaria.uhcwtf.schedulers.PvPScheduler;
import fr.geeklegend.vylaria.uhcwtf.schedulers.StartScheduler;
import fr.geeklegend.vylaria.uhcwtf.schedulers.TimeScheduler;
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
		if (GameState.isState(GameState.WAITING))
		{
			objectiveSign.setDisplayName("§7- §eUhcWTF §7-");
			objectiveSign.setLine(0, "§1§6 ");
			objectiveSign.setLine(1, "§1§7Joueurs » §a" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
			objectiveSign.setLine(2, "§1§2 ");
			if (!StartScheduler.isRunning())
			{
				objectiveSign.setLine(3, "§cEn attente...");
			} else
			{
				objectiveSign.setLine(3, "§e" + StartScheduler.getTimer() + " seconde(s)");
			}
			objectiveSign.setLine(4, "§1§1 ");
			objectiveSign.setLine(5, "§7Id §f" + Bukkit.getMotd());
			objectiveSign.setLine(6, "§1§4 ");
			objectiveSign.setLine(7, ip);
		} else
		{
			String timeFormat = new SimpleDateFormat("mm:ss").format(Integer.valueOf(TimeScheduler.getTimer() * 1000));
			String pvpTimeFormat = new SimpleDateFormat("mm:ss").format(Integer.valueOf(PvPScheduler.getTimer() * 1000));
			String borderTimeFormat = new SimpleDateFormat("mm:ss").format(Integer.valueOf(BorderTimeScheduler.getTimer() * 1000));

			objectiveSign.setDisplayName("§7- §eUhcWTF §7| §e" + timeFormat + " §7-");
			objectiveSign.setLine(0, "§2§6 ");
			objectiveSign.setLine(1, "§2§7Joueurs » §a" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
			objectiveSign.setLine(2, "§2§2 ");
			objectiveSign.setLine(3, "§eTemps:");
			if (!GameManager.isPvP())
			{
				objectiveSign.setLine(4, " §7PvP » §e" + pvpTimeFormat);
			} else
			{
				objectiveSign.setLine(4, " §7PvP » §2✔");
			}
			if (!GameManager.isBorder())
			{
				objectiveSign.setLine(5, " §7Bordure » §e" + borderTimeFormat);
			} else
			{
				objectiveSign.setLine(5, " §7Bordure » §2✔");
			}
			objectiveSign.setLine(6, "§2§1 ");
			objectiveSign.setLine(7, "§eBordure:");
			objectiveSign.setLine(8, " §7Taille » §e" + BorderManager.getSize() + "§7 / §e-" + BorderManager.getSize());
			objectiveSign.setLine(9, "§2§4 ");
			objectiveSign.setLine(10, ip);
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