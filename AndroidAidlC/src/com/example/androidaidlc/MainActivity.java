package com.example.androidaidlc;

import com.example.androidaidls.IMyAidlInterface;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button button;
	TextView mTextview;
	private IMyAidlInterface iMyAidlInterface;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.Button1);
		mTextview = (TextView) findViewById(R.id.textname);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(iMyAidlInterface!=null)
				try {
					String name = iMyAidlInterface.getName();
					mTextview.setText(name);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				else
					Toast.makeText(MainActivity.this, "未连接aidl服务", Toast.LENGTH_SHORT).show();				
			}

		});
		Intent intent = new Intent();
		intent.setComponent(new ComponentName("com.example.androidaidls", "com.example.androidaidls.MyService"));
		bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
	}

	private ServiceConnection mServiceConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			iMyAidlInterface = null;
		}
	};

	@Override
	public void onBackPressed() {
		finish();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
