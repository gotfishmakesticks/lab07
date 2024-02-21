package g313.mirenkov.lab07;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edit_key, edit_value;
    database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_key = findViewById(R.id.edit_key);
        edit_value = findViewById(R.id.edit_value);
        db = new database(this, "qbase.db", null, 1);
    }

    public void on_select(View v){
        String key = edit_key.getText().toString();
        if (key.equals("")) {
            Toast.makeText(this, "No key or value provided", Toast.LENGTH_LONG).show();
            return;
        }
        String value = db.select(key);
        if (value == null) {
            Toast.makeText(this, "Key does not exist in this database", Toast.LENGTH_LONG).show();
            return;
        }
        edit_value.setText(value);
    }

    public void on_insert(View v) {
        String key = edit_key.getText().toString();
        String value = edit_value.getText().toString();
        if (key.equals("") || value.equals("")) {
            Toast.makeText(this, "No key or value provided", Toast.LENGTH_LONG).show();
            return;
        }
        try {
            db.insert(key, value);
            Toast.makeText(this, "Successfully inserted key", Toast.LENGTH_LONG).show();
        } catch(Exception e) {
            show_exception(e);
        }
    }

    public void on_delete(View v) {
        String key = edit_key.getText().toString();
        if (key.equals("")) {
            Toast.makeText(this, "No key provided", Toast.LENGTH_LONG).show();
            return;
        }
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Deleting key");
        ad.setMessage("Are you sure you want to delete this key?");
        ad.setPositiveButton(android.R.string.yes, (dialog, which) -> {
            try {
                db.delete(key);
                Toast.makeText(this, "Successfully deleted key", Toast.LENGTH_LONG).show();
                edit_key.setText("");
                edit_value.setText("");
            }
            catch(Exception e) {
                show_exception(e);
            }
        });
        ad.setNegativeButton(android.R.string.no, null);
        ad.setIcon(android.R.drawable.ic_dialog_alert);
        ad.show();
    }

    public void on_update(View v) {
        String key = edit_key.getText().toString();
        String value = edit_value.getText().toString();
        if (key.equals("") || value.equals("")) {
            Toast.makeText(this, "No key or value provided", Toast.LENGTH_LONG).show();
            return;
        }
        try {
            db.update(key, value);
            Toast.makeText(this, "Successfully updated value", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            show_exception(e);
        }
    }

    public void show_exception(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }
}