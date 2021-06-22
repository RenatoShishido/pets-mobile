package com.example.petlife.fragment;

import android.Manifest;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.petlife.R;
import com.example.petlife.dao.PetDAO;
import com.example.petlife.entities.Pet;
import com.example.petlife.entities.Session;
import com.example.petlife.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CadastroPetFragment extends Fragment {

    EditText nomePET, racaPET, tipoPET, idadePET;
    ImageButton imageButtonPET;
    CheckBox castradoPET, vacinadoPET;
    Spinner sexoPET;
    Button doarBF;
    private Button btnTake, btnSelect, pegaFoto;
    private ImageView ivImage;
    private String imagemString = "";
    // Códigos auxiliares para tratamentos futuros
    private static final int PERMISSION_REQUEST_CODE = 200;
    private int GALLERY = 1, CAMERA = 2;


    Pet pet = new Pet();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doar, container, false);
        doarBF = view.findViewById(R.id.doarBF);

        nomePET = view.findViewById(R.id.nomePET);
        racaPET = view.findViewById(R.id.racaPET);
        tipoPET = view.findViewById(R.id.tipoPET);
        idadePET = view.findViewById(R.id.idadePET);
        castradoPET = view.findViewById(R.id.checkbox_castrado);
        vacinadoPET = view.findViewById(R.id.checkbox_vacinado);
        imageButtonPET = view.findViewById(R.id.imageButtonPET);
        pegaFoto = view.findViewById(R.id.pegaFoto);

        pegaFoto.setOnClickListener(v -> {
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA);
        });


        Spinner sexoPET = (Spinner) view.findViewById(R.id.spinner1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.sexo, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sexoPET.setAdapter(adapter);

        imageButtonPET.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, GALLERY);
        });
        doarBF.setOnClickListener(v -> {
            pet.setSexo(sexoPET.getSelectedItem().toString());
            salvarDados();
        });
        return view;
    }

    public void salvarDados() {
        List<String> erros = new ArrayList<String>();
        if (nomePET.getText().toString().isEmpty()){
            erros.add("Campo nome precisa ser preenchido");
            nomePET.getBackground().mutate().setColorFilter(ContextCompat.getColor(getActivity(),
                    R.color.design_default_color_error), PorterDuff.Mode.SRC_ATOP);
        }
        if (racaPET.getText().toString().isEmpty()) {
            erros.add("Campo raca precisa ser preenchido");
            racaPET.getBackground().mutate().setColorFilter(ContextCompat.getColor(getActivity(),
                    R.color.design_default_color_error), PorterDuff.Mode.SRC_ATOP);
        }
        if (idadePET.getText().toString().isEmpty()) {
            erros.add("Campo idade precisa ser preenchido");
            idadePET.getBackground().mutate().setColorFilter(ContextCompat.getColor(getActivity(),
                    R.color.design_default_color_error), PorterDuff.Mode.SRC_ATOP);
        }

        if (tipoPET.getText().toString().isEmpty()) {
            erros.add("Campo tipo precisa ser preenchido");
            tipoPET.getBackground().mutate().setColorFilter(ContextCompat.getColor(getActivity(),
                    R.color.design_default_color_error), PorterDuff.Mode.SRC_ATOP);
        }

        if(erros.size() > 0 ) {
            String message = "";
            for (int i = 0; i < erros.size() ; i++) {
                message +=  erros.get(i) + "\n";
            }

            Utils.aviso(getActivity(), message);
            return;
        }


        pet.setIdade(Integer.parseInt(idadePET.getText().toString()));
        pet.setNome(nomePET.getText().toString());
        pet.setRaca(racaPET.getText().toString());
        pet.setTipo(tipoPET.getText().toString());
        pet.setVacinado(vacinadoPET.isChecked() == true ? 1 : 0);
        pet.setCastrado(castradoPET.isChecked() == true ? 1 : 0);
        pet.setUserId(Session.getSession().getUsuario().getId());
        pet.setPetPictureUrl(imagemString);

        PetDAO petDAO = new PetDAO(getActivity());

        try {
            petDAO.insert(pet);
            Utils.aviso(getActivity(), "Pet cadastrado com sucesso");
            limparTela();
        } catch (Exception e) {
            Utils.aviso(getActivity(), e.getMessage());
        }
    }

    public void limparTela() {
        nomePET.setText("");
        racaPET.setText("");
        tipoPET.setText("");
        idadePET.setText("");
        vacinadoPET.setChecked(false);
        castradoPET.setChecked(false);
        pet.setUserId(null);
        imageButtonPET.setImageResource(R.drawable.ic_baseline_pets_24);
        pet.setPetPictureUrl("");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermission()) {
            // Success
        } else {
            requestPermission();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_CANCELED) {
            return;
        }
        if (requestCode == CAMERA) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageButtonPET.setImageBitmap(resizeImage(bitmap, 400, 150));

            saveImage(bitmap);

        } else if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),
                            contentURI);
                    imageButtonPET.setImageBitmap(resizeImage(bitmap, 400, 150));
                    saveImage(bitmap);


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Failed!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void saveImage(Bitmap bitmap) {

        byte[] imagemBytes;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, bytes);

        imagemBytes = bytes.toByteArray();

        imagemString = Base64.encodeToString(imagemBytes, Base64.DEFAULT);

    }

    public static Bitmap resizeImage(Bitmap bitmap, int newWidth, int newHeight) {
        Bitmap output = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Matrix m = new Matrix();
        m.setScale((float) newWidth / bitmap.getWidth(), (float) newHeight / bitmap.getHeight());
        canvas.drawBitmap(bitmap, m, new Paint());
        return output;
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Permission Granted",
                            Toast.LENGTH_SHORT).show();
                    // main logic
                } else {
                    Toast.makeText(getActivity(), "Permission Denied",
                            Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
