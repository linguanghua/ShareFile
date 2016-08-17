package com.junxu.sharefile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {
	
	private File mPrivateRootDir;
	private File mImagesDir;
    File[] mImageFiles;
    String[] mImageFilenames;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	 
    /**	InputStream is = getResources().openRawResource(R.drawable.in_2);
    	Bitmap bm = BitmapFactory.decodeStream(is);
    	
    	SaveImageToData(bm,"in","png",90);
    */
		
		
//		 Intent mResultIntent = new Intent("com.example.myapp.ACTION_RETURN_FILE");
//
//	        mPrivateRootDir = getFilesDir();
//
//	        mImagesDir = new File(mPrivateRootDir, "images");
//
//	        mImageFiles = mImagesDir.listFiles();
//
////	        setResult(Activity.RESULT_CANCELED, null);
//	        
//	        for(int i=0;i<mImageFiles.length;i++){
//	        	Log.d("*****", mImageFiles[i].getAbsolutePath());
//	        }
		
		
	     Button	btn = (Button) findViewById(R.id.button1);
	     btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this,FileSelectActivity.class));
			}
		});
		
	}
	
	/* 将图像保存到Data目录 */
	public boolean SaveImageToData(Bitmap bmpToSave, String FileNameWithoutExtension, String ext, int Quality)
	{ 
		try
		{
			if (Quality > 100)
				Quality = 100;
			else if (Quality < 1)
				Quality = 1;
			
			//创建images文件夹
			String path = getFilesDir().getAbsolutePath() ;
			File file = new File(path + "/images") ;
			if(!file.exists()){
				file.mkdirs() ;
			}
			 
			file = new File(path + "/images" + "/in_2.png");
			if(!file.exists()){
				file.createNewFile();
			}  
			
			FileOutputStream fos = new FileOutputStream(file);
					
			if (ext.equals("png"))
				bmpToSave.compress(Bitmap.CompressFormat.PNG, Quality, fos);
			else if (ext.equals("jpeg"))
				bmpToSave.compress(Bitmap.CompressFormat.JPEG, Quality, fos);
			 
			//写入文件
			fos.flush();
			fos.close();

			return true;
		}
		catch (Exception e)
		{
			if (e.getMessage() != null)
				Log.w("*******", e.getMessage());
			else
				e.printStackTrace();

			return false;
		}
	}

	
	 
 
}
