package org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.data;

import org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.common.AAAPWF;
import org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.common.AAAPWFItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class AAAPFWItemTagProvider extends ItemTagsProvider {
    public AAAPFWItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registry) {
        super(output, registry, AAAPWF.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        tag(ItemTags.create(Identifier.parse("curios:aaapw_filter_bci")))
                .add(AAAPWFItems.THE_FILTER.get());
    }
}
