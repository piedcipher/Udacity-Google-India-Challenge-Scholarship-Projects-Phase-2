package com.wordpress.piedcipher.aapnuamdavad.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wordpress.piedcipher.aapnuamdavad.fragments.EventsFragment;
import com.wordpress.piedcipher.aapnuamdavad.fragments.HotelsFragment;
import com.wordpress.piedcipher.aapnuamdavad.fragments.PlacesFragment;
import com.wordpress.piedcipher.aapnuamdavad.fragments.RestaurantsFragment;

public class CustomPagerAdapter extends FragmentStatePagerAdapter {

    public CustomPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    /**
     * @param position The position of the fragment requested.
     * @return the Fragment associated with a specified position.
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PlacesFragment();
            case 1:
                return new RestaurantsFragment();
            case 2:
                return new HotelsFragment();
            case 3:
                return new EventsFragment();
            default:
                return null;
        }
    }

    /**
     * @return the number of views available.
     */
    @Override
    public int getCount() {
        return 4;
    }
}