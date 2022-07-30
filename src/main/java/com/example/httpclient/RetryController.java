package com.example.httpclient;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class RetryController {
//Below will solve some issues happening in httpclient retrying
//	https://stackoverflow.com/questions/29488106/java-lang-illegalstateexception-connection-pool-shut-down-while-using-spring-re
	public static final String STATUS_500_URL = "https://httpbin.org/status/500";
	public static final String STATUS_504_URL = "https://httpbin.org/status/504";
    public static final String STATUS_400_URL = "https://httpbin.org/status/400";
    public static final String STATUS_404_URL = "https://httpbin.org/status/404";
    
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@ApiOperation(value = "Save Team Detail")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get Team Details Collection"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/http-retry", method = RequestMethod.POST)
	public void retriesFor500WithResponseInterceptor() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.custom()
				.addInterceptorLast(new HttpResponseInterceptor() {
					@Override
					public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
						String[] stsCodeList={"500","504"};
						if(stsCodeList.length>0) {
							for(String stsCode:stsCodeList) {
								if(Integer.parseInt(stsCode)==response.getStatusLine().getStatusCode())
									throw new IOException("Retry it");
							}
						}
						/*
						 * if (response.getStatusLine().getStatusCode() == 500 ||
						 * response.getStatusLine().getStatusCode() == 504) { throw new
						 * IOException("Retry it"); }
						 */
					}
				})
          .build()) {

            this.executeRequestForStatus(httpClient, STATUS_504_URL);
        }
    }

    private void executeRequestForStatus(CloseableHttpClient httpClient, String url) throws IOException {
        final HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
            EntityUtils.consumeQuietly(response.getEntity());
        }
    }
}
