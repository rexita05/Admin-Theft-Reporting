package com.example.rexita_pc.admin.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.rexita_pc.admin.Activity.SigninActivity;
import com.example.rexita_pc.admin.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends Fragment {

    Button btnLogout;
    ImageView mImage;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_logout, container, false);

        mImage = (ImageView)rootView.findViewById(R.id.user);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        mAuth = FirebaseAuth.getInstance();
//        Uri url = mAuth.getCurrentUser().getPhotoUrl();
//        Glide.with(getActivity()).load(url).thumbnail(0.5f).into(mImage);
        btnLogout = (Button)rootView.findViewById(R.id.btnLogout);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    private void signOut() {
        // Firebase sign out
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Apa anda yakin ingin keluar ?")
                .setCancelable(false)
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(getActivity(), SigninActivity.class));
                        mAuth.signOut();
                        getActivity().finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
