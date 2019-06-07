package com.example.component;

import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleComponentActivity extends Activity {
	
	private TextView tv_simple_message;
	private EditText et_simple_number;
	private Button btn_simple_submit;
	private CheckBox cb_simple_basket;
	private CheckBox cb_simple_football;
	private CheckBox cb_simple_pingpang;
	private RadioGroup rg_simple_sex;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_component);
		
		tv_simple_message=(TextView) findViewById(R.id.tv_simple_message);
		tv_simple_message.setText("IGNB");
		
		et_simple_number=(EditText) findViewById(R.id.et_simple_number);
		
		
		btn_simple_submit=(Button) findViewById(R.id.btn_simple_submit);
		btn_simple_submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String number =et_simple_number.getText().toString();
				Toast.makeText(SimpleComponentActivity.this, number, 0).show();
			}
			});
		final ImageView imageView =(ImageView) findViewById(R.id.iv_simple_play);
		imageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				//设置背景图片
				imageView.setBackgroundResource(android.R.drawable.alert_light_frame);
				//设置前景图片
				imageView.setImageResource(android.R.drawable.ic_media_pause);
				
			}
		});
		cb_simple_basket=(CheckBox) findViewById(R.id.cb_simple_basket);
		cb_simple_football=(CheckBox) findViewById(R.id.cb_simple_football);
		cb_simple_pingpang=(CheckBox) findViewById(R.id.cb_simple_pingpang);
		cb_simple_football.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
									
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO 自动生成的方法存根
				if(isChecked) {
					Toast.makeText(SimpleComponentActivity.this, "选中了足球", 0).show();
				}else {
					Toast.makeText(SimpleComponentActivity.this, "没选中足球", 0).show();
				}
			}
		});
		
		rg_simple_sex=(RadioGroup) findViewById(R.id.rg_simple_sex);
		rg_simple_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO 自动生成的方法存根
				//找到选择的radiobutton
				RadioButton radioButton =(RadioButton) findViewById(checkedId);
				//得到文本
				String sex =radioButton.getText().toString();
				//提示
				Toast.makeText(SimpleComponentActivity.this, sex, 0).show();
			}
		} );
}
	public void confirm(View v) {
		// TODO 自动生成的方法存根
		StringBuffer sb =new StringBuffer();
		if(cb_simple_basket.isChecked()) {
			sb.append(cb_simple_basket.getText().toString()).append(" ");
		}
		if(cb_simple_football.isChecked()) {
			sb.append(cb_simple_football.getText().toString()).append(" ");
		}
		if(cb_simple_pingpang.isChecked()) {
			sb.append(cb_simple_pingpang.getText().toString());
		}
		Toast.makeText(this,sb.toString(), 0).show();		
	}
}

