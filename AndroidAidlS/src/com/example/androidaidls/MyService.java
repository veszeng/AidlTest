package com.example.androidaidls;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	public MyService() {
	}
	Handler handler;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		Log.i("MyService", "--------------onBind");
		return new MyBinder();
	}
	class MyBinder extends IMyAidlInterface.Stub {

		@Override
		public String getName() throws RemoteException {
			Log.i("MyService", "--------------getName");
			// TODO Auto-generated method stub
			handler = new Handler(Looper.getMainLooper());
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(getApplicationContext(), "zeng", Toast.LENGTH_SHORT).show();
				}
			});
			return "zeng";
		}

		@Override
		public String getAge() throws RemoteException {
			Log.i("MyService", "--------------getAge");
			// TODO Auto-generated method stub
			return "22";
		}

	}
}
