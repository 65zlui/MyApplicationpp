package com.example.myapplicationpp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {


    public static long timea;
    public static long timeb;
    public static TextView title1;
    private String flag;
    public static TextView title2;
    public static int idx;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final News[] news=new News[2];
        news[0]=new News();
        news[0].setTitle("国家防总:2013年洪灾");
        news[0].setContent("    据国家防总办公室统计，并同相关部门核实，2013年全国洪涝灾害受灾人口1.2亿人，因灾死亡774人、失踪374人，" +
                "倒塌房屋53万间，农作物受灾11901千公顷、成灾6623千公顷，受损水库1241座、堤防3.7万处、护岸5.3万处、水闸7187座，" +
                "县级以上城市受淹234个，洪涝灾害直接经济损失3146亿元。\n" +
                "    2013年我国洪涝灾害损失总体偏轻，但部分地区灾情较重。去年，我国东北、华北大部、西北东部和西部、西南东北部、华南大部降雨量较常年偏多1-2成，黑龙江、松花江流域汛期平均降雨量较常年偏多3-4成。先后有9个台风在我国登陆。");
        news[0].setSource("新华网");
        news[0].setTime("2014/1/9");

        news[1]=new News();
        news[1].setTitle("九寨沟7级地震救灾工作");
        news[1].setContent("    按照习近平总书记、李克强总理等中央领导同志重要指示批示精神，国家减灾委、民政部针对四川九寨沟7.0级地震，紧急启动国家Ⅲ级救灾应急响应，国家减灾委、国务院抗震救灾指挥部组成联合工作组，由民政部副部长顾朝曦、中国地震局副局长阴朝民带队，会同发展改革委、财政部、国土资源部、住房城乡建设部、交通运输部、卫生计生委等8个部门有关负责同志紧急赶赴灾区，指导和帮助做好抢险救援、受灾群众紧急转移安置、伤病员救治和灾区交通通讯抢通保通等各项救灾工作。\n" +
                "\n" +
                "   据四川省民政厅报告，截至9日3时，地震已造成九寨沟县7人死亡、数十人受伤，具体灾情正在进一步核查统计中。");
        news[1].setSource("民政部门户网站");
        news[1].setTime("2017/5/18");

        title1= findViewById(R.id.news1_title);
        title1.setText(news[0].getTitle());
//        title1.setTextColor(Color.BLACK);
        TextView source1= findViewById(R.id.source1);
        source1.setText(news[0].getSource());
        TextView time1= findViewById(R.id.time1);
        time1.setText(news[0].getTime());

        title2= findViewById(R.id.news2_title);
        title2.setText(news[1].getTitle());
//        title2.setTextColor(Color.BLACK);
        TextView source2= findViewById(R.id.source2);
        source2.setText(news[1].getSource());
        TextView time2= findViewById(R.id.time2);
        time2.setText(news[1].getTime());

        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timea=System.currentTimeMillis();
                flag="n1";
                Intent intent=new Intent();
                intent.setClassName(ListActivity.this,"com.example.myapplicationpp.DetailActivity");
                idx = R.id.news1_title;

                intent.putExtra("title",news[0].getTitle());
                intent.putExtra("source",news[0].getSource());
                intent.putExtra("time",news[0].getTime());
                intent.putExtra("news_content",news[0].getContent());
                startActivityForResult(intent,1);
            }
        });

        title2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timea=System.currentTimeMillis();
                flag="n2";
                Intent intent=new Intent();
                intent.setClassName(ListActivity.this,"com.example.myapplicationpp.DetailActivity");
                idx = R.id.news2_title;
                intent.putExtra("title",news[1].getTitle());
                intent.putExtra("source",news[1].getSource());
                intent.putExtra("time",news[1].getTime());
                intent.putExtra("news_content",news[1].getContent());
                startActivityForResult(intent,1);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onRestart() {
        super.onRestart();

        if((timea-timeb)>30*1000)
        {
            if(flag.equals("n1")){
                title1=(TextView)findViewById(R.id.news1_title);
                title1.setTextColor(Color.GRAY);
            }
            if(flag.equals("n2")){
                title2=(TextView)findViewById(R.id.news2_title);
                title2.setTextColor(Color.GRAY);
            }
        }


    }

}
