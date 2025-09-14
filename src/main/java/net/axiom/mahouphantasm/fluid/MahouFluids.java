//package net.axiom.mahouphantasm.fluid;
//
//import com.simibubi.create.foundation.data.CreateRegistrate;
//import com.tterrag.registrate.util.entry.FluidEntry;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraftforge.fluids.ForgeFlowingFluid;
//import net.axiom.mahouphantasm.MahouPhantasm;
//
//public class MahouFluids {
//
//    private static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MahouPhantasm.MOD_ID);
//    public static void register() {
//    }
//
//    private static final ResourceLocation LUMISENE_STILL_RL = new ResourceLocation(MahouPhantasm.MOD_ID, "block/lumisene_still");
//    private static final ResourceLocation LUMISENE_FLOW_RL  = new ResourceLocation(MahouPhantasm.MOD_ID, "block/lumisene_flow");
//
//    public static final FluidEntry<ForgeFlowingFluid.Flowing> LUMISENE = REGISTRATE
//            .fluid(
//                    "lumisene",                  // registry name
//                    LUMISENE_STILL_RL,           // still texture
//                    LUMISENE_FLOW_RL,            // flowing texture
//                    CreateRegistrate::defaultFluidType // FluidType Create
//            )
//            .properties(b -> b
//                    .viscosity(800)
//                    .density(1000)
//            )
//            .fluidProperties(fp -> fp
//                    .levelDecreasePerBlock(1)
//                    .tickRate(5)
//                    .slopeFindDistance(4)
//                    .explosionResistance(100f)
//            )
//            .source(ForgeFlowingFluid.Source::new) // source
//            .bucket()
//            .build()
//            .register();
//}
