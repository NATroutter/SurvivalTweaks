package net.natroutter.survivaltweaks.features.recipies;

import net.natroutter.survivaltweaks.Handler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Light {

    private Handler handler;

    public Light(Handler handler) {
        NamespacedKey key = new NamespacedKey(handler.getInstance(), "light");
        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.LIGHT, 16));
        recipe.shape("GGG", "DYD", "SSS");
        recipe.setIngredient('G', Material.GLASS);
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('Y', Material.DRAGON_BREATH);
        recipe.setIngredient('S', Material.SEA_LANTERN);

        Bukkit.addRecipe(recipe);

    }


}
