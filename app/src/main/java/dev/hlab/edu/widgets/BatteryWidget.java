package dev.hlab.edu.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

public class BatteryWidget extends AppWidgetProvider {
    private static final String TAG = "BatteryWidgetTAG";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.i(TAG, "onUpdate() called");
        for (int appWidgetId : appWidgetIds) {
            Log.i(TAG, "Sono nel for");
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        AlexBroadcastReceiver receiver = new AlexBroadcastReceiver();
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.battery_widget_layout);
        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.getApplicationContext().registerReceiver(receiver, iFilter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        int batteryPct = level * 100 / scale;
        Log.i(TAG, "Livello batteria: " + batteryPct);
        //views.setTextViewText(R.id.battery_level_tv, String.valueOf(batteryPct));
        //views.setProgressBar(R.id.battery_indicator, 100, batteryPct, false);
        int color = context.getResources().getColor(R.color.green, context.getTheme());
        if (batteryPct < 10) {
            color = context.getResources().getColor(R.color.red, context.getTheme());
        } else if (batteryPct < 20) {
            color = context.getResources().getColor(R.color.orange, context.getTheme());
        } else if (batteryPct < 40) {
            color = context.getResources().getColor(R.color.yellow, context.getTheme());
        }
        switch (batteryPct) {
            case 100: case 99: case 98: case 97:
                views.setInt(R.id.slot_10, "setBackgroundColor", color);
            case 96: case 95: case 94: case 93: case 92: case 91: case 90:
                views.setInt(R.id.slot_9, "setBackgroundColor", color);
            case 89: case 88: case 87: case 86: case 85: case 84: case 83: case 82: case 81: case 80:
                views.setInt(R.id.slot_8, "setBackgroundColor", color);
            case 79: case 78: case 77: case 76: case 75: case 74: case 73: case 72: case 71: case 70:
                views.setInt(R.id.slot_7, "setBackgroundColor", color);
            case 69: case 68: case 67: case 66: case 65: case 64: case 63: case 62: case 61: case 60:
                views.setInt(R.id.slot_6, "setBackgroundColor", color);
            case 59: case 58: case 57: case 56: case 55: case 54: case 53: case 52: case 51: case 50:
                views.setInt(R.id.slot_5, "setBackgroundColor", color);
            case 49: case 48: case 47: case 46: case 45: case 44: case 43: case 42: case 41: case 40:
                views.setInt(R.id.slot_4, "setBackgroundColor", color);
            case 39: case 38: case 37: case 36: case 35: case 34: case 33: case 32: case 31: case 30:
                views.setInt(R.id.slot_3, "setBackgroundColor", color);
            case 29: case 28: case 27: case 26: case 25: case 24: case 23: case 22: case 21: case 20:
                views.setInt(R.id.slot_2, "setBackgroundColor", color);
            case 19: case 18: case 17: case 16: case 15: case 14: case 13: case 12: case 11: case 10:
                views.setInt(R.id.slot_1, "setBackgroundColor", color);
            case 9: case 8: case 7: case 6: case 5: case 4: case 3: case 2: case 1: case 0:
                views.setInt(R.id.slot_0, "setBackgroundColor", color);
        }

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

    }
}
