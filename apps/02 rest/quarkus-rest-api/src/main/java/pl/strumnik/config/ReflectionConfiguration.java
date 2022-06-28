package pl.strumnik.config;

import io.quarkus.runtime.annotations.RegisterForReflection;
import pl.strumnik.domain.Address;
import pl.strumnik.domain.Authority;
import pl.strumnik.domain.User;

// https://quarkus.io/guides/writing-native-applications-tips
@RegisterForReflection(targets = {User.class, Address.class, Authority.class})
public class ReflectionConfiguration {
}
