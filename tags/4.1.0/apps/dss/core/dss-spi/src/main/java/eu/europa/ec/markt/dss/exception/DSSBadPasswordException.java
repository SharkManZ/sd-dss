/*
 * DSS - Digital Signature Services
 *
 * Copyright (C) 2013 European Commission, Directorate-General Internal Market and Services (DG MARKT), B-1049 Bruxelles/Brussel
 *
 * Developed by: 2013 ARHS Developments S.A. (rue Nicolas Bové 2B, L-1253 Luxembourg) http://www.arhs-developments.com
 *
 * This file is part of the "DSS - Digital Signature Services" project.
 *
 * "DSS - Digital Signature Services" is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * DSS is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * "DSS - Digital Signature Services".  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.europa.ec.markt.dss.exception;

import java.util.ResourceBundle;

/**
 * Thrown when using bad password
 *
 * @version $Revision: 3113 $ - $Date: 2013-12-04 16:00:22 +0100 (Wed, 04 Dec 2013) $
 */

public class DSSBadPasswordException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ResourceBundle bundle = ResourceBundle.getBundle("eu/europa/ec/markt/dss/i18n");

    private MSG key;

    /**
     * Supported messages
     */
    public enum MSG {
        PKCS11_BAD_PASSWORD, PKCS12_BAD_PASSWORD, JAVA_KEYSTORE_BAD_PASSWORD
    }

    /**
     * The default constructor for DSSBadPasswordException.
     *
     * @param message
     */
    public DSSBadPasswordException(MSG message) {
        init(message);
    }

    public DSSBadPasswordException(MSG message, Throwable cause) {
        super(cause);
        init(message);
    }

    private void init(MSG message) {
        if (message == null) {
            throw new IllegalArgumentException("Cannot build Exception without a message");
        }
        this.key = message;
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(key.toString());
    }

}
