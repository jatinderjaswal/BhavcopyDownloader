package com.BhavcopyDownloader;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utilities {
	
	private String strFileName = null; 
	
	public String createDownloadURL(Date dtDate){
		String strDownloadURL = "http://www.nseindia.com/content/historical/EQUITIES/";
		Calendar dtToday = Calendar.getInstance();
		dtToday.setTime(dtDate);
		strDownloadURL = strDownloadURL + new String(new SimpleDateFormat("yyyy").format(dtToday.getTime())).toUpperCase() + "/";
		strDownloadURL = strDownloadURL + new String(new SimpleDateFormat("MMM").format(dtToday.getTime())).toUpperCase() + "/";
		strFileName = "cm" + new String(new SimpleDateFormat("ddMMMyyyy").format(dtToday.getTime())).toUpperCase() + "bhav.csv.zip";
		strDownloadURL = strDownloadURL + strFileName ;
		return strDownloadURL;
	}

	public void saveFile(final String strURL) throws MalformedURLException, IOException{
		BufferedInputStream fIn = null;
		FileOutputStream fOut = null;
		try{
			fIn = new BufferedInputStream(new URL(strURL).openStream());
			fOut = new FileOutputStream(strFileName);
			final byte data[] = new byte[1024];
			int count;
			while((count=fIn.read(data,0,1024))!=-1){
				fOut.write(data,0,count);				
			}
		}
		finally{
			if(fIn != null){
				fIn.close();
			}
			if(fOut!=null){
				fOut.close();
			}
		}
	}
}
