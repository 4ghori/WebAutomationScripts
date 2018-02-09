package Utility.JIRA;

import java.net.URI;

/**
 * This Utility class is primarily for JWT Generator
 *
 * @author MSG QA Automation Team
 * @version 1.0
 * @category MSG Automation Framework JWT
 * @since 02/08/2017, 10:00AM EST
 */

public interface JwtGenerator {

	String generateJWT(String requestMethod, URI uri,
			int jwtExpiryWindowSeconds);
}
