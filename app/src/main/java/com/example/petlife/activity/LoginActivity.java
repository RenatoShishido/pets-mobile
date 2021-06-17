package com.example.petlife.activity;

import android.app.Activity;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petlife.R;
import com.example.petlife.dao.UsuarioDAO;
import com.example.petlife.entities.Session;
import com.example.petlife.entities.Usuario;
import com.example.petlife.utils.Utils;

import java.time.Duration;


public class LoginActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText email, password, emailCadastro, passwordCadastro, nome , endereco, telefone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        emailCadastro = findViewById(R.id.emailCadastro);
        passwordCadastro = findViewById(R.id.passwordCadastro);
        nome = findViewById(R.id.nome);
        endereco = findViewById(R.id.endereco);
        telefone = findViewById(R.id.telefone);

        Button btnFazerLogin = findViewById(R.id.login);

        Button btnFazerCadastro = findViewById(R.id.cadastro);

        btnFazerLogin.setOnClickListener(v -> {
            Usuario user = new Usuario();
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());

            UsuarioDAO userDAO = new UsuarioDAO(this);

            if(userDAO.auth(user)) {
                Session session = Session.getSession();
                session.setUsuario(user);
                session.setIsLogged(true);
                Intent it = new Intent(this, MainActivity.class);
                startActivity(it);

                Utils.aviso(this, "Usuario logado com sucesso");
            }
        });


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
    }


}