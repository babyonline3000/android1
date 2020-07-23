package com.example.cosmos.baby.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cosmos.baby.Antropometria;
import com.example.cosmos.baby.Antropometria2;
import com.example.cosmos.baby.ClassListItems;
import com.example.cosmos.baby.ClassListItems1;
import com.example.cosmos.baby.ClassListItems2;
import com.example.cosmos.baby.ConnectionClass;
import com.example.cosmos.baby.ContItems;
import com.example.cosmos.baby.FindItems;
import com.example.cosmos.baby.GruppiZdor;
import com.example.cosmos.baby.History;
import com.example.cosmos.baby.History_perevod_gruppa;
import com.example.cosmos.baby.Itogo;
import com.example.cosmos.baby.Kal;
import com.example.cosmos.baby.ListItemsStoyat;
import com.example.cosmos.baby.Login2;
import com.example.cosmos.baby.Mantu;
import com.example.cosmos.baby.Otchet5_18_07_19;
import com.example.cosmos.baby.R;
import com.example.cosmos.baby.Vremenno;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.victor.loading.rotate.RotateLoading;

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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Timer;


public class TabelFragment extends Fragment {

    CalendarFragment calendarFragment;
    String[] bolez, bolez2, bolez3, bolez4,prishel_massive,prishel_massive2,prishel_massive3,bolez_med_red;
    String prishel_string = "- Я ПРИШЕЛ";
    String prishel_string_UN = "- ОТМЕНА (Я ПРИШЕЛ)";
    String prishel_string_UN3 = "- ПОСТАВИТЬ НА ПИТАНИЕ";




    TextView currentDateTime;

    Calendar dateAndTime,dateAndTime2;
    final String MSSQL = "MSSQL";
    final String MSQQ = "MSQQ";
    final String MSSW = "MSSW";

    private ArrayList<ClassListItems> itemsArrayList;
    private ArrayList<ClassListItems2> itemsArrayList2;

    private ArrayList<ClassListItems1> items1;
    private ArrayList<ClassListItems1> items2;

    private ArrayList<FindItems> findArrayList;
    private ArrayList<ContItems> contArrayList;
    private ArrayList<ListItemsStoyat> gruppa1;

    HashSet<ClassListItems2> set2;

    List<String> gruppa,gruppa2,gruppa3;
    List<String> g1,g2,g3,g4,g5;

    private MyAppAdapter myAppAdapter;
    private MyAppAdapter2 myAppAdapter2;
    private MyAppAdapter3 myAppAdapter3;
    private AdapterFind myFindAdapter;
    private AdapterCont myContAdapter;

    private ListView listView,listView2,listView3,listviewFind,listviewCont;

    private boolean success = false;
    private ConnectionClass connectionClass;
    String count_notgo,date,countGR,countGR_stoyat,countGR_snyati,month,gm1,login,find,gm2;
    Button b1,b2,b3,b4;
    String[]mgruppa,m1,m2,m3,m4,m5;
    public static final String GRUPPA = "mygruppa";
    SharedPreferences mGR;
    SharedPreferences sPref;
    Integer mon,flag1,flag_begin,flagCalendar,boss_start;

    ImageView icon;
    FlowingDrawer mDrawer;

    String name,item_gruppa;
    LinearLayout fgb;

    String [] doljnost = {"Заведующий","Ст.воспитатель","Ст.медсестра","Медсестра","Делопроизводитель","Воспитатель","Няня","Общий телефон дет.сада"};
    EditText add_tel,editText_fio_cont,edit_fio_cont;
    TextView add_dol,add_name_gruppa_cont,edit_tel,edit_dol,edit_name_gruppa_cont;
    String item_dol,item_gruppa_add_cont,id_cont,tel,fio_cont;
    String getfio,getdol,getnamegr,gettel,getid_c,dateday,datemonth;

    double diagonalInches;


    private final int IDD = 0;
    private final int IDD1 = 1;
    private final int IDD2 = 2;
    private final int IDD3 = 3;

    Integer clock,month_int,day_int,today_segodnyaINT,datemonthINT,IntDayOfWeek;
    String hour,get_id_child,today_segodnya,item1,id_child_surprise_1,count_surprise,ggg1t,count_notgo2,count_gr,count_nestoyat,count_grup;

    String msg = "отметка невозможна";
    String msg_del = "удаление невозможно";
    String holholmsg = "сегодня выходной";

    TextView tablo,t1,t2,t3,t4,t5,t6,t7,t8,t9,t0, tok,t_exit_sotr;
    Integer itablo,ifirstpincot,i_create_table_deti,i_current_year,i_current_month,idso_x;
    private Timer mTimer;
//    private MyTimerTask mMyTimerTask;
    String nameso;
    String idso,schetso,rozdso,name_sp_pincot;
    Integer in_int,out_int;
    List<String> list_pincot_so = new ArrayList<>();
    String[]mPincotSo;

    public static final String ALARM_EVENT = "net.multipi.ALARM";
    public static final int ALARM_INTERVAL_SEC = 5;

    String s_kto,query_h,reason;

    ProgressDialog progress;

    Integer progress1,progress2,progress3,progress4,so,get_id_gruppa;

    ProgressBar progressBar;
    RotateLoading rotateLoading;

    RelativeLayout relativeLayout;
    ArrayList<String> stringList;
    ArrayList<String> stringList_work;

    private final int V_NORMAL = 0;
    private final int V_TODAY = 1;
    private final int MED = 2;
    private final int SURPRISE = 3;
    private final int SURPRISE2 = 4;
    private final int MED2 = 5;
    private final int V_TODAY2 = 6;
    private final int MED3 = 7;

    String date_to_db,date_in_dialog;

