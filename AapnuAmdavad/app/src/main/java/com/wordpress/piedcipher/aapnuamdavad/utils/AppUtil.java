package com.wordpress.piedcipher.aapnuamdavad.utils;

import android.content.Context;

import com.wordpress.piedcipher.aapnuamdavad.R;
import com.wordpress.piedcipher.aapnuamdavad.models.Event;
import com.wordpress.piedcipher.aapnuamdavad.models.Hotel;
import com.wordpress.piedcipher.aapnuamdavad.models.Place;
import com.wordpress.piedcipher.aapnuamdavad.models.Restaurant;

import java.util.ArrayList;

public class AppUtil {

    public static ArrayList<Place> getPopulatedPlacesArrayList(Context context) {
        ArrayList<Place> placesArrayList = new ArrayList<>();
        placesArrayList.add(new Place(context.getString(R.string.place_sidi_saiyyed_mosque), R.drawable.place_mosque_of_sidi_sayed_jaali, context.getString(R.string.place_description_sidi_saiyyed_mosque), context.getString(R.string.place_wiki_title_sidi_saiyyed_mosque)));
        placesArrayList.add(new Place(context.getString(R.string.place_sabarmati_ashram), R.drawable.place_sabarmati_ashram, context.getString(R.string.place_description_sabarmati_ashram), context.getString(R.string.place_wiki_title_sabarmati_ashram)));
        placesArrayList.add(new Place(context.getString(R.string.place_kankaria_lake), R.drawable.place_kankaria_lake, context.getString(R.string.place_description_kankaria_lake), context.getString(R.string.place_wiki_title_kankaria_lake)));
        placesArrayList.add(new Place(context.getString(R.string.place_sabarmati_riverfront), R.drawable.place_sabarmati_riverfront, context.getString(R.string.place_description_sabarmati_riverfront), context.getString(R.string.place_wiki_title_sabarmati_riverfront)));
        placesArrayList.add(new Place(context.getString(R.string.place_law_garden), R.drawable.place_law_garden, context.getString(R.string.place_description_law_garden), context.getString(R.string.place_wiki_title_law_garden)));
        return placesArrayList;
    }

    public static ArrayList<Event> getPopulatedEventsArrayList(Context context) {
        String[] eventURLs = {
                "https://insider.in/lolstars-ft-abish-mathew-ahmedabad-aug25-2018/event",
                "https://insider.in/beardothon-ahmedabad-sep23-2018/event",
                "https://allevents.in/ahmedabad/the-comedy-factory-show-ahmedabad/20001032602823",
                "https://www.meetup.com/GDG-Ahmedabad/events/253327605/"
        };

        ArrayList<Event> eventsArrayList = new ArrayList<>();
        eventsArrayList.add(new Event(context.getString(R.string.event_five_star_ke_lol_stars), context.getString(R.string.event_description_five_star_ke_lol_stars), context.getString(R.string.event_date_five_star_ke_lol_stars), context.getString(R.string.event_venue_five_star_ke_lol_stars), R.drawable.event_five_star_ke_lol_stars, eventURLs[0]));
        eventsArrayList.add(new Event(context.getString(R.string.event_beardothon_ahmedabad), context.getString(R.string.event_description_beardothon_ahmedabad), context.getString(R.string.event_date_beardothon_ahmedabad), context.getString(R.string.event_venue_beardothon_ahmedabad), R.drawable.event_beardo_thon, eventURLs[1]));
        eventsArrayList.add(new Event(context.getString(R.string.event_flutter_study_jams_gdg_ahmedabad), context.getString(R.string.event_description_flutter_study_jams_gdg_ahmedabad), context.getString(R.string.event_date_flutter_study_jams_gdg_ahmedabad), context.getString(R.string.event_venue_flutter_study_jams_gdg_ahmedabad), R.drawable.event_flutter_study_jams_gdg_ahmedabad, eventURLs[2]));
        eventsArrayList.add(new Event(context.getString(R.string.event_comedy_factory_show), context.getString(R.string.event_description_comedy_factory_show), context.getString(R.string.event_date_comedy_factory_show), context.getString(R.string.event_venue_comedy_factory_show), R.drawable.event_comedy_factory_show, eventURLs[3]));
        return eventsArrayList;
    }

