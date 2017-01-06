package com.example.a1084081.oriondemoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.a1084081.oriondemoapp.model.User;

public class DetailActivity extends AppCompatActivity {

    TextView mUserName,mPhone,mAddress,mWebsite, mCompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        User user = (User) getIntent().getSerializableExtra("user");
        mUserName = (TextView) findViewById(R.id.textViewName);
        mAddress = (TextView) findViewById(R.id.textViewAddress);
        mPhone = (TextView) findViewById(R.id.textViewPhone);
        mWebsite = (TextView) findViewById(R.id.textViewWebsite);
        mCompany = (TextView) findViewById(R.id.textViewCompany);

        mUserName.setText(user.getUsername());
        mAddress.setText(user.getAddress().getSuite()+" "+user.getAddress().getStreet()+"\n"+user.getAddress().getCity()+" "+user.getAddress().getZipcode());
        mPhone.setText(user.getPhone());
        mWebsite.setText(user.getWebsite());
        mCompany.setText(user.getCompany().getName()+"\n"+user.getCompany().getCatchPhrase()+"\n"+user.getCompany().getBs());


    }
}
