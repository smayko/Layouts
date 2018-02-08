package rs.aleph.android.example12.activities.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import rs.aleph.android.example12.R;

/**
 * Created by nikola.kosmajac on 2/8/2018.
 */

public class DialogAboutFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_add_circle_outline_white_24dp)
                .setMessage(R.string.about)
                .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create();

    }

    public static DialogAboutFragment newInstance(int title) {
        DialogAboutFragment frag = new DialogAboutFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }
}
