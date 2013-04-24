package ve.gob.cnti.android.app;

import ve.gob.cnti.android.R;
import ve.gob.cnti.android.info.Constants;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class Directory extends Main implements Constants {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.directory);

		// Ingresando valores del Array dentro de la el chelist de la
		// aplicacion Gob Linea Android
		String[] powersArray = getResources().getStringArray(R.array.powers);
		final String[] statesArray = getResources().getStringArray(
				R.array.states);
		Spinner spinner = (Spinner) findViewById(R.id.spinner_directory);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, powersArray);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		// Ingresando valores para el ListView segun el poder seleccionado en
		// caso que la selleccion se el poder municipal se cargara otro Array
		// con los estados,
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent,
					android.view.View v, int position, long id) {
				String[] agencies = getListItems(Directory.this, AGENCIES
						+ " WHERE poder = " + (position + 1)
						+ " ORDER BY nombre");
				switch (position) {
				case 5:
					addItems(agencies, 5);
					break;
				case 6:
					addItems(statesArray, 6);
					break;
				default:
					addItems(agencies, position);
					break;
				}
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

	}

	private void addItems(String[] items, final int power) {
		final ListView list = (ListView) findViewById(R.id.list_directory);
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		list.setAdapter(listAdapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				itemAction(power, position, list.getItemAtPosition(position)
						.toString());
			}
		});
	}

	private void itemAction(int power, int position, String item) {
		switch (power) {
		case 5:
			String[] officers = getArrayFromCursor(this, "SELECT * FROM "
					+ AGENCIES + " WHERE nombre = '" + item + "' AND poder = "
					+ (power + 1));
			Show.setDialog(this, officers);
			break;
		case 6:
			showActivity(Mayoralties.class, position + 1);
			break;
		default:
			String[] agencies = getArrayFromCursor(this, "SELECT * FROM "
					+ AGENCIES + " WHERE nombre = '" + item + "' AND poder = "
					+ (power + 1));
			Show.setDialog(this, agencies);
			break;
		}
	}

	/**
	 * Metodo para terminar la actividad
	 * 
	 * @param button
	 */
	public void onMainClick(View button) {
		finish();
	}
}
