package com.example.cosmos.baby.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cosmos.baby.ClassListItemsOt52;
import com.example.cosmos.baby.ConnectionClass;
import com.example.cosmos.baby.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


public class FiveFragment extends Fragment {

    TextView textview_month,t1,t2,t3,t4,t5,textview_week,summa,tvse,tsum1,tsum2,tsum3,tsum4,tsum5,textview_year;
    ListView listViewOt5;
    private ArrayList<ClassListItemsOt52> itemsArrayListOt5;
    private AdapterOt5 myAdapterOt5;
    private boolean success = false;
    private ConnectionClass connectionClass;
    String login;
    String query1,item_month_string,sum,query66,item_week_string,sum_so,item_year_string;
    SharedPreferences sPref;
    Integer year,month,cur_year,item_year,cur_year_minus_odin;
    Integer mon;
    Integer today;
    Integer number_week;
    Integer item_month;
    GregorianCalendar calendar;
    String[]mesyacy = {"ЯНВАРЬ","ФЕВРАЛЬ","МАРТ","АПРЕЛЬ","МАЙ","ИЮНЬ","ИЮЛЬ","АВГУСТ","СЕНТЯБРЬ","ОКТЯБРЬ","НОЯБРЬ","ДЕКАБРЬ"};
    String[]count_week,m_count_year;
    Integer flag_week,flag_20,chas,item_yearQQQ,num_week;
    String svse,s1,s2,s3,s4,s5;
    String msg = "месяц еще не наступил";
    long animaha = 300;
    ArrayList<String> stringList;
    ArrayList<String> stringList_work;
    Date date_today;
    ImageView restart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_five, container, false);
        textview_month = view.findViewById(R.id.ot5_month);
        textview_week = view.findViewById(R.id.ot5_week);
        restart = view.findViewById(R.id.five_restart);
        t1 = view.findViewById(R.id.d1);
        t2 = view.findViewById(R.id.d2);
        t3 = view.findViewById(R.id.d3);
        t4 = view.findViewById(R.id.d4);
        t5 = view.findViewById(R.id.d5);

        tvse = view.findViewById(R.id.sumvse);
        tsum1 = view.findViewById(R.id.sum1);
        tsum2 = view.findViewById(R.id.sum2);
        tsum3 = view.findViewById(R.id.sum3);
        tsum4 = view.findViewById(R.id.sum4);
        tsum5 = view.findViewById(R.id.sum5);

        summa = view.findViewById(R.id.summa);

        listViewOt5 = view.findViewById(R.id.list_ot5);
        connectionClass = new ConnectionClass();
        itemsArrayListOt5 = new ArrayList<>();
        sPref = getActivity().getSharedPreferences("login",Context.MODE_PRIVATE);
        login = sPref.getString("login", "");
        Log.d("fgt",login);
        return view;
    }

    public void nnull(){
        listViewOt5.setAdapter(null);
        summa.setText(null);
        tvse.setText(null);
        tsum1.setText(null);
        tsum2.setText(null);
        tsum3.setText(null);
        tsum4.setText(null);
        tsum5.setText(null);
        t1.setText(null);
        t2.setText(null);
        t3.setText(null);
        t4.setText(null);
        t5.setText(null);
    }

    public void restart(View v){
        onResume();
    }

    public void getchas(){
        Date currentDate5 = new Date();
        chas = currentDate5.getHours();
        Log.d("fgt",String.valueOf(chas)+" public void getchas()");
    }

    public void settext(int a1,int a2,int a3,int a4,int a5){
        t1.setText(String.valueOf(a1));
        t2.setText(String.valueOf(a2));
        t3.setText(String.valueOf(a3));
        t4.setText(String.valueOf(a4));
        t5.setText(String.valueOf(a5));
    }

    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    @Override
    public void onResume() {
        super.onResume();

        stringList = new ArrayList<>();
        stringList_work = new ArrayList<>();
        stringList = getArrayList("array_holidays");
        stringList_work = getArrayList("array_work");


        date_today = new Date();
        Log.d("FATAL",date_today+" date_today3333333333333333333333333");
//        Date testdate = new Date(2019-1900,9,17,0,0);
//
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(testdate);
////        calendar.set(Calendar.MONTH,11);
//
////        Log.d("fgt","1111111111111111111111111111111111111  "+calendar.getTime());
//        Log.d("fgt","1111111111111111111111111111111111111  "+sdf.format(calendar.getTime()));

//        year = 2019;
        item_yearQQQ = 0;
        flag_20 = 0;
        Log.d("fgt","1");
        Date currentDate = new Date();
        Date currentYear = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("M", Locale.getDefault());
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("d", Locale.getDefault());
        Log.d("fgt","2");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy", Locale.getDefault());
        Log.d("fgt","3");
        String dateText = dateFormat.format(currentDate);
        String dateText1 = dateFormat1.format(currentDate);
        String dateText2 = dateFormat2.format(currentYear);
        Log.d("fgt",dateText2);
        mon = Integer.valueOf(dateText);
        today = Integer.valueOf(dateText1);
        cur_year = Integer.valueOf(dateText2);
        Log.d("fgt",cur_year.toString());


        Date currentDate5 = new Date();
        chas = currentDate5.getHours();
        Log.d("fgt",String.valueOf(chas)+" chas");

        getchas();

        month = mon;
        item_month_string = mesyacy[mon - 1];
//        flag_week=4;

//        new SyncData_get_array_holidays().execute();

//        sPref = getActivity().getSharedPreferences("flag_week",Context.MODE_PRIVATE);
//        SharedPreferences.Editor ed = sPref.edit();
//        ed.putInt("flag_week",flag_week);
//        ed.apply();

        Log.d("fhg",item_month_string+"resume");
        textview_month.setText(item_month_string);

        sPref = getActivity().getSharedPreferences("item_month_string",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed1 = sPref.edit();
        ed1.putString("item_month_string",item_month_string);
        ed1.apply();

//        sPref = getActivity().getSharedPreferences("flag_week",Context.MODE_PRIVATE);
//        flag_week = sPref.getInt("flag_week", 0);
//        if (flag_week==0){
//            dialog_month_ot(textview_month);
//        }

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResume();
            }
        });
        textview_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_month_ot();
            }
        });
        textview_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_week_ot();
            }
        });

        day(date_today,1);

    }



    private class SyncData_get_array_holidays extends AsyncTask<String, String, String> {

        /*String msg = "подключитесь к интернету!";
        ProgressDialog progress;*/



        @Override
        protected void onPreExecute() {
            //progress = ProgressDialog.show(Tabel.this, "", "загрузка базы данных...", true);
            /*progress = new ProgressDialog(Tabel.this,R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();*/
            stringList = new ArrayList<>();
            stringList.clear();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
//                    Log.d(MSSQL,"success = false");
                }
                else {

                    String query_v = "select * from holidays"; // число детей в выбранной группе
                    //Log.d("holidays",query_v);
                    Statement stmt_v = conn.createStatement();
                    ResultSet rs_v = stmt_v.executeQuery(query_v);
                    if (rs_v != null){
                        while (rs_v.next()){
                            try {

                                stringList.add(rs_v.getString("date_hol"));
                                //Log.d("holidays", String.valueOf(rs_v.getDate("date_hol")));

                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                        Log.d("holidays", String.valueOf(stringList.size()));
                        //msg = "ЗАГРУЖЕНО";
                        success = true;
                    }else {
                        //msg = "НЕ ЗАГРУЖЕНО";
                        success = false;
                    }


                }

                conn.close();
                Log.d("ooooo","SyncData2_tabel");
            } catch (Exception e) {
                e.printStackTrace();
                Writer writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                msg = writer.toString();
                success = false;
            }
            return msg;
        }


        @Override
        protected void onPostExecute(String msg) {
            //progress.dismiss();
            //Toast.makeText(MainActivity.this, msg + "", Toast.LENGTH_SHORT).show();

            if (success == false){

            }else {
                try {

//                    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    Date today = new Date();
//                    Log.d("holidays","Сегодня: " + sdf.format(today));
//
//                    //show first day next month and lost work day this month
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(today);
//                    calendar.add(Calendar.MONTH, 1);//Used for finding next month
//                    calendar.set(Calendar.DAY_OF_MONTH, 1);//Setting the Day of month as 1 for starting
//                    Log.d("holidays","первый календарный день след месяца: " + sdf.format(calendar.getTime()));
//                    do{
//                        calendar.add(Calendar.DATE, -1); //In the first case decease day by 1 so get the this months last day
//                    } while (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY );
//                    //Date lastDayOfMonth = calendar.getTime();
//                    Log.d("holidays","послед раб день этого месяца: " + sdf.format(calendar.getTime()));
//
//                    //show first and first work day this month
//                    Calendar calendar2 = Calendar.getInstance();
//                    calendar2.setTime(today);
//                    calendar2.set(Calendar.DAY_OF_MONTH, 1);
//                    Log.d("holidays","первый календарный день этого месяца: " + sdf.format(calendar2.getTime()));
//
//                    while (calendar2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
//                            calendar2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY  || stringList.contains(sdf.format(calendar2.getTime()))){
//                        Log.d("holidays","iteration: " + sdf.format(calendar2.getTime()));
//                        calendar2.add(Calendar.DATE, 1);
//                    }
//                    Log.d("holidays","первый раб день этого месяца: " + sdf.format(calendar2.getTime()));
//
//                    if (calendar2.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) Log.d("holidays","понедельник: " + sdf.format(calendar2.getTime()));;
//
//                    //show lost work day before this month
//                    Calendar calendar3 = Calendar.getInstance();
//                    calendar3.setTime(today);
//                    calendar3.set(Calendar.DAY_OF_MONTH, 1);
//                    do{
//                        calendar3.add(Calendar.DATE, -1); //In the first case decease day by 1 so get the this months last day
//                    } while (calendar3.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar3.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY );
//                    Log.d("holidays","послед раб день прошлого месяца: " + sdf.format(calendar3.getTime()));
//
//                    //show lost work day before this month
//                    Calendar calendar4 = Calendar.getInstance();
//                    //calendar4.setTime(today);
//                    calendar4.set(Calendar.DAY_OF_MONTH, 1);
//                    calendar4.set(Calendar.YEAR, 2019);
//                    Log.d("holidays","");
//                    Log.d("holidays","произвольный день: " + sdf.format(calendar4.getTime()));
//
//                    if (stringList.contains(sdf.format(calendar4.getTime()))) Log.d("holidays","!!!");;

                    day(date_today,1);
                }catch (Exception ex){
                    Log.d("holidays","not progress !!!");
                }
            }
        }
    }

    public void day(Date today,int i){

//        int num_week;

        List<Integer> a1 = new ArrayList<>();
        int [] mas = new int[23];
        int [] mas_day = new int[5];

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat sdf_int_day = new SimpleDateFormat("dd");
//        Date today = new Date();

        Calendar c1 = Calendar.getInstance();
        c1.setTime(today);
        c1.set(Calendar.DAY_OF_MONTH,1);
        while((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c1.getTime())))&&!stringList_work.contains(sdf.format(c1.getTime()))){
            c1.add(Calendar.DATE, 1);
//            Log.d("paut",sdf.format(c1.getTime()));
        }
        Log.d("paut","первый раб день этого месяца: " + sdf.format(c1.getTime()));
        Calendar c2 = Calendar.getInstance();
        c2.setTime(today);
        c2.add(Calendar.MONTH, 1);
        c2.set(Calendar.DAY_OF_MONTH, 1);
        do{
            c2.add(Calendar.DATE, -1);
        } while ((c2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c2.getTime())))&&!stringList_work.contains(sdf.format(c2.getTime())));

        int last_work_day = Integer.valueOf(sdf_int_day.format(c2.getTime()));
        do{
            c2.add(Calendar.DATE, -1);
        } while ((c2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c2.getTime())))&&!stringList_work.contains(sdf.format(c2.getTime())));
        do{
            c2.add(Calendar.DATE, -1);
        } while ((c2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c2.getTime())))&&!stringList_work.contains(sdf.format(c2.getTime())));
        int tabel = Integer.valueOf(sdf_int_day.format(c2.getTime()));
        Log.d("paut","послед раб день этого месяца: " + sdf.format(c2.getTime()));

        Calendar c0 = Calendar.getInstance();
        c0.setTime(today);
        while((c0.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c0.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c0.getTime())))&&!stringList_work.contains(sdf.format(c0.getTime()))){
            c0.add(Calendar.DATE, -1);
        }
        int today_work_day = Integer.valueOf(sdf_int_day.format(c0.getTime()));
        do{
            c0.add(Calendar.DATE, 1);
        } while ((c0.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c0.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c0.getTime())))&&!stringList_work.contains(sdf.format(c0.getTime())));
        Log.d("paut","сегодня или до сегодня раб день этого месяца: " + sdf.format(c0.getTime()));
        int next_work_day_after_today = Integer.valueOf(sdf_int_day.format(c0.getTime()));
        int first_work_day = Integer.valueOf(sdf_int_day.format(c1.getTime()));

        int hour = c0.get(Calendar.HOUR_OF_DAY);

        Log.d("paut", String.valueOf(first_work_day));
        Log.d("paut", String.valueOf(last_work_day));
        Log.d("paut", String.valueOf(today_work_day));
        Log.d("paut", "сегодня или до сегодня раб день этого месяца ЧАС: " +hour);
        Log.d("paut", "3 раб день с конца: " +tabel);

        if ((today_work_day > tabel || (today_work_day == tabel && hour >= 12)) && i == 1){
            Log.v("fgt","block 1");
            int iter_array = 0;
            num_week = 1;
            int iter_day_for_week = 0;
            while(first_work_day <= last_work_day){
                if ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c1.getTime())))&&!stringList_work.contains(sdf.format(c1.getTime()))){
                    c1.add(Calendar.DATE, 1);
                    first_work_day++;
                    continue;
                }else {
                    mas[iter_array] = first_work_day;
                    iter_array++;
                    c1.add(Calendar.DATE, 1);
                    if (iter_day_for_week == 5){
                        iter_day_for_week = 0;
                        mas_day = new int[mas_day.length];;
                        num_week++;
                    }
                    mas_day[iter_day_for_week] = first_work_day;
                    iter_day_for_week++;
                }
                first_work_day++;
            }
