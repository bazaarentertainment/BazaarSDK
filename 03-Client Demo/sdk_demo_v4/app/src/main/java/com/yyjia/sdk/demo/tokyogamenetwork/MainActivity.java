package com.yyjia.sdk.demo.tokyogamenetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yyjia.sdk.center.GMcenter;
import com.yyjia.sdk.data.Information;
import com.yyjia.sdk.listener.ExitGameListener;
import com.yyjia.sdk.listener.InitListener;
import com.yyjia.sdk.listener.LoginListener;
import com.yyjia.sdk.listener.PayListener;
import com.yyjia.sdk.util.Utils;

public class MainActivity extends Activity {

    private GMcenter mCenter = null;
    private Button loginBtn, createRoleBtn, updateRoleBtn, payOneBtn, payTwoBtn, btnLogin, btnExitGame;

    private String roleId;
    private String roleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        if (mCenter == null) {
            mCenter = GMcenter.getInstance(MainActivity.this);
            // 登录登出监听器
            mCenter.setLoginListener(new LoginListener() {
                // 登录监听方法
                @Override
                public void loginSuccessed(String code) {
                    if (code == Information.LOGIN_SUSECCEDS) {
                        // SDK登录成功 游戏(服务端)请求 登录验证
                        ToastUtil.showShortToast(MainActivity.this, "LOGIN SUCCESS");
                        mCenter.showFloatingView(MainActivity.this);
                    } else {
                        ToastUtil.showShortToast(MainActivity.this, "LOGIN FAIL");
                    }
                }

                // 登出监听方法
                public void logoutSuccessed(String code) {
                    if (code == Information.LOGOUT_SUSECCED) {
                        // 账号 退出 游戏需要重启到 登录界面
                        ToastUtil.showShortToast(MainActivity.this, "EXIT SUCCESS");
                    } else {
                        ToastUtil.showShortToast(MainActivity.this, "EXIT FAIL");
                    }
                }

                @Override
                public void logcancelSuccessed(String code) {
                    if (code == Information.LOGCANCEL_SUSECCED) {
                        ToastUtil.showShortToast(MainActivity.this, "CACEL LOGIN");
                    }
                }
            });

            mCenter.setInitListener(new InitListener() {

                @Override
                public void onSuccess(int code) {
                    mCenter.checkLogin();
                }

                @Override
                public void onFailure(int code, String errMsg) {
                    if (code == Information.CODE_GET_APP_ID_ERROR) {
                        // 获取AppId失败
                    } else if (code == Information.CODE_NET_TIME_OUT) {
                        // 网络连接超时
                    } else if (code == Information.CODE_SERVER_RETURN_DATA_ERROR) {
                        // 服务端返回数据错误
                    }
                    ToastUtil.showShortToast(MainActivity.this, errMsg);
                }
            });

            // 游戏退出监听方法(当调用exitDialog()方法，必须设置此监听)
            mCenter.setExitGameListener(new ExitGameListener() {
                @Override
                public void onSuccess() {
                    ToastUtil.showShortToast(MainActivity.this, "Exit Game Success");
                }

                @Override
                public void onFailure() {
                    ToastUtil.showShortToast(MainActivity.this, "Exit Game Failure");
                }

                @Override
                public void onCancel() {
                    ToastUtil.showShortToast(MainActivity.this, "Exit Game Cancel");
                }
            });
        }
        Utils.E("onCreate");
        mCenter.onCreate(MainActivity.this);
    }

    private void initView() {
        loginBtn = (Button) findViewById(R.id.logout);
        createRoleBtn = (Button) findViewById(R.id.btn_create_role);
        updateRoleBtn = (Button) findViewById(R.id.btn_update_role);
        payOneBtn = (Button) findViewById(R.id.btn_toPay_oneyuan);
        payTwoBtn = (Button) findViewById(R.id.btn_toPay_twoyuan);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnExitGame = (Button) findViewById(R.id.btn_exit_game);
        loginBtn.setOnClickListener(onClick);
        createRoleBtn.setOnClickListener(onClick);
        updateRoleBtn.setOnClickListener(onClick);
        payOneBtn.setOnClickListener(onClick);
        payTwoBtn.setOnClickListener(onClick);
        btnLogin.setOnClickListener(onClick);
        btnExitGame.setOnClickListener(onClick);
    }

    private View.OnClickListener onClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnLogin:
                    mCenter.checkLogin();
                    break;
                case R.id.btn_create_role:
                    roleId = System.currentTimeMillis() / 1000 + ""; // 游戏中请填写真实的角色ID
                    roleName = System.currentTimeMillis() / 1000 + "";// 游戏中请填写真实的角色名称
                    mCenter.submitRoleInfo("1", "1", roleId, roleName, "1", System.currentTimeMillis() + "");
                    break;
                case R.id.btn_update_role:
                    mCenter.submitRoleInfo("1", "1", roleId, roleName, "20", System.currentTimeMillis() + "");
                    break;
                case R.id.btn_toPay_oneyuan:
                    mCenter.pay(MainActivity.this, 1.0f, "test", "1", "1",
                            System.currentTimeMillis() + "", "123456", payListener);
                    break;
                case R.id.btn_toPay_twoyuan:
                    //WebView wview=new WebView(MainActivity.this);
                    //wview.loadUrl("http://fb.6816.com/tool/");
                    mCenter.pay(MainActivity.this, 2.0f, "test", "1", "1", System.currentTimeMillis() + "", "123456", payListener);
                    break;
                case R.id.logout:
                    mCenter.logout();
                    break;
                case R.id.btn_exit_game:
                    // 如果游戏没有自己的退出对话框，则调用exitDialog()这个方法
                    mCenter.exitDialog(MainActivity.this);
                    // 如果有自己的退出框，则调用exitGame这个方法
                    // 只要游戏能确保正常退出的时候能走onDestroy()方法,就在onDestroy()的方法里的super.onDestrot()之前调用，不能的话再游戏确认退出的时候调用
