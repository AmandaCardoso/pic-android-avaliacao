package com.example.administrador.projeto1.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.projeto1.R;
import com.example.administrador.projeto1.model.entities.Client;
import com.example.administrador.projeto1.model.entities.ClientAddress;
import com.example.administrador.projeto1.model.entities.Usuario;
import com.example.administrador.projeto1.model.persistence.UsuarioContract;
import com.example.administrador.projeto1.util.FormHelper;

/**
 * Created by Administrador on 20/07/2015.
 */
public class LoginActivity extends AppCompatActivity {

    Button buttonLogin;
    private EditText txtLogin;
    private EditText txtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindFildes();
        bindLoginButton();
    }

    private void bindLoginButton() {
        buttonLogin = (Button) findViewById(R.id.btnLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (FormHelper.requiredValidate(LoginActivity.this, txtLogin, txtSenha)) {
                    Usuario user = bindUsuario();
                    if(UsuarioContract.vericacaoLogin(user)){
                        Intent intent = new Intent(LoginActivity.this, ClientListActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, R.string.success, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(LoginActivity.this, R.string.excessao, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private Usuario bindUsuario() {

        Usuario users = new Usuario();

        users.setName(txtLogin.getText().toString());
        users.setSenha(txtSenha.getText().toString());
        return users;
    }

    private void bindFildes() {
        txtLogin = (EditText) findViewById(R.id.login);
        txtSenha = (EditText) findViewById(R.id.password);
    }
}

