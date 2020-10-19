package com.example.wuhanbus.provider;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.wuhanbus.R;
import com.example.wuhanbus.service.BusStopWidgetDBSetService;

/**
 * Implementation of App Widget functionality.
 */
public class BusStopAppWidget extends AppWidgetProvider {
    private static Intent intent;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        intent = new Intent(context, BusStopWidgetDBSetService.class);
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.bus_stop_app_widget);
        rv.setRemoteAdapter(R.id.lv_busstop_widget, intent);
        appWidgetManager.updateAppWidget(appWidgetId,rv);
        //context.startService(intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        context.startService(intent);
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        super.onDisabled(context);
    }
}

