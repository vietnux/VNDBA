package com.booboot.vndbandroid.model.vndbandroid;

import com.booboot.vndbandroid.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by od on 12/03/2016.
 */
public class Language {
    public final static Map<String, String> FULL_TEXT = new HashMap<>();
    public final static Map<String, Integer> FLAGS = new HashMap<>();

    static {
        FULL_TEXT.put("ar", "Arabic");
        FULL_TEXT.put("ca", "Catalan");
        FULL_TEXT.put("cs", "Czech");
        FULL_TEXT.put("da", "Danish");
        FULL_TEXT.put("de", "German");
        FULL_TEXT.put("en", "English");
        FULL_TEXT.put("es", "Spanish");
        FULL_TEXT.put("fi", "Finnish");
        FULL_TEXT.put("fr", "French");
        FULL_TEXT.put("he", "Hebrew");
        FULL_TEXT.put("hu", "Hungarian");
        FULL_TEXT.put("id", "Indonesian");
        FULL_TEXT.put("it", "Italian");
        FULL_TEXT.put("ja", "Japanese");
        FULL_TEXT.put("ko", "Korean");
        FULL_TEXT.put("nl", "Dutch");
        FULL_TEXT.put("no", "Norwegian");
        FULL_TEXT.put("pl", "Polish");
        FULL_TEXT.put("pt-br", "Portuguese (Brazil)");
        FULL_TEXT.put("pt-pt", "Portuguese (Portugal)");
        FULL_TEXT.put("ro", "Romanian");
        FULL_TEXT.put("ru", "Russian");
        FULL_TEXT.put("sk", "Slovak");
        FULL_TEXT.put("sv", "Swedish");
        FULL_TEXT.put("ta", "Tagalog");
        FULL_TEXT.put("tr", "Turkish");
        FULL_TEXT.put("uk", "Ukrainian");
        FULL_TEXT.put("vi", "Vietnamese");
        FULL_TEXT.put("zh", "Chinese");

        FLAGS.put("ar", R.drawable.ar);
        FLAGS.put("ca", R.drawable.ca);
        FLAGS.put("cs", R.drawable.cs);
        FLAGS.put("da", R.drawable.da);
        FLAGS.put("de", R.drawable.de);
        FLAGS.put("en", R.drawable.en);
        FLAGS.put("es", R.drawable.es);
        FLAGS.put("fi", R.drawable.fi);
        FLAGS.put("fr", R.drawable.fr);
        FLAGS.put("he", R.drawable.he);
        FLAGS.put("hu", R.drawable.hu);
        FLAGS.put("id", R.drawable.id);
        FLAGS.put("it", R.drawable.it);
        FLAGS.put("ja", R.drawable.ja);
        FLAGS.put("ko", R.drawable.ko);
        FLAGS.put("nl", R.drawable.nl);
        FLAGS.put("no", R.drawable.no);
        FLAGS.put("pl", R.drawable.pl);
        FLAGS.put("pt-br", R.drawable.pt_br);
        FLAGS.put("pt-pt", R.drawable.pt_pt);
        FLAGS.put("ro", R.drawable.ro);
        FLAGS.put("ru", R.drawable.ru);
        FLAGS.put("sk", R.drawable.sk);
        FLAGS.put("sv", R.drawable.sv);
        FLAGS.put("ta", R.drawable.ta);
        FLAGS.put("tr", R.drawable.tr);
        FLAGS.put("uk", R.drawable.uk);
        FLAGS.put("vi", R.drawable.vi);
        FLAGS.put("zh", R.drawable.zh);
    }
}
