import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {

    public Moneda buscarMoneda(String monedaBase, String monedaTarget) {
        // Armamos la URL exacta con la base y el objetivo
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/b351bb7c7d454f115d94d6a0/pair/" + monedaBase + "/" + monedaTarget);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moneda.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con la API o moneda no encontrada.");
        }
    }
}
