package org.blanco.sportstablet;

import org.blanco.sportstablet.views.DrawingSurface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements
	View.OnClickListener{

	public static final String TAG = "sports_tablet";
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initComponents();
    }

    /***
     * initiates the components of this activity
     */
	private void initComponents() {
	
		actionBar = (LinearLayout) findViewById(R.id.main_act_action_bar);
		if (actionBar != null){
			btnClear = (Button) actionBar.findViewById(R.id.action_bar_btn_clear);
			btnClear.setOnClickListener(this);
		}
		surface = (DrawingSurface) findViewById(R.id.main_act_draw_surface);
		if (surface == null)
			Toast.makeText(this, "surface not found", 500).show();
		else{
			surface.setBackgroundDrawable(retrieveBackgroundField(this));
		}
	}
    
	public Drawable retrieveBackgroundField(Context context){
		String backName = PreferenceManager.getDefaultSharedPreferences(this)
			.getString(SettingsActivity.FIELD_TYPE_PREF_NAME,"uno");
		int identifier = 
			getResources().getIdentifier("volleyball_court", "drawable", "org.blanco.sportstablet");
		Drawable result = null;
		if (identifier > 0){
			result = getResources().getDrawable(identifier);
		}
		return result;
	}
	
    /***
     * The implementation of the ClickListeners that are set to this object.
     * Select the action to performed based on the Id of the pressed View.
     */
	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
			case R.id.action_bar_btn_clear:
				if (surface != null) surface.clear();
			break;
		}
	}

	/***
	 * Inflate the options menu for the activity
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		try{
			new MenuInflater(this).inflate(R.menu.main_act_main_menu, menu);
		}catch(Exception e){
			Log.e(TAG, "Error Inflating Options Menu",e);
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.main_act_main_menu_settings:
				startActivity(new Intent(this,SettingsActivity.class));
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onStart() {
		loadPaintPreferences();
		super.onStart();
	}

	/***
	 * Loads the preferences of the Paint from the application settings.
	 */
	private void loadPaintPreferences(){
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		//String color = pref.getString(SettingsActivity.PAINT_COLOR_PREF_NAME, "White"); //Default white
		//surface.setPaintColor(Color.BLUE);
		final String width = pref.getString(SettingsActivity.PAINT_WIDTH_PREF_NAME, "10.0"); //Default 10;
		surface.reloadPaintWidth(Float.parseFloat(width));
	}



	LinearLayout actionBar = null;
    Button btnClear = null;
    DrawingSurface surface = null;
}