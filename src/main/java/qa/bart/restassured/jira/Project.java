package qa.bart.restassured.jira;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Project
{
    public final String key;
    public final String name;
    public final String description;
    public final String lead;
    public final String assigneeType;
    public final String projectTypeKey;
    public final String projectTemplateKey;

    @JsonCreator
    public Project(
            @JsonProperty ("key") String key,
            @JsonProperty ("name") String name,
            @JsonProperty ("description") String description,
            @JsonProperty ("lead") String lead,
            @JsonProperty ("assigneeType") String assigneeType,
            @JsonProperty ("projectTypeKey") String projectTypeKey,
            @JsonProperty ("projectTemplateKey") String projectTemplateKey
    )
    {
        this.key = key;
        this.name = name;
        this.description = description;
        this.lead = lead;
        this.assigneeType = assigneeType;
        this.projectTypeKey = projectTypeKey;
        this.projectTemplateKey = projectTemplateKey;
    }
}
