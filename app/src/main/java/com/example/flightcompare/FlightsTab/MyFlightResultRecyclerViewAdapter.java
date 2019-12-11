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

public class MyFlightResultRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ROUNDTRIP = 0;
    private final int ONEWAY = 1;
    private final List<Trip> mItems;
    private Context context;

    class ViewHolderRoundTrip extends RecyclerView.ViewHolder {
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

        public ViewHolderRoundTrip(View v) {
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

    class ViewHolderOneWay extends RecyclerView.ViewHolder {
        ImageView departAirlineImage;
        TextView departTimeTextView;
        TextView departDurationTextView;
        TextView tripPriceTextView;
        ImageButton faveButton;
        boolean favorited = false;

        public ViewHolderOneWay(View v) {
            super(v);
            departAirlineImage = v.findViewById(R.id.depart_flight_airline_img);
            departTimeTextView = v.findViewById(R.id.depart_time_text);
            departDurationTextView = v.findViewById(R.id.depart_duration_text);

            tripPriceTextView = v.findViewById(R.id.flight_results_price_text);

            faveButton = v.findViewById(R.id.favoriteButton);
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
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 1 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        FlightLeg flightLeg = mItems.get(position).getInboundLeg();
        if(flightLeg.getDepartureDate() == null) {
            return ONEWAY;
        }
        else {
            return ROUNDTRIP;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.flightresult_list_item, parent, false);;
        switch (viewType) {
            case 0:
                itemView = LayoutInflater.from(context)
                        .inflate(R.layout.flightresult_list_item, parent, false);
                return new ViewHolderRoundTrip(itemView);
            case 1:
                itemView = LayoutInflater.from(context)
                        .inflate(R.layout.flightresult_list_item_one_way, parent, false);
                return new ViewHolderOneWay(itemView);
        }
        // just because I need a return statement
        return new ViewHolderOneWay(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = holder.getItemViewType();
        Trip trip = mItems.get(position);

        if(type == ROUNDTRIP) {
            ViewHolderRoundTrip viewHolderRoundTrip = (ViewHolderRoundTrip)holder;

            // set the favorite button
            if(trip.isSaved()) {
                viewHolderRoundTrip.faveButton.setImageResource(R.drawable.ic_favorite_full_24dp);
            }
            else {
                viewHolderRoundTrip.faveButton.setImageResource(R.drawable.ic_favorite_border_light_grey_24dp);
            }

            viewHolderRoundTrip.departAirlineImage.setImageResource(Singleton.getAirlineImage(trip.getOutboundLeg().getCarrier()));
            // parse the time for the outbound leg
            viewHolderRoundTrip.departTimeTextView.setText(parseFlightTime(trip.getOutboundLeg()));
            viewHolderRoundTrip.departDurationTextView.setText(trip.getOutboundLeg().getFlightDuration());

            viewHolderRoundTrip.returnAirlineImage.setImageResource(Singleton.getAirlineImage(trip.getInboundLeg().getCarrier()));
            // parse the time for the inbound leg
            viewHolderRoundTrip.returnTimeTextView.setText(parseFlightTime(trip.getInboundLeg()));
            viewHolderRoundTrip.returnDurationTextView.setText(trip.getInboundLeg().getFlightDuration());
            viewHolderRoundTrip.divider.setVisibility(View.VISIBLE);

            String priceFormatted = "$" + trip.getPrice();
            viewHolderRoundTrip.tripPriceTextView.setText(priceFormatted);

            viewHolderRoundTrip.bindFavoriteButton(mItems.get(position));
        }
        else if(type == ONEWAY) {
            ViewHolderOneWay viewHolderOneWay = (ViewHolderOneWay)holder;

            // set the favorite button
            if(trip.isSaved()) {
                viewHolderOneWay.faveButton.setImageResource(R.drawable.ic_favorite_full_24dp);
            }
            else {
                viewHolderOneWay.faveButton.setImageResource(R.drawable.ic_favorite_border_light_grey_24dp);
            }

            viewHolderOneWay.departAirlineImage.setImageResource(Singleton.getAirlineImage(trip.getOutboundLeg().getCarrier()));
            // parse the time for the outbound leg
            viewHolderOneWay.departTimeTextView.setText(parseFlightTime(trip.getOutboundLeg()));
            viewHolderOneWay.departDurationTextView.setText(trip.getOutboundLeg().getFlightDuration());

            String priceFormatted = "$" + trip.getPrice();
            viewHolderOneWay.tripPriceTextView.setText(priceFormatted);

            viewHolderOneWay.bindFavoriteButton(mItems.get(position));
        }
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
        String minOut = departDate.substring(departDate.indexOf(":") + 1, departDate.indexOf(":", departDate.indexOf(":") + 1));
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