//            Log.d("paut", String.valueOf(num_week));
//            Log.d("paut", String.valueOf(iter_day_for_week));
        }else {
            Log.v("fgt","block 2");
            int iter_array = 0;
            num_week = 1;
            int iter_day_for_week = 0;
            if (hour >= 12){//next_work_day_after_today
                while(first_work_day <= next_work_day_after_today){
                    if ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c1.getTime())))&&!stringList_work.contains(sdf.format(c1.getTime()))){
                        c1.add(Calendar.DATE, 1);
                        first_work_day++;
                        continue;
                    }else {
                        mas[iter_array] = first_work_day;
                        iter_array++;
                        c1.add(Calendar.DATE, 1);
                        if (iter_day_for_week == 5){
                            iter_day_for_week = 0;
                            mas_day = new int[mas_day.length];;
                            num_week++;
                        }
                        mas_day[iter_day_for_week] = first_work_day;
                        iter_day_for_week++;
                    }
                    first_work_day++;
                }
            }else {
                Log.v("fgt","block 3");
                while(first_work_day <= today_work_day){
                    if ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c1.getTime())))&&!stringList_work.contains(sdf.format(c1.getTime()))){
                        c1.add(Calendar.DATE, 1);
                        first_work_day++;
                        continue;
                    }else {
                        mas[iter_array] = first_work_day;
                        iter_array++;
                        c1.add(Calendar.DATE, 1);
                        if (iter_day_for_week == 5){
                            iter_day_for_week = 0;
                            mas_day = new int[mas_day.length];;
                            num_week++;
                        }
                        mas_day[iter_day_for_week] = first_work_day;
                        iter_day_for_week++;
                    }
                    first_work_day++;
                }
            }



            Log.d("paut", "num_week "+String.valueOf(num_week));
            Log.d("paut", "iter_day_for_week "+String.valueOf(iter_day_for_week));
        }



