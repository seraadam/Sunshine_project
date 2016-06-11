package com.shape.in.sunshine;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * {@link ForecastAdapter} exposes a list of weather forecasts
 * from a {@link android.database.Cursor} to a {@link android.widget.ListView}.
 */
public class ForecastAdapter extends CursorAdapter {
    /**
     * {@link ForecastAdapter} exposes a list of weather forecasts
     * from a {@link Cursor} to a {@link android.widget.ListView}.
     */

        private static final int VIEW_TYPE_COUNT = 2;
        private static final int VIEW_TYPE_TODAY = 0;
        private static final int VIEW_TYPE_FUTURE_DAY = 1;

        /**
         * Cache of the children views for a forecast list item.
         */
        public static class ViewHolder {
            public final ImageView iconView;
            public final TextView dateView;
            public final TextView descriptionView;
            public final TextView highTempView;
            public final TextView lowTempView;

            public ViewHolder(View view) {
                iconView = (ImageView) view.findViewById(R.id.list_item_icon);
                dateView = (TextView) view.findViewById(R.id.list_item_date_textview);
                descriptionView = (TextView) view.findViewById(R.id.list_item_forecast_textview);
                highTempView = (TextView) view.findViewById(R.id.list_item_high_textview);
                lowTempView = (TextView) view.findViewById(R.id.list_item_low_textview);
            }
        }

        public ForecastAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            // Choose the layout type
            int viewType = getItemViewType(cursor.getPosition());

            int layoutId = -1;
            switch (viewType) {
                case VIEW_TYPE_TODAY: {
                    layoutId = R.layout.list_item_forecast_today;
                    break;
                }
                case VIEW_TYPE_FUTURE_DAY: {
                    layoutId = R.layout.list_item_forecast;
                    break;
                }
            }

            View view = LayoutInflater.from(context).inflate(layoutId, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            ViewHolder viewHolder = (ViewHolder) view.getTag();

            int viewType = getItemViewType(cursor.getPosition());
            switch (viewType) {
                case VIEW_TYPE_TODAY: {

                   int weatherID = Utility.getArtResourceForWeatherCondition(cursor.getInt(ForecastFragment.COL_WEATHER_CONDITION_ID));
                    viewHolder.iconView.setImageResource(weatherID);
                    Log.i("weather id =", Integer.toString(weatherID) );
                    break;
                }
                case VIEW_TYPE_FUTURE_DAY: {

                 int   weatherID = Utility.getIconResourceForWeatherCondition(cursor.getInt(ForecastFragment.COL_WEATHER_CONDITION_ID));
                    viewHolder.iconView.setImageResource(weatherID);
                    Log.i("weather id =", Integer.toString(weatherID) );
                    break;
                }
            }

            // Use placeholder image for now


            // Read date from cursor
            long dateInMillis = cursor.getLong(ForecastFragment.COL_WEATHER_DATE);
            // Find TextView and set formatted date on it
            viewHolder.dateView.setText(Utility.getFriendlyDayString(context, dateInMillis));

            // Read weather forecast from cursor
            String description = cursor.getString(ForecastFragment.COL_WEATHER_DESC);
            // Find TextView and set weather forecast on it
            viewHolder.descriptionView.setText(description);

            // Read user preference for metric or imperial temperature units
            boolean isMetric = Utility.isMetric(context);

            // Read high temperature from cursor
            double high = cursor.getDouble(ForecastFragment.COL_WEATHER_MAX_TEMP);
            viewHolder.highTempView.setText(Utility.formatTemperature(context, high, isMetric));

            // Read low temperature from cursor
            double low = cursor.getDouble(ForecastFragment.COL_WEATHER_MIN_TEMP);
            viewHolder.lowTempView.setText(Utility.formatTemperature(context, low, isMetric));
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? VIEW_TYPE_TODAY : VIEW_TYPE_FUTURE_DAY;
        }

        @Override
        public int getViewTypeCount() {
            return VIEW_TYPE_COUNT;
        }
    }
    /**
     * Prepare the weather high/lows for presentation.
     */
//    private String formatHigh(double high) {
//        boolean isMetric = Utility.isMetric(mContext);
//        String highStr = Utility.formatTemperature(high, isMetric) ;
//        Log.i("highlowstring",highStr);
//        return highStr;
//    }
//    private String formatLows(double low) {
//        boolean isMetric = Utility.isMetric(mContext);
//        String LowStr =  Utility.formatTemperature(low, isMetric);
//        Log.i("highlowstring",LowStr);
//        return LowStr;
//    }

    /*
        This is ported from FetchWeatherTask --- but now we go straight from the cursor to the
        string.
     */
//    private String convertCursorRowToUXFormat(Cursor cursor) {
//        // get row indices for our cursor
////        int idx_max_temp = cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP);
////        int idx_min_temp = cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP);
////        int idx_date = cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_DATE);
////        int idx_short_desc = cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_SHORT_DESC);
//
//        String highAndLow = formatHighLows(
//                cursor.getDouble(ForecastFragment.COL_WEATHER_MAX_TEMP),
//                cursor.getDouble(ForecastFragment.COL_WEATHER_MIN_TEMP));
//        Log.i("high and low me :", highAndLow);
//        return Utility.formatDate(cursor.getLong(ForecastFragment.COL_WEATHER_DATE)) +
//                " - " + cursor.getString(ForecastFragment.COL_WEATHER_DESC) +
//                " - " + highAndLow;
//
//
//    }
