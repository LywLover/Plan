package com.djrapitops.plan.modules.server.sponge;

import com.djrapitops.plan.PlanSponge;
import com.djrapitops.plan.system.info.server.properties.ServerProperties;
import com.djrapitops.plan.system.info.server.properties.SpongeServerProperties;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Dagger module for Sponge ServerProperties.
 *
 * @author Rsl1122
 */
@Module
public class SpongeServerPropertiesModule {

    @Provides
    @Singleton
    ServerProperties provideServerProperties(PlanSponge plugin) {
        return new SpongeServerProperties(plugin.getGame());
    }
}