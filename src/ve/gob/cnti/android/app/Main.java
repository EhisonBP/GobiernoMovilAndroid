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

package ve.gob.cnti.android.app;

import ve.gob.cnti.android.R;
import ve.gob.cnti.android.database.DatabaseHelper;
import ve.gob.cnti.android.info.Preferences;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Main extends Activity {

	public static DatabaseHelper myDataBase;

	/**
	 * Método que lanza la vista principal de la Actividad Main
	 * 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myDataBase = new DatabaseHelper(this);
		try {
			myDataBase.createDataBase(this);
		} catch (Exception e) {
			Log.e("DataBase",
					"Error al crear la base de datos de Gobierno Movil");
		}
	}

	/**
	 * Crea el menú de opciones
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.preferences:
			Intent intent = new Intent(this, Preferences.class);
			startActivity(intent);
			break;
		}
		return false;
	}
}
