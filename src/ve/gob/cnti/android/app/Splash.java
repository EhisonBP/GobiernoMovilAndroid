package ve.gob.cnti.android.app;

import java.util.Timer;
import java.util.TimerTask;

import ve.gob.cnti.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		StarAnimations();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Intent intent = new Intent().setClass(Splash.this, Main.class);
				startActivity(intent);
				finish();
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 6000);
	}

	/**
	 * Meodo que realiza la animacion de cada unos de los elementos que estan en
	 * el layout de Splash.
	 */
	public void StarAnimations() {
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
		animation.reset();
		LinearLayout layout = (LinearLayout) findViewById(R.id.splash);
		layout.clearAnimation();
		layout.startAnimation(animation);

		animation = AnimationUtils.loadAnimation(this, R.anim.translate);
		animation.reset();
		ImageView imageView = (ImageView) findViewById(R.id.logoSplash);
		imageView.clearAnimation();
		imageView.startAnimation(animation);
	}
}