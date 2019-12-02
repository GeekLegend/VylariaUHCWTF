package fr.geeklegend.vylaria.uhcwtf.listeners.manager;

import org.bukkit.plugin.PluginManager;

import fr.geeklegend.vylaria.uhcwtf.UHCWTF;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.AsyncPlayerPreLoginListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.BlockBreakListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.BlockPlaceListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.EntityDamageByEntityListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.EntityDamageListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.EntityRegainHealthListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.FoodLevelChangeListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.InventoryClickListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.other.WeatherChangeListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.player.PlayerDropItemListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.player.PlayerInteractListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.player.PlayerJoinListener;
import fr.geeklegend.vylaria.uhcwtf.listeners.player.PlayerQuitListener;

public class ListenersManager
{
	
	private UHCWTF instance;
	
	public ListenersManager(UHCWTF instance)
	{
		this.instance = instance;
	}
	
	public void registerListeners()
	{
		PluginManager pluginManager = instance.getServer().getPluginManager();
		pluginManager.registerEvents(new PlayerJoinListener(), instance);
		pluginManager.registerEvents(new PlayerQuitListener(), instance);
		pluginManager.registerEvents(new PlayerInteractListener(), instance);
		pluginManager.registerEvents(new PlayerDropItemListener(), instance);
		
		pluginManager.registerEvents(new AsyncPlayerPreLoginListener(), instance);
		pluginManager.registerEvents(new InventoryClickListener(), instance);
		pluginManager.registerEvents(new FoodLevelChangeListener(), instance);
		pluginManager.registerEvents(new WeatherChangeListener(), instance);
		pluginManager.registerEvents(new EntityDamageListener(), instance);
		pluginManager.registerEvents(new EntityRegainHealthListener(), instance);
		pluginManager.registerEvents(new EntityDamageByEntityListener(), instance);
		pluginManager.registerEvents(new BlockBreakListener(), instance);
		pluginManager.registerEvents(new BlockPlaceListener(), instance);
	}

}
