package com.function.java;

import com.microsoft.azure.eventgrid.models.EventGridEvent;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.BlobInput;
import com.microsoft.azure.functions.annotation.EventGridTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;

public class ThumbnailFunction {

  @FunctionName("Thumbnail")
  public void run(@EventGridTrigger(name = "event") EventGridEvent eventGridEvent,
      @BlobInput(name = "input", dataType = "binary", path = "{data.url}") byte[] input,
      @BindingName("data.url") String blobUrl,
      final ExecutionContext context) {
    try {
      context.getLogger().info("Java HTTP trigger processed a request for blob url :: " + blobUrl);
    } catch (Exception e) {
      context.getLogger().info("Exception while generating blob.");
    }
  }
}
