package com.example.tcc.ong;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc.Entities.Doacao;
import com.example.tcc.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class OngFeedDoacaoFragment extends Fragment {

    private RecyclerView feedDoacoesOng;
    private FirebaseFirestore mFirebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.ong_fragment_feed_doacao, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFirebaseFirestore = FirebaseFirestore.getInstance();
        feedDoacoesOng = view.findViewById(R.id.feedOng);

        Query query = mFirebaseFirestore.collection("Aguardando").whereEqualTo("origem", "Doador");

        FirestoreRecyclerOptions<Doacao> options = new FirestoreRecyclerOptions.Builder<Doacao>()
                .setQuery(query, Doacao.class)
                .build();

        adapter = new FirestoreRecyclerAdapter< Doacao, ItensViewHolder >(options) {
            @NonNull
            @Override
            public ItensViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ong_customfeedlayout, parent, false);
                return new ItensViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ItensViewHolder holder, int position, @NonNull Doacao model) {
                holder.tipo.setText(model.getTipo());
                holder.categoria.setText("Categoria: " + model.getCategoria());
                holder.quantidade.setText("Quantidade: " + model.getQtd());
                Picasso.get().load(model.getImgUrl1()).into(holder.imagem);
                Log.i("parsa", Uri.parse(model.getImgUrl1()).toString());
            }
        };

        Log.i("xx", view.getContext().toString());
        feedDoacoesOng.setHasFixedSize(true);
        feedDoacoesOng.setLayoutManager(new LinearLayoutManager(view.getContext()));
        feedDoacoesOng.setAdapter(adapter);

    }

    private static class ItensViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagem;
        private TextView tipo;
        private TextView categoria;
        private TextView quantidade;

        public ItensViewHolder(@NonNull View itemView) {
            super(itemView);

            tipo = itemView.findViewById(R.id.txtTipo);
            categoria = itemView.findViewById(R.id.txtCat);
            quantidade = itemView.findViewById(R.id.txtQuantidade);
            imagem = itemView.findViewById(R.id.imgDoacao);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}