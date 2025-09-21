package net.axiom.mahouphantasm.spell;

import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import net.axiom.mahouphantasm.MahouPhantasm;
import net.minecraft.resources.ResourceLocation;


public class MSpellAnimations {
    public static ResourceLocation ANIMATION_RESOURCE = ResourceLocation.fromNamespaceAndPath(MahouPhantasm.MOD_ID, "animation");

    public static final AnimationHolder MIMICRY_UPSWING = new AnimationHolder(MahouPhantasm.MOD_ID + ":mimicry_upswing", true);
    public static final AnimationHolder MIMICRY_SLASH = new AnimationHolder(MahouPhantasm.MOD_ID + ":mimicry_slash", true);

    public MSpellAnimations() {
    }
}
