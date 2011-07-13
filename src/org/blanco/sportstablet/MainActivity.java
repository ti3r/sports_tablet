package org.blanco.sportstablet;

import org.blanco.sportstablet.views.DrawingSurface;

import android.app.Activity;
import android.os.Bundle;
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

	private void initComponents() {
	
		actionBar = (LinearLayout) findViewById(R.id.main_act_action_bar);
		if (actionBar != null){
			btnClear = (Button) actionBar.findViewById(R.id.action_bar_btn_clear);
			btnClear.setOnClickListener(this);
		}
		surface = (DrawingSurface) findViewById(R.id.main_act_draw_surface);
		if (surface == null)
			Toast.makeText(this, "surface not found", 500).show();
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

	LinearLayout actionBar = null;
    Button btnClear = null;
    DrawingSurface surface = null;
}