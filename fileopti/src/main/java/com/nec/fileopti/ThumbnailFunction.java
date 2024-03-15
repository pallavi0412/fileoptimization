package com.nec.fileopti;

import java.net.URI;
import java.util.Optional;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

 
public class ThumbnailFunction {
    private static final String BLOB_STORAGE_CONNECTION_STRING = System.getenv("AzureWebJobsStorage");
 
    private static String getBlobNameFromUrl(String blobUrl) {
        URI uri = URI.create(blobUrl);
        BlobClient blobClient = new BlobClientBuilder().endpoint(blobUrl).buildClient();
        return blobClient.getBlobName();
    }

    @FunctionName("HttpExample")
    public HttpResponseMessage run(
        @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST},
            authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
      context.getLogger().info("Java HTTP trigger processed a request.");

      // Parse query parameter
      final String query = request.getQueryParameters().get("name");
      final String name = request.getBody().orElse(query);

      if (name == null) {
        return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
            .body("Please pass a name on the query string or in the request body").build();
      } else {
        return request.createResponseBuilder(HttpStatus.OK).body("Hello, " + name).build();
        }
    }

  }
