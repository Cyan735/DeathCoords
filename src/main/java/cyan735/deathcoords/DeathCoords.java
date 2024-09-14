package cyan735.deathcoords;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathCoords extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        //Registers a death listener
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent playerDeathEvent) {
        //Gets players coordinates
        Player player = playerDeathEvent.getEntity().getPlayer();
        int x = (int) player.getLocation().getX();
        int y = (int) player.getLocation().getY();
        int z = (int) player.getLocation().getZ();

        String environment = player.getWorld().getEnvironment().name();
        String dimension;

        if (environment.equals("NORMAL")) {
            dimension = "the Overworld";
        }
        else if (environment.equals("NETHER")) {
            dimension = "the Nether";
        }
        else if (environment.equals("THE_END")) {
            dimension = "the End";
        }
        else {
            dimension = "an Unknown Dimension";
        }

        //Sends message to player
        //Delay to send message after death message
        Bukkit.getScheduler().runTaskLater(this, () -> player.sendMessage("You died in " + dimension + " at (" + x + ", " + y + ", " + z + ")"), 1);
    }
}
