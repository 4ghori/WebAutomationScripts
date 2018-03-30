package com.msg.qa.common;

import com.atlassian.jwt.SigningAlgorithm;
import com.atlassian.jwt.core.writer.JsonSmartJwtJsonBuilder;
import com.atlassian.jwt.core.writer.JwtClaimsBuilder;
import com.atlassian.jwt.core.writer.NimbusJwtWriterFactory;
import com.atlassian.jwt.httpclient.CanonicalHttpUriRequest;
import com.atlassian.jwt.writer.JwtJsonBuilder;
import com.atlassian.jwt.writer.JwtWriterFactory;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class JWTTokenMaker {

	public static void main(String args[])
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		createUriWithJwt();
	}

	public static String createUriWithJwt()
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		long issuedAt = System.currentTimeMillis() / 1000L;
		long expiresAt = issuedAt + 180L;
		String key = "atlassian-connect-addon"; // the key from the add-on
												// descriptor
		String sharedSecret = "..."; // the sharedsecret key received
		// during the add-on installation handshake
		String method = "GET";
		String baseUrl = "https://<my-dev-environment>.atlassian.net/";
		String contextPath = "/";
		String apiPath = "/rest/api/latest/serverInfo";

		JwtJsonBuilder jwtBuilder = new JsonSmartJwtJsonBuilder()
				.issuedAt(issuedAt).expirationTime(expiresAt).issuer(key);

		CanonicalHttpUriRequest canonical = new CanonicalHttpUriRequest(method,
				apiPath, contextPath, new HashMap());
		JwtClaimsBuilder.appendHttpRequestClaims(jwtBuilder, canonical);

		JwtWriterFactory jwtWriterFactory = new NimbusJwtWriterFactory();
		String jwtbuilt = jwtBuilder.build();
		String jwtToken = jwtWriterFactory
				.macSigningWriter(SigningAlgorithm.HS256, sharedSecret)
				.jsonToJwt(jwtbuilt);

		String apiUrl = baseUrl + apiPath + "?jwt=" + jwtToken;
		return apiUrl;
	}
}
