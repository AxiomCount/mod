//package net.axiom.mahouphantasm.setup;
//
//import io.redspace.ironsspellbooks.block.alchemist_cauldron.AlchemistCauldronRecipe;
//import io.redspace.ironsspellbooks.block.alchemist_cauldron.AlchemistCauldronRecipeRegistry;
//import io.redspace.ironsspellbooks.registries.ItemRegistry;
//import net.axiom.mahouphantasm.MahouPhantasm;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.Items;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//import net.minecraftforge.registries.ForgeRegistries;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class ISSAlchemistCauldronRegistrar {
//    private static final Logger LOGGER = LogManager.getLogger();
//
//    public static void onCommonSetup(final FMLCommonSetupEvent event) {
//        // Delayed
//        event.enqueueWork(() -> {
//            Item amethyst = Items.AMETHYST_SHARD;
//            Item lumisene = ForgeRegistries.ITEMS.getValue(new ResourceLocation("supplementaries", "lumisene_bottle"));
//            Item resultInk = ItemRegistry.INK_COMMON.get();
//
//            if (lumisene == null) {
//                LOGGER.warn("Supplementaries item 'lumisene_bottle' not found — recipe not registered");
//                return;
//            }
//
//            // base = amethyst, ingredient = lumisene -> result = INK_COMMON
//            AlchemistCauldronRecipe recipe = new AlchemistCauldronRecipe(amethyst, lumisene, resultInk)
//                    .setBaseRequirement(2)   // Amount of ingredients
//                    .setResultLimit(1);      // Just result
//
//            ResourceLocation recipeId = new ResourceLocation(MahouPhantasm.MOD_ID, "ink_from_amethyst_lumisene");
//            AlchemistCauldronRecipeRegistry.registerRecipe(recipeId, recipe);
//
//            LOGGER.info("Registered alchemist cauldron recipe: {}", recipeId);
//
//            // ResourceLocation recipeId2 = new ResourceLocation(MahouPhantasm.MOD_ID, "ink_from_lumisene_glow");
//            // AlchemistCauldronRecipeRegistry.registerRecipe(recipeId2, new AlchemistCauldronRecipe(lumisene, amethyst, resultInk).setBaseRequirement(2).setResultLimit(1));
//        });
//    }
//    public static void register(IEventBus modEventBus) { modEventBus.addListener(ISSAlchemistCauldronRegistrar::onCommonSetup); }
//}