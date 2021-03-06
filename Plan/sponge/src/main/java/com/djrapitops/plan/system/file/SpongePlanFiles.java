/*
 *  This file is part of Player Analytics (Plan).
 *
 *  Plan is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License v3 as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Plan is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Plan. If not, see <https://www.gnu.org/licenses/>.
 */
package com.djrapitops.plan.system.file;

import com.djrapitops.plan.PlanPlugin;
import org.spongepowered.api.Sponge;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Implements jar resource fetching with Sponge Asset API.
 *
 * @author Rsl1122
 */
@Singleton
public class SpongePlanFiles extends PlanFiles {

    @Inject
    public SpongePlanFiles(PlanPlugin plugin) {
        super(plugin);
    }

    @Override
    public Resource getResourceFromJar(String resourceName) {
        try {
            return new SpongeAssetResource(resourceName, Sponge.getAssetManager().getAsset(plugin, resourceName).orElse(null));
        } catch (IllegalStateException spongeNotEnabled) {
            return super.getResourceFromJar(resourceName);
        }
    }
}