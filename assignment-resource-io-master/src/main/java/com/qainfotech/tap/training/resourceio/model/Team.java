package com.qainfotech.tap.training.resourceio.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Team {

	private final String name;
	private final Integer id;
	private final List<Individual> members;

	public Team(Map<String, Object> teamMap) {
		this.name = (String) teamMap.get("name");
		this.id = (Integer) teamMap.get("id");
		List<Individual> list = (List<Individual>) teamMap.get("members");
		this.members = list;

	}

	/**
	 * get team name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * get team id
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * get list of individuals that are members of this team
	 * 
	 * @return
	 */
	public List<Individual> getMembers() {
		return members;
	}

	/**
	 * get a list of individuals that are members of this team and are also active
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public List<Individual> getActiveMembers() throws IOException, ParseException {

		List<Individual> list = new ArrayList<>();
		List<Individual> activemembers = new ArrayList<>();

		list = this.getMembers();

		for (int i = 0; i < list.size(); i++) {
			

			if (list.get(i).isActive().equals(true)) {
				activemembers.add(list.get(i));
				System.out.println("hello");

			}

		}
		System.out.println(activemembers.toString());
		return activemembers;
		/*
		 * List<Individual> individualList = new ArrayList<>(); List<Individual>
		 * activeIndividualList = new ArrayList<>();
		 * 
		 * individualList = this.getMembers(); for (Individual e : individualList) {
		 * boolean active = e.isActive(); if (active) activeIndividualList.add(e); }
		 * 
		 * return activeIndividualList;
		 */

	}

	/**
	 * get a list of individuals that are members of this team but are inactive
	 * 
	 * @return
	 */
	public List<Individual> getInactiveMembers() {
		/*
		 * List<Individual> list=new ArrayList<>(); List<Individual> inactivemembers=new
		 * ArrayList<>(); list=this.getMembers();
		 * 
		 * for(int i=0;i<list.size();i++) {
		 * 
		 * if(list.get(i).isActive()==false) { inactivemembers.add(list.get(i));
		 * 
		 * } } return inactivemembers;
		 */
		List<Individual> individualList;
		List<Individual> inactiveIndividualList = new ArrayList<>();

		individualList = this.getMembers();

		for (Individual e : individualList) {
			if (e.isActive().compareTo(false) == 0)
				inactiveIndividualList.add(e);

		}

		return inactiveIndividualList;
	}

}
