package com.uwastudios.umusix;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class FragmentListLagu extends Fragment {
    ListView lv;
    String[] items;
    public FragmentListLagu() {
        // Required empty public constructor

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_fragment_list_lagu, container, false);

        lv = (ListView) rootview.findViewById(R.id.lv_playlist);

        final ArrayList<File> mySongs = findSongs(Environment.getExternalStorageDirectory());
        items = new String[mySongs.size()];
        for (int i = 0; i < mySongs.size(); i++) {
            //          toast(mySongs.get(i).getName().toString()); //untuk menyalin nama musik dari directory
            items[i] = mySongs.get(i).getName().toString().replace(".mp3", "").replace(".wav", "");
        }

        lv.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.layoutlagulist, R.id.layoutlistlagu, items));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                startActivity(new Intent(getActivity().getApplicationContext(), Player.class).putExtra("pos", position).putExtra("songlist", mySongs));
            }
        });

        return rootview;
    }


    public ArrayList<File> findSongs(File root){
        ArrayList<File> al = new ArrayList<File>();
        File[] files = root.listFiles();
        for(File singleFile : files){
            if(singleFile.isDirectory() && !singleFile.isHidden()){
                al.addAll(findSongs(singleFile));
            }
            else{
                if(singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav")){
                    al.add(singleFile);
                }
            }
        }
        return al;
    }
//
//    public void toast(String text){
//        Toast.makeText(getActivity(),text, Toast.LENGTH_SHORT).show();
//
//    }


}