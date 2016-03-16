package com.booboot.vndbandroid.activity;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.booboot.vndbandroid.R;
import com.booboot.vndbandroid.api.bean.Item;
import com.booboot.vndbandroid.db.DB;
import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.listeners.RecyclerItemClickListener;
import com.dexafree.materialList.view.MaterialListView;

/**
 * Created by od on 09/03/2016.
 */
public class PlayingFragment extends Fragment {
    public final static String STATUS_ARG = "STATUS";
    public final static String VN_ARG = "VN";
    private MaterialListView materialListView;
    private int status;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.playing_fragment, container, false);
        status = getArguments().getInt(STATUS_ARG);

        Log.d("D", "Creating a new Playing Fragment...");
        materialListView = (MaterialListView) rootView.findViewById(R.id.materialListView);

        for (final Item vn : DB.results.getItems()) {
            if (vn.getStatus() != status) continue;

            Card card = new Card.Builder(getActivity())
                    .withProvider(new CardProvider())
                    .setLayout(R.layout.vn_card_layout)
                    .setTitle(vn.getTitle())
                    .setSubtitle(vn.getOriginal())
                    .setSubtitleColor(Color.BLACK)
                    .setTitleGravity(Gravity.END)
                    .setSubtitleGravity(Gravity.END)
                    .setDescription(vn.getReleased())
                    .setDescriptionGravity(Gravity.END)
                    .setDrawable(vn.getImage())
                    .endConfig().build();
            card.setTag(vn);

            materialListView.getAdapter().add(card);
            materialListView.scrollToPosition(0);
        }


        materialListView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(Card card, int position) {
                Log.d("CARD_TYPE", card.getTag() + "");

                Intent intent = new Intent(getActivity(), VNDetailsActivity.class);
                intent.putExtra(VN_ARG, (Item) card.getTag());
                // intent.putExtra(VN_ARG, Integer.parseInt(card.getTag().toString()));
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(Card card, int position) {
                Log.d("LONG_CLICK", card.getProvider().getTitle());
            }
        });

        return rootView;
    }
}
