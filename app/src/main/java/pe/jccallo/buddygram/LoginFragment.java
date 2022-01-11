package pe.jccallo.buddygram;

import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        // identificamos campos de pasword
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);

        // identificamos al boton de login
        MaterialButton loginButton = view.findViewById(R.id.login_button);

        // validar si la contraseña contiene 6 o mas caracteres.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(getString(R.string.login_error_message));
                } else {
                    passwordTextInput.setError(null); // limpiar el error
                    ((NavigationHost) getActivity()).navigateTo(new HomeFragment(), false);
                }
            }
        });

        // despues de cometer un error
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(null);
                }
                return false;
            }
        });

        return view;
    }

    // valida el password
    private boolean isPasswordValid(@Nullable Editable text) { return text != null && text.length() >= 6;}
}
