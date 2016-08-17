package com.junxu.sharefileclient;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;
import android.provider.OpenableColumns;

public class MainActivity extends Activity {
	
	private Button btn;
	private ImageView img;
	private TextView info;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    btn = (Button) findViewById(R.id.button1);
		img = (ImageView) findViewById(R.id.imageView1);
		info = (TextView) findViewById(R.id.textView1);
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent mRequestFileIntent = new Intent(Intent.ACTION_PICK);
					   mRequestFileIntent.setType("image/jpg");
					   startActivityForResult(mRequestFileIntent, 0);
			}
		});
		 
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d("******", " 222"+resultCode);
		if( resultCode != RESULT_OK ){
			return;
		}else{
			Uri fileUri = data.getData();
			 
			 Cursor returnCursor =
			            getContentResolver().query(fileUri, null, null, null, null);


			int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
			int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
			    returnCursor.moveToFirst();
			    
			    info.setText("\t文件名："+returnCursor.getString(nameIndex)
			    		+"\n\t文件大小："+returnCursor.getString(sizeIndex));
			    Log.d("*****", returnCursor.getString(nameIndex));
			    Log.d("*****", returnCursor.getString(sizeIndex));
			    
			    returnCursor.close();  
			    
			Bitmap bm = null;
			
			try {
				InputStream is = getContentResolver().openInputStream(fileUri);
				bm = BitmapFactory.decodeStream(is);
				img.setImageBitmap(bm);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			  
		}
	}
 
}
