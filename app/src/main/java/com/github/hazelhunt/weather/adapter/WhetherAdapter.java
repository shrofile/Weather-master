package com.github.hazelhunt.weather.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.hazelhunt.weather.R;
import com.github.hazelhunt.weather.model.Weather;
import com.github.hazelhunt.weather.model.WeatherInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WhetherAdapter extends RecyclerView.Adapter<WhetherAdapter.WhetherViewHolder> {

    List<Weather> mlist;
    Context mContext;


    public WhetherAdapter(List<Weather> mlist,Context context) {
        this.mlist = mlist;
        this.mContext=context;
    }

    @Override
    public WhetherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_whether, parent, false);
        ButterKnife.bind(this,itemView);

        return new WhetherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WhetherViewHolder holder, int position) {
        holder.bindWhether(mlist.get(position));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class WhetherViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.city_text)
        TextView cityText;

        @Bind(R.id.weather_condition)
        TextView conditionText;

        @Bind(R.id.temp_text)
        TextView tempText;

        @Bind(R.id.humidity_text)
        TextView humidityText;

        @Bind(R.id.wind_text)
        TextView windText;

        public WhetherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindWhether(Weather w) {
            String city = getDate(w.getDate_mili_sec(),"dd/MM/yyyy hh:mm:ss.SSS");
            String condition = w.getWeather_overview().get(0).getDescription();
            String temp = w.getMainWeather().getTemp() +
                    mContext.getString(R.string.celsius);
            String humidity = mContext.getString(R.string.humidity) + ": " +
                    w.getMainWeather().getHumidity() + "%";
            String wind = mContext.getString(R.string.wind_speed) + ": " +
                    w.getWind().getSpeed() + " m/s";

            cityText.setText(city);
            conditionText.setText(condition);
            tempText.setText(temp);
            humidityText.setText(humidity);
            windText.setText(wind);
        }

        public  String getDate(long milliSeconds, String dateFormat)
        {
            // Create a DateFormatter object for displaying date in specified format.
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

            // Create a calendar object that will convert the date and time value in milliseconds to date.
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milliSeconds);
            return formatter.format(calendar.getTime());
        }
    }
}
