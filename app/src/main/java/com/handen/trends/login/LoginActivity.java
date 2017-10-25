package com.handen.trends.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.handen.trends.R;


public class LoginActivity extends AppCompatActivity implements SignInFragment.SignInInterfaceListener,
        RegistrationFragment.RegistrationInterfaceListener {

    private final String REGISTRATION_FRAGMENT_TAG = "registration";
    private final String SIGNIN_FRAGMENT_TAG = "signIn";

    private FrameLayout fragmentHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fragmentHost = (FrameLayout) findViewById(R.id.fragment_host_login_activity);
        displayFragment(RegistrationFragment.newInstance(), REGISTRATION_FRAGMENT_TAG);

    }

    /**
     * Метод, который отображает или заменяет старый фрагмент на новый фрагмент
     *
     * @param fragment
     */
    public void displayFragment(Fragment fragment, String TAG) {

        FragmentManager fragmentManager = LoginActivity.this.getSupportFragmentManager();

        //Получаем текущий фрагмент
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_host_login_activity);

        if(currentFragment == null) {
            fragmentManager
                    .beginTransaction()
//                    .addToBackStack(TAG)
                    .replace(R.id.fragment_host_login_activity, fragment, TAG)
                    .commit();
            return;
        }

        if (!TAG.equals(currentFragment.getTag())) {
            fragmentManager
                    .beginTransaction()
 //                   .addToBackStack(TAG)
                    .replace(R.id.fragment_host_login_activity, fragment, TAG)
                    .commit();
        }

    }

    @Override
    public void gotoSignIn() {
        displayFragment(new SignInFragment(), SIGNIN_FRAGMENT_TAG);
    }

    @Override
    public void gotoRegistration() {
        displayFragment(new RegistrationFragment(), REGISTRATION_FRAGMENT_TAG);
    }
}
