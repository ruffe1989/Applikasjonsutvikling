package no.usn.kvisli.listedemo.Fragmentversjon;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import no.usn.kvisli.listedemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KnappeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KnappeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button fylkeKnapp, bildeKnapp, kommuneKnapp;
    public interface OnMenyButtonListener {
        public void onMenyButtonClicked(int button);
    }


    public KnappeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KnappeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KnappeFragment newInstance(String param1, String param2) {
        KnappeFragment fragment = new KnappeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private KnappeFragment.OnMenyButtonListener menyCallback;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof KnappeFragment.OnMenyButtonListener){
            menyCallback = (KnappeFragment.OnMenyButtonListener) context;
        }
        else {
            throw new RuntimeException(context.toString() +
                    "må implementere KnappeFragment.OnMenyButtonClicked");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        menyCallback=null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View knappeMenyen = inflater.inflate(R.layout.fragment_knappe, container, false);
        fylkeKnapp = knappeMenyen.findViewById(R.id.fylkeKnapp);
        fylkeKnapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menyCallback.onMenyButtonClicked(view.getId());
            }
        });
        return knappeMenyen;
    }

}