//        Log.d("paut", String.valueOf(a1.size()));
//        int y = 0;
//        for (int num : a1){
//            Log.d("paut", String.valueOf(num));
//            y++;
//        }
//        Log.d("paut", String.valueOf(y));

//        for (int i=0; i < mas.length; i++) {
//            Log.d("paut", String.valueOf(mas[i]));
//        }
//        Log.d("paut", String.valueOf(mas_day.length));

//        for (int num : mas){
//            Log.d("paut", String.valueOf(num));
//        }
        int poker = 0;
        for (int num : mas_day){
//            Log.d("paut", String.valueOf(num));
            if (num!=0){
                poker++;
            }
        }

//        Log.d("paut", "num_week " + num_week);
//        Log.d("paut", "poker " + poker);


        String o1 = String.valueOf(mas_day[0]);
        String o2 = String.valueOf(mas_day[1]);
        String o3 = String.valueOf(mas_day[2]);
        String o4 = String.valueOf(mas_day[3]);
        String o5 = String.valueOf(mas_day[4]);

        if (Integer.valueOf(o1)==0)o1="";
        if (Integer.valueOf(o2)==0)o2="";
        if (Integer.valueOf(o3)==0)o3="";
        if (Integer.valueOf(o4)==0)o4="";
        if (Integer.valueOf(o5)==0)o5="";

