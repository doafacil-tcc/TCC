package com.example.tcc.doador;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.tcc.Entities.Doacao;
import com.example.tcc.Entities.DoacaoRoupa;
import com.example.tcc.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class DoadorNewRoupaFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Button btnEnviarRoupa, btnImg1, btnImg2, btnImg3;
    Spinner mSpinnerTipo, mSpinnerCondicao, mSpinnerQtd;
    EditText mTamanho, mDescricao;
    String spnTipo, spnCondicao, spnQtd;
    ImageView img1, img2, img3;
    Uri mSelectedUri1, mSelectedUri2, mSelectedUri3;
    String uid, tamanho, descricao;
    String imagem1, imagem2, imagem3;
    int x,y,z;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.doador_fragment_new_roupa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        btnEnviarRoupa = view.findViewById(R.id.btnAceitarDoacaoRoupa);
        btnImg1 = view.findViewById(R.id.btnAddImagemRoupa1);
        btnImg2 = view.findViewById(R.id.btnAddImagemRoupa2);
        btnImg3 = view.findViewById(R.id.btnAddImagemRoupa3);
        mSpinnerTipo = view.findViewById(R.id.spnTipoRoupa);
        mSpinnerCondicao = view.findViewById(R.id.spnCondicaoRoupa);
        mSpinnerQtd = view.findViewById(R.id.spnQuantidadeRoupa);
        mTamanho = view.findViewById(R.id.edtTamanhoRoupa);
        mDescricao = view.findViewById(R.id.edtDescricaoRoupa);
        img1 = view.findViewById(R.id.imgAddImagemRoupa1);
        img2 = view.findViewById(R.id.imgAddImagemRoupa2);
        img3 = view.findViewById(R.id.imgAddImagemRoupa3);

        ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter.createFromResource(view.getContext(), R.array.spnTipoRoupaDoacao, android.R.layout.simple_spinner_item);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        ArrayAdapter<CharSequence> adapterCondicao = ArrayAdapter.createFromResource(view.getContext(), R.array.spnCondicaoDoacao, android.R.layout.simple_spinner_item);
        adapterCondicao.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        ArrayAdapter<CharSequence> adapterQtd = ArrayAdapter.createFromResource(view.getContext(), R.array.spnQuantidadeDoacao, android.R.layout.simple_spinner_item);
        adapterQtd.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mSpinnerTipo.setAdapter(adapterTipo);
        mSpinnerTipo.setOnItemSelectedListener(this);
        mSpinnerCondicao.setAdapter(adapterCondicao);
        mSpinnerCondicao.setOnItemSelectedListener(this);
        mSpinnerQtd.setAdapter(adapterQtd);
        mSpinnerQtd.setOnItemSelectedListener(this);

        btnImg1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPhoto1();
            }
        }));

        btnImg2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPhoto2();
            }
        }));

        btnImg3.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPhoto3();
            }
        }));

        btnEnviarRoupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              DoarRoupa();
            }


            private void DoarRoupa() {

                uid = FirebaseAuth.getInstance().getUid();
                tamanho = mTamanho.getText().toString();
                descricao = mDescricao.getText().toString();
                Log.i("roupa", "tamanho");
                Log.i("roupa", "descricao");

                if (tamanho.isEmpty() ||  descricao.isEmpty() || spnTipo.equals("Escolha...")
                        || spnCondicao.equals("Escolha...") || spnQtd.equals("Escolha...") || (mSelectedUri1 == null)){
                    Toast.makeText(view.getContext(), "Preencha tudo parça. TA LOKÃO?", Toast.LENGTH_SHORT).show();
                    Log.i("roupa", "não foi");
                    return;
                }

                saveDoacaoInFirebase();

            }
        });
    }

    private void saveDoacaoInFirebase() {

        uid = FirebaseAuth.getInstance().getUid();
        String foto1 = UUID.randomUUID().toString();
        final StorageReference ref1 = FirebaseStorage.getInstance().getReference("/DoacaoRoupa/" + uid + foto1);
        ref1.putFile(mSelectedUri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                ref1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imagem1 = uri.toString();
                        Log.i("sucesso", imagem1);
                    }
                });
            }
        });

        if (mSelectedUri2 != null){
            String foto2 = UUID.randomUUID().toString();
            final StorageReference ref2 = FirebaseStorage.getInstance().getReference("/DoacaoRoupa/" + uid + foto2);
            ref2.putFile(mSelectedUri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imagem2 = uri.toString();
                        }
                    });
                }
            });
        }

        if (mSelectedUri3 != null){
            String foto3 = UUID.randomUUID().toString();
            final StorageReference ref3 = FirebaseStorage.getInstance().getReference("/DoacaoRoupa/" + uid + foto3);
            ref3.putFile(mSelectedUri3).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref3.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imagem3 = uri.toString();
                        }
                    });
                }
            });
        }

        final String iddoacao = UUID.randomUUID().toString();
        final String status = "Aguardando";
        final String unica_ou_camp = "unica";
        final String categoria = "Roupa";
        final String origem = "Doador";

        Intent intent = new Intent(getActivity(), DoadorDoacaoFinalizada.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {


        Doacao nova_roupa = new Doacao(iddoacao, uid, null, spnTipo, spnQtd, tamanho, spnCondicao, descricao,
                imagem1, imagem2, imagem3, status, unica_ou_camp, categoria, origem);

        FirebaseFirestore.getInstance().collection("Aguardando")
                .document(iddoacao)
                .set(nova_roupa)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
            }
        }.start();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3) {
            mSelectedUri1 = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), mSelectedUri1);
                img1.setImageDrawable(new BitmapDrawable(bitmap));
                btnImg1.setAlpha(0);
            } catch (IOException e) { }
        }

        if (requestCode == 4) {
            mSelectedUri2 = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), mSelectedUri2);
                img2.setImageDrawable(new BitmapDrawable(bitmap));
                btnImg2.setAlpha(0);
            } catch (IOException e) { }
        }

        if (requestCode == 5) {
            mSelectedUri3 = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), mSelectedUri3);
                img3.setImageDrawable(new BitmapDrawable(bitmap));
                btnImg3.setAlpha(0);
            } catch (IOException e) { }
        }
    }

    private void selectPhoto1() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 3);
    }

    private void selectPhoto2() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 4);
    }

    private void selectPhoto3() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 5);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spn = adapterView.getItemAtPosition(i).toString();
        spnTipo = mSpinnerTipo.getSelectedItem().toString();
        spnCondicao = mSpinnerCondicao.getSelectedItem().toString();
        spnQtd = mSpinnerQtd.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}