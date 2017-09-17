package za.co.dvt.interfaces;

import za.co.dvt.enums.RequestMethod;

/**
 * Created by sibusiso on 2017/09/16.
 */

public interface HttpClientDataProvider {

    public String RequestAsync(String requestUri, RequestMethod method);
}
