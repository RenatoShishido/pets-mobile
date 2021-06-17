package com.example.petlife.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.petlife.R;
import com.example.petlife.dao.UsuarioDAO;
import com.example.petlife.entities.Usuario;
import com.example.petlife.utils.Utils;

public class PerfilActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText email, password, nome , endereco, telefone;
    Button btnAlterar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inicializarItens();

        btnAlterar.setOnClickListener(v -> {
            Usuario user = new Usuario();
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());
            user.setNome(nome.getText().toString());
            user.setEndereco(endereco.getText().toString());
            user.setTelefone(telefone.getText().toString());

            UsuarioDAO userDAO = new UsuarioDAO(this);

            try {
//                userDAO.insert(user);
                Utils.aviso(this, "Usuario Editado com sucesso");
            } catch (Exception e) {
                Utils.aviso(this, e.getMessage());
            }

        });

    }

    public void inicializarItens() {
        email = findViewById(R.id.emailCadastro);
        password = findViewById(R.id.passwordCadastro);
        nome = findViewById(R.id.nome);
        endereco = findViewById(R.id.endereco);
        telefone = findViewById(R.id.telefone);
        btnAlterar = findViewById(R.id.btnAlterar);

    }
}