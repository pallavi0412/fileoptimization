package com.nec.fileopti;

import java.net.URI;
import org.apache.commons.lang3.exception.ExceptionUtils;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import com.microsoft.azure.eventgrid.models.EventGridEvent;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.BlobInput;
import com.microsoft.azure.functions.annotation.EventGridTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;
 
public class ThumbnailFunction {
    private static final String BLOB_STORAGE_CONNECTION_STRING = System.getenv("AzureWebJobsStorage");
 
    private static String getBlobNameFromUrl(String blobUrl) {
        URI uri = URI.create(blobUrl);
        BlobClient blobClient = new BlobClientBuilder().endpoint(blobUrl).buildClient();
        return blobClient.getBlobName();
    }

    @FunctionName("Thumbnail")
    public void run(
            @EventGridTrigger(name = "event") EventGridEvent eventGridEvent,
        @BlobInput(name = "input", dataType = "binary", path = "{data.url}") byte[] input,
            @BindingName("data.url") String blobUrl,
            final ExecutionContext context) {
        try {
            if (input != null) {
              String containerName = System.getenv("THUMBNAIL_CONTAINER_NAME");
              context.getLogger().info(containerName + " :: " + " ending...");
                } else {
                    context.getLogger().info(String.format("No encoder support for: %s", blobUrl));
                }
            
        } catch (Exception ex) {
            context.getLogger().info(ExceptionUtils.getStackTrace(ex));
            throw ex;
        }
    }
  }
