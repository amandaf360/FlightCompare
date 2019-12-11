package com.example.flightcompare.FlightsTab;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flightcompare.Data.JsonObjects.FlightLeg;
import com.example.flightcompare.Data.JsonObjects.Trip;
import com.example.flightcompare.Data.Singleton;
import com.example.flightcompare.R;

import java.util.List;

//interface OnItemClickListener {
//    void onItemClicked(Trip trip);
//}

public class MyFlightResultRecyclerViewAdapter extends RecyclerView.Adapter<MyFlightResultRecyclerViewAdapter.ViewHolder> {

    private final List<Trip> mItems;
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView departAirlineImage;
        TextView departTimeTextView;
        TextView departDurationTextView;
        ImageView returnAirlineImage;
        TextView returnTimeTextView;
        TextView returnDurationTextView;
        TextView tripPriceTextView;
        ImageButton faveButton;
        View divider;
        boolean favorited = false;

//        CheckBox mCheckBox;

        ViewHolder(View v) {
            super(v);
            departAirlineImage = v.findViewById(R.id.depart_flight_airline_img);
            departTimeTextView = v.findViewById(R.id.depart_time_text);
            departDurationTextView = v.findViewById(R.id.depart_duration_text);

            returnAirlineImage = v.findViewById(R.id.return_flight_airline_img);
            returnTimeTextView = v.findViewById(R.id.return_time_text);
            returnDurationTextView = v.findViewById(R.id.return_duration_text);

            tripPriceTextView = v.findViewById(R.id.flight_results_price_text);
            divider = v.findViewById(R.id.flight_results_divider);

            faveButton = v.findViewById(R.id.favoriteButton);
//            mCheckBox = v.findViewById(R.id.checkbox);
        }

        void bindFavoriteButton(final Trip trip) {
            faveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("SAVED BUTTON CLICKED");
                    favorited = trip.isSaved();
                    if(favorited) {
                        faveButton.setImageResource(R.drawable.ic_favorite_border_light_grey_24dp);
                        Singleton.removeSavedTrip(trip);
                    }
                    else {
                        faveButton.setImageResource(R.drawable.ic_favorite_full_24dp);
                        Singleton.addSavedTrip(trip);
                    }
                }
            });
        }
    }

    public MyFlightResultRecyclerViewAdapter(List<Trip> lines) {
        this.mItems = lines;
    }

    @Override
    public MyFlightResultRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flightresult_list_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Trip trip = mItems.get(position);
        boolean roundTrip = (trip.getInboundLeg().getDepartureDate() != null);

        holder.departAirlineImage.setImageResource(R.drawable.delta_logo);
        // parse the time for the outbound leg
        holder.departTimeTextView.setText(parseFlightTime(trip.getOutboundLeg()));
        holder.departDurationTextView.setText(trip.getOutboundLeg().getFlightDuration());
        holder.departDurationTextView.setText("1h 35m");

        if(roundTrip) {
            holder.returnAirlineImage.setImageResource(R.drawable.delta_logo);
            // parse the time for the inbound leg
            holder.returnTimeTextView.setText(parseFlightTime(trip.getInboundLeg()));
            holder.returnDurationTextView.setText(trip.getInboundLeg().getFlightDuration());
            holder.returnDurationTextView.setText("3h 5m");
            holder.divider.setVisibility(View.VISIBLE);
        }
        else {
            holder.divider.setVisibility(View.INVISIBLE);
        }
        holder.tripPriceTextView.setText("$" + trip.getPrice());

        holder.bindFavoriteButton(mItems.get(position));

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private String parseDate(FlightLeg flightLeg) {
        String departDate = flightLeg.getDepartureDate();
        //yyyy-mm-ddThh:mm:ss
        Integer year = Integer.parseInt(departDate.substring(0, 4));
        Integer month = Integer.parseInt(departDate.substring(5, 7));
        Integer day = Integer.parseInt(departDate.substring(8, 10));
        return (month.toString() + "/" + day.toString() + "/" + year.toString());
    }

    private String parseFlightTime(FlightLeg flightLeg) {
        String departDate = flightLeg.getDepartureDate();
        String returnDate = flightLeg.getArrivalDate();
        //yyyy-mm-ddThh:mm:ss
        Integer hourOut = Integer.parseInt(departDate.substring(11, departDate.indexOf(":")));
        Integer minOut = Integer.parseInt(departDate.substring(departDate.indexOf(":") + 1, departDate.indexOf(":", departDate.indexOf(":") + 1)));
        boolean outPM = (hourOut > 12);
        Integer hourIn = Integer.parseInt(returnDate.substring(11, departDate.indexOf(":")));
        Integer minIn = Integer.parseInt(returnDate.substring(departDate.indexOf(":") + 1, departDate.indexOf(":", departDate.indexOf(":") + 1)));
        boolean inPM = (hourIn > 12);
        return ((hourOut > 12 ? hourOut - 12 : hourOut) + ":" + minOut + (outPM ? "PM" : "AM") +
                " - " + (hourIn > 12 ? hourIn - 12 : hourIn) + ":" + minIn + (inPM ? "PM" : "AM"));
    }






//
//
//
//    public MyFlightResultRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
//        mValues = items;
//        mListener = listener;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.flightresult_list_item, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.mItem = mValues.get(position);
//        holder.mIdView.setText(mValues.get(position).id);
//        holder.mContentView.setText(mValues.get(position).content);
//
//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return mValues.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public final View mView;
//        public final TextView mIdView;
//        public final TextView mContentView;
//        public DummyItem mItem;
//
//        public ViewHolder(View view) {
//            super(view);
//            mView = view;
//            mIdView = (TextView) view.findViewById(R.id.item_number);
//            mContentView = (TextView) view.findViewById(R.id.content);
//        }
//
//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
//    }
}
