package com.neti.question.activity;

import java.util.List;

import com.neti.database.util.DatabaseHandler;
import com.neti.database.util.SessionManager;
import com.neti.nannydroidalpha.R;
import com.neti.question.model.QuestionKebersihan;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class KebersihanActivity extends Activity {
	
	// Session Manager Class
	SessionManager session;
	
	@Override
	public void onBackPressed() {
		//Disable Back Button
	}
	
	List<QuestionKebersihan> quesList;
	int nilai_kebersihan;
	int qid=0;
	QuestionKebersihan currentQ;
	TextView txtQuestion;
	RadioButton rda, rdb, rdc, rdd ;
	Button butNext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		
		session = new SessionManager (KebersihanActivity.this);
		
		DatabaseHandler db=new DatabaseHandler(this);
		quesList=db.getAllQuestionKebersihan();
		currentQ=quesList.get(qid);
		txtQuestion=(TextView)findViewById(R.id.textView1);
		rda=(RadioButton)findViewById(R.id.radio0);
		rdb=(RadioButton)findViewById(R.id.radio1);
		rdc=(RadioButton)findViewById(R.id.radio2);
		rdd=(RadioButton)findViewById(R.id.radio3);
		butNext=(Button)findViewById(R.id.button1);
		setQuestionView();
		butNext.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
				RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
				Log.d("yourans", currentQ.getANSWER()+" "+answer.getText());
				if(currentQ.getANSWER().equals(answer.getText()))
				{
					nilai_kebersihan = nilai_kebersihan + 2;
					Log.d("score", "Your score"+nilai_kebersihan);
				}
				if(qid<5){					
					currentQ=quesList.get(qid);
					setQuestionView();
				}else{
					// save session into shared preferences
			        session.createScoreEtika(nilai_kebersihan);
			        
					Intent intent = new Intent(KebersihanActivity.this, KesehatanActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_quiz, menu);
		return true;
	}
	private void setQuestionView()
	{
		txtQuestion.setText(currentQ.getQUESTION());
		rda.setText(currentQ.getA());
		rdb.setText(currentQ.getB());
		rdc.setText(currentQ.getC());
		rdd.setText(currentQ.getD());
		qid++;
	}
}
