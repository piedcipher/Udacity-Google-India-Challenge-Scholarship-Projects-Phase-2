package com.wordpress.piedcipher.dhun.utils;

import android.content.Context;

import com.wordpress.piedcipher.dhun.R;
import com.wordpress.piedcipher.dhun.models.Album;
import com.wordpress.piedcipher.dhun.models.Artist;
import com.wordpress.piedcipher.dhun.models.Song;

import java.util.ArrayList;
import java.util.Collections;

/**
 * DhunDataUtil is a Utility class which provides helpers, getters, setters utility methods.
 */
public class DhunDataUtil {

    private ArrayList<Song> mSongsArrayList = new ArrayList<>();

    private static String mNowPlayingSongTitle = "Maps";
    private static String mNowPlayingSongArtist = "Maroon 5";
    private static int mNowPlayingSongAlbumArt = R.drawable.artist_maroon5_album_v;
    private static String mNowPlayingSongDuration = "3:09";
    private static boolean mNowPlayingSongState = true;
    private static boolean mNowPlayingBarState = false;

    /**
     *
     * @param nowPlayingSongTitle is Title of the Song which is being played right now.
     * @param nowPlayingSongArtist is Artist of the Song which is being played right now.
     * @param nowPlayingSongAlbumArt is AlbumArt of the Song which is being played right now.
     * @param nowPlayingSongDuration is Duration of the Song which is being played right now.
     * @param nowPlayingSongState is Play/Pause state of the Song which is being played right now.
     * @param nowPlayingBarState is current visibility of Expandable layout.
     */
    public static void setNowPlayingSongData(String nowPlayingSongTitle, String nowPlayingSongArtist, int nowPlayingSongAlbumArt, String nowPlayingSongDuration, boolean nowPlayingSongState, boolean nowPlayingBarState) {
        mNowPlayingSongTitle = nowPlayingSongTitle;
        mNowPlayingSongArtist = nowPlayingSongArtist;
        mNowPlayingSongAlbumArt = nowPlayingSongAlbumArt;
        mNowPlayingSongDuration = nowPlayingSongDuration;
        mNowPlayingSongState = nowPlayingSongState;
        mNowPlayingBarState = nowPlayingBarState;
    }

    /**
     *
     * @return title of the song which is being played right now.
     */
    public static String getNowPlayingSongTitle() {
        return mNowPlayingSongTitle;
    }

    /**
     *
     * @return artist of the song which is being played right now.
     */
    public static String getNowPlayingSongArtist() {
        return mNowPlayingSongArtist;
    }

    /**
     *
     * @return albumArt of the song which is being played right now.
     */
    public static int getNowPlayingSongAlbumArt() {
        return mNowPlayingSongAlbumArt;
    }

    /**
     *
     * @return duration of the song which is being played right now.
     */
    public static String getNowPlayingSongDuration() {
        return mNowPlayingSongDuration;
    }

    /**
     *
     * @param nowPlayingState sets title of the song which is being played right now.
     */
    public static void setNowPlayingSongState(boolean nowPlayingState) {
        mNowPlayingSongState = nowPlayingState;
    }

    /**
     *
     * @return state of song which is being played right now.
     */
    public static boolean getNowPlayingSongState() {
        return mNowPlayingSongState;
    }

    /**
     *
     * @param nowPlayingBarState sets visibility of Expandable layout in Now-playing bar.
     */
    public static void setNowPlayingBarState(boolean nowPlayingBarState) {
        mNowPlayingBarState = nowPlayingBarState;
    }

    /**
     *
     * @return visibility of Expandable layout in Now-playing bar.
     */
    public static boolean getNowPlayingBarState() {
        return mNowPlayingBarState;
    }

