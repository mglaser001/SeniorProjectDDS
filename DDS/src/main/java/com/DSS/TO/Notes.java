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
@JsonPropertyOrder({ "id", "body", "attachment", "author", "created_at", "updated_at", "system", "noteable_id",
		"noteable_type", "noteable_iid", "resolvable" })
public class Notes {

	@JsonProperty("id")
	private Integer id;
	@JsonProperty("body")
	private String body;
	@JsonProperty("attachment")
	private Object attachment;
	@JsonProperty("author")
	private Author author;
	@JsonProperty("created_at")
	private String createdAt;
	@JsonProperty("updated_at")
	private String updatedAt;
	@JsonProperty("system")
	private Boolean system;
	@JsonProperty("noteable_id")
	private Integer noteableId;
	@JsonProperty("noteable_type")
	private String noteableType;
	@JsonProperty("noteable_iid")
	private Integer noteableIid;
	@JsonProperty("resolvable")
	private Boolean resolvable;
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

	@JsonProperty("body")
	public String getBody() {
		return body;
	}

	@JsonProperty("body")
	public void setBody(String body) {
		this.body = body;
	}

	@JsonProperty("attachment")
	public Object getAttachment() {
		return attachment;
	}

	@JsonProperty("attachment")
	public void setAttachment(Object attachment) {
		this.attachment = attachment;
	}

	@JsonProperty("author")
	public Author getAuthor() {
		return author;
	}

	@JsonProperty("author")
	public void setAuthor(Author author) {
		this.author = author;
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

	@JsonProperty("system")
	public Boolean getSystem() {
		return system;
	}

	@JsonProperty("system")
	public void setSystem(Boolean system) {
		this.system = system;
	}

	@JsonProperty("noteable_id")
	public Integer getNoteableId() {
		return noteableId;
	}

	@JsonProperty("noteable_id")
	public void setNoteableId(Integer noteableId) {
		this.noteableId = noteableId;
	}

	@JsonProperty("noteable_type")
	public String getNoteableType() {
		return noteableType;
	}

	@JsonProperty("noteable_type")
	public void setNoteableType(String noteableType) {
		this.noteableType = noteableType;
	}

	@JsonProperty("noteable_iid")
	public Integer getNoteableIid() {
		return noteableIid;
	}

	@JsonProperty("noteable_iid")
	public void setNoteableIid(Integer noteableIid) {
		this.noteableIid = noteableIid;
	}

	@JsonProperty("resolvable")
	public Boolean getResolvable() {
		return resolvable;
	}

	@JsonProperty("resolvable")
	public void setResolvable(Boolean resolvable) {
		this.resolvable = resolvable;
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
