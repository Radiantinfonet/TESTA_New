package com.radiant.rpl.testa.ExamSection;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.radiant.rpl.testa.Common.FiletoBase64;
import com.radiant.rpl.testa.LocalDB.DbAutoSave;

import java.util.HashMap;

import radiant.rpl.radiantrpl.R;

import static android.content.Context.MODE_PRIVATE;

public class FragmentChild1 extends Fragment implements View.OnClickListener {
    String childname,quename,option1,option2,option3,option4,que_img_viva,img_option1_viva,img_option2_viva,img_option3_viva,img_option4_viva;
    int pgnn;
    String dummystuid;
    SharedPreferences sp;

    Bitmap que_imgConverted_viva,img_option1converted_viva, img_option2converted_viva, getImg_option3converted_viva,getImg_option4converted_viva;
    ImageView que_iv_viva,que_opt1_viva,que_opt2_viva,que_opt3_viva,que_opt4_viva;

    TextView textViewChildName,t1,optiona,optionb,optionc,optiond,titlea,titleb,titlec,titled,mfr11;
    LinearLayout l1,l2,l3,l4;
    DbAutoSave dbAutoSave;
    String idd;
    String query;
    boolean statusvalue;
    HashMap<String,String> hm=new HashMap<>();
    boolean anyButtonClicked1=false;
    private static GetStatusQue getStatusQue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child1, container, false);
        Bundle bundle = getArguments();
        pgnn=bundle.getInt("pgno");
        childname = bundle.getString("data");
        quename =bundle.getString("daa");
        option1=bundle.getString("op1");
        option2=bundle.getString("op2");
        option3=bundle.getString("op3");
        option4=bundle.getString("op4");

        que_img_viva=bundle.getString("que_img_viva");
        img_option1_viva=bundle.getString("img_op1_viva");
        img_option2_viva=bundle.getString("img_op2_viva");
        img_option3_viva=bundle.getString("img_op3_viva");
        img_option4_viva=bundle.getString("img_op4_viva");

        hm.put(quename,childname);
        sp=getActivity().getSharedPreferences("mypref", MODE_PRIVATE);
        dbAutoSave = new DbAutoSave(getContext());
        getIDs(view);
        setEvents();
        idd=dbAutoSave.getDataOfSingleClient(query);
        dummystuid=sp.getString("userid","");

        getPhotoFromStringviva();
        return view;
    }


    public void getPhotoFromStringviva(){
        if (que_img_viva!=null){
            que_imgConverted_viva= FiletoBase64.getFilefromString(que_img_viva);
            if (que_imgConverted_viva!=null){
                que_iv_viva.setVisibility(View.VISIBLE);
                que_iv_viva.setImageBitmap(que_imgConverted_viva);

            }
            else {
                System.out.println("Bitmap is empty");
            }
        }

        if (img_option1_viva!=null){
            img_option1converted_viva=FiletoBase64.getFilefromString(img_option1_viva);
            if (img_option1converted_viva!=null){
                que_opt1_viva.setVisibility(View.VISIBLE);
                que_opt1_viva.setImageBitmap(img_option1converted_viva);

            }
            else {
                System.out.println("Bitmap is empty");
            }

        }
        if (img_option2_viva!=null){
            img_option2converted_viva=FiletoBase64.getFilefromString(img_option2_viva);
            if (img_option2converted_viva!=null){
                que_opt2_viva.setVisibility(View.VISIBLE);
                que_opt2_viva.setImageBitmap(img_option2converted_viva);
            }
            else {
                System.out.println("Bitmap is empty");
            }

        }
        if (img_option3_viva!=null){
            getImg_option3converted_viva=FiletoBase64.getFilefromString(img_option3_viva);
            if (getImg_option3converted_viva!=null){
                que_opt3_viva.setVisibility(View.VISIBLE);
                que_opt3_viva.setImageBitmap(getImg_option3converted_viva);
            }
            else {
                System.out.println("Bitmap is empty");
            }

        }
        if (img_option4_viva!=null){
            getImg_option4converted_viva=FiletoBase64.getFilefromString(img_option4_viva);
            if (getImg_option4converted_viva!=null){
                que_opt4_viva.setVisibility(View.VISIBLE);
                que_opt4_viva.setImageBitmap(getImg_option4converted_viva);
            }
            else {
                System.out.println("Bitmap is empty");
            }

        }

    }


    private void getIDs(View view) {
        textViewChildName =view.findViewById(R.id.Quesnoo1);
        t1=view.findViewById(R.id.que1);
        optiona=view.findViewById(R.id.optiona1);
        optionb=view.findViewById(R.id.optionb1);
        optionc=view.findViewById(R.id.optionc1);
        optiond=view.findViewById(R.id.optiond1);
        titlea=view.findViewById(R.id.optionnoa1);
        titleb=view.findViewById(R.id.optionnob1);
        titlec=view.findViewById(R.id.optionnoc1);
        titled=view.findViewById(R.id.optionnod1);
        l1=view.findViewById(R.id.option11);
        l2=view.findViewById(R.id.option21);
        l3=view.findViewById(R.id.option31);
        l4=view.findViewById(R.id.option41);
        View vv1=view.findViewById(R.id.vv11);
        View vv2=view.findViewById(R.id.vv22);
        View vv3=view.findViewById(R.id.vv33);

        que_iv_viva=view.findViewById(R.id.iv_question1);
        que_opt1_viva=view.findViewById(R.id.iv_option11);
        que_opt2_viva=view.findViewById(R.id.iv_option21);
        que_opt3_viva=view.findViewById(R.id.iv_option31);
        que_opt4_viva=view.findViewById(R.id.iv_option41);


        mfr11=view.findViewById(R.id.markforreviewww1);
        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);
        mfr11.setOnClickListener(this);
        textViewChildName.setText(pgnn+".");
        //textViewChildName.setText(childname+".)");
        t1.setText(quename);
        optiona.setText(option1);
        optionb.setText(option2);
        optionc.setText(option3);
        optiond.setText(option4);

        if(TextUtils.isEmpty(option1)){
            titlea.setVisibility(View.GONE);
            vv1.setVisibility(View.GONE);
        }
        if(TextUtils.isEmpty(option2)){
            titleb.setVisibility(View.GONE);
            vv2.setVisibility(View.GONE);
        }
        if(TextUtils.isEmpty(option3)){
            titlec.setVisibility(View.GONE);
            vv3.setVisibility(View.GONE);
        }
        if(TextUtils.isEmpty(option4)){
            titled.setVisibility(View.GONE);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void changeColorBack(TextView textView)
    {
        textView.setBackground(getResources().getDrawable(R.drawable.rounded_grey));
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static void aaa(GetStatusQue gettt){
        getStatusQue=gettt;
    }

    @Override
    public void setUserVisibleHint(boolean visible)
    {
        super.setUserVisibleHint(visible);
        if (visible && isResumed())
        {
            System.out.println("Visible Resume");
            if (dbAutoSave.getDataOfSingleClientstatus1(""+pgnn)!=null && dbAutoSave.getStatusDataOfSingleClientstatus1(""+pgnn).equals("3")){
                dbAutoSave.updateDataunanswered1(dummystuid,""+pgnn,"0",""+pgnn);
                System.out.println("Case with 3 status ");
            }
            onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(getStatusQue!=null){
            getStatusQue.gets(anyButtonClicked1);
            System.out.println("ttttt on pause running");
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        String aab;
        if (!getUserVisibleHint())
        {
            return;
        }
        query=hm.get(quename);
        if (dbAutoSave.getD(query)!=null){
            aab =dbAutoSave.getD(hm.get(quename));
        }
        else{
            aab="BB";
        }

        if (aab.equals("A")){
            titlea.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
        }else if (aab.equals("B")){
            titleb.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
        }
        else if (aab.equals("C")){
            titlec.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
        }
        else if (aab.equals("D")){
            titled.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
        }
        else {

        }
        //Show Answer updated in Db
        l1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        l2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        l3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        l4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        if (statusvalue){
        }
        else{
        }

    }



    private void setEvents() {

    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        changeColorBack(titlea);
        changeColorBack(titleb);
        changeColorBack(titlec);
        changeColorBack(titled);

        if (anyButtonClicked1) {
            System.out.println("ttttt button clicked captured");
        } else {
            anyButtonClicked1 = true;
        }

        switch (v.getId()) {
            case R.id.option11:
                titlea.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
                idd=dbAutoSave.getDataOfSingleClient(hm.get(quename));
                if (idd!=(null)){
                    dbAutoSave.updateData(hm.get(quename),dummystuid,titlea.getText().toString(),idd);
                    statusvalue=true;
                }
                else {
                    dbAutoSave.insertData(hm.get(quename),dummystuid,  titlea.getText().toString());
                    if (dbAutoSave.getDataOfSingleClientstatus1(Integer.toString(pgnn))!=null){
                        dbAutoSave.updateDataunanswered1(dummystuid,Integer.toString(pgnn),"1",Integer.toString(pgnn));

                    }else {
                        dbAutoSave.insertDataunanswered1(dummystuid,Integer.toString(pgnn),"1");
                    }
                }
                break;
            case R.id.option21:
                titleb.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
                idd=dbAutoSave.getDataOfSingleClient(hm.get(quename));
                if (idd!=(null)){
                    dbAutoSave.updateData(hm.get(quename),dummystuid,titleb.getText().toString(),idd);
                    statusvalue=true;
                }
                else {
                    dbAutoSave.insertData(hm.get(quename),dummystuid,  titleb.getText().toString());
                    if (dbAutoSave.getDataOfSingleClientstatus1(Integer.toString(pgnn))!=null){
                        dbAutoSave.updateDataunanswered1(dummystuid,Integer.toString(pgnn),"1",Integer.toString(pgnn));

                    }else {
                        dbAutoSave.insertDataunanswered1(dummystuid,Integer.toString(pgnn),"1");
                    }
                }
                break;
            case R.id.option31:
                titlec.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
                idd=dbAutoSave.getDataOfSingleClient(hm.get(quename));
                if (idd!=(null)){
                    dbAutoSave.updateData(hm.get(quename),dummystuid,titlec.getText().toString(),idd);
                    statusvalue=true;
                }
                else {
                    dbAutoSave.insertData(hm.get(quename),dummystuid,  titlec.getText().toString());
                    if (dbAutoSave.getDataOfSingleClientstatus1(Integer.toString(pgnn))!=null){
                        dbAutoSave.updateDataunanswered1(dummystuid,Integer.toString(pgnn),"1",Integer.toString(pgnn));

                    }else {
                        dbAutoSave.insertDataunanswered1(dummystuid,Integer.toString(pgnn),"1");
                    }
                }
                break;
            case  R.id.option41:

                titled.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
                idd=dbAutoSave.getDataOfSingleClient(hm.get(quename));
                if (idd!=(null)){
                    dbAutoSave.updateData(hm.get(quename),dummystuid,titled.getText().toString(),idd);
                    statusvalue=true;
                }
                else {
                    dbAutoSave.insertData(hm.get(quename), dummystuid,  titled.getText().toString());
                    if (dbAutoSave.getDataOfSingleClientstatus1(Integer.toString(pgnn))!=null){
                        dbAutoSave.updateDataunanswered1(dummystuid,Integer.toString(pgnn),"1",Integer.toString(pgnn));

                    }else {
                        dbAutoSave.insertDataunanswered1(dummystuid,Integer.toString(pgnn),"1");
                    }
                }
                break;
            case R.id.markforreviewww1:
                mfr11.setBackgroundColor(Color.parseColor("#f89c1b"));

                // mfr.setBackgroundColor(R.color.yellowdark);
                mfr11.setText("Marked");
                if (dbAutoSave.getDataOfSingleClientstatus1(Integer.toString(pgnn))!=null){
                    dbAutoSave.updateDataunanswered1(dummystuid,Integer.toString(pgnn),"2",Integer.toString(pgnn));

                }else {
                    dbAutoSave.insertDataunanswered1(dummystuid,Integer.toString(pgnn),"2");
                }
                break;
            default:
                break;

        }
    }
}
