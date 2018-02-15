package no.usn.kvisli.listedemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jon on 01.02.2018.
 */

public class Kommune {
    int kommuneNummer, folkeTall;
    String kommuneNavn, fylke;
    double areal;

    @Override
    public String toString() {
        return kommuneNavn;
    }

    public Kommune(JSONObject jsonKommune) {
        this.kommuneNummer = jsonKommune.optInt("Kommunenr");
        this.folkeTall = jsonKommune.optInt("Folketall");
        this.kommuneNavn = jsonKommune.optString("Kommunenavn", "mangler navn");
        this.fylke = jsonKommune.optString("Fylke", "mangler fylke");
        this.areal = jsonKommune.optDouble("Areal");
    }

    public Kommune(int kommuneNummer, int folkeTall, String kommuneNavn, String fylke, float areal) {
        this.kommuneNummer = kommuneNummer;
        this.folkeTall = folkeTall;
        this.kommuneNavn = kommuneNavn;
        this.fylke = fylke;
        this.areal = areal;
    }

    public static ArrayList<Kommune> lagKommuneListe(String jsonKommuner)
        throws JSONException, NullPointerException {
        ArrayList<Kommune> kommuneListe = new ArrayList<Kommune>();

        JSONObject jsonAlleKomm = new JSONObject(jsonKommuner);
        JSONArray jsonKommuneTabell = jsonAlleKomm.getJSONArray("kommuner");
        for (int i=0; i < jsonKommuneTabell.length();i++) {
            JSONObject jsonKommune = jsonKommuneTabell.getJSONObject(i);
            Kommune denneKommunen = new Kommune(jsonKommune);
            kommuneListe.add(denneKommunen);
        }
        return kommuneListe;
    }
}
