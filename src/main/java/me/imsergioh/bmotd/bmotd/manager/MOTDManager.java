package me.imsergioh.bmotd.bmotd.manager;

import me.imsergioh.bmotd.bmotd.BMotd;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class MOTDManager {

    private static final BMotd plugin = BMotd.getPlugin();

    private File file;
    private Configuration config;

    private String profileName = "default";
    private List<String> motdList = new ArrayList<>();
    private File favicon;

    public MOTDManager() {
        regFile();
        registerConfigFromConfig();
    }

    public void registerConfigFromConfig(){
        try {
            this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (Exception e){e.printStackTrace();}
        profileName = config.getString("profile");
        motdList = config.getStringList("profiles."+profileName+".motd");
        this.favicon = new File(plugin.getDataFolder()+"//"+config.getString("profiles."+profileName+".icon"));
    }

    public Favicon getFavicon(){
        try {
            BufferedImage image = ImageIO.read(favicon);
            return Favicon.create(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMOTD(){
        return motdList.get(0)+"\n"+motdList.get(1);
    }

    public boolean isActive(String configPath){
        return config.getBoolean(configPath);
    }

    public boolean hasWhitelist(String name){
        if(config.getBoolean("config.whitelist-enabled") == true) {
            return config.getStringList("config.whitelist").contains(name);
        }
        return true;
    }

    public int getMaxSlots(){
        return config.getInt("profiles."+profileName+".max-slots");
    }

    private void regFile() {
        plugin.getDataFolder().mkdirs();
        this.file = new File(plugin.getDataFolder(), "config.yml");
        try {
            if (!file.exists()) {
                try (InputStream in = plugin.getResourceAsStream("config.yml")) {
                    Files.copy(in, file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Configuration getConfig() {
        return config;
    }
}
