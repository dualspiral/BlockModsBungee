package uk.co.drnaylor.blockmodsbungee;

import java.util.Set;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BlockModsListener implements Listener {

    private final BlockMods plugin;

    public BlockModsListener(BlockMods plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerConnected(ServerConnectedEvent event) {
        ProxiedPlayer player = event.getPlayer();
        Set<String> modList = player.getModList().keySet();

        if (modList.isEmpty()) {
            return;
        }

        for ( String mod : modList ) {
            // Mods we MUST support, so we don't do anything to check them.
            if (mod.equals("FML") || mod.equals("Forge") || mod.equals("mcp")) {
                continue;
            }

            if (plugin.isWhitelist() != plugin.getModList().contains( mod.toLowerCase() )) {
                // If a whitelist, then reject if a mod does not appear in the list. Blacklist, do the opposite.
                TextComponent tc = new TextComponent("The mod " + mod + " is not allowed on this server!" );
                tc.setColor(ChatColor.RED);
                player.disconnect( tc );
            }
        }
    }
}
