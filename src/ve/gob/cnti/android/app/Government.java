package ve.gob.cnti.android.app;

import ve.gob.cnti.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class Government extends Activity {

	EditText NAME;
	EditText EMAILTEXT;
	RadioButton SUBJECT;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.government);
		NAME = (EditText) findViewById(R.id.name);
		EMAILTEXT = (EditText) findViewById(R.id.email_text);
		SUBJECT = (RadioButton) findViewById(R.id.general);
	}

	public void onSendClick(View button) {
		String radioButtonSelected;
		radioButtonSelected = SUBJECT.isChecked() ? getString(R.string.general)
				: getString(R.string.news);
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
				new String[] { getString(R.string.email) });
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
				getString(R.string.government_online) + " - "
						+ radioButtonSelected.toString());
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
				EMAILTEXT.getText() + "\n\n\n" + NAME.getText() + "\n\n"
						+ getString(R.string.signature));
		Government.this.startActivity(Intent.createChooser(emailIntent,
				getString(R.string.select)));
	}

}
