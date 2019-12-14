package fr.geeklegend.vylaria.uhcwtf.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.geeklegend.vylaria.uhcwtf.UhcWTF;

public class Config {

	private static FileConfiguration defaultConfig = YamlConfiguration.loadConfiguration(getFile("config"));

	public static void create(String fileName) {
		if (!UhcWTF.getInstance().getDataFolder().exists()) {
			UhcWTF.getInstance().getDataFolder().mkdir();
		}
		File file = new File(UhcWTF.getInstance().getDataFolder(), fileName + ".yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void load(String fileName) {
		YamlConfiguration.loadConfiguration(getFile(fileName));
	}

	public static void saveDefaultConfig() {
		try {
			defaultConfig.save(getFile("config"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File getFile(String fileName) {
		return new File(UhcWTF.getInstance().getDataFolder(), fileName + ".yml");
	}

	public static FileConfiguration getDefaultConfig() {
		return defaultConfig;
	}

}