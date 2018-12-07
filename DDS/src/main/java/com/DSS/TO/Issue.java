package com.DSS.TO;

import java.util.HashMap;
import java.util.List;
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
"iid",
"project_id",
"title",
"description",
"state",
"created_at",
"updated_at",
"closed_at",
"closed_by",
"labels",
"milestone",
"assignees",
"author",
"assignee",
"user_notes_count",
"upvotes",
"downvotes",
"due_date",
"confidential",
"discussion_locked",
"web_url"
})
public class Issue {

@JsonProperty("id")
private Integer id;
@JsonProperty("iid")
private Integer iid;
@JsonProperty("project_id")
private Integer projectId;
@JsonProperty("title")
private String title;
@JsonProperty("description")
private String description;
@JsonProperty("state")
private String state;
@JsonProperty("created_at")
private String createdAt;
@JsonProperty("updated_at")
private String updatedAt;
@JsonProperty("closed_at")
private Object closedAt;
@JsonProperty("closed_by")
private Object closedBy;
@JsonProperty("labels")
private List<String> labels = null;
@JsonProperty("milestone")
private Object milestone;
@JsonProperty("assignees")
private List<Object> assignees = null;
@JsonProperty("author")
private Author author;
@JsonProperty("assignee")
private Object assignee;
@JsonProperty("user_notes_count")
private Integer userNotesCount;
@JsonProperty("upvotes")
private Integer upvotes;
@JsonProperty("downvotes")
private Integer downvotes;
@JsonProperty("due_date")
private Object dueDate;
@JsonProperty("confidential")
private Boolean confidential;
@JsonProperty("discussion_locked")
private Object discussionLocked;
@JsonProperty("web_url")
private String webUrl;
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

@JsonProperty("iid")
public Integer getIid() {
return iid;
}

@JsonProperty("iid")
public void setIid(Integer iid) {
this.iid = iid;
}

@JsonProperty("project_id")
public Integer getProjectId() {
return projectId;
}

@JsonProperty("project_id")
public void setProjectId(Integer projectId) {
this.projectId = projectId;
}

@JsonProperty("title")
public String getTitle() {
return title;
}

@JsonProperty("title")
public void setTitle(String title) {
this.title = title;
}

@JsonProperty("description")
public String getDescription() {
return description;
}

@JsonProperty("description")
public void setDescription(String description) {
this.description = description;
}

@JsonProperty("state")
public String getState() {
return state;
}

@JsonProperty("state")
public void setState(String state) {
this.state = state;
}

@JsonProperty("created_at")
public String getCreatedAt() {
return createdAt;
}

@JsonProperty("created_at")
public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

@JsonProperty("updated_at")
public String getUpdatedAt() {
return updatedAt;
}

@JsonProperty("updated_at")
public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

@JsonProperty("closed_at")
public Object getClosedAt() {
return closedAt;
}

@JsonProperty("closed_at")
public void setClosedAt(Object closedAt) {
this.closedAt = closedAt;
}

@JsonProperty("closed_by")
public Object getClosedBy() {
return closedBy;
}

@JsonProperty("closed_by")
public void setClosedBy(Object closedBy) {
this.closedBy = closedBy;
}

@JsonProperty("labels")
public List<String> getLabels() {
return labels;
}

@JsonProperty("labels")
public void setLabels(List<String> labels) {
this.labels = labels;
}

@JsonProperty("milestone")
public Object getMilestone() {
return milestone;
}

@JsonProperty("milestone")
public void setMilestone(Object milestone) {
this.milestone = milestone;
}

@JsonProperty("assignees")
public List<Object> getAssignees() {
return assignees;
}

@JsonProperty("assignees")
public void setAssignees(List<Object> assignees) {
this.assignees = assignees;
}

@JsonProperty("author")
public Author getAuthor() {
return author;
}

@JsonProperty("author")
public void setAuthor(Author author) {
this.author = author;
}

@JsonProperty("assignee")
public Object getAssignee() {
return assignee;
}

@JsonProperty("assignee")
public void setAssignee(Object assignee) {
this.assignee = assignee;
}

@JsonProperty("user_notes_count")
public Integer getUserNotesCount() {
return userNotesCount;
}

@JsonProperty("user_notes_count")
public void setUserNotesCount(Integer userNotesCount) {
this.userNotesCount = userNotesCount;
}

@JsonProperty("upvotes")
public Integer getUpvotes() {
return upvotes;
}

@JsonProperty("upvotes")
public void setUpvotes(Integer upvotes) {
this.upvotes = upvotes;
}

@JsonProperty("downvotes")
public Integer getDownvotes() {
return downvotes;
}

@JsonProperty("downvotes")
public void setDownvotes(Integer downvotes) {
this.downvotes = downvotes;
}

@JsonProperty("due_date")
public Object getDueDate() {
return dueDate;
}

@JsonProperty("due_date")
public void setDueDate(Object dueDate) {
this.dueDate = dueDate;
}

@JsonProperty("confidential")
public Boolean getConfidential() {
return confidential;
}

@JsonProperty("confidential")
public void setConfidential(Boolean confidential) {
this.confidential = confidential;
}

@JsonProperty("discussion_locked")
public Object getDiscussionLocked() {
return discussionLocked;
}

@JsonProperty("discussion_locked")
public void setDiscussionLocked(Object discussionLocked) {
this.discussionLocked = discussionLocked;
}

@JsonProperty("web_url")
public String getWebUrl() {
return webUrl;
}

@JsonProperty("web_url")
public void setWebUrl(String webUrl) {
this.webUrl = webUrl;
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
