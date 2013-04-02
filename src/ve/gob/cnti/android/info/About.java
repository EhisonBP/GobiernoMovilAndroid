package ve.gob.cnti.android.info;

import ve.gob.cnti.android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class About extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
	}

	public void onMainClick(View button) {
		finish();
	}
	
}
