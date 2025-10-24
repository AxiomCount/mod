package net.axiom.mahouphantasm.entity.spells.redmist_slash;

import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import net.axiom.mahouphantasm.entity.MahouEntityRegistry;

import java.util.Optional;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class RedmistSlash extends AoeEntity {
    private static final EntityDataAccessor<Boolean> DATA_MIRRORED;
    private static final EntityDataAccessor<Boolean> DATA_VERTICAL;
    LivingEntity target;
    public final int ticksPerFrame;
    public final int deathTime;

    public RedmistSlash(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.ticksPerFrame = 2;
        this.deathTime = 8;
    }

    public RedmistSlash(Level level, boolean mirrored, boolean vertical) {
        this(MahouEntityRegistry.REDMISTSLASH.get(), level);
        this.getEntityData().set(DATA_MIRRORED, mirrored);
        this.getEntityData().set(DATA_VERTICAL, vertical);
    }

    public void applyEffect(LivingEntity target) {
    }

    public void tick() {
        if (!this.firstTick) {
            this.firstTick = true;
        }
        if (this.tickCount >= 8) {
            this.discard();
        }
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_MIRRORED, false);
        this.getEntityData().define(DATA_VERTICAL, false);
    }

    public boolean isMirrored() {
        return this.getEntityData().get(DATA_MIRRORED);
    }

    public boolean isVertical() {
        return this.getEntityData().get(DATA_VERTICAL);
    }

    public boolean shouldBeSaved() {
        return false;
    }


    public void refreshDimensions() {}

    public void ambientParticles() {}

    public float getParticleCount() { return 0.0F; }

    public Optional<ParticleOptions> getParticle() {
        return Optional.empty();
    }


    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    static {
        DATA_MIRRORED = SynchedEntityData.defineId(RedmistSlash.class, EntityDataSerializers.BOOLEAN);
        DATA_VERTICAL = SynchedEntityData.defineId(RedmistSlash.class, EntityDataSerializers.BOOLEAN);
    }
}
