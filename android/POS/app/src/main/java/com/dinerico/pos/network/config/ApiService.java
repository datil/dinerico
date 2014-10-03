package com.dinerico.pos.network.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by josephleon on 9/30/14.
 */

public class ApiService extends RetrofitGsonSpiceService {

  public static final String BASE_URL = "http://www.datil.ec/datum";
  public static final int CACHE_TIME = 5000;

  @Override
  public void onCreate() {
    super.onCreate();
    addRetrofitInterface(Router.class);
  }

  @Override
  protected RestAdapter.Builder createRestAdapterBuilder() {
    RestAdapter.Builder adapter = super.createRestAdapterBuilder();

    Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
              public Date deserialize(
                      JsonElement json, Type typeOfT,
                      JsonDeserializationContext context
              ) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong() * 1000);
              }
            })
            .create();
    adapter.setConverter(new GsonConverter(gson));
    adapter.setLogLevel(RestAdapter.LogLevel.FULL);
    return adapter;
  }

  @Override
  protected String getServerUrl() {
    return BASE_URL;
  }


}