    /**
     *
     * @param context defines the Context from which this method was called.
     * @return an ArrayList populated with Songs.
     */
    public ArrayList<Song> getPopulatedSongsArrayList(Context context) {
        mSongsArrayList.add(new Song(context.getString(R.string.song_maps), context.getString(R.string.artist_maroon5), "3:09", R.drawable.artist_maroon5_album_v));
        mSongsArrayList.add(new Song(context.getString(R.string.song_animals), context.getString(R.string.artist_maroon5), "3:51", R.drawable.artist_maroon5_album_v));
        mSongsArrayList.add(new Song(context.getString(R.string.song_it_was_always_you), context.getString(R.string.artist_maroon5), "3:59", R.drawable.artist_maroon5_album_v));
        mSongsArrayList.add(new Song(context.getString(R.string.song_unkiss_me), context.getString(R.string.artist_maroon5), "3:58", R.drawable.artist_maroon5_album_v));
        mSongsArrayList.add(new Song(context.getString(R.string.song_sugar), context.getString(R.string.artist_maroon5), "3:55", R.drawable.artist_maroon5_album_v));
        mSongsArrayList.add(new Song(context.getString(R.string.song_leaving_california), context.getString(R.string.artist_maroon5), "3:23", R.drawable.artist_maroon5_album_v));
        mSongsArrayList.add(new Song(context.getString(R.string.song_in_your_pocket), context.getString(R.string.artist_maroon5), "3:39", R.drawable.artist_maroon5_album_v));
        mSongsArrayList.add(new Song(context.getString(R.string.song_new_love), context.getString(R.string.artist_maroon5), "3:16", R.drawable.artist_maroon5_album_v));
        mSongsArrayList.add(new Song(context.getString(R.string.song_coming_back_for_you), context.getString(R.string.artist_maroon5), "3:46", R.drawable.artist_maroon5_album_v));
        mSongsArrayList.add(new Song(context.getString(R.string.song_feelings), context.getString(R.string.artist_maroon5), "3:14", R.drawable.artist_maroon5_album_v));
        mSongsArrayList.add(new Song(context.getString(R.string.song_my_heart_is_open), context.getString(R.string.artist_maroon5), "3:56", R.drawable.artist_maroon5_album_v));
        mSongsArrayList.add(new Song(context.getString(R.string.song_this_summer), context.getString(R.string.artist_maroon5), "3:44", R.drawable.artist_maroon5_album_v));
        mSongsArrayList.add(new Song(context.getString(R.string.song_shoot_love), context.getString(R.string.artist_maroon5), "3:09", R.drawable.artist_maroon5_album_v));
        mSongsArrayList.add(new Song(context.getString(R.string.song_lost_stars), context.getString(R.string.artist_maroon5), "4:27", R.drawable.artist_maroon5_album_v));
        mSongsArrayList.add(new Song(context.getString(R.string.song_one_more_night), context.getString(R.string.artist_maroon5), "3:39", R.drawable.artist_maroon5_album_overexposed));
        mSongsArrayList.add(new Song(context.getString(R.string.song_payphone), context.getString(R.string.artist_maroon5), "3:51", R.drawable.artist_maroon5_album_overexposed));
        mSongsArrayList.add(new Song(context.getString(R.string.song_daylight), context.getString(R.string.artist_maroon5), "3:45", R.drawable.artist_maroon5_album_overexposed));
        mSongsArrayList.add(new Song(context.getString(R.string.song_lucky_strike), context.getString(R.string.artist_maroon5), "3:05", R.drawable.artist_maroon5_album_overexposed));
        mSongsArrayList.add(new Song(context.getString(R.string.song_the_man_who_never_lied), context.getString(R.string.artist_maroon5), "3:25", R.drawable.artist_maroon5_album_overexposed));
        mSongsArrayList.add(new Song(context.getString(R.string.song_love_somebody), context.getString(R.string.artist_maroon5), "3:49", R.drawable.artist_maroon5_album_overexposed));
        mSongsArrayList.add(new Song(context.getString(R.string.song_lady_killer), context.getString(R.string.artist_maroon5), "2:43", R.drawable.artist_maroon5_album_overexposed));
        mSongsArrayList.add(new Song(context.getString(R.string.song_fortune_teller), context.getString(R.string.artist_maroon5), "3:23", R.drawable.artist_maroon5_album_overexposed));
        mSongsArrayList.add(new Song(context.getString(R.string.song_sad), context.getString(R.string.artist_maroon5), "3:14", R.drawable.artist_maroon5_album_overexposed));
        mSongsArrayList.add(new Song(context.getString(R.string.song_tickets), context.getString(R.string.artist_maroon5), "3:29", R.drawable.artist_maroon5_album_overexposed));
        mSongsArrayList.add(new Song(context.getString(R.string.song_doin_dirt), context.getString(R.string.artist_maroon5), "3:31", R.drawable.artist_maroon5_album_overexposed));
        mSongsArrayList.add(new Song(context.getString(R.string.song_beautiful_goodbye), context.getString(R.string.artist_maroon5), "4:15", R.drawable.artist_maroon5_album_overexposed));
        mSongsArrayList.add(new Song(context.getString(R.string.song_wipe_your_eyes), context.getString(R.string.artist_maroon5), "3:34", R.drawable.artist_maroon5_album_overexposed));
        mSongsArrayList.add(new Song(context.getString(R.string.song_wasted_years), context.getString(R.string.artist_maroon5), "3:32", R.drawable.artist_maroon5_album_overexposed));
        mSongsArrayList.add(new Song(context.getString(R.string.song_lets_stay_together), context.getString(R.string.artist_maroon5), "3:23", R.drawable.artist_maroon5_album_overexposed));
        mSongsArrayList.add(new Song(context.getString(R.string.song_moves_like_jagger), context.getString(R.string.artist_maroon5), "3:21", R.drawable.artist_maroon5_album_overexposed));
        mSongsArrayList.add(new Song(context.getString(R.string.song_welcome_to_new_york), context.getString(R.string.artist_taylor_swift), "3:32", R.drawable.artist_taylor_swift_album_1989));
        mSongsArrayList.add(new Song(context.getString(R.string.song_blank_space), context.getString(R.string.artist_taylor_swift), "3:51", R.drawable.artist_taylor_swift_album_1989));
        mSongsArrayList.add(new Song(context.getString(R.string.song_style), context.getString(R.string.artist_taylor_swift), "3:51", R.drawable.artist_taylor_swift_album_1989));
        mSongsArrayList.add(new Song(context.getString(R.string.song_out_of_the_woods), context.getString(R.string.artist_taylor_swift), "3:55", R.drawable.artist_taylor_swift_album_1989));
        mSongsArrayList.add(new Song(context.getString(R.string.song_all_you_had_to_do_was_stay), context.getString(R.string.artist_taylor_swift), "3:13", R.drawable.artist_taylor_swift_album_1989));
        mSongsArrayList.add(new Song(context.getString(R.string.song_shake_it_off), context.getString(R.string.artist_taylor_swift), "3:39", R.drawable.artist_taylor_swift_album_1989));
        mSongsArrayList.add(new Song(context.getString(R.string.song_i_wish_you_would), context.getString(R.string.artist_taylor_swift), "3:27", R.drawable.artist_taylor_swift_album_1989));
        mSongsArrayList.add(new Song(context.getString(R.string.song_bad_blood), context.getString(R.string.artist_taylor_swift), "3:31", R.drawable.artist_taylor_swift_album_1989));
        mSongsArrayList.add(new Song(context.getString(R.string.song_wildest_dreams), context.getString(R.string.artist_taylor_swift), "3:40", R.drawable.artist_taylor_swift_album_1989));
        mSongsArrayList.add(new Song(context.getString(R.string.song_how_you_get_the_girl), context.getString(R.string.artist_taylor_swift), "4:07", R.drawable.artist_taylor_swift_album_1989));
        mSongsArrayList.add(new Song(context.getString(R.string.song_this_love), context.getString(R.string.artist_taylor_swift), "4:10", R.drawable.artist_taylor_swift_album_1989));
        mSongsArrayList.add(new Song(context.getString(R.string.song_i_know_places), context.getString(R.string.artist_taylor_swift), "3:15", R.drawable.artist_taylor_swift_album_1989));
        mSongsArrayList.add(new Song(context.getString(R.string.song_clean), context.getString(R.string.artist_taylor_swift), "4:31", R.drawable.artist_taylor_swift_album_1989));
        mSongsArrayList.add(new Song(context.getString(R.string.song_love_never_felt_so_good), context.getString(R.string.artist_michael_jackson), "3:54", R.drawable.artist_michael_jackson_album_xscape));
        mSongsArrayList.add(new Song(context.getString(R.string.song_chicago), context.getString(R.string.artist_michael_jackson), "4:05", R.drawable.artist_michael_jackson_album_xscape));
        mSongsArrayList.add(new Song(context.getString(R.string.song_loving_you), context.getString(R.string.artist_michael_jackson), "3:15", R.drawable.artist_michael_jackson_album_xscape));
        mSongsArrayList.add(new Song(context.getString(R.string.song_a_place_with_no_name), context.getString(R.string.artist_michael_jackson), "5:35", R.drawable.artist_michael_jackson_album_xscape));
        mSongsArrayList.add(new Song(context.getString(R.string.song_slave_to_the_rhythm), context.getString(R.string.artist_michael_jackson), "4:16", R.drawable.artist_michael_jackson_album_xscape));
        mSongsArrayList.add(new Song(context.getString(R.string.song_blue_gangsta), context.getString(R.string.artist_michael_jackson), "4:14", R.drawable.artist_michael_jackson_album_xscape));
        mSongsArrayList.add(new Song(context.getString(R.string.song_xscape), context.getString(R.string.artist_michael_jackson), "4:05", R.drawable.artist_michael_jackson_album_xscape));
        mSongsArrayList.add(new Song(context.getString(R.string.song_best_4_u), context.getString(R.string.artist_maroon5), "3:59", R.drawable.artist_maroon5_album_red_pill_blues));
        mSongsArrayList.add(new Song(context.getString(R.string.song_what_lovers_do), context.getString(R.string.artist_maroon5), "3:19", R.drawable.artist_maroon5_album_red_pill_blues));
        mSongsArrayList.add(new Song(context.getString(R.string.song_wait), context.getString(R.string.artist_maroon5), "3:10", R.drawable.artist_maroon5_album_red_pill_blues));
        mSongsArrayList.add(new Song(context.getString(R.string.song_lips_on_you), context.getString(R.string.artist_maroon5), "3:36", R.drawable.artist_maroon5_album_red_pill_blues));
        mSongsArrayList.add(new Song(context.getString(R.string.song_bet_my_heart), context.getString(R.string.artist_maroon5), "3:16", R.drawable.artist_maroon5_album_red_pill_blues));
        mSongsArrayList.add(new Song(context.getString(R.string.song_help_me_out), context.getString(R.string.artist_maroon5), "3:13", R.drawable.artist_maroon5_album_red_pill_blues));
        mSongsArrayList.add(new Song(context.getString(R.string.song_who_i_am), context.getString(R.string.artist_maroon5), "3:03", R.drawable.artist_maroon5_album_red_pill_blues));
        mSongsArrayList.add(new Song(context.getString(R.string.song_whiskey), context.getString(R.string.artist_maroon5), "3:30", R.drawable.artist_maroon5_album_red_pill_blues));
        mSongsArrayList.add(new Song(context.getString(R.string.song_girls_like_you), context.getString(R.string.artist_maroon5), "3:35", R.drawable.artist_maroon5_album_red_pill_blues));
        mSongsArrayList.add(new Song(context.getString(R.string.song_closure), context.getString(R.string.artist_maroon5), "11:28", R.drawable.artist_maroon5_album_red_pill_blues));
        mSongsArrayList.add(new Song(context.getString(R.string.song_denim_jacket), context.getString(R.string.artist_maroon5), "3:52", R.drawable.artist_maroon5_album_red_pill_blues));
        mSongsArrayList.add(new Song(context.getString(R.string.song_visions), context.getString(R.string.artist_maroon5), "3:50", R.drawable.artist_maroon5_album_red_pill_blues));
        mSongsArrayList.add(new Song(context.getString(R.string.song_plastic_rose), context.getString(R.string.artist_maroon5), "3:42", R.drawable.artist_maroon5_album_red_pill_blues));
        mSongsArrayList.add(new Song(context.getString(R.string.song_dont_wanna_know), context.getString(R.string.artist_maroon5), "3:34", R.drawable.artist_maroon5_album_red_pill_blues));
        mSongsArrayList.add(new Song(context.getString(R.string.song_cold), context.getString(R.string.artist_maroon5), "3:54", R.drawable.artist_maroon5_album_red_pill_blues));
        mSongsArrayList.add(new Song(context.getString(R.string.song_let_it_happen), context.getString(R.string.artist_tame_impala), "7:47", R.drawable.artist_tame_impala_album_currents));
        mSongsArrayList.add(new Song(context.getString(R.string.song_nangs), context.getString(R.string.artist_tame_impala), "1:47", R.drawable.artist_tame_impala_album_currents));
        mSongsArrayList.add(new Song(context.getString(R.string.song_the_moment), context.getString(R.string.artist_tame_impala), "4:15", R.drawable.artist_tame_impala_album_currents));
        mSongsArrayList.add(new Song(context.getString(R.string.song_yes_im_changing), context.getString(R.string.artist_tame_impala), "4:30", R.drawable.artist_tame_impala_album_currents));
        mSongsArrayList.add(new Song(context.getString(R.string.song_eventually), context.getString(R.string.artist_tame_impala), "5:18", R.drawable.artist_tame_impala_album_currents));
        mSongsArrayList.add(new Song(context.getString(R.string.song_gossip), context.getString(R.string.artist_tame_impala), "0:55", R.drawable.artist_tame_impala_album_currents));
        mSongsArrayList.add(new Song(context.getString(R.string.song_the_less_i_know_the_better), context.getString(R.string.artist_tame_impala), "3:36", R.drawable.artist_tame_impala_album_currents));
        mSongsArrayList.add(new Song(context.getString(R.string.song_past_life), context.getString(R.string.artist_tame_impala), "3:48", R.drawable.artist_tame_impala_album_currents));
        mSongsArrayList.add(new Song(context.getString(R.string.song_disciples), context.getString(R.string.artist_tame_impala), "1:58", R.drawable.artist_tame_impala_album_currents));
        mSongsArrayList.add(new Song(context.getString(R.string.song_cause_im_a_man), context.getString(R.string.artist_tame_impala), "4:01", R.drawable.artist_tame_impala_album_currents));
        mSongsArrayList.add(new Song(context.getString(R.string.song_reality_in_motion), context.getString(R.string.artist_tame_impala), "4:12", R.drawable.artist_tame_impala_album_currents));
        mSongsArrayList.add(new Song(context.getString(R.string.song_love_paranoia), context.getString(R.string.artist_tame_impala), "3:05", R.drawable.artist_tame_impala_album_currents));
        mSongsArrayList.add(new Song(context.getString(R.string.song_new_person_same_old_mistakes), context.getString(R.string.artist_tame_impala), "6:03", R.drawable.artist_tame_impala_album_currents));
        mSongsArrayList.add(new Song(context.getString(R.string.song_pokemon_theme), context.getString(R.string.artist_jonathan_young), "3:42", R.drawable.artist_jonathan_young_album_pokejon));
        mSongsArrayList.add(new Song(context.getString(R.string.song_viridian_city), context.getString(R.string.artist_jonathan_young), "3:48", R.drawable.artist_jonathan_young_album_pokejon));
        mSongsArrayList.add(new Song(context.getString(R.string.song_double_trouble), context.getString(R.string.artist_jonathan_young), "3:40", R.drawable.artist_jonathan_young_album_pokejon));
        mSongsArrayList.add(new Song(context.getString(R.string.song_unbeatable), context.getString(R.string.artist_jonathan_young), "1:52", R.drawable.artist_jonathan_young_album_pokejon));
        mSongsArrayList.add(new Song(context.getString(R.string.song_pokerap), context.getString(R.string.artist_jonathan_young), "3:08", R.drawable.artist_jonathan_young_album_pokejon));
        mSongsArrayList.add(new Song(context.getString(R.string.song_mistys_song), context.getString(R.string.artist_jonathan_young), "2:43", R.drawable.artist_jonathan_young_album_pokejon));
        mSongsArrayList.add(new Song(context.getString(R.string.song_johto), context.getString(R.string.artist_jonathan_young), "2:55", R.drawable.artist_jonathan_young_album_pokejon));
        mSongsArrayList.add(new Song(context.getString(R.string.song_what_kind_of_pokemon_are_you), context.getString(R.string.artist_jonathan_young), "2:35", R.drawable.artist_jonathan_young_album_pokejon));
        mSongsArrayList.add(new Song(context.getString(R.string.song_this_dream), context.getString(R.string.artist_jonathan_young), "1:56", R.drawable.artist_jonathan_young_album_pokejon));
        mSongsArrayList.add(new Song(context.getString(R.string.song_2BA_master), context.getString(R.string.artist_jonathan_young), "3:56", R.drawable.artist_jonathan_young_album_pokejon));
        mSongsArrayList.add(new Song(context.getString(R.string.song_state_of_grace), context.getString(R.string.artist_taylor_swift), "4:55", R.drawable.artist_taylor_swift_album_red));
        mSongsArrayList.add(new Song(context.getString(R.string.song_red), context.getString(R.string.artist_taylor_swift), "3:43", R.drawable.artist_taylor_swift_album_red));
        mSongsArrayList.add(new Song(context.getString(R.string.song_treacherous), context.getString(R.string.artist_taylor_swift), "4:02", R.drawable.artist_taylor_swift_album_red));
        mSongsArrayList.add(new Song(context.getString(R.string.song_i_knew_you_were_trouble), context.getString(R.string.artist_taylor_swift), "3:39", R.drawable.artist_taylor_swift_album_red));
        mSongsArrayList.add(new Song(context.getString(R.string.song_all_too_well), context.getString(R.string.artist_taylor_swift), "5:29", R.drawable.artist_taylor_swift_album_red));
        mSongsArrayList.add(new Song(context.getString(R.string.song_22), context.getString(R.string.artist_taylor_swift), "3:52", R.drawable.artist_taylor_swift_album_red));
        mSongsArrayList.add(new Song(context.getString(R.string.song_i_almost_do), context.getString(R.string.artist_taylor_swift), "4:04", R.drawable.artist_taylor_swift_album_red));
        mSongsArrayList.add(new Song(context.getString(R.string.song_we_are_never_ever_getting_back_together), context.getString(R.string.artist_taylor_swift), "3:13", R.drawable.artist_taylor_swift_album_red));
        mSongsArrayList.add(new Song(context.getString(R.string.song_stay_stay_stay), context.getString(R.string.artist_taylor_swift), "3:25", R.drawable.artist_taylor_swift_album_red));
        mSongsArrayList.add(new Song(context.getString(R.string.song_the_last_time), context.getString(R.string.artist_taylor_swift), "4:59", R.drawable.artist_taylor_swift_album_red));
        mSongsArrayList.add(new Song(context.getString(R.string.song_holy_ground), context.getString(R.string.artist_taylor_swift), "3:22", R.drawable.artist_taylor_swift_album_red));
        mSongsArrayList.add(new Song(context.getString(R.string.song_beautiful_tragic), context.getString(R.string.artist_taylor_swift), "4:44", R.drawable.artist_taylor_swift_album_red));
        mSongsArrayList.add(new Song(context.getString(R.string.song_the_lucky_one), context.getString(R.string.artist_taylor_swift), "4:00", R.drawable.artist_taylor_swift_album_red));
        mSongsArrayList.add(new Song(context.getString(R.string.song_everything_has_changed), context.getString(R.string.artist_taylor_swift), "4:05", R.drawable.artist_taylor_swift_album_red));
        mSongsArrayList.add(new Song(context.getString(R.string.song_starlight), context.getString(R.string.artist_taylor_swift), "3:40", R.drawable.artist_taylor_swift_album_red));
        mSongsArrayList.add(new Song(context.getString(R.string.song_begin_again), context.getString(R.string.artist_taylor_swift), "3:59", R.drawable.artist_taylor_swift_album_red));

        return mSongsArrayList;
    }

