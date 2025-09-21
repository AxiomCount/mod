package net.axiom.mahouphantasm.debug;

import net.axiom.mahouphantasm.spell.MSpellAnimations;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = "mahouphantasm", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class AnimationDebug {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void checkAnimations() {
        MSpellAnimations.MIMICRY_UPSWING.getForPlayer().ifPresentOrElse(
                rl -> LOGGER.info("[AnimationTest] Found animation mimicry_upswing at: " + rl),
                () -> LOGGER.warn("[AnimationTest] mimicry_upswing NOT found!")
        );

        MSpellAnimations.MIMICRY_SLASH.getForPlayer().ifPresentOrElse(
                rl -> LOGGER.info("[AnimationTest] Found animation mimicry_slash at: " + rl),
                () -> LOGGER.warn("[AnimationTest] mimicry_slash NOT found!")
        );
    }
}