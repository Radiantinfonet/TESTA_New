package com.radiant.rpl.testa.ExamSection;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.radiant.rpl.testa.LocalDB.DbAutoSave;

import java.util.ArrayList;

import radiant.rpl.radiantrpl.R;

public class CustomAdapter extends BaseAdapter{
    String result[];
    Context con;
    String aa;
    ArrayList<String> queidd=new ArrayList<>();
    ArrayList<String> statussss=new ArrayList<>();
    ArrayList<String> questatuss=new ArrayList<>();
    ArrayList<Integer> qqqq=new ArrayList<>();
    String img[];
    DbAutoSave dbAutoSave;
    private static GotoQuestion gotoQuestion;

    private static LayoutInflater inflater = null;

    public CustomAdapter(ArrayList<String> queidd, Context con, ArrayList<String> statussss, ArrayList<String> questatuss,ArrayList<Integer> qqqq) {
        this.queidd = queidd;
        this.con = con;
        this.statussss=statussss;
        this.questatuss=questatuss;
        this.qqqq=qqqq;
        inflater = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return queidd.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final holder  hld = new holder();
        View rowview;


            rowview = inflater.inflate(R.layout.gdmainfortestquestion, null);
            hld.tv = rowview.findViewById(R.id.txt2);
             hld.tv.setText(qqqq.get(position).toString());
               System.out.println("the size"+statussss);
            int ii=statussss.size();
                if (position < ii ) {
                    if (statussss.get(position).equals("1")) {
                        hld.tv.setBackground(ContextCompat.getDrawable(con, R.drawable.rounded_txt));
                    } else if (statussss.get(position).equals("2")) {
                        hld.tv.setBackground(ContextCompat.getDrawable(con, R.drawable.rounded_yellow));
                    } else if (statussss.get(position).equals("0")) {
                        hld.tv.setBackground(ContextCompat.getDrawable(con, R.drawable.not_visiteda));
                    } else if (statussss.get(position).equals("3")){
                        hld.tv.setBackground(ContextCompat.getDrawable(con, R.drawable.rounded_grey));
                    }else{

                    }
            }


        hld.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoQuestion.getposition(qqqq.get(position));
                System.out.println("the clicked button is"+qqqq.get(position));
            }
        });

            return rowview;
    }

    public static void aa(GotoQuestion gq){
        gotoQuestion=gq;
    }

    public class holder
    {
        TextView tv;

    }



}

