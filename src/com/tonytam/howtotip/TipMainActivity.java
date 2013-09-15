package com.tonytam.howtotip;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TipMainActivity extends Activity implements TextWatcher {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tip_main);
		
	}
	
	protected void onStart() {
		super.onStart();
		
		// Request focus and show soft keyboard automatically
		EditText etBill = (EditText) findViewById(R.id.editTextYourBill);

		etBill.requestFocus();	
		showSoftKeyboard(etBill);
		
		etBill.addTextChangedListener(this);
	}

	/* (non-Javadoc)
	 * @see android.text.TextWatcher#beforeTextChanged(java.lang.CharSequence, int, int, int)
	 */
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence, int, int, int)
	 */
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterTextChanged(Editable t) {
		Button buttonNormal = (Button) findViewById(R.id.buttonTipRateNormal);
		Button buttonCheap = (Button) findViewById(R.id.buttonTipRateCheap);
		Button buttonGenerous = (Button) findViewById(R.id.buttonTipRateGenerous);	
		buttonNormal.setEnabled(true);
		buttonGenerous.setEnabled(true);
		buttonCheap.setEnabled(true);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tip_main, menu);
		return true;
	}
	
	public void showSoftKeyboard(View view) {
	    if (view.requestFocus()) {
	        InputMethodManager imm = (InputMethodManager)
	                getSystemService(Context.INPUT_METHOD_SERVICE);
	        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
	    }
	}
	
	
	public void calcTips(View v) {
		float tipRate = 0.0f;
		float total = 0.0f;
		float bill = 0.0f;
		float totalTips = 0.0f;
		
		Button tipButton = (Button) v; 
		EditText tvBill = (EditText) findViewById(R.id.editTextYourBill);
		TextView tvCalTips = (TextView) findViewById(R.id.textViewCalcTips);
		TextView tvCalcTotal = (TextView) findViewById(R.id.textViewCalcTotal);
		Button buttonNormal = (Button) findViewById(R.id.buttonTipRateNormal);
		Button buttonCheap = (Button) findViewById(R.id.buttonTipRateCheap);
		Button buttonGenerous = (Button) findViewById(R.id.buttonTipRateGenerous);
		
		try {
			bill = Float.valueOf(tvBill.getText().toString().trim());
		} catch (NumberFormatException e){
			bill = 0.0f;
		}
		// Log.d("calcTip click handler", v.toString());
		tipButton.setEnabled(false);

		switch (tipButton.getId()) {
			case(R.id.buttonTipRateCheap):
				// Log.d("calc switch", "buttonTipRateCheap pressed");
				buttonNormal.setEnabled(true);
				buttonGenerous.setEnabled(true);
				tipRate = 0.10f;
				break;
			case(R.id.buttonTipRateNormal):
				// Log.d("calc switch", "buttonTipRateNormal pressed");
				tipRate = 0.15f;
				buttonGenerous.setEnabled(true);
				buttonCheap.setEnabled(true);
				break;
			case(R.id.buttonTipRateGenerous):
				// Log.d("calc switch", "buttonTipRateGenerous pressed");
				tipRate = 0.20f;
				buttonNormal.setEnabled(true);
				buttonCheap.setEnabled(true);
				break;
			default:
				// Log.d("calc switch", "Hey I don't know this button");
		}
		DecimalFormat f = new DecimalFormat("#0.00");
		
		total = bill + (bill * tipRate);
		totalTips = (bill * tipRate);
		tvCalTips.setText(f.format(totalTips));
		tvCalcTotal.setText(f.format(total));
	}
	
	@Override
	public boolean onOptionsItemSelected (MenuItem item){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.author);
		
		builder.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // User cancelled the dialog
	           }
	       });
		
		AlertDialog dialog = builder.create();
		dialog.show();
		Log.d("onOptionsItemSelected", "button click");
		return true;
	}

}
