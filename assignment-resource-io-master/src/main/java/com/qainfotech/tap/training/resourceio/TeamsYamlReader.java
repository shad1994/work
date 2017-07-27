package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsYamlReader {

	/**
	 * get a list of individual objects from db yaml file
	 * 
	 * @return
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public List<Individual> getListOfIndividuals() throws FileNotFoundException {
		Yaml yaml = new Yaml();
		InputStream inputstream = new FileInputStream(new File("src/main/resources/db.yaml"));
		Map<String, Object> outermap = (Map<String, Object>) yaml.load(inputstream);
		ArrayList<Individual> list = (ArrayList<Individual>) outermap.get("individuals");

		List<Individual> newlist = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();

		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> innermap = (Map<String, Object>) list.get(i);

			Individual individual = new Individual(innermap);
			newlist.add(individual);

		}

		return newlist;
	}

	/**
	 * get individual object by id
	 * 
	 * @param id
	 *            individual id
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 * @throws FileNotFoundException
	 */
	public Individual getIndividualById(Integer id) throws ObjectNotFoundException, FileNotFoundException {
		TeamsYamlReader teamsyamlreader = new TeamsYamlReader();
		List<Individual> list = teamsyamlreader.getListOfIndividuals();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(id)) {
				return list.get(i);
			}

		}

		throw new ObjectNotFoundException("Individual", "id", id.toString());
	}

	/*
	 * public static void main(String args[]) throws FileNotFoundException,
	 * ObjectNotFoundException { TeamsYamlReader t=new TeamsYamlReader();
	 * t.getIndividualById(1); }
	 */

	/**
	 * get individual object by name
	 * 
	 * @param name
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 * @throws FileNotFoundException
	 */
	public Individual getIndividualByName(String name) throws ObjectNotFoundException, FileNotFoundException {
		TeamsYamlReader teamsyamlreader = new TeamsYamlReader();
		List<Individual> list = teamsyamlreader.getListOfIndividuals();
		int flag = 0, i;
		for (i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(name)) {
				flag = 1;
				break;

			}
		}
		if (flag == 1) {
			return list.get(i);
		} else

			throw new ObjectNotFoundException("Individual", "Name", name.toString());

	}
	public static void main(String args[]) throws FileNotFoundException, ObjectNotFoundException
	{
		TeamsYamlReader team=new TeamsYamlReader();
		team.getIndividualByName("vidushi");
	}

	/**
	 * get a list of individual objects who are not active
	 * 
	 * @return List of inactive individuals object
	 * @throws FileNotFoundException
	 */
	public List<Individual> getListOfInactiveIndividuals() throws FileNotFoundException {
		TeamsYamlReader teamsyamlreader = new TeamsYamlReader();
		List<Individual> list = teamsyamlreader.getListOfIndividuals();
		List<Individual> inactive = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isActive() == false) {

				inactive.add(list.get(i));

			}
		}

		return inactive;
		// throw new UnsupportedOperationException("Not implemented.");
	}

	/**
	 * get a list of individual objects who are active
	 * 
	 * @return List of active individuals object
	 * @throws FileNotFoundException
	 */
	public List<Individual> getListOfActiveIndividuals() throws FileNotFoundException {

		TeamsYamlReader teamsyamlreader = new TeamsYamlReader();
		List<Individual> list = teamsyamlreader.getListOfIndividuals();
		List<Individual> active = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isActive() == true) {

				active.add(list.get(i));

			}
		}

		return active;
		// throw new UnsupportedOperationException("Not implemented.");
	}

	/**
	 * get a list of team objects from db yaml
	 * 
	 * @return
	 * @throws FileNotFoundException 
	 */
	public List<Team> getListOfTeams() throws FileNotFoundException {
		Yaml yaml=new Yaml();
		InputStream input=new FileInputStream(new File("src/main/resources/db.yaml"));
		Map<String, Object> map=(Map<String, Object>) yaml.load(input);
		List<Team> list=(List<Team>) map.get("teams");
		List<Team> newlist=new ArrayList<>();
		
		for(int i=0;i<list.size();i++)
		{
			Map<String, Object> innermap=(Map<String, Object>) list.get(i);
			Team team=new Team(innermap);
			newlist.add(team);
		}
		return newlist;
		
		//throw new UnsupportedOperationException("Not implemented.");
	}
}
