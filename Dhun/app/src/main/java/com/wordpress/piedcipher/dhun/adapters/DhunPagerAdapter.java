package com.wordpress.piedcipher.dhun.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wordpress.piedcipher.dhun.R;
import com.wordpress.piedcipher.dhun.fragments.AlbumsFragment;
import com.wordpress.piedcipher.dhun.fragments.ArtistsFragment;
import com.wordpress.piedcipher.dhun.fragments.SongsFragment;

public class DhunPagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;

    public DhunPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        mContext = context;
    }

    /**
     * @param position The position of the fragment requested.
     * @return the Fragment associated with a specified position.
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AlbumsFragment();
            case 1:
                return new ArtistsFragment();
            case 2:
                return new SongsFragment();
            default:
                return null;
        }
    }

    /**
     * @return the number of views available.
     */
    @Override
    public int getCount() {
        return 3;
    }

    /**
     * This method is called by ViewPager to obtain a title string to describe the specified page.
     *
     * @param position The position of the title requested.
     * @return A title for the requested page.
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getResources().getString(R.string.albums_fragment_tab_title);
            case 1:
                return mContext.getResources().getString(R.string.artists_fragment_tab_title);
            case 2:
                return mContext.getResources().getString(R.string.songs_fragment_tab_title);
            default:
                return null;
        }
    }
}