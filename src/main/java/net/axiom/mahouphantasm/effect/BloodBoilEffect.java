package net.axiom.mahouphantasm.effect;


import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.player.ClientMagicData;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class BloodBoilEffect extends MagicMobEffect {
    private int duration;
    public BloodBoilEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int amplifier) {
        this.duration = pDuration;
        return true;
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide && entity instanceof Player player) {
            float damage = ClientMagicData.getSyncedSpellData(player).getHeartstopAccumulatedDamage();
            float f = 1.0F - Mth.clamp(damage / player.getHealth(), 0.0F, 1.0F);
            int i = (int) (10.0F + 30.0F * f);
            if (this.duration % Math.max(i, 1) == 0) {
                player.playSound(SoundEvents.WARDEN_HEARTBEAT, 0.6F, 1.0F);

                MagicManager.spawnParticles(entity.level(), ParticleHelper.BLOOD,
                        entity.getX(), entity.getY() + entity.getBbHeight() * 0.5, entity.getZ(),
                        20, entity.getBbWidth() * 0.3, entity.getBbHeight() * 0.5,
                        entity.getBbWidth() * 0.3, 0.02, false);
            }
        }
    }


    public void removeAttributeModifiers(@NotNull LivingEntity entity, @NotNull AttributeMap pAttributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, pAttributeMap, amplifier);

        var synced = MagicData.getPlayerMagicData(entity).getSyncedData();
        float accumulated = synced.getHeartstopAccumulatedDamage();
        synced.setHeartstopAccumulatedDamage(0f);

        Level level = entity.level();
        if (!level.isClientSide) {
            if (entity.getHealth() <= 0.5f) {
                entity.setHealth(0.5f);
                level.explode(entity, entity.getX(), entity.getY(), entity.getZ(),
                        6.0f, Level.ExplosionInteraction.MOB);
                entity.kill();
            } else {
                float returned = accumulated * (0.4f + amplifier * 0.1f);
                float radius  = Math.max(2.0f, 5.0f - amplifier);

                level.explode(entity, entity.getX(), entity.getY(), entity.getZ(),
                        radius, Level.ExplosionInteraction.MOB);

                entity.hurt(DamageSources.get(level, ISSDamageTypes.BLOOD_MAGIC), returned);
            }
        }
    }
}
