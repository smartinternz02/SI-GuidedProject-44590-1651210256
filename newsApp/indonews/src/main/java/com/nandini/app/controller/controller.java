package com.nandini.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class controller {
	ApiCalls ap=new ApiCalls();
	@Autowired
	Repo repo;
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView home(ModelAndView modelAndView)
	{
		modelAndView.setViewName("index");
		return modelAndView;
	}
	@RequestMapping(value="/ng", method=RequestMethod.GET) 
	public ModelAndView nghome(ModelAndView modelAndView) throws IOException, InterruptedException
	{
		String map=ap.newsGlance();
		ObjectMapper mapper = new ObjectMapper();
	    Map<String, Object> RecMap=mapper.readValue(map, Map.class);
	    ArrayList<String> data=(ArrayList<String>)RecMap.get("value");
	  
	    Iterator it=data.iterator();
	    Iterable<Object> iterable = (Iterable<Object>) StreamSupport.stream(Spliterators.spliteratorUnknownSize(it, 0),false).collect(Collectors.toList());
	   
	    modelAndView.addObject("newsList",iterable);
		modelAndView.setViewName("news");
		return modelAndView;
	}
	@RequestMapping(value="/news", method=RequestMethod.GET)
	public ModelAndView nhome(ModelAndView modelAndView) throws IOException, InterruptedException
	{
		String map=ap.news();
		ObjectMapper mapper = new ObjectMapper();
	    Map<String, Object> RecMap=mapper.readValue(map, Map.class);
	    ArrayList<String> data=(ArrayList<String>)RecMap.get("value");

	    Iterator it=data.iterator();
	    Iterable<Object> iterable = (Iterable<Object>) StreamSupport.stream(Spliterators.spliteratorUnknownSize(it, 0),false).collect(Collectors.toList());
	  
	    modelAndView.addObject("newsList",iterable);
		modelAndView.setViewName("news2");
		return modelAndView;
	}
	@RequestMapping(value="/srch", method=RequestMethod.GET) 
	public ModelAndView srgethome(ModelAndView modelAndView,Input input) throws IOException, InterruptedException
	{
		
	    modelAndView.addObject("input",input);
		modelAndView.setViewName("search");
		return modelAndView;
	}	
	@RequestMapping(value="/srch", method=RequestMethod.POST) 
	public ModelAndView srhome(ModelAndView modelAndView,Input input) throws IOException, InterruptedException
	{
		String map=ap.srch(input.getWord());
		ObjectMapper mapper = new ObjectMapper();
	    Map<String, Object> RecMap=mapper.readValue(map, Map.class);
	    ArrayList<String> data=(ArrayList<String>)RecMap.get("value");
	    System.out.println("data is: "+data);
	    Iterator it=data.iterator();
	    Iterable<Object> iterable = (Iterable<Object>) StreamSupport.stream(Spliterators.spliteratorUnknownSize(it, 0),false).collect(Collectors.toList());
	    modelAndView.addObject("input",input);
	    modelAndView.addObject("news",iterable);
		modelAndView.setViewName("search");
		return modelAndView;
	}	
}
