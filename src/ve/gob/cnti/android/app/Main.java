package ve.gob.cnti.android.app;

import ve.gob.cnti.android.R;
import ve.gob.cnti.android.database.DatabaseHelper;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class Main extends Activity {

	public static DatabaseHelper myDataBase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myDataBase = new DatabaseHelper(this);
		try {
			myDataBase.createDataBase(this);
		} catch (Exception e) {
			throw new Error("Error al crear la base de datos ");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
