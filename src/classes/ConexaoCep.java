package classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ConexaoCep {
    private String enderecoBuscado;
    private String bairroBuscado;
    private String cidadeBuscado;
    private String estadoBuscado;

    public ConexaoCep(String cep) throws URISyntaxException, ExecutionException, InterruptedException, JsonProcessingException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://viacep.com.br/ws/"+cep+"/json/"))
                .GET()
                .build();
        ObjectMapper mapper = new ObjectMapper();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        JsonNode rootNode = mapper.readTree(response.get().body());
        this.enderecoBuscado = rootNode.findValue("logradouro").asText();
        this.bairroBuscado = rootNode.findValue("bairro").asText();
        this.cidadeBuscado = rootNode.findValue("localidade").asText();
        this.estadoBuscado = rootNode.findValue("uf").asText();
    }


    public String getEnderecoBuscado() {
        return enderecoBuscado;
    }

    public String getBairroBuscado() {
        return bairroBuscado;
    }

    public String getCidadeBuscado() {
        return cidadeBuscado;
    }

    public String getEstadoBuscado() {
        return estadoBuscado;
    }
}