package com.example.ap;

import java.util.List;
import com.example.ap.vo.*;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnNavigationListener{
	private ListView mListView;
	private Context mContext;
	private final String URL="http://172.17.67.27:8080/service/xml/data.xml";
	private List<News> lists;
	private String[] str = new String[]{"网易科技","网易娱乐","网易财经"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		
		ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str); 
		myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		actionBar.setListNavigationCallbacks(myAdapter, this); 
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(MainActivity.this, "actionBar ok！", Toast.LENGTH_SHORT).show();
			
			break;
		case android.R.id.home:
			Toast.makeText(MainActivity.this, "网易科技", Toast.LENGTH_LONG).show();
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onNavigationItemSelected(int arg0, long arg1) {
		Toast.makeText(MainActivity.this, str[arg0], Toast.LENGTH_LONG).show();
		
		return false;
	}
}