//        settext(15,16,17,18,19);

        t1.setText(o1);
        t2.setText(o2);
        t3.setText(o3);
        t4.setText(o4);
        t5.setText(o5);

//        if (mas_day[0] == today_work_day){t1.setTextColor(Color.parseColor("#FF6347"));
//        if (mas_day[1] == today_work_day)t2.setTextColor(Color.parseColor("#FF6347"));
//        if (mas_day[2] == today_work_day)t3.setTextColor(Color.parseColor("#FF6347"));
//        if (mas_day[3] == today_work_day)t4.setTextColor(Color.parseColor("#FF6347"));
//        if (mas_day[4] == today_work_day)t5.setTextColor(Color.parseColor("#FF6347"));

        textview_week.setText(String.valueOf(num_week)+" НЕДЕЛЯ");

        if (i == 1) number_week = num_week;

        Log.d("fgt",month.toString());
        Log.d("fgt",mon.toString());

        String name_table = "deti";

        if (month<mon){
            name_table = "deti"+month+cur_year;
        }

        query66 = "exec Five_days "+cur_year+","+month+","+mas[0]+","+mas[1]+","+mas[2]+","+mas[3]+","+mas[4]+","+mas[5]+","+mas[6]+","+mas[7]+","+mas[8]+","+mas[9]+","+mas[10]+","+mas[11]+","+mas[12]+","+mas[13]+","+mas[14]+","+mas[15]+","+mas[16]+","+mas[17]+","+mas[18]+","+mas[19]+","+mas[20]+","+mas[21]+","+mas[22]+",'"+name_table+"'";

        switch (poker){
            case 1:
                query1 = "exec DAY1 "+cur_year+","+month+","+mas_day[0]+",'"+name_table+"'";
                break;
            case 2:
                query1 = "exec DAY2 "+cur_year+","+month+","+mas_day[0]+","+mas_day[1]+",'"+name_table+"'";
                break;
            case 3:
                query1 = "exec DAY3 "+cur_year+","+month+","+mas_day[0]+","+mas_day[1]+","+mas_day[2]+",'"+name_table+"'";
                break;
            case 4:
                query1 = "exec DAY4 "+cur_year+","+month+","+mas_day[0]+","+mas_day[1]+","+mas_day[2]+","+mas_day[3]+",'"+name_table+"'";
                break;
            case 5:
                query1 = "exec DAY5 "+cur_year+","+month+","+mas_day[0]+","+mas_day[1]+","+mas_day[2]+","+mas_day[3]+","+mas_day[4]+",'"+name_table+"'";
                break;
            default:
                Toast.makeText(getActivity(), "error flag5", Toast.LENGTH_SHORT).show();
                break;
        }

        Log.d("fgt",query66);
        Log.d("fgt",query1);
        Log.d("fgt","public void day NEW !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        start1();
    }

    public void start1(){
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        if ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(calendar.getTime())))&&!stringList_work.contains(sdf.format(calendar.getTime()))){
            listViewOt5.animate()
                    .alpha(0f)
                    .setDuration(300)
                    .setInterpolator(new FastOutLinearInInterpolator());
            Toast.makeText(getActivity(), "Сегодня выходной !", Toast.LENGTH_SHORT).show();
        }else {
            new Ot5_start().execute("");
            new Ot5_start2().execute("");
        }
    }

    private class Ot5_start extends AsyncTask<String, String, String> {

        String msg = "подключитесь к интернету!";
        ProgressDialog progress;
        //List<String> list_count_year = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();
            //itemsArrayListOt5.clear();
            //Animation animation = AnimationUtils.loadAnimation(Otchet5_18_07_19.this, android.R.anim.slide_out_right);

            //list_count_year.clear();
            //summa.setText(null);
            summa.animate()
                    .alpha(0f)
                    .setDuration(300)
                    .setInterpolator(new FastOutLinearInInterpolator());
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
                }
                else {
                    if(!conn.isClosed()){
                        Log.d("fgt","open");
                    }

                    Log.d("fgt",query66+"******************************");
                    //Log.d("fgt",query1+"******************************");

                    Statement stmt66 = conn.createStatement();
                    ResultSet rs66 = stmt66.executeQuery(query66);
                    if (rs66 != null){
                        while (rs66.next()){
                            try {
                                sum = rs66.getString("summa").trim();
                                //sum_so = rs66.getString("sotr").trim();
                                //Log.d("fgt",sum+"******************************");
                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                        //msg = "ЗАГРУЖЕНО";
                        success = true;
                    }else {
                        //msg = "НЕ ЗАГРУЖЕНО";
                        success = false;
                    }



                    /*String query_year = "select distinct(year(datenotgo)) [year] from gogo";
                    Log.d("fgt",query_year);
                    Statement stmt1 = conn.createStatement();
                    ResultSet rs1 = stmt1.executeQuery(query_year);
                    if (rs1 != null){
                        while (rs1.next()){
                            try {
                                list_count_year.add(rs1.getString("year"));
                                list_count_year.add("2018");

                                Log.d("fgt",rs1.getString("year"));
                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                        //msg = "ЗАГРУЖЕНО";
                        success = true;
                    }else {
                        //msg = "НЕ ЗАГРУЖЕНО";
                        success = false;
                    }*/





                }
                conn.close();
                if (conn.isClosed()){
                    Log.d("fgt","closed");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Writer writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                msg = writer.toString();
                success = false;
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {
            progress.dismiss();

            //Toast.makeText(MainActivity.this, msg + "", Toast.LENGTH_SHORT).show();
            if (success == false){

            }else {
                try {
                    /*myAdapterOt5 = new AdapterOt5(itemsArrayListOt5, Otchet_5.this);
                    listViewOt5.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listViewOt5.setAdapter(myAdapterOt5);*/

                    //m_count_year = list_count_year.toArray(new String[0]);



                    summa.setText(sum);
                    //listViewOt5.setStackFromBottom(false);

                    /*summa.setVisibility(View.VISIBLE);
                    //Animation animation = AnimationUtils.loadAnimation(Otchet5_18_07_19.this, android.R.anim.slide_in_left);
                    Animation animation = AnimationUtils.loadAnimation(Otchet5_18_07_19.this, android.R.anim.fade_in);
                    animation.setDuration(animaha);
                    summa.startAnimation(animation);*/

                    summa.animate()
                            .alpha(1.0f)
                            .setDuration(100)
                            .setInterpolator(new FastOutLinearInInterpolator());

                }catch (Exception ex){

                }
            }

            /*Ot5_start2 ot5Start2 = new Ot5_start2();
            ot5Start2.execute("");*/
        }
    }

    private class Ot5_start2 extends AsyncTask<String, String, String> {

        String msg = "подключитесь к интернету!";
        ProgressDialog progress;


        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();
            /*Animation animation = AnimationUtils.loadAnimation(Otchet5_18_07_19.this, android.R.anim.fade_out);
            animation.setDuration(animaha);
            listViewOt5.startAnimation(animation);
            listViewOt5.setVisibility(View.INVISIBLE);
            //listViewOt5.setAdapter(null);
            itemsArrayListOt5.clear();*/

            listViewOt5.animate()
                    .alpha(0f)
                    .setDuration(300)
                    .setInterpolator(new FastOutLinearInInterpolator());

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
                }
                else {
                    if(!conn.isClosed()){
                        Log.d("fgt","open");
                    }
                    itemsArrayListOt5.clear();
                    /*String query667 = "exec dell_all_##bb";
                    Log.d("fgt",query667);
                    Statement stmt66 = conn.createStatement();
                    stmt66.executeQuery(query667);*/


                    Log.d("fgt",query1);
                    Statement stmt1 = conn.createStatement();
                    ResultSet rs1 = stmt1.executeQuery(query1);
                    if (rs1 != null){
                        while (rs1.next()){
                            try {
                                itemsArrayListOt5.add(new ClassListItemsOt52(rs1.getString("name").trim(),rs1.getString("kolvse").trim(),rs1.getString("kol").trim(),rs1.getString("kol1").trim(),rs1.getString("kol2").trim(),rs1.getString("kol3").trim(),rs1.getString("kol4").trim()));
                                //Log.d("fgt",rs1.getString("kolvse"));
                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                        //msg = "ЗАГРУЖЕНО";
                        success = true;
                    }else {
                        //msg = "НЕ ЗАГРУЖЕНО";
                        success = false;
                    }

                    /*String query = "select sum(isnull(kolvse,0)) as kolvse, sum(isnull(kol,0)) as kol, sum(isnull(kol1,0)) as kol1, sum(isnull(kol2,0)) as kol2, sum(isnull(kol3,0)) as kol3, sum(isnull(kol4,0)) as kol4 from ##bb111";
                    Log.d("fgt",query);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs != null){
                        while (rs.next()){
                            try {
                                svse = rs.getString("kolvse").trim();
                                s1 = rs.getString("kol").trim();
                                s2 = rs.getString("kol1").trim();
                                s3 = rs.getString("kol2").trim();
                                s4 = rs.getString("kol3").trim();
                                s5 = rs.getString("kol4").trim();
                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                        //msg = "ЗАГРУЖЕНО";
                        success = true;
                    }else {
                        //msg = "НЕ ЗАГРУЖЕНО";
                        success = false;
                    }*/

                }
                conn.close();
                if (conn.isClosed()){
                    Log.d("fgt","closed");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Writer writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                msg = writer.toString();
                success = false;
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {
            progress.dismiss();

            //Toast.makeText(MainActivity.this, msg + "", Toast.LENGTH_SHORT).show();
            if (success == false){

            }else {
                try {


                    myAdapterOt5 = new AdapterOt5(itemsArrayListOt5, getActivity());
                    listViewOt5.setAdapter(myAdapterOt5);

                    /*listViewOt5.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(Otchet5_18_07_19.this, android.R.anim.fade_in);
                    animation.setDuration(animaha);
                    listViewOt5.startAnimation(animation);*/


                    listViewOt5.animate()
                            .alpha(1.0f)
                            .setDuration(100)
                            .setInterpolator(new FastOutLinearInInterpolator());

                    /*tvse.setText(svse);
                    tsum1.setText(s1);
                    tsum2.setText(s2);
                    tsum3.setText(s3);
                    tsum4.setText(s4);
                    tsum5.setText(s5);*/




                }catch (Exception ex){

                }
            }


        }
    }

    public class AdapterOt5 extends BaseAdapter {

        public class ViewHolder{
            TextView textName;
            TextView textKvse;
            TextView textK;
            TextView textK1;
            TextView textK2;
            TextView textK3;
            TextView textK4;
            LinearLayout llll;
        }

        public List<ClassListItemsOt52> parkingList;

        public Context context;
        ArrayList<ClassListItemsOt52> arraylist;

        private AdapterOt5(List<ClassListItemsOt52> apps, Context context){
            this.parkingList = apps;
            this.context = context;
            arraylist = new ArrayList<>();
            arraylist.addAll(parkingList);
        }

        public void remove(int position) {
            parkingList.remove(position);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return parkingList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            ViewHolder viewHolder = null;
            if (rowView == null){
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.list_content_ot5, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textName = (TextView)rowView.findViewById(R.id.name_grup_ot5);
                viewHolder.textKvse = (TextView)rowView.findViewById(R.id.ddvse);
                viewHolder.textK = (TextView)rowView.findViewById(R.id.dd5);
                viewHolder.textK1 = (TextView)rowView.findViewById(R.id.dd10);
                viewHolder.textK2 = (TextView)rowView.findViewById(R.id.dd15);
                viewHolder.textK3 = (TextView)rowView.findViewById(R.id.dd20);
                viewHolder.textK4 = (TextView)rowView.findViewById(R.id.ddall);
                viewHolder.llll = (LinearLayout)rowView.findViewById(R.id.LL_boss_dv);
                rowView.setTag(viewHolder);
            }
            else {
                viewHolder = (ViewHolder)convertView.getTag();
            }

            viewHolder.textName.setText(parkingList.get(position).getName()+"");
            viewHolder.textKvse.setText(parkingList.get(position).getKolvse()+"");
            viewHolder.textK.setText(parkingList.get(position).getKol()+"");
            viewHolder.textK1.setText(parkingList.get(position).getKol1()+"");
            viewHolder.textK2.setText(parkingList.get(position).getKol2()+"");
            viewHolder.textK3.setText(parkingList.get(position).getKol3()+"");
            viewHolder.textK4.setText(parkingList.get(position).getKol4()+"");


            if (parkingList.get(position).getName().contains("всего")){
                viewHolder.llll.setBackgroundResource(R.color.yelow_light);
            }
            if (parkingList.get(position).getName().contains("Сотрудники")){
                viewHolder.llll.setBackgroundResource(R.color.gray5);
            }


            return rowView;
        }
    }

    public void dialog_month_ot(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите месяц :");
        builder.setItems(mesyacy, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                month = item + 1;
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat sdf_int_day = new SimpleDateFormat("dd");

//                if (flag_week==4){
//                    count_week = new String[]{"1 НЕДЕЛЯ", "2 НЕДЕЛЯ", "3 НЕДЕЛЯ", "4 НЕДЕЛЯ"};
//                }else if (flag_week==5){
//                    count_week = new String[]{"1 НЕДЕЛЯ", "2 НЕДЕЛЯ", "3 НЕДЕЛЯ", "4 НЕДЕЛЯ", "5 НЕДЕЛЯ"};
//                }
//
//                sPref = getActivity().getSharedPreferences("flag_week",Context.MODE_PRIVATE);
//                SharedPreferences.Editor ed = sPref.edit();
//                ed.putInt("flag_week",flag_week);
//                ed.apply();
//
//                Log.d("otchet55",String.valueOf(flag_week));
                item_month_string = mesyacy[item];

                sPref = getActivity().getSharedPreferences("item_month_string",Context.MODE_PRIVATE);
                SharedPreferences.Editor ed1 = sPref.edit();
                ed1.putString("item_month_string",item_month_string);
                ed1.apply();

                textview_month.setText(item_month_string);
//                Log.d("fgt","1  "+item);
//                Date r1 = new Date();
                Date r1 = new Date(cur_year - 1900,item,1,0,0);
                Calendar c2 = Calendar.getInstance();
                c2.setTime(r1);
//                Log.d("fgt","21  "+sdf.format(c2.getTime()));
//                c2.set(Calendar.MONTH, item);
//                Log.d("fgt","2  "+sdf.format(c2.getTime()));

                c2.add(Calendar.MONTH, 1);
//                Log.d("fgt","3  "+sdf.format(c2.getTime()));
//                c2.set(Calendar.DAY_OF_MONTH, 1);
//                Log.d("fgt","4  "+sdf.format(c2.getTime()));
                do{
                    c2.add(Calendar.DATE, -1);
                } while ((c2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c2.getTime())))&&!stringList_work.contains(sdf.format(c2.getTime())));
//                Log.d("fgt","5  "+sdf.format(c2.getTime()));
//                Log.d("fgt","333333333333333333  "+String.valueOf(sdf.format(c2.getTime())));
                int last_work_day = Integer.valueOf(sdf_int_day.format(c2.getTime()));
//                Log.d("fgt","333333333333333333  "+String.valueOf(last_work_day));


                Date testdate = new Date(cur_year - 1900,item,last_work_day,0,0);
//
//
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(testdate);
                Log.d("prover","1111111111111111111  "+sdf.format(calendar.getTime()));


                if (mon == month)day(date_today,1);
                if (mon < month)nnull();
                if (mon > month)day(testdate,1);

                /*Log.d("fgt",month.toString());
                Log.d("fgt",mon.toString());*/

                dialog.cancel();
            }
        });
        AlertDialog alertDialogObject = builder.create();
        ListView listView=alertDialogObject.getListView();
        listView.setDivider(new ColorDrawable(Color.GRAY)); // set color
        listView.setDividerHeight(1); // set height
        listView.animate().start();
        alertDialogObject.setCancelable(true);
        alertDialogObject.show();
    }

    public void dialog_week_ot(){
        switch (number_week){
            case 1:
                count_week = new String[]{"1 НЕДЕЛЯ"};
                break;
            case 2:
                count_week = new String[]{"1 НЕДЕЛЯ", "2 НЕДЕЛЯ"};
                break;
            case 3:
                count_week = new String[]{"1 НЕДЕЛЯ", "2 НЕДЕЛЯ", "3 НЕДЕЛЯ"};
                break;
            case 4:
                count_week = new String[]{"1 НЕДЕЛЯ", "2 НЕДЕЛЯ", "3 НЕДЕЛЯ", "4 НЕДЕЛЯ"};
                break;
            case 5:
                count_week = new String[]{"1 НЕДЕЛЯ", "2 НЕДЕЛЯ", "3 НЕДЕЛЯ", "4 НЕДЕЛЯ", "5 НЕДЕЛЯ"};
                break;

        }

//        getchas();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите неделю :");
        builder.setItems(count_week, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                item_week_string = count_week[item];
                textview_week.setText(item_week_string);

//                Log.d("fgt", String.valueOf(item));

                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat sdf_int_day = new SimpleDateFormat("dd");

                Date r1 = new Date();
                Calendar c2 = Calendar.getInstance();
                c2.setTime(r1);
                c2.set(Calendar.MONTH, month-1);
//                Log.d("fgt","2  "+sdf.format(c2.getTime()));

//        c2.add(Calendar.MONTH, 1);
////                Log.d("fgt","3  "+sdf.format(c2.getTime()));
                c2.set(Calendar.DAY_OF_MONTH, 1);
//                Log.d("fgt","4  "+sdf.format(c2.getTime()));
                int t = 0;
                int y = 0;

                while (t < (item+1)){
                    if ((c2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c2.getTime())))&&!stringList_work.contains(sdf.format(c2.getTime()))){

                    }else {
                        y++;
                        Log.d("fgt","y  "+y);
                        if (y==5){
                            y=0;
                            t++;
                        }
                    }
                    c2.add(Calendar.DATE, 1);
                }
                do{
                    c2.add(Calendar.DATE, -1);
                } while ((c2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c2.getTime())))&&!stringList_work.contains(sdf.format(c2.getTime())));



                Log.d("fgt","4  "+sdf.format(c2.getTime()));
                int last_day = Integer.valueOf(sdf_int_day.format(c2.getTime()));
                Date testdate = new Date(cur_year - 1900,month-1,last_day,0,0);

