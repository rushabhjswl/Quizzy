package com.example.quizzy;

/*import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ScoresGraph extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Scores");
    DatabaseReference reference2;
    long scores[] = new long[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores_graph);

        final AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        //anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        reference.child("amaan").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i = 1; i <= dataSnapshot.getChildrenCount(); i++) {
                    long score = dataSnapshot.child(i + "").getValue(Long.class);
                    Log.e("Hello", "score " + score);
                    scores[i - 1] = score;
                    Log.e("array values" ,scores[i-1]+"");
                }

                Cartesian cartesian = AnyChart.column();
                List<DataEntry> data = new ArrayList<>();
                int countOfScores = 0;
                Log.e("array 3" ,scores[3]+"");
                for (int i = scores.length; i > 0; i--) {
                    data.add(new ValueDataEntry("Quiz " + i, scores[i - 1]));

                    countOfScores++;
                    if (countOfScores == 5) {
                        break;
                    }

                }
                Column column = cartesian.column(data);

                column.tooltip()
                        .titleFormat("{%X}")
                        .position(Position.CENTER_BOTTOM)
                        .anchor(Anchor.CENTER_BOTTOM)
                        .offsetX(0d)
                        .offsetY(5d)
                        .format("${%Value}{groupsSeparator: }");

                cartesian.animation(true);
                cartesian.title("Your Scores");

                cartesian.yScale().minimum(0d);

                cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

                cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
                cartesian.interactivity().hoverMode(HoverMode.BY_X);

                //cartesian.xAxis(0).title("Product");
                //cartesian.yAxis(0).title("Revenue");

                anyChartView.setChart(cartesian);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
*/