    public static ArrayList<Hotel> getPopulatedHotelsArrayList(Context context) {
        String[] hotelURL = {
                "http://www.camahotelsindia.com/camaahmedabad/",
                "https://www.hyatt.com/en-US/hotel/india/hyatt-regency-ahmedabad/amdhr",
                "https://www.ihg.com/crowneplaza/hotels/us/en/gujarat/amdch/hoteldetail",
                "https://www.radissonblu.com/en/hotel-ahmedabad"
        };

        ArrayList<Hotel> hotelsArrayList = new ArrayList<>();
        hotelsArrayList.add(new Hotel(context.getString(R.string.hotel_cama_hotel), R.drawable.hotel_cama_hotel, context.getString(R.string.hotel_rating_cama_hotel), context.getString(R.string.hotel_review_cama_hotel), context.getString(R.string.hotel_description_cama_hotel), context.getString(R.string.hotel_contact_number_cama_hotel), hotelURL[0]));
        hotelsArrayList.add(new Hotel(context.getString(R.string.hotel_hyatt_regency), R.drawable.hotel_hyatt_regency, context.getString(R.string.hotel_rating_hyatt_regency), context.getString(R.string.hotel_review_hyatt_regency), context.getString(R.string.hotel_description_hyatt_regency), context.getString(R.string.hotel_contact_number_hyatt_regency), hotelURL[1]));
        hotelsArrayList.add(new Hotel(context.getString(R.string.hotel_crowne_plaza), R.drawable.hotel_crowne_plaza, context.getString(R.string.hotel_rating_crowne_plaza), context.getString(R.string.hotel_review_crowne_plaza), context.getString(R.string.hotel_description_crowne_plaza), context.getString(R.string.hotel_contact_number_crowne_plaza), hotelURL[2]));
        hotelsArrayList.add(new Hotel(context.getString(R.string.hotel_radisson_blu), R.drawable.hotel_radisson_blu, context.getString(R.string.hotel_rating_radisson_blu), context.getString(R.string.hotel_review_radisson_blu), context.getString(R.string.hotel_description_radisson_blu), context.getString(R.string.hotel_contact_number_radisson_blu), hotelURL[3]));
        return hotelsArrayList;
    }

    public static ArrayList<Restaurant> getPopulatedRestaurantsArrayList(Context context) {
        String[] restaurantsURL = {
                "https://www.zomato.com/ahmedabad/patang-the-revolving-restaurant-ashram-road",
                "https://www.zomato.com/ahmedabad/the-jungle-bhookh-ellis-bridge",
                "https://www.zomato.com/ahmedabad/agashiye-the-house-of-mg-lal-darwaja",
                "https://www.zomato.com/ahmedabad/new-freeze-land-c-g-road"
        };

        ArrayList<Restaurant> restaurantsArrayList = new ArrayList<>();
        restaurantsArrayList.add(new Restaurant(context.getString(R.string.restaurant_patang_restaurant), R.drawable.restaurant_patang, context.getString(R.string.restaurant_description_patang_restaurant), context.getString(R.string.restaurant_review_patang_restaurant), context.getString(R.string.restaurant_timings_patang_restaurant), context.getString(R.string.restaurant_contact_number_patang_restaurant), restaurantsURL[0]));
        restaurantsArrayList.add(new Restaurant(context.getString(R.string.restaurant_jungle_bhookh), R.drawable.restaurant_jungle_bhookh, context.getString(R.string.restaurant_description_jungle_bhookh), context.getString(R.string.restaurant_review_jungle_bhookh), context.getString(R.string.restaurant_timings_jungle_bhookh), context.getString(R.string.restaurant_contact_number_jungle_bhookh), restaurantsURL[1]));
        restaurantsArrayList.add(new Restaurant(context.getString(R.string.restaurant_agashiye), R.drawable.restaurant_agashiye, context.getString(R.string.restaurant_description_agashiye), context.getString(R.string.restaurant_review_agashiye), context.getString(R.string.restaurant_timings_agashiye), context.getString(R.string.restaurant_contact_number_agashiye), restaurantsURL[2]));
        restaurantsArrayList.add(new Restaurant(context.getString(R.string.restaurant_freezeland), R.drawable.restaurant_freezaland, context.getString(R.string.restaurant_description_freezeland), context.getString(R.string.restaurant_review_freezeland), context.getString(R.string.restaurant_timings_freezeland), context.getString(R.string.restaurant_contact_number_freezeland), restaurantsURL[3]));
        return restaurantsArrayList;
    }
}