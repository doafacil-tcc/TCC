package com.example.tcc.ong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

//        Query query = mFirebaseFirestore.collection("Aguardando").whereEqualTo("origem", "Doador");
//
//        FirestoreRecyclerOptions<Doacao> options = new FirestoreRecyclerOptions.Builder<Doacao>()
//                .setQuery(query, Doacao.class)
//                .build();
//
//        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<Doacao, ItensViewHolder>(options) {
//            @NonNull
//            @Override
//            public ItensViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ong_customfeedlayout, parent, false);
//                return new ItensViewHolder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull ItensViewHolder holder, int position, @NonNull Doacao model) {
//
//            }
//        };

    }

//    private class ItensViewHolder extends RecyclerView.ViewHolder {

//        private ImageView imagem;
//        private TextView tipo;
//        private TextView categoria;
//        private TextView quantidade;

//        public ItensViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }
}