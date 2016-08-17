package com.junxu.sharefile;

import android.graphics.Bitmap;

public class FileInfo {
	
	private String name;
	private String path;
	private Bitmap bm;
	
	public FileInfo(String name,String path,Bitmap bm ){
		this.name = name;
		this.path = path;
		this.bm=bm;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Bitmap getBm() {
		return bm;
	}

	public void setBm(Bitmap bm) {
		this.bm = bm;
	}

	
	

}
