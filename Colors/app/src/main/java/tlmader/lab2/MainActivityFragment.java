package tlmader.lab2;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private RecyclerView recyclerView;
    private ColorsAdapter adapter;

    private ArrayList<ColorPair> colors;
    private View colorView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);

        colorView = view.findViewById(R.id.color_view);

        // Create ArrayAdapter and use it to bind tags to the ListView
        adapter = new ColorsAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        colors = new ArrayList<>();
        initColors();

        // TODO: Add colors to list

        return view;
    }

    private void initColors() {

        colors.add(new ColorPair(R.string.blue, R.color.blue));
        colors.add(new ColorPair(R.string.green, R.color.green));
        colors.add(new ColorPair(R.string.yellow, R.color.yellow));
        colors.add(new ColorPair(R.string.red, R.color.red));
    }

    // Create the basic adapter extending from RecyclerView.Adapter
    // Note that we specify the custom ViewHolder which gives us access to our views
    public class ColorsAdapter extends RecyclerView.Adapter<ViewHolder> {

        // Usually involves inflating a layout from XML and returning the holder
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View contactView = inflater.inflate(R.layout.item, parent, false);


            contactView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Drawable background = v.findViewById(R.id.color_view).getBackground();
                    colorView.setBackground(background);
                }

            });

            // Return a new holder instance
            return new ViewHolder(contactView);
        }

        // Involves populating data into the item through holder
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {

            ColorPair pair = colors.get(position);
            viewHolder.colorView.setBackgroundResource(pair.getColorId());
            viewHolder.textView.setText(pair.getStringId());
        }

        // Return the total count of items
        @Override
        public int getItemCount() {
            return colors.size();
        }
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView textView;
        public View colorView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.text);
            colorView = itemView.findViewById(R.id.color_view);
        }
    }

    public class ColorPair {

        private int stringId;
        private int colorId;

        public ColorPair(int stringId, int colorId) {
            this.stringId = stringId;
            this.colorId = colorId;
        }

        public int getColorId() {
            return colorId;
        }

        public int getStringId() {
            return stringId;
        }
    }
}
