package com.example.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.client.Utilities.saveBase64Image;
import static com.example.client.Utilities.testValidBase64Image;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Test class for the OpenAI client.
 * This class uses Spring Boot to test functionalities related to image generation.
 *
 * @Author: Elimane
 */
@SpringBootTest
class OpenAIClientTests {

    /**
     * Injecting the AIClient bean to make API calls to OpenAI.
     */
    @Autowired
    AIClient client;

    /**
     * Tests the `createImageUrl` method to generate an image and retrieve its URL.
     * The test uses a request describing two golden retrievers playing in the snow.
     *
     * Steps:
     * - Verifies that the returned URL is not empty.
     * - Prints the generated URL to the console.
     */
    @Test
    public void testCreateImageUrl() {
        String url = client.createImageUrl("Two golden retrievers playing tug-o-war in the snow.");
        assertThat(url).isNotBlank(); // Verifies that the URL is not blank.
        System.out.println("URL: " + url); // Prints the URL to the console.
    }

    /**
     * Tests the `createImageB64` method to generate a Base64-encoded image.
     * The test uses a request describing two golden retrievers playing in the snow.
     *
     * Steps:
     * - Verifies that the returned Base64 string is not empty.
     * - Validates that the Base64 string represents a valid image.
     * - Saves the decoded image as a PNG file.
     */
    @Test
    public void testCreateImageB64() {
        String imageB64 = client.createImageB64("Two golden retrievers playing tug-o-war in the snow.");
        assertThat(imageB64).isNotBlank(); // Verifies that the Base64 string is not blank.
        testValidBase64Image(imageB64); // Validates that the Base64 string corresponds to a valid image.
        saveBase64Image(imageB64, "image.png"); // Saves the image as a PNG file.
    }

}
