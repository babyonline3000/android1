package com.example.cosmos.baby.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cosmos.baby.ClassListItemsDateReasonInMonth;
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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class CalendarFragment extends Fragment{

    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,t23,t24,t25,t26,t27,t28,t29,t30,t31,t32,t33,t34,t35,t36,t37,t38,t39,t40;
    TextView month_back,month_next,name_current_month,t_karan_show,namename,set_gogo_all_month,del_gogo_all_month;
    String [] month_array = {"ЯНВАРЬ","ФЕВРАЛЬ","МАРТ","АПРЕЛЬ","МАЙ","ИЮНЬ","ИЮЛЬ","АВГУСТ","СЕНТЯБРЬ","ОКТЯБРЬ","НОЯБРЬ","ДЕКАБРЬ"};
    Calendar c1;
    ArrayList<String> stringList;
    ArrayList<String> stringList_work;
    private boolean success = false;
    private ConnectionClass connectionClass;
    String login,name;
    Integer id,id_gruppa;
    Integer month,year,idso,star,boss_start,so,idso_x,check_interpolar;
    SharedPreferences sPref;
    List<Integer> data, data_today,data_karan,data_karan2;
    String numClick,nameClick,reason,date_to_db;
    private final int V_NORMAL = 0;
    private final int V_TODAY = 1;
    private final int MED = 2;
    String[] bolez, bolez2, bolez3;
    String a = "- ПРОЧЕЕ (отпуск, санаторий, выходной и т.п.)";
    String b = "- ОРЗ";
    String c = "- РВОТА, ПОНОС";
    String d = "- ВЕТРЯНКА";
    String f = "- ДРУГИЕ заболевания (отит, бронхит, гастрит и т.п.)";
    String a1 = "- ПРИДУ С ОПОЗДАНИЕМ";
    String b1 = "- НЕ ПРИДУ СОВСЕМ";
    String msg = "отметка невозможна";
    String msg_closed_tabel = "отметка невозможна, табель закрыт";
    String msg_del_closed_tabel = "удаление невозможно, табель закрыт";
    String msg_del = "удаление невозможно";
    private ArrayList<ClassListItemsDateReasonInMonth> itemsDateReasonInMonthArrayList;
    private ArrayList<ClassListItemsDateReasonInMonth> itemsDateReasonInMonthArrayList_today;
    String date_in_dialog,dol;

    public CalendarFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        t1 = view.findViewById(R.id.id_day_of_month1);
        t2 = view.findViewById(R.id.id_day_of_month2);
        t3 = view.findViewById(R.id.id_day_of_month3);
        t4 = view.findViewById(R.id.id_day_of_month4);
        t5 = view.findViewById(R.id.id_day_of_month5);
        t6 = view.findViewById(R.id.id_day_of_month6);
        t7 = view.findViewById(R.id.id_day_of_month7);
        t8 = view.findViewById(R.id.id_day_of_month8);
        t9 = view.findViewById(R.id.id_day_of_month9);
        t10 = view.findViewById(R.id.id_day_of_month10);
        t11 = view.findViewById(R.id.id_day_of_month11);
        t12 = view.findViewById(R.id.id_day_of_month12);
        t13 = view.findViewById(R.id.id_day_of_month13);
        t14 = view.findViewById(R.id.id_day_of_month14);
        t15 = view.findViewById(R.id.id_day_of_month15);
        t16 = view.findViewById(R.id.id_day_of_month16);
        t17 = view.findViewById(R.id.id_day_of_month17);
        t18 = view.findViewById(R.id.id_day_of_month18);
        t19 = view.findViewById(R.id.id_day_of_month19);
        t20 = view.findViewById(R.id.id_day_of_month20);
        t21 = view.findViewById(R.id.id_day_of_month21);
        t22 = view.findViewById(R.id.id_day_of_month22);
        t23 = view.findViewById(R.id.id_day_of_month23);
        t24 = view.findViewById(R.id.id_day_of_month24);
        t25 = view.findViewById(R.id.id_day_of_month25);
        t26 = view.findViewById(R.id.id_day_of_month26);
        t27 = view.findViewById(R.id.id_day_of_month27);
        t28 = view.findViewById(R.id.id_day_of_month28);
        t29 = view.findViewById(R.id.id_day_of_month29);
        t30 = view.findViewById(R.id.id_day_of_month30);
        t31 = view.findViewById(R.id.id_day_of_month31);
        t32 = view.findViewById(R.id.id_day_of_month32);
        t33 = view.findViewById(R.id.id_day_of_month33);
        t34 = view.findViewById(R.id.id_day_of_month34);
        t35 = view.findViewById(R.id.id_day_of_month35);
        t36 = view.findViewById(R.id.id_day_of_month36);
        t37 = view.findViewById(R.id.id_day_of_month37);
        t38 = view.findViewById(R.id.id_day_of_month38);
        t39 = view.findViewById(R.id.id_day_of_month39);
        t40 = view.findViewById(R.id.id_day_of_month40);

        t_karan_show = view.findViewById(R.id.karan_show_fr);
        namename = view.findViewById(R.id.namename_fr);

        month_back = view.findViewById(R.id.id_month_to_back);
        month_next = view.findViewById(R.id.id_month_to_next);
        name_current_month = view.findViewById(R.id.name_current_month);

        set_gogo_all_month = view.findViewById(R.id.set_gogo_all_month);
        del_gogo_all_month = view.findViewById(R.id.del_gogo_all_month);

        connectionClass = new ConnectionClass();
        List<String> bolele = new ArrayList<String>();
        bolele.add(a);
        bolele.add(b);
        bolele.add(c);
        bolele.add(d);
        bolele.add(f);
        bolez = bolele.toArray(new String[bolele.size()]);
        List<String> bolele2 = new ArrayList<String>();
        bolele2.add(a1);
        bolele2.add(b1);
        bolez2 = bolele2.toArray(new String[bolele2.size()]);
        List<String> bolele3 = new ArrayList<String>();
        bolele3.add(a);
        bolele3.add(b);
        bolele3.add(c);
        bolele3.add(d);
        bolele3.add(f);
        bolele3.add(a1);
        bolele3.add(b1);
        bolez3 = bolele3.toArray(new String[bolele3.size()]);

        itemsDateReasonInMonthArrayList = new ArrayList<>();
        itemsDateReasonInMonthArrayList_today = new ArrayList<>();

        return view;


    }

    public void thiskarantin(TextView v){
        v.setTextColor(getResources().getColor(R.color.color_karan1));
        v.setTypeface(null, Typeface.BOLD);

    }

    private class SyncData_get_array_holidays extends AsyncTask<String, String, String> {

        String msg = "подключитесь к интернету!";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();

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
                        Log.d("calen", String.valueOf(stringList.size()));
                        //msg = "ЗАГРУЖЕНО";
                        success = true;
                    }else {
                        //msg = "НЕ ЗАГРУЖЕНО";
                        success = false;
                    }


                }

                conn.close();
                Log.d("calen","conn.close()");
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
                    Log.d("calen","yes progress !!!");

                    saveArrayList(stringList,"array_holidays");

                    int check_save_array_holidays = 1;
                    sPref = getActivity().getSharedPreferences("check_save_array_holidays",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putInt("check_save_array_holidays",check_save_array_holidays);
                    ed.apply();

                    c1 = Calendar.getInstance();
                    startMonth(c1);

//                    int u = verifi_date_today(year,month,4,stringList);
//                    Log.e("ccaall",u+" u_result");

                }catch (Exception ex){
                    Log.d("calen","not progress !!!");
                }
            }
        }
    }

    public boolean checking(Calendar c1){
        DateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd");
        if((stringList.contains(sdf_date.format(c1.getTime()))||c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY||c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
                &&!stringList_work.contains(sdf_date.format(c1.getTime()))){
            return true;
        }
        return false;
    }

    public void startMonth(Calendar c1){
        DateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd");
//        Date today = new Date();
//        c1.setTime(today);
        month = c1.get(Calendar.MONTH);
        year = c1.get(Calendar.YEAR);



        Log.d("ccaall","month_int "+month+" year_int "+year);
        name_current_month.setText(month_array[month]+" "+year);
        Integer month_int_back = null;
        Integer month_int_next = null;
        //11
        if (month-1==-1){
            month_int_back=11;
        }else {
            month_int_back=month-1;
        }
        month_back.setText(month_array[month_int_back]);
        if (month+1==12){
            month_int_next=0;
        }else {
            month_int_next=month+1;
        }
        month_next.setText(month_array[month_int_next]);

        c1.set(Calendar.DAY_OF_MONTH,1);
//        Log.d("calen","первый день текущего месяца: " + sdf.format(c1.getTime()));
        Integer day_of_week = null;
        switch (c1.get(Calendar.DAY_OF_WEEK)){
            case Calendar.SUNDAY://воскресение
                day_of_week = 7;
                break;
            case Calendar.SATURDAY://суббота
                day_of_week = 6;
                break;
            case Calendar.MONDAY://понедельник
                day_of_week = 1;
                break;
            case Calendar.TUESDAY://вторник
                day_of_week = 2;
                break;
            case Calendar.WEDNESDAY://среда
                day_of_week = 3;
                break;
            case Calendar.THURSDAY://четверг
                day_of_week = 4;
                break;
            case Calendar.FRIDAY://пятница
                day_of_week = 5;
                break;
        }

//        Log.d("calen","day_of_week "+day_of_week);
        c1.add(Calendar.MONTH, 1);
        c1.set(Calendar.DAY_OF_MONTH,1);
        c1.add(Calendar.DATE, -1);
        int last_day = c1.get(Calendar.DAY_OF_MONTH);
//        Toast.makeText(getActivity(), last_day+" last_day", Toast.LENGTH_SHORT).show();

        int a [] = new int [41];

        for(int i = 1;i <= last_day;i++){
            a[day_of_week] = i;
            day_of_week++;
        }

//        Log.v("ccaall","1");

        if(a[1]==0){
            t1.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[1]);
//            Log.v("ccaall",sdf_date.format(c1.getTime()));
            if(checking(c1)){
                this_day_is_holiday(t1);
            }else {
                this_day_is_not_holiday(t1);
            }
            t1.setText(String.valueOf(a[1]));
        }
        if(a[2]==0){
            t2.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[2]);
            if(checking(c1)){
                this_day_is_holiday(t2);
            }else {
                this_day_is_not_holiday(t2);
            }
            t2.setText(String.valueOf(a[2]));
        }
        if(a[3]==0){
            t3.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[3]);
            if(checking(c1)){
                this_day_is_holiday(t3);
            }else {
                this_day_is_not_holiday(t3);
            }
            t3.setText(String.valueOf(a[3]));
        }
        if(a[4]==0){
            t4.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[4]);
            if(checking(c1)){
                this_day_is_holiday(t4);
            }else {
                this_day_is_not_holiday(t4);
            }
            t4.setText(String.valueOf(a[4]));
        }
        if(a[5]==0){
            t5.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[5]);
            if(checking(c1)){
                this_day_is_holiday(t5);
            }else {
                this_day_is_not_holiday(t5);
            }
            t5.setText(String.valueOf(a[5]));
        }
        if(a[6]==0){
            t6.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[6]);
            if(checking(c1)){
                this_day_is_holiday(t6);
            }else {
                this_day_is_not_holiday(t6);
            }
            t6.setText(String.valueOf(a[6]));
        }
