package uk.co.drnaylor.blockmodsbungee;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class BlockMods extends Plugin {

    private boolean isWhitelist;

    private final List<String> modList = new ArrayList<>();

    /**
     * Runs when the plugin is enabled.
     */
    @Override
    public void onEnable() {
        try {
            loadConfig();
            this.getProxy().getPluginManager().registerCommand(this, new BlockModsCommand(this));
            this.getProxy().getPluginManager().registerListener(this, new BlockModsListener(this));
        } catch (IOException ex) {
            Logger.getLogger(BlockMods.class.getName()).log(Level.SEVERE, "Failed to load. This plugin will do nothing", ex);
        }
    }

    public void loadConfig() throws IOException {
        Configuration config = getConfig();
        isWhitelist = config.getBoolean( "isWhitelist", true );
        List<String> mods = config.getStringList( "modList" );

        modList.clear();
        if (mods != null) {
            for ( String mod : mods ) {
                modList.add( mod.toLowerCase() );
            }
        }
    }

    private Configuration getConfig() throws IOException {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        File file = new File(getDataFolder(), "config.yml");

        if (!file.exists()) {
            Files.copy(getResourceAsStream("config.yml"), file.toPath());
        }

        return ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
    }

    public boolean isWhitelist() {
        return isWhitelist;
    }

    public List<String> getModList() {
        return modList;
    }
}
