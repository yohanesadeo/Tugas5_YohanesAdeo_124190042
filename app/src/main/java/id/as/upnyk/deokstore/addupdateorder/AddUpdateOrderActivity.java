package id.as.upnyk.deokstore.addupdateorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import id.as.upnyk.deokstore.GameModel;
import id.as.upnyk.deokstore.R;
import id.as.upnyk.deokstore.db.AppDatabase;
import id.as.upnyk.deokstore.db.TopUp;

public class AddUpdateOrderActivity extends AppCompatActivity implements View.OnClickListener, AddUpdateView {

    private EditText etGame, etAmount;
    private Button btnSubmit, btnCancel;
    private ImageButton btnBack;
    private TextView tvTitle;

    private GameModel game;
    private TopUp topUp;
    private AddUpdatePresenter addUpdatePresenter;
    private AppDatabase appDatabase;

    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_order);

        etGame = findViewById(R.id.et_add_update_game);
        etAmount = findViewById(R.id.et_add_update_amount);
        btnSubmit = findViewById(R.id.btn_add_update_submit);
        btnCancel = findViewById(R.id.btn_add_update_cancel);
        btnBack = findViewById(R.id.btn_add_update_back);
        tvTitle = findViewById(R.id.tv_add_update_title);
        addUpdatePresenter = new AddUpdatePresenter(this);
        appDatabase = AppDatabase.getInstance(this);

        btnBack.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        game = getIntent().getParcelableExtra("EXTRA_GAME");
        if (game != null){
            etGame.setText(game.getTitle());
        }

        topUp = getIntent().getParcelableExtra("EXTRA_TOP_UP");
        if (topUp != null){
            isEdit = true;
            tvTitle.setText("Update Top Up");
            etGame.setText(topUp.getGameName());
            etAmount.setText(String.valueOf(topUp.getAmount()));
            btnSubmit.setText("UPDATE");
            btnCancel.setText("DELETE");
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnBack){
            finish();
        }
        if (v == btnCancel){
            if (isEdit){
                addUpdatePresenter.deleteOrder(appDatabase, topUp);
            } else {
                finish();
            }
        } else if (v == btnSubmit){
            if (orderIsCompleted()){
                if (isEdit){
                    topUp.setAmount(Integer.parseInt(etAmount.getText().toString().trim()));
                    addUpdatePresenter.updateOrder(appDatabase, topUp);
                } else {
                    addUpdatePresenter.insertOrder(appDatabase, etGame.getText().toString().trim(), Integer.parseInt(etAmount.getText().toString().trim()));
                }
            }
        }
    }

    @Override
    public void onSuccessAdd() {
        Toast.makeText(this, "Success add order to Cart", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onSuccessDelete() {
        Toast.makeText(this, "Success delete order", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onSuccessUpdate() {
        Toast.makeText(this, "Success update order", Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean orderIsCompleted() {
        return !etGame.getText().toString().trim().isEmpty()
                && !etAmount.getText().toString().isEmpty()
                && Integer.parseInt(etAmount.getText().toString().trim()) != 0;
    }
}