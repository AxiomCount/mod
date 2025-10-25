package net.axiom.mahouphantasm.spell;

import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import net.axiom.mahouphantasm.MahouPhantasm;
import net.minecraft.resources.ResourceLocation;


public class MSpellAnimations {
    public static ResourceLocation ANIMATION_RESOURCE = ResourceLocation.fromNamespaceAndPath(MahouPhantasm.MOD_ID, "animation");

    public static final AnimationHolder UPSTANDING_SLASH_UPSWING = new AnimationHolder(MahouPhantasm.MOD_ID + ":upstanding_slash_upswing", true);
    public static final AnimationHolder UPSTANDING_SLASH_FINISH = new AnimationHolder(MahouPhantasm.MOD_ID + ":upstanding_slash_finish", true);
//    public static final AnimationHolder SWORD_UPWARD = new AnimationHolder(MahouPhantasm.MOD_ID + ":sword_upward", true);

    public MSpellAnimations() {
    }
}
