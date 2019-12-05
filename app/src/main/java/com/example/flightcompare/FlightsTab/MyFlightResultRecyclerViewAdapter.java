package com.example.flightcompare.FlightsTab;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flightcompare.Data.CollectionObjects.Flight;
import com.example.flightcompare.R;

import java.util.ArrayList;
import java.util.List;

public class MyFlightResultRecyclerViewAdapter extends RecyclerView.Adapter<MyFlightResultRecyclerViewAdapter.ViewHolder> {

    private final List<Object> mItems;
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView1;
        TextView mTextView1;
        TextView mTextView2;
        TextView mTextView3;
        ImageView mImageView2;
        TextView mTextView4;
        TextView mTextView5;
        TextView mTextView6;
        ImageButton faveButton;
        boolean favorited = false;
//        CheckBox mCheckBox;

        ViewHolder(View v) {
            super(v);
            mImageView1 = v.findViewById(R.id.depart_flight_airline_img);
            mTextView1 = v.findViewById(R.id.depart_time_text);
            mTextView2 = v.findViewById(R.id.depart_duration_text);
            mTextView3 = v.findViewById(R.id.depart_price_text);

            mImageView2 = v.findViewById(R.id.return_flight_airline_img);
            mTextView4 = v.findViewById(R.id.return_time_text);
            mTextView5 = v.findViewById(R.id.return_duration_text);
            mTextView6 = v.findViewById(R.id.return_price_text);

            faveButton = v.findViewById(R.id.favoriteButton);
//            mCheckBox = v.findViewById(R.id.checkbox);
        }

        void bindFavoriteButton(final Object item) {
            favorited = !favorited;
            faveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(favorited) {
                        faveButton.setImageResource(R.drawable.ic_favorite_border_light_grey_24dp);
                    }
                    else {
                        faveButton.setImageResource(R.drawable.ic_favorite_full_24dp);
                    }
                   // favoriteButton.setSelected(!btnextra.isPressed());
    //                if (favoriteButton.isPressed()) {
    //                    favoriteButton.setImageResource(R.drawable.yourImage);
    //                }
    //                else {
    //                    favoriteButton.setImageResource(R.drawable.fav);
    //                }
                }
            });
        }

    }

    public MyFlightResultRecyclerViewAdapter(List<Object> lines) {
        this.mItems = lines;
    }

    @Override
    public MyFlightResultRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_flightresult_list_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        Icon flightIcon = (Icon)context.getResources().getDrawable(R.drawable.ic_flights, null);
        String temp;
        holder.bindFavoriteButton(mItems.get(position));
        //final ImageButton btnTest =(ImageButton) findViewById(R.id.btnexctract);
//        final ImageButton favoriteButton = holder.faveButton;
//        favoriteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//               // favoriteButton.setSelected(!btnextra.isPressed());
////                if (favoriteButton.isPressed()) {
////                    favoriteButton.setImageResource(R.drawable.yourImage);
////                }
////                else {
////                    favoriteButton.setImageResource(R.drawable.fav);
////                }
//            }
//        });
        holder.mImageView1.setColorFilter(context.getResources().getColor(R.color.accentOrange));
        holder.mImageView2.setImageResource(R.drawable.ic_flights);
        holder.mImageView1.setImageResource(R.drawable.ic_flights);
//        holder.mImageView1.setImageDrawable(flightIcon);

        /*
        TextView mTextView1;
        TextView mTextView2;
        TextView mTextView3;
        ImageView mImageView2;
        TextView mTextView4;
        TextView mTextView5;
        TextView mTextView6;
        CheckBox mCheckBox;
         */
//        holder.mImageView2.setImageDrawable(flightIcon);
        ArrayList<Flight> flights = (ArrayList<Flight>)mItems.get(position);
//        String temp = flights.get(0).getDepart_time().toString() + " - " + flights.get(0).getArrive_time();
        temp = "11:30 AM - 4:40 PM";
        holder.mTextView1.setText(temp);
        temp = flights.get(0).getFlytime().toString();
        holder.mTextView2.setText(temp);
        holder.mTextView3.setText(flights.get(0).getPrice().toString());

//        temp = flights.get(1).getDepart_time().toString() + " - " + flights.get(1).getArrive_time();
        temp = "9:30 AM - 12:40 PM";
        holder.mTextView4.setText(temp);
        temp = flights.get(1).getFlytime().toString();
        holder.mTextView5.setText(temp);
        holder.mTextView6.setText(flights.get(1).getPrice().toString());


//        Drawable maleIcon = context.getResources().getDrawable(R.drawable.male_icon,null);
//        Drawable star = context.getResources().getDrawable(R.drawable.star_icon,null);
//        Drawable femaleIcon = context.getResources().getDrawable(R.drawable.female_icon,null);
//
//        String eventId = "";
//        String personName = "";
//        Event myEvent = new Event();
//        Person myPerson = new Person();
//        String toDisplay = "";
//        if(lines.get(position).getClass() == Event.class) {
//            myEvent = (Event)lines.get(position);
//            eventId = myEvent.getEventID();
//            myEvent = Model.get().getEventById(eventId);
//            Person myEventPerson = Model.get().getPerson(myEvent.getPersonID());
//            toDisplay = (myEvent.getEventType() + ": " + myEvent.getCity() + ", " + myEvent.getCountry()
//                    + " (" + myEvent.getYear() + ")" + "\n" + myEventPerson.getFirstName() + " " + myEventPerson.getLastName());
//            holder.mImageView.setImageDrawable(star);
//            holder.bindEvent(eventId);
//        }
//        else if(lines.get(position).getClass() == Person.class) {
//            myPerson = (Person)lines.get(position);
//            String personId = myPerson.getPersonID();
//            toDisplay = (myPerson.getFirstName() + " " + myPerson.getLastName());
//            if(myPerson.getGender() == 'm') {
//                holder.mImageView.setImageDrawable(maleIcon);
//            }
//            else {
//                holder.mImageView.setImageDrawable(femaleIcon);
//            }
//            holder.bindPerson(personId);
//        }
//
//        holder.mTextView.setText(toDisplay);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
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
//                .inflate(R.layout.fragment_flightresult_list_item, parent, false);
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
