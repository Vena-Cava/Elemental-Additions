package me.venacava.elementaladditions;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class ElementalAdditions extends JavaPlugin implements SlimefunAddon {

    public static ElementalAdditions instance;

    private NestedItemGroup nestedItemGroup;
    private ItemGroup mainItemGroup;
    private ItemGroup miscItemGroup;
    private ItemGroup foodItemGroup;
    private ItemGroup drinksItemGroup;

    @Override
    public void onEnable() {
        // Read something from your config.yml
        Config cfg = new Config(this);

        if (cfg.getBoolean("options.auto-update")) {
            // You could start an Auto-Updater for example
        }

        instance = this;
        cfg = new Config(this);

        registerItems();

    }

    private void registerItems() {
        nestedItemGroup = new NestedItemGroup(new NamespacedKey(this, "parent_category"), new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromHashCode("847d73a91b52393f2c27e453fb89ab3d784054d414e390d58abd22512edd2b")), "&aElemental Additions"));
        miscItemGroup = new SubItemGroup(new NamespacedKey(this, "misc"), nestedItemGroup, new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromHashCode("606be2df2122344bda479feece365ee0e9d5da276afa0e8ce8d848f373dd131")), "&aElemental Additions - Ingredients and Tools"));
        foodItemGroup = new SubItemGroup(new NamespacedKey(this, "food"), nestedItemGroup, new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromHashCode("a14216d10714082bbe3f412423e6b19232352f4d64f9aca3913cb46318d3ed")), "&aElemental Additions - Food"));
        drinksItemGroup = new SubItemGroup(new NamespacedKey(this, "drinks"), nestedItemGroup, new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromHashCode("2a8f1f70e85825607d28edce1a2ad4506e732b4a5345a5ea6e807c4b313e88")), "&aElemental Additions - Drinks"));

        // @formatter:off
        SlimefunItemStack cactusJuice = new SlimefunItemStack("EA_CACTUS_JUICE", Material.POTION, "&eCactus Juice");
        new SlimefunItem(drinksItemGroup, cactusJuice, RecipeType.JUICER, new ItemStack[] {new ItemStack(Material.CACTUS), null, null, null, null, null, null, null, null}, new SlimefunItemStack(cactusJuice, 2));
        PotionMeta meta = (PotionMeta) cactusJuice.getItemMeta();
        meta.setBasePotionData(new PotionData(PotionType.LUCK));
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        cactusJuice.setItemMeta(meta);
    }

    public class CactusJuice extends SlimefunItem {

        public CactusJuice(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
            super(itemGroup, item, recipeType, recipe);
        }

        @Override
        public void preRegister() {
            // We will add our Item Handlers right here.
            ItemUseHandler itemUseHandler = this::onItemUseRightClick;
            addItemHandler(itemUseHandler);
        }

        private void onItemUseRightClick(PlayerRightClickEvent event) {
            // Calling event.cancel() in here would prevent the cake
            // from being placed down.
            event.getPlayer().addPotionEffect(PotionEffectType.WEAKNESS, 6, 0),
            event.getPlayer().addPotionEffect(PotionEffectType.CONFUSION, 6, 0)
        }

    }



    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }

}
