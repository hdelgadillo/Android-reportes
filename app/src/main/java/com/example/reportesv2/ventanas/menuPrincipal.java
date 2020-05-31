package com.example.reportesv2.ventanas;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.reportesv2.R;

import static androidx.navigation.Navigation.findNavController;

/**
 * A simple {@link Fragment} subclass.
 */
public class menuPrincipal extends Fragment {


    public menuPrincipal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.fragment_menu_principal, container, false);
        Button boton = vista.findViewById(R.id.button1);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController(v).navigate(R.id.link_reportes);
            }
        });
        // Inflate the layout for this fragment
        return vista;
    }

}
