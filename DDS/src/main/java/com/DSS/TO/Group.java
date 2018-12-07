package com.DSS.TO;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"web_url",
"name",
"path",
"description",
"visibility",
"lfs_enabled",
"avatar_url",
"request_access_enabled",
"full_name",
"full_path",
"parent_id"
})
public class Group {

@JsonProperty("id")
private Integer id;
@JsonProperty("web_url")
private String webUrl;
@JsonProperty("name")
private String name;
@JsonProperty("path")
private String path;
@JsonProperty("description")
private String description;
@JsonProperty("visibility")
private String visibility;
@JsonProperty("lfs_enabled")
private Boolean lfsEnabled;
@JsonProperty("avatar_url")
private Object avatarUrl;
@JsonProperty("request_access_enabled")
private Boolean requestAccessEnabled;
@JsonProperty("full_name")
private String fullName;
@JsonProperty("full_path")
private String fullPath;
@JsonProperty("parent_id")
private Object parentId;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

@JsonProperty("web_url")
public String getWebUrl() {
return webUrl;
}

@JsonProperty("web_url")
public void setWebUrl(String webUrl) {
this.webUrl = webUrl;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("path")
public String getPath() {
return path;
}

@JsonProperty("path")
public void setPath(String path) {
this.path = path;
}

@JsonProperty("description")
public String getDescription() {
return description;
}

@JsonProperty("description")
public void setDescription(String description) {
this.description = description;
}

@JsonProperty("visibility")
public String getVisibility() {
return visibility;
}

@JsonProperty("visibility")
public void setVisibility(String visibility) {
this.visibility = visibility;
}

@JsonProperty("lfs_enabled")
public Boolean getLfsEnabled() {
return lfsEnabled;
}

@JsonProperty("lfs_enabled")
public void setLfsEnabled(Boolean lfsEnabled) {
this.lfsEnabled = lfsEnabled;
}

@JsonProperty("avatar_url")
public Object getAvatarUrl() {
return avatarUrl;
}

@JsonProperty("avatar_url")
public void setAvatarUrl(Object avatarUrl) {
this.avatarUrl = avatarUrl;
}

@JsonProperty("request_access_enabled")
public Boolean getRequestAccessEnabled() {
return requestAccessEnabled;
}

@JsonProperty("request_access_enabled")
public void setRequestAccessEnabled(Boolean requestAccessEnabled) {
this.requestAccessEnabled = requestAccessEnabled;
}

@JsonProperty("full_name")
public String getFullName() {
return fullName;
}

@JsonProperty("full_name")
public void setFullName(String fullName) {
this.fullName = fullName;
}

@JsonProperty("full_path")
public String getFullPath() {
return fullPath;
}

@JsonProperty("full_path")
public void setFullPath(String fullPath) {
this.fullPath = fullPath;
}

@JsonProperty("parent_id")
public Object getParentId() {
return parentId;
}

@JsonProperty("parent_id")
public void setParentId(Object parentId) {
this.parentId = parentId;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}