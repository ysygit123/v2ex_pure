package org.github.v2ex.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import org.github.v2ex.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by syyu on 2015/12/20
 * 登录
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.login_username)
    EditText mEt_username;
    @Bind(R.id.login_password)
    EditText mEt_password;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    /**
     *  初始化
     */
    private void initView() {
        ButterKnife.bind(this);
        mToolbar.setTitle(getResources().getString(R.string.login_btn_confirm));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