//                Calendar calendar4 = Calendar.getInstance();
//                calendar4.setTime(testdate);
//                Log.d("fgt","1111111111111111111111111111111111111  "+sdf.format(calendar4.getTime()));

                if (month > mon) {
                    nnull();
                }else {
                    if ((item+1) < count_week.length){
                        day(testdate,0);
                    }else {
                        if (mon == month){
                            onResume();
                        }else {
                            Date r = new Date();
                            Calendar c = Calendar.getInstance();
                            c.setTime(r);
                            c.set(Calendar.MONTH, month-1);
                            c.add(Calendar.MONTH, 1);
                            c.set(Calendar.DAY_OF_MONTH, 1);
                            do{
                                c.add(Calendar.DATE, -1);
                            } while ((c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c.getTime())))&&!stringList_work.contains(sdf.format(c.getTime())));
                            int last_work_day = Integer.valueOf(sdf_int_day.format(c.getTime()));
                            Date d = new Date(cur_year - 1900,month-1,last_work_day,0,0);
//                        Calendar calendar = Calendar.getInstance();
//                        calendar.setTime(d);
//                        Log.d("fgt","333333333333333333  "+String.valueOf(sdf.format(calendar.getTime())));
                            day(d,0);
                        }
                    }
                }


                dialog.cancel();
            }
        });
        AlertDialog alertDialogObject = builder.create();
        ListView listView=alertDialogObject.getListView();
        listView.setDivider(new ColorDrawable(Color.GRAY)); // set color
        listView.setDividerHeight(1); // set height
        listView.animate().start();
        alertDialogObject.setCancelable(true);
        alertDialogObject.show();
    }
}
