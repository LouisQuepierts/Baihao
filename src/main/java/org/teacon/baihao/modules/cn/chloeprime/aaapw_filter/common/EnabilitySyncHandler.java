package org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.common;

import org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.client.AAAPWConfigController;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.attachment.AttachmentSyncHandler;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

class EnabilitySyncHandler implements AttachmentSyncHandler<Boolean> {
    @Override
    @ParametersAreNonnullByDefault
    public boolean sendToPlayer(IAttachmentHolder holder, ServerPlayer to) {
        return holder == to;
    }

    @Override
    public void write(@Nonnull RegistryFriendlyByteBuf buf, Boolean attachment, boolean initialSync) {
        buf.writeBoolean(attachment);
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NonNull Boolean read(IAttachmentHolder holder, RegistryFriendlyByteBuf buf, @Nullable Boolean previousValue) {
        var result = buf.readBoolean();
        AAAPWConfigController.update(result);
        return result;
    }
}
