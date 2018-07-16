package com.example.a14049472.demofilereadwriting;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.FocusFinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

public class MainActivity extends AppCompatActivity {
    Button btnWrite;
    Button btnRead;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnWrite=(Button)findViewById(R.id.btnWrite);
        btnRead=(Button)findViewById(R.id.btnRead);
        tv=(TextView)findViewById(R.id.tv);
        final String folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath()+"/MyFolder";
        File folder = new File(folderLocation);
        if(folder.exists()==false){
            boolean result = folder.mkdir();
            if(result==true){
                Log.d("File Read/Write","Folder created");
            }
        }
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //File targetFile=new File(folderLocation,"data.txt");
                try{
                    String folderLocation=Environment.getExternalStorageDirectory().getAbsolutePath()+"/MyFolder";
                    File targetFile = new File(folderLocation,"data.txt");
                    FileWriter writer_I = new FileWriter(targetFile,true);
                    writer_I.write("Hello world"+"\n");
                    writer_I.flush();
                    writer_I.close();

                }catch(Exception e){
                    Toast.makeText(MainActivity.this,"Failed to write",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File targetFile = new File(folderLocation,"data.txt");
                if(targetFile.exists()==true){
                    String data="";
                    try{
                        FileReader reader = new FileReader(targetFile);
                        BufferedReader br = new BufferedReader(reader);
                        String line = br.readLine();
                        while(line!=null){
                            data+=line + "\n";
                            line=br.readLine();
                            tv.setText(data);
                        }
                        br.close();
                        reader.close();
                    }catch(Exception e){
                        Toast.makeText(MainActivity.this,"Failed to read",Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    Log.d("Content",data);
                }

            }
        });
    }

}
