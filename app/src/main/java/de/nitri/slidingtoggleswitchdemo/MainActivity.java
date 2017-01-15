package de.nitri.slidingtoggleswitchdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import de.nitri.slidingtoggleswitch.SlidingToggleSwitchView;
import de.nitri.slidingtoggleswitch.SlidingToggleSwitchView.OnToggleListener;

public class MainActivity extends AppCompatActivity implements OnToggleListener {


    private TextView tvSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ToggleDemoFragment()).commit();
        }

    }

    public static class ToggleDemoFragment extends Fragment {

        public ToggleDemoFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container,
                    false);
            ((MainActivity) getActivity()).tvSelected = (TextView) rootView
                    .findViewById(R.id.textView1);
            ((MainActivity) getActivity()).tvSelected
                    .setText(getString(R.string.left_button_text));
            return rootView;
        }
    }

    @Override
    public void onToggle(int result) {

        if (result == SlidingToggleSwitchView.LEFT_SELECTED)
            tvSelected.setText(getString(R.string.left_button_text));
        else
            tvSelected.setText(getString(R.string.right_button_text));

    }

}
