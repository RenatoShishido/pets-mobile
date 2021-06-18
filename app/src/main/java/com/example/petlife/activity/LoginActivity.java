package com.example.petlife.activity;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;

import com.example.petlife.R;
import com.example.petlife.dao.UsuarioDAO;
import com.example.petlife.entities.Session;
import com.example.petlife.entities.Usuario;
import com.example.petlife.utils.Utils;


public class LoginActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText email, password;
    Button btnFazerCadastro;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);

        Button btnFazerLogin = findViewById(R.id.login);
        btnFazerCadastro = findViewById(R.id.btnFazerCadastro);

        btnFazerLogin.setOnClickListener(v -> {
            Usuario user = new Usuario();
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());

            UsuarioDAO userDAO = new UsuarioDAO(this);

            if(userDAO.auth(user)) {
                Session session = Session.getSession();
                UsuarioDAO usuarioDAO = new UsuarioDAO(this);
                session.setUsuario(usuarioDAO.getByEmail(user.getEmail()));
                session.setIsLogged(true);
                Intent it = new Intent(this, MainActivity.class);
                startActivity(it);
                Utils.aviso(this, "Usuario logado com sucesso");
            }

            Utils.aviso(this, "Usuario nao existe");

        });

        btnFazerCadastro.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(i);
        });
    }


}