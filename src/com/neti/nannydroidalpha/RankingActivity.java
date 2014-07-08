package com.neti.nannydroidalpha;

import java.util.List;

import com.neti.database.util.FetchDataListener;
import com.neti.database.util.FetchDataTask;
import com.neti.model.Ranking;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

public class RankingActivity extends ListActivity implements FetchDataListener{
    private ProgressDialog dialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_ranking);        
        initView();   
    }

    private void initView() {
        // show progress dialog
        dialog = ProgressDialog.show(this, "", "Loading...");
        
        String url = "http://10.0.3.2/nannydroid/ranking.php";
        FetchDataTask task = new FetchDataTask(this);
        task.execute(url);
    }
    
    @Override
    public void onFetchComplete(List<Ranking> data) {
        // dismiss the progress dialog
        if(dialog != null)  dialog.dismiss();
        // create new adapter
        RankingAdapter adapter = new RankingAdapter(this, data);
        // set the adapter to list
        setListAdapter(adapter);        
    }

    @Override
    public void onFetchFailure(String msg) {
        // dismiss the progress dialog
        if(dialog != null)  dialog.dismiss();
        // show failure message
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();        
    }
}
