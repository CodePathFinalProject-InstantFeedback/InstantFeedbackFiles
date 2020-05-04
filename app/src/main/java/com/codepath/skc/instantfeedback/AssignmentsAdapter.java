package com.codepath.skc.instantfeedback;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.skc.instantfeedback.Models.Assignment;
import com.codepath.skc.instantfeedback.Models.Course;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.Features;
import com.ibm.watson.natural_language_understanding.v1.model.KeywordsOptions;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.parse.Parse.getApplicationContext;

public class AssignmentsAdapter extends RecyclerView.Adapter<AssignmentsAdapter.ViewHolder> {

    public static final String TAG = "AssignmentAdapter";
    private String UserType=ParseUser.getCurrentUser().get("UserType").toString();
    private Context context;
    private List<Assignment> assignments;

    class MyXAxisFormatter extends ValueFormatter {

        List<String> emotion = Arrays.asList("Anger", "Joy","Sadness", "Scared");

        @Override
        public String getAxisLabel(float value, AxisBase axis) {

            return emotion.get(Math.round(value));

        }
    }

    public static Handler UIHandler;

    static
    {
        UIHandler = new Handler(Looper.getMainLooper());
    }

    public static void runOnUI(Runnable runnable) {
        UIHandler.post(runnable);
    }

    private class AskWatsonTask extends AsyncTask<String, Void, String> {


        private final String Str="Not long enough";

        public String emotion;
        Assignment assignment;

        public AskWatsonTask(Assignment assignment)
        {
            this.assignment=assignment;
        }

        @Override
        protected void onPostExecute (String result){
                if (result.length()>0) {
                JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                JsonArray arr = jsonObject.getAsJsonArray("keywords");
                emotion = arr.get(0).getAsJsonObject().get("emotion").toString();
                JsonObject jsonObjectEmotion = new JsonParser().parse(emotion).getAsJsonObject();
                this.assignment.setKeyangerval(assignment.getKeyGetangerval() + Float.valueOf(jsonObjectEmotion.get("anger").toString()));
                this.assignment.setKeyfearval(assignment.getKeyGetfearval() + Float.valueOf(jsonObjectEmotion.get("fear").toString()));
                this.assignment.setKeysadnessval(assignment.getKeyGetsadnessval() + Float.valueOf(jsonObjectEmotion.get("sadness").toString()));
                this.assignment.setKeyjoyval(assignment.getKeyGetjoyval() + Float.valueOf(jsonObjectEmotion.get("joy").toString()));
                Toast.makeText(getApplicationContext(), "Sucessfully Sumbitted!", Toast.LENGTH_SHORT).show();
                this.assignment.saveInBackground();
                }
        }

        @Override
        protected String doInBackground(String... textToAnalyze) {

            try {


                Log.i(TAG, "Inside Watson Stuff");
            /*runOnUiThread((new Runnable() {
                @Override
                public void run() {
                    etSentiment.setText("What is happening Inside the UI threatd-we are running the Alchemy API for watson");
                }
            }));*/
                Log.i(TAG, textToAnalyze[0]);
                Authenticator authenticator = new IamAuthenticator("vjhgXKOCV8nkwz6fAhNJ10nhNc1n4WtNsZAHVdutovAt");
                NaturalLanguageUnderstanding naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2019-07-12", authenticator);
                naturalLanguageUnderstanding.setServiceUrl("https://api.eu-gb.natural-language-understanding.watson.cloud.ibm.com/instances/099e0a58-8fa6-455b-a803-8f8895ccbd2b");
                KeywordsOptions keywords = new KeywordsOptions.Builder()
                        .sentiment(true)
                        .emotion(true)
                        .limit(3)
                        .build();

                Features features = new Features.Builder()
                        .keywords(keywords)
                        .build();

                AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                        .text(textToAnalyze[0])
                        .features(features)
                        .build();
                AnalysisResults response = naturalLanguageUnderstanding
                        .analyze(parameters).execute()
                        .getResult();
                System.out.println(response);


            /*AlchemyLanguage service=new AlchemyLanguage();
            service.setApiKey("Bluemix Api Key");
            sentiment="Test Sentiment";
            Log.i(TAG,sentiment);*/
                return response.toString();
            } catch (ServiceResponseException e) {


                AssignmentsAdapter.runOnUI(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "The text is too short from IBM to analyze", Toast.LENGTH_SHORT).show();
                    }
                });