    /**
     *
     * @param context defines the Context from which this method was called.
     * @return an ArrayList populated with Albums.
     */
    public ArrayList<Album> getPopulatedAlbumsArrayList(Context context) {
        ArrayList<Album> albumsArrayList = new ArrayList<>();

        albumsArrayList.add(new Album(context.getString(R.string.artist_maroon5_album_v), context.getString(R.string.artist_maroon5), R.drawable.artist_maroon5_album_v));
        albumsArrayList.add(new Album(context.getString(R.string.artist_maroon5_album_overexposed), context.getString(R.string.artist_maroon5), R.drawable.artist_maroon5_album_overexposed));
        albumsArrayList.add(new Album(context.getString(R.string.artist_taylor_swift_album_1989), context.getString(R.string.artist_taylor_swift), R.drawable.artist_taylor_swift_album_1989));
        albumsArrayList.add(new Album(context.getString(R.string.artist_michael_jackson_album_xscape), context.getString(R.string.artist_michael_jackson), R.drawable.artist_michael_jackson_album_xscape));
        albumsArrayList.add(new Album(context.getString(R.string.artist_maroon5_album_red_pill_blues), context.getString(R.string.artist_maroon5), R.drawable.artist_maroon5_album_red_pill_blues));
        albumsArrayList.add(new Album(context.getString(R.string.artist_tame_impala_album_currents), context.getString(R.string.artist_tame_impala), R.drawable.artist_tame_impala_album_currents));
        albumsArrayList.add(new Album(context.getString(R.string.artist_jonathan_young_pokejon), context.getString(R.string.artist_jonathan_young), R.drawable.artist_jonathan_young_album_pokejon));
        albumsArrayList.add(new Album(context.getString(R.string.artist_taylor_swift_album_red), context.getString(R.string.artist_taylor_swift), R.drawable.artist_taylor_swift_album_red));

        Collections.shuffle(albumsArrayList);

        return albumsArrayList;
    }

