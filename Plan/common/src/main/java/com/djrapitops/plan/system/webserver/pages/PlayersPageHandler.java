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
package com.djrapitops.plan.system.webserver.pages;

import com.djrapitops.plan.api.exceptions.WebUserAuthException;
import com.djrapitops.plan.api.exceptions.connection.ForbiddenException;
import com.djrapitops.plan.api.exceptions.connection.WebException;
import com.djrapitops.plan.db.Database;
import com.djrapitops.plan.system.database.DBSystem;
import com.djrapitops.plan.system.webserver.Request;
import com.djrapitops.plan.system.webserver.RequestTarget;
import com.djrapitops.plan.system.webserver.auth.Authentication;
import com.djrapitops.plan.system.webserver.cache.PageId;
import com.djrapitops.plan.system.webserver.cache.ResponseCache;
import com.djrapitops.plan.system.webserver.response.Response;
import com.djrapitops.plan.system.webserver.response.ResponseFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * PageHandler for /players page.
 *
 * @author Rsl1122
 */
@Singleton
public class PlayersPageHandler implements PageHandler {

    private final DBSystem dbSystem;
    private final ResponseFactory responseFactory;

    @Inject
    public PlayersPageHandler(
            DBSystem dbSystem,
            ResponseFactory responseFactory
    ) {
        this.dbSystem = dbSystem;
        this.responseFactory = responseFactory;
    }

    @Override
    public Response getResponse(Request request, RequestTarget target) throws WebException {
        Database.State dbState = dbSystem.getDatabase().getState();
        if (dbState != Database.State.OPEN) {
            throw new ForbiddenException("Database is " + dbState.name() + " - Please try again later. You can check database status with /plan info");
        }
        return ResponseCache.loadResponse(PageId.PLAYERS.id(), responseFactory::playersPageResponse);
    }

    @Override
    public boolean isAuthorized(Authentication auth, RequestTarget target) throws WebUserAuthException {
        return auth.getWebUser().getPermLevel() <= 1;
    }
}
