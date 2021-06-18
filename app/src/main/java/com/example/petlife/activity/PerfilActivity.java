package com.example.petlife.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petlife.R;
import com.example.petlife.dao.UsuarioDAO;
import com.example.petlife.entities.Session;
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

            UsuarioDAO userDAO1 = new UsuarioDAO(this);

            try {
//                userDAO.insert(user);
                Utils.aviso(this, "Usuario alterado com sucesso");
            } catch (Exception e) {
                Utils.aviso(this, e.getMessage());
            }

        });

    }

    public void inicializarItens() {
        email = findViewById(R.id.emailPerfil);
        password = findViewById(R.id.passwordPerfil);
        nome = findViewById(R.id.nomePerfil);
        endereco = findViewById(R.id.enderecoPerfil);
        telefone = findViewById(R.id.telefonePerfil);
        btnAlterar = findViewById(R.id.btnAlterar);

        Usuario usuario = Session.getSession().getUsuario();

        if(Session.getSession().isLogged()){
            if(usuario.getEmail() != null)
                email.setText(usuario.getEmail());
            if(usuario.getPassword() != null)
                password.setText(usuario.getPassword());
            if(usuario.getNome() != null)
                nome.setText(usuario.getNome());
            if(usuario.getEndereco() != null)
                endereco.setText(usuario.getEndereco());
            if(usuario.getTelefone() != null)
                telefone.setText(usuario.getTelefone());

        }



    }
}