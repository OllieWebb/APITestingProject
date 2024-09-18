package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojo.Pet;
import pojo.User;

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
    public static RequestSpecification userRequestSpec(String baseUri, String path, User body) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .setContentType(ContentType.JSON)
                .setBody(body)
                .build();
    }
    public static RequestSpecification userRequestSpec(String baseUri, String path,Map<String, String> pathParams, User body) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addPathParams(pathParams)
                .setContentType(ContentType.JSON)
                .setBody(body)
                .build();
    }
}