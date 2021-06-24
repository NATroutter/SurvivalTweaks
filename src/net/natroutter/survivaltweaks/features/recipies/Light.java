package net.natroutter.survivaltweaks.features.recipies;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Light {

    public Light(JavaPlugin plugin) {
        NamespacedKey key = new NamespacedKey(plugin, "light");
        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.LIGHT, 16));
        recipe.shape("GGG", "DYD", "SSS");
        recipe.setIngredient('G', Material.GLASS);
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('Y', Material.DRAGON_HEAD);
        recipe.setIngredient('S', Material.SEA_LANTERN);

        Bukkit.addRecipe(recipe);

    }


}
