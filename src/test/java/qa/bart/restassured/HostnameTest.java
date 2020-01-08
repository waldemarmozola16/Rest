package qa.bart.restassured;


public class HostnameTest
{
    protected final String hostname;

    HostnameTest(String hostname)
    {
        this.hostname = hostname;
    }

    public String path(String path)
    {
        return hostname.concat(path);
    }
}