                return "";
            }
        }

        protected String returnEmotion()
        {
            return  emotion;
        }

    }

    //Adapter code

    public AssignmentsAdapter(Context context, List<Assignment> assignments){
        this.context=context;
        this.assignments=assignments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (UserType.toLowerCase().equals("student")) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_assignments, parent, false);
            return new ViewHolder(view);
        }

        else{
            View view = LayoutInflater.from(context).inflate(R.layout.item_assignments_professor, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Assignment assignment=assignments.get(position);
        if (UserType.toLowerCase().equals("student")) {
            holder.bindStudent(assignment);
        }
        else{
            holder.bindProfessor(assignment);
        }

    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }


    class ViewHolder extends  RecyclerView.ViewHolder  {
        private TextView etAssignmentName;
        private float totalRating;
        private int newTotal;
        private  float currentRating;
        private TextView etDeadline;
        private RatingBar etRatingBar;
        private Button etSubmitRating;
        BarChart chart;
        private TextView etAssignmentNameProf;
        private  RatingBar etRatingBarProf;
        private EditText etIBMAnalyze;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etAssignmentName = itemView.findViewById(R.id.etAssignmentName);
            etDeadline = itemView.findViewById(R.id.etDeadline);
            etIBMAnalyze = itemView.findViewById(R.id.etIBMAnalyze);
            etRatingBar = itemView.findViewById(R.id.etratingBar);
            etSubmitRating = itemView.findViewById(R.id.etSubmitRating);
            etAssignmentNameProf = itemView.findViewById(R.id.etAssignmentNameProf);
            etRatingBarProf = itemView.findViewById(R.id.etratingBarProf);
        }

        public void bindStudent(final Assignment assignment) {
            etAssignmentName.setText(assignment.getKeyAssignmentname());
            etRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    currentRating=rating;
                }
            });

            etSubmitRating.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newTotal=assignment.getNumberOfRatings()+1;
                    Log.i(TAG,String.valueOf(newTotal));
                    totalRating=currentRating+assignment.getKeyTotalrating();
                    Log.i(TAG,"The new average rating amount is"+(totalRating/newTotal));
                    assignment.setNumberOfRatings(newTotal);
                    assignment.setKeyTotalrating(Math.round(totalRating));
                    AskWatsonTask task = new AskWatsonTask(assignment);
                    task.execute(etIBMAnalyze.getText().toString());
                }
            });
        }

        public void bindProfessor(Assignment assignment){
            etAssignmentNameProf.setText(assignment.getKeyAssignmentname());
            float rating=Math.round(assignment.getKeyTotalrating()/assignment.getNumberOfRatings());
            etRatingBarProf.setRating(rating);
            chart=itemView.findViewById(R.id.chart);
            chart.setTouchEnabled(false);
            chart.getXAxis().setTextSize(20);
            chart.getLegend().setDrawInside(false);
            chart.getAxisLeft().setDrawGridLines(false);
            chart.getAxisLeft().setDrawLabels(false);
            chart.getAxisRight().setDrawGridLines(false);
            chart.getAxisRight().setDrawLabels(false);
            List<BarEntry> entries = new ArrayList<BarEntry>();
            BarEntry bar= new BarEntry(0, assignment.getKeyGetangerval()/assignment.getNumberOfRatings());
            BarEntry bar2= new BarEntry(1,assignment.getKeyGetjoyval()/assignment.getNumberOfRatings());
            BarEntry bar3= new BarEntry(2, assignment.getKeyGetsadnessval()/assignment.getNumberOfRatings());
            BarEntry bar4= new BarEntry(3,assignment.getKeyGetfearval()/assignment.getNumberOfRatings());
            entries.add(bar);
            entries.add(bar2);
            entries.add(bar3);
            entries.add(bar4);
            BarDataSet dataSet=new BarDataSet(entries,"");
            int [] colors=new int[] {itemView.getResources().getColor(R.color.Red),itemView.getResources().getColor(R.color.Joy), itemView.getResources().getColor(R.color.Sadness),itemView.getResources().getColor(R.color.Fear)};
            dataSet.setColors(colors);
            dataSet.setValueTextSize(20f);
            BarData barData=new BarData(dataSet);
            chart.getXAxis().setGranularity(1f);
            chart.getXAxis().setGranularityEnabled(true);
            chart.setData(barData);
            chart.getBarData().setBarWidth(0.8f);
            chart.getXAxis().setAxisMinimum(-0.5f);
            chart.getXAxis().setAxisMaximum(chart.getBarData().getBarWidth()*4);
            chart.getXAxis().setDrawAxisLine(true);
            chart.getXAxis().setValueFormatter(new MyXAxisFormatter());
            chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            chart.invalidate();
        }

    }

}