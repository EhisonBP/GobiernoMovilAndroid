/*
 * Licencia GPL v3
 * 
 * Copyright (C) 2012 Centro Nacional de Tecnologías de Información.
 * Gobierno Móvil es un producto de Gobierno en Línea Venezuela.
 * 
 * Copyright (C) 2013 Richard Ricciardelli. All Rights Reserved.
 * Copyright (C) 2013 Ehison Pérez. All Rights Reserved.
 * Copyright (C) 2013 Gerardo Pérez. All Rights Reserved.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see http://www.gnu.org/licenses
 */

package ve.gob.cnti.android.info;

import android.provider.BaseColumns;

public interface Constants extends BaseColumns {

	// Constantes para la actualizacion de la base de datos
	public static final String DB_NAME = "GobMovil.sqlite";
	public static final String DB_PATH = "/data/data/ve.gob.cnti.android/databases/";
	public static int DB_VERSION = 2;

	// Constantes de nombre de las tablas en la base de datos y sintaxis de sql
	public static final String POWERS = "poder";
	public static final String STATES = "estado";
	public static final String MUNICIPALITIES = "municipios";
	public static final String AGENCIES = "institucion";
	public static final String PROCEDURES = "tramites";
	public static final String MAYORALTIES = "alcaldias";
	public static final String WHERE = " WHERE ";
	public static final String OR = " OR ";

	// Constantes para la funcion de vibracion al ingresar a cualquier seccion
	// de la app
	public static final int VIBRATION_ERROR = 80;
	public static final int VIBRATION_INTENT = 30;
	public static final int WAITING_PERIOD = 2000;

}
