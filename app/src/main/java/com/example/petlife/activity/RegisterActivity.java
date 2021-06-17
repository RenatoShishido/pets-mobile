package com.example.petlife.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.petlife.R;
import com.example.petlife.dao.UsuarioDAO;
import com.example.petlife.entities.Session;
import com.example.petlife.entities.Usuario;
import com.example.petlife.utils.Utils;

public class RegisterActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText emailCadastro, passwordCadastro, nome , endereco, telefone;
    Button btnFazerLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnFazerLogin =  findViewById(R.id.btnFazerLogin);
        emailCadastro = findViewById(R.id.emailCadastro);
        passwordCadastro = findViewById(R.id.passwordCadastro);
        nome = findViewById(R.id.nome);
        endereco = findViewById(R.id.endereco);
        telefone = findViewById(R.id.telefone);

        Button btnFazerCadastro = findViewById(R.id.cadastro);


        btnFazerCadastro.setOnClickListener(v -> {

            Usuario user = new Usuario();
            user.setEmail(emailCadastro.getText().toString());
            user.setPassword(passwordCadastro.getText().toString());
            user.setNome(nome.getText().toString());
            user.setEndereco(endereco.getText().toString());
            user.setTelefone(telefone.getText().toString());

            UsuarioDAO userDAO = new UsuarioDAO(this);

            try {
                userDAO.insert(user);
                Utils.aviso(this, "Usuario cadastrado com sucesso");
            } catch (Exception e) {
                Utils.aviso(this, e.getMessage());
            }

        });
        btnFazerLogin.setOnClickListener(v -> {
            Intent it = new Intent(this, LoginActivity.class);
            startActivity(it);
        });
    }
}