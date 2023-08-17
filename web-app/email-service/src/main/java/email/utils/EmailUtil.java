package email.utils;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;

import java.util.Collection;
import java.util.function.Function;

public class EmailUtil {
    public static Function<String, InternetAddress> toInternetAddress() {
        return address -> {
            try {
                return new InternetAddress(address);
            } catch (AddressException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static InternetAddress[] toInternetAddressArray(Collection<String> addresses) {
        if (addresses == null) {
            return new InternetAddress[]{};
        }
        return addresses.stream().map(toInternetAddress()).toArray(InternetAddress[]::new);
    }

}