    List<String> l_count_vse,l_count_stoyat,l_count_snyati,l_count_today,l_not_go,l_opozd,l_surprise;
    String msg_closed_tabel = "отметка невозможна, табель закрыт";
    String msg_del_closed_tabel = "удаление невозможно, табель закрыт";
    String query_update_new;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tabel, container, false);
        calendarFragment = new CalendarFragment();
        List<String> bolele = new ArrayList<>();
        bolele.add(calendarFragment.a);
        bolele.add(calendarFragment.b);
        bolele.add(calendarFragment.c);
        bolele.add(calendarFragment.d);
        bolele.add(calendarFragment.f);
        bolez = bolele.toArray(new String[bolele.size()]);

        List<String> bolele2 = new ArrayList<>();
        bolele2.add(calendarFragment.a1);
        bolele2.add(calendarFragment.b1);
        bolez2 = bolele2.toArray(new String[bolele2.size()]);

        List<String> bolele21 = new ArrayList<>();
        bolele21.add(calendarFragment.a1);
        bolele21.add(calendarFragment.b1);
        bolele21.add("");
        bolele21.add("УДАЛИТЬ ОПОВЕЩЕНИЕ");
        prishel_massive3 = bolele21.toArray(new String[bolele21.size()]);



        List<String> bolele3 = new ArrayList<>();
        bolele3.add(calendarFragment.a);
        bolele3.add(calendarFragment.b);
        bolele3.add(calendarFragment.c);
        bolele3.add(calendarFragment.d);
        bolele3.add(calendarFragment.f);
        bolele3.add(calendarFragment.a1);
        bolele3.add(calendarFragment.b1);
        bolez3 = bolele3.toArray(new String[bolele3.size()]);

        List<String> bolele4 = new ArrayList<>();
        bolele4.add(calendarFragment.a);
        bolele4.add(calendarFragment.b);
        bolele4.add(calendarFragment.c);
        bolele4.add(calendarFragment.d);
        bolele4.add(calendarFragment.f);
        bolele4.add(calendarFragment.a1);
        bolele4.add(calendarFragment.b1);
        bolele4.add("");
        bolele4.add("УДАЛИТЬ ОПОВЕЩЕНИЕ");
        bolez4 = bolele4.toArray(new String[bolele4.size()]);

        List<String> bolele_med_red = new ArrayList<>();
        bolele_med_red.add(prishel_string);
        bolele_med_red.add(prishel_string_UN);
        bolele_med_red.add("");
        bolele_med_red.add(calendarFragment.a);
        bolele_med_red.add(calendarFragment.b);
        bolele_med_red.add(calendarFragment.c);
        bolele_med_red.add(calendarFragment.d);
        bolele_med_red.add(calendarFragment.f);
        bolele_med_red.add(calendarFragment.a1);
        bolele_med_red.add(calendarFragment.b1);
        bolele_med_red.add("");
        bolele_med_red.add(prishel_string_UN3);
        bolez_med_red = bolele_med_red.toArray(new String[bolele_med_red.size()]);



        List<String> prishel_array = new ArrayList<>();
        prishel_array.add(prishel_string);
        prishel_array.add(prishel_string_UN);
        prishel_massive = prishel_array.toArray(new String[prishel_array.size()]);

        List<String> prishel_array2 = new ArrayList<>();
        prishel_array2.add(prishel_string);
        prishel_array2.add(prishel_string_UN);
        prishel_array2.add("");
        prishel_array2.add(prishel_string_UN3);
        prishel_massive2 = prishel_array2.toArray(new String[prishel_array2.size()]);



        listView = view.findViewById(R.id.list_deti);
        listView2 = view.findViewById(R.id.list_deti2);
        listView3 = view.findViewById(R.id.list_deti3);

        connectionClass = new ConnectionClass();

        itemsArrayList = new ArrayList<>();
        itemsArrayList2 = new ArrayList<>();
        findArrayList = new ArrayList<>();
        contArrayList = new ArrayList<>();
        items1 = new ArrayList<>();
        items2 = new ArrayList<>();

        icon = view.findViewById(R.id.icon55);

        set2 = new LinkedHashSet<>();

        gruppa = new ArrayList<>();
        gruppa1 = new ArrayList<>();
        gruppa2 = new ArrayList<>();
        gruppa3 = new ArrayList<>();

        g1 = new ArrayList<>();
        g2 = new ArrayList<>();
        g3 = new ArrayList<>();
        g4 = new ArrayList<>();
        g5 = new ArrayList<>();

        b1 = view.findViewById(R.id.but1);
        b2 = view.findViewById(R.id.but2);
        b3 = view.findViewById(R.id.but3);
        b4 = view.findViewById(R.id.but4);

        currentDateTime = view.findViewById(R.id.currentDateTime);

        sPref = getActivity().getSharedPreferences("item_gruppa", Context.MODE_PRIVATE);
        item_gruppa = sPref.getString("item_gruppa", "");

        sPref = getActivity().getSharedPreferences("login",Context.MODE_PRIVATE);
        login = sPref.getString("login", "");

        mgruppa = loadArray("mgruppa", getActivity());

        return view;
    }

    public int verifi_date_today(int year,int month,int day){
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

    public void pressed_day(int i){
        date_to_db = new SimpleDateFormat("yyyy-MM-dd").format(dateAndTime.getTime());
        date_in_dialog = new SimpleDateFormat("dd.MM.yyyy").format(dateAndTime.getTime());

        switch (i){
            case 1:
                if (boss_start==555){
                    switch (verifi_date_today_med(dateAndTime.get(Calendar.YEAR),dateAndTime.get(Calendar.MONTH),dateAndTime.get(Calendar.DAY_OF_MONTH))){
                        case 0:
                            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                            break;
                        case 555:
                            show_dialog(MED);
                            break;
                    }
                }else {
                    switch (verifi_date_today(dateAndTime.get(Calendar.YEAR),dateAndTime.get(Calendar.MONTH),dateAndTime.get(Calendar.DAY_OF_MONTH))){
                        case 0:
                            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            show_dialog(V_TODAY);
                            break;
                        case 4:
                            Toast.makeText(getActivity(), msg_closed_tabel, Toast.LENGTH_SHORT).show();
                            break;
                        case 999:
                            show_dialog(V_NORMAL);
                            break;
                    }
                }
                break;
            case 2:
                if (boss_start==555){
                    switch (verifi_date_today_med(dateAndTime.get(Calendar.YEAR),dateAndTime.get(Calendar.MONTH),dateAndTime.get(Calendar.DAY_OF_MONTH))){
                        case 0:
                            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                            break;
                        case 555:
                            show_dialog(MED3);
                            break;
                    }
                }else {
                    switch (verifi_date_today(dateAndTime.get(Calendar.YEAR),dateAndTime.get(Calendar.MONTH),dateAndTime.get(Calendar.DAY_OF_MONTH))){
                        case 0:
                            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            show_dialog(SURPRISE);
                            break;
                        case 4:
                            Toast.makeText(getActivity(), msg_closed_tabel, Toast.LENGTH_SHORT).show();
                            break;
                        case 999:
                            show_dialog(SURPRISE2);
                            break;
                    }
                }
                break;
            case 3:
                if (boss_start==555){
                    switch (verifi_date_today_med(dateAndTime.get(Calendar.YEAR),dateAndTime.get(Calendar.MONTH),dateAndTime.get(Calendar.DAY_OF_MONTH))){
                        case 0:
                            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                            break;
                        case 555:
                            show_dialog(MED2);
                            break;
                    }
                }else {
                    switch (verifi_date_today(dateAndTime.get(Calendar.YEAR),dateAndTime.get(Calendar.MONTH),dateAndTime.get(Calendar.DAY_OF_MONTH))){
                        case 0:
                            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            show_dialog(V_TODAY2);
                            break;
                        case 4:
                            Toast.makeText(getActivity(), msg_closed_tabel, Toast.LENGTH_SHORT).show();
                            break;
                        case 999:
//                            show_dialog(V_TODAY2);
                            break;
                    }
                }
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
//                    query_np = "insert into gogo (id_child,id_gruppa,datenotgo,prichina,surprise) values ('"+get_id_child+"','"+get_id_gruppa+"','"+date_to_db+"','"+reason+"','');";
                    query_np = "exec Insert_Delete_And_History_So 1,"+get_id_child+","+get_id_gruppa+",'"+date_to_db+"','"+reason+"',0,"+idso_x+"";
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
            new SyncDataBoss().execute();
        }
    }

    private class SyncData_snyat extends AsyncTask<String, String, String> {

        String msg = "подключитесь к интернету!";
        ProgressDialog progress;
        Dialog progressDialog;

        @Override
        protected void onPreExecute() {

            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();

            l_count_vse.clear();
            l_count_stoyat.clear();
            l_count_snyati.clear();
            l_count_today.clear();
            l_not_go.clear();
            l_opozd.clear();
            l_surprise.clear();

            gruppa1.clear();
            itemsArrayList.clear();
            itemsArrayList2.clear();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
                    Log.d(MSSQL,"success = false");
                }
                else {

                    String query9 = "begin tran exec Insert_Delete_And_History_So 1,"+get_id_child+","+get_id_gruppa+",'"+date_to_db+"','"+reason+"',0,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                    //Log.d("fgt",query9);
//                    Log.d("novoeee",new SimpleDateFormat("yyyy-MM-dd").format(dateAndTime.getTime()));
//                    Log.d("novoeee",query9);
                    Statement stmt9 = conn.createStatement();
                    ResultSet rs9 = stmt9.executeQuery(query9);
                    if (rs9 != null){
                        while (rs9.next()){
                            try {



                                if (rs9.getString("name")!=null){
                                    l_count_vse.add(rs9.getString("name").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name1")!=null){
                                    l_count_stoyat.add(rs9.getString("name1").trim());
                                    gruppa1.add(new ListItemsStoyat(rs9.getString("name1").trim(),rs9.getString("id_child1").trim(),rs9.getInt("id_gruppa"),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"))); //получаем список детей стоят
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name2")!=null){
                                    l_count_snyati.add(rs9.getString("name2").trim());
                                    l_surprise.add(rs9.getString("surprise").trim());
                                    //itemsArrayList.add(new ClassListItems(rs9.getString("name2").trim(),rs9.getString("id_child2").trim(),rs9.getString("prichina").trim(),rs9.getString("surprise").trim(),rs9.getString("start").trim(),rs9.getString("in").trim(),rs9.getString("out").trim(),rs9.getString("id_gruppa").trim()));
                                    itemsArrayList.add(new ClassListItems(rs9.getString("name2").trim(),rs9.getString("id_child2").trim(),rs9.getString("reason").trim(),rs9.getString("surprise").trim(),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"),rs9.getInt("id_gruppa")));

                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name3")!=null){
                                    l_count_today.add(rs9.getString("name3").trim());
                                    //itemsArrayList2.add(new ClassListItems2(rs9.getString("name3").trim(),rs9.getString("id_child3").trim(),rs9.getString("today").trim(),rs9.getString("start").trim(),rs9.getString("in").trim(),rs9.getString("out").trim(),rs9.getString("id_gruppa").trim()));
                                    itemsArrayList2.add(new ClassListItems2(rs9.getString("name3").trim(),rs9.getString("id_child3").trim(),rs9.getString("reason").trim(),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"),rs9.getInt("id_gruppa")));
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }
                                /*if (rs9.getString("today").contains("ОПОЗДАНИЕМ")){
                                    l_opozd.add(rs9.getString("today").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }*/
                                /*if (rs9.getString("today").contains("СОВСЕМ")){
                                    l_not_go.add(rs9.getString("today").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }*/
                                if (rs9.getInt("id")==200) l_not_go.add(rs9.getString("id"));


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
                //Log.d("ooooo","syncdata_tabel");
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

            /*Log.d("fgt", String.valueOf(l_count_vse.size())+" l_count_vse");
            Log.d("fgt", String.valueOf(l_count_stoyat.size())+" l_count_stoyat");
            Log.d("fgt", String.valueOf(l_count_snyati.size())+" l_count_snyati");
            Log.d("fgt", String.valueOf(l_count_today.size())+" l_count_today");

            Log.d("fgt", String.valueOf(l_opozd.size())+" l_opozd");
            Log.d("fgt", String.valueOf(l_not_go.size())+" l_not_go");
            Log.d("fgt", String.valueOf(l_surprise.size())+" l_surprise");*/



            //Toast.makeText(MainActivity.this, msg + "", Toast.LENGTH_SHORT).show();
            if (success == false){

            }else {
                try {

                    myAppAdapter2 = new MyAppAdapter2(gruppa1, getActivity());
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter2);
                    listView.setStackFromBottom(false);

                    sPref = getActivity().getSharedPreferences("item_gruppa",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed2 = sPref.edit();
                    ed2.putString("item_gruppa",item_gruppa);
                    ed2.commit();



                    myAppAdapter = new MyAppAdapter(itemsArrayList, getActivity());
                    int savedPosition = listView2.getFirstVisiblePosition();
                    View firstVisibleView = listView2.getChildAt(0);
                    int savedListTop = (firstVisibleView == null) ? 0 : firstVisibleView.getTop();
                    listView2.setAdapter(myAppAdapter);
                    if (savedPosition >= 0) { //initialized to -1
                        listView2.setSelectionFromTop(savedPosition, savedListTop);
                    }

                    myAppAdapter3 = new MyAppAdapter3(itemsArrayList2, getActivity());
                    //listView3.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView3.setAdapter(myAppAdapter3);
                    listView3.setStackFromBottom(false);


                    m3 = l_surprise.toArray(new String[0]);
                    for(int i=0;i<=m3.length;i++) {
                        if(l_surprise.contains("")) {
                            l_surprise.remove("");
                        }
                    }
                    String [] mm3 = l_surprise.toArray(new String[0]);
                    count_surprise = String.valueOf(mm3.length); //кол-во сюрпризов




                    b1.setText("Поставлены "+String.valueOf(l_count_stoyat.size())+"/"+String.valueOf(l_count_today.size()));
                    b3.setText(item_gruppa+" "+String.valueOf(l_count_vse.size()));
                    b2.setText("Сняты "+String.valueOf(l_count_snyati.size())+"/"+count_surprise);
                    b4.setText("Не пришли "+String.valueOf(l_count_today.size())+"/"+String.valueOf(l_not_go.size()));


                }catch (Exception ex){

                }
            }
        }
    }

    private class SyncData_today extends AsyncTask<String, String, String> {

        String msg = "подключитесь к интернету!";
        ProgressDialog progress;
        Dialog progressDialog;

        @Override
        protected void onPreExecute() {

            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();

            l_count_vse.clear();
            l_count_stoyat.clear();
            l_count_snyati.clear();
            l_count_today.clear();
            l_not_go.clear();
            l_opozd.clear();
            l_surprise.clear();

            gruppa1.clear();
            itemsArrayList.clear();
            itemsArrayList2.clear();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
                    Log.d(MSSQL,"success = false");
                }
                else {

                    String query9 = "begin tran exec Insert_Delete_And_History_So 2,"+get_id_child+","+get_id_gruppa+",'"+date_to_db+"','"+reason+"',0,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                    //Log.d("fgt",query9);
//                    Log.d("novoeee",new SimpleDateFormat("yyyy-MM-dd").format(dateAndTime.getTime()));
//                    Log.d("novoeee",query9);
                    Statement stmt9 = conn.createStatement();
                    ResultSet rs9 = stmt9.executeQuery(query9);
                    if (rs9 != null){
                        while (rs9.next()){
                            try {



                                if (rs9.getString("name")!=null){
                                    l_count_vse.add(rs9.getString("name").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name1")!=null){
                                    l_count_stoyat.add(rs9.getString("name1").trim());
                                    gruppa1.add(new ListItemsStoyat(rs9.getString("name1").trim(),rs9.getString("id_child1").trim(),rs9.getInt("id_gruppa"),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"))); //получаем список детей стоят
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name2")!=null){
                                    l_count_snyati.add(rs9.getString("name2").trim());
                                    l_surprise.add(rs9.getString("surprise").trim());
                                    //itemsArrayList.add(new ClassListItems(rs9.getString("name2").trim(),rs9.getString("id_child2").trim(),rs9.getString("prichina").trim(),rs9.getString("surprise").trim(),rs9.getString("start").trim(),rs9.getString("in").trim(),rs9.getString("out").trim(),rs9.getString("id_gruppa").trim()));
                                    itemsArrayList.add(new ClassListItems(rs9.getString("name2").trim(),rs9.getString("id_child2").trim(),rs9.getString("reason").trim(),rs9.getString("surprise").trim(),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"),rs9.getInt("id_gruppa")));

                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name3")!=null){
                                    l_count_today.add(rs9.getString("name3").trim());
                                    //itemsArrayList2.add(new ClassListItems2(rs9.getString("name3").trim(),rs9.getString("id_child3").trim(),rs9.getString("today").trim(),rs9.getString("start").trim(),rs9.getString("in").trim(),rs9.getString("out").trim(),rs9.getString("id_gruppa").trim()));
                                    itemsArrayList2.add(new ClassListItems2(rs9.getString("name3").trim(),rs9.getString("id_child3").trim(),rs9.getString("reason").trim(),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"),rs9.getInt("id_gruppa")));
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }
                                /*if (rs9.getString("today").contains("ОПОЗДАНИЕМ")){
                                    l_opozd.add(rs9.getString("today").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }*/
                                /*if (rs9.getString("today").contains("СОВСЕМ")){
                                    l_not_go.add(rs9.getString("today").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }*/
                                if (rs9.getInt("id")==200) l_not_go.add(rs9.getString("id"));


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
                //Log.d("ooooo","syncdata_tabel");
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

            /*Log.d("fgt", String.valueOf(l_count_vse.size())+" l_count_vse");
            Log.d("fgt", String.valueOf(l_count_stoyat.size())+" l_count_stoyat");
            Log.d("fgt", String.valueOf(l_count_snyati.size())+" l_count_snyati");
            Log.d("fgt", String.valueOf(l_count_today.size())+" l_count_today");

            Log.d("fgt", String.valueOf(l_opozd.size())+" l_opozd");
            Log.d("fgt", String.valueOf(l_not_go.size())+" l_not_go");
            Log.d("fgt", String.valueOf(l_surprise.size())+" l_surprise");*/



            //Toast.makeText(MainActivity.this, msg + "", Toast.LENGTH_SHORT).show();
            if (success == false){

            }else {
                try {

                    myAppAdapter2 = new MyAppAdapter2(gruppa1, getActivity());
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter2);
                    listView.setStackFromBottom(false);

                    sPref = getActivity().getSharedPreferences("item_gruppa",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed2 = sPref.edit();
                    ed2.putString("item_gruppa",item_gruppa);
                    ed2.commit();



                    myAppAdapter = new MyAppAdapter(itemsArrayList, getActivity());
                    int savedPosition = listView2.getFirstVisiblePosition();
                    View firstVisibleView = listView2.getChildAt(0);
                    int savedListTop = (firstVisibleView == null) ? 0 : firstVisibleView.getTop();
                    listView2.setAdapter(myAppAdapter);
                    if (savedPosition >= 0) { //initialized to -1
                        listView2.setSelectionFromTop(savedPosition, savedListTop);
                    }

                    myAppAdapter3 = new MyAppAdapter3(itemsArrayList2, getActivity());
                    //listView3.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView3.setAdapter(myAppAdapter3);
                    listView3.setStackFromBottom(false);


                    m3 = l_surprise.toArray(new String[0]);
                    for(int i=0;i<=m3.length;i++) {
                        if(l_surprise.contains("")) {
                            l_surprise.remove("");
                        }
                    }
                    String [] mm3 = l_surprise.toArray(new String[0]);
                    count_surprise = String.valueOf(mm3.length); //кол-во сюрпризов




                    b1.setText("Поставлены "+String.valueOf(l_count_stoyat.size())+"/"+String.valueOf(l_count_today.size()));
                    b3.setText(item_gruppa+" "+String.valueOf(l_count_vse.size()));
                    b2.setText("Сняты "+String.valueOf(l_count_snyati.size())+"/"+count_surprise);
                    b4.setText("Не пришли "+String.valueOf(l_count_today.size())+"/"+String.valueOf(l_not_go.size()));


                }catch (Exception ex){

                }
            }
        }
    }

    private class SyncData_today_old extends AsyncTask<String, String, String> {

        String msg = "Error !";
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
//                    String query_np = "insert into gogo (id_child,id_gruppa,datenotgo,today,surprise) values ('"+get_id_child+"','"+get_id_gruppa+"','"+date_to_db+"','"+reason+"','');";
                    String query_np = "exec Insert_Delete_And_History_So 2,"+get_id_child+","+get_id_gruppa+",'"+date_to_db+"','"+reason+"',0,"+idso_x+"";
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
            new SyncDataBoss().execute();
        }
    }

    private class SyncData_del extends AsyncTask<String, String, String> {

        String msg = "подключитесь к интернету!";
        ProgressDialog progress;
        Dialog progressDialog;

        @Override
        protected void onPreExecute() {

            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();

            l_count_vse.clear();
            l_count_stoyat.clear();
            l_count_snyati.clear();
            l_count_today.clear();
            l_not_go.clear();
            l_opozd.clear();
            l_surprise.clear();

            gruppa1.clear();
            itemsArrayList.clear();
            itemsArrayList2.clear();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
                    Log.d(MSSQL,"success = false");
                }
                else {

                    String query9 = "begin tran exec Insert_Delete_And_History_So 3,"+get_id_child+",0,'"+date_to_db+"','',0,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                    //Log.d("fgt",query9);
//                    Log.d("novoeee",new SimpleDateFormat("yyyy-MM-dd").format(dateAndTime.getTime()));
//                    Log.d("novoeee",query9);
                    Statement stmt9 = conn.createStatement();
                    ResultSet rs9 = stmt9.executeQuery(query9);
                    if (rs9 != null){
                        while (rs9.next()){
                            try {



                                if (rs9.getString("name")!=null){
                                    l_count_vse.add(rs9.getString("name").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name1")!=null){
                                    l_count_stoyat.add(rs9.getString("name1").trim());
                                    gruppa1.add(new ListItemsStoyat(rs9.getString("name1").trim(),rs9.getString("id_child1").trim(),rs9.getInt("id_gruppa"),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"))); //получаем список детей стоят
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name2")!=null){
                                    l_count_snyati.add(rs9.getString("name2").trim());
                                    l_surprise.add(rs9.getString("surprise").trim());
                                    //itemsArrayList.add(new ClassListItems(rs9.getString("name2").trim(),rs9.getString("id_child2").trim(),rs9.getString("prichina").trim(),rs9.getString("surprise").trim(),rs9.getString("start").trim(),rs9.getString("in").trim(),rs9.getString("out").trim(),rs9.getString("id_gruppa").trim()));
                                    itemsArrayList.add(new ClassListItems(rs9.getString("name2").trim(),rs9.getString("id_child2").trim(),rs9.getString("reason").trim(),rs9.getString("surprise").trim(),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"),rs9.getInt("id_gruppa")));

                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name3")!=null){
                                    l_count_today.add(rs9.getString("name3").trim());
                                    //itemsArrayList2.add(new ClassListItems2(rs9.getString("name3").trim(),rs9.getString("id_child3").trim(),rs9.getString("today").trim(),rs9.getString("start").trim(),rs9.getString("in").trim(),rs9.getString("out").trim(),rs9.getString("id_gruppa").trim()));
                                    itemsArrayList2.add(new ClassListItems2(rs9.getString("name3").trim(),rs9.getString("id_child3").trim(),rs9.getString("reason").trim(),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"),rs9.getInt("id_gruppa")));
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }
                                /*if (rs9.getString("today").contains("ОПОЗДАНИЕМ")){
                                    l_opozd.add(rs9.getString("today").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }*/
                                /*if (rs9.getString("today").contains("СОВСЕМ")){
                                    l_not_go.add(rs9.getString("today").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }*/
                                if (rs9.getInt("id")==200) l_not_go.add(rs9.getString("id"));


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
                //Log.d("ooooo","syncdata_tabel");
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

            /*Log.d("fgt", String.valueOf(l_count_vse.size())+" l_count_vse");
            Log.d("fgt", String.valueOf(l_count_stoyat.size())+" l_count_stoyat");
            Log.d("fgt", String.valueOf(l_count_snyati.size())+" l_count_snyati");
            Log.d("fgt", String.valueOf(l_count_today.size())+" l_count_today");

            Log.d("fgt", String.valueOf(l_opozd.size())+" l_opozd");
            Log.d("fgt", String.valueOf(l_not_go.size())+" l_not_go");
            Log.d("fgt", String.valueOf(l_surprise.size())+" l_surprise");*/



            //Toast.makeText(MainActivity.this, msg + "", Toast.LENGTH_SHORT).show();
            if (success == false){

            }else {
                try {

                    myAppAdapter2 = new MyAppAdapter2(gruppa1, getActivity());
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter2);
                    listView.setStackFromBottom(false);

                    sPref = getActivity().getSharedPreferences("item_gruppa",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed2 = sPref.edit();
                    ed2.putString("item_gruppa",item_gruppa);
                    ed2.commit();



                    myAppAdapter = new MyAppAdapter(itemsArrayList, getActivity());
                    int savedPosition = listView2.getFirstVisiblePosition();
                    View firstVisibleView = listView2.getChildAt(0);
                    int savedListTop = (firstVisibleView == null) ? 0 : firstVisibleView.getTop();
                    listView2.setAdapter(myAppAdapter);
                    if (savedPosition >= 0) { //initialized to -1
                        listView2.setSelectionFromTop(savedPosition, savedListTop);
                    }

                    myAppAdapter3 = new MyAppAdapter3(itemsArrayList2, getActivity());
                    //listView3.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView3.setAdapter(myAppAdapter3);
                    listView3.setStackFromBottom(false);


                    m3 = l_surprise.toArray(new String[0]);
                    for(int i=0;i<=m3.length;i++) {
                        if(l_surprise.contains("")) {
                            l_surprise.remove("");
                        }
                    }
                    String [] mm3 = l_surprise.toArray(new String[0]);
                    count_surprise = String.valueOf(mm3.length); //кол-во сюрпризов




                    b1.setText("Поставлены "+String.valueOf(l_count_stoyat.size())+"/"+String.valueOf(l_count_today.size()));
                    b3.setText(item_gruppa+" "+String.valueOf(l_count_vse.size()));
                    b2.setText("Сняты "+String.valueOf(l_count_snyati.size())+"/"+count_surprise);
                    b4.setText("Не пришли "+String.valueOf(l_count_today.size())+"/"+String.valueOf(l_not_go.size()));


                }catch (Exception ex){

                }
            }
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
//                    query_np = "delete gogo where datenotgo='"+date_to_db+"' and id_child='"+get_id_child+"';";

                    query_np = "exec Insert_Delete_And_History_So 3,"+get_id_child+",0,'"+date_to_db+"','',0,"+idso_x+"";
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
            new SyncDataBoss().execute();
        }
    }

    private class SyncData_del_today extends AsyncTask<String, String, String> {

        String msg = "подключитесь к интернету!";
        ProgressDialog progress;
        Dialog progressDialog;

        @Override
        protected void onPreExecute() {

            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();

            l_count_vse.clear();
            l_count_stoyat.clear();
            l_count_snyati.clear();
            l_count_today.clear();
            l_not_go.clear();
            l_opozd.clear();
            l_surprise.clear();

            gruppa1.clear();
            itemsArrayList.clear();
            itemsArrayList2.clear();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
                    Log.d(MSSQL,"success = false");
                }
                else {

                    String query9 = "begin tran exec Insert_Delete_And_History_So 4,"+get_id_child+",0,'"+date_to_db+"','',0,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                    //Log.d("fgt",query9);
//                    Log.d("novoeee",new SimpleDateFormat("yyyy-MM-dd").format(dateAndTime.getTime()));
//                    Log.d("novoeee",query9);
                    Statement stmt9 = conn.createStatement();
                    ResultSet rs9 = stmt9.executeQuery(query9);
                    if (rs9 != null){
                        while (rs9.next()){
                            try {



                                if (rs9.getString("name")!=null){
                                    l_count_vse.add(rs9.getString("name").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name1")!=null){
                                    l_count_stoyat.add(rs9.getString("name1").trim());
                                    gruppa1.add(new ListItemsStoyat(rs9.getString("name1").trim(),rs9.getString("id_child1").trim(),rs9.getInt("id_gruppa"),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"))); //получаем список детей стоят
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name2")!=null){
                                    l_count_snyati.add(rs9.getString("name2").trim());
                                    l_surprise.add(rs9.getString("surprise").trim());
                                    //itemsArrayList.add(new ClassListItems(rs9.getString("name2").trim(),rs9.getString("id_child2").trim(),rs9.getString("prichina").trim(),rs9.getString("surprise").trim(),rs9.getString("start").trim(),rs9.getString("in").trim(),rs9.getString("out").trim(),rs9.getString("id_gruppa").trim()));
                                    itemsArrayList.add(new ClassListItems(rs9.getString("name2").trim(),rs9.getString("id_child2").trim(),rs9.getString("reason").trim(),rs9.getString("surprise").trim(),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"),rs9.getInt("id_gruppa")));

                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name3")!=null){
                                    l_count_today.add(rs9.getString("name3").trim());
                                    //itemsArrayList2.add(new ClassListItems2(rs9.getString("name3").trim(),rs9.getString("id_child3").trim(),rs9.getString("today").trim(),rs9.getString("start").trim(),rs9.getString("in").trim(),rs9.getString("out").trim(),rs9.getString("id_gruppa").trim()));
                                    itemsArrayList2.add(new ClassListItems2(rs9.getString("name3").trim(),rs9.getString("id_child3").trim(),rs9.getString("reason").trim(),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"),rs9.getInt("id_gruppa")));
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }
                                /*if (rs9.getString("today").contains("ОПОЗДАНИЕМ")){
                                    l_opozd.add(rs9.getString("today").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }*/
                                /*if (rs9.getString("today").contains("СОВСЕМ")){
                                    l_not_go.add(rs9.getString("today").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }*/
                                if (rs9.getInt("id")==200) l_not_go.add(rs9.getString("id"));


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
                //Log.d("ooooo","syncdata_tabel");
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

            /*Log.d("fgt", String.valueOf(l_count_vse.size())+" l_count_vse");
            Log.d("fgt", String.valueOf(l_count_stoyat.size())+" l_count_stoyat");
            Log.d("fgt", String.valueOf(l_count_snyati.size())+" l_count_snyati");
            Log.d("fgt", String.valueOf(l_count_today.size())+" l_count_today");

            Log.d("fgt", String.valueOf(l_opozd.size())+" l_opozd");
            Log.d("fgt", String.valueOf(l_not_go.size())+" l_not_go");
            Log.d("fgt", String.valueOf(l_surprise.size())+" l_surprise");*/



            //Toast.makeText(MainActivity.this, msg + "", Toast.LENGTH_SHORT).show();
            if (success == false){

            }else {
                try {

                    myAppAdapter2 = new MyAppAdapter2(gruppa1, getActivity());
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter2);
                    listView.setStackFromBottom(false);

                    sPref = getActivity().getSharedPreferences("item_gruppa",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed2 = sPref.edit();
                    ed2.putString("item_gruppa",item_gruppa);
                    ed2.commit();



                    myAppAdapter = new MyAppAdapter(itemsArrayList, getActivity());
                    int savedPosition = listView2.getFirstVisiblePosition();
                    View firstVisibleView = listView2.getChildAt(0);
                    int savedListTop = (firstVisibleView == null) ? 0 : firstVisibleView.getTop();
                    listView2.setAdapter(myAppAdapter);
                    if (savedPosition >= 0) { //initialized to -1
                        listView2.setSelectionFromTop(savedPosition, savedListTop);
                    }

                    myAppAdapter3 = new MyAppAdapter3(itemsArrayList2, getActivity());
                    //listView3.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView3.setAdapter(myAppAdapter3);
                    listView3.setStackFromBottom(false);


                    m3 = l_surprise.toArray(new String[0]);
                    for(int i=0;i<=m3.length;i++) {
                        if(l_surprise.contains("")) {
                            l_surprise.remove("");
                        }
                    }
                    String [] mm3 = l_surprise.toArray(new String[0]);
                    count_surprise = String.valueOf(mm3.length); //кол-во сюрпризов




                    b1.setText("Поставлены "+String.valueOf(l_count_stoyat.size())+"/"+String.valueOf(l_count_today.size()));
                    b3.setText(item_gruppa+" "+String.valueOf(l_count_vse.size()));
                    b2.setText("Сняты "+String.valueOf(l_count_snyati.size())+"/"+count_surprise);
                    b4.setText("Не пришли "+String.valueOf(l_count_today.size())+"/"+String.valueOf(l_not_go.size()));


                }catch (Exception ex){

                }
            }
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
//                    query_np = "delete gogo where datenotgo='"+date_to_db+"' and id_child='"+get_id_child+"' and id in (100,200)";

                    query_np = "exec Insert_Delete_And_History_So 4,"+get_id_child+",0,'"+date_to_db+"','',0,"+idso_x+"";
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
            new SyncDataBoss().execute();
        }
    }

    public void show_dialog (int id) {
        reason = "";
        switch (id) {
            case V_NORMAL:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Выберите причину (снять с питания) на "+date_in_dialog);
                builder.setItems(bolez, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item==0){reason=calendarFragment.a;}
                        if (item==1){reason=calendarFragment.b;}
                        if (item==2){reason=calendarFragment.c;}
                        if (item==3){reason=calendarFragment.d;}
                        if (item==4){reason=calendarFragment.f;}
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
                        if (item==0){reason=calendarFragment.a1;}
                        if (item==1){reason=calendarFragment.b1;}
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
            case V_TODAY2://update
                AlertDialog.Builder builder10 = new AlertDialog.Builder(getActivity());
                builder10.setTitle("Выберите событие на "+date_in_dialog);
                builder10.setItems(prishel_massive3, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item==0){
                            reason=calendarFragment.a1;
//                            query_update_new = "update gogo set today='"+reason+"',id=100 where id_child = '"+get_id_child+"' and datenotgo = '"+date+"'";

                            //Insert_Delete_And_History_So @check int, @id_child int, @id_gruppa int, @datenotgo date, @reason varchar(max), @id_reason int, @id_x int as
                            query_update_new = "begin tran exec Insert_Delete_And_History_So 6,"+get_id_child+",0,'"+date+"','"+reason+"',100,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                            new Update_gogo().execute();
                        }
                        if (item==1){
                            reason=calendarFragment.b1;
//                            query_update_new = "update gogo set today='"+reason+"',id=200 where id_child = '"+get_id_child+"' and datenotgo = '"+date+"'";
                            query_update_new = "begin tran exec Insert_Delete_And_History_So 6,"+get_id_child+",0,'"+date+"','"+reason+"',200,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                            new Update_gogo().execute();
                        }
                        if (item==3){
                            reason=calendarFragment.b1;
                            new SyncData_del_today().execute();
                        }

                        dialog.cancel();
                    }
                });
                AlertDialog alertDialogObject10 = builder10.create();
                ListView listView10=alertDialogObject10.getListView();
                listView10.setDivider(new ColorDrawable(Color.GRAY)); // set color
                listView10.setDividerHeight(1); // set height
                alertDialogObject10.setCancelable(true);
                alertDialogObject10.show();
                break;
            case MED:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle("Выберите причину на "+date_in_dialog);
                builder2.setItems(bolez3, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item==0){reason=calendarFragment.a;new SyncData_snyat().execute();}
                        if (item==1){reason=calendarFragment.b;new SyncData_snyat().execute();}
                        if (item==2){reason=calendarFragment.c;new SyncData_snyat().execute();}
                        if (item==3){reason=calendarFragment.d;new SyncData_snyat().execute();}
                        if (item==4){reason=calendarFragment.f;new SyncData_snyat().execute();}
                        if (item==5){reason=calendarFragment.a1;new SyncData_today().execute();}
                        if (item==6){reason=calendarFragment.b1;new SyncData_today().execute();}
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
            case SURPRISE:
                AlertDialog.Builder builder3 = new AlertDialog.Builder(getActivity());
                builder3.setTitle("Выберите событие на "+date_in_dialog);
                builder3.setItems(prishel_massive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item==0){
                            new Update_surprise().execute();
                        }
                        if (item==1){
                            new Update_surpriseUN().execute();
                        }
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialogObject3 = builder3.create();
                ListView listView3=alertDialogObject3.getListView();
//                listView3.getChildAt(1).setBackgroundColor(Color.BLUE);
                listView3.setDivider(new ColorDrawable(Color.GRAY)); // set color
                listView3.setDividerHeight(1); // set height
                alertDialogObject3.setCancelable(true);
                alertDialogObject3.show();
                break;
            case SURPRISE2:
                AlertDialog.Builder builder4 = new AlertDialog.Builder(getActivity());
                builder4.setTitle("Выберите событие на "+date_in_dialog);
                builder4.setItems(prishel_massive2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item==0){
                            new Update_surprise().execute();
                        }
                        if (item==1){
                            new Update_surpriseUN().execute();
                        }
                        if (item==3){
                            get_id_child = id_child_surprise_1;
                            new SyncData_del().execute();
                        }

                        dialog.cancel();
                    }
                });
                AlertDialog alertDialogObject4 = builder4.create();
                ListView listView4=alertDialogObject4.getListView();
                listView4.setDivider(new ColorDrawable(Color.GRAY)); // set color
                listView4.setDividerHeight(1); // set height

                alertDialogObject4.setCancelable(true);
                alertDialogObject4.show();
                break;
            case MED2://update
                AlertDialog.Builder builder5 = new AlertDialog.Builder(getActivity());
                builder5.setTitle("Выберите событие на "+date_in_dialog);
                builder5.setItems(bolez4, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item==0){
                            reason=calendarFragment.a;
//                            query_update_new = "update gogo set prichina='"+reason+"',id=0 where id_child = '"+get_id_child+"' and datenotgo = '"+date+"'";
                            query_update_new = "begin tran exec Insert_Delete_And_History_So 5,"+get_id_child+",0,'"+date+"','"+reason+"',0,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                            new Update_gogo().execute();
                        }
                        if (item==1){
                            reason=calendarFragment.b;
//                            query_update_new = "update gogo set prichina='"+reason+"',id=4 where id_child = '"+get_id_child+"' and datenotgo = '"+date+"'";
                            query_update_new = "begin tran exec Insert_Delete_And_History_So 5,"+get_id_child+",0,'"+date+"','"+reason+"',4,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                            new Update_gogo().execute();
                        }
                        if (item==2){
                            reason=calendarFragment.c;
//                            query_update_new = "update gogo set prichina='"+reason+"',id=20 where id_child = '"+get_id_child+"' and datenotgo = '"+date+"'";
                            query_update_new = "begin tran exec Insert_Delete_And_History_So 5,"+get_id_child+",0,'"+date+"','"+reason+"',20,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                            new Update_gogo().execute();
                        }
                        if (item==3){
                            reason=calendarFragment.d;
//                            query_update_new = "update gogo set prichina='"+reason+"',id=6 where id_child = '"+get_id_child+"' and datenotgo = '"+date+"'";
                            query_update_new = "begin tran exec Insert_Delete_And_History_So 5,"+get_id_child+",0,'"+date+"','"+reason+"',6,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                            new Update_gogo().execute();
                        }
                        if (item==4){
                            reason=calendarFragment.f;
//                            query_update_new = "update gogo set prichina='"+reason+"',id=28 where id_child = '"+get_id_child+"' and datenotgo = '"+date+"'";
                            query_update_new = "begin tran exec Insert_Delete_And_History_So 5,"+get_id_child+",0,'"+date+"','"+reason+"',28,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                            new Update_gogo().execute();
                        }
                        if (item==5){
                            reason=calendarFragment.a1;
//                            query_update_new = "update gogo set today='"+reason+"',id=100 where id_child = '"+get_id_child+"' and datenotgo = '"+date+"'";
                            query_update_new = "begin tran exec Insert_Delete_And_History_So 6,"+get_id_child+",0,'"+date+"','"+reason+"',100,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                            new Update_gogo().execute();
                        }
                        if (item==6){
                            reason=calendarFragment.b1;
//                            query_update_new = "update gogo set today='"+reason+"',id=200 where id_child = '"+get_id_child+"' and datenotgo = '"+date+"'";
                            query_update_new = "begin tran exec Insert_Delete_And_History_So 6,"+get_id_child+",0,'"+date+"','"+reason+"',200,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                            new Update_gogo().execute();
                        }
                        if (item==8){
                            reason=calendarFragment.b1;
                            new SyncData_del_today().execute();
                        }

                        dialog.cancel();
                    }
                });
                AlertDialog alertDialogObject5 = builder5.create();
                ListView listView5=alertDialogObject5.getListView();
                listView5.setDivider(new ColorDrawable(Color.GRAY)); // set color
                listView5.setDividerHeight(1); // set height
                alertDialogObject5.setCancelable(true);
                alertDialogObject5.show();
                break;
            case MED3://update
                AlertDialog.Builder builder6 = new AlertDialog.Builder(getActivity());
                builder6.setTitle("Выберите событие на "+date_in_dialog);
                builder6.setItems(bolez_med_red, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        get_id_child = id_child_surprise_1;
                        if (item==0){
                            new Update_surprise().execute();
                        }
                        if (item==1){
                            new Update_surpriseUN().execute();
                        }
                        if (item==3){
                            reason=calendarFragment.a;
//                            query_update_new = "update gogo set prichina='"+reason+"',id=0 where id_child = '"+get_id_child+"' and datenotgo = '"+date+"'";
                            query_update_new = "begin tran exec Insert_Delete_And_History_So 5,"+get_id_child+",0,'"+date+"','"+reason+"',0,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                            new Update_gogo().execute();
                        }
                        if (item==4){
                            reason=calendarFragment.b;
//                            query_update_new = "update gogo set prichina='"+reason+"',id=4 where id_child = '"+get_id_child+"' and datenotgo = '"+date+"'";
                            query_update_new = "begin tran exec Insert_Delete_And_History_So 5,"+get_id_child+",0,'"+date+"','"+reason+"',4,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                            new Update_gogo().execute();
                        }
                        if (item==5){
                            reason=calendarFragment.c;
//                            query_update_new = "update gogo set prichina='"+reason+"',id=20 where id_child = '"+get_id_child+"' and datenotgo = '"+date+"'";
                            query_update_new = "begin tran exec Insert_Delete_And_History_So 5,"+get_id_child+",0,'"+date+"','"+reason+"',20,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                            new Update_gogo().execute();
                        }
                        if (item==6){
                            reason=calendarFragment.d;
//                            query_update_new = "update gogo set prichina='"+reason+"',id=6 where id_child = '"+get_id_child+"' and datenotgo = '"+date+"'";
                            query_update_new = "begin tran exec Insert_Delete_And_History_So 5,"+get_id_child+",0,'"+date+"','"+reason+"',6,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                            new Update_gogo().execute();
                        }
                        if (item==7){
                            reason=calendarFragment.f;
//                            query_update_new = "update gogo set prichina='"+reason+"',id=28 where id_child = '"+get_id_child+"' and datenotgo = '"+date+"'";
                            query_update_new = "begin tran exec Insert_Delete_And_History_So 5,"+get_id_child+",0,'"+date+"','"+reason+"',28,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                            new Update_gogo().execute();
                        }
                        if (item==8){
                            reason=calendarFragment.a1;
//                            query_update_new = "update gogo set today='"+reason+"',id=100 where id_child = '"+get_id_child+"' and datenotgo = '"+date+"'";
                            query_update_new = "begin tran exec Insert_Delete_And_History_So 6,"+get_id_child+",0,'"+date+"','"+reason+"',100,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                            new Update_gogo().execute();
                        }
                        if (item==9){
                            reason=calendarFragment.b1;
//                            query_update_new = "update gogo set today='"+reason+"',id=200 where id_child = '"+get_id_child+"' and datenotgo = '"+date+"'";
                            query_update_new = "begin tran exec Insert_Delete_And_History_So 6,"+get_id_child+",0,'"+date+"','"+reason+"',200,"+idso_x+"; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                            new Update_gogo().execute();
                        }
                        if (item==11){
                            new SyncData_del().execute();
                        }

                        dialog.cancel();
                    }
                });
                AlertDialog alertDialogObject6 = builder6.create();
                ListView listView6=alertDialogObject6.getListView();
                listView6.setDivider(new ColorDrawable(Color.GRAY)); // set color
                listView6.setDividerHeight(1); // set height
                alertDialogObject6.setCancelable(true);
                alertDialogObject6.show();
                break;




        }
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

    @Override
    public void onResume() {
        super.onResume();

        sPref = getActivity().getSharedPreferences("idso_x",Context.MODE_PRIVATE);
        idso_x = sPref.getInt("idso_x", 0);

        stringList = new ArrayList<>();
        stringList_work = new ArrayList<>();
        stringList = getArrayList("array_holidays");
        stringList_work = getArrayList("array_work");

//        new SyncData_get_array_holidays().execute();
        dateAndTime = Calendar.getInstance();
        dateAndTime2=Calendar.getInstance();


        currentDateTime.setText(DateUtils.formatDateTime(getActivity(),dateAndTime2.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_WEEKDAY));

        SimpleDateFormat dateFormat1 = new SimpleDateFormat("EE, d MMM yyyy", Locale.getDefault());
        String vramya = dateFormat1.format(dateAndTime2.getTimeInMillis());
        currentDateTime.setText(vramya);


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        date = dateFormat.format(dateAndTime2.getTimeInMillis()).toString();








        Integer so = 0;
        sPref = getActivity().getSharedPreferences("so",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed29 = sPref.edit();
        ed29.putInt("so",so);
        ed29.apply();
        //Log.d("datadate",date);

        Date currentDate2 = new Date();
        SimpleDateFormat dateFormat_day = new SimpleDateFormat("d", Locale.getDefault());
        today_segodnya = dateFormat_day.format(currentDate2);
        today_segodnyaINT = Integer.valueOf(today_segodnya);



        sPref = getActivity().getSharedPreferences("boss_start",Context.MODE_PRIVATE);
        boss_start = sPref.getInt("boss_start", 0);

        /*if (boss_start==555){
            SimpleDateFormat dateFormat_year = new SimpleDateFormat("yyyy", Locale.getDefault());
            SimpleDateFormat dateFormat_month44 = new SimpleDateFormat("M", Locale.getDefault());
            i_current_year = Integer.valueOf(dateFormat_year.format(currentDate2));
            i_current_month = Integer.valueOf(dateFormat_month44.format(currentDate2));

            Log.d("fgt",today_segodnyaINT+"***"+i_current_month+"***"+i_current_year);


            if (i_current_month!=1){
                i_current_month = i_current_month-1;
                sPref = getActivity().getSharedPreferences("i_create_table_deti"+i_current_month+i_current_year,Context.MODE_PRIVATE);
                i_create_table_deti = sPref.getInt("i_create_table_deti"+i_current_month+i_current_year, 0);
                if (i_create_table_deti==0){
                    new Create_table_deti().execute();
                }else {
                    Log.d("fgt","*******************555*********************");
                }
                Log.d("fgt",today_segodnyaINT+"***"+i_current_month+"***"+i_current_year);
            }else if (i_current_month==1){
                i_current_month = 12;
                i_current_year = i_current_year-1;
                sPref = getActivity().getSharedPreferences("i_create_table_deti"+i_current_month+i_current_year,Context.MODE_PRIVATE);
                i_create_table_deti = sPref.getInt("i_create_table_deti"+i_current_month+i_current_year, 0);
                if (i_create_table_deti==0){
                    new Create_table_deti().execute();
                }else {
                    Log.d("fgt","*******************555*********************");
                }
                Log.d("fgt",today_segodnyaINT+"***"+i_current_month+"***"+i_current_year);
            }else {
                Log.d("fgt","сегодня не 1 число месяца");
            }

        }*/









        sPref = getActivity().getSharedPreferences("flagCalendar",Context.MODE_PRIVATE);
        flagCalendar = sPref.getInt("flagCalendar", 0);



        if (flagCalendar==1){
            sPref = getActivity().getSharedPreferences("vramya",Context.MODE_PRIVATE);
            String vramya2 = sPref.getString("vramya", "");
            currentDateTime.setText(vramya2);

            sPref = getActivity().getSharedPreferences("date",Context.MODE_PRIVATE);
            date = sPref.getString("date", "");

            sPref = getActivity().getSharedPreferences("dateday",Context.MODE_PRIVATE);
            dateday = sPref.getString("dateday", "");

            sPref = getActivity().getSharedPreferences("datemonth",Context.MODE_PRIVATE);
            datemonth = sPref.getString("datemonth", "");
            datemonthINT = Integer.valueOf(datemonth);

            Log.d("prov3",datemonth);

            flagCalendar = 0;
            sPref = getActivity().getSharedPreferences("flagCalendar",Context.MODE_PRIVATE);
            SharedPreferences.Editor ed2 = sPref.edit();
            ed2.putInt("flagCalendar",flagCalendar);
            ed2.apply();

            Log.d("fgt","eee");
        }else {
            //setInitialDateTime();
            Log.d("fgt","rrr");

            SimpleDateFormat dateFormat10 = new SimpleDateFormat("EE, d MMM yyyy", Locale.getDefault());
            String vramya10 = dateFormat10.format(dateAndTime2.getTimeInMillis());
            currentDateTime.setText(vramya10);

            SimpleDateFormat dateFormat44 = new SimpleDateFormat("M", Locale.getDefault());
            datemonth = dateFormat44.format(dateAndTime2.getTimeInMillis()).toString();
            datemonthINT = Integer.valueOf(datemonth);
            dateday = new SimpleDateFormat("dd", Locale.getDefault()).format(dateAndTime2.getTimeInMillis()).toString();

            Log.d("fgt",datemonth);

            //currentDateTime.setText(DateUtils.formatDateTime(this,dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_WEEKDAY));
            SimpleDateFormat dateFormat88 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            date = dateFormat88.format(dateAndTime2.getTimeInMillis()).toString();
            Log.d("fgt",date);

            IntDayOfWeek = dateAndTime2.get(Calendar.DAY_OF_WEEK);
            sPref = getActivity().getSharedPreferences("IntDayOfWeek",Context.MODE_PRIVATE);
            SharedPreferences.Editor ed99 = sPref.edit();
            ed99.putInt("IntDayOfWeek",IntDayOfWeek);
            ed99.apply();


        }


        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float yInches= metrics.heightPixels/metrics.ydpi;
        float xInches= metrics.widthPixels/metrics.xdpi;
        diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);

        flag1 = 1;
        sPref = getActivity().getSharedPreferences("flag1",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed2 = sPref.edit();
        ed2.putInt("flag1",flag1);
        ed2.apply();

        Date currentDate = new Date();
        SimpleDateFormat dateFormat_month = new SimpleDateFormat("M", Locale.getDefault());
        month = dateFormat_month.format(currentDate);
        mon = Integer.valueOf(month);

        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");



        sPref = getActivity().getSharedPreferences("flag_begin",Context.MODE_PRIVATE);
        flag_begin = sPref.getInt("flag_begin", 0);

        if (flag_begin==0){
            Log.d("fgt","flag_begin = 0");
            Log.d("fgt",login);
            group_start();
        }else {
            Log.d("fgt","flag_begin = 1");
            all_start();
        }

        //Log.d("datadate",date);

        Log.d("tabelboss",String.valueOf(boss_start));

        final float f1 = 0.9f;
        final float f11 = 0.97f;
        final float f2 = 1f;

        currentDateTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        currentDateTime.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        currentDateTime.animate().setDuration(1).scaleX(f11).scaleY(f1).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        currentDateTime.animate().setDuration(1).scaleX(f2).scaleY(f2).start();
                        setDate();
                        break;
                }
                return true;
            }
        });

        b1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        b1.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        b1.animate().setDuration(1).scaleX(f11).scaleY(f1).start();
                        //setDialog(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        b1.animate().setDuration(1).scaleX(f2).scaleY(f2).start();
                        //setDialog(b1);
                        all_start();
                        /*if (rotateLoading.isStart()){
                            rotateLoading.stop();
                        }else {
                            rotateLoading.start();
                        }*/
                        break;
                }
                return true;
            }
        });

        b2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        b2.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        b2.animate().setDuration(1).scaleX(f11).scaleY(f1).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        b2.animate().setDuration(1).scaleX(f2).scaleY(f2).start();
                        if (boss_start==555)startActivity(new Intent(getActivity(), Vremenno.class));
                        //startActivity(new Intent(getActivity(),Vrem.class));
                        //all_start();
                        break;
                }
                return true;
            }
        });

        b3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        b3.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        b3.animate().setDuration(1).scaleX(f11).scaleY(f1).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        b3.animate().setDuration(1).scaleX(f2).scaleY(f2).start();
                        dialog_group();
                        break;
                }
                return true;
            }
        });

        b4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        b4.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        b4.animate().setDuration(1).scaleX(f11).scaleY(f1).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        b4.animate().setDuration(1).scaleX(f2).scaleY(f2).start();
                        if (boss_start==555)startActivity(new Intent(getActivity(), GruppiZdor.class));
                        break;
                }
                return true;
            }
        });

        Log.d("fgt","datemonthINT RESUME "+datemonthINT);

    }

    // отображаем диалоговое окно для выбора даты
    public void setDate() {
        new DatePickerDialog(getActivity(), d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // установка начальных даты и времени
    private void setInitialDateTime() {

        currentDateTime.setText(DateUtils.formatDateTime(getActivity(),dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_WEEKDAY));

        SimpleDateFormat dateFormat1 = new SimpleDateFormat("EE, d MMM yyyy", Locale.getDefault());
        String vramya = dateFormat1.format(dateAndTime.getTimeInMillis());
        currentDateTime.setText(vramya);


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        date = dateFormat.format(dateAndTime.getTimeInMillis()).toString();

        SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd", Locale.getDefault());
        dateday = dateFormat3.format(dateAndTime.getTimeInMillis()).toString();
        SimpleDateFormat dateFormat4 = new SimpleDateFormat("M", Locale.getDefault());
        datemonth = dateFormat4.format(dateAndTime.getTimeInMillis()).toString();
        datemonthINT = Integer.valueOf(datemonth);

        Integer IntDayOfWeek = null;
        IntDayOfWeek = dateAndTime.get(Calendar.DAY_OF_WEEK);
        sPref = getActivity().getSharedPreferences("IntDayOfWeek",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed99 = sPref.edit();
        ed99.putInt("IntDayOfWeek",IntDayOfWeek);
        ed99.apply();

    }

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();

            Log.d("prov3",datemonth+"    setInitialDateTime");

            all_start();
            //start2();
            //group_start();
        }
    };

    /*public void all_start555(){
        new SyncDataBoss().execute();
    }*/

    public void all_start(){
//        String date_checking = new SimpleDateFormat("yyyy-MM-dd").format(dateAndTime.getTime());
//        Log.v("FATAL",date_checking+" date_checking");
        if ((dateAndTime.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || dateAndTime.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || stringList.contains(new SimpleDateFormat("yyyy-MM-dd").format(dateAndTime.getTime())))&&!stringList_work.contains(new SimpleDateFormat("yyyy-MM-dd").format(dateAndTime.getTime()))){
            Log.v("FATAL"," holidays");
            Toast.makeText(getActivity(), holholmsg, Toast.LENGTH_SHORT).show();
            listView.setAdapter(null);
            listView2.setAdapter(null);
            listView3.setAdapter(null);
            b4.setAlpha(1.0f);
            b4.animate()
                    .alpha(0f)
                    .setDuration(1800)
                    .setInterpolator(new FastOutLinearInInterpolator());
       // }

        /*sPref = getActivity().getSharedPreferences("IntDayOfWeek",Context.MODE_PRIVATE);
        IntDayOfWeek = sPref.getInt("IntDayOfWeek", 0);
        Log.d("tyr11",String.valueOf(IntDayOfWeek)+" - IntDayOfWeek");
        Integer hd = Integer.valueOf(dateday);
        Integer hm = Integer.valueOf(datemonth);
        Log.d("tyr11",String.valueOf(hd)+" - dateday");
        Log.d("tyr11",String.valueOf(hm)+" - datemonth");*/

        /*progress1 = 0;
        progress2 = 0;
        progress3 = 0;
        progress4 = 0;*/



        /*if (IntDayOfWeek==1||IntDayOfWeek==7||hm==3&&hd==8||hm==5&&(hd==1||hd==2||hd==3||hd==9||hd==10)||hm==6&&hd==12||hm==11&&hd==4){
            Toast.makeText(getActivity(), holholmsg, Toast.LENGTH_SHORT).show();
            listView.setAdapter(null);
            listView2.setAdapter(null);
            listView3.setAdapter(null);
            b4.setAlpha(1.0f);
            b4.animate()
                    .alpha(0f)
                    .setDuration(1800)
                    .setInterpolator(new FastOutLinearInInterpolator());*/

            //b4.setVisibility(View.INVISIBLE);
        }else {
            b4.setAlpha(0f);
            b4.animate()
                    .alpha(1.0f)
                    .setDuration(1800)
                    .setInterpolator(new FastOutLinearInInterpolator());
            //b4.setVisibility(View.VISIBLE);

            gruppa1.clear();
            itemsArrayList.clear();
            itemsArrayList2.clear();

            /*g1.clear();
            g2.clear();
            g3.clear();
            g4.clear();
            g5.clear();*/

            b1.setText("Поставлены");
            b2.setText("Сняты");
            b3.setText(item_gruppa);
            b4.setText("Не пришли");

            /*count_notgo2 = "0";
            count_notgo = "0";
            count_surprise = "0";
            gm1 = "0";
            gm2 = "0";
            count_gr = "";*/

            /*progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();

            new SyncData().execute();
            new SyncData2().execute();
            new SyncData3().execute();
            new SyncData4().execute();*/

            l_count_vse = new ArrayList<>();
            l_count_stoyat = new ArrayList<>();
            l_count_snyati = new ArrayList<>();
            l_count_today = new ArrayList<>();
            l_not_go = new ArrayList<>();
            l_opozd = new ArrayList<>();
            l_surprise = new ArrayList<>();

            new SyncDataBoss().execute();

        }
    }

    public void group_start(){
        Start_gruppa syncData = new Start_gruppa();
        syncData.execute("");
    }

    public void finding(){
        Find syncData = new Find();
        syncData.execute("");
    }

    public void cont_show(){
        Cont syncData = new Cont();
        syncData.execute("");
    }

    public void cont_insert(){
        Insert_add_cont syncData = new Insert_add_cont();
        syncData.execute("");
    }

    public void cont_update(){
        Update_cont syncData = new Update_cont();
        syncData.execute("");
    }

    public void cont_delete(){
        Delete_cont syncData = new Delete_cont();
        syncData.execute("");
    }

    public void dialog_group_start(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите группу :");
        builder.setItems(mgruppa, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                item_gruppa = mgruppa[item];
                b3.setText(item_gruppa);
                sPref = getActivity().getSharedPreferences("item_gruppa",Context.MODE_PRIVATE);
                SharedPreferences.Editor ed2 = sPref.edit();
                ed2.putString("item_gruppa",item_gruppa);
                ed2.apply();
                Log.d("item_gruppa",item_gruppa +"222222222222");

                int id_gruppa = item+1;
                sPref = getActivity().getSharedPreferences("id_gruppa",Context.MODE_PRIVATE);
                SharedPreferences.Editor ed_id_gruppa = sPref.edit();
                ed_id_gruppa.putInt("id_gruppa",id_gruppa);
                ed_id_gruppa.apply();

                all_start();
                //Toast.makeText(getActivity(), "***"+item_gruppa+"***", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        AlertDialog alertDialogObject = builder.create();
        ListView listView=alertDialogObject.getListView();
        listView.setDivider(new ColorDrawable(Color.GRAY)); // set color
        listView.setDividerHeight(1); // set height
        alertDialogObject.setCancelable(false);
        alertDialogObject.show();
    }




    public void perehod_month_sotr_if_yes_pincot(){

        int so = 777;
        sPref = getActivity().getSharedPreferences("so",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed2 = sPref.edit();
        ed2.putInt("so",so);
        ed2.apply();

        /*if (datemonthINT==2 || datemonthINT==1){
            startActivity(new Intent(getActivity(), Feb.class));
        }*/


    }

    public void dialog_otchety(){
        String[]otche={"Детодни","ОРЗ"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите отчёт :");
        builder.setItems(otche, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item){
                    case 0:
                        startActivity(new Intent(getActivity(), Otchet5_18_07_19.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), Itogo.class));
                        break;
                }
                dialog.cancel();
            }
        });
        AlertDialog alertDialogObject = builder.create();
        ListView listView=alertDialogObject.getListView();
        listView.setDivider(new ColorDrawable(Color.GRAY)); // set color
        listView.setDividerHeight(1); // set height
        alertDialogObject.setCancelable(true);
        alertDialogObject.show();
    }

    public void dialog_journal(){
        String[]journal={"р.Манту","Гельминты"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите журнал :");
        builder.setItems(journal, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item){
                    case 0:
                        startActivity(new Intent(getActivity(), Mantu.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), Kal.class));
                        break;
                }
                dialog.cancel();
            }
        });
        AlertDialog alertDialogObject = builder.create();
        ListView listView=alertDialogObject.getListView();
        listView.setDivider(new ColorDrawable(Color.GRAY)); // set color
        listView.setDividerHeight(1); // set height
        alertDialogObject.setCancelable(true);
        alertDialogObject.show();
    }

    public void dialog_history(){
        String[]journal={"История отметок","История переводов в группы"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите историю :");
        builder.setItems(journal, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item){
                    case 0:
                        startActivity(new Intent(getActivity(), History.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), History_perevod_gruppa.class));
                        break;
                }
                dialog.cancel();
            }
        });
        AlertDialog alertDialogObject = builder.create();
        ListView listView=alertDialogObject.getListView();
        listView.setDivider(new ColorDrawable(Color.GRAY)); // set color
        listView.setDividerHeight(1); // set height
        alertDialogObject.setCancelable(true);
        alertDialogObject.show();
    }

    public void dialog_antropometry(){
        String[]otche={"Журнал учета рост/вес","Мебель"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите раздел :");
        builder.setItems(otche, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item){
                    case 0:
                        startActivity(new Intent(getActivity(), Antropometria2.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), Antropometria.class));
                        break;
                }
                dialog.cancel();
            }
        });
        AlertDialog alertDialogObject = builder.create();
        ListView listView=alertDialogObject.getListView();
        listView.setDivider(new ColorDrawable(Color.GRAY)); // set color
        listView.setDividerHeight(1); // set height
        alertDialogObject.setCancelable(true);
        alertDialogObject.show();
    }

    public void dialog_group(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите группу :");
        builder.setItems(mgruppa, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                item_gruppa = mgruppa[item];
                b3.setText(item_gruppa);
                sPref = getActivity().getSharedPreferences("item_gruppa",Context.MODE_PRIVATE);
                SharedPreferences.Editor ed2 = sPref.edit();
                ed2.putString("item_gruppa",item_gruppa);
                ed2.apply();

                int id_gruppa = item+1;
                sPref = getActivity().getSharedPreferences("id_gruppa",Context.MODE_PRIVATE);
                SharedPreferences.Editor ed_id_gruppa = sPref.edit();
                ed_id_gruppa.putInt("id_gruppa",id_gruppa);
                ed_id_gruppa.apply();
                Log.d("item_gruppa",item_gruppa +"3333333333333");

                all_start();
                //Toast.makeText(getActivity(), "***"+item_gruppa+"***", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        AlertDialog alertDialogObject = builder.create();
        ListView listView=alertDialogObject.getListView();
        listView.setDivider(new ColorDrawable(Color.GRAY)); // set color
        listView.setDividerHeight(1); // set height
        alertDialogObject.setCancelable(true);
        alertDialogObject.show();
    }

    public void dialog_group_add_cont(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите группу :");
        builder.setItems(mgruppa, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                item_gruppa_add_cont = mgruppa[item];
                add_name_gruppa_cont.setText(item_gruppa_add_cont);
                dialog.cancel();
            }
        });
        AlertDialog alertDialogObject = builder.create();
        ListView listView=alertDialogObject.getListView();
        listView.setDivider(new ColorDrawable(Color.GRAY)); // set color
        listView.setDividerHeight(1); // set height
        alertDialogObject.setCancelable(true);
        alertDialogObject.show();
    }

    public void dialog_group_add_cont2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите группу :");
        builder.setItems(mgruppa, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                item_gruppa_add_cont = mgruppa[item];
                edit_name_gruppa_cont.setText(item_gruppa_add_cont);
                dialog.cancel();
            }
        });
        AlertDialog alertDialogObject = builder.create();
        ListView listView=alertDialogObject.getListView();
        listView.setDivider(new ColorDrawable(Color.GRAY)); // set color
        listView.setDividerHeight(1); // set height
        alertDialogObject.setCancelable(true);
        alertDialogObject.show();
    }

    public void dialog_dol(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите должность :");
        builder.setItems(doljnost, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                item_dol = doljnost[item];
                add_dol.setText(item_dol);

                dialog.cancel();
            }
        });
        AlertDialog alertDialogObject = builder.create();
        ListView listView=alertDialogObject.getListView();
        listView.setDivider(new ColorDrawable(Color.GRAY)); // set color
        listView.setDividerHeight(1); // set height
        alertDialogObject.setCancelable(true);
        alertDialogObject.show();
    }



    public void dialog_dol2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите должность :");
        builder.setItems(doljnost, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                item_dol = doljnost[item];
                edit_dol.setText(item_dol);

                dialog.cancel();
            }
        });
        AlertDialog alertDialogObject = builder.create();
        ListView listView=alertDialogObject.getListView();
        listView.setDivider(new ColorDrawable(Color.GRAY)); // set color
        listView.setDividerHeight(1); // set height
        alertDialogObject.setCancelable(true);
        alertDialogObject.show();
    }

    private class SyncDataBoss extends AsyncTask<String, String, String> {

        String msg = "подключитесь к интернету!";
        ProgressDialog progress;



        Dialog progressDialog;




        @Override
        protected void onPreExecute() {

            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();

            l_count_vse.clear();
            l_count_stoyat.clear();
            l_count_snyati.clear();
            l_count_today.clear();
            l_not_go.clear();
            l_opozd.clear();
            l_surprise.clear();

            gruppa1.clear();
            itemsArrayList.clear();
            itemsArrayList2.clear();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
                    Log.d(MSSQL,"success = false");
                }
                else {

                    String query9 = "exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'";
                    //Log.d("fgt",query9);
//                    Log.d("novoeee",new SimpleDateFormat("yyyy-MM-dd").format(dateAndTime.getTime()));
//                    Log.d("novoeee",query9);
                    Statement stmt9 = conn.createStatement();
                    ResultSet rs9 = stmt9.executeQuery(query9);
                    if (rs9 != null){
                        while (rs9.next()){
                            try {



                                if (rs9.getString("name")!=null){
                                    l_count_vse.add(rs9.getString("name").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name1")!=null){
                                    l_count_stoyat.add(rs9.getString("name1").trim());
                                    gruppa1.add(new ListItemsStoyat(rs9.getString("name1").trim(),rs9.getString("id_child1").trim(),rs9.getInt("id_gruppa"),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"))); //получаем список детей стоят
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name2")!=null){
                                    l_count_snyati.add(rs9.getString("name2").trim());
                                    l_surprise.add(rs9.getString("surprise").trim());
                                    //itemsArrayList.add(new ClassListItems(rs9.getString("name2").trim(),rs9.getString("id_child2").trim(),rs9.getString("prichina").trim(),rs9.getString("surprise").trim(),rs9.getString("start").trim(),rs9.getString("in").trim(),rs9.getString("out").trim(),rs9.getString("id_gruppa").trim()));
                                    itemsArrayList.add(new ClassListItems(rs9.getString("name2").trim(),rs9.getString("id_child2").trim(),rs9.getString("reason").trim(),rs9.getString("surprise").trim(),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"),rs9.getInt("id_gruppa")));

                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name3")!=null){
                                    l_count_today.add(rs9.getString("name3").trim());
                                    //itemsArrayList2.add(new ClassListItems2(rs9.getString("name3").trim(),rs9.getString("id_child3").trim(),rs9.getString("today").trim(),rs9.getString("start").trim(),rs9.getString("in").trim(),rs9.getString("out").trim(),rs9.getString("id_gruppa").trim()));
                                    itemsArrayList2.add(new ClassListItems2(rs9.getString("name3").trim(),rs9.getString("id_child3").trim(),rs9.getString("reason").trim(),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"),rs9.getInt("id_gruppa")));
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }
                                /*if (rs9.getString("today").contains("ОПОЗДАНИЕМ")){
                                    l_opozd.add(rs9.getString("today").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }*/
                                /*if (rs9.getString("today").contains("СОВСЕМ")){
                                    l_not_go.add(rs9.getString("today").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }*/
                                if (rs9.getInt("id")==200) l_not_go.add(rs9.getString("id"));


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
                //Log.d("ooooo","syncdata_tabel");
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

            /*Log.d("fgt", String.valueOf(l_count_vse.size())+" l_count_vse");
            Log.d("fgt", String.valueOf(l_count_stoyat.size())+" l_count_stoyat");
            Log.d("fgt", String.valueOf(l_count_snyati.size())+" l_count_snyati");
            Log.d("fgt", String.valueOf(l_count_today.size())+" l_count_today");

            Log.d("fgt", String.valueOf(l_opozd.size())+" l_opozd");
            Log.d("fgt", String.valueOf(l_not_go.size())+" l_not_go");
            Log.d("fgt", String.valueOf(l_surprise.size())+" l_surprise");*/



            //Toast.makeText(MainActivity.this, msg + "", Toast.LENGTH_SHORT).show();
            if (success == false){

            }else {
                try {

                    myAppAdapter2 = new MyAppAdapter2(gruppa1, getActivity());
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter2);
                    listView.setStackFromBottom(false);

                    sPref = getActivity().getSharedPreferences("item_gruppa",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed2 = sPref.edit();
                    ed2.putString("item_gruppa",item_gruppa);
                    ed2.commit();



                    myAppAdapter = new MyAppAdapter(itemsArrayList, getActivity());
                    int savedPosition = listView2.getFirstVisiblePosition();
                    View firstVisibleView = listView2.getChildAt(0);
                    int savedListTop = (firstVisibleView == null) ? 0 : firstVisibleView.getTop();
                    listView2.setAdapter(myAppAdapter);
                    if (savedPosition >= 0) { //initialized to -1
                        listView2.setSelectionFromTop(savedPosition, savedListTop);
                    }

                    myAppAdapter3 = new MyAppAdapter3(itemsArrayList2, getActivity());
                    //listView3.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView3.setAdapter(myAppAdapter3);
                    listView3.setStackFromBottom(false);


                    m3 = l_surprise.toArray(new String[0]);
                    for(int i=0;i<=m3.length;i++) {
                        if(l_surprise.contains("")) {
                            l_surprise.remove("");
                        }
                    }
                    String [] mm3 = l_surprise.toArray(new String[0]);
                    count_surprise = String.valueOf(mm3.length); //кол-во сюрпризов




                    b1.setText("Поставлены "+String.valueOf(l_count_stoyat.size())+"/"+String.valueOf(l_count_today.size()));
                    b3.setText(item_gruppa+" "+String.valueOf(l_count_vse.size()));
                    b2.setText("Сняты "+String.valueOf(l_count_snyati.size())+"/"+count_surprise);
                    b4.setText("Не пришли "+String.valueOf(l_count_today.size())+"/"+String.valueOf(l_not_go.size()));


                }catch (Exception ex){

                }
            }
        }
    }

    private class SyncData_get_array_holidays extends AsyncTask<String, String, String> {

        /*String msg = "подключитесь к интернету!";
        ProgressDialog progress;*/



        @Override
        protected void onPreExecute() {
            //progress = ProgressDialog.show(getActivity(), "", "загрузка базы данных...", true);
            /*progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();*/
//            stringList = new ArrayList<>();
            stringList.clear();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
                    Log.d(MSSQL,"success = false");
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

                    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date today = new Date();
                    Log.d("holidays","Сегодня: " + sdf.format(today));

                    //show first day next month and lost work day this month
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(today);
                    calendar.add(Calendar.MONTH, 1);//Used for finding next month
                    calendar.set(Calendar.DAY_OF_MONTH, 1);//Setting the Day of month as 1 for starting
                    Log.d("holidays","первый календарный день след месяца: " + sdf.format(calendar.getTime()));
                    do{
                        calendar.add(Calendar.DATE, -1); //In the first case decease day by 1 so get the this months last day
                    } while (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY );
                    //Date lastDayOfMonth = calendar.getTime();
                    Log.d("holidays","послед раб день этого месяца: " + sdf.format(calendar.getTime()));

                    //show first and first work day this month
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(today);
                    calendar2.set(Calendar.DAY_OF_MONTH, 1);
                    Log.d("holidays","первый календарный день этого месяца: " + sdf.format(calendar2.getTime()));

                    while (calendar2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                            calendar2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY  || stringList.contains(sdf.format(calendar2.getTime()))){
                        Log.d("holidays","iteration: " + sdf.format(calendar2.getTime()));
                        calendar2.add(Calendar.DATE, 1);
                    }
                    Log.d("holidays","первый раб день этого месяца: " + sdf.format(calendar2.getTime()));

                    if (calendar2.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) Log.d("holidays","понедельник: " + sdf.format(calendar2.getTime()));;

                    //show lost work day before this month
                    Calendar calendar3 = Calendar.getInstance();
                    calendar3.setTime(today);
                    calendar3.set(Calendar.DAY_OF_MONTH, 1);
                    do{
                        calendar3.add(Calendar.DATE, -1); //In the first case decease day by 1 so get the this months last day
                    } while (calendar3.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar3.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY );
                    Log.d("holidays","послед раб день прошлого месяца: " + sdf.format(calendar3.getTime()));

                    //show lost work day before this month
                    Calendar calendar4 = Calendar.getInstance();
                    //calendar4.setTime(today);
                    calendar4.set(Calendar.DAY_OF_MONTH, 1);
                    calendar4.set(Calendar.YEAR, 2019);
                    Log.d("holidays","");
                    Log.d("holidays","произвольный день: " + sdf.format(calendar4.getTime()));

                    if (stringList.contains(sdf.format(calendar4.getTime()))) Log.d("holidays","!!!");;

                    super_mothod();
                }catch (Exception ex){
                    Log.d("holidays","not progress !!!");
                }
            }
        }
    }

    public void super_mothod(){

        int num_week;

        List<Integer> a1 = new ArrayList<>();
        int [] mas = new int[23];
        int [] mas_day = new int[5];

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();

        Calendar c1 = Calendar.getInstance();
        c1.setTime(today);
        c1.set(Calendar.DAY_OF_MONTH,1);
        while(c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c1.getTime()))){
            c1.add(Calendar.DATE, 1);
//            Log.d("paut",sdf.format(c1.getTime()));
        }
//        Log.d("paut","первый раб день этого месяца: " + sdf.format(c1.getTime()));

        Calendar c2 = Calendar.getInstance();
        c2.setTime(today);
        c2.add(Calendar.MONTH, 1);
        c2.set(Calendar.DAY_OF_MONTH, 1);
        do{
            c2.add(Calendar.DATE, -1);
        } while (c2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c2.getTime())));

//        Log.d("paut","послед раб день этого месяца: " + sdf.format(c2.getTime()));

        Calendar c0 = Calendar.getInstance();
        c0.setTime(today);
        while(c0.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c0.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c0.getTime()))){
            c0.add(Calendar.DATE, -1);
        }
//        Log.d("paut","сегодня или до сегодня раб день этого месяца: " + sdf.format(c0.getTime()));

        DateFormat sdf_int_day = new SimpleDateFormat("dd");
        int first_work_day = Integer.valueOf(sdf_int_day.format(c1.getTime()));
        int last_work_day = Integer.valueOf(sdf_int_day.format(c2.getTime()));
        int today_work_day = Integer.valueOf(sdf_int_day.format(c0.getTime()));
        int tabel = last_work_day - 2;

        int hour = c0.get(Calendar.HOUR_OF_DAY);

//        Log.d("paut", String.valueOf(first_work_day));
//        Log.d("paut", String.valueOf(last_work_day));
//        Log.d("paut", String.valueOf(today_work_day));
//        Log.d("paut", "сегодня или до сегодня раб день этого месяца ЧАС: " +hour);
//        Log.d("paut", "3 раб день с конца: " +tabel);

        if (today_work_day == tabel && hour >= 12){
            int iter_array = 0;
            num_week = 1;
            int iter_day_for_week = 0;
            while(first_work_day <= last_work_day){
                if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c1.getTime()))){
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
            int iter_array = 0;
            num_week = 1;
            int iter_day_for_week = 0;
            while(first_work_day <= today_work_day){
                if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || stringList.contains(sdf.format(c1.getTime()))){
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
//            Log.d("paut", "num_week "+String.valueOf(num_week));
//            Log.d("paut", "iter_day_for_week "+String.valueOf(iter_day_for_week));
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
            Log.d("paut", String.valueOf(num));
            if (num!=0){
                poker++;
            }
        }

        Log.d("paut", "num_week " + num_week);
        Log.d("paut", "poker " + poker);


    }

    private class Start_gruppa extends AsyncTask<String, String, String> {

        String msg = "подключитесь к интернету!";
        ProgressDialog progress;


        @Override
        protected void onPreExecute() {
            //progress = ProgressDialog.show(getActivity(), "", "загрузка базы данных...", true);
            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();
            gruppa.clear();
            Log.d("MSSQL","onPreExecuteMN");
            //itemsArrayList.clear();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
                    //Log.d(MSSQL,"success = false");

                }
                else {


                    String query_v = "select name from gruppa order by id";
                    Statement stmt_v = conn.createStatement();
                    ResultSet rs_v = stmt_v.executeQuery(query_v);
                    if (rs_v != null){
                        while (rs_v.next()){
                            try {
                                String gr = rs_v.getString("name").trim();
                                Log.d("MSSQL", gr);
                                gruppa.add(gr);

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
                Log.d("ooooo","Start_gruppa_tabel");
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

                    if(gruppa.size()>0){
                        Log.d("fgt","yes");

                        flag_begin = 1;
                        sPref = getActivity().getSharedPreferences("flag_begin",Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putInt("flag_begin",flag_begin);
                        ed.apply();
                    }else{
                        startActivity(new Intent(getActivity(), Login2.class));
                        getActivity().finish();
                    }



                    mgruppa = gruppa.toArray(new String[0]);
                    saveArray(mgruppa,"mgruppa",getActivity());

                    item_gruppa = mgruppa[0];
                    b3.setText(item_gruppa);
                    sPref = getActivity().getSharedPreferences("item_gruppa",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed2 = sPref.edit();
                    ed2.putString("item_gruppa",item_gruppa);
                    ed2.apply();
                    Log.d("item_gruppa",item_gruppa +"222222222222");

                    int id_gruppa = 1;
                    sPref = getActivity().getSharedPreferences("id_gruppa",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed_id_gruppa = sPref.edit();
                    ed_id_gruppa.putInt("id_gruppa",id_gruppa);
                    ed_id_gruppa.apply();

                    all_start();

//                    dialog_group_start();
                }catch (Exception ex){

                }
            }
        }
    }

    private class Find extends AsyncTask<String, String, String> {

        String msg = "подключитесь к интернету!";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();
            findArrayList.clear();
        }
        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
                }
                else {
                    String query_v = "select deti.id,deti.name as name,gruppa.id as id_gruppa,gruppa.name as name_gruppa,number_schet as num,month([in])[in],isnull(month([out]),0)[out] from deti left outer join gruppa on deti.id_gruppa=gruppa.id where deti.name like '"+find+"%%'";
                    Statement stmt_v = conn.createStatement();
                    ResultSet rs_v = stmt_v.executeQuery(query_v);
                    Log.d("tttrrr",query_v);
                    if (rs_v != null){
                        while (rs_v.next()){
                            try {
                                findArrayList.add(new FindItems(rs_v.getString("name").trim(),rs_v.getString("id").trim(),rs_v.getInt("id_gruppa"),rs_v.getString("num").trim(),rs_v.getString("name_gruppa").trim(),rs_v.getString("in").trim(),rs_v.getString("out").trim()));
                                Log.d("tttrrr",rs_v.getString("name"));
                                Log.d("tttrrr",rs_v.getString("id"));
                                Log.d("tttrrr",rs_v.getString("id_gruppa"));
                                Log.d("tttrrr",rs_v.getString("num"));
                                Log.d("tttrrr",rs_v.getString("name_gruppa"));
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
                Log.d("ooooo","Find_tabel");
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
                    if (findArrayList.isEmpty()){
                        Toast.makeText(getActivity(), "Поиск не дал результатов", Toast.LENGTH_LONG).show();
                    }else {
                        finded(getActivity());
                    }
                }catch (Exception ex){
                }
            }
        }
    }

    private class Cont extends AsyncTask<String, String, String> {

        String msg = "подключитесь к интернету!";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();
            contArrayList.clear();
        }
        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
                }
                else {
                    String query_v = "select * from contakt left outer join gruppa on contakt.name_gruppa_cont = gruppa.name  order by id_cont,id";
                    Statement stmt_v = conn.createStatement();
                    ResultSet rs_v = stmt_v.executeQuery(query_v);
                    Log.d("tttrrr",query_v);
                    if (rs_v != null){
                        while (rs_v.next()){
                            try {
                                contArrayList.add(new ContItems(rs_v.getString("id_c").trim(),rs_v.getString("dol").trim(),rs_v.getString("fio_cont").trim(),rs_v.getString("name_gruppa_cont").trim(),rs_v.getString("tel").trim()));
                                Log.d("tttrrr",rs_v.getString("id_c"));
                                Log.d("tttrrr",rs_v.getString("dol"));
                                Log.d("tttrrr",rs_v.getString("fio_cont"));
                                Log.d("tttrrr",rs_v.getString("name_gruppa_cont"));
                                Log.d("tttrrr",rs_v.getString("tel"));
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
                Log.d("ooooo","Cont_tabel");
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
                    if (contArrayList.isEmpty()){
                        //Toast.makeText(getActivity(), "Поиск не дал результатов", Toast.LENGTH_LONG).show();
                        add_cont(getActivity());
                    }else {
                        contakt(getActivity());
                    }
                }catch (Exception ex){
                }
            }
        }
    }

    public void set_so_null(){
        int so = 0;
        sPref = getActivity().getSharedPreferences("so",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt("so",so);
        ed.apply();
    }

    public class MyAppAdapter extends BaseAdapter {

        public class ViewHolder{
            TextView textName;
            TextView textPrichina;
            TextView textId;
            TextView textSurprise;
            TextView textZnak;
        }

        public List<ClassListItems> parkingList;

        public Context context;
        ArrayList<ClassListItems> arraylist;

        private MyAppAdapter(List<ClassListItems> apps, Context context){
            this.parkingList = apps;
            this.context = context;
            arraylist = new ArrayList<ClassListItems>();
            arraylist.addAll(parkingList);
        }

        public void remove(int position) {
            parkingList.remove(position);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            Log.d(MSSQL,"getCount");
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            Log.d(MSSQL,"getItem");
            return parkingList.get(position);
        }

        @Override
        public long getItemId(int position) {
            Log.d(MSSQL,"getItemId");
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            ViewHolder viewHolder = null;
            if (rowView == null){
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.list_content3, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textName = (TextView)rowView.findViewById(R.id.name);
                viewHolder.textId = (TextView)rowView.findViewById(R.id.id2);
                viewHolder.textPrichina = (TextView)rowView.findViewById(R.id.prichina);
                viewHolder.textSurprise = (TextView)rowView.findViewById(R.id.surprise);
                viewHolder.textZnak = (TextView)rowView.findViewById(R.id.znak_podkl2);
                rowView.setTag(viewHolder);
                Log.d(MSSQL,"getView");
            }
            else {
                viewHolder = (ViewHolder)convertView.getTag();
                Log.d(MSSQL,"viewHolder = (ViewHolder)convertView.getTag()");
            }

            viewHolder.textName.setText(parkingList.get(position).getName()+"");
            viewHolder.textId.setText(parkingList.get(position).getId()+"");
            viewHolder.textPrichina.setText(parkingList.get(position).getPrichina()+"");
            viewHolder.textSurprise.setText(parkingList.get(position).getSurprise()+"");
            viewHolder.textZnak.setText(parkingList.get(position).getStart()+"");




            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id_child_surprise_1 = parkingList.get(position).getId().toString();
                    Log.d("fgt",parkingList.get(position).getId().toString());

                    pressed_day(2);

//                    showDialog(IDD3);
                }
            });

            rowView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    name = myAppAdapter.parkingList.get(position).getName().toString().trim();
                    int in_int = myAppAdapter.parkingList.get(position).getIn();
                    int out_int = myAppAdapter.parkingList.get(position).getOut();
                    int yin_int = myAppAdapter.parkingList.get(position).getYin();
                    int yout_int = myAppAdapter.parkingList.get(position).getYout();


                    set_so_null();//делаем so=0

                    sPref = getActivity().getSharedPreferences("in_int",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed_in_int = sPref.edit();
                    ed_in_int.putInt("in_int",in_int);
                    ed_in_int.apply();
                    sPref = getActivity().getSharedPreferences("out_int",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed_out_int = sPref.edit();
                    ed_out_int.putInt("out_int",out_int);
                    ed_out_int.apply();
                    sPref = getActivity().getSharedPreferences("yin_int",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed_yin_int = sPref.edit();
                    ed_yin_int.putInt("yin_int",yin_int);
                    ed_yin_int.apply();
                    sPref = getActivity().getSharedPreferences("yout_int",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed_yout_int = sPref.edit();
                    ed_yout_int.putInt("yout_int",yout_int);
                    ed_yout_int.apply();

                    get_id_child = parkingList.get(position).getId().toString().trim();
                    get_id_gruppa = parkingList.get(position).getId_gruppa();

                    sPref = getActivity().getSharedPreferences("name_child",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edid_name = sPref.edit();
                    edid_name.putString("name_child",name);
                    edid_name.apply();

                    sPref = getActivity().getSharedPreferences("id_child",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edid_child = sPref.edit();
                    edid_child.putInt("id_child", Integer.parseInt(get_id_child));
                    edid_child.apply();

                    sPref = getActivity().getSharedPreferences("id_gruppa",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edid_gruppa = sPref.edit();
                    edid_gruppa.putInt("id_gruppa",get_id_gruppa);
                    edid_gruppa.apply();

                    FragmentManager fragmentManager = getFragmentManager();
                    if (fragmentManager != null) {
                        fragmentManager
                                .beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.container, new CalendarFragment())
                                .addToBackStack(null)
                                .commit();
                    }

                    return false;
                }
            });
            return rowView;
        }



    }

    public class AdapterFind extends BaseAdapter {

        public class ViewHolder{
            TextView textName;
            TextView textId;
            TextView textId_gruppa;
            TextView textNum;
            TextView textName_gruppa;

        }

        public List<FindItems> parkingList;

        public Context context;
        ArrayList<FindItems> arraylist;

        private AdapterFind(List<FindItems> apps, Context context){
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
//            Log.d(MSSQL,"getCount");
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
//            Log.d(MSSQL,"getItem");
            return parkingList.get(position);
        }

        @Override
        public long getItemId(int position) {
//            Log.d(MSSQL,"getItemId");
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            ViewHolder viewHolder = null;
            if (rowView == null){
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.list_content_find, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textName = (TextView)rowView.findViewById(R.id.nameF);
                viewHolder.textName_gruppa = (TextView)rowView.findViewById(R.id.nameFgruppa);
                //viewHolder.textPrichina = (TextView)rowView.findViewById(R.id.prichina);
                rowView.setTag(viewHolder);
//                Log.d(MSSQL,"getView");
            }
            else {
                viewHolder = (ViewHolder)convertView.getTag();
//                Log.d(MSSQL,"viewHolder = (ViewHolder)convertView.getTag()");
            }

            viewHolder.textName.setText(parkingList.get(position).getName()+"");
            viewHolder.textName_gruppa.setText(parkingList.get(position).getName_gruppa()+"");
            //viewHolder.textPrichina.setText(parkingList.get(position).getPrichina()+"");

            /*rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //name = myAppAdapter.parkingList.get(position).getName().toString().trim();
                    Toast.makeText(getActivity(), "888888888", Toast.LENGTH_SHORT).show();


                }
            });*/

//            Log.d(MSSQL,"0000000000000000000000000000000000000000");
            return rowView;

        }
    }

    public class AdapterCont extends BaseAdapter {

        public class ViewHolder{
            TextView textId;
            TextView textDol;
            TextView textFio;
            TextView textName_gruppa;
            TextView textTel;

        }

        public List<ContItems> parkingList;

        public Context context;
        ArrayList<ContItems> arraylist;

        private AdapterCont(List<ContItems> apps, Context context){
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
//            Log.d(MSSQL,"getCount");
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
//            Log.d(MSSQL,"getItem");
            return parkingList.get(position);
        }

        @Override
        public long getItemId(int position) {
//            Log.d(MSSQL,"getItemId");
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            ViewHolder viewHolder = null;
            if (rowView == null){
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.list_content_cont, parent, false);
                viewHolder = new ViewHolder();
                //viewHolder.textId = (TextView)rowView.findViewById(R.id.nameFgruppa);
                viewHolder.textDol = (TextView)rowView.findViewById(R.id.dol);
                viewHolder.textFio = (TextView)rowView.findViewById(R.id.name_fio_cont);
                viewHolder.textName_gruppa = (TextView)rowView.findViewById(R.id.name_gruppa_cont);
                viewHolder.textTel = (TextView)rowView.findViewById(R.id.tel);
                rowView.setTag(viewHolder);
//                Log.d(MSSQL,"getView");
            }
            else {
                viewHolder = (ViewHolder)convertView.getTag();
//                Log.d(MSSQL,"viewHolder = (ViewHolder)convertView.getTag()");
            }

            viewHolder.textDol.setText(parkingList.get(position).getDol()+"");
            viewHolder.textFio.setText(parkingList.get(position).getFio()+"");
            viewHolder.textName_gruppa.setText(parkingList.get(position).getName_gruppa()+"");
            viewHolder.textTel.setText(parkingList.get(position).getTel()+"");

            /*rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //name = myAppAdapter.parkingList.get(position).getName().toString().trim();
                    Toast.makeText(getActivity(), "888888888", Toast.LENGTH_SHORT).show();


                }
            });*/

//            Log.d(MSSQL,"0000000000000000000000000000000000000000");
            return rowView;

        }
    }

    public class MyAppAdapter2 extends BaseAdapter {

        public class ViewHolder{
            TextView textName;
            TextView textZnak;
            TextView textIn;
            TextView textOut;

        }

        public List<ListItemsStoyat> parkingList;

        public Context context;
        ArrayList<ListItemsStoyat> arraylist;

        private MyAppAdapter2(List<ListItemsStoyat> apps, Context context){
            this.parkingList = apps;
            this.context = context;
            arraylist = new ArrayList<ListItemsStoyat>();
            arraylist.addAll(parkingList);
        }

        public void remove(int position) {
            parkingList.remove(position);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
//            Log.d(MSSQL,"getCount");
            return parkingList.size();
        }

        /*@Override
        public boolean isEnabled(int position) {
            return false;
        }*/

        @Override
        public Object getItem(int position) {
//            Log.d(MSSQL,"getItem");
            return parkingList.get(position);
        }

        @Override
        public long getItemId(int position) {
//            Log.d(MSSQL,"getItemId");
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            ViewHolder viewHolder = null;
            if (rowView == null){
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.list_content2, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textName = (TextView)rowView.findViewById(R.id.name);
                viewHolder.textZnak = (TextView)rowView.findViewById(R.id.znak_podkl);
                //viewHolder.textPrichina = (TextView)rowView.findViewById(R.id.prichina);
                //viewHolder.textToday = (TextView)rowView.findViewById(R.id.prichina);
                rowView.setTag(viewHolder);
//                Log.d(MSSQL,"getView");
            }
            else {
                viewHolder = (ViewHolder)convertView.getTag();
//                Log.d(MSSQL,"viewHolder = (ViewHolder)convertView.getTag()");
            }

            viewHolder.textName.setText(parkingList.get(position).getName()+"");
            viewHolder.textZnak.setText(parkingList.get(position).getStart()+"");


            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = myAppAdapter2.parkingList.get(position).getName().toString().trim();
                    get_id_child = parkingList.get(position).getId().toString().trim();
                    get_id_gruppa = parkingList.get(position).getId_gruppa();

                    sPref = getActivity().getSharedPreferences("id_child",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putString("id_child",get_id_child);
                    ed.apply();

                    sPref = getActivity().getSharedPreferences("id_gruppa",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed3 = sPref.edit();
                    ed3.putInt("id_gruppa",get_id_gruppa);
                    ed3.apply();

//                    Log.d("fgt","dateday "+dateday);                //выбранный день
//                    Log.d("fgt","datemonth "+datemonth);            //выбранный месяц
//                    Log.d("fgt","mon "+mon);                        //текущий месяц
//                    Log.d("fgt","datemonthINT "+datemonthINT);      //выбранный месяц инт
//                    Log.d("fgt","name "+name);
//                    Log.d("fgt","date "+date);                      //выбранная дата в формате yyyy-mm-dd
//                    Log.d("fgt","get_id_child "+get_id_child);
//                    Log.d("fgt","get_id_gruppa "+get_id_gruppa);
//
//                    Log.i("FATAL",stringList.size()+"*");
//                    Log.i("FATAL",stringList_work.size()+"*");

                    pressed_day(1);
//                    mega();
                    //get_id_id_id();

                }
            });

            rowView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    name = myAppAdapter2.parkingList.get(position).getName().toString().trim();
                    int in_int = myAppAdapter2.parkingList.get(position).getIn();
                    int out_int = myAppAdapter2.parkingList.get(position).getOut();
                    int yin_int = myAppAdapter2.parkingList.get(position).getYin();
                    int yout_int = myAppAdapter2.parkingList.get(position).getYout();

                    sPref = getActivity().getSharedPreferences("in_int",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed_in_int = sPref.edit();
                    ed_in_int.putInt("in_int",in_int);
                    ed_in_int.apply();
                    sPref = getActivity().getSharedPreferences("out_int",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed_out_int = sPref.edit();
                    ed_out_int.putInt("out_int",out_int);
                    ed_out_int.apply();
                    sPref = getActivity().getSharedPreferences("yin_int",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed_yin_int = sPref.edit();
                    ed_yin_int.putInt("yin_int",yin_int);
                    ed_yin_int.apply();
                    sPref = getActivity().getSharedPreferences("yout_int",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed_yout_int = sPref.edit();
                    ed_yout_int.putInt("yout_int",yout_int);
                    ed_yout_int.apply();


                    set_so_null();//делаем so=0

                    get_id_child = parkingList.get(position).getId().toString().trim();
                    get_id_gruppa = parkingList.get(position).getId_gruppa();

                    sPref = getActivity().getSharedPreferences("name_child",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edid_name = sPref.edit();
                    edid_name.putString("name_child",name);
                    edid_name.apply();

                    sPref = getActivity().getSharedPreferences("id_child",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edid_child = sPref.edit();
                    edid_child.putInt("id_child", Integer.parseInt(get_id_child));
                    edid_child.apply();

                    sPref = getActivity().getSharedPreferences("id_gruppa",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edid_gruppa = sPref.edit();
                    edid_gruppa.putInt("id_gruppa",get_id_gruppa);
                    edid_gruppa.apply();

                    FragmentManager fragmentManager = getFragmentManager();
                    if (fragmentManager != null) {
                        fragmentManager
                                .beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.container, new CalendarFragment())
                                .addToBackStack(null)
                                .commit();
                    }

                    //Log.d("int_in",myAppAdapter2.parkingList.get(position).getIn().toString().trim());
                    //Log.d("int_out",myAppAdapter2.parkingList.get(position).getOut().toString().trim());

                    //Toast.makeText(getActivity(), name +"***"+item_gruppa+"* "+month, Toast.LENGTH_SHORT).show();

                    return true;
                }
            });

            //viewHolder.textPrichina.setText(parkingList.get(position).getPrichina()+"");
            //viewHolder.textToday.setText(parkingList.get(position).getToday()+"");

            /*if (parkingList.get(position).getPrichina().contains("null")){
                viewHolder.textPrichina.setText("");
            }else {
                viewHolder.textPrichina.setText(parkingList.get(position).getPrichina()+"");


            }
            if (parkingList.get(position).getToday().contains("null")){
                viewHolder.textToday.setText("");
            }else {
                viewHolder.textToday.setText(parkingList.get(position).getToday()+"");
            }*/





//            Log.d(MSSQL,"0000000000000000000000000000000000000000");
            return rowView;

        }
    }

    public boolean checking(Calendar c1){
        DateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd");
        /*if((stringList.contains(sdf_date.format(c1.getTime()))||c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY||c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
                &&!stringList_work.contains(sdf_date.format(c1.getTime()))){
            return true;
        }*/
        return false;
    }

    public class MyAppAdapter3 extends BaseAdapter {

        public class ViewHolder{
            TextView textName;
            TextView textToday;
            TextView textId;
            TextView textZnak;

        }

        public List<ClassListItems2> parkingList;

        public Context context;
        ArrayList<ClassListItems2> arraylist;

        private MyAppAdapter3(List<ClassListItems2> apps, Context context){
            this.parkingList = apps;
            this.context = context;
            arraylist = new ArrayList<ClassListItems2>();
            arraylist.addAll(parkingList);
        }

        public void remove(int position) {
            parkingList.remove(position);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            Log.d(MSSQL,"getCount");
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            Log.d(MSSQL,"getItem");
            return parkingList.get(position);
        }

        @Override
        public long getItemId(int position) {
            Log.d(MSSQL,"getItemId");
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            ViewHolder viewHolder = null;
            if (rowView == null){
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.list_content2, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textName = (TextView)rowView.findViewById(R.id.name);
                viewHolder.textId = (TextView)rowView.findViewById(R.id.id);
                viewHolder.textToday = (TextView)rowView.findViewById(R.id.prichina);
                viewHolder.textZnak = (TextView)rowView.findViewById(R.id.znak_podkl);
                rowView.setTag(viewHolder);
                Log.d(MSSQL,"getView");
            }
            else {
                viewHolder = (ViewHolder)convertView.getTag();
                Log.d(MSSQL,"viewHolder = (ViewHolder)convertView.getTag()");
            }

            viewHolder.textName.setText(parkingList.get(position).getName()+"");
            viewHolder.textId.setText(parkingList.get(position).getId()+"");
            viewHolder.textToday.setText(parkingList.get(position).getToday()+"");
            viewHolder.textZnak.setText(parkingList.get(position).getStart()+"");

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = myAppAdapter3.parkingList.get(position).getName().toString().trim();
//                    int in_int = myAppAdapter3.parkingList.get(position).getIn();
//                    int out_int = myAppAdapter3.parkingList.get(position).getOut();
//                    int yin_int = myAppAdapter3.parkingList.get(position).getYin();
//                    int yout_int = myAppAdapter3.parkingList.get(position).getYout();

                    /*get_id_child = parkingList.get(position).getId().toString().trim();
                    get_id_gruppa = parkingList.get(position).getId_gruppa();

                    sPref = getActivity().getSharedPreferences("id_child",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edid_child = sPref.edit();
                    edid_child.putInt("id_child", Integer.parseInt(get_id_child));
                    edid_child.apply();

                    sPref = getActivity().getSharedPreferences("id_gruppa",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edid_gruppa = sPref.edit();
                    edid_gruppa.putInt("id_gruppa",get_id_gruppa);
                    edid_gruppa.apply();*/

                    /*sPref = getActivity().getSharedPreferences("in_int",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putInt("in_int",in_int);
                    ed.apply();
                    sPref = getActivity().getSharedPreferences("out_int",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed1 = sPref.edit();
                    ed1.putInt("out_int",out_int);
                    ed1.apply();*/

                    pressed_day(3);

                }
            });

            rowView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    name = myAppAdapter3.parkingList.get(position).getName().toString().trim();
                    int in_int = myAppAdapter3.parkingList.get(position).getIn();
                    int out_int = myAppAdapter3.parkingList.get(position).getOut();
                    int yin_int = myAppAdapter3.parkingList.get(position).getYin();
                    int yout_int = myAppAdapter3.parkingList.get(position).getYout();

                    set_so_null();//делаем so=0

                    get_id_child = parkingList.get(position).getId().toString().trim();
                    get_id_gruppa = parkingList.get(position).getId_gruppa();

                    sPref = getActivity().getSharedPreferences("name_child",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edid_name = sPref.edit();
                    edid_name.putString("name_child",name);
                    edid_name.apply();

                    sPref = getActivity().getSharedPreferences("id_child",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edid_child = sPref.edit();
                    edid_child.putInt("id_child", Integer.parseInt(get_id_child));
                    edid_child.apply();

                    sPref = getActivity().getSharedPreferences("id_gruppa",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edid_gruppa = sPref.edit();
                    edid_gruppa.putInt("id_gruppa",get_id_gruppa);
                    edid_gruppa.apply();

                    sPref = getActivity().getSharedPreferences("in_int",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed_in_int = sPref.edit();
                    ed_in_int.putInt("in_int",in_int);
                    ed_in_int.apply();
                    sPref = getActivity().getSharedPreferences("out_int",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed_out_int = sPref.edit();
                    ed_out_int.putInt("out_int",out_int);
                    ed_out_int.apply();
                    sPref = getActivity().getSharedPreferences("yin_int",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed_yin_int = sPref.edit();
                    ed_yin_int.putInt("yin_int",yin_int);
                    ed_yin_int.apply();
                    sPref = getActivity().getSharedPreferences("yout_int",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed_yout_int = sPref.edit();
                    ed_yout_int.putInt("yout_int",yout_int);
                    ed_yout_int.apply();

                    FragmentManager fragmentManager = getFragmentManager();
                    if (fragmentManager != null) {
                        fragmentManager
                                .beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.container, new CalendarFragment())
                                .addToBackStack(null)
                                .commit();
                    }
                    return true;
                }
            });







            Log.d(MSSQL,"0000000000000000000000000000000000000000");
            return rowView;

        }
    }

    public boolean saveArray(String[] array, String arrayName, Context mContext) {
        SharedPreferences prefs = getActivity().getSharedPreferences("preferencename", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.length);
        for(int i=0;i<array.length;i++)
            editor.putString(arrayName + "_" + i, array[i]);
        return editor.commit();
    }

    public String[] loadArray(String arrayName, Context mContext) {
        SharedPreferences prefs = getActivity().getSharedPreferences("preferencename", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String array[] = new String[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        return array;
    }

    public void find(final Context context) {
        final AlertDialog.Builder ab = new AlertDialog.Builder(context);
        View v = LayoutInflater.from(context).inflate(R.layout.find, null);
        ab.setView(v);

        final EditText findd = (EditText)v.findViewById(R.id.ed_pass222);


        findd.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (findd.getText().toString().isEmpty()){
                    findd.setBackgroundResource(R.color.gray5);
                }else {
                    findd.setBackgroundResource(R.color.Aquamarine);
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });




        ab.setPositiveButton("Найти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*if (findd.getText().toString().isEmpty()){
                    Toast.makeText(context, "Вы ввели не все данные", Toast.LENGTH_SHORT).show();
                }else {
                    find = findd.getText().toString();
                    finding();
                }*/
                find = findd.getText().toString();
                Log.d("tttrrr",find);
                finding();

                dialog.dismiss();
            }
        });
        ab.setNeutralButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ab.create().show();
    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
//        isMyServiceRunning(MyServiceDoska.class);
//        isMyServiceRunning2(MyService.class);

    }*/

    /*@Override
    protected void onPause() {
        super.onPause();
//        isMyServiceRunning(MyServiceDoska.class);
//        isMyServiceRunning2(MyService.class);
    }*/

    public void finded(final Context context) {
        final AlertDialog.Builder ab = new AlertDialog.Builder(context);
        View v = LayoutInflater.from(context).inflate(R.layout.find_listview, null);
        ab.setView(v);
        listviewFind = (ListView)v.findViewById(R.id.list_finded);
        myFindAdapter = new AdapterFind(findArrayList,getActivity());
        listviewFind.setAdapter(myFindAdapter);

        ab.setNegativeButton("Закрыть", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final Dialog dialog = ab.create();
        listviewFind.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                name = myFindAdapter.parkingList.get(position).getName().toString();
                Integer in_int = Integer.valueOf(myFindAdapter.parkingList.get(position).getIn().toString().trim());
                Integer out_int = Integer.valueOf(myFindAdapter.parkingList.get(position).getOut().toString().trim());

                get_id_child = myFindAdapter.parkingList.get(position).getId().toString().trim();
                get_id_gruppa = myFindAdapter.parkingList.get(position).getId_gruppa();

                sPref = getActivity().getSharedPreferences("id_child",Context.MODE_PRIVATE);
                SharedPreferences.Editor edid_child = sPref.edit();
                edid_child.putString("id_child",get_id_child);
                edid_child.apply();

                sPref = getActivity().getSharedPreferences("id_gruppa",Context.MODE_PRIVATE);
                SharedPreferences.Editor edid_gruppa = sPref.edit();
                edid_gruppa.putInt("id_gruppa",get_id_gruppa);
                edid_gruppa.apply();

                sPref = getActivity().getSharedPreferences("in_int",Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = sPref.edit();
                ed.putInt("in_int",in_int);
                ed.apply();
                sPref = getActivity().getSharedPreferences("out_int",Context.MODE_PRIVATE);
                SharedPreferences.Editor ed1 = sPref.edit();
                ed1.putInt("out_int",out_int);
                ed1.apply();
                //Log.d("fgt",name+"tabel");
                //Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show()


            }
        });
        //ab.create().show();
        dialog.show();
    }

    public void contakt(final Context context) {
        final AlertDialog.Builder ab = new AlertDialog.Builder(context);
        View v = LayoutInflater.from(context).inflate(R.layout.contakt_listview, null);
        ab.setView(v);
        listviewCont = (ListView)v.findViewById(R.id.list_contakt);
        myContAdapter = new AdapterCont(contArrayList,getActivity());
        listviewCont.setAdapter(myContAdapter);

        ab.setNegativeButton("Закрыть", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ab.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                add_cont(getActivity());
                dialog.dismiss();
            }
        });
        final Dialog dialog = ab.create();


        listviewCont.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getfio = myContAdapter.parkingList.get(position).getFio().toString();
                getdol = myContAdapter.parkingList.get(position).getDol().toString();
                getnamegr = myContAdapter.parkingList.get(position).getName_gruppa().toString();
                gettel = myContAdapter.parkingList.get(position).getTel().toString();
                getid_c = myContAdapter.parkingList.get(position).getId().toString();
                //Toast.makeText(getActivity(), getid_c, Toast.LENGTH_SHORT).show();
                edit_cont(getActivity());
                dialog.dismiss();
            }
        });
        //ab.create().show();
        dialog.show();
    }

    public void add_cont(final Context context) {
        final AlertDialog.Builder ab = new AlertDialog.Builder(context);
        View v = LayoutInflater.from(context).inflate(R.layout.add_cont, null);
        ab.setView(v);

        add_dol = (TextView)v.findViewById(R.id.add_dol);
        editText_fio_cont = (EditText)v.findViewById(R.id.ed_fio_cont);
        add_name_gruppa_cont = (TextView)v.findViewById(R.id.add_name_gruppa_cont);
        add_tel = (EditText)v.findViewById(R.id.add_tel);

        add_dol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_dol();
            }
        });

        add_name_gruppa_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_group_add_cont();
            }
        });

        ab.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                item_dol = add_dol.getText().toString().trim();
                if (item_dol.isEmpty()){
                    Toast.makeText(getActivity(), "Вы ввели не все данные", Toast.LENGTH_SHORT).show();
                }else {
                    fio_cont = editText_fio_cont.getText().toString().trim();
                    tel = add_tel.getText().toString().trim();
                    item_dol = add_dol.getText().toString().trim();
                    item_gruppa_add_cont = add_name_gruppa_cont.getText().toString().trim();


                    if (item_dol.contains("Заведующий")){
                        id_cont = "2";
                    }
                    if (item_dol.contains("Ст.воспитатель")){
                        id_cont = "3";
                    }
                    if (item_dol.contains("Ст.медсестра")){
                        id_cont = "4";
                    }
                    if (item_dol.contains("Медсестра")){
                        id_cont = "5";
                    }
                    if (item_dol.contains("Делопроизводитель")){
                        id_cont = "6";
                    }
                    if (item_dol.contains("Воспитатель")){
                        id_cont = "7";
                    }
                    if (item_dol.contains("Няня")){
                        id_cont = "8";
                    }
                    if (item_dol.contains("Общий")){
                        id_cont = "1";
                    }

                    if (item_gruppa_add_cont.isEmpty()){
                        item_gruppa_add_cont = "";
                    }
                    if (tel.isEmpty()){
                        tel = "";
                    }

                    if (fio_cont.isEmpty()){
                        fio_cont = "";
                    }

                    Log.d("ftg",fio_cont);
                    Log.d("ftg",tel);
                    Log.d("ftg",item_dol);
                    Log.d("ftg",item_gruppa_add_cont);
                    Log.d("ftg",id_cont);

                    cont_insert();

                }


                //add_cont(getActivity());

                dialog.dismiss();

                cont_show();
            }
        });
        ab.setNegativeButton("Закрыть", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cont_show();
                dialog.dismiss();
            }
        });
        ab.create().show();
    }

    public void edit_cont(final Context context) {
        final AlertDialog.Builder ab = new AlertDialog.Builder(context);
        View v = LayoutInflater.from(context).inflate(R.layout.edit_cont, null);
        ab.setView(v);

        edit_dol = (TextView)v.findViewById(R.id.edit_dol);
        edit_fio_cont = (EditText)v.findViewById(R.id.edit_fio_cont);
        edit_name_gruppa_cont = (TextView)v.findViewById(R.id.edit_name_gruppa_cont);
        edit_tel = (EditText)v.findViewById(R.id.edit_tel);

        edit_dol.setText(getdol);
        edit_fio_cont.setText(getfio);
        edit_name_gruppa_cont.setText(getnamegr);
        edit_tel.setText(gettel);



        edit_dol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_dol2();
            }
        });

        edit_name_gruppa_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_group_add_cont2();
            }
        });



        ab.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getdol = edit_dol.getText().toString().trim();
                getfio = edit_fio_cont.getText().toString().trim();
                getnamegr = edit_name_gruppa_cont.getText().toString().trim();
                gettel = edit_tel.getText().toString().trim();

                if (getdol.isEmpty()){
                    getdol="";
                }
                if (getfio.isEmpty()){
                    getfio="";
                }
                if (getnamegr.isEmpty()){
                    getnamegr="";
                }
                if (gettel.isEmpty()){
                    gettel="";
                }

                if (getdol.contains("Заведующий")){
                    id_cont = "2";
                }
                if (getdol.contains("Ст.воспитатель")){
                    id_cont = "3";
                }
                if (getdol.contains("Ст.медсестра")){
                    id_cont = "4";
                }
                if (getdol.contains("Медсестра")){
                    id_cont = "5";
                }
                if (getdol.contains("Делопроизводитель")){
                    id_cont = "6";
                }
                if (getdol.contains("Воспитатель")){
                    id_cont = "7";
                }
                if (getdol.contains("Няня")){
                    id_cont = "8";
                }
                if (getdol.contains("Общий")){
                    id_cont = "1";
                }

                Log.d("tttrrr",getdol);
                Log.d("tttrrr",getfio);
                Log.d("tttrrr",getnamegr);
                Log.d("tttrrr",gettel);
                Log.d("tttrrr",id_cont);
                Log.d("tttrrr",getid_c);

                cont_update();
                dialog.dismiss();


            }
        });
        ab.setNegativeButton("Закрыть", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cont_show();
                dialog.dismiss();
            }
        });

        ab.setNeutralButton("Удалить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cont_delete();
                Log.d("fgt","3333");
                dialog.dismiss();
            }
        });

        ab.create().show();
    }

    private class Insert_add_cont extends AsyncTask<String, String, String> {

        String msg = "Error !";
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
                    String query_np = "insert into contakt (id_cont,dol,name_gruppa_cont,tel,fio_cont) values ("+id_cont+",'"+item_dol+"','"+item_gruppa_add_cont+"','"+tel+"','"+fio_cont+"')";
                    Log.d("tttrrr", query_np);
                    Statement stmt_np = conn_np.createStatement();
                    stmt_np.execute(query_np);


                }

                conn_np.close();
                Log.d("ooooo","Insert_add_cont_tabel");
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
            Toast.makeText(getActivity(), "Сотрудник успешно добавлен", Toast.LENGTH_LONG).show();
        }
    }

    private class Create_table_deti extends AsyncTask<String, String, String> {

        String msg = "Error !";

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
                }
                else {
                    String query = "IF OBJECT_ID('deti"+i_current_month+i_current_year+"','U') IS NOT NULL\n" +
                            "begin\n" +
                            "select 1 [check]\n" +
                            "end;\n" +
                            "else\n" +
                            "begin\n" +
                            "select 0 [check]\n" +
                            "select * into deti"+i_current_month+i_current_year+" from deti\n" +
                            "end;";


                    /*String query = "" +
                            "" +
                            "" +
                            "select * into deti"+today_segodnyaINT+i_current_year+" from deti";*/
                    Log.d("fgt", query);
                    conn.createStatement().execute(query);
                }

                conn.close();
                Log.d("ooooo","Insert_add_cont_tabel");
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
            new Proverka_Create_table_deti().execute();
        }
    }

    private class Proverka_Create_table_deti extends AsyncTask<String, String, String> {
        String msg = "Error !";
        Integer i_check;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
                }
                else {
                    String query = "IF OBJECT_ID('deti"+i_current_month+i_current_year+"','U') IS NOT NULL\n" +
                            "begin\n" +
                            "select 1 [check]\n" +
                            "end;\n" +
                            "else\n" +
                            "begin\n" +
                            "select 0 [check]\n" +
                            "end;";

                    Log.d("fgt", query);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs != null){
                        while (rs.next()){
                            try {

                                i_check = rs.getInt("check");

                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                        success = true;
                    }else {
                        success = false;
                    }
                }
                conn.close();
                Log.d("ooooo","Insert_add_cont_tabel");
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
            if (i_check==1){
                //Toast.makeText(getActivity(), "create copy deti !!!!!!!!!!!!"+i_current_month+i_current_year, Toast.LENGTH_LONG).show();
                sPref = getActivity().getSharedPreferences("i_create_table_deti"+i_current_month+i_current_year,Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = sPref.edit();
                ed.putInt("i_create_table_deti"+i_current_month+i_current_year,i_check);
                ed.apply();
            }else if (i_check==0){
                new Create_table_deti().execute();
            }
        }
    }

    private class Update_cont extends AsyncTask<String, String, String> {

        String msg = "Error !";
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
                    String query_np = "update contakt set id_cont = '"+id_cont+"', dol='"+getdol+"',fio_cont='"+getfio+"',name_gruppa_cont='"+getnamegr+"',tel='"+gettel+"' where id_c="+getid_c+"";
                    Log.d("tttrrr", query_np);
                    Statement stmt_np = conn_np.createStatement();
                    stmt_np.execute(query_np);


                }

                conn_np.close();
                Log.d("ooooo","Update_cont_tabel");
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
            cont_show();
            Toast.makeText(getActivity(), "Данные контакта обновлены", Toast.LENGTH_LONG).show();
        }
    }

    public void surprise(){
        Update_surprise updateSurprise = new Update_surprise();
        updateSurprise.execute("");
    }

    public void surpriseUN(){
        Update_surpriseUN updateSurprise = new Update_surpriseUN();
        updateSurprise.execute("");
    }

    private class Update_surprise extends AsyncTask<String, String, String> {

        String msg = "подключитесь к интернету!";
        ProgressDialog progress;
        Dialog progressDialog;

        @Override
        protected void onPreExecute() {

            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();

            l_count_vse.clear();
            l_count_stoyat.clear();
            l_count_snyati.clear();
            l_count_today.clear();
            l_not_go.clear();
            l_opozd.clear();
            l_surprise.clear();

            gruppa1.clear();
            itemsArrayList.clear();
            itemsArrayList2.clear();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
                    Log.d(MSSQL,"success = false");
                }
                else {

                    String query9 = "begin tran update gogo set surprise = '+' where id_child = '"+id_child_surprise_1+"' and datenotgo = '"+date+"'; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                    //Log.d("fgt",query9);
//                    Log.d("novoeee",new SimpleDateFormat("yyyy-MM-dd").format(dateAndTime.getTime()));
//                    Log.d("novoeee",query9);
                    Statement stmt9 = conn.createStatement();
                    ResultSet rs9 = stmt9.executeQuery(query9);
                    if (rs9 != null){
                        while (rs9.next()){
                            try {



                                if (rs9.getString("name")!=null){
                                    l_count_vse.add(rs9.getString("name").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name1")!=null){
                                    l_count_stoyat.add(rs9.getString("name1").trim());
                                    gruppa1.add(new ListItemsStoyat(rs9.getString("name1").trim(),rs9.getString("id_child1").trim(),rs9.getInt("id_gruppa"),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"))); //получаем список детей стоят
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name2")!=null){
                                    l_count_snyati.add(rs9.getString("name2").trim());
                                    l_surprise.add(rs9.getString("surprise").trim());
                                    //itemsArrayList.add(new ClassListItems(rs9.getString("name2").trim(),rs9.getString("id_child2").trim(),rs9.getString("prichina").trim(),rs9.getString("surprise").trim(),rs9.getString("start").trim(),rs9.getString("in").trim(),rs9.getString("out").trim(),rs9.getString("id_gruppa").trim()));
                                    itemsArrayList.add(new ClassListItems(rs9.getString("name2").trim(),rs9.getString("id_child2").trim(),rs9.getString("reason").trim(),rs9.getString("surprise").trim(),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"),rs9.getInt("id_gruppa")));

                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name3")!=null){
                                    l_count_today.add(rs9.getString("name3").trim());
                                    //itemsArrayList2.add(new ClassListItems2(rs9.getString("name3").trim(),rs9.getString("id_child3").trim(),rs9.getString("today").trim(),rs9.getString("start").trim(),rs9.getString("in").trim(),rs9.getString("out").trim(),rs9.getString("id_gruppa").trim()));
                                    itemsArrayList2.add(new ClassListItems2(rs9.getString("name3").trim(),rs9.getString("id_child3").trim(),rs9.getString("reason").trim(),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"),rs9.getInt("id_gruppa")));
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }
                                /*if (rs9.getString("today").contains("ОПОЗДАНИЕМ")){
                                    l_opozd.add(rs9.getString("today").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }*/
                                /*if (rs9.getString("today").contains("СОВСЕМ")){
                                    l_not_go.add(rs9.getString("today").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }*/
                                if (rs9.getInt("id")==200) l_not_go.add(rs9.getString("id"));


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
                //Log.d("ooooo","syncdata_tabel");
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

            /*Log.d("fgt", String.valueOf(l_count_vse.size())+" l_count_vse");
            Log.d("fgt", String.valueOf(l_count_stoyat.size())+" l_count_stoyat");
            Log.d("fgt", String.valueOf(l_count_snyati.size())+" l_count_snyati");
            Log.d("fgt", String.valueOf(l_count_today.size())+" l_count_today");

            Log.d("fgt", String.valueOf(l_opozd.size())+" l_opozd");
            Log.d("fgt", String.valueOf(l_not_go.size())+" l_not_go");
            Log.d("fgt", String.valueOf(l_surprise.size())+" l_surprise");*/



            //Toast.makeText(MainActivity.this, msg + "", Toast.LENGTH_SHORT).show();
            if (success == false){

            }else {
                try {

                    myAppAdapter2 = new MyAppAdapter2(gruppa1, getActivity());
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter2);
                    listView.setStackFromBottom(false);

                    sPref = getActivity().getSharedPreferences("item_gruppa",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed2 = sPref.edit();
                    ed2.putString("item_gruppa",item_gruppa);
                    ed2.commit();



                    myAppAdapter = new MyAppAdapter(itemsArrayList, getActivity());
                    int savedPosition = listView2.getFirstVisiblePosition();
                    View firstVisibleView = listView2.getChildAt(0);
                    int savedListTop = (firstVisibleView == null) ? 0 : firstVisibleView.getTop();
                    listView2.setAdapter(myAppAdapter);
                    if (savedPosition >= 0) { //initialized to -1
                        listView2.setSelectionFromTop(savedPosition, savedListTop);
                    }

                    myAppAdapter3 = new MyAppAdapter3(itemsArrayList2, getActivity());
                    //listView3.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView3.setAdapter(myAppAdapter3);
                    listView3.setStackFromBottom(false);


                    m3 = l_surprise.toArray(new String[0]);
                    for(int i=0;i<=m3.length;i++) {
                        if(l_surprise.contains("")) {
                            l_surprise.remove("");
                        }
                    }
                    String [] mm3 = l_surprise.toArray(new String[0]);
                    count_surprise = String.valueOf(mm3.length); //кол-во сюрпризов




                    b1.setText("Поставлены "+String.valueOf(l_count_stoyat.size())+"/"+String.valueOf(l_count_today.size()));
                    b3.setText(item_gruppa+" "+String.valueOf(l_count_vse.size()));
                    b2.setText("Сняты "+String.valueOf(l_count_snyati.size())+"/"+count_surprise);
                    b4.setText("Не пришли "+String.valueOf(l_count_today.size())+"/"+String.valueOf(l_not_go.size()));


                }catch (Exception ex){

                }
            }
        }
    }

    private class Update_surprise_old extends AsyncTask<String, String, String> {

        String msg = "Error !";
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
                    String query_np = "update gogo set surprise = '+' where id_child = '"+id_child_surprise_1+"' and datenotgo = '"+date+"'";
                    Log.d("tttrrr", query_np);
                    Statement stmt_np = conn_np.createStatement();
                    stmt_np.execute(query_np);


                }

                conn_np.close();
                Log.d("ooooo","Update_surprise_tabel");
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
            new SyncDataBoss().execute();
        }
    }

    private class Update_gogo extends AsyncTask<String, String, String> {

        String msg = "подключитесь к интернету!";
        ProgressDialog progress;
        Dialog progressDialog;

        @Override
        protected void onPreExecute() {

            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();

            l_count_vse.clear();
            l_count_stoyat.clear();
            l_count_snyati.clear();
            l_count_today.clear();
            l_not_go.clear();
            l_opozd.clear();
            l_surprise.clear();

            gruppa1.clear();
            itemsArrayList.clear();
            itemsArrayList2.clear();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
                    Log.d(MSSQL,"success = false");
                }
                else {

                    /*String query9 = "begin tran update gogo set surprise = '+' where id_child = '"+id_child_surprise_1+"' and datenotgo = '"+date+"'; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";*/
                    //Log.d("fgt",query9);
//                    Log.d("novoeee",new SimpleDateFormat("yyyy-MM-dd").format(dateAndTime.getTime()));
//                    Log.d("novoeee",query9);
                    Statement stmt9 = conn.createStatement();
                    ResultSet rs9 = stmt9.executeQuery(query_update_new);
                    if (rs9 != null){
                        while (rs9.next()){
                            try {



                                if (rs9.getString("name")!=null){
                                    l_count_vse.add(rs9.getString("name").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name1")!=null){
                                    l_count_stoyat.add(rs9.getString("name1").trim());
                                    gruppa1.add(new ListItemsStoyat(rs9.getString("name1").trim(),rs9.getString("id_child1").trim(),rs9.getInt("id_gruppa"),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"))); //получаем список детей стоят
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name2")!=null){
                                    l_count_snyati.add(rs9.getString("name2").trim());
                                    l_surprise.add(rs9.getString("surprise").trim());
                                    //itemsArrayList.add(new ClassListItems(rs9.getString("name2").trim(),rs9.getString("id_child2").trim(),rs9.getString("prichina").trim(),rs9.getString("surprise").trim(),rs9.getString("start").trim(),rs9.getString("in").trim(),rs9.getString("out").trim(),rs9.getString("id_gruppa").trim()));
                                    itemsArrayList.add(new ClassListItems(rs9.getString("name2").trim(),rs9.getString("id_child2").trim(),rs9.getString("reason").trim(),rs9.getString("surprise").trim(),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"),rs9.getInt("id_gruppa")));

                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name3")!=null){
                                    l_count_today.add(rs9.getString("name3").trim());
                                    //itemsArrayList2.add(new ClassListItems2(rs9.getString("name3").trim(),rs9.getString("id_child3").trim(),rs9.getString("today").trim(),rs9.getString("start").trim(),rs9.getString("in").trim(),rs9.getString("out").trim(),rs9.getString("id_gruppa").trim()));
                                    itemsArrayList2.add(new ClassListItems2(rs9.getString("name3").trim(),rs9.getString("id_child3").trim(),rs9.getString("reason").trim(),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"),rs9.getInt("id_gruppa")));
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }
                                /*if (rs9.getString("today").contains("ОПОЗДАНИЕМ")){
                                    l_opozd.add(rs9.getString("today").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }*/
                                /*if (rs9.getString("today").contains("СОВСЕМ")){
                                    l_not_go.add(rs9.getString("today").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }*/
                                if (rs9.getInt("id")==200) l_not_go.add(rs9.getString("id"));


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
                //Log.d("ooooo","syncdata_tabel");
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

            /*Log.d("fgt", String.valueOf(l_count_vse.size())+" l_count_vse");
            Log.d("fgt", String.valueOf(l_count_stoyat.size())+" l_count_stoyat");
            Log.d("fgt", String.valueOf(l_count_snyati.size())+" l_count_snyati");
            Log.d("fgt", String.valueOf(l_count_today.size())+" l_count_today");

            Log.d("fgt", String.valueOf(l_opozd.size())+" l_opozd");
            Log.d("fgt", String.valueOf(l_not_go.size())+" l_not_go");
            Log.d("fgt", String.valueOf(l_surprise.size())+" l_surprise");*/



            //Toast.makeText(MainActivity.this, msg + "", Toast.LENGTH_SHORT).show();
            if (success == false){

            }else {
                try {

                    myAppAdapter2 = new MyAppAdapter2(gruppa1, getActivity());
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter2);
                    listView.setStackFromBottom(false);

                    sPref = getActivity().getSharedPreferences("item_gruppa",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed2 = sPref.edit();
                    ed2.putString("item_gruppa",item_gruppa);
                    ed2.commit();



                    myAppAdapter = new MyAppAdapter(itemsArrayList, getActivity());
                    int savedPosition = listView2.getFirstVisiblePosition();
                    View firstVisibleView = listView2.getChildAt(0);
                    int savedListTop = (firstVisibleView == null) ? 0 : firstVisibleView.getTop();
                    listView2.setAdapter(myAppAdapter);
                    if (savedPosition >= 0) { //initialized to -1
                        listView2.setSelectionFromTop(savedPosition, savedListTop);
                    }

                    myAppAdapter3 = new MyAppAdapter3(itemsArrayList2, getActivity());
                    //listView3.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView3.setAdapter(myAppAdapter3);
                    listView3.setStackFromBottom(false);


                    m3 = l_surprise.toArray(new String[0]);
                    for(int i=0;i<=m3.length;i++) {
                        if(l_surprise.contains("")) {
                            l_surprise.remove("");
                        }
                    }
                    String [] mm3 = l_surprise.toArray(new String[0]);
                    count_surprise = String.valueOf(mm3.length); //кол-во сюрпризов




                    b1.setText("Поставлены "+String.valueOf(l_count_stoyat.size())+"/"+String.valueOf(l_count_today.size()));
                    b3.setText(item_gruppa+" "+String.valueOf(l_count_vse.size()));
                    b2.setText("Сняты "+String.valueOf(l_count_snyati.size())+"/"+count_surprise);
                    b4.setText("Не пришли "+String.valueOf(l_count_today.size())+"/"+String.valueOf(l_not_go.size()));


                }catch (Exception ex){

                }
            }
        }
    }

    private class Update_gogo_old extends AsyncTask<String, String, String> {

        String msg = "Error !";
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

                    Log.d("tttrrr", query_update_new);
                    Statement stmt_np = conn_np.createStatement();
                    stmt_np.execute(query_update_new);


                }

                conn_np.close();
                Log.d("ooooo","Update_surprise_tabel");
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
            new SyncDataBoss().execute();
        }
    }

    private class Update_surpriseUN extends AsyncTask<String, String, String> {

        String msg = "подключитесь к интернету!";
        ProgressDialog progress;
        Dialog progressDialog;

        @Override
        protected void onPreExecute() {

            progress = new ProgressDialog(getActivity(),R.style.MyTheme);
            progress.setCancelable(false);
            progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress.show();

            l_count_vse.clear();
            l_count_stoyat.clear();
            l_count_snyati.clear();
            l_count_today.clear();
            l_not_go.clear();
            l_opozd.clear();
            l_surprise.clear();

            gruppa1.clear();
            itemsArrayList.clear();
            itemsArrayList2.clear();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection conn = connectionClass.CONN(login);
                if (conn == null){
                    success = false;
                    Log.d(MSSQL,"success = false");
                }
                else {

                    String query9 = "begin tran update gogo set surprise = '' where id_child = '"+id_child_surprise_1+"' and datenotgo = '"+date+"'; exec SSSuper2019_5 '"+date+"','"+item_gruppa+"'; commit tran";
                    //Log.d("fgt",query9);
//                    Log.d("novoeee",new SimpleDateFormat("yyyy-MM-dd").format(dateAndTime.getTime()));
//                    Log.d("novoeee",query9);
                    Statement stmt9 = conn.createStatement();
                    ResultSet rs9 = stmt9.executeQuery(query9);
                    if (rs9 != null){
                        while (rs9.next()){
                            try {



                                if (rs9.getString("name")!=null){
                                    l_count_vse.add(rs9.getString("name").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name1")!=null){
                                    l_count_stoyat.add(rs9.getString("name1").trim());
                                    gruppa1.add(new ListItemsStoyat(rs9.getString("name1").trim(),rs9.getString("id_child1").trim(),rs9.getInt("id_gruppa"),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"))); //получаем список детей стоят
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name2")!=null){
                                    l_count_snyati.add(rs9.getString("name2").trim());
                                    l_surprise.add(rs9.getString("surprise").trim());
                                    //itemsArrayList.add(new ClassListItems(rs9.getString("name2").trim(),rs9.getString("id_child2").trim(),rs9.getString("prichina").trim(),rs9.getString("surprise").trim(),rs9.getString("start").trim(),rs9.getString("in").trim(),rs9.getString("out").trim(),rs9.getString("id_gruppa").trim()));
                                    itemsArrayList.add(new ClassListItems(rs9.getString("name2").trim(),rs9.getString("id_child2").trim(),rs9.getString("reason").trim(),rs9.getString("surprise").trim(),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"),rs9.getInt("id_gruppa")));

                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }

                                if (rs9.getString("name3")!=null){
                                    l_count_today.add(rs9.getString("name3").trim());
                                    //itemsArrayList2.add(new ClassListItems2(rs9.getString("name3").trim(),rs9.getString("id_child3").trim(),rs9.getString("today").trim(),rs9.getString("start").trim(),rs9.getString("in").trim(),rs9.getString("out").trim(),rs9.getString("id_gruppa").trim()));
                                    itemsArrayList2.add(new ClassListItems2(rs9.getString("name3").trim(),rs9.getString("id_child3").trim(),rs9.getString("reason").trim(),rs9.getString("start").trim(),rs9.getInt("in"),rs9.getInt("out"),rs9.getInt("yin"),rs9.getInt("yout"),rs9.getInt("id_gruppa")));
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }
                                /*if (rs9.getString("today").contains("ОПОЗДАНИЕМ")){
                                    l_opozd.add(rs9.getString("today").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }*/
                                /*if (rs9.getString("today").contains("СОВСЕМ")){
                                    l_not_go.add(rs9.getString("today").trim());
                                    //Log.d("fgt",rs1.getString("name2").trim());
                                }*/
                                if (rs9.getInt("id")==200) l_not_go.add(rs9.getString("id"));


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
                //Log.d("ooooo","syncdata_tabel");
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

            /*Log.d("fgt", String.valueOf(l_count_vse.size())+" l_count_vse");
            Log.d("fgt", String.valueOf(l_count_stoyat.size())+" l_count_stoyat");
            Log.d("fgt", String.valueOf(l_count_snyati.size())+" l_count_snyati");
            Log.d("fgt", String.valueOf(l_count_today.size())+" l_count_today");

            Log.d("fgt", String.valueOf(l_opozd.size())+" l_opozd");
            Log.d("fgt", String.valueOf(l_not_go.size())+" l_not_go");
            Log.d("fgt", String.valueOf(l_surprise.size())+" l_surprise");*/



            //Toast.makeText(MainActivity.this, msg + "", Toast.LENGTH_SHORT).show();
            if (success == false){

            }else {
                try {

                    myAppAdapter2 = new MyAppAdapter2(gruppa1, getActivity());
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter2);
                    listView.setStackFromBottom(false);

                    sPref = getActivity().getSharedPreferences("item_gruppa",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed2 = sPref.edit();
                    ed2.putString("item_gruppa",item_gruppa);
                    ed2.commit();



                    myAppAdapter = new MyAppAdapter(itemsArrayList, getActivity());
                    int savedPosition = listView2.getFirstVisiblePosition();
                    View firstVisibleView = listView2.getChildAt(0);
                    int savedListTop = (firstVisibleView == null) ? 0 : firstVisibleView.getTop();
                    listView2.setAdapter(myAppAdapter);
                    if (savedPosition >= 0) { //initialized to -1
                        listView2.setSelectionFromTop(savedPosition, savedListTop);
                    }

                    myAppAdapter3 = new MyAppAdapter3(itemsArrayList2, getActivity());
                    //listView3.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView3.setAdapter(myAppAdapter3);
                    listView3.setStackFromBottom(false);


                    m3 = l_surprise.toArray(new String[0]);
                    for(int i=0;i<=m3.length;i++) {
                        if(l_surprise.contains("")) {
                            l_surprise.remove("");
                        }
                    }
                    String [] mm3 = l_surprise.toArray(new String[0]);
                    count_surprise = String.valueOf(mm3.length); //кол-во сюрпризов




                    b1.setText("Поставлены "+String.valueOf(l_count_stoyat.size())+"/"+String.valueOf(l_count_today.size()));
                    b3.setText(item_gruppa+" "+String.valueOf(l_count_vse.size()));
                    b2.setText("Сняты "+String.valueOf(l_count_snyati.size())+"/"+count_surprise);
                    b4.setText("Не пришли "+String.valueOf(l_count_today.size())+"/"+String.valueOf(l_not_go.size()));


                }catch (Exception ex){

                }
            }
        }
    }

    private class Update_surpriseUN_old extends AsyncTask<String, String, String> {

        String msg = "Error !";
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
                    String query_np = "update gogo set surprise = '' where id_child = '"+id_child_surprise_1+"' and datenotgo = '"+date+"'";
                    Log.d("tttrrr", query_np);
                    Statement stmt_np = conn_np.createStatement();
                    stmt_np.execute(query_np);


                }

                conn_np.close();
                Log.d("ooooo","Update_surpriseUN_tabel");
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
            new SyncDataBoss().execute();
        }
    }

    private class Delete_cont extends AsyncTask<String, String, String> {

        String msg = "Error !";
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
                    String query_np = "delete contakt where id_c="+getid_c+"";
                    Log.d("tttrrr", query_np);
                    Statement stmt_np = conn_np.createStatement();
                    stmt_np.execute(query_np);


                }

                conn_np.close();
                Log.d("ooooo","Delete_cont_tabel");
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
            cont_show();
            Toast.makeText(getActivity(), "Контакт удален", Toast.LENGTH_LONG).show();
        }
    }

    public void startAlertDialog(final Context context) {
        final AlertDialog.Builder ab = new AlertDialog.Builder(context);
        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateAndTime.set(Calendar.YEAR, year);
                dateAndTime.set(Calendar.MONTH, monthOfYear);
                dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //setInitialDateTime();

                //all_start();
                //start2();
                //group_start();
            }
        };

        ab.setPositiveButton("ПЕРЕЙТИ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        ab.setNegativeButton("ОТМЕНИТЬ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ab.create().show();
    }

    @Override
    public void onStop() {
        super.onStop();
        //Log.d("idgrup",item_gruppa);
    }
}
