package com.radiant.rpl.testa.ExamSection;

import android.annotation.SuppressLint;
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

/**
 * Created by DAT on 9/1/2015.
 */
public class FragmentChild extends Fragment implements View.OnClickListener {
    String childname,quename,option1,option2,option3,option4,que_img,img_option1,img_option2,img_option3,img_option4;
    Bitmap que_imgConverted,img_option1converted, img_option2converted, getImg_option3converted,getImg_option4converted;
    ImageView que_iv,que_opt1,que_opt2,que_opt3,que_opt4;
    int pgnn;
    String dummystuid;
    SharedPreferences sp;
    String getoptiona,getoptionb,getoptionc,getoptiond;
    TextView textViewChildName,t1,optiona,optionb,optionc,optiond,titlea,titleb,titlec,titled;
    LinearLayout l1,l2,l3,l4;
    DbAutoSave dbAutoSave;
    String idd;
    String query;
    boolean statusvalue;
    HashMap<String,String> hm=new HashMap<>();
    private static GetStatusQue getStatusQue;
    boolean anyButtonClicked=false;
    TextView mfr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child, container, false);
        Bundle bundle = getArguments();
        pgnn=bundle.getInt("pgno");
        childname = bundle.getString("data");
        quename =bundle.getString("daa");
        option1=bundle.getString("op1");
        option2=bundle.getString("op2");
        option3=bundle.getString("op3");
        option4=bundle.getString("op4");

        que_img=bundle.getString("que_img");
        img_option1=bundle.getString("img_op1");
        img_option2=bundle.getString("img_op2");
        img_option3=bundle.getString("img_op3");
        img_option4=bundle.getString("img_op4");



        hm.put(quename,childname);
        sp=getActivity().getSharedPreferences("mypref", MODE_PRIVATE);
        dbAutoSave = new DbAutoSave(getContext());
        getIDs(view);
        setEvents();
        idd=dbAutoSave.getDataOfSingleClient(query);
        dummystuid=sp.getString("userid","");
        getoptiona=optiona.getText().toString();
        getoptionb=optionb.getText().toString();
        getoptionc=optionc.getText().toString();
        getoptiond=optionc.getText().toString();
        System.out.println("abcc"+pgnn);

        getPhotoFromString();

        System.out.println("que_img"+que_img);

        return view;
    }

    public void getPhotoFromString(){
        if (que_img!=null){
            que_imgConverted= FiletoBase64.getFilefromString(que_img);
            if (que_imgConverted!=null){
                que_iv.setVisibility(View.VISIBLE);
                que_iv.setImageBitmap(que_imgConverted);

            }
            else {
                System.out.println("Bitmap is empty");
            }
        }

        if (img_option1!=null){
            img_option1converted=FiletoBase64.getFilefromString(img_option1);
            if (img_option1converted!=null){
                que_opt1.setVisibility(View.VISIBLE);
                que_opt1.setImageBitmap(img_option1converted);

            }
            else {
                System.out.println("Bitmap is empty");
            }

        }
        if (img_option2!=null){
            img_option2converted=FiletoBase64.getFilefromString(img_option2);
            if (img_option2converted!=null){
                que_opt2.setVisibility(View.VISIBLE);
                que_opt2.setImageBitmap(img_option2converted);
            }
            else {
                System.out.println("Bitmap is empty");
            }

        }
        if (img_option3!=null){
            getImg_option3converted=FiletoBase64.getFilefromString(img_option3);
            if (getImg_option3converted!=null){
                que_opt3.setVisibility(View.VISIBLE);
                que_opt3.setImageBitmap(getImg_option3converted);
            }
            else {
                System.out.println("Bitmap is empty");
            }

        }
        if (img_option4!=null){
            getImg_option4converted=FiletoBase64.getFilefromString(img_option4);
            if (getImg_option4converted!=null){
                que_opt4.setVisibility(View.VISIBLE);
                que_opt4.setImageBitmap(getImg_option4converted);
            }
            else {
                System.out.println("Bitmap is empty");
            }

        }

    }

    private void getIDs(View view) {
        textViewChildName =view.findViewById(R.id.Quesnoo);
        t1=view.findViewById(R.id.que);
        optiona=view.findViewById(R.id.optiona);
        optionb=view.findViewById(R.id.optionb);
        optionc=view.findViewById(R.id.optionc);
        optiond=view.findViewById(R.id.optiond);
        titlea=view.findViewById(R.id.optionnoa);
        titleb=view.findViewById(R.id.optionnob);
        titlec=view.findViewById(R.id.optionnoc);
        titled=view.findViewById(R.id.optionnod);
        View vv1=view.findViewById(R.id.vv1);
        View vv2=view.findViewById(R.id.vv2);
        View vv3=view.findViewById(R.id.vv3);
        l1=view.findViewById(R.id.option1);
        l2=view.findViewById(R.id.option2);
        l3=view.findViewById(R.id.option3);
        l4=view.findViewById(R.id.option4);

        que_iv=view.findViewById(R.id.iv_question);
        que_opt1=view.findViewById(R.id.iv_option1);
        que_opt2=view.findViewById(R.id.iv_option2);
        que_opt3=view.findViewById(R.id.iv_option3);
        que_opt4=view.findViewById(R.id.iv_option4);


        mfr=view.findViewById(R.id.markforreviewww);
        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);
        mfr.setOnClickListener(this);
        textViewChildName.setText(pgnn+".");
        //textViewChildName.setText(childname+".)");
        t1.setText(quename);

        optiona.setText(option1);
        optionb.setText(option2);
        optionc.setText(option3);
        optiond.setText(option4);


        //que_iv.setImageBitmap(que_imgConverted);
        que_opt1.setImageBitmap(img_option1converted);
        que_opt1.setImageBitmap(img_option2converted);
        que_opt3.setImageBitmap(getImg_option3converted);
        que_opt4.setImageBitmap(getImg_option4converted);

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

        System.out.println("vasdfd"+getoptiona);
        System.out.println("vasdfd"+getoptionb);
        System.out.println("vasdfd"+getoptionc);
        System.out.println("vasdfd"+getoptiond);

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
            if (dbAutoSave.getDataOfSingleClientstatus(""+pgnn)!=null && dbAutoSave.getStatusDataOfSingleClientstatus(""+pgnn).equals("3")){
                dbAutoSave.updateDataunanswered(dummystuid,""+pgnn,"0",""+pgnn);
                System.out.println("Case with 3 status ");
            }
            onResume();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        String aab;
        System.out.println("ttttt on resume running");
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




    }



    @Override
    public void onPause() {
        super.onPause();

        if(getStatusQue!=null){
        getStatusQue.gets(anyButtonClicked);
        System.out.println("ttttt on pause running");
        }
    }

    private void setEvents() {

    }


    @SuppressLint("ResourceAsColor")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        changeColorBack(titlea);
        changeColorBack(titleb);
        changeColorBack(titlec);
        changeColorBack(titled);

        String statussdata=dbAutoSave.getDataOfSingleClientstatus(hm.get(quename));


        if (anyButtonClicked) {
            System.out.println("ttttt button clicked captured");
        } else {
            anyButtonClicked = true;
        }

        switch (v.getId()) {
            case R.id.option1:
                titlea.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
                idd=dbAutoSave.getDataOfSingleClient(hm.get(quename));
                if (idd!=(null)){
                    dbAutoSave.updateData(hm.get(quename),dummystuid,titlea.getText().toString(),idd);
                   // statusvalue=true;
                }
                else {
                    dbAutoSave.insertData(hm.get(quename),dummystuid,  titlea.getText().toString());

                    if (dbAutoSave.getDataOfSingleClientstatus(Integer.toString(pgnn))!=null){
                        dbAutoSave.updateDataunanswered(dummystuid,Integer.toString(pgnn),"1",Integer.toString(pgnn));

                    }else {
                        dbAutoSave.insertDataunanswered(dummystuid,Integer.toString(pgnn),"1");
                    }

                  //  statusvalue=true;
                }
                break;
            case R.id.option2:
                titleb.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
                idd=dbAutoSave.getDataOfSingleClient(hm.get(quename));
                if (idd!=(null)){
                    dbAutoSave.updateData(hm.get(quename),dummystuid,titleb.getText().toString(),idd);
                    //statusvalue=true;
                }
                else {
                    dbAutoSave.insertData(hm.get(quename),dummystuid,  titleb.getText().toString());

                    if (dbAutoSave.getDataOfSingleClientstatus(Integer.toString(pgnn))!=null){
                        dbAutoSave.updateDataunanswered(dummystuid,Integer.toString(pgnn),"1",Integer.toString(pgnn));

                    }else {
                        dbAutoSave.insertDataunanswered(dummystuid,Integer.toString(pgnn),"1");
                    }

                    //statusvalue=true;
                }
                break;
            case R.id.option3:
                titlec.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
                idd=dbAutoSave.getDataOfSingleClient(hm.get(quename));
                if (idd!=(null)){
                    dbAutoSave.updateData(hm.get(quename),dummystuid,titlec.getText().toString(),idd);
                    //statusvalue=true;
                }
                else {
                    dbAutoSave.insertData(hm.get(quename),dummystuid,  titlec.getText().toString());

                    if (dbAutoSave.getDataOfSingleClientstatus(Integer.toString(pgnn))!=null){
                        dbAutoSave.updateDataunanswered(dummystuid,Integer.toString(pgnn),"1",Integer.toString(pgnn));

                    }else {
                        dbAutoSave.insertDataunanswered(dummystuid,Integer.toString(pgnn),"1");
                    }

                }
                break;
            case  R.id.option4:

                titled.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_txt));
                idd=dbAutoSave.getDataOfSingleClient(hm.get(quename));
                if (idd!=(null)){
                    dbAutoSave.updateData(hm.get(quename),dummystuid,titled.getText().toString(),idd);
                    //statusvalue=true;
                }
                else {
                    dbAutoSave.insertData(hm.get(quename), dummystuid,  titled.getText().toString());

                    if (dbAutoSave.getDataOfSingleClientstatus(Integer.toString(pgnn))!=null){
                        dbAutoSave.updateDataunanswered(dummystuid,Integer.toString(pgnn),"1",Integer.toString(pgnn));

                    }else {
                        dbAutoSave.insertDataunanswered(dummystuid,Integer.toString(pgnn),"1");
                    }

                    //statusvalue=true;
                }
                break;

            case R.id.markforreviewww:
                mfr.setBackgroundColor(Color.parseColor("#f89c1b"));

                // mfr.setBackgroundColor(R.color.yellowdark);
                mfr.setText("Marked");
                if (dbAutoSave.getDataOfSingleClientstatus(Integer.toString(pgnn))!=null){
                    dbAutoSave.updateDataunanswered(dummystuid,Integer.toString(pgnn),"2",Integer.toString(pgnn));

                }else {
                    dbAutoSave.insertDataunanswered(dummystuid,Integer.toString(pgnn),"2");
                }
                break;
                default:
                break;
        }


    }
}
