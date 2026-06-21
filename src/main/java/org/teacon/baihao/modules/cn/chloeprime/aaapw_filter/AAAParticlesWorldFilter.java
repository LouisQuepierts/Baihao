package org.teacon.baihao.modules.cn.chloeprime.aaapw_filter;

import org.teacon.baihao.Baihao;
import org.teacon.baihao.modules.cn.chloeprime.aaapw_filter.common.AAAPWF;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Baihao.MODID)
public class AAAParticlesWorldFilter {
    public static final String MOD_ID = "aaapw_filter";

    public AAAParticlesWorldFilter(IEventBus modbus) {
        AAAPWF.init(modbus);
    }
}
