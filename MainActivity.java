package com.example.xutils;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class MainActivity extends Activity {
private ProgressBar bar;
private TextView textview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bar=(ProgressBar)findViewById(R.id.bar);
		textview=(TextView)findViewById(R.id.show);
	}
public void down(View v){
	HttpUtils utils=new HttpUtils();
	String fileName="Wildlife.wmv";
	String path="http://10.54.1.196:8080/"+fileName;
	utils.download(path, "sdcard/Wildlife.wmv",
									true,//是否支持断点续传
									true,//
									new RequestCallBack<File>() {
										//下载成功后调用此方法
										@Override
										public void onSuccess(ResponseInfo<File> arg0) {
											// TODO Auto-generated method stub
											Toast.makeText(getApplication(), arg0.result.getPath(), Toast.LENGTH_SHORT).show();
										}
										//下载失败调用此方法a
										@Override
										public void onFailure(HttpException arg0, String arg1) {
											// TODO Auto-generated method stub
											Toast.makeText(getApplication(), arg1, Toast.LENGTH_SHORT).show();
										}
										@Override
										public void onLoading(long total,long current,
												boolean isUploading) {
											// TODO Auto-generated method stub
											super.onLoading(total, current, isUploading);
											bar.setMax((int)total);
											bar.setProgress((int)current);
											textview.setText(current*100/total+"%");
										}
									});
			
}
}
