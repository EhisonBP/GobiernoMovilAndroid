package ve.gob.cnti.android.app;

import ve.gob.cnti.android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Directory extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.directory);
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
