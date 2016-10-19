package com.voy;

import android.app.Activity;
import android.content.Context;
import java.util.Stack;

public class AppManager{

	private static Stack<Activity> activityStack;// = new
													// LinkedList<Activity>();
	private static AppManager instance;

	private AppManager() {
	}

	
	public static AppManager getInstance() {
		if (null == instance) {
			synchronized (AppManager.class) {
				if (null == instance) {
					instance = new AppManager();
				}
			}
		}
		return instance;
	}

	
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	public synchronized void removeActivity(Activity activity) {
		if (activityStack.contains(activity)) {
			activityStack.remove(activity);
		}
	}



	
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();

	}


	public void exitApp(Context context) {
		finishAllActivity();
		System.gc();
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
