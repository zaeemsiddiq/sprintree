package monash.sprintree.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import monash.sprintree.R;
import monash.sprintree.activities.MapsActivity;
import monash.sprintree.data.History;
import monash.sprintree.listAdapters.HistoryListAdapter;

public class HistoryFragment extends Fragment {
    private TextView errorMessageText;
    private ListView listView;
    MapsActivity listener;  // used to reference back to main activity
    private HistoryListAdapter historyListAdapter; // adapter to load into list
    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance(MapsActivity listener) {
        HistoryFragment fragment = new HistoryFragment();
        fragment.listener = listener;   //setting up the listener
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        initialiseListView(view);
        return view;
    }
    /*
    public void refresh() { // reload the list
        historyListAdapter = new HistoryListAdapter(getActivity(), Constants.dbContext.getAllFavorites());
        listView.setAdapter(favoriteListAdapter);
        if(favoriteListAdapter.getCount()==0) { // hide/ show the error message
            listView.setVisibility(View.GONE);
            errorMessageText.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            errorMessageText.setVisibility(View.GONE);
        }
        favoriteListAdapter.notifyDataSetChanged();
    }*/

    private void initialiseListView(View view) {
        errorMessageText = (TextView) view.findViewById(R.id.favoritesTextEmptyMessage);
        errorMessageText.setVisibility(View.GONE);
        listView = (ListView) view.findViewById(R.id.favoritesListView);

        List<History> histories = new ArrayList<>();
        History history1 = new History();
        history1.historyId = 1;
        history1.historyName = "This is 1";
        histories.add(history1);

        History history2 = new History();
        history2.historyId = 1;
        history2.historyName = "This is 2";
        histories.add(history2);

        History history3 = new History();
        history3.historyId = 1;
        history3.historyName = "This is 3";
        histories.add(history3);

        historyListAdapter = new HistoryListAdapter(getActivity(), histories);
        listView.setAdapter(historyListAdapter);
        if(historyListAdapter.getCount()==0) { // hide/ show the error message
            listView.setVisibility(View.GONE);
            errorMessageText.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            errorMessageText.setVisibility(View.GONE);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {    // preplan
                Toast.makeText(getActivity(), position + "clicked", Toast.LENGTH_SHORT).show();
                /*new AlertDialog.Builder(getActivity())
                        .setTitle("Confirm")
                        .setMessage("Are you sure you want to travel this route ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Favorite favorite = favoriteListAdapter.getItem(position);
                                listener.PrePlanStart(favorite.getSourceStop(), favorite.getDestinationStop());
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();*/
            }
        });

       listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
               /*
               new AlertDialog.Builder(getActivity())
                       .setTitle("Confirm")
                       .setMessage("Are you sure you want to delete this route ?")
                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int which) { // delete
                               Constants.dbContext.deleteFavorite(favoriteListAdapter.getItem(position).getFavouriteID());
                               Toast.makeText(getActivity(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                               refresh();
                           }
                       })
                       .setNegativeButton("No", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int which) {
                               // do nothing
                           }
                       })
                       .setIcon(android.R.drawable.ic_dialog_alert)
                       .show();*/
               return true;
           }
       });
    }

}