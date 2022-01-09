// This file is part of OceanEdit.
//
// OceanEdit is free software: you can redistribute it and/or modify it under the terms of the GNU General
// Public License as published by the FreeSoftware Foundation, either version 2 of the License, or (at
// your option) any later version.
//
// OceanEdit is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
// the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License along with OceanEdit. If not, see
// <https://www.gnu.org/licenses/>.

module com.oceanedit {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.oceanedit to javafx.fxml;
    exports com.oceanedit;
    exports com.oceanedit.Controllers;
    opens com.oceanedit.Controllers to javafx.fxml;
}