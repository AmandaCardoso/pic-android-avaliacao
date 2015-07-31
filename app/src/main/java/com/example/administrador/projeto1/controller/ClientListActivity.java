package com.example.administrador.projeto1.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrador.projeto1.R;
import com.example.administrador.projeto1.model.entities.Client;
import com.example.administrador.projeto1.model.persistence.MemoryClientRepository;
import com.example.administrador.projeto1.model.persistence.SQLiteClientRepositiry;
import com.melnykov.fab.FloatingActionButton;

import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.List;


public class ClientListActivity extends AppCompatActivity {

    private static final String TAG = ClientListActivity.class.getSimpleName();
    private ListView listClient;
    private Client client;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindClientList();
        bindFab();
    }

    private void bindFab(){
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientListActivity.this, SaveClientActivity.class);
                startActivity(intent);
            }
        });
    }

    private void bindClientList() {
    /*
    ListView listViewClients = (ListView) findViewById(R.id.listViewClients);
    listViewClients.setAdapter(new ArrayAdapter<String>(ClientListActivity.this,
            android.R.layout.simple_list_item_1, Arrays.asList("Nome 1", "Nome 2", "Nome 3")));
    */
        listClient = (ListView) findViewById(R.id.listViewClients);
        final ClientListAdapter adapter = new ClientListAdapter(ClientListActivity.this, new ArrayList<Client>());
        listClient.setAdapter(adapter);

        listClient.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                client = (Client) parent.getItemAtPosition(position);
                return false;
            }
        });
        listClient.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Client client = (Client) parent.getItemAtPosition(position);
            // Best Practices: http://stackoverflow.com/questions/4275678/how-to-make-phone-call-using-intent-in-android
            final Intent goToSOPhoneCall = new Intent(Intent.ACTION_CALL /* or Intent.ACTION_DIAL (no manifest permission needed) */);
            goToSOPhoneCall.setData(Uri.parse("tel:" + client.getPhone()));
            startActivity(goToSOPhoneCall);
        }
    });

                registerForContextMenu(listClient);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_client_list_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuRemove) {
            new AlertDialog.Builder(ClientListActivity.this)
                    .setMessage(getString(R.string.confirm_progress))
                    .setTitle(getString(R.string.confirm))
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            client.delete();
                            refreshClientList();
                            Toast.makeText(ClientListActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            })
                    .create()
                    .show();
        } else if (item.getItemId() == R.id.menuEdit) {
            Intent intent = new Intent(ClientListActivity.this, SaveClientActivity.class);
            intent.putExtra(SaveClientActivity.CLIENT_PARAM, (Parcelable) client);
            startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            // Create the text message with a string
            final Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Seu texto aqui...");
            sendIntent.setType(HTTP.PLAIN_TEXT_TYPE);

            // Create intent to show the chooser dialog
            final Intent chooser = Intent.createChooser(sendIntent, "Titulo Chooser");

            // Verify the original intent will resolve to at least one activity
            if (sendIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooser);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshClientList();
    }

    private void refreshClientList() {
        ClientListAdapter adapter = (ClientListAdapter) listClient.getAdapter();
        adapter.setClients(Client.getAll());
        adapter.notifyDataSetChanged();
    }

}
