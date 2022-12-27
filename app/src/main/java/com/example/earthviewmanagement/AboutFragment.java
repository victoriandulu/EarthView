package com.example.earthviewmanagement;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AboutFragment extends Fragment {

    TextView details,details1,details2,insta,facebook,twitter;
    LinearLayout layout,layout1,layout2;
    CardView cardView1;
    CardView cardView2;
    CardView cardView3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_about, container, false);
        details = root.findViewById(R.id.penda_details);
        insta = root.findViewById(R.id.instagram);
        facebook = root.findViewById(R.id.facebook);
        twitter = root.findViewById(R.id.twitter);
        layout = root.findViewById(R.id.linear);
        layout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        details1 = root.findViewById(R.id.penda_details1);
        layout1 = root.findViewById(R.id.linear1);
        layout1.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        details2 = root.findViewById(R.id.penda_details2);
        layout2 = root.findViewById(R.id.linear2);
        layout2.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        cardView1 = root.findViewById(R.id.cardView);
        cardView2 = root.findViewById(R.id.cardView2);

        cardView3 = root.findViewById(R.id.cardView3);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int V = (details2.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;

                TransitionManager.beginDelayedTransition(layout, new AutoTransition());
                details2.setVisibility(V);

            }

            {
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {int V = (details1.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

                TransitionManager.beginDelayedTransition(layout, new AutoTransition());
                details1.setVisibility(V);

            }
        });
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int V = (details.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

                TransitionManager.beginDelayedTransition(layout, new AutoTransition());
                details.setVisibility(V);


            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://web.facebook.com/earthviewLtd/?_rdc=1&_rdr");
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://twitter.com/earthviewl?lang=en");
            }
        });
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.instagram.com/penda_health/");
            }
        });

       return root;

    }

    private void gotoUrl(String s) {
        Uri uri=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}






