package com.animasugit.rest.webservices.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
    
    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean(){
        SomeBean someBean = new SomeBean("value1","value2","value3");
        // Create a mapping on somebean
        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        // add a filter on the mapping
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
        // create a filter provider
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        // set the filter provider to the mapping originally created
        mapping.setFilters(filters);

        return mapping;
    }
}
