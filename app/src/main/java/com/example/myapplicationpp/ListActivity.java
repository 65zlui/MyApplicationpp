package com.example.myapplicationpp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    public static ListView listView;
    private CreateNewsDB createNewsDB;
    //接收进入和退出DetailActivity的时间
    static long ttime1 = 0;
    static long ttime2 = 0;

    //用于记录新闻条数
    int count = 0;
    //用于记录被点击的行号
    String number;


    //用于记录被点击的新闻的标题、来源、时间、内容

    //用于承装浏览过的新闻ID
    static List<String> lstID = new ArrayList<String>();
    public static TextView title1;
    public static TextView title2;
    public static String t1;
    public static String t2;
    public static String t3;
    public static String t4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

        setContentView(R.layout.activity_list);
        SharedPreferences.Editor editor = getSharedPreferences("AlRead", MODE_PRIVATE).edit();

        CreateNewsDB createNewsDB = new CreateNewsDB(this, "News.db", null, 1);
        editor.putString("data", ",");
        editor.apply();
        SQLiteDatabase db = createNewsDB.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put("Title", "国家防总:2013年洪灾");
        values.put("Content", "    据国家防总办公室统计，并同相关部门核实，2013年全国洪涝灾害受灾人口1.2亿人，因灾死亡774人、失踪374人," +
                "                倒塌房屋53万间，农作物受灾11901千公顷、成灾6623千公顷，受损水库1241座、堤防3.7万处、护岸5.3万处、水闸7187座" +
                "                县级以上城市受淹234个，洪涝灾害直接经济损失3146亿元。" +
                "                2013年我国洪涝灾害损失总体偏轻，但部分地区灾情较重。去年，我国东北、华北大部、西北东部和西部、西南东北部、华南大部降雨量较常年偏多1-2成，黑龙江、松花江流域汛期平均降雨量较常年偏多3-4成。先后有9个台风在我国登陆。");
        values.put("Source", "新华网");
        values.put("time", "2014/1/9");
        values.put("imageSource", R.drawable.hz);
        db.insert("NewsTable", null, values);
        values.clear();

        values.put("Title", "九寨沟7级地震救灾工作");
        values.put("Content", "    按照习近平总书记、李克强总理等中央领导同志重要指示批示精神，国家减灾委、民政部针对四川九寨沟7.0级地震，紧急启动国家Ⅲ级救灾应急响应，国家减灾委、国务院抗震救灾指挥部组成联合工作组，由民政部副部长顾朝曦、中国地震局副局长阴朝民带队，会同发展改革委、财政部、国土资源部、住房城乡建设部、交通运输部、卫生计生委等8个部门有关负责同志紧急赶赴灾区，指导和帮助做好抢险救援、受灾群众紧急转移安置、伤病员救治和灾区交通通讯抢通保通等各项救灾工作。" +
                " 据四川省民政厅报告，截至9日3时，地震已造成九寨沟县7人死亡、数十人受伤，具体灾情正在进一步核查统计中。");
        values.put("Source", "民政部门户网站");
        values.put("time", "2017/5/18");
        values.put("imageSource", R.drawable.asd);
        db.insert("NewsTable", null, values);
        values.clear();

        List<News> lstNews = new ArrayList<News>();

        Cursor cursor = db.query("NewsTable", null, null, null, null, null, null);
        int count = cursor.getCount();
        int i = 0;

        final News[] news = new News[count];
