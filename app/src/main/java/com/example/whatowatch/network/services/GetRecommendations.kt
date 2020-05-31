import com.example.whatowatch.model.RecomendationModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetRecommendations {

    @GET("/contents")
    fun getRecommendation(@Query("search",encoded = true) filter: String?):
            Call<List<RecomendationModel>>
}