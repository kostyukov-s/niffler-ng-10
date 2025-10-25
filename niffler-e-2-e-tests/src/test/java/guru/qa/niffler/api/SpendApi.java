package guru.qa.niffler.api;

import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Date;
import java.util.List;

public interface SpendApi {

  //SpendController
  @GET("internal/spends/{id}")
  Call<SpendJson> getSpend(@Path("id") String id);

  @GET("internal/spends/all")
  Call<List<SpendJson>> getAllSpends(@Query("username") String username,
                                     @Query("filterCurrency") CurrencyValues filterCurrency,
                                     @Query("from") Date from,
                                     @Query("to") Date to);

  @POST("internal/spends/add")
  Call<SpendJson> addSpend(@Body SpendJson spend);

  @PATCH("internal/spends/edit")
  Call<SpendJson> editSpend(@Body SpendJson spend);

  @DELETE("internal/spends/remove")
  Call<Void> removeSpends(@Query("username") String username, @Query("ids") List<String> ids);

  //CategoriesController
  @GET("internal/categories/all")
  Call<List<CategoryJson>> getAllCategories(@Query("username") String username);

  @POST("internal/categories/add")
  Call<CategoryJson> addCategory(@Body CategoryJson category);

  @PATCH("internal/categories/update")
  Call<CategoryJson> updateCategory(@Body CategoryJson category);
}