//        Log.v("ccaall","2");
        if(a[7]==0){
            t7.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[7]);
//            Log.v("ccaall",sdf_date.format(c1.getTime()));
//            Log.v("ccaall", String.valueOf(stringList.size()));
            if(checking(c1)){
                this_day_is_holiday(t7);
            }else {
                this_day_is_not_holiday(t7);
            }
            t7.setText(String.valueOf(a[7]));
        }
//        Log.v("ccaall","3");
        if(a[8]==0){
            t8.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[8]);
            if(checking(c1)){
                this_day_is_holiday(t8);
            }else {
                this_day_is_not_holiday(t8);
            }
            t8.setText(String.valueOf(a[8]));
        }
        if(a[9]==0){
            t9.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[9]);
            if(checking(c1)){
                this_day_is_holiday(t9);
            }else {
                this_day_is_not_holiday(t9);
            }
            t9.setText(String.valueOf(a[9]));
        }
        if(a[10]==0){
            t10.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[10]);
            if(checking(c1)){
                this_day_is_holiday(t10);
            }else {
                this_day_is_not_holiday(t10);
            }
            t10.setText(String.valueOf(a[10]));
        }
        if(a[11]==0){
            t11.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[11]);
            if(checking(c1)){
                this_day_is_holiday(t11);
            }else {
                this_day_is_not_holiday(t11);
            }
            t11.setText(String.valueOf(a[11]));
        }
        if(a[12]==0){
            t12.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[12]);
            if(checking(c1)){
                this_day_is_holiday(t12);
            }else {
                this_day_is_not_holiday(t12);
            }
            t12.setText(String.valueOf(a[12]));
        }
        if(a[13]==0){
            t13.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[13]);
            if(checking(c1)){
                this_day_is_holiday(t13);
            }else {
                this_day_is_not_holiday(t13);
            }
            t13.setText(String.valueOf(a[13]));
        }
        if(a[14]==0){
            t14.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[14]);
            if(checking(c1)){
                this_day_is_holiday(t14);
            }else {
                this_day_is_not_holiday(t14);
            }
            t14.setText(String.valueOf(a[14]));
        }
        if(a[15]==0){
            t15.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[15]);
            if(checking(c1)){
                this_day_is_holiday(t15);
            }else {
                this_day_is_not_holiday(t15);
            }
            t15.setText(String.valueOf(a[15]));
        }
        if(a[16]==0){
            t16.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[16]);
            if(checking(c1)){
                this_day_is_holiday(t16);
            }else {
                this_day_is_not_holiday(t16);
            }
            t16.setText(String.valueOf(a[16]));
        }
        if(a[17]==0){
            t17.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[17]);
            if(checking(c1)){
                this_day_is_holiday(t17);
            }else {
                this_day_is_not_holiday(t17);
            }
            t17.setText(String.valueOf(a[17]));
        }
        if(a[18]==0){
            t18.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[18]);
            if(checking(c1)){
                this_day_is_holiday(t18);
            }else {
                this_day_is_not_holiday(t18);
            }
            t18.setText(String.valueOf(a[18]));
        }
        if(a[19]==0){
            t19.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[19]);
            if(checking(c1)){
                this_day_is_holiday(t19);
            }else {
                this_day_is_not_holiday(t19);
            }
            t19.setText(String.valueOf(a[19]));
        }
        if(a[20]==0){
            t20.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[20]);
            if(checking(c1)){
                this_day_is_holiday(t20);
            }else {
                this_day_is_not_holiday(t20);
            }
            t20.setText(String.valueOf(a[20]));
        }
        if(a[21]==0){
            t21.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[21]);
            if(checking(c1)){
                this_day_is_holiday(t21);
            }else {
                this_day_is_not_holiday(t21);
            }
            t21.setText(String.valueOf(a[21]));
        }
        if(a[22]==0){
            t22.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[22]);
            if(checking(c1)){
                this_day_is_holiday(t22);
            }else {
                this_day_is_not_holiday(t22);
            }
            t22.setText(String.valueOf(a[22]));
        }
        if(a[23]==0){
            t23.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[23]);
            if(checking(c1)){
                this_day_is_holiday(t23);
            }else {
                this_day_is_not_holiday(t23);
            }
            t23.setText(String.valueOf(a[23]));
        }
        if(a[24]==0){
            t24.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[24]);
            if(checking(c1)){
                this_day_is_holiday(t24);
            }else {
                this_day_is_not_holiday(t24);
            }
            t24.setText(String.valueOf(a[24]));
        }
        if(a[25]==0){
            t25.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[25]);
            if(checking(c1)){
                this_day_is_holiday(t25);
            }else {
                this_day_is_not_holiday(t25);
            }
            t25.setText(String.valueOf(a[25]));
        }
        if(a[26]==0){
            t26.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[26]);
            if(checking(c1)){
                this_day_is_holiday(t26);
            }else {
                this_day_is_not_holiday(t26);
            }
            t26.setText(String.valueOf(a[26]));
        }
        if(a[27]==0){
            t27.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[27]);
            if(checking(c1)){
                this_day_is_holiday(t27);
            }else {
                this_day_is_not_holiday(t27);
            }
            t27.setText(String.valueOf(a[27]));
        }
        if(a[28]==0){
            t28.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[28]);
            if(checking(c1)){
                this_day_is_holiday(t28);
            }else {
                this_day_is_not_holiday(t28);
            }
            t28.setText(String.valueOf(a[28]));
        }
        if(a[29]==0){
            t29.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[29]);
            if(checking(c1)){
                this_day_is_holiday(t29);
            }else {
                this_day_is_not_holiday(t29);
            }
            t29.setText(String.valueOf(a[29]));
        }
        if(a[30]==0){
            t30.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[30]);
            if(checking(c1)){
                this_day_is_holiday(t30);
            }else {
                this_day_is_not_holiday(t30);
            }
            t30.setText(String.valueOf(a[30]));
        }
        if(a[31]==0){
            t31.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[31]);
            if(checking(c1)){
                this_day_is_holiday(t31);
            }else {
                this_day_is_not_holiday(t31);
            }
            t31.setText(String.valueOf(a[31]));
        }
        if(a[32]==0){
            t32.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[32]);
            if(checking(c1)){
                this_day_is_holiday(t32);
            }else {
                this_day_is_not_holiday(t32);
            }
            t32.setText(String.valueOf(a[32]));
        }
        if(a[33]==0){
            t33.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[33]);
            if(checking(c1)){
                this_day_is_holiday(t33);
            }else {
                this_day_is_not_holiday(t33);
            }
            t33.setText(String.valueOf(a[33]));
        }
        if(a[34]==0){
            t34.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[34]);
            if(checking(c1)){
                this_day_is_holiday(t34);
            }else {
                this_day_is_not_holiday(t34);
            }
            t34.setText(String.valueOf(a[34]));
        }
        if(a[35]==0){
            t35.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[35]);
            if(checking(c1)){
                this_day_is_holiday(t35);
            }else {
                this_day_is_not_holiday(t35);
            }
            t35.setText(String.valueOf(a[35]));
        }
        if(a[36]==0){
            t36.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[36]);
            if(checking(c1)){
                this_day_is_holiday(t36);
            }else {
                this_day_is_not_holiday(t36);
            }
            t36.setText(String.valueOf(a[36]));
        }
        if(a[37]==0){
            t37.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[37]);
            if(checking(c1)){
                this_day_is_holiday(t37);
            }else {
                this_day_is_not_holiday(t37);
            }
            t37.setText(String.valueOf(a[37]));
        }
        if(a[38]==0){
            t38.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[38]);
            if(checking(c1)){
                this_day_is_holiday(t38);
            }else {
                this_day_is_not_holiday(t38);
            }
            t38.setText(String.valueOf(a[38]));
        }
        if(a[39]==0){
            t39.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[39]);
            if(checking(c1)){
                this_day_is_holiday(t39);
            }else {
                this_day_is_not_holiday(t39);
            }
            t39.setText(String.valueOf(a[39]));
        }
        if(a[40]==0){
            t40.setText("");
        }else{
            c1.set(Calendar.DAY_OF_MONTH,a[40]);
            if(checking(c1)){
                this_day_is_holiday(t40);
            }else {
                this_day_is_not_holiday(t40);
            }
            t40.setText(String.valueOf(a[40]));
        }

        new SyncData().execute("");
    }

    public void this_day_is_holiday(TextView v){
        v.setTextColor(getResources().getColor(R.color.LightSalmon));
        v.setEnabled(false);
    }

    public void this_day_is_not_holiday(TextView v){
        v.setTextColor(getResources().getColor(R.color.black));
        v.setEnabled(true);
    }

    public void dialog_add(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выбрать ребенка :");
        builder.setItems(bolez, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

            }
        });
        AlertDialog alertDialogObject = builder.create();
        ListView listView=alertDialogObject.getListView();
        listView.setDivider(new ColorDrawable(Color.GRAY)); // set color
        listView.setDividerHeight(1); // set height
        alertDialogObject.setCancelable(true);
        alertDialogObject.show();
    }

    public void show_dialog (int id) {
        reason = "";
        switch (id) {
            case V_NORMAL:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Выберите причину (снять с питания) на "+date_in_dialog);
                builder.setItems(bolez, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item==0){reason=a;}
                        if (item==1){reason=b;}
                        if (item==2){reason=c;}
                        if (item==3){reason=d;}
                        if (item==4){reason=f;}
                        new SyncData_snyat().execute();
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialogObject = builder.create();
                ListView listView=alertDialogObject.getListView();
                listView.setDivider(new ColorDrawable(Color.GRAY)); // set color
                listView.setDividerHeight(1); // set height
                alertDialogObject.setCancelable(true);
                alertDialogObject.show();
                break;
            case V_TODAY:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("Выберите оповещение (не снимает с питания) на "+date_in_dialog);
                builder1.setItems(bolez2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item==0){reason=a1;}
                        if (item==1){reason=b1;}
                        new SyncData_today().execute();
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialogObject1 = builder1.create();
                ListView listView1=alertDialogObject1.getListView();
                listView1.setDivider(new ColorDrawable(Color.GRAY)); // set color
                listView1.setDividerHeight(1); // set height
                alertDialogObject1.setCancelable(true);
                alertDialogObject1.show();
                break;
            case MED:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle("Выберите причину на "+date_in_dialog);
                builder2.setItems(bolez3, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item==0){reason=a;new SyncData_snyat().execute();}
                        if (item==1){reason=b;new SyncData_snyat().execute();}
                        if (item==2){reason=c;new SyncData_snyat().execute();}
                        if (item==3){reason=d;new SyncData_snyat().execute();}
                        if (item==4){reason=f;new SyncData_snyat().execute();}
                        if (item==5){reason=a1;new SyncData_today().execute();}
                        if (item==6){reason=b1;new SyncData_today().execute();}
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialogObject2 = builder2.create();
                ListView listView2=alertDialogObject2.getListView();
                listView2.setDivider(new ColorDrawable(Color.GRAY)); // set color
                listView2.setDividerHeight(1); // set height
                alertDialogObject2.setCancelable(true);
                alertDialogObject2.show();
                break;
        }
    }

    private class SyncData_snyat_old extends AsyncTask<String, String, String> {

        String msg,query_np = "Error !";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn_np = connectionClass.CONN(login);
                if (conn_np == null){
                    success = false;
                }
                else {
                    if (so==777){
                        query_np = "insert into gogoS (id,dateS) values ('"+idso+"','"+date_to_db+"');";
                    }else {
//                        query_np = "insert into gogo (id_child,id_gruppa,datenotgo,prichina,surprise) values ('"+id+"','"+id_gruppa+"','"+date_to_db+"','"+reason+"','');";
                        //Insert_Delete_And_History_So @check int, @id_child int, @id_gruppa int, @datenotgo date, @reason varchar(max), @id_reason int, @id_x int as
                        query_np = "exec Insert_Delete_And_History_So 1,"+id+","+id_gruppa+",'"+date_to_db+"','"+reason+"',0,"+idso_x+"";
                    }
                    Log.e("tttrrr", query_np);
                    Statement stmt_np = conn_np.createStatement();
                    stmt_np.execute(query_np);
                }
                conn_np.close();
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
            new SyncData().execute();
        }
    }

    private class SyncData_snyat extends AsyncTask<String, String, String> {

        String msg,query_np,query5,query8,rost_child,ves_child = "";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();
            data.clear();
            data_today.clear();
            data_karan.clear();
            data_karan2.clear();
            itemsDateReasonInMonthArrayList.clear();
            itemsDateReasonInMonthArrayList_today.clear();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn_np = connectionClass.CONN(login);
                if (conn_np == null){
                    success = false;
                }
                else {
                    if (so==777){
                        query_np = "begin tran insert into gogoS (id,dateS) values ('"+idso+"','"+date_to_db+"'); exec Table_number_one_calendar_so "+year+","+(month+1)+",'"+idso+"'; commit tran";
                    }else {
                        query_np = "begin tran exec Insert_Delete_And_History_So 1,"+id+","+id_gruppa+",'"+date_to_db+"','"+reason+"',0,"+idso_x+"; exec Table_number_one_calendar "+year+","+(month+1)+",'"+id_gruppa+"','"+id+"'; commit tran";
                    }
                    Log.e("tttrrr", query_np);
                    Statement stmt_np = conn_np.createStatement();
                    ResultSet rs = stmt_np.executeQuery(query_np);

                    if (rs != null){
                        while (rs.next()){
                            try {
                                if (rs.getString("date_krestik")!=null){
                                    data.add(rs.getInt("date_krestik"));
//                                    Log.e("tttrrr", rs.getInt("date_krestik")+" rs.getInt(date_krestik)");
                                }

                                if (rs.getString("date_today")!=null){
                                    data_today.add(rs.getInt("date_today"));
//                                    Log.e("tttrrr", rs.getInt("date_today")+" rs.getInt(date_today)");
                                }

                                if (so!=777){
                                    if (rs.getString("dataka1")!=null){
                                        data_karan.add(rs.getInt("dataka1"));
//                                    Log.e("tttrrr", rs.getInt("dataka1")+" rs.getInt(dataka1)");
                                    }

                                    if (rs.getString("dataka2")!=null){
                                        data_karan2.add(rs.getInt("dataka2"));
//                                    Log.e("tttrrr", rs.getInt("dataka2")+" rs.getInt(dataka2)");
                                    }

                                    if (rs.getString("id")!=null){
                                        nameClick = rs.getString("nam").trim();
//                                    Log.e("tttrrr", nameClick+" nameClick");
                                        numClick = rs.getString("num").trim();
//                                    Log.e("tttrrr", numClick+" numClick");
                                    }

                                    if (rs.getString("id_child")!=null){
                                        rost_child = rs.getString("rost").trim();
//                                    Log.e("tttrrr", rost_child+" rost_child");
                                        ves_child = rs.getString("ves").trim();
//                                    Log.e("tttrrr", ves_child+" ves_child");
                                    }

                                    if (rs.getString("date_krestik2")!=null){
                                        itemsDateReasonInMonthArrayList.add(new ClassListItemsDateReasonInMonth(rs.getString("date_krestik2").trim(),rs.getString("p1").trim()));
                                    }
                                    if (rs.getString("date_today2")!=null){
                                        itemsDateReasonInMonthArrayList_today.add(new ClassListItemsDateReasonInMonth(rs.getString("date_today2").trim(),rs.getString("t1").trim()));
                                    }
                                }



//                                Log.i("tttrrr",so+" so");
                                if (so==777){
                                    if (rs.getString("dol")!=null){
                                        dol = rs.getString("dol").trim();
//                                        Log.i("tttrrr",dol);
                                    }
                                    if (rs.getString("num")!=null){
                                        numClick = rs.getString("num").trim();
//                                    Log.e("tttrrr", numClick+" numClick");
                                    }
                                }


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




                }
                conn_np.close();
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
            if (success == false){
                Log.e("MSSQL", "success == false");
                Toast.makeText(getActivity(), "Отсутствует соединение с интернетом!", Toast.LENGTH_LONG).show();
            }else {
                try {
                    if (so!=777){
                        sPref = getActivity().getSharedPreferences("rost",Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed2 = sPref.edit();
                        ed2.putString("rost",rost_child);
                        ed2.apply();

                        sPref = getActivity().getSharedPreferences("ves",Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString("ves",ves_child);
                        ed.apply();
                    }

                    if (check_interpolar == 1){
                        inflate_calendar();
                        check_interpolar = 0;
                    }

                    if (data_karan.size()>0||data_karan2.size()>0){
                        t_karan_show.setVisibility(View.VISIBLE);
                    }else {
                        t_karan_show.setVisibility(View.GONE);
                    }


                    inflate_day(t1);
                    inflate_day(t2);
                    inflate_day(t3);
                    inflate_day(t4);
                    inflate_day(t5);
                    inflate_day(t6);
                    inflate_day(t7);
                    inflate_day(t8);
                    inflate_day(t9);
                    inflate_day(t10);
                    inflate_day(t11);
                    inflate_day(t12);
                    inflate_day(t13);
                    inflate_day(t14);
                    inflate_day(t15);
                    inflate_day(t16);
                    inflate_day(t17);
                    inflate_day(t18);
                    inflate_day(t19);
                    inflate_day(t20);
                    inflate_day(t21);
                    inflate_day(t22);
                    inflate_day(t23);
                    inflate_day(t24);
                    inflate_day(t25);
                    inflate_day(t26);
                    inflate_day(t27);
                    inflate_day(t28);
                    inflate_day(t29);
                    inflate_day(t30);
                    inflate_day(t31);
                    inflate_day(t32);
                    inflate_day(t33);
                    inflate_day(t34);
                    inflate_day(t35);
                    inflate_day(t36);
                    inflate_day(t37);
                    inflate_day(t38);
                    inflate_day(t39);
                    inflate_day(t40);

                }catch (Exception ex){

                }
            }
//            new SyncData().execute();
        }
    }

    private class SyncData_today extends AsyncTask<String, String, String> {

        String msg,query_np,query5,query8,rost_child,ves_child = "";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();
            data.clear();
            data_today.clear();
            data_karan.clear();
            data_karan2.clear();
            itemsDateReasonInMonthArrayList.clear();
            itemsDateReasonInMonthArrayList_today.clear();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn_np = connectionClass.CONN(login);
                if (conn_np == null){
                    success = false;
                }
                else {
                    if (so==777){
                        query_np = "begin tran insert into gogoS (id,dateS,propuskS) values ('"+idso+"','"+date_to_db+"','не приду'); exec Table_number_one_calendar_so "+year+","+(month+1)+",'"+idso+"'; commit tran";
                    }else {
                        query_np = "begin tran exec Insert_Delete_And_History_So 2,"+id+","+id_gruppa+",'"+date_to_db+"','"+reason+"',0,"+idso_x+"; exec Table_number_one_calendar "+year+","+(month+1)+",'"+id_gruppa+"','"+id+"'; commit tran";
                    }
                    Log.e("tttrrr", query_np);
                    Statement stmt_np = conn_np.createStatement();
                    ResultSet rs = stmt_np.executeQuery(query_np);

                    if (rs != null){
                        while (rs.next()){
                            try {
                                if (rs.getString("date_krestik")!=null){
                                    data.add(rs.getInt("date_krestik"));
//                                    Log.e("tttrrr", rs.getInt("date_krestik")+" rs.getInt(date_krestik)");
                                }

                                if (rs.getString("date_today")!=null){
                                    data_today.add(rs.getInt("date_today"));
//                                    Log.e("tttrrr", rs.getInt("date_today")+" rs.getInt(date_today)");
                                }

                                if (so!=777){
                                    if (rs.getString("dataka1")!=null){
                                        data_karan.add(rs.getInt("dataka1"));
//                                    Log.e("tttrrr", rs.getInt("dataka1")+" rs.getInt(dataka1)");
                                    }

                                    if (rs.getString("dataka2")!=null){
                                        data_karan2.add(rs.getInt("dataka2"));
//                                    Log.e("tttrrr", rs.getInt("dataka2")+" rs.getInt(dataka2)");
                                    }

                                    if (rs.getString("id")!=null){
                                        nameClick = rs.getString("nam").trim();
//                                    Log.e("tttrrr", nameClick+" nameClick");
                                        numClick = rs.getString("num").trim();
//                                    Log.e("tttrrr", numClick+" numClick");
                                    }

                                    if (rs.getString("id_child")!=null){
                                        rost_child = rs.getString("rost").trim();
//                                    Log.e("tttrrr", rost_child+" rost_child");
                                        ves_child = rs.getString("ves").trim();
//                                    Log.e("tttrrr", ves_child+" ves_child");
                                    }

                                    if (rs.getString("date_krestik2")!=null){
                                        itemsDateReasonInMonthArrayList.add(new ClassListItemsDateReasonInMonth(rs.getString("date_krestik2").trim(),rs.getString("p1").trim()));
                                    }
                                    if (rs.getString("date_today2")!=null){
                                        itemsDateReasonInMonthArrayList_today.add(new ClassListItemsDateReasonInMonth(rs.getString("date_today2").trim(),rs.getString("t1").trim()));
                                    }
                                }



//                                Log.i("tttrrr",so+" so");
                                if (so==777){
                                    if (rs.getString("dol")!=null){
                                        dol = rs.getString("dol").trim();
//                                        Log.i("tttrrr",dol);
                                    }
                                    if (rs.getString("num")!=null){
                                        numClick = rs.getString("num").trim();
//                                    Log.e("tttrrr", numClick+" numClick");
                                    }
                                }


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




                }
                conn_np.close();
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
            if (success == false){
                Log.e("MSSQL", "success == false");
                Toast.makeText(getActivity(), "Отсутствует соединение с интернетом!", Toast.LENGTH_LONG).show();
            }else {
                try {
                    if (so!=777){
                        sPref = getActivity().getSharedPreferences("rost",Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed2 = sPref.edit();
                        ed2.putString("rost",rost_child);
                        ed2.apply();

                        sPref = getActivity().getSharedPreferences("ves",Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString("ves",ves_child);
                        ed.apply();
                    }

                    if (check_interpolar == 1){
                        inflate_calendar();
                        check_interpolar = 0;
                    }

                    if (data_karan.size()>0||data_karan2.size()>0){
                        t_karan_show.setVisibility(View.VISIBLE);
                    }else {
                        t_karan_show.setVisibility(View.GONE);
                    }


                    inflate_day(t1);
                    inflate_day(t2);
                    inflate_day(t3);
                    inflate_day(t4);
                    inflate_day(t5);
                    inflate_day(t6);
                    inflate_day(t7);
                    inflate_day(t8);
                    inflate_day(t9);
                    inflate_day(t10);
                    inflate_day(t11);
                    inflate_day(t12);
                    inflate_day(t13);
                    inflate_day(t14);
                    inflate_day(t15);
                    inflate_day(t16);
                    inflate_day(t17);
                    inflate_day(t18);
                    inflate_day(t19);
                    inflate_day(t20);
                    inflate_day(t21);
                    inflate_day(t22);
                    inflate_day(t23);
                    inflate_day(t24);
                    inflate_day(t25);
                    inflate_day(t26);
                    inflate_day(t27);
                    inflate_day(t28);
                    inflate_day(t29);
                    inflate_day(t30);
                    inflate_day(t31);
                    inflate_day(t32);
                    inflate_day(t33);
                    inflate_day(t34);
                    inflate_day(t35);
                    inflate_day(t36);
                    inflate_day(t37);
                    inflate_day(t38);
                    inflate_day(t39);
                    inflate_day(t40);

                }catch (Exception ex){

                }
            }
//            new SyncData().execute();
        }
    }

    private class SyncData_today_old extends AsyncTask<String, String, String> {

        String msg,query_np = "Error !";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn_np = connectionClass.CONN(login);
                if (conn_np == null){
                    success = false;
                }
                else {
                    if (so==777){
                        query_np = "insert into gogoS (id,dateS,propuskS) values ('"+idso+"','"+date_to_db+"','не приду')";
                    }else {
//                        query_np = "insert into gogo (id_child,id_gruppa,datenotgo,today,surprise) values ('"+id+"','"+id_gruppa+"','"+date_to_db+"','"+reason+"','');";
//                        query_np = "exec Insert_Delete_And_History_Deti 2,"+id+","+id_gruppa+",'"+date_to_db+"','"+reason+"'";

                        //Insert_Delete_And_History_So @check int, @id_child int, @id_gruppa int, @datenotgo date, @reason varchar(max), @id_reason int, @id_x int as
                        query_np = "exec Insert_Delete_And_History_So 2,"+id+","+id_gruppa+",'"+date_to_db+"','"+reason+"',0,"+idso_x+"";
                    }

                    Log.e("tttrrr", query_np);
                    Statement stmt_np = conn_np.createStatement();
                    stmt_np.execute(query_np);
                }
                conn_np.close();
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
            new SyncData().execute();
        }
    }

    private class SyncData_del extends AsyncTask<String, String, String> {

        String msg,query_np,query5,query8,rost_child,ves_child = "";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();
            data.clear();
            data_today.clear();
            data_karan.clear();
            data_karan2.clear();
            itemsDateReasonInMonthArrayList.clear();
            itemsDateReasonInMonthArrayList_today.clear();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn_np = connectionClass.CONN(login);
                if (conn_np == null){
                    success = false;
                }
                else {
                    if (so==777){
                        query_np = "begin tran delete gogoS where dateS='"+date_to_db+"' and id='"+idso+"'; exec Table_number_one_calendar_so "+year+","+(month+1)+",'"+idso+"'; commit tran";
                    }else {
                        query_np = "begin tran exec Insert_Delete_And_History_So 3,"+id+",0,'"+date_to_db+"','',0,"+idso_x+"; exec Table_number_one_calendar "+year+","+(month+1)+",'"+id_gruppa+"','"+id+"'; commit tran";
                    }
                    Log.e("tttrrr", query_np);
                    Statement stmt_np = conn_np.createStatement();
                    ResultSet rs = stmt_np.executeQuery(query_np);

                    if (rs != null){
                        while (rs.next()){
                            try {
                                if (rs.getString("date_krestik")!=null){
                                    data.add(rs.getInt("date_krestik"));
//                                    Log.e("tttrrr", rs.getInt("date_krestik")+" rs.getInt(date_krestik)");
                                }

                                if (rs.getString("date_today")!=null){
                                    data_today.add(rs.getInt("date_today"));
//                                    Log.e("tttrrr", rs.getInt("date_today")+" rs.getInt(date_today)");
                                }

                                if (so!=777){
                                    if (rs.getString("dataka1")!=null){
                                        data_karan.add(rs.getInt("dataka1"));
//                                    Log.e("tttrrr", rs.getInt("dataka1")+" rs.getInt(dataka1)");
                                    }

                                    if (rs.getString("dataka2")!=null){
                                        data_karan2.add(rs.getInt("dataka2"));
//                                    Log.e("tttrrr", rs.getInt("dataka2")+" rs.getInt(dataka2)");
                                    }

                                    if (rs.getString("id")!=null){
                                        nameClick = rs.getString("nam").trim();
//                                    Log.e("tttrrr", nameClick+" nameClick");
                                        numClick = rs.getString("num").trim();
//                                    Log.e("tttrrr", numClick+" numClick");
                                    }

                                    if (rs.getString("id_child")!=null){
                                        rost_child = rs.getString("rost").trim();
//                                    Log.e("tttrrr", rost_child+" rost_child");
                                        ves_child = rs.getString("ves").trim();
//                                    Log.e("tttrrr", ves_child+" ves_child");
                                    }

                                    if (rs.getString("date_krestik2")!=null){
                                        itemsDateReasonInMonthArrayList.add(new ClassListItemsDateReasonInMonth(rs.getString("date_krestik2").trim(),rs.getString("p1").trim()));
                                    }
                                    if (rs.getString("date_today2")!=null){
                                        itemsDateReasonInMonthArrayList_today.add(new ClassListItemsDateReasonInMonth(rs.getString("date_today2").trim(),rs.getString("t1").trim()));
                                    }
                                }



//                                Log.i("tttrrr",so+" so");
                                if (so==777){
                                    if (rs.getString("dol")!=null){
                                        dol = rs.getString("dol").trim();
//                                        Log.i("tttrrr",dol);
                                    }
                                    if (rs.getString("num")!=null){
                                        numClick = rs.getString("num").trim();
//                                    Log.e("tttrrr", numClick+" numClick");
                                    }
                                }


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




                }
                conn_np.close();
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
            if (success == false){
                Log.e("MSSQL", "success == false");
                Toast.makeText(getActivity(), "Отсутствует соединение с интернетом!", Toast.LENGTH_LONG).show();
            }else {
                try {
                    if (so!=777){
                        sPref = getActivity().getSharedPreferences("rost",Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed2 = sPref.edit();
                        ed2.putString("rost",rost_child);
                        ed2.apply();

                        sPref = getActivity().getSharedPreferences("ves",Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString("ves",ves_child);
                        ed.apply();
                    }

                    if (check_interpolar == 1){
                        inflate_calendar();
                        check_interpolar = 0;
                    }

                    if (data_karan.size()>0||data_karan2.size()>0){
                        t_karan_show.setVisibility(View.VISIBLE);
                    }else {
                        t_karan_show.setVisibility(View.GONE);
                    }


                    inflate_day(t1);
                    inflate_day(t2);
                    inflate_day(t3);
                    inflate_day(t4);
                    inflate_day(t5);
                    inflate_day(t6);
                    inflate_day(t7);
                    inflate_day(t8);
                    inflate_day(t9);
                    inflate_day(t10);
                    inflate_day(t11);
                    inflate_day(t12);
                    inflate_day(t13);
                    inflate_day(t14);
                    inflate_day(t15);
                    inflate_day(t16);
                    inflate_day(t17);
                    inflate_day(t18);
                    inflate_day(t19);
                    inflate_day(t20);
                    inflate_day(t21);
                    inflate_day(t22);
                    inflate_day(t23);
                    inflate_day(t24);
                    inflate_day(t25);
                    inflate_day(t26);
                    inflate_day(t27);
                    inflate_day(t28);
                    inflate_day(t29);
                    inflate_day(t30);
                    inflate_day(t31);
                    inflate_day(t32);
                    inflate_day(t33);
                    inflate_day(t34);
                    inflate_day(t35);
                    inflate_day(t36);
                    inflate_day(t37);
                    inflate_day(t38);
                    inflate_day(t39);
                    inflate_day(t40);

                }catch (Exception ex){

                }
            }
//            new SyncData().execute();
        }
    }

    private class SyncData_del_old extends AsyncTask<String, String, String> {

        String msg,query_np = "";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn_np = connectionClass.CONN(login);
                if (conn_np == null){
                    success = false;
                }
                else {
                    if (so==777){
                        query_np = "delete gogoS where dateS='"+date_to_db+"' and id='"+idso+"';";
                    }else {
//                        query_np = "delete gogo where datenotgo='"+date_to_db+"' and id_child='"+id+"';";
//                        query_np = "exec Insert_Delete_And_History_Deti 3,"+id+",0,'"+date_to_db+"',''";

                        //Insert_Delete_And_History_So @check int, @id_child int, @id_gruppa int, @datenotgo date, @reason varchar(max), @id_reason int, @id_x int as
                        query_np = "exec Insert_Delete_And_History_So 3,"+id+",0,'"+date_to_db+"','',0,"+idso_x+"";
                    }
                    Log.e("tttrrr", query_np);
                    Statement stmt_np = conn_np.createStatement();
                    stmt_np.execute(query_np);
                }
                conn_np.close();
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
            new SyncData().execute();
        }
    }

    private class SyncData_del_today extends AsyncTask<String, String, String> {

        String msg,query_np,query5,query8,rost_child,ves_child = "";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();
            data.clear();
            data_today.clear();
            data_karan.clear();
            data_karan2.clear();
            itemsDateReasonInMonthArrayList.clear();
            itemsDateReasonInMonthArrayList_today.clear();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn_np = connectionClass.CONN(login);
                if (conn_np == null){
                    success = false;
                }
                else {
                    if (so==777){
                        query_np = "begin tran delete gogoS where dateS='"+date_to_db+"' and id='"+idso+"'; exec Table_number_one_calendar_so "+year+","+(month+1)+",'"+idso+"'; commit tran";
                    }else {
                        query_np = "begin tran exec Insert_Delete_And_History_So 4,"+id+",0,'"+date_to_db+"','',0,"+idso_x+"; exec Table_number_one_calendar "+year+","+(month+1)+",'"+id_gruppa+"','"+id+"'; commit tran";
                    }
                    Log.e("tttrrr", query_np);
                    Statement stmt_np = conn_np.createStatement();
                    ResultSet rs = stmt_np.executeQuery(query_np);

                    if (rs != null){
                        while (rs.next()){
                            try {
                                if (rs.getString("date_krestik")!=null){
                                    data.add(rs.getInt("date_krestik"));
//                                    Log.e("tttrrr", rs.getInt("date_krestik")+" rs.getInt(date_krestik)");
                                }

                                if (rs.getString("date_today")!=null){
                                    data_today.add(rs.getInt("date_today"));
//                                    Log.e("tttrrr", rs.getInt("date_today")+" rs.getInt(date_today)");
                                }

                                if (so!=777){
                                    if (rs.getString("dataka1")!=null){
                                        data_karan.add(rs.getInt("dataka1"));
//                                    Log.e("tttrrr", rs.getInt("dataka1")+" rs.getInt(dataka1)");
                                    }

                                    if (rs.getString("dataka2")!=null){
                                        data_karan2.add(rs.getInt("dataka2"));
//                                    Log.e("tttrrr", rs.getInt("dataka2")+" rs.getInt(dataka2)");
                                    }

                                    if (rs.getString("id")!=null){
                                        nameClick = rs.getString("nam").trim();
//                                    Log.e("tttrrr", nameClick+" nameClick");
                                        numClick = rs.getString("num").trim();
//                                    Log.e("tttrrr", numClick+" numClick");
                                    }

                                    if (rs.getString("id_child")!=null){
                                        rost_child = rs.getString("rost").trim();
//                                    Log.e("tttrrr", rost_child+" rost_child");
                                        ves_child = rs.getString("ves").trim();
//                                    Log.e("tttrrr", ves_child+" ves_child");
                                    }

                                    if (rs.getString("date_krestik2")!=null){
                                        itemsDateReasonInMonthArrayList.add(new ClassListItemsDateReasonInMonth(rs.getString("date_krestik2").trim(),rs.getString("p1").trim()));
                                    }
                                    if (rs.getString("date_today2")!=null){
                                        itemsDateReasonInMonthArrayList_today.add(new ClassListItemsDateReasonInMonth(rs.getString("date_today2").trim(),rs.getString("t1").trim()));
                                    }
                                }



//                                Log.i("tttrrr",so+" so");
                                if (so==777){
                                    if (rs.getString("dol")!=null){
                                        dol = rs.getString("dol").trim();
//                                        Log.i("tttrrr",dol);
                                    }
                                    if (rs.getString("num")!=null){
                                        numClick = rs.getString("num").trim();
//                                    Log.e("tttrrr", numClick+" numClick");
                                    }
                                }


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




                }
                conn_np.close();
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
            if (success == false){
                Log.e("MSSQL", "success == false");
                Toast.makeText(getActivity(), "Отсутствует соединение с интернетом!", Toast.LENGTH_LONG).show();
            }else {
                try {
                    if (so!=777){
                        sPref = getActivity().getSharedPreferences("rost",Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed2 = sPref.edit();
                        ed2.putString("rost",rost_child);
                        ed2.apply();

                        sPref = getActivity().getSharedPreferences("ves",Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString("ves",ves_child);
                        ed.apply();
                    }

                    if (check_interpolar == 1){
                        inflate_calendar();
                        check_interpolar = 0;
                    }

                    if (data_karan.size()>0||data_karan2.size()>0){
                        t_karan_show.setVisibility(View.VISIBLE);
                    }else {
                        t_karan_show.setVisibility(View.GONE);
                    }


                    inflate_day(t1);
                    inflate_day(t2);
                    inflate_day(t3);
                    inflate_day(t4);
                    inflate_day(t5);
                    inflate_day(t6);
                    inflate_day(t7);
                    inflate_day(t8);
                    inflate_day(t9);
                    inflate_day(t10);
                    inflate_day(t11);
                    inflate_day(t12);
                    inflate_day(t13);
                    inflate_day(t14);
                    inflate_day(t15);
                    inflate_day(t16);
                    inflate_day(t17);
                    inflate_day(t18);
                    inflate_day(t19);
                    inflate_day(t20);
                    inflate_day(t21);
                    inflate_day(t22);
                    inflate_day(t23);
                    inflate_day(t24);
                    inflate_day(t25);
                    inflate_day(t26);
                    inflate_day(t27);
                    inflate_day(t28);
                    inflate_day(t29);
                    inflate_day(t30);
                    inflate_day(t31);
                    inflate_day(t32);
                    inflate_day(t33);
                    inflate_day(t34);
                    inflate_day(t35);
                    inflate_day(t36);
                    inflate_day(t37);
                    inflate_day(t38);
                    inflate_day(t39);
                    inflate_day(t40);

                }catch (Exception ex){

                }
            }
//            new SyncData().execute();
        }
    }

    private class SyncData_del_today_old extends AsyncTask<String, String, String> {

        String msg,query_np = "";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn_np = connectionClass.CONN(login);
                if (conn_np == null){
                    success = false;
                }
                else {
                    if (so==777){
                        query_np = "delete gogoS where dateS='"+date_to_db+"' and id='"+idso+"';";
                    }else {
//                        query_np = "delete gogo where datenotgo='"+date_to_db+"' and id_child='"+id+"' and id in (100,200)";
//                        query_np = "exec Insert_Delete_And_History_Deti 4,"+id+",0,'"+date_to_db+"',''";

                        //Insert_Delete_And_History_So @check int, @id_child int, @id_gruppa int, @datenotgo date, @reason varchar(max), @id_reason int, @id_x int as
                        query_np = "exec Insert_Delete_And_History_So 4,"+id+",0,'"+date_to_db+"','',0,"+idso_x+"";
                    }
                    Log.e("tttrrr", query_np);
                    Statement stmt_np = conn_np.createStatement();
                    stmt_np.execute(query_np);
                }
                conn_np.close();
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
            new SyncData().execute();
        }
    }

    private class SyncData extends AsyncTask<String, String, String> {

        String msg,query,query5,query8,rost_child,ves_child = "";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();
            data.clear();
            data_today.clear();
            data_karan.clear();
            data_karan2.clear();
            itemsDateReasonInMonthArrayList.clear();
            itemsDateReasonInMonthArrayList_today.clear();
//            clear_calendar();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
                    Log.e("MSSQL", "success");
                }
                else {
                    if (so==777){
                        query = "exec Table_number_one_calendar_so "+year+","+(month+1)+",'"+idso+"'";
                    }else {
                        query = "exec Table_number_one_calendar "+year+","+(month+1)+",'"+id_gruppa+"','"+id+"'";
                    }

                    Log.e("tttrrr", query);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    if (rs != null){
                        while (rs.next()){
                            try {
                                if (rs.getString("date_krestik")!=null){
                                    data.add(rs.getInt("date_krestik"));
//                                    Log.e("tttrrr", rs.getInt("date_krestik")+" rs.getInt(date_krestik)");
                                }

                                if (rs.getString("date_today")!=null){
                                    data_today.add(rs.getInt("date_today"));
//                                    Log.e("tttrrr", rs.getInt("date_today")+" rs.getInt(date_today)");
                                }

                                if (so!=777){
                                    if (rs.getString("dataka1")!=null){
                                        data_karan.add(rs.getInt("dataka1"));
//                                    Log.e("tttrrr", rs.getInt("dataka1")+" rs.getInt(dataka1)");
                                    }

                                    if (rs.getString("dataka2")!=null){
                                        data_karan2.add(rs.getInt("dataka2"));
//                                    Log.e("tttrrr", rs.getInt("dataka2")+" rs.getInt(dataka2)");
                                    }

                                    if (rs.getString("id")!=null){
                                        nameClick = rs.getString("nam").trim();
//                                    Log.e("tttrrr", nameClick+" nameClick");
                                        numClick = rs.getString("num").trim();
//                                    Log.e("tttrrr", numClick+" numClick");
                                    }

                                    if (rs.getString("id_child")!=null){
                                        rost_child = rs.getString("rost").trim();
//                                    Log.e("tttrrr", rost_child+" rost_child");
                                        ves_child = rs.getString("ves").trim();
//                                    Log.e("tttrrr", ves_child+" ves_child");
                                    }

                                    if (rs.getString("date_krestik2")!=null){
                                        itemsDateReasonInMonthArrayList.add(new ClassListItemsDateReasonInMonth(rs.getString("date_krestik2").trim(),rs.getString("p1").trim()));
                                    }
                                    if (rs.getString("date_today2")!=null){
                                        itemsDateReasonInMonthArrayList_today.add(new ClassListItemsDateReasonInMonth(rs.getString("date_today2").trim(),rs.getString("t1").trim()));
                                    }
                                }



//                                Log.i("tttrrr",so+" so");
                                if (so==777){
                                    if (rs.getString("dol")!=null){
                                        dol = rs.getString("dol").trim();
//                                        Log.i("tttrrr",dol);
                                    }
                                    if (rs.getString("num")!=null){
                                        numClick = rs.getString("num").trim();
//                                    Log.e("tttrrr", numClick+" numClick");
                                    }
                                }


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


                }
                conn.close();
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
            Log.e("MSSQL", "onPostExecute");
            progress.dismiss();
            if (success == false){
                Log.e("MSSQL", "success == false");
            }else {
                try {

//                    Log.w("tttrrr",itemsDateReasonInMonthArrayList.size()+" itemsDateReasonInMonthArrayList_today");
//                    Log.w("tttrrr",itemsDateReasonInMonthArrayList_today.size()+" itemsDateReasonInMonthArrayList_today");

                    /*if (so==777){
                        sPref = getSharedPreferences("number",MODE_PRIVATE);
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString("number",numClick);
                        ed.apply();
                    }else {
                        sPref = getSharedPreferences("item_gruppa",MODE_PRIVATE);
                        SharedPreferences.Editor ed2 = sPref.edit();
                        ed2.putString("item_gruppa",nameClick);
                        ed2.apply();

                        sPref = getSharedPreferences("number",MODE_PRIVATE);
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString("number",numClick);
                        ed.apply();

                        try {
                            if (rost_child!=null && ves_child!=null){
                                rost_menu.setText("Рост:  "+rost_child+" см.");
                                ves_menu.setText("Вес:  "+ves_child+" кг.");
                            }else {
                                rost_menu.setText(null);
                                ves_menu.setText(null);
                            }

                        }catch (Exception e){
                            Log.i("exeption2019",e.toString());
                        }
                    }*/

                    if (so!=777){
                        sPref = getActivity().getSharedPreferences("rost",Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed2 = sPref.edit();
                        ed2.putString("rost",rost_child);
                        ed2.apply();

                        sPref = getActivity().getSharedPreferences("ves",Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString("ves",ves_child);
                        ed.apply();
                    }

                    if (check_interpolar == 1){
                        inflate_calendar();
                        check_interpolar = 0;
                    }

                    if (data_karan.size()>0||data_karan2.size()>0){
                        t_karan_show.setVisibility(View.VISIBLE);
                    }else {
                        t_karan_show.setVisibility(View.GONE);
                    }


                    inflate_day(t1);
                    inflate_day(t2);
                    inflate_day(t3);
                    inflate_day(t4);
                    inflate_day(t5);
                    inflate_day(t6);
                    inflate_day(t7);
                    inflate_day(t8);
                    inflate_day(t9);
                    inflate_day(t10);
                    inflate_day(t11);
                    inflate_day(t12);
                    inflate_day(t13);
                    inflate_day(t14);
                    inflate_day(t15);
                    inflate_day(t16);
                    inflate_day(t17);
                    inflate_day(t18);
                    inflate_day(t19);
                    inflate_day(t20);
                    inflate_day(t21);
                    inflate_day(t22);
                    inflate_day(t23);
                    inflate_day(t24);
                    inflate_day(t25);
                    inflate_day(t26);
                    inflate_day(t27);
                    inflate_day(t28);
                    inflate_day(t29);
                    inflate_day(t30);
                    inflate_day(t31);
                    inflate_day(t32);
                    inflate_day(t33);
                    inflate_day(t34);
                    inflate_day(t35);
                    inflate_day(t36);
                    inflate_day(t37);
                    inflate_day(t38);
                    inflate_day(t39);
                    inflate_day(t40);

                }catch (Exception ex){

                }
            }
        }
    }

    public void show_name_reason(int dateToSearch,int i){
        Log.e("tttrrr", "run show_name_reason");
        switch (i){
            case 1:
                Iterator<ClassListItemsDateReasonInMonth> itemsIterator = itemsDateReasonInMonthArrayList.iterator();//создаем итератор
                while(itemsIterator.hasNext()) {//до тех пор, пока в списке есть элементы
                    ClassListItemsDateReasonInMonth items = itemsIterator.next();//получаем следующий элемент
                    if(Integer.valueOf(items.getDate()) == dateToSearch){
                        Toast.makeText(getActivity(), items.getName().trim(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case 2:
                Iterator<ClassListItemsDateReasonInMonth> itemsIterator_today = itemsDateReasonInMonthArrayList_today.iterator();//создаем итератор
                while(itemsIterator_today.hasNext()) {//до тех пор, пока в списке есть элементы
                    ClassListItemsDateReasonInMonth items_today = itemsIterator_today.next();//получаем следующий элемент
                    if(Integer.valueOf(items_today.getDate()) == dateToSearch){
                        Toast.makeText(getActivity(), items_today.getName().trim(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    public void inflate_day(TextView view){
        if (view.getText().length()>0){
            int e = Integer.valueOf((String) view.getText());
            if (data.contains(e)){
                view.setBackgroundResource(R.drawable.krs);
            }else if (data_today.contains(e)){
                view.setBackgroundResource(R.drawable.radius10);
            }else {
                view.setBackgroundResource(R.color.Transp);
            }

            if (data_karan.contains(e)||data_karan2.contains(e)){
                thiskarantin(view);
            }else {
                view.setTypeface(null, Typeface.NORMAL);
//                t_karan_show.setVisibility(View.GONE);
            }
        }else {
            view.setBackgroundResource(R.color.Transp);
            view.setTypeface(null, Typeface.NORMAL);
//            t_karan_show.setVisibility(View.GONE);
        }
    }

    public void clear_calendar(){
        int i = 20;
        t1.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t2.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t3.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t4.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t5.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t6.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t7.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t8.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t9.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t10.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t11.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t12.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t13.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t14.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t15.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t16.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t17.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t18.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t19.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t20.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t21.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t22.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t23.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t24.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t25.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t26.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t27.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t28.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t29.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t30.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t31.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t32.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t33.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t34.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t35.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t36.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t37.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t38.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t39.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t40.animate().alpha(0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        
    }

    public void inflate_calendar(){
        int i = 200;
        t1.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t2.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t3.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t4.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t5.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t6.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t7.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t8.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t9.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t10.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t11.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t12.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t13.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t14.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t15.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t16.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t17.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t18.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t19.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t20.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t21.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t22.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t23.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t24.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t25.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t26.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t27.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t28.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t29.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t30.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t31.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t32.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t33.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t34.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t35.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t36.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t37.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t38.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t39.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());
        t40.animate().alpha(1.0f).setDuration(i).setInterpolator(new FastOutLinearInInterpolator());

    }

    public void saveArrayList(ArrayList<String> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void add_all_otmetki(final Context context) {
        final AlertDialog.Builder ab = new AlertDialog.Builder(context);
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_all_otmetki, null);
        ab.setView(v);
        TextView on_otmetki = v.findViewById(R.id.on_otmetki);
        TextView off_otmetki = v.findViewById(R.id.off_otmetki);
        final Dialog dialog = ab.create();
        on_otmetki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boss_start == 555 || star == 9){
                    Log.d("fgt","allmonth 555");
                    new NotGoAllMonth().execute();
                }else {
                    Log.d("fgt","allmonth ne 555");
                }
                dialog.dismiss();
            }
        });
        off_otmetki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void del_all_otmetki(final Context context) {
        final AlertDialog.Builder ab = new AlertDialog.Builder(context);
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_all_otmetki_un, null);
        ab.setView(v);
        TextView on_otmetki = v.findViewById(R.id.on_otmetkiun);
        TextView off_otmetki = v.findViewById(R.id.off_otmetkiun);
        final Dialog dialog = ab.create();
        on_otmetki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boss_start == 555 || star == 9){
                    Log.d("fgt","allmonth 555");
                    new UnNotGoAllMonth().execute();
                }else {
                    Log.d("fgt","allmonth ne 555");
                }
                dialog.dismiss();
            }
        });
        off_otmetki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private class NotGoAllMonth extends AsyncTask<String, String, String> {

        String msg,query_np = "";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn_np = connectionClass.CONN(login);
                if (conn_np == null){
                    success = false;
                }
                else {
                    if (so==777){
                        query_np = "exec All_day_on_month_so "+idso_x+","+year+","+(month+1)+"";
                    }else {
                        query_np = "exec All_day_on_month "+id+","+id_gruppa+","+year+","+(month+1)+"";
                    }

                    Log.e("tttrrr", query_np);
                    Statement stmt_np = conn_np.createStatement();
                    stmt_np.execute(query_np);
                }
                conn_np.close();
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
            new SyncData().execute();
        }
    }

    private class UnNotGoAllMonth extends AsyncTask<String, String, String> {

        String msg,query_np = "";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn_np = connectionClass.CONN(login);
                if (conn_np == null){
                    success = false;
                }
                else {
                    if (so==777){
                        query_np = "exec DeleteAllDayOnMont_so "+idso_x+","+year+","+(month+1)+"";
                    }else {
                        query_np = "exec DeleteAllDayOnMont "+id+","+id_gruppa+","+year+","+(month+1)+"";
                    }

                    Log.e("tttrrr", query_np);
                    Statement stmt_np = conn_np.createStatement();
                    stmt_np.execute(query_np);
                }
                conn_np.close();
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
            new SyncData().execute();
        }
    }

    public void showToastMessage(String text, int duration){
        final Toast toast = Toast.makeText(getActivity(), text,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
        new CountDownTimer(duration, 1000) {
            public void onTick(long millisUntilFinished) {
                toast.show();
            }
            public void onFinish() {
                toast.cancel();
            }
        }.start();
    }

    @Override
    public void onResume() {
        super.onResume();

        check_interpolar = 0;

//        Log.v("fgt","onResume CalendarFragment");

        sPref = getActivity().getSharedPreferences("first_run", Context.MODE_PRIVATE);
        int first_run = sPref.getInt("first_run", 0);

        if (first_run==0){
            String messaga = "Если Вы здесь впервые - нажмите на меню в левом верхнем углу и выберите раздел 'Справка'.";
            showToastMessage(messaga,8000);
            first_run = 1;
            sPref = getActivity().getSharedPreferences("first_run",Context.MODE_PRIVATE);
            SharedPreferences.Editor ed_first_run = sPref.edit();
            ed_first_run.putInt("first_run",first_run);
            ed_first_run.apply();
        }

        data = new ArrayList<>();
        data_today = new ArrayList<>();
        data_karan = new ArrayList<>();
        data_karan2 = new ArrayList<>();

        sPref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        login = sPref.getString("login", "");
//        Log.d("calen",login);

        sPref = getActivity().getSharedPreferences("star",Context.MODE_PRIVATE);
        star = sPref.getInt("star", 0);
        Log.d("fragment_calendar",star.toString()+" star");

        sPref = getActivity().getSharedPreferences("boss_start",Context.MODE_PRIVATE);
        boss_start = sPref.getInt("boss_start", 0);
        Log.d("fragment_calendar",boss_start.toString()+" boss_start");

        sPref = getActivity().getSharedPreferences("so",Context.MODE_PRIVATE);
        so = sPref.getInt("so", 0);
        Log.d("fragment_calendar",so.toString()+" so");

        sPref = getActivity().getSharedPreferences("idso",Context.MODE_PRIVATE);
        idso = sPref.getInt("idso", 0);
        Log.d("fragment_calendar",idso.toString()+" idso");

        sPref = getActivity().getSharedPreferences("idso_x",Context.MODE_PRIVATE);
        idso_x = sPref.getInt("idso_x", 0);
        Log.d("fragment_calendar",idso_x.toString()+" idso_x");
        if (so!=777){
            sPref = getActivity().getSharedPreferences("id_child",Context.MODE_PRIVATE);
            id = sPref.getInt("id_child", 0);
            Log.d("FATAL", String.valueOf(id));
        }


        sPref = getActivity().getSharedPreferences("id_gruppa",Context.MODE_PRIVATE);
        id_gruppa = sPref.getInt("id_gruppa", 0);

        sPref = getActivity().getSharedPreferences("name_child",Context.MODE_PRIVATE);
        name = sPref.getString("name_child", "");

        sPref = getActivity().getSharedPreferences("name",Context.MODE_PRIVATE);
        String name_so = sPref.getString("name", "");



//        sPref = getActivity().getSharedPreferences("check_save_array_holidays",Context.MODE_PRIVATE);
//        int check_save_array_holidays = sPref.getInt("check_save_array_holidays", 0);

        stringList = new ArrayList<>();
        stringList_work = new ArrayList<>();
        stringList = getArrayList("array_holidays");
        stringList_work = getArrayList("array_work");

//        Log.i("FATAL",stringList.size()+"*");
//        Log.i("FATAL",stringList_work.size()+"*");

        c1 = Calendar.getInstance();

        if ((boss_start==0 && so == 0) || boss_start==555){

            sPref = getActivity().getSharedPreferences("out_int",Context.MODE_PRIVATE);
//            int out_int = (sPref.getInt("out_int", 0));
            int out_int = sPref.getInt("out_int", 0);

            sPref = getActivity().getSharedPreferences("yout_int",Context.MODE_PRIVATE);
            int yout_int = sPref.getInt("yout_int", 0);
            if (out_int != 0){
                out_int--;
                c1.set(Calendar.MONTH,out_int);
                c1.set(Calendar.YEAR,yout_int);
            }
//            Log.d("ccaall","c1 "+c1);
        }

        startMonth(c1);

        /*Calendar proverka_na_next_year = Calendar.getInstance();
        proverka_na_next_year.add(Calendar.YEAR,1);
        proverka_na_next_year.set(Calendar.MONTH,0);
        proverka_na_next_year.set(Calendar.DAY_OF_MONTH,1);
//            Log.w("tttrrr",new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(proverka_na_next_year.getTime())+" proverka_na_next_year");

        if (check_save_array_holidays!=0){
            stringList = getArrayList("array_holidays");
            if (stringList.size()>0 && stringList.contains(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(proverka_na_next_year.getTime()))){
//                    Log.v("tttrrr","array_holidays is save !!!");
                c1 = Calendar.getInstance();
                startMonth(c1);
            }else {
//                    Log.v("tttrrr","array_holidays is not save !!! or not next year !!!");
                new SyncData_get_array_holidays().execute();
            }
        }else {
            Log.v("tttrrr","array_holidays is not save !!!");
            new SyncData_get_array_holidays().execute();
        }*/

        if (so==777){
            namename.setText(name_so);
        }else {
            namename.setText(name);
        }










        t_karan_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((Month)getActivity()).onNavigationItemSelected(0);
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager != null) {
                    fragmentManager
                            .beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .replace(R.id.container, new KaranFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        set_gogo_all_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boss_start == 555 || star == 9){
                    add_all_otmetki(getActivity());
                }
            }
        });

        del_gogo_all_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boss_start == 555 || star == 9){
                    del_all_otmetki(getActivity());
                }
            }
        });

        name_current_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dni_propuski = data.size();
//                Log.i("FATAL",dni_propuski+" dni_propuski");
                int rab_dni = 0;

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR,year);
//                Log.v("tttrrr",new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())+" 1");
                calendar.set(Calendar.MONTH,month);
//                Log.v("tttrrr",new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())+" 2");
                calendar.add(Calendar.MONTH,1);
//                Log.v("tttrrr",new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())+" 3");
                calendar.set(Calendar.DAY_OF_MONTH,1);
//                Log.v("tttrrr",new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())+" 4");
                calendar.add(Calendar.DATE,-1);
//                Log.v("tttrrr",new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())+" 5");
                int first_iter_day = calendar.get(Calendar.DAY_OF_MONTH);
//                Log.v("FATAL",first_iter_day+" first_iter_day");

                int iter = 1;

                while (iter <= first_iter_day){
                    if ((calendar.get(Calendar.DAY_OF_WEEK)!= Calendar.SUNDAY && calendar.get(Calendar.DAY_OF_WEEK)!= Calendar.SATURDAY && !stringList.contains(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())))||stringList_work.contains(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()))){
//                        Log.v("FATAL",new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())+" rab_dni");
                        rab_dni++;
                    }
                    calendar.add(Calendar.DATE,-1);
                    iter++;
                }

                int dni_posesh = rab_dni - dni_propuski;

//                int count_karan = (data_karan.size())+(data_karan2.size());
                String pose = String.valueOf(dni_posesh);
                String prop = String.valueOf(dni_propuski);
                String rabo = String.valueOf(rab_dni);
                /*String karan = String.valueOf(count_karan);
                if (count_karan>0){
                    karan = " \n"+"карантин: "+karan;
                }else {
                    karan = "";
                }*/

                Toast toast = Toast.makeText(getActivity(),"рабочие дни: "+rabo+" \n"+"посещения: "+pose+" \n"+"пропуски: "+prop, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.LEFT,10,230);
                toast.show();
            }
        });

        namename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (so == 777){
                    Toast toast = Toast.makeText(getActivity(),"Должность : "+dol, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP | Gravity.RIGHT,10,90);
                    toast.show();
                }else {
                    Toast toast = Toast.makeText(getActivity(),"Группа : "+nameClick, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP | Gravity.RIGHT,10,90);
                    toast.show();
                }

            }
        });

        month_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat sdf_month = new SimpleDateFormat("M", Locale.getDefault());
                DateFormat sdf_year = new SimpleDateFormat("yyyy", Locale.getDefault());
                Integer in_int = 0;
                Integer yin_int = 0;

                if (boss_start==0 && so == 777){

                    sPref = getActivity().getSharedPreferences("in_int_so",Context.MODE_PRIVATE);
                    in_int = sPref.getInt("in_int_so", 0);
                    sPref = getActivity().getSharedPreferences("yin_int_so",Context.MODE_PRIVATE);
                    yin_int = sPref.getInt("yin_int_so", 0);

//                    Toast.makeText(getActivity(), "so "+ in_int+" 222 "+yin_int, Toast.LENGTH_SHORT).show();
                }else {
                    sPref = getActivity().getSharedPreferences("in_int",Context.MODE_PRIVATE);
                    in_int = sPref.getInt("in_int", 0);
                    sPref = getActivity().getSharedPreferences("yin_int",Context.MODE_PRIVATE);
                    yin_int = sPref.getInt("yin_int", 0);

//                    Toast.makeText(getActivity(), "not so "+ in_int+" 222 "+yin_int, Toast.LENGTH_SHORT).show();
                }

                int month7 = Integer.parseInt(sdf_month.format(c1.getTime()));
                int year7 = Integer.parseInt(sdf_year.format(c1.getTime()));

                if (month7 == in_int && year7 == yin_int){
                    Toast.makeText(getActivity(), "Вы не зачислены в след.месяце!", Toast.LENGTH_SHORT).show();
                }else {
                    check_interpolar = 1;
                    clear_calendar();
                    c1.add(Calendar.MONTH, -1);
                    startMonth(c1);
                }

            }
        });

        month_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat sdf_month = new SimpleDateFormat("M", Locale.getDefault());
                DateFormat sdf_year = new SimpleDateFormat("yyyy", Locale.getDefault());
                Integer out_int = 0;
                Integer yout_int = 0;

                if (boss_start==0 && so == 777){
//                    Toast.makeText(getActivity(), "so", Toast.LENGTH_SHORT).show();
                    sPref = getActivity().getSharedPreferences("out_int_so",Context.MODE_PRIVATE);
                    out_int = sPref.getInt("out_int_so", 0);
                    sPref = getActivity().getSharedPreferences("yout_int_so",Context.MODE_PRIVATE);
                    yout_int = sPref.getInt("yout_int_so", 0);

//                    Toast.makeText(getActivity(), "so "+ out_int+" 222 "+yout_int, Toast.LENGTH_SHORT).show();
                }else {
//                    Toast.makeText(getActivity(), "not so", Toast.LENGTH_SHORT).show();
                    sPref = getActivity().getSharedPreferences("out_int",Context.MODE_PRIVATE);
                    out_int = sPref.getInt("out_int", 0);
                    sPref = getActivity().getSharedPreferences("yout_int",Context.MODE_PRIVATE);
                    yout_int = sPref.getInt("yout_int", 0);

//                    Toast.makeText(getActivity(), "so "+ out_int+" 222 "+yout_int, Toast.LENGTH_SHORT).show();
                }

                int month7 = Integer.parseInt(sdf_month.format(c1.getTime()));
                int year7 = Integer.parseInt(sdf_year.format(c1.getTime()));

                if (month7 == out_int && year7 == yout_int){
                    Toast.makeText(getActivity(), "Вы не зачислены в след.месяце!", Toast.LENGTH_SHORT).show();
                }else {
                    check_interpolar = 1;
                    clear_calendar();
                    c1.add(Calendar.MONTH, 1);
                    startMonth(c1);
                }
            }
        });







        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t1);
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t2);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t3);
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t4);
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t5);
            }
        });
        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t6);
            }
        });
        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t7);
            }
        });
        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t8);
            }
        });
        t9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t9);
            }
        });
        t10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t10);
            }
        });
        t11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t11);
            }
        });
        t12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t12);
            }
        });
        t13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t13);
            }
        });
        t14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t14);
            }
        });
        t15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t15);
            }
        });
        t16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t16);
            }
        });
        t17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t17);
            }
        });
        t18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t18);
            }
        });
        t19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t19);
            }
        });
        t20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t20);
            }
        });
        t21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t21);
            }
        });
        t22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t22);
            }
        });
        t23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t23);
            }
        });
        t24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t24);
            }
        });
        t25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t25);
            }
        });
        t26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t26);
            }
        });
        t27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t27);
            }
        });
        t28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t28);
            }
        });
        t29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t29);
            }
        });
        t30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t30);
            }
        });
        t31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t31);
            }
        });
        t32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t32);
            }
        });
        t33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t33);
            }
        });
        t34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t34);
            }
        });
        t35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t35);
            }
        });
        t36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t36);
            }
        });
        t37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t37);
            }
        });
        t38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t38);
            }
        });
        t39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t39);
            }
        });
        t40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed_day(t40);
            }
        });

        /////////////////////////////////////// long

        t1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t1);
                return true;
            }
        });
        t2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t2);
                return true;
            }
        });
        t3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t3);
                return true;
            }
        });
        t4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t4);
                return true;
            }
        });
        t5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t5);
                return true;
            }
        });
        t6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t6);
                return true;
            }
        });
        t7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t7);
                return true;
            }
        });
        t8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t8);
                return true;
            }
        });
        t9.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t9);
                return true;
            }
        });
        t10.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t10);
                return true;
            }
        });
        t11.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t11);
                return true;
            }
        });
        t12.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t12);
                return true;
            }
        });
        t13.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t13);
                return true;
            }
        });
        t14.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t14);
                return true;
            }
        });
        t15.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t15);
                return true;
            }
        });
        t16.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t16);
                return true;
            }
        });
        t17.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t17);
                return true;
            }
        });
        t18.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t18);
                return true;
            }
        });
        t19.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t19);
                return true;
            }
        });
        t20.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t20);
                return true;
            }
        });
        t21.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t21);
                return true;
            }
        });
        t22.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t22);
                return true;
            }
        });
        t23.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t23);
                return true;
            }
        });
        t24.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t24);
                return true;
            }
        });
        t25.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t25);
                return true;
            }
        });
        t26.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t26);
                return true;
            }
        });
        t27.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t27);
                return true;
            }
        });
        t28.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t28);
                return true;
            }
        });
        t29.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t29);
                return true;
            }
        });
        t30.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t30);
                return true;
            }
        });
        t31.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t31);
                return true;
            }
        });
        t32.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t32);
                return true;
            }
        });
        t33.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t33);
                return true;
            }
        });
        t34.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t34);
                return true;
            }
        });
        t35.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t35);
                return true;
            }
        });
        t36.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t36);
                return true;
            }
        });
        t37.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t37);
                return true;
            }
        });
        t38.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t38);
                return true;
            }
        });
        t39.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t39);
                return true;
            }
        });
        t40.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pressed_day_delete(t40);
                return true;
            }
        });



    }

    public void pressed_day(TextView view){
        String r = (String) view.getText();
        if (r.length()>0){
            int e = Integer.valueOf(r);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,e);
            date_to_db = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            date_in_dialog = new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime());

            if (data.contains(e)||data_today.contains(e)){
                if (data.contains(e))show_name_reason(e,1);
                if (data_today.contains(e))show_name_reason(e,2);
            }else {
                if (boss_start==555){
                    switch (verifi_date_today_med(year,month,e)){
                        case 0:
                            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                            break;
                        case 555:
                            if (so==777){
                                new SyncData_snyat().execute();
                            }else {
                                show_dialog(MED);
                            }
                            break;
                    }
                }else {
                    switch (verifi_date_today(year,month,e,stringList)){
                        case 0:
                            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            if (so==777){
                                new SyncData_today().execute();
                            }else {
                                show_dialog(V_TODAY);
                            }
                            break;
                        case 4:
                            Toast.makeText(getActivity(), msg_closed_tabel, Toast.LENGTH_SHORT).show();
                            break;
                        case 999:
                            if (so==777){
                                new SyncData_snyat().execute();
                            }else {
                                show_dialog(V_NORMAL);
                            }
                            break;
                    }
                }
            }
        }
    }

    public void pressed_day_delete(TextView view){
        String r = (String) view.getText();
        if (r.length()>0){
            int e = Integer.valueOf(r);
//            Log.w("tttrrr",verifi_date_today(year,month,e,stringList)+" verifi_date_today");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,e);
            date_to_db = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
//            Log.w("tttrrr",date_to_db+" date_to_db");
            if (boss_start==555 && (data.contains(e)||data_today.contains(e))){
                switch (verifi_date_today_med(year,month,e)){
                    case 0:
                        Toast.makeText(getActivity(), msg_del, Toast.LENGTH_SHORT).show();
                        break;
                    case 555:
                        new SyncData_del().execute();
                        break;
                }
            }else {
                switch (verifi_date_today(year,month,e,stringList)){
                    case 0:
                        if (data.contains(e)||data_today.contains(e))Toast.makeText(getActivity(), msg_del, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        if (data_today.contains(e)){
                            new SyncData_del_today().execute();
                        }else {
                            Toast.makeText(getActivity(), msg_del, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 4:
                        if (data.contains(e)||data_today.contains(e))Toast.makeText(getActivity(), msg_del_closed_tabel, Toast.LENGTH_SHORT).show();
                        break;
                    case 999:
                        if (data.contains(e))new SyncData_del().execute();
                        break;
                }
            }
        }
    }

    public int verifi_date_today(int year,int month,int day, List<String> stringList){
//        month--;
        DateFormat sdf_month = new SimpleDateFormat("M", Locale.getDefault());
        DateFormat sdf_year = new SimpleDateFormat("yyyy", Locale.getDefault());
        DateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        Calendar today = Calendar.getInstance();

        Calendar last_day = Calendar.getInstance();
        last_day.add(Calendar.MONTH, 1);
        last_day.set(Calendar.DAY_OF_MONTH,1);
        last_day.add(Calendar.DATE, -1);

        Calendar first_day = Calendar.getInstance();
        first_day.set(Calendar.DAY_OF_MONTH,1);

        Calendar item_day = Calendar.getInstance();
        item_day.set(Calendar.DAY_OF_MONTH,day);
        item_day.set(Calendar.YEAR,year);
        item_day.set(Calendar.MONTH,month);

        int hour = today.get(Calendar.HOUR_OF_DAY);//реальный текущий час
        Log.i("ccaall", hour+" hour real");
        int month_item = Integer.parseInt(sdf_month.format(item_day.getTime()));
        Log.i("ccaall", month_item+" month_item");
        int month_today = Integer.parseInt(sdf_month.format(today.getTime()));
        Log.i("ccaall", month_today+" month_today");
        int year_item = Integer.parseInt(sdf_year.format(item_day.getTime()));
        Log.i("ccaall", year_item+" year_item");
        int year_today = Integer.parseInt(sdf_year.format(today.getTime()));
        Log.i("ccaall", year_today+" year_today");

//        Log.i("calen", sdf_date.format(cal.getTime())+" cal.getTime()");
//        Log.i("calen", sdf_date.format(real_cal.getTime())+" real_cal.getTime()");
        Log.i("ccaall", item_day.getTime()+" item_day.getTime()");
        Log.i("ccaall", today.getTime()+" today.getTime()");
        Log.i("ccaall", last_day.getTime()+" last_day.getTime()");

        set_time_to_null(item_day);
        set_time_to_null(today);

        Log.i("ccaall",item_day.compareTo(today)+" compareTo");

        set_time_to_null(item_day);
        set_time_to_null(today);

        if (item_day.before(today)){
            return 0;
        }
        if (item_day.equals(today) && hour < 12){
            return 1;
        }
        if (item_day.equals(today) && hour >= 12){
            return 0;
        }

        Calendar last_work_today_month1 = Calendar.getInstance();
        Calendar last_work_today_month2 = Calendar.getInstance();
        Calendar last_work_today_month3 = Calendar.getInstance();

        set_time_to_null(last_day);
        set_time_to_null(first_day);

        while (last_day.after(first_day) || last_day.equals(first_day)){
            if ((last_day.get(Calendar.DAY_OF_WEEK)!= Calendar.SUNDAY && last_day.get(Calendar.DAY_OF_WEEK)!= Calendar.SATURDAY && !stringList.contains(sdf_date.format(last_day.getTime())))
                    ||stringList_work.contains(sdf_date.format(last_day.getTime()))){
                last_work_today_month1.set(Calendar.DAY_OF_MONTH,last_day.get(Calendar.DAY_OF_MONTH));
                Log.i("ccaall",last_work_today_month1.getTime()+" last_work_today_month1");//первый раб день с конца
                break;
            }else {
                last_day.add(Calendar.DATE,-1);
            }
        }

        last_day.add(Calendar.DATE,-1);

        set_time_to_null(last_day);
        set_time_to_null(first_day);

        while (last_day.after(first_day) || last_day.equals(first_day)){
            if ((last_day.get(Calendar.DAY_OF_WEEK)!= Calendar.SUNDAY && last_day.get(Calendar.DAY_OF_WEEK)!= Calendar.SATURDAY && !stringList.contains(sdf_date.format(last_day.getTime())))
                    ||stringList_work.contains(sdf_date.format(last_day.getTime()))){
                last_work_today_month2.set(Calendar.DAY_OF_MONTH,last_day.get(Calendar.DAY_OF_MONTH));
                Log.i("ccaall",last_work_today_month2.getTime()+" last_work_today_month2");//второй раб день с конца
                break;
            }else {
                last_day.add(Calendar.DATE,-1);
            }
        }

        last_day.add(Calendar.DATE,-1);

        set_time_to_null(last_day);
        set_time_to_null(first_day);

        while (last_day.after(first_day) || last_day.equals(first_day)){
            if ((last_day.get(Calendar.DAY_OF_WEEK)!= Calendar.SUNDAY && last_day.get(Calendar.DAY_OF_WEEK)!= Calendar.SATURDAY && !stringList.contains(sdf_date.format(last_day.getTime())))
                    ||stringList_work.contains(sdf_date.format(last_day.getTime()))){
                last_work_today_month3.set(Calendar.DAY_OF_MONTH,last_day.get(Calendar.DAY_OF_MONTH));
                Log.i("ccaall",last_work_today_month3.getTime()+" last_work_today_month3");//третий раб день с конца
                break;
            }else {
                last_day.add(Calendar.DATE,-1);
            }
        }

        ///////////////////////////////////////////////////////////////////

        set_time_to_null(item_day);
        set_time_to_null(last_work_today_month1);
        set_time_to_null(last_work_today_month2);
        set_time_to_null(today);
        set_time_to_null(last_work_today_month3);

//        Log.d("tttrrr",item_day.getTimeInMillis()+" item_day");
//        Log.d("tttrrr",today.getTimeInMillis()+" today");
//
//        Log.d("tttrrr",month_item+" month_item");
//        Log.d("tttrrr",month_today+" month_today");
//        Log.d("tttrrr",hour+" hour");
//
//        Log.d("tttrrr",last_work_today_month1.getTimeInMillis()+" last_work_today_month1");
//        Log.d("tttrrr",last_work_today_month2.getTimeInMillis()+" last_work_today_month2");
//        Log.d("tttrrr",last_work_today_month3.getTimeInMillis()+" last_work_today_month3");
//
//        long it = item_day.getTimeInMillis();
//        long to = today.getTimeInMillis();
//        long l1 = last_work_today_month1.getTimeInMillis();
//        long l2 = last_work_today_month2.getTimeInMillis();
//        long l3 = last_work_today_month3.getTimeInMillis();

//        if (it==to )

        if (item_day.after(today) || item_day.equals(today)){
            if (month_item == month_today){

                if (item_day.equals(last_work_today_month1) && (today.equals(last_work_today_month1) || today.equals(last_work_today_month2) || (today.equals(last_work_today_month3) && hour >= 12))){
                    return 4;
                }

                if (item_day.equals(last_work_today_month2) && (today.equals(last_work_today_month2) || (today.equals(last_work_today_month3) && hour >= 12))){
                    return 4;
                }
            }
        }

        ///////////////////////////////////////////////////////////////////

        Calendar iter_day = Calendar.getInstance();
        Calendar work_day_before_item_day = Calendar.getInstance();
        iter_day.set(Calendar.DAY_OF_MONTH,day);//выбранный день
        iter_day.set(Calendar.YEAR,year);
        iter_day.set(Calendar.MONTH,month);
        iter_day.add(Calendar.DATE,-1);

        Log.i("ccaall",iter_day.getTime()+" iter_day");//iter_day

        set_time_to_null(iter_day);
        set_time_to_null(today);

        while (iter_day.after(today) || iter_day.equals(today)){
            if ((iter_day.get(Calendar.DAY_OF_WEEK)!= Calendar.SUNDAY && iter_day.get(Calendar.DAY_OF_WEEK)!= Calendar.SATURDAY && !stringList.contains(sdf_date.format(iter_day.getTime())))
                    ||stringList_work.contains(sdf_date.format(iter_day.getTime()))){
                work_day_before_item_day.set(Calendar.DAY_OF_MONTH,iter_day.get(Calendar.DAY_OF_MONTH));
                work_day_before_item_day.set(Calendar.MONTH,iter_day.get(Calendar.MONTH));
                work_day_before_item_day.set(Calendar.YEAR,iter_day.get(Calendar.YEAR));
                Log.i("ccaall",work_day_before_item_day.getTime()+" work_day_before_item_day");//получаем ближайший рабочий день до выбранной даты

                set_time_to_null(today);
                set_time_to_null(item_day);
                set_time_to_null(work_day_before_item_day);

                if (item_day.after(today) && today.equals(work_day_before_item_day) && hour >= 12){
                    Log.i("ccaall"," return 0;888888888888888888");
                    return 0;
                }
                break;
            }else {
                iter_day.add(Calendar.DATE,-1);
            }
        }

        Calendar iter_day2 = Calendar.getInstance();
        Calendar work_day_after_today = Calendar.getInstance();


        if ((today.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || today.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || stringList.contains(sdf_date.format(today.getTime())))
                &&!stringList_work.contains(sdf_date.format(today.getTime()))){
            iter_day2.add(Calendar.DATE,1);
            while (true){
                if ((iter_day2.get(Calendar.DAY_OF_WEEK)!= Calendar.SUNDAY && iter_day2.get(Calendar.DAY_OF_WEEK)!= Calendar.SATURDAY && !stringList.contains(sdf_date.format(iter_day2.getTime())))
                        ||stringList_work.contains(sdf_date.format(iter_day2.getTime()))){
                    work_day_after_today.set(Calendar.DAY_OF_MONTH,iter_day2.get(Calendar.DAY_OF_MONTH));
                    work_day_after_today.set(Calendar.MONTH,iter_day2.get(Calendar.MONTH));
                    work_day_after_today.set(Calendar.YEAR,iter_day2.get(Calendar.YEAR));
                    Log.i("ccaall",work_day_after_today.getTime()+" work_day_after_today");//получаем ближайший рабочий день после сегодня
                    break;
                }else {
                    iter_day2.add(Calendar.DATE,1);
                }
            }

            set_time_to_null(item_day);
            set_time_to_null(work_day_after_today);

            if (item_day.equals(work_day_after_today)){
                return 0;
            }

            set_time_to_null(item_day);
            Log.d("ccaall",item_day.get(Calendar.DAY_OF_WEEK)+" item_day.get(Calendar.DAY_OF_WEEK)");
            if ((item_day.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || item_day.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || stringList.contains(sdf_date.format(item_day.getTime())))
                    &&!stringList_work.contains(sdf_date.format(item_day.getTime()))){
                return 0;
            }
        }
        return 999;
    }

    public int verifi_date_today_med(int year,int month,int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,day);

        Calendar calendar1_today = Calendar.getInstance();

        if ((calendar.get(Calendar.MONTH) < calendar1_today.get(Calendar.MONTH) && calendar.get(Calendar.YEAR) == calendar1_today.get(Calendar.YEAR)) || calendar.get(Calendar.YEAR) < calendar1_today.get(Calendar.YEAR)){
            return 0;
        }
        return 555;
    }

    public void set_time_to_null(Calendar calendar){
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.MINUTE,0);
    }


}
