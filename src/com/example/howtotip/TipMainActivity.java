package com.example.howtotip;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class TipMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tip_main);
		// Request focus and show soft keyboard automatically
		EditText etBill = (EditText) findViewById(R.id.editTextYourBill);

		etBill.requestFocus();

		InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		// only will trigger it if no physical keyboard is open
		mgr.showSoftInput(etBill, InputMethodManager.SHOW_IMPLICIT);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tip_main, menu);
		return true;
	}
	
	public void calcTips(View v) {
		float tipRate = 0.0f;
		float total = 0.0f;
		float bill = 0.0f;
		float totalTips = 0.0f;
		
		EditText tvBill = (EditText) findViewById(R.id.editTextYourBill);
		TextView tvCalTips = (TextView) findViewById(R.id.textViewCalcTips);
		TextView tvCalcTotal = (TextView) findViewById(R.id.textViewCalcTotal);
		 
		try {
			bill = Float.valueOf(tvBill.getText().toString().trim());
		} catch (NumberFormatException e){
			bill = 0.0f;
		}
		Log.d("calcTip click handler", v.toString());
		
		switch (v.getId()) {
			case(R.id.buttonTipRateCheap):
				Log.d("calc switch", "buttonTipRateCheap pressed");
				tipRate = 0.10f;
				break;
			case(R.id.buttonTipRateNormal):
				Log.d("calc switch", "buttonTipRateNormal pressed");
				tipRate = 0.15f;
				break;
			case(R.id.buttonTipRateGenerous):
				Log.d("calc switch", "buttonTipRateGenerous pressed");
				tipRate = 0.20f;
				break;
			default:
				Log.d("calc switch", "Hey I don't know this button");
		}
		DecimalFormat f = new DecimalFormat("#0.00");
		
		total = bill + (bill * tipRate);
		totalTips = (bill * tipRate);
		tvCalTips.setText(f.format(totalTips));
		tvCalcTotal.setText(f.format(total));
	}

}
