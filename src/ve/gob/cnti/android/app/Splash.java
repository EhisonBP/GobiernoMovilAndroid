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
		startAnimations();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Intent intent = new Intent().setClass(Splash.this, Main.class);
				startActivity(intent);
				finish();
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 5000);
	}

	/**
	 * Método que realiza la animación de cada unos de los elementos que están
	 * en el layout de Splash.
	 */
	public void startAnimations() {
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