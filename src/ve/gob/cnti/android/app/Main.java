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
import ve.gob.cnti.android.info.Constants;
import ve.gob.cnti.android.info.Preferences;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Main extends Activity implements Constants {

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
					"Error al crear la base de datos de Gobierno Móvil");
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

	/**
	 * Selecciona opcion del menu segun lo seleccionado.
	 */
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

	/**
	 * Implementando la interfaz para escuchar los clicks del usuario en la
	 * Activity.
	 * 
	 * @param v
	 *            Vista que recibió el click
	 * 
	 * @author Richard Ricciardelli
	 */
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.directory_main:
			showActivity(Directory.class, 0);
			break;
		case R.id.procedures_main:
			// showActivity(Procedures.class, 0);
			break;
		case R.id.government_main:
			showActivity(Government.class, 0);
			break;
		}
	}

	/**
	 * Muestra la pantalla seleccionada.
	 * 
	 * @param c
	 *            Clase a activar en Activity
	 * @param item
	 *            Información que pasa a través de las Activity
	 * 
	 * @author Richard Ricciardelli
	 */
	public void showActivity(Class<?> c, int item) {
		if (Preferences.getVibration(getApplicationContext()))
			setVibration(VIBRATION_INTENT);
		startActivity(new Intent(this, c).putExtra("item", item));
	}

	/**
	 * Colocar el valor de vibración.
	 * 
	 * @param ms
	 *            Milisegundos
	 * 
	 * @author Richard Ricciardelli
	 */
	public void setVibration(int ms) {
		((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(ms);
	}

	/**
	 * Metodo que lista los nombre de las instituciones segun el ID que tengan
	 * 
	 * @param context
	 *            (La clase que va ejecutar el metodo)
	 * @param query
	 *            (Nombre de la tabla para completar el query que hace la
	 *            consulta a la base de datos)
	 * 
	 * @return
	 */
	public String[] getListItems(Context context, String query) {
		openDatabase(context);
		Cursor cursor = db.rawQuery("SELECT nombre FROM " + query, null);
		String[] items = new String[cursor.getCount()];
		short i = 0;
		if (cursor.moveToFirst())
			do {
				items[i] = cursor.getString(0);
				i++;
			} while (cursor.moveToNext());
		cursor.close();
		closeDatabase();
		return items;
	}
}
