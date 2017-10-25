package com.handen.trends.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.handen.trends.MainActivity;
import com.handen.trends.R;

public class RegistrationFragment extends Fragment {


    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private Button gotoSignInButton;



    private RegistrationInterfaceListener mListener;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment RegistrationFragment.
     */

    public static RegistrationFragment newInstance() {
        RegistrationFragment fragment = new RegistrationFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        emailEditText = (EditText) view.findViewById(R.id.edit_text_email);
        passwordEditText = (EditText) view.findViewById(R.id.edit_text_password);
        registerButton = (Button) view.findViewById(R.id.button_registration);
        gotoSignInButton = (Button) view.findViewById(R.id.button_goto_signin);

        gotoSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoSignIn();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegistration();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RegistrationInterfaceListener) {
            mListener = (RegistrationInterfaceListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void startRegistration()
    {
        Intent intent = new Intent(getContext(), MainActivity.class);
        
        startActivity(intent);
        Toast.makeText(getContext(), "startRegistration", Toast.LENGTH_SHORT).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface RegistrationInterfaceListener {

        void gotoSignIn();
    }
}
