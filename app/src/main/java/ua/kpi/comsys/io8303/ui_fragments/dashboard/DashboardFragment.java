package ua.kpi.comsys.io8303.ui_fragments.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.lab6.R;

public class DashboardFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Switch switch1 = (Switch) root.findViewById(R.id.switch1);
        View f1 = root.findViewById(R.id.line);
        View f2 = root.findViewById(R.id.pie);
        switch1.setText("Show Line Graph");
        showPie(f1, f2);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) //Line A
            {
                if(isChecked) {
                    showLine(f1, f2);
                    switch1.setText("Show Line Graph");
                } else {
                    showPie(f1, f2);
                    switch1.setText("Show Pie Chart");
                }
            }
        });

        return root;
    }

    public void showPie(View f1, View f2) {
        f2.setVisibility(View.INVISIBLE);
        f1.setVisibility(View.VISIBLE);
    }

    public void showLine(View f1, View f2) {
        f1.setVisibility(View.INVISIBLE);
        f2.setVisibility(View.VISIBLE);
    }
}