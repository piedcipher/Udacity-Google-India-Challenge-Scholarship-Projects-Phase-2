package com.wordpress.piedcipher.samachar.activities;

import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wordpress.piedcipher.samachar.R;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle(R.string.settings);
    }

    public static class SamacharPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            Preference searchTermsPreference = findPreference(getString(R.string.settings_search_terms_key));
            bindPreferenceSummaryToValue(searchTermsPreference);
            Preference orderByPreference = findPreference(getString(R.string.settings_order_by_key));
            bindPreferenceSummaryToValue(orderByPreference);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);
                if (index >= 0) {
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[index]);
                }
            } else {
                preference.setSummary(stringValue);
            }
            return true;
        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            onPreferenceChange(preference, sharedPreferences.getString(preference.getKey(), ""));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavUtils.navigateUpFromSameTask(this);
    }
}