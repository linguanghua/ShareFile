package com.junxu.sharefile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FileSelectActivity extends Activity implements OnItemClickListener {
	
	private File mPrivateRootDir;
	private File mImagesDir;
    File[] mImageFiles;
    String[] mImageFilenames;
    
    private ListView lv;
    private ListAdapter adapter;
    private List<FileInfo> files;
    private Intent mResultIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fileselect);
        mResultIntent =new Intent("com.junxu.sharefileclient.ACTION_RETURN_FILE");
	    mPrivateRootDir = getFilesDir();
	    mImagesDir = new File(mPrivateRootDir, "images");
	    mImageFiles = mImagesDir.listFiles();

	    lv = (ListView) findViewById(R.id.lv);
	    files = new ArrayList<FileInfo>();
	    
//        setResult(Activity.RESULT_CANCELED, null);
	    
	    for(int i=0;i<mImageFiles.length;i++){
	    	String path = mImageFiles[i].getAbsolutePath();
	    	Bitmap bm = BitmapFactory.decodeFile(path);
	    	FileInfo fInfo = new FileInfo(path.substring(path.lastIndexOf("/")+1), path, bm);
	    	files.add(fInfo);
	    }
	    
	    adapter = new ListAdapter(files, FileSelectActivity.this);
	    
	    lv.setAdapter(adapter);
	    lv.setOnItemClickListener(this);
	    
	    
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		File requestFile = new File(files.get(position).getPath());
		String authority="com.junxu.sharefile.fileprovider";
		Log.d("********", files.get(position).getPath());
		try{
			Uri fileUri = FileProvider.getUriForFile(FileSelectActivity.this, authority, requestFile);
			Log.d("********", fileUri.toString());
			if(fileUri!=null){
                mResultIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                mResultIntent.setDataAndType(fileUri,getContentResolver().getType(fileUri));
                setResult(Activity.RESULT_OK,mResultIntent);
                Log.d("********", "ok"+RESULT_OK);
            } 
		}catch(IllegalArgumentException e){
			e.printStackTrace();
			  mResultIntent.setDataAndType(null, "");
              setResult(RESULT_CANCELED,mResultIntent);
		}
		finish();
	}

}
