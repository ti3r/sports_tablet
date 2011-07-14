package org.blanco.sportstablet;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity {
	
	public static final String PAINT_COLOR_PREF_NAME = "settings_act_pref_paint_color";
	public static final String PAINT_WIDTH_PREF_NAME = "settings_act_pref_paint_width";
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.main_setttings);
	}
}
