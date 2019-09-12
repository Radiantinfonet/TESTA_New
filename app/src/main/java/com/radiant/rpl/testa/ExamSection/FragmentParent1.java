package com.radiant.rpl.testa.ExamSection;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.radiant.rpl.testa.LocalDB.DbAutoSave;

import radiant.rpl.radiantrpl.R;

import static android.content.Context.MODE_PRIVATE;

public class FragmentParent1 extends Fragment {
    // private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    DbAutoSave dbAutoSave;
    String queidd,queiddd;
    int pageno,pagenoo;
    TextView prev,skip,next;
    SharedPreferences spp1;
    String pgnoo1;
    String statussdata11,stuidd1;
    String dd1,EE1;
    int gg1,pp1;

    private static ShowButton showbuttonn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parent1, container, false);
        dbAutoSave=new DbAutoSave(getContext());
        getIDs(view);
        statussdata11=dbAutoSave.getDataOfSingleClientstatus(pgnoo1);

        spp1=getActivity().getSharedPreferences("mypref", MODE_PRIVATE);
        stuidd1=spp1.getString("userid","");

        CustomAdapter1.aa(new GotoQuestion() {
            @Override
            public void getposition(int a) {
                viewPager.setCurrentItem(a-1);
                showbuttonn.dd(true);
                if (dbAutoSave.getDataOfSingleClientstatus1(""+a)!=null && dbAutoSave.getStatusDataOfSingleClientstatus1(""+a).equals("3")){
                    dbAutoSave.updateDataunanswered1(stuidd1,""+a,"0",""+a);
                    System.out.println("Case with 3 status ");
                }
            }
        });


       /* markforreview11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg1=viewPager.getCurrentItem()+1;
                dd1=Integer.toString(gg1);
                System.out.println("markforreview"+stuidd1+" "+dd1);
                if (dbAutoSave.getDataOfSingleClientstatus1(dd1)!=null){
                    dbAutoSave.updateDataunanswered1(stuidd1,dd1,"2",dd1);
                }else {
                    dbAutoSave.insertDataunanswered1(stuidd1,dd1,"2");
                }
            }
        });
*/

        return view;
    }

    private void getIDs(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.my_viewpager1);
        View vv=view.findViewById(R.id.count_down_strip_footer1);
        prev=vv.findViewById(R.id.prev);
        next=vv.findViewById(R.id.next);
        skip=vv.findViewById(R.id.skip);
        adapter = new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);

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
                System.out.println("page scrolled");

                FragmentChild.aaa(new GetStatusQue() {
                    @Override
                    public void gets(boolean b) {
                        if (!b){
                            for (int i=1;i<=1;i++){
                                pp1=viewPager.getCurrentItem()+1;
                                EE1=Integer.toString(pp1);
                                System.out.println("On page change"+stuidd1+" "+EE1);
                                /*if (dbAutoSave.getDataOfSingleClientstatus1(EE1)!=null){
                                    dbAutoSave.updateDataunanswered1(stuidd1,EE1,"0",EE1);
                                }else {
                                    dbAutoSave.insertDataunanswered1(stuidd1,EE1,"0");

                                }*/
                            }
                        }
                    }
                });
            }
        });

    }

    public static void aa(ShowButton ss){
        showbuttonn=ss;
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void addPage(String pagename, String que, int pgn,String op1, String op2, String op3, String op4
                        ,String que_img,String optionimg1,
                        String optionimg2,String optionimg3,String optionimg4
    ) {
        Bundle bundle = new Bundle();
        bundle.putString("data", pagename);
        bundle.putInt("pgno",pgn);
        bundle.putString("daa",que);
        bundle.putString("op1",op1);
        bundle.putString("op2",op2);
        bundle.putString("op3",op3);
        bundle.putString("op4",op4);

        bundle.putString("que_img_viva",que_img);
        bundle.putString("img_op1_viva",optionimg1);
        bundle.putString("img_op2_viva",optionimg2);
        bundle.putString("img_op3_viva",optionimg3);
        bundle.putString("img_op4_viva",optionimg4);

        FragmentChild1 fragmentChild = new FragmentChild1();
        fragmentChild.setArguments(bundle);
        adapter.addFrag(fragmentChild, pagename,que);
        adapter.notifyDataSetChanged();
        if (adapter.getCount() > 0)
            viewPager.setCurrentItem(0);
    }
}
