package com.example.myapplicationpp;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.myapplicationpp.ListActivity.t1;
import static com.example.myapplicationpp.ListActivity.t2;
import static com.example.myapplicationpp.ListActivity.t3;
import static com.example.myapplicationpp.ListActivity.t4;
import static com.example.myapplicationpp.ListActivity.title1;
import static com.example.myapplicationpp.ListActivity.title2;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
   public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
       View view=inflater.inflate(R.layout.fragment_detail,container,false);

//       TextView title=(TexView)view.findViewById(R.id.title);
//       title.setText((CharSequence) ListActivity.title1);
//       title.setText((CharSequence) ListActivity.title2);
//       TextView source=

       //title1= (TextView)view.findViewById(R.id.news1_title);
       title1= (TextView)view.findViewById(R.id.ftitle);
       title1.setText(t1);
//        title1.setTextColor(Color.BLACK);
       //TextView source1= (TextView)view.findViewById(R.id.source1);
       TextView source1= (TextView)view.findViewById(R.id.fsource);
       source1.setText(t2);
       //TextView time1= (TextView)view.findViewById(R.id.time1);
       TextView time1= (TextView)view.findViewById(R.id.ftime);
       time1.setText(t3);

        TextView x=(TextView)view.findViewById(R.id.fcontent);
        x.setMovementMethod(ScrollingMovementMethod.getInstance());
       x.setText(t4);
      /* title2= (TextView)view.findViewById(R.id.news2_title);
       title2.setText(news[1].getTitle());
//        title2.setTextColor(Color.BLACK);
       TextView source2= (TextView)view.findViewById(R.id.source2);
       source2.setText(news[1].getSource());
       TextView time2= (TextView)view.findViewById(R.id.time2);
       time2.setText(news[1].getTime());*/


       return  view;
   }

}
