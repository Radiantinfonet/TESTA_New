package com.radiant.rpl.testa.ExamSection;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.radiant.rpl.testa.LocalDB.DbAutoSave;

import java.util.HashMap;

import radiant.rpl.radiantrpl.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by DAT on 9/1/2015.
 */
public class FragmentParent extends Fragment {
    // private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    DbAutoSave dbAutoSave;
    int pageno,pagenoo;
    String stuidd;
    TextView prev,skip,next;
    private static ShowButton showbuttonn;
    SharedPreferences spp;
    String pgnoo;
    String quename1,childname1,idddd;
    HashMap<String,String> hm1=new HashMap<>();
    String statussdata1;
    String dd,EE;
    int gg,pp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parent, container, false);
        dbAutoSave=new DbAutoSave(getContext());
        getIDs(view);
        statussdata1=dbAutoSave.getDataOfSingleClientstatus(pgnoo);

        spp=getActivity().getSharedPreferences("mypref", MODE_PRIVATE);
        stuidd=spp.getString("userid","");

        CustomAdapter.aa(new GotoQuestion() {
            @Override
            public void getposition(int a) {
                viewPager.setCurrentItem(a-1);
                showbuttonn.dd(true);
                if (dbAutoSave.getDataOfSingleClientstatus(""+a)!=null && dbAutoSave.getStatusDataOfSingleClientstatus(""+a).equals("3")){
                    dbAutoSave.updateDataunanswered(stuidd,""+a,"0",""+a);
                    System.out.println("Case with 3 status ");
                }
            }
        });


        /*markforreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg=viewPager.getCurrentItem()+1;
                dd=Integer.toString(gg);
                System.out.println("markforreview"+stuidd+" "+dd);
                if (dbAutoSave.getDataOfSingleClientstatus(dd)!=null){
                    dbAutoSave.updateDataunanswered(stuidd,dd,"2",dd);
                }else {
                    dbAutoSave.insertDataunanswered(stuidd,dd,"2");
                }
            }
        });*/


        return view;
    }

    private void getIDs(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.my_viewpager);
        View vv=view.findViewById(R.id.count_down_strip_footer);
       // markforreview=view.findViewById(R.id.markforrevieww);
        prev=vv.findViewById(R.id.prev);
        next=vv.findViewById(R.id.next);
        skip=vv.findViewById(R.id.skip);
        adapter = new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(0);
        pagenoo=viewPager.getCurrentItem();


        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(getItem(-1), true);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(getItem(+1), true);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(getItem(+1), true);
            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            Boolean first = true;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (!first && positionOffset == 0 && positionOffsetPixels == 0){
                    onPageSelected(0);
                    first = false;

                }
            }
            @Override
            public void onPageSelected(int position) {

                pageno=viewPager.getCurrentItem();

                if (pageno == adapter.getCount()-1){
                    //ttv.setVisibility(View.VISIBLE);
                    showbuttonn.getData(1);
                    System.out.println("fragment on last page");

                }else{
                    showbuttonn.getData(0);
                    System.out.println("not true");

                }


            }

            @Override
            public void onPageScrollStateChanged(int i) {
                System.out.println("page scrolled"+i);

                FragmentChild.aaa(new GetStatusQue() {
                    @Override
                    public void gets(boolean b) {
                        if (!b){
                            for (int j=1;j<=1;j++){
                                pp=viewPager.getCurrentItem()+1;
                                EE=Integer.toString(pp);
                                System.out.println("On page change"+stuidd+" "+EE);
                                System.out.println("Status on page"+EE+"is"+dbAutoSave.getStatusDataOfSingleClientstatus(EE));

                              /* if (dbAutoSave.getDataOfSingleClientstatus(EE)!=null && dbAutoSave.getStatusDataOfSingleClientstatus(EE).equals("3")){
                                    dbAutoSave.updateDataunanswered(stuidd,EE,"0",EE);
                                    System.out.println("Case with 3 status ");
                                }*//*else {
                                    dbAutoSave.insertDataunanswered(stuidd,EE,"0");
                                    System.out.println("Case with no status ");

                                }*/
                            }
                        }
                    }
                });
            }
        });

    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    public static void aa(ShowButton ss){
        showbuttonn=ss;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void addPage(String pagename, String que, int pgn,String op1, String op2, String op3, String op4,String que_img,String optionimg1,
                        String optionimg2,String optionimg3,String optionimg4) {
        Bundle bundle = new Bundle();
        bundle.putString("data", pagename);
        bundle.putInt("pgno",pgn);
        bundle.putString("daa",que);
        bundle.putString("op1",op1);
        bundle.putString("op2",op2);
        bundle.putString("op3",op3);
        bundle.putString("op4",op4);

        bundle.putString("que_img",que_img);
        bundle.putString("img_op1",optionimg1);
        bundle.putString("img_op2",optionimg2);
        bundle.putString("img_op3",optionimg3);
        bundle.putString("img_op4",optionimg4);

        pgnoo=pagename;
        quename1=que;
        childname1=pagename;
        hm1.put(que,pagename);

        FragmentChild fragmentChild = new FragmentChild();
        fragmentChild.setArguments(bundle);
        adapter.addFrag(fragmentChild, pagename,que);
        adapter.notifyDataSetChanged();
        if (adapter.getCount() > 0)
            viewPager.setCurrentItem(0);
    }
}