//                    mCenter.exitGame(MainActivity.this);
                    break;
                default:
                    break;
            }
        }
    };

    private PayListener payListener = new PayListener() {

        @Override
        public void paySuccessed(String code, String cporderid) {
            if (code == Information.PAY_SUSECCED) {
                ToastUtil.showShortToast(MainActivity.this, "PAY SUCCESS");
            } else {
                ToastUtil.showShortToast(MainActivity.this, "PAY FAIL");
            }
        }

        @Override
        public void payGoBack() {
            ToastUtil.showShortToast(MainActivity.this, "PAY BACK");
        }
    };

    @Override
    public void finish() {
        super.finish();
        Utils.E("onFinish");
        exitGame();
        System.exit(0);
    }

    private void exitGame() {
        mCenter.exitGame(this);
        mCenter = null;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

    }

    // 在onPause里调用
    @Override
    protected void onPause() {
        super.onPause();
        Utils.E("onPause");
        if (mCenter != null) {
            mCenter.onPause(MainActivity.this);
            mCenter.hideFloatingView(this);
        }
    }

    // 在onStop里调用
    @Override
    protected void onStop() {
        super.onStop();
        Utils.E("onStop");
        if (mCenter != null) {
            mCenter.hideFloatingView(this);
            mCenter.onStop(MainActivity.this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mCenter != null) {  // 必须在super.onDestroy()之前调用
            mCenter.onDestroy(this);
        }
        super.onDestroy();
        Utils.E("onDestroy");
    }

    // 在onResume里调用
    @Override
    protected void onResume() {
        super.onResume();
        Utils.E("onResume");
        if (mCenter != null) {
            mCenter.onResume(MainActivity.this);
            mCenter.showFloatingView(MainActivity.this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mCenter != null) {
            mCenter.onActivityResult(this, requestCode, resultCode, data);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Utils.E("onStart");
        mCenter.onStart(MainActivity.this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Utils.E("onRestart");
        mCenter.onRestart(MainActivity.this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
