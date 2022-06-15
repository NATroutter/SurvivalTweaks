package net.natroutter.survivaltweaks.features.recipies;

import net.natroutter.survivaltweaks.Handler;
import org.bukkit.plugin.java.JavaPlugin;

public class RecipeHandler {

    private Handler handler;

    public RecipeHandler(Handler handler) {
        new Light(handler);
    }


}
