package net.axiom.mahouphantasm.entity.spells.redmist_slash;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.axiom.mahouphantasm.MahouPhantasm;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import java.util.Random;

public class RedmistSlashRenderer extends EntityRenderer<RedmistSlash> {
    private static final ResourceLocation[] TEXTURES = new ResourceLocation[]{
            MahouPhantasm.id("textures/entity/redmist_slash/redmist_slash_1.png"),
            MahouPhantasm.id("textures/entity/redmist_slash/redmist_slash_2.png"),
            MahouPhantasm.id("textures/entity/redmist_slash/redmist_slash_3.png"),
            MahouPhantasm.id("textures/entity/redmist_slash/redmist_slash_4.png")
    };

    public RedmistSlashRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(RedmistSlash entity, float yaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F - entity.getYRot()));
        poseStack.mulPose(Axis.ZP.rotationDegrees(entity.getXRot()));

        if (entity.isVertical()) {
            poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
        }

        float randomZ = (float)(new Random(31L * entity.getId())).nextInt(-8, 8);
        poseStack.mulPose(Axis.XP.rotationDegrees(randomZ));

        drawSlash(poseStack.last(), entity, bufferSource,
                entity.getBbWidth() * 1.5F, entity.isMirrored());
        poseStack.popPose();
        super.render(entity, yaw, partialTicks, poseStack, bufferSource, light);
    }

    private void drawSlash(PoseStack.Pose pose, RedmistSlash entity,
                           MultiBufferSource bufferSource, float width, boolean mirrored) {
        Matrix4f poseMatrix = pose.pose();
        Matrix3f normalMatrix = pose.normal();
        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));

        float halfWidth = width * 0.5F;
        float height = entity.getBbHeight() * 0.5F;

        consumer.vertex(poseMatrix, -halfWidth, height, -halfWidth)
                .color(255, 255, 255, 255)
                .uv(0.0F, mirrored ? 1.0F : 0.0F)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(15728880)
                .normal(normalMatrix, 0.0F, 1.0F, 0.0F)
                .endVertex();

        consumer.vertex(poseMatrix, halfWidth, height, -halfWidth)
                .color(255, 255, 255, 255)
                .uv(1.0F, mirrored ? 1.0F : 0.0F)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(15728880)
                .normal(normalMatrix, 0.0F, 1.0F, 0.0F)
                .endVertex();

        consumer.vertex(poseMatrix, halfWidth, height, halfWidth)
                .color(255, 255, 255, 255)
                .uv(1.0F, mirrored ? 0.0F : 1.0F)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(15728880)
                .normal(normalMatrix, 0.0F, 1.0F, 0.0F)
                .endVertex();

        consumer.vertex(poseMatrix, -halfWidth, height, halfWidth)
                .color(255, 255, 255, 255)
                .uv(0.0F, mirrored ? 0.0F : 1.0F)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(15728880)
                .normal(normalMatrix, 0.0F, 1.0F, 0.0F)
                .endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(RedmistSlash entity) {
        int frame = (entity.tickCount / 2) % TEXTURES.length;
        return TEXTURES[frame];
    }
}