//        final News[] news=new News[2];
        if (cursor.moveToFirst()) {
            do {
                //获取数据值

                int id = cursor.getInt(cursor.getColumnIndex("nlID"));
                String title = cursor.getString(cursor.getColumnIndex("Title"));
                String content = cursor.getString(cursor.getColumnIndex("Content"));
                String source = cursor.getString(cursor.getColumnIndex("Source"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                int imageSource = cursor.getInt(cursor.getColumnIndex("imageSource"));
                //将每组值重组为一则新闻news[i]
                news[i] = new News();

                news[i].setId(String.valueOf(id));
                news[i].setTitle(title);
                news[i].setContent(content);
                news[i].setSource(source);
                news[i].setTime(time);
                news[i].setImageSource(imageSource);
                //将每条新闻存入lstNews
                lstNews.add(news[i]);
                i++;
            } while (cursor.moveToNext());
        }




    class MyAdapter extends ArrayAdapter<News> {
        //每行新闻的布局
        private int resourceId;

        public MyAdapter(Context context, int resource, List<News> objects) {
            super(context, resource, objects);
            resourceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //获取当前项的实例
            News myNew = getItem(position);
            //利用convertView缓存提高ListView效率
            View view;
            if (convertView == convertView) {
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            } else {
                view = convertView;
            }
            //获取对应控件实例并赋值
            TextView tvTitle1 = (TextView) view.findViewById(R.id.news1_title);
            tvTitle1.setText(myNew.getTitle());
            tvTitle1.setTextColor(Color.BLACK);
            TextView tvTitle2 = (TextView) view.findViewById(R.id.source1);
            tvTitle2.setText("来源：" + myNew.getSource());
            TextView tvTitle3 = (TextView) view.findViewById(R.id.time1);
            tvTitle3.setText("时间：" + myNew.getTime());
            ImageView ivTitle = (ImageView) view.findViewById(R.id.imageView);
            ivTitle.setImageResource(myNew.getImageSource());
            return view;
        }
    }

    MyAdapter adapter = new MyAdapter(ListActivity.this, R.layout.item, lstNews);
    listView=findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

    {

        @Override
        public void onItemClick (AdapterView < ? > parent, View view,int position, long id){

        //用Intent传输数据到DetailActivity
        Intent intent = new Intent();
        intent.setClassName(ListActivity.this, "com.example.myapplicationpp.DetailActivity");
        number = news[position].getId();
        intent.putExtra("title", news[position].getTitle());
        intent.putExtra("source", news[position].getSource());
        intent.putExtra("time", news[position].getTime());
        intent.putExtra("news_content", news[position].getContent());
        startActivityForResult(intent, 1);
    }
    });
    //借用适配器ArrayAdapter来将数据传入ListView
}



    private void getAlRead()
    {
        //达到规定阅读时间，则将新闻ID存入文件
        if((ttime2-ttime1)/1000>1 && ttime1!=0) {

            SharedPreferences.Editor editor = getSharedPreferences("AlRead", MODE_PRIVATE).edit();
            lstID.add(number);
            //将已读新闻ID转化为字符串保存
            String str = "";
            for (int i = 0; i < lstID.size(); i++) {
                str += lstID.get(i) + ",";
            }
            editor.putString("data", str);
            editor.apply();
        }
    }

    private void changeColor()
    {
        //如果文件存在，则将文件内已读新闻置灰
        File mml=new File("data/data/com.example.myapplicationpp/shared_prefs","AlRead.xml");
        if(mml.exists())
        {
            SharedPreferences pref=getSharedPreferences("AlRead", MODE_PRIVATE);
            String str=pref.getString("data","");
            //将字符串以“，”分割为字符数组sstr
            String sstr[]=str.split("[,]");
            //再将字符数组存入List
            List<String> lstAlRead=new ArrayList<String>();
            for (int i=0; i<sstr.length; i++) {
                if(!lstAlRead.contains(sstr[i])) {
                    lstAlRead.add(sstr[i]);
                }
            }
            //将lstAlRead中的新闻ID置灰
            for(int i=0;i<lstAlRead.size();i++)
            {
                // 获得子item的layout
                LinearLayout layout = (LinearLayout)listView.getChildAt(Integer.valueOf(lstAlRead.get(i))-1);
                TextView tvAlRead = (TextView) layout.findViewById(R.id.news1_title);
                tvAlRead.setTextColor(Color.GRAY);
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        ttime2=System.currentTimeMillis();

        //若达到规定浏览时间，则将该新闻ID添加进存放已浏览新闻的list
        getAlRead();
        //判断新闻是否已经浏览，若是，则将标题颜色置灰
        changeColor();
    }


}
