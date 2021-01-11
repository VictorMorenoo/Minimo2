package edu.upc.dsa.minimo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    private List<User> UserList;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txt1;
        public View layout;
        public TextView txt2;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txt1 = (TextView) v.findViewById(R.id.textRecycler1);
            txt2=(TextView) v.findViewById(R.id.textRecycler2);
        }
    }

    public RecyclerAdapter(List<User> myDataset)
    {
        UserList = myDataset;
        LayoutInflater inflater;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.recycler_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.txt1.setText(UserList.get(position).getName());
        holder.txt2.setText(UserList.get(position).getLanguage());
    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }
}
