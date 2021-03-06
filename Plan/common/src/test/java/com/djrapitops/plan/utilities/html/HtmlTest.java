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
package com.djrapitops.plan.utilities.html;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for different functions of the {@link Html} class.
 *
 * @author Rsl1122
 */
@RunWith(JUnitPlatform.class)
class HtmlTest {

    @Test
    void parsingWithNoArgsDoesNotReplacePlaceholder() {
        String expResult = "${0}</span>";
        String result = Html.SPAN.parse();

        assertEquals(expResult, result);
    }

    @Test
    void parsingWithArgsReplacesPlaceholder() {
        String expResult = "Test</span>";
        String result = Html.SPAN.parse("Test");

        assertEquals(expResult, result);
    }

    @Test
    void colorsToSpanResetsColors() {
        String testString = "§fHello, §aPerson§r - How Are you?";
        String expected = Html.COLOR_F.parse() + "Hello, " + Html.COLOR_A.parse() + "Person</span></span> - How Are you?";
        String result = Html.swapColorCodesToSpan(testString);
        assertEquals(expected, result);
    }
}
