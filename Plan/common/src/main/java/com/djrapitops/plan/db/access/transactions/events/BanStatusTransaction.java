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
package com.djrapitops.plan.db.access.transactions.events;

import com.djrapitops.plan.db.access.ExecStatement;
import com.djrapitops.plan.db.access.Executable;
import com.djrapitops.plan.db.access.transactions.Transaction;
import com.djrapitops.plan.db.sql.parsing.Update;
import com.djrapitops.plan.db.sql.tables.UserInfoTable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.util.function.BooleanSupplier;

/**
 * Transaction to update a player's ban status.
 *
 * @author Rsl1122
 */
public class BanStatusTransaction extends Transaction {

    private UUID playerUUID;
    private BooleanSupplier banStatus;

    public BanStatusTransaction(UUID playerUUID, BooleanSupplier banStatus) {
        this.playerUUID = playerUUID;
        this.banStatus = banStatus;
    }

    @Override
    protected void performOperations() {
        execute(updateBanStatus());
    }

    private Executable updateBanStatus() {
        String sql = Update.values(UserInfoTable.TABLE_NAME, UserInfoTable.BANNED)
                .where(UserInfoTable.USER_UUID + "=?")
                .toString();

        return new ExecStatement(sql) {
            @Override
            public void prepare(PreparedStatement statement) throws SQLException {
                statement.setBoolean(1, banStatus.getAsBoolean());
                statement.setString(2, playerUUID.toString());
            }
        };
    }
}