package com.example.flightcompare;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flightcompare.Data.CollectionObjects.Flight;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MySavedFlightsRecyclerViewAdapter extends RecyclerView.Adapter<MySavedFlightsRecyclerViewAdapter.ViewHolder> {

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
        CheckBox mCheckBox;

        ViewHolder(View v) {
            super(v);
            mImageView1 = v.findViewById(R.id.depart_flight_airline_img);
            mTextView1 = v.findViewById(R.id.depart_time_text);
            mTextView2 = v.findViewById(R.id.depart_duration_text);
           // mTextView3 = v.findViewById(R.id.depart_price_text);

            mImageView2 = v.findViewById(R.id.return_flight_airline_img);
            mTextView4 = v.findViewById(R.id.return_time_text);
            mTextView5 = v.findViewById(R.id.return_duration_text);
            //mTextView6 = v.findViewById(R.id.return_price_text);

            mCheckBox = v.findViewById(R.id.checkbox);
        }
    }

    public MySavedFlightsRecyclerViewAdapter(List<Object> lines) {
        this.mItems = lines;
    }

    @Override
    public MySavedFlightsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_flightresult_list_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Drawable flightIcon = context.getResources().getDrawable(R.drawable.ic_flights, null);
        String temp;
        holder.mImageView1.setImageDrawable(flightIcon);

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
        holder.mImageView2.setImageDrawable(flightIcon);
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
