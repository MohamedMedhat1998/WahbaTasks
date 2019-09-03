package com.andalus.wahbatasks;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat implements
        SharedPreferences.OnSharedPreferenceChangeListener{
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

        addPreferencesFromResource(R.xml.pref_room_task);

        SharedPreferences sharedPreferences= getPreferenceScreen().getSharedPreferences();
        PreferenceScreen prefScreen=getPreferenceScreen();
        int count =prefScreen.getPreferenceCount();

        for(int i =0 ; i < count ; i++)
        {
            Preference p = prefScreen.getPreference(i);
                if(!( p instanceof CheckBoxPreference))
                {
                    String value =sharedPreferences.getString(p.getKey(),"");
                    setPreferenceSummary(p, value);
                }
        }
    }

    private void setPreferenceSummary(Preference preference, String value)
    {
        if( preference instanceof ListPreference)
        {
            ListPreference listPreference=(ListPreference) preference;
            int index=listPreference.findIndexOfValue(value);
            if(index >= 0)
            {
                listPreference.setSummary(listPreference.getEntries()[index]);
            }
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {


        Preference preference=findPreference(key);

        if(null !=preference)
        {
            if(!(preference instanceof CheckBoxPreference))
            {
                String value=sharedPreferences.getString(preference.getKey(),"");
                setPreferenceSummary(preference,value);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

    }
}
