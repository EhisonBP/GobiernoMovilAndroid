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

package ve.gob.cnti.android.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ve.gob.cnti.android.info.Constants;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper implements Constants {

	private SQLiteDatabase dataBase;
	private final Context context;

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
	}

	/**
	 * Método para validar que el archivo se encuentra en la ruta predeterminada
	 * 
	 * @author Ehison Pérez
	 * @return <code>true</code> si la base de datos existe, <code>false</code>
	 *         de lo contrario
	 */
	private boolean checkDataBase() {
		File dbFile = new File(DB_PATH + DB_NAME);
		return dbFile.exists();
	}

	/**
	 * Método que se utiliza en el Main de la aplicación para crear la base de
	 * datos dentro de ella
	 * 
	 * @author Ehison Pérez
	 * @throws IOException
	 */
	public void createDataBase(Context context) throws IOException {
		boolean dbExist = checkDataBase();
		if (!dbExist) {
			try {
				this.getReadableDatabase();
				copyDataBase();
			} catch (Exception e) {
				throw new Error(e.getMessage());
			}
		} else {
			Log.i("DataBase",
					"Entra en este condicion en caso de que la base de datos se encuetra "
							+ "registrada y se consultara para actualizarla");
			deletedDataBase(SearchVersion(), DB_VERSION);
		}
	}

	/**
	 * Método que permite copiar el archivo de la base de datos en la carpeta de
	 * instalación del sistema
	 * 
	 * @author Ehison Pérez
	 * @throws IOException
	 */
	public void copyDataBase() throws IOException {
		/**
		 * Abrimos la base de datos de la carpeta assets con un objeto de la
		 * clase InputStream.
		 */
		InputStream myInput = context.getAssets().open(DB_NAME);
		/**
		 * Carpeta de destino donde hemos creado la base de datos vacía.
		 */
		String outFileName = DB_PATH + DB_NAME;
		/**
		 * Abrimos la base de datos vacía con un objeto de la clase
		 * OutputStream.
		 */
		OutputStream myOutput = new FileOutputStream(outFileName);
		/**
		 * Transfiere los bytes entre el stream de entrada y el de salida.
		 */
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		/**
		 * Cerramos los ficheros abiertos.
		 */
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	/**
	 * Método para abrir la base de datos
	 * 
	 * @author Ehison Pérez
	 * @throws SQLException
	 */
	public void openDatabaBase() throws SQLException {
		String myPath = DB_PATH + DB_NAME;
		dataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);
	}

	/**
	 * Método utilizado para poder cerra la base de datos
	 * 
	 * @author Ehison Pérez
	 */
	public synchronized void close() {
		if (dataBase != null)
			dataBase.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

	/**
	 * Metodo para BUscar la Version de la
	 * 
	 * @param version
	 * @return
	 */
	public int SearchVersion() {
		dataBase = getWritableDatabase();
		int i = 0;
		Cursor cursor = dataBase.rawQuery("SELECT version FROM actualizacion",
				null);
		if (cursor.moveToFirst()) {
			do {
				i = cursor.getInt(0);
				Log.i("DataBase", "La version de la base de datos es: " + i);
			} while (cursor.moveToNext());
		}
		return i;
	}

	public void deletedDataBase(int oldVersion, int newVersion) throws IOException {
		if (oldVersion != newVersion) {
			Log.i("DataBase", "Eliminando Base de Datos Vieja");
			context.deleteDatabase(DB_NAME);
			Log.i("DataBase", "Base de datos Vieja Eliminada");
			createDataBase(context);
		}
	}

}