    /**
     *
     * @param context defines the Context from which this method was called.
     * @return an ArrayList populated with Artists.
     */
    public ArrayList<Artist> getPopulatedArtistArrayList(Context context) {
        ArrayList<Artist> artistsArrayList = new ArrayList<>();

        artistsArrayList.add(new Artist(context, context.getString(R.string.artist_maroon5), R.drawable.artist_maroon5));
        artistsArrayList.add(new Artist(context, context.getString(R.string.artist_taylor_swift), R.drawable.artist_taylor_swift));
        artistsArrayList.add(new Artist(context, context.getString(R.string.artist_michael_jackson), R.drawable.artist_micheal_jackson));
        artistsArrayList.add(new Artist(context, context.getString(R.string.artist_tame_impala), R.drawable.artist_tame_impala));
        artistsArrayList.add(new Artist(context, context.getString(R.string.artist_jonathan_young), R.drawable.artist_jonathan_young));

        Collections.shuffle(artistsArrayList);

        return artistsArrayList;
    }

    /**
     *
     * @param context defines the Context from which this method was called.
     * @param artist is an Object of Artist model-class.
     * @return song-count for a particular Artist.
     */
    public static int getArtistSongCount(Context context, Artist artist) {
        ArrayList<Song> songArrayList = new DhunDataUtil().getPopulatedSongsArrayList(context);
        int songCount = 0;
        for (int i = 0; i < songArrayList.size(); i++) {
            if (songArrayList.get(i).getArtist().equals(artist.getArtistName())) {
                songCount++;
            }
        }

        return songCount;
    }

    /**
     *
     * @param context defines the Context from which this method was called.
     * @param artist is an Object of Artist model-class.
     * @return album-count for a particular Artist.
     */
    public static int getArtistAlbumCount(Context context, Artist artist) {
        ArrayList<Album> albumArrayList = new DhunDataUtil().getPopulatedAlbumsArrayList(context);
        int albumCount = 0;
        for (int i = 0; i < albumArrayList.size(); i++) {
            if (albumArrayList.get(i).getAlbumArtist().equals(artist.getArtistName())) {
                albumCount++;
            }
        }

        return albumCount;
    }
}