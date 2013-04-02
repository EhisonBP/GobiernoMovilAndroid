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

import ve.gob.cnti.android.R;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

public class Preferences extends PreferenceActivity implements
		SharedPreferences.OnSharedPreferenceChangeListener {

	private static final String OPT_VIBRATION = "vibration";
	private static final boolean OPT_VIBRATION_DEF = true;
	private static final String OPT_SEARCH = "search";
	private static final boolean OPT_SEARCH_DEF = false;
	private static final String OPT_SUGGESTIONS = "suggestions";
	private static final boolean OPT_SUGGESTIONS_DEF = true;
	private static final String OPT_RESULTS = "results";
	private static final boolean OPT_RESULTS_DEF = true;
	private static final String OPT_ABOUT = "about";
	private static final String OPT_LICENSE = "license";
	private static final String OPT_HELP = "help";
	private static final String OPT_UPDATE = "automatic_update";
	private static final boolean OPT_UPDATE_DEF = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference);
		PreferenceManager.getDefaultSharedPreferences(this)
				.registerOnSharedPreferenceChangeListener(this);
		getPreferenceManager().findPreference(OPT_ABOUT).setTitle(
				getString(R.string.about_title, getString(R.string.version)));
		setListener(OPT_LICENSE, License.class);
		setListener(OPT_ABOUT, About.class);

	}

	private void setListener(final String key, final Class<?> c) {
		Preference preference = getPreferenceManager().findPreference(key);
		preference
				.setOnPreferenceClickListener(new OnPreferenceClickListener() {
					public boolean onPreferenceClick(Preference preference) {
						if (key.equals(OPT_SEARCH))
							Log.i("Preferencias", "Nada"); // alertDialog();
						else
							startActivity(new Intent(preference.getContext(), c));
						return false;
					}
				});
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
		// TODO Auto-generated method stub

	}
}
