package id.ac.ui.edoocatia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import id.ac.ui.edoocatia.R;

public class Preferences extends PreferenceActivity implements OnPreferenceChangeListener {
	   //private CheckBoxPreference pamareter;

	   @Override
	   protected void onPostCreate(Bundle bundle) {
	      super.onPostCreate(bundle);

	      getPreferenceManager().setSharedPreferencesName("preferences"); // don't rename it, somethink strange here, don't solve yet
	      getPreferenceManager().setSharedPreferencesMode(0);

	      addPreferencesFromResource(R.xml.preferences);

	      //pamareter = (CheckBoxPreference) findPreference("pamareter");
	      //pamareter.setChecked(Config.pamareter);
	      //pamareter.setOnPreferenceChangeListener(this);
	   }

	@Override
	public boolean onPreferenceChange(Preference pref, Object newVal) {
	  
		// TODO Auto-generated method stub
		return false;
	}



	   //@Override
	   //public boolean onPreferenceChange(Preference preference, Object newValue) {
	      //if (preference == pamareter) {
	      //   Config.pamareter = (Boolean) newValue;
	      //   pamareter.setChecked(Config.pamareter);
	      //   return true;
	      //}

	      //return false;
	   //}
	}

