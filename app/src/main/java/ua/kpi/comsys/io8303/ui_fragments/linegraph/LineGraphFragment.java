package ua.kpi.comsys.io8303.ui_fragments.linegraph;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.lab6.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class LineGraphFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_linegraph, container, false);

        LineChart lineChart = (LineChart) root.findViewById(R.id.idLineChart);
        showLineChart(lineChart);
        return root;
    }

    private void showLineChart(LineChart lineChart){

        ArrayList<Entry> lineEntries= new ArrayList<Entry>();
        for (float x = -6; x < 6; x += 0.05) {
            lineEntries.add( new Entry(x, (float)Math.exp(x)));
        }

        LineDataSet lineDataSet  = new LineDataSet(lineEntries, "");

        LineData lineData = new LineData(lineDataSet);

        lineChart.setDrawMarkers(false);
        lineChart.getXAxis().setGranularityEnabled(false);
        lineChart.getDescription().setEnabled(false); //remove description
        lineChart.getLegend().setEnabled(false); //remove legend

        lineChart.setData(lineData);
        lineChart.invalidate();
    }
}