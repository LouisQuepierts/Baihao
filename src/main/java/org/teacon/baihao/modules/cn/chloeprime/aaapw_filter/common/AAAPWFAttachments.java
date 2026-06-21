package org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.common;

import com.mojang.serialization.Codec;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public final class AAAPWFAttachments {
    static final DeferredRegister<AttachmentType<?>> DFR = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, AAAPWF.MOD_ID);
    public static final Supplier<AttachmentType<Boolean>> AAAPW_ENABLED = DFR.register("aaapw_enabled", () -> AttachmentType
            .builder(() -> false)
            .serialize(Codec.BOOL.fieldOf("%s:aaapw_enabled".formatted(AAAPWF.MOD_ID)))
            .copyOnDeath()
            .sync(new EnabilitySyncHandler())
            .build());

    private AAAPWFAttachments() {
    }

}
