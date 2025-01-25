package com.example.client;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Component;

/**
 * Represents an OpenAI client for generating images.
 * This class is a Spring bean marked with the @Component annotation.
 * The bean is explicitly named "openAIClientBean" to avoid conflicts with internal SpringAI beans.
 */
/**
 * @Author: Elimane
 */
@Component("openAIClientBean")
public class OpenAIClient implements AIClient {

    /**
     * Final field of type {@link ImageModel}.
     * This field is injected via the constructor to use ImageModel functionalities.
     */
    private final ImageModel model;

    /**
     * Constructor for the OpenAIClient class.
     *
     * @param model An instance of {@link ImageModel} injected by Spring.
     */
    public OpenAIClient(ImageModel model) {
        this.model = model;
    }

    /**
     * Default options for generating images. By default, only one image is generated.
     */
    private static final OpenAiImageOptions DEFAULT_OPTIONS = OpenAiImageOptions.builder().withN(1).build();

    /**
     * Creates an image and returns its URL.
     * This method uses the default options defined in {@link OpenAiImageOptions}.
     *
     * @param request The textual description of the image to generate.
     * @return The URL of the generated image as a string.
     */
    public String createImageUrl(String request) {

        /**
         * Creates an {@link ImagePrompt} object using the request text
         * and the default options.
         */
        ImagePrompt prompt = new ImagePrompt(
                request,
                DEFAULT_OPTIONS
        );

        /**
         * Calls the model to generate an image based on the prompt.
         * The response is captured in an {@link ImageResponse} object.
         */
        ImageResponse response = model.call(prompt);

        /**
         * Returns the URL of the generated image extracted from the response.
         */
        return response.getResult().getOutput().getUrl();

    }

    /**
     * Creates an image and returns its Base64-encoded content.
     * This method uses custom options to define the response format.
     *
     * @param request The textual description of the image to generate.
     * @return A Base64-encoded string representing the generated image.
     */
    public String createImageB64(String request) {

        /**
         * Creates an {@link OpenAiImageOptions} object to configure the response
         * format as "b64_json" (Base64 encoded).
         */
        OpenAiImageOptions B64_OPTIONS =
                OpenAiImageOptions.builder()
                        .withResponseFormat("b64_json").build();

        /**
         * Creates an {@link ImagePrompt} object using the request text
         * and the custom Base64 options.
         */
        ImagePrompt prompt = new ImagePrompt(
                request,
                B64_OPTIONS
        );

        /**
         * Calls the model to generate a Base64 image based on the prompt.
         * The response is captured in an {@link ImageResponse} object.
         */
        ImageResponse response = model.call(prompt);

        /**
         * Returns the Base64-encoded string extracted from the response.
         */
        return response.getResult().getOutput().getB64Json();

    }
}
