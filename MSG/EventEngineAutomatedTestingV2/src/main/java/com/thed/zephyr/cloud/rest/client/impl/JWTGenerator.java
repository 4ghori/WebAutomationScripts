package com.thed.zephyr.cloud.rest.client.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
 
import com.thed.zephyr.cloud.rest.ZFJCloudRestClient;
import com.thed.zephyr.cloud.rest.client.JwtGenerator;
 
public class JWTGenerator {
 
                public static void main(String[] args) throws URISyntaxException, IllegalStateException, IOException {
                                String zephyrBaseUrl = "https://prod-api.zephyr4jiracloud.com/connect";
                              /*
                                String accessKey = "amlyYTo1MzYxZTYwNy1kMmRjLTQzYTItYjg3MC1iM2MwYjRjNjNmMjQgcmFjaGl0a3VtYXIucmFzdG9naSBVU0VSX0RFRkFVTFRfTkFNRQ";
                                String secretKey = "-ZclG5mEB1SJgpw3FuWwvgUqkVYxFcjWQNNkfcLnT34";
                                String userName = "rachitkumar.rastogi";
                               */
                                
                                String accessKey = "amlyYTo1MzYxZTYwNy1kMmRjLTQzYTItYjg3MC1iM2MwYjRjNjNmMjQgbXNndGVzdGluZyBVU0VSX0RFRkFVTFRfTkFNRQ";
                                String secretKey = "Ftxkea9n9kLNL5isxw-tFXDZp4FQ8QO4f9_ejikR6bI";
                                String userName = "MSGTesting";
                              
                                
                                ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, userName).build();
                                JwtGenerator jwtGenerator = client.getJwtGenerator();
                               
                                // Rockettes   
                                // String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/cycle/0001487892205006-242ac112-0001?versionId=11500&projectId=11001";
                               
                                // GET CYCLE - Adhoc   
                                // String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/cycle/-1?versionId=11500&projectId=11001";
                               
                                // 2 -> GET CYCLE - Smoke Testing
                                // String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/cycle/0001487892205006-242ac112-0001?versionId=11500&projectId=11001";
                               
                                // 1-> ALL Cycles
                               // String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/cycles/search?versionId=11500&projectId=11001";
                               
                                // GET LIST OF ALL EXECUTIONS
                                 //String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/executions?issueId=35118&projectId=11200";
                               
                                // GET EXECUTION Status BY CYCLE ID
                                 String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/executions/search/cycle/0001487892205006-242ac112-0001?versionId=11500&projectId=11001";
                               // String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/executions/search/cycle/-1?versionId=-1&projectId=11200";
                                 
                                // String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/executions/search/cycle/-1?versionId=11500&projectId=11001";
                                 
                                 //String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/teststep/33713/0001488229955146-242ac112-0001?projectId=11001";
                                 
                                //String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/teststep/33713?projectId=11001";
                                 
                                // POST EXECUTION Status for TEST IN CYCLE ID
                                //String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/execution/";
                                
                                //String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/executions/add/cycle/-1";
                               
                                // String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/execution/0001488309366593-242ac112-0001";
                              
                                //String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/execution/0001488311117862-242ac112-0001";
                                
                               // String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/executions/search/cycle/-1?versionId=11500&projectId=11001";
                               
                                // NOT WORKING...
                                // String createCycleUri = zephyrBaseUrl + "public/rest/api/1.0/executions/search/cycle/-1?versionId=11500&projectId=11001";
                   
                                // 1. Get ALL TEST STEPS FOR A TEST CASE
                                // String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/teststep/33713?projectId=11001";
                               
                                 /** RESPONSE OF ABOVE STATEMENT
                                  * [
  {
    "id": "0001488229955146-242ac112-0001",
    "orderId": 1,
    "issueId": 33713,
    "step": "Safari 10",
    "data": "All Test Cases",
    "result": "false",
    "createdBy": "rachitkumar.rastogi",
    "modifiedBy": "rachitkumar.rastogi",
    "createdOn": 1488229955146,
    "lastModifiedOn": 1489277865487
  },
  {
    "id": "0001488310436803-242ac112-0001",
    "orderId": 2,
    "issueId": 33713,
    "step": "iPhone 7 Plus",
    "data": "All sub methods",
    "result": "true",
    "createdBy": "rachitkumar.rastogi",
    "modifiedBy": "rachitkumar.rastogi",
    "createdOn": 1488310436803,
    "lastModifiedOn": 1489277694162
  }
]
                                  */
                                 
                                 
                                // 2. PUT TEST STEP STATUS UPDATE
                                //String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/stepresult/0001488229955146-242ac112-0001";
                               
                                // 1. Get all of the Steps of a Test CASE
                                // String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/stepresult/search?executionId=0001488309366593-242ac112-0001&issueId=33713&isOrdered=true";
                               
                                // 2. UPT request for updating the Test Step Status
                                //String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/stepresult/0001488309366624-242ac112-0001";
                               
                                 /**
                                  * META DATA SETUP IN ZEPHYR
                                  */
                                 // 1. POST Request for Creating TEST CYCLE
                                // String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/cycle";
                               
                                URI uri = new URI(createCycleUri);
                                int expirationInSec = 360;
                                //String jwt = jwtGenerator.generateJWT("PUT", uri, expirationInSec);
                                String jwt = jwtGenerator.generateJWT("GET", uri, expirationInSec);
                                
                                //String jwt = jwtGenerator.generateJWT("POST", uri, expirationInSec);
                                
                                System.out.println("FINAL API : " +uri.toString());
                                System.out.println("JWT Token : " +jwt);    
               
                                // Once we get the JWT we need to get json and Parse it.
                               
                               
               
                }
 
}