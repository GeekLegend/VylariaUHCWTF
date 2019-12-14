package fr.geeklegend.vylaria.uhcrun.listeners.manager;

import org.bukkit.plugin.PluginManager;

import fr.geeklegend.vylaria.uhcwtf.UhcWTF;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.AsyncPlayerChatListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.BlockBreakListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.BlockPlaceListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.EntityDamageByEntityListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.EntityDamageListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.EntityRegainHealthListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.FoodLevelChangeListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.InventoryClickListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.WeatherChangeListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.player.PlayerDeathListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.player.PlayerDropItemListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.player.PlayerInteractListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.player.PlayerJoinListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.player.PlayerMoveListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.player.PlayerQuitListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.player.PlayerRespawnListener;

public class ListenersManager
{

	private UhcWTF instance;
	
	public ListenersManager(UhcWTF instance)
	{
		this.instance = instance;
	}
	
	public void register()
	{
		PluginManager pluginManager = instance.getServer().getPluginManager();
		pluginManager.registerEvents(new PlayerJoinListener(), instance);
		pluginManager.registerEvents(new PlayerQuitListener(), instance);
		pluginManager.registerEvents(new PlayerDropItemListener(), instance);
		pluginManager.registerEvents(new PlayerMoveListener(), instance);
		pluginManager.registerEvents(new PlayerDeathListener(), instance);
		pluginManager.registerEvents(new PlayerRespawnListener(), instance);
		pluginManager.registerEvents(new PlayerInteractListener(), instance);
		
		pluginManager.registerEvents(new AsyncPlayerChatListener(), instance);
		pluginManager.registerEvents(new BlockBreakListener(), instance);
		pluginManager.registerEvents(new BlockPlaceListener(), instance);
		pluginManager.registerEvents(new FoodLevelChangeListener(), instance);
		pluginManager.registerEvents(new EntityDamageByEntityListener(), instance);
		pluginManager.registerEvents(new EntityDamageListener(), instance);
		pluginManager.registerEvents(new EntityRegainHealthListener(), instance);
		pluginManager.registerEvents(new InventoryClickListener(), instance);
		pluginManager.registerEvents(new WeatherChangeListener(), instance);
	}
	
}
