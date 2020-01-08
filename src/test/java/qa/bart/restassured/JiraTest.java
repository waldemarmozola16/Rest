package qa.bart.restassured;


public class JiraTest extends HostnameTest
{
    private static final String JIRA_HOSTNAME = "https://infoshare-test-instance-one.atlassian.net";
    protected static String username = "bszulc.infoshare.one@gmail.com";
    protected static String password = "qjoB4Hrh6ARIXEtcQj5D3BC3";
    protected static String user = "admin";

    public JiraTest()
    {
        super(JIRA_HOSTNAME);
    }
}
