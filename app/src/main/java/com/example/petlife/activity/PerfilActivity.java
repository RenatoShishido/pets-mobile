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
import org.mindrot.jbcrypt.BCrypt;

public class PerfilActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText emailPerfil, passwordPerfil, nomePerfil , enderecoPerfil, telefonePerfil;
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
            user.setId(Session.getSession().getUsuario().getId());
            user.setEmail(emailPerfil.getText().toString());
            user.setPassword(passwordPerfil.getText().toString());
            user.setNome(nomePerfil.getText().toString());
            user.setEndereco(enderecoPerfil.getText().toString());
            user.setTelefone(telefonePerfil.getText().toString());

            UsuarioDAO userDAO = new UsuarioDAO(this);

            try {
                userDAO.update(user);
                Utils.aviso(this, "Usuario alterado com sucesso");
                Session.getSession().setUsuario(user);
            } catch (Exception e) {
                Utils.aviso(this, e.getMessage());
            }

        });

    }

    public void inicializarItens() {
        emailPerfil = findViewById(R.id.emailPerfil);
        passwordPerfil = findViewById(R.id.passwordPerfil);
        nomePerfil = findViewById(R.id.nomePerfil);
        enderecoPerfil = findViewById(R.id.enderecoPerfil);
        telefonePerfil = findViewById(R.id.telefonePerfil);
        btnAlterar = findViewById(R.id.btnAlterar);

        Usuario usuario = Session.getSession().getUsuario();

        if(Session.getSession().isLogged()){
            if(usuario.getEmail() != null)
                emailPerfil.setText(usuario.getEmail());
            if(usuario.getPassword() != null)
                passwordPerfil.setText(usuario.getPassword());
            if(usuario.getNome() != null)
                nomePerfil.setText(usuario.getNome());
            if(usuario.getEndereco() != null)
                enderecoPerfil.setText(usuario.getEndereco());
            if(usuario.getTelefone() != null)
                telefonePerfil.setText(usuario.getTelefone());

        }



    }
}