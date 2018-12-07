package com.DSS.DAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.DSS.TO.Group;
import com.DSS.TO.Issue;
import com.DSS.TO.Notes;
import com.DSS.TO.Project;

@SpringBootApplication
public final class GitlabDAO {
	public static String resourceURL = "https://gitlab.com/api/v4/";
	
	public static Group getGroup(String groupId) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		String url = resourceURL + "groups/" + groupId;
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<Group> response = restTemplate.exchange(url, HttpMethod.GET, entity, Group.class);
		return response.getBody();	
	}
	public static List<Issue> getIssuesFromProjectid(String projectid) {
		List<Issue> issues = new ArrayList<Issue>();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		String url = resourceURL + "projects/"+ projectid + "/issues";
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<Issue[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Issue[].class);
		for(Issue i: response.getBody()) {
			issues.add(i);
		}
		return issues;	
	}
	public static List<Notes> getNotes(String projectid, String issueiid) {
		List<Notes> notes = new ArrayList<Notes>();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		String url = resourceURL + "projects/"+ projectid + "/issues/" + issueiid +"/notes?private_token=UTpBGKPUeZe2tSTsaqbh";
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<Notes[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Notes[].class);
		for(Notes i: response.getBody()) {
			notes.add(i);
		}
		return notes;	
	}
}
