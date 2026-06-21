package org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.data;

import org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.common.AAAPWF;
import org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.common.AAAPWFItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

import javax.annotation.ParametersAreNonnullByDefault;

public class AAAPWFModelProvider extends ModelProvider {
    public AAAPWFModelProvider(PackOutput output) {
        super(output, AAAPWF.MOD_ID);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(AAAPWFItems.THE_FILTER.get(), ModelTemplates.FLAT_ITEM);
    }
}
