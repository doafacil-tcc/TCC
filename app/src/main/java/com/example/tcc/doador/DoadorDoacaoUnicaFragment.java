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


public class DoadorDoacaoUnicaFragment extends Fragment {

    ListView feedDoacoes;

    String[] nomes = {"Doação#1","Doação#2","Doação#3","Doação#4","Doação#5","Doação#6","Doação#7","Doação#8"};

    String[] detalhes = {"Descrição#1","Descrição#2","Descrição#3","Descrição#4","Descrição#5","Descrição#6","Descrição#7","Descrição#8"};

    int imagem = R.drawable.ic_launcher_foreground;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.doador_fragment_doacao_unica, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        feedDoacoes = view.findViewById(R.id.feedDoar);

        CustomAdapter customAdapterDoacao = new CustomAdapter();
        feedDoacoes.setAdapter(customAdapterDoacao);

    }

    class CustomAdapter extends BaseAdapter{

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