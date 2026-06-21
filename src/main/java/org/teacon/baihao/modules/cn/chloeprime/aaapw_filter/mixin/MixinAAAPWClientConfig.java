package org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.mixin;

import cn.chloeprime.aaa_particles_world.client.ClientConfig;
import org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.client.AAAPWConfigController;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientConfig.class)
public class MixinAAAPWClientConfig {
    @WrapOperation(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;define(Ljava/lang/String;Z)Lnet/neoforged/neoforge/common/ModConfigSpec$BooleanValue;"))
    private static ModConfigSpec.BooleanValue setAllDefaultValueToFalse(ModConfigSpec.Builder specBuilder, String path, boolean defaultValue, Operation<ModConfigSpec.BooleanValue> original) {
        return original.call(specBuilder, path, AAAPWConfigController.FILTER_EXEMPTS.contains(path) && defaultValue);
    }
}
