package pl.strumnik.config;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.security.authentication.AuthorizationException;
import io.micronaut.security.authentication.DefaultAuthorizationExceptionHandler;
import jakarta.inject.Singleton;

@Singleton
@Replaces(DefaultAuthorizationExceptionHandler.class)
public class DefaultAuthorizationExceptionHandlerReplacement extends DefaultAuthorizationExceptionHandler {

  @Override
  protected MutableHttpResponse<?> httpResponseWithStatus(final HttpRequest request, final AuthorizationException e) {
    if (e.isForbidden())
      return HttpResponse.status(HttpStatus.FORBIDDEN);

    return HttpResponse.status(HttpStatus.UNAUTHORIZED)
        .header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"Realm\"");
  }
}