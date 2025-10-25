package net.axiom.mahouphantasm.setup;

import net.axiom.mahouphantasm.MahouPhantasm;
import net.axiom.mahouphantasm.entity.EntityRegistry;
import net.axiom.mahouphantasm.entity.spells.redmist_slash.RedmistSlashRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = MahouPhantasm.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = {Dist.CLIENT}
)

public class ClientSetup {
    public ClientSetup() {
    }

    @SubscribeEvent
    public static void rendererRegister(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer((EntityType) EntityRegistry.REDMISTSLASH.get(), RedmistSlashRenderer::new);
    }
}