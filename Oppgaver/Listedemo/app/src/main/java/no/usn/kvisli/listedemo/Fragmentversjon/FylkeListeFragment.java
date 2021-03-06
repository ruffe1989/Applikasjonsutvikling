package no.usn.kvisli.listedemo.Fragmentversjon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import no.usn.kvisli.listedemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FylkeListeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FylkeListeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private String[] fylkeTabell;
    private ListView fylkeListen;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fylkeTabell = getResources().getStringArray(R.array.norske_fylker);
    }

    public FylkeListeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rotView = inflater.inflate(R.layout.fragment_fylke, container, false);
        ArrayAdapter<String> fylkeAdapter =
                new ArrayAdapter<String>(getActivity(),
                        R.layout.fylke_list_item, fylkeTabell);
        fylkeListen = rotView.findViewById(R.id.fylke_liste);
        if (fylkeListen!=null)
            fylkeListen.setAdapter(fylkeAdapter);

        return rotView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
