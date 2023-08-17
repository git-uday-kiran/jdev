package draw.bytes;

import software.amazon.awssdk.auth.credentials.AwsCredentials;

public class AwsCredentialsImpl implements AwsCredentials {

    private static final String ACCESS_KEY_ID = "AKIAQMDMIO4A7ETP6GLF";
    private static final String SECRET_ACCESS_KEY = "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";

    @Override
    public String accessKeyId() {
        return ACCESS_KEY_ID;
    }

    @Override
    public String secretAccessKey() {
        return SECRET_ACCESS_KEY;
    }
}