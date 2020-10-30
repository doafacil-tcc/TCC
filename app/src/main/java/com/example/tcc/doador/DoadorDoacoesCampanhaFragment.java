package com.example.tcc.doador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tcc.R;


public class DoadorDoacoesCampanhaFragment extends Fragment {

    ListView feedCampanha;

    String[] nomes = {"Campanha#1","Campanha#2","Campanha#3","Campanha#4","Campanha#5","Campanha#6","Campanha#7","Campanha#8"};

    String[] detalhes = {"Descrição#1","Descrição#2","Descrição#3","Descrição#4","Descrição#5","Descrição#6","Descrição#7","Descrição#8"};

    int imagem = R.drawable.ic_launcher_background;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.doador_fragment_doacoes_campanha, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        feedCampanha = view.findViewById(R.id.feedCampanhas);

        CustomAdapter customAdapterCampanha = new CustomAdapter();
        feedCampanha.setAdapter(customAdapterCampanha);
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return nomes.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            View v = getLayoutInflater().inflate(R.layout.doador_customfeedlayout, null);

            ImageView mImageView = v.findViewById(R.id.imgDoacao);
            TextView mTitulo = v.findViewById(R.id.txtTituloDoacao);
            TextView mDetalhe = v.findViewById(R.id.txtDetalheDoacao);

            mImageView.setImageResource(imagem);
            mTitulo.setText(nomes[i]);
            mDetalhe.setText(detalhes[i]);

            return v;
        }
    }
}