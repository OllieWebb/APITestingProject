package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojo.Pet;

import java.util.Map;

public class RequestUtils {
    public static RequestSpecification getRequestSpec(String baseUri, String path, Map<String, String> headers, Map<String, String> pathParams) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(headers)
                .addPathParams(pathParams)
                .build();
    }

    // overload method to make get request without passing an empty map
    public static RequestSpecification getRequestSpec(String baseUri, String path, Map<String, String> pathParams) {
        return getRequestSpec(baseUri, path, Map.of(), pathParams);
    }

    // overload method to make get request without passing any maps
    public static RequestSpecification getRequestSpec(String baseUri, String path) {
        return getRequestSpec(baseUri, path, Map.of());
    }

    public static RequestSpecification deleteRequestSpec(String baseUri, String path, Map<String, String> headers, Map<String, String> pathParams) {
        return getRequestSpec(baseUri, path, headers, pathParams);
    }

    public static RequestSpecification putRequestSpec(String baseUri, String path, Map<String, String> headers, Map<String, String> pathParams, Map<String, ?> body) {
        return postRequestSpec(baseUri, path, headers, pathParams, body);
    }

    public static RequestSpecification postRequestSpec(String baseUri, String path, Map<String, String> headers, Map<String, String> pathParams, Map<String, ?> body) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(headers)
                .addPathParams(pathParams)
                .setContentType(ContentType.JSON)
                .setBody(body)
                .build();
    }

    public static RequestSpecification postRequestSpecList(String baseUri, String path, Map<String, String> headers, Map<String, String> pathParams, Map<String, ?>[] body) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(headers)
                .addPathParams(pathParams)
                .setContentType(ContentType.JSON)
                .setBody(body)
                .build();
    }

    public static RequestSpecification petRequestSpec(String baseUri, String path, Pet body) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .setContentType(ContentType.JSON)
                .setBody(body)
                .build();
    }
}
    /*
}
    public static RequestSpecification buildBasicRequest(String uri, String path){
        return getBasicRequest(uri,path)
                .build();
    }

    private static RequestSpecBuilder getBasicRequest(String uri, String path){
        return new RequestSpecBuilder()
                .setBaseUri(uri)
                .setBasePath(path)
                .addHeaders(Map.of(
                        "Accept", "application/json",
                        "Content-Type", "application/json"
                ));
    }

    public static RequestSpecification buildOctetRequest(String uri, String path){
        return getOctetRequest(uri,path)
                .build();
    }

    private static RequestSpecBuilder getOctetRequest(String uri, String path){
        return new RequestSpecBuilder()
                .setBaseUri(uri)
                .setBasePath(path)
                .setContentType("application/octet-stream")
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ));
    }

    public static RequestSpecification buildRequestWithPathParams(String uri, String path,Map<String, ? extends Serializable> pathParams){
        return getRequestWithPathParams(uri,path,pathParams)
                .build();
    }

    public static RequestSpecBuilder getRequestWithPathParams(String uri, String path, Map<String, ? extends Serializable> pathParams){
        return getBasicRequest(uri, path)
                .addPathParams(pathParams);
    }

    public static RequestSpecification buildRequestWithPathParamsAndApiKey(String uri, String path, Map<String, Integer> pathParams, String apiKey) {
        return getRequestWithPathParams(uri, path, pathParams)
                .addHeader("api_key", apiKey)
                .build();
    }*/