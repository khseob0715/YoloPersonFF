package com.aiden.yolopersonff.Fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiden.yolopersonff.R;
import com.aiden.yolopersonff.RecyclerAdapter;
import com.aiden.yolopersonff.RecyclerItem;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Aiden on 2018-06-25.
 */

public class Bulletin_Fragment extends Fragment {

    private String[] names = {"70%","65%","40%","90%","55%","30%","20%","10%","78%"};
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    private ArrayList<RecyclerItem> mItems = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bulletin,null);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        setRecyclerView();
        return view;
    }

    private void setRecyclerView(){
        // 각 Item 들이 RecyclerView 의 전체 크기를 변경하지 않는 다면
        // setHasFixedSize() 함수를 사용해서 성능을 개선할 수 있습니다.
        // 변경될 가능성이 있다면 false 로 , 없다면 true를 설정해주세요.
        recyclerView.setHasFixedSize(true);

        // RecyclerView에 Adapter를 설정해줍니다.
        adapter = new RecyclerAdapter(mItems);
        recyclerView.setAdapter(adapter);

        // 다양한 LayoutManager 가 있습니다. 원하시는 방법을 선택해주세요.
        // 지그재그형의 그리드 형식
        //mainBinding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        // 그리드 형식
        //mainBinding.recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        // 가로 또는 세로 스크롤 목록 형식
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        setData();
    }

    private void setData(){
        mItems.clear();

        Geocoder mGeoCoder = new Geocoder(getActivity());


        try {
            // Geocoder 내 위도, 경도는 이 후 위치 데이터를 받아올 경우 변경 예정
            List<Address> mResultList = mGeoCoder.getFromLocation(35.1636816,126.7969408,1);

            // RecyclerView 에 들어갈 데이터를 추가합니다.
            // names 데이터는 임시로 생성 한 데이터 이 후 일치율 데이터를 받아올 경우 삭제 예정
            for(String name : names){

                // 시간을 넣기 위해 임시로 생성 이 후 시간 데이터를 받아올 경우 삭제 예정
                long now = System.currentTimeMillis();
                // 현재시간을 date 변수에 저장한다.
                Date date = new Date(now);
                // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                // nowDate 변수에 값을 저장한다.
                String formatDate = sdfNow.format(date);

                mItems.add(new RecyclerItem(name, mResultList.get(0).getAddressLine(0), formatDate));
            }
            // 데이터 추가가 완료되었으면 notifyDataSetChanged() 메서드를 호출해 데이터 변경 체크를 실행합니다.
            adapter.notifyDataSetChanged();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Bulletin_Fragment", "onComplete: 주소변환 실패");
        }
    }
}
