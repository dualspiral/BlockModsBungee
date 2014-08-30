package uk.co.drnaylor.blockmodsbungee;

import java.io.IOException;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class BlockModsCommand extends Command {

    private final BlockMods plugin;

    public BlockModsCommand(BlockMods plugin) {
        super("blockmods");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase( "reload" ) && sender.hasPermission( "blockmods.reload" )) {
            try {
                plugin.loadConfig();
                TextComponent component = new TextComponent( "BlockMods config reloaded!" );
                component.setColor(ChatColor.GREEN);
                sender.sendMessage( component );
            } catch (IOException ex) {
                TextComponent component = new TextComponent( "Failed to reload BlockMods config." );
                component.setColor(ChatColor.RED);
                sender.sendMessage( component );
            }

            return;
        }

        TextComponent component = new TextComponent( "BlockMods v" + plugin.getDescription().getVersion() );
        component.setColor(ChatColor.GREEN);
        sender.sendMessage( component );
    }
    
}
