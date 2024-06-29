package com.harsh.sharespace;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookWorkspaceActivity extends AppCompatActivity {

    ImageView ivWorkspaceCoverImage;
    TextView tvWorkspaceName, tvWorkspaceResources, tvWorkspaceOwnerName, tvWorkspacePricePerDay, tvWorkspaceTotalCost;

    Button btnStartDate, btnEndDate, btnProceedPayment;

    String startDate = "", endDate = "";
    Double totalCost = 0.0;
    int sDay=0, sMonth=0, sYear=0;
    int eDay=0, eMonth=0, eYear=0;

    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_workspace);

        ivWorkspaceCoverImage = findViewById(R.id.iv_book_workspace_cover_image);
        tvWorkspaceName = findViewById(R.id.tv_book_workspace_name);
        tvWorkspaceResources = findViewById(R.id.tv_book_workspace_resources);
        tvWorkspaceOwnerName = findViewById(R.id.tv_book_workspace_owner_name);
        tvWorkspacePricePerDay = findViewById(R.id.tv_book_workspace_price_per_day);
        tvWorkspaceTotalCost = findViewById(R.id.tv_book_workspace_total_cost);
        btnStartDate = findViewById(R.id.btn_book_start_date);
        btnEndDate = findViewById(R.id.btn_book_end_date);
        btnProceedPayment = findViewById(R.id.btn_book_proceed_payment);

        btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String todayDate = sdf.format(Calendar.getInstance().getTime());
                String today[] = todayDate.split("-");
                DatePickerDialog dp = new DatePickerDialog(BookWorkspaceActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1 = (i1 + 1);
                        startDate = i + "-" + i1 + "-" + i2;
                        String[] d = startDate.split("-");
                        if (Integer.parseInt(d[0]) <= 9) {
                            d[0] = "0" + d[0];
                        }
                        if (Integer.parseInt(d[1]) <= 9) {
                            d[1] = "0" + d[1];
                        }
                        if (Integer.parseInt(d[2]) <= 9) {
                            d[2] = "0" + d[2];
                        }
                        sDay = Integer.parseInt(d[2]);
                        sMonth = Integer.parseInt(d[1]);
                        sYear = Integer.parseInt(d[0]);
                        startDate = d[0] + "-" + d[1] + "-" + d[2];
                        btnStartDate.setText(i2 + "-" + i1 + "-" + i);
                        if (!btnStartDate.getText().toString().endsWith("E") && !btnEndDate.getText().toString().endsWith("E")) {
                            if(validateDate()){
                                setTotalCost();
                            }else{
                                Toast.makeText(BookWorkspaceActivity.this, "Select End date greater than Start date!", Toast.LENGTH_SHORT).show();
                                btnProceedPayment.setEnabled(false);
                            }
                        }
                    }
                }, Integer.parseInt(today[0]), Integer.parseInt((today[1])) - 1, Integer.parseInt(today[2]));
                dp.show();
            }
        });
        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String todayDate = sdf.format(Calendar.getInstance().getTime());
                String today[] = todayDate.split("-");
                DatePickerDialog dp = new DatePickerDialog(BookWorkspaceActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1 = (i1 + 1);
                        endDate = i + "-" + i1 + "-" + i2;
                        String[] d = endDate.split("-");
                        if (Integer.parseInt(d[0]) <= 9) {
                            d[0] = "0" + d[0];
                        }
                        if (Integer.parseInt(d[1]) <= 9) {
                            d[1] = "0" + d[1];
                        }
                        if (Integer.parseInt(d[2]) <= 9) {
                            d[2] = "0" + d[2];
                        }
                        eDay = Integer.parseInt(d[2]);
                        eMonth = Integer.parseInt(d[1]);
                        eYear = Integer.parseInt(d[0]);
                        endDate = d[0] + "-" + d[1] + "-" + d[2];
                        btnEndDate.setText(i2 + "-" + i1 + "-" + i);
                        if (!btnStartDate.getText().toString().endsWith("E") && !btnEndDate.getText().toString().endsWith("E")) {
                            if(validateDate()){
                                setTotalCost();
                            }else{
                                Toast.makeText(BookWorkspaceActivity.this, "Select End date greater than Start date!", Toast.LENGTH_SHORT).show();
                                btnProceedPayment.setEnabled(false);
                            }
                        }
                    }
                }, Integer.parseInt(today[0]), Integer.parseInt((today[1])) - 1, Integer.parseInt(today[2]));
                dp.show();
            }
        });
        getWindow().setNavigationBarColor(getResources().getColor(R.color.all_bg));
    }
    public void setTotalCost(){
        btnProceedPayment.setEnabled(true);
        tvWorkspaceTotalCost.setVisibility(View.VISIBLE);
        tvWorkspaceTotalCost.setText("Total Cost: ₹"+totalCost);
    }
    public Boolean validateDate(){
        if(eYear>=sYear){
            if(eMonth>=sMonth){
                if(eDay>=sDay){
                    return true;
                }
            }
        }
        return false;
    }
}