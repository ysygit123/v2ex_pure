package org.github.v2ex;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.github.v2ex.activities.LoginActivity;
import org.github.v2ex.utils.SnackbarUtil;
import org.github.v2ex.views.RoundedImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shaoyi yu on 2015/12/20.
 */
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.id_drawerlayout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.id_navigationview)
    NavigationView mNavigationView;
    @Bind(R.id.id_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.id_tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.id_viewpager)
    ViewPager mViewPager;
    @Bind(R.id.id_header)
    RoundedImageView mImageView_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * 初始化view
     */
    private void initView() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbar.setOnMenuItemClickListener(onMenuItemClick);
        mImageView_photo.setOnClickListener(loginListener);
        ActionBarDrawerToggle mActionBarDrawerToggle =
                new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.main_open, R.string.main_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        //导航菜单点击事件
        onNavgationViewMenuItemSelected(mNavigationView);
    }

    /**
     * 设置NavigationView中menu的item被选中后要执行的操作
     *
     * @param mNav
     */
    private void onNavgationViewMenuItemSelected(final NavigationView mNav) {
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                String msgString = "";
                switch (menuItem.getItemId()) {
                    case R.id.nav_menu_favourite:
                        msgString = (String) menuItem.getTitle();
                        break;
                    case R.id.nav_menu_notes:
                        msgString = (String) menuItem.getTitle();
                        break;
                    case R.id.nav_menu_setting:
                        msgString = (String) menuItem.getTitle();
                        break;
                    default:
                        break;
                }

                // Menu item点击后选中，并关闭Drawerlayout
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                SnackbarUtil.show(mNav, msgString, 0);

                return true;
            }
        });
    }

    /**
     * Toolbar icon单击事件监听器
     */
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_edit:
                    msg += "Click edit";
                    break;
                case R.id.action_share:
                    msg += "Click share";
                    break;
                case R.id.action_settings:
                    msg += "Click setting";
                    break;
                default:
                    break;
            }
            if (!msg.equals("")) {
                SnackbarUtil.show(mToolbar, msg, 0);
            }
            return true;
        }
    };

    /**
     * 点击用户头像登陆事件监听器
     */
    private View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    };

}
