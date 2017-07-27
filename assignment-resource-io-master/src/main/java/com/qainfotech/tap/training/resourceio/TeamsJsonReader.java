package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
public class TeamsJsonReader{
    
    /**
     * get a list of individual objects from db json file
     * 
     * @return 
     * @throws ParseException 
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public List<Individual> getListOfIndividuals() throws FileNotFoundException, IOException, ParseException{
    	 FileReader filereader=new FileReader("src/test/resources/db.json");
    	 JSONParser parser=new JSONParser();
    	 Object obj=parser.parse(filereader);
    	 JSONObject object=(JSONObject) obj;
    	 JSONArray array=(JSONArray) object.get("individuals");
    	 List<Individual> list=new ArrayList();
    	 Map<String, Object> map=new HashMap<>();
    	 
    	 for(int i=0;i<array.size();i++)
    	 {
    		 JSONObject obj1=(JSONObject) array.get(i);
    		 map.put("name",obj1.get("name").toString() );
    		 map.put("id", (Object) ((Long) obj1.get("id")).intValue());
				map.put("active", (Object) obj1.get("active"));
    		 
    		 Individual individual=new Individual(map);
    		 list.add(individual);
    	 }
		return list;
    	 
    	 

    }
    
    /**
     * get individual object by id
     * 
     * @param id individual id
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     * @throws ParseException 
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public Individual getIndividualById(Integer id) throws ObjectNotFoundException, FileNotFoundException, IOException, ParseException{
    	TeamsJsonReader teamsjsonreader=new TeamsJsonReader();
    	List<Individual> list=teamsjsonreader.getListOfIndividuals();
    	for(int i=0;i<list.size();i++)
    	{
    		if(list.get(i).getId().equals(id))
    		{
    			return list.get(i);
    		}
    		
    		
    	}
    	throw new ObjectNotFoundException("Individual","id",id.toString());
       
    }
    
    /**
     * get individual object by name
     * 
     * @param name
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     * @throws ParseException 
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public Individual getIndividualByName(String name) throws ObjectNotFoundException, FileNotFoundException, IOException, ParseException{
    	TeamsJsonReader teamsjsonreader=new TeamsJsonReader();
    	List<Individual> list=teamsjsonreader.getListOfIndividuals();
    	
    	for(int i=0;i<list.size();i++)
    	{
    		if(list.get(i).getName().equals(name))
    		{
    			return list.get(i);
    		}
    	}
    	throw new ObjectNotFoundException("Individual","name",name.toString());
       
    }
    
    
    /**
     * get a list of individual objects who are not active
     * 
     * @return List of inactive individuals object
     * @throws ParseException 
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public List<Individual> getListOfInactiveIndividuals() throws FileNotFoundException, IOException, ParseException{
    	TeamsJsonReader teamsjsonreader=new TeamsJsonReader();
    	List<Individual> list=teamsjsonreader.getListOfIndividuals();
    	List<Individual> inactivememberslist=new ArrayList();
    	
    	for(int i=0;i<list.size();i++)
    	{
    		if(list.get(i).isActive()==false)
    		{
    			 inactivememberslist.add(list.get(i));
    			
    		}
    	}
    	 return inactivememberslist;
    	
    	
    	
       // throw new UnsupportedOperationException("Not implemented.");
    }
    
    
    /**
     * get a list of individual objects who are active
     * 
     * @return List of active individuals object
     * @throws ParseException 
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public List<Individual> getListOfActiveIndividuals() throws FileNotFoundException, IOException, ParseException{
    	TeamsJsonReader teamsjsonreader=new TeamsJsonReader();
    	List<Individual> list=teamsjsonreader.getListOfIndividuals();
    	List<Individual> activememberslist=new ArrayList();
    	
    	for(int i=0;i<list.size();i++)
    	{
    		if(list.get(i).isActive()==true)
    		{
    			 activememberslist.add(list.get(i));
    			
    		}
    	}
    	 return activememberslist;
    	
    	
    	
    	
        //throw new UnsupportedOperationException("Not implemented.");
    }
    
    /**
     * get a list of team objects from db json
     * 
     * @return 
     * @throws ParseException 
     * @throws IOException 
     * @throws ObjectNotFoundException 
     */
    public List<Team> getListOfTeams() throws IOException, ParseException, ObjectNotFoundException{
    	
    	FileReader filereader=new FileReader("src/test/resources/db.json");
    	JSONParser parser=new JSONParser();
    	Object obj=parser.parse(filereader);
    	JSONObject jsonobject=(JSONObject) obj;
    	JSONArray jsonarray=(JSONArray) jsonobject.get("teams");
    	Map<String, Object> map=new HashMap<>();
    	TeamsJsonReader team=new TeamsJsonReader();
    	List<Team> teamlist=new ArrayList<>();
    	
    	for(int i=0;i<jsonarray.size();i++)
    	{
    		List<Individual> memberslist=new ArrayList<>();
    		JSONObject individualobject=(JSONObject) jsonarray.get(i);
    		map.put("name",individualobject.get("name").toString());
    		 map.put("id", (Object) ((Long) individualobject.get("id")).intValue());
    		
    		JSONArray jsonmemberarray=(JSONArray) individualobject.get("members");
    		
    		for(int j=0;j<jsonmemberarray.size();j++)
    		{
    			
    	
    			Individual in=team.getIndividualById(((Long)jsonmemberarray.get(j)).intValue());
    			memberslist.add(in);
    		}
    		map.put("members", memberslist);
    		teamlist.add(new Team(map));

    		
    	}
   
    	return teamlist;
    	
    	
    	 
    	
}
  
